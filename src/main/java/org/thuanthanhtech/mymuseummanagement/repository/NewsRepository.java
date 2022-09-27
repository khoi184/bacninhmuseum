package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.thuanthanhtech.mymuseummanagement.entity.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.publish = 1 and n.type = :type", nativeQuery = true)
    List<News> findAllByPublishAndType(@Param("type") Integer type);

    @Query(value = "select * from news n where n.status = 1 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<News> findAllBySearch(Pageable pageable, @Param("search") String search);
}
