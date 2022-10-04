package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thuanthanhtech.mymuseummanagement.entity.News;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.publish = 1 and n.type = :type", nativeQuery = true)
    List<News> findAllByPublishAndType(@Param("type") Integer type);

    @Query(value = "select * from news n where n.status = 1 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<News> findAllBySearch(Pageable pageable, @Param("search") String search);

    @Query(value = "SELECT * FROM news n WHERE n.create_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<News> findAllByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT count(n.name)  FROM News n WHERE n.publish = 1 and n.status = 1 and n.creatDate BETWEEN :startDate AND :endDate")
    Integer countAllActiveByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT count(n.name) FROM News n WHERE n.status = 1 and n.creatDate BETWEEN :startDate AND :endDate")
    Integer countAllNews(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
