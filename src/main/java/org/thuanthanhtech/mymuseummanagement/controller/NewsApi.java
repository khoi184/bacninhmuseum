package org.thuanthanhtech.mymuseummanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.dto.NewsDTO;
import org.thuanthanhtech.mymuseummanagement.entity.News;
import org.thuanthanhtech.mymuseummanagement.service.NewsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsApi {
    private final NewsService newsService;

    @PostMapping("/create")
    public ResponseEntity<?> createSynthetic(@RequestBody News news) {
        newsService.createNews(news);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNews(@RequestBody News news, @PathVariable("id") Long id) {
        newsService.updateNews(news, id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all-by-search")
    public ResponseEntity<Page<News>> getAllNews(Pageable pageable, @RequestBody NewsDTO newsDTO) {
        return new ResponseEntity<>(newsService.getAllNews(pageable,newsDTO),HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(newsService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Can not found by id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-by-publish")
    public ResponseEntity<List<News>> getAllNewsByPublish(@RequestParam("type") Integer type) {
        return new ResponseEntity<>(newsService.getAllNewsByPublishAndType(type),HttpStatus.OK);
    }
}
