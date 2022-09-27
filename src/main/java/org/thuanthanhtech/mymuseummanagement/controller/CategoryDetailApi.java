package org.thuanthanhtech.mymuseummanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.dto.CategoryDetailDTO;
import org.thuanthanhtech.mymuseummanagement.entity.CategoryDetail;
import org.thuanthanhtech.mymuseummanagement.service.CategoryDetailService;

import java.util.List;

@RestController
@RequestMapping("category-detail")
@RequiredArgsConstructor
public class CategoryDetailApi {

    private final CategoryDetailService categoryDetailService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategoryDetail(@RequestBody CategoryDetail categoryDetail) {
        categoryDetailService.createCategoryDetail(categoryDetail);
        return new ResponseEntity<>(categoryDetail, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategoryDetail(@RequestBody CategoryDetail categoryDetail,
                                                  @PathVariable("id") Long id) {
        categoryDetailService.updateCategoryDetail(categoryDetail, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delelteCategoryDetail(@PathVariable("id") Long id) {
        categoryDetailService.deleteCategoryDetail(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDetail>> getAllCategoryDetail() {
        return new ResponseEntity<>(categoryDetailService.getAllCategoryDetail(), HttpStatus.OK);
    }
}
