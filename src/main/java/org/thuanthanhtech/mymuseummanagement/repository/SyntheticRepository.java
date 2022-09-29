package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thuanthanhtech.mymuseummanagement.entity.Synthetic;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SyntheticRepository extends JpaRepository<Synthetic, Long> {
    Optional<Synthetic> findByName(String name);

    List<Synthetic> findALlByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from synthetic s where s.status = 1 and lower(concat(coalesce(s.name,''), coalesce(s.title ,''))) like lower(:search)", nativeQuery = true)
    Page<Synthetic> findAllBySearch(Pageable pageable, @Param("search") String search);
}
