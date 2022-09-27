package org.thuanthanhtech.mymuseummanagement.service;

import org.springframework.stereotype.Service;
import org.thuanthanhtech.mymuseummanagement.dto.CategoryDetailDTO;
import org.thuanthanhtech.mymuseummanagement.entity.CategoryDetail;

import java.util.List;

@Service
public interface CategoryDetailService {

    CategoryDetail createCategoryDetail(CategoryDetail categoryDetail);

    CategoryDetail updateCategoryDetail(CategoryDetail categoryDetail, Long id);

    void deleteCategoryDetail(Long id);

    List<CategoryDetail> getAllCategoryDetail();
}
