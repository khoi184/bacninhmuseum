package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.thuanthanhtech.mymuseummanagement.entity.News;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.publish = 1 and n.type = :type", nativeQuery = true)
    List<News> findAllByPublishAndType(@Param("type") Integer type);

    @Query(value = "select * from news n where n.status = 1 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<News> findAllBySearch(Pageable pageable, @Param("search") String search);

    @Query(value = "SELECT * FROM news n WHERE n.create_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<News> findAllByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT count(n.name) FROM news n WHERE n.publish = 1 AND n.create_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    Integer countAllByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
//    @Query(value = "SELECT count(n.name) FROM news n WHERE n.publish = 1 AND date_sub(:date , INTERVAL 7 DAY)", nativeQuery = true)
//    Integer countAllByDate(@Param("date") Date endDate);
}
