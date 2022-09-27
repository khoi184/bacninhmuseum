package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thuanthanhtech.mymuseummanagement.entity.CategoryDetail;
import org.thuanthanhtech.mymuseummanagement.repository.CategoryDetailRepository;
import org.thuanthanhtech.mymuseummanagement.service.CategoryDetailService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryDetailServiceImpl implements CategoryDetailService {

    private final CategoryDetailRepository categoryDetailRepository;

    @SneakyThrows
    @Override
    public CategoryDetail createCategoryDetail(CategoryDetail categoryDetail) {
        Optional<CategoryDetail> optionalCategoryDetail = categoryDetailRepository.findByName(categoryDetail.getName());
        CategoryDetail categoryDetails = new CategoryDetail();
        if (optionalCategoryDetail.isEmpty()) {
            categoryDetails.setName(categoryDetail.getName());
            categoryDetails.setTitle(categoryDetail.getTitle());
            categoryDetails.setContent(categoryDetail.getContent());
            categoryDetails.setDescription(categoryDetail.getDescription());
            categoryDetails.setStatus(Constants.STATUS_ACTIVE);
            categoryDetailRepository.save(categoryDetail);
        } else {
            throw new Exception("Name existed!");
        }
        return categoryDetail;
    }

    @SneakyThrows
    @Override
    public CategoryDetail updateCategoryDetail(CategoryDetail categoryDetail, Long id) {
        Optional<CategoryDetail> optionalCategoryDetail = categoryDetailRepository.findById(id);
        if (optionalCategoryDetail.isPresent()) {
            CategoryDetail categoryDetails = optionalCategoryDetail.get();
            Optional<CategoryDetail> optional = categoryDetailRepository.findByName(categoryDetail.getName());
            if (optional.isEmpty() || categoryDetail.getId().equals(optional.get().getId())) {
                categoryDetails.setName(categoryDetail.getName());
                categoryDetails.setTitle(categoryDetail.getTitle());
                categoryDetails.setContent(categoryDetail.getContent());
                categoryDetails.setDescription(categoryDetail.getDescription());
                categoryDetails.setStatus(Constants.STATUS_ACTIVE);
                categoryDetailRepository.save(categoryDetail);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: !" + id);
        }
        return categoryDetail;
    }

    @SneakyThrows
    @Override
    public void deleteCategoryDetail(Long id) {
        List<CategoryDetail> categoryDetailList = categoryDetailRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categoryDetailList)) {
            throw new Exception("Can not found!");
        }
        for (CategoryDetail categoryDetail : categoryDetailList) {
            categoryDetail.setStatus(Constants.STATUS_INACTIVE);
            categoryDetailRepository.save(categoryDetail);
        }
    }

    @Override
    public List<CategoryDetail> getAllCategoryDetail() {
        return categoryDetailRepository.findAll();
    }
}
