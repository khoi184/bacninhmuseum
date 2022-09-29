package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thuanthanhtech.mymuseummanagement.entity.Category;
import org.thuanthanhtech.mymuseummanagement.entity.CategoryDetail;
import org.thuanthanhtech.mymuseummanagement.repository.CategoryDetailRepository;
import org.thuanthanhtech.mymuseummanagement.repository.CategoryRepository;
import org.thuanthanhtech.mymuseummanagement.service.CategoryService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public static final String CATEGORY = "Category";

    private final CategoryDetailRepository categoryDetailRepository;

    @Transactional
    @Override
    public Category createCategory(Category category) throws Exception {
        validRequest(category);
        Optional<Category> cat = categoryRepository.findByName(category.getName());
        Category categories = new Category();
        if (cat.isEmpty()) {
            categories.setName(category.getName());
            categories.setStatus(Constants.STATUS_ACTIVE);
            categoryRepository.save(category);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return category;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Category updateCategory(Category category, Long id) {
        validRequest(category);
        Optional<Category> categories = categoryRepository.findById(id);
        if (categories.isPresent()) {
            Category cat = categories.get();

            Optional<Category> categoryName = categoryRepository.findByName(category.getName());
            if (categoryName.isEmpty() || cat.getId().equals(categoryName.get().getId())) {
                cat.setName(category.getName());
                cat.setStatus(Constants.STATUS_ACTIVE);
                categoryRepository.save(cat);
            } else {
                throw new MessageDescriptorFormatException("Name existed");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return category;
    }

    @SneakyThrows
    @Transactional
    @Override
    public Map<String, Object> deleteCategory(Long id) {
        List<Category> categories = categoryRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categories)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (Category category : categories) {
            category.setStatus(Constants.STATUS_INACTIVE);
            category.setModifiedDate(new Date());
            categoryRepository.save(category);
        }

        List<CategoryDetail> categoryDetailList = categoryDetailRepository.findByCategoryIdAndStatus();
        if (CollectionUtils.isEmpty(categoryDetailList)) {
            throw new MessageDescriptorFormatException("Category detail can not found!");
        }
        for (CategoryDetail categoryDetail : categoryDetailList) {
            categoryDetail.setStatus(Constants.STATUS_INACTIVE);
            categoryDetailRepository.save(categoryDetail);
        }

        Map<String, Object> result = new HashMap<>();
        result.put(CATEGORY, categories);
        return result;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @SneakyThrows
    private void validRequest(Category category) {
        if (category == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }

    }

}
