package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thuanthanhtech.mymuseummanagement.entity.CategoryDetail;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {
    Optional<CategoryDetail> findByName(String name);

    List<CategoryDetail> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "SELECT cd.* FROM category_detail cd JOIN category c WHERE c.id = cd.category_id AND cd.status = 1 and c.id = :id", nativeQuery = true)
    List<CategoryDetail> findByCategoryIdAndStatus(@Param("id")  Long id);
}
