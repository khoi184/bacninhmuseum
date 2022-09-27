package org.thuanthanhtech.mymuseummanagement.service;

import org.thuanthanhtech.mymuseummanagement.entity.Category;

import javax.transaction.Transactional;
import java.util.Map;


public interface CategoryService {

    @Transactional
    Category createCategory(Category category) throws Exception;

    @Transactional
    Category updateCategory(Category category, Long id)  ;

    Map<String, Object> deleteCategory(Long id) ;

    Iterable<Category> findAll();

}
