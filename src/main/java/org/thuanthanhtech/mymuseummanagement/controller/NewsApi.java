package org.thuanthanhtech.mymuseummanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thuanthanhtech.mymuseummanagement.dto.NewsDTO;
import org.thuanthanhtech.mymuseummanagement.entity.News;
import org.thuanthanhtech.mymuseummanagement.service.NewsService;

import java.time.LocalDate;
import java.util.Date;
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

    @GetMapping("/get-all")
    public ResponseEntity<Page<News>> getAllNews(Pageable pageable, NewsDTO newsDTO) {
        return new ResponseEntity<>(newsService.getAllNews(pageable, newsDTO), HttpStatus.OK);
    }

    @GetMapping("/get-all-by-publish")
    public ResponseEntity<List<News>> getAllNewsByPublish(@RequestBody NewsDTO newsDTO) {
        return new ResponseEntity<>(newsService.getAllNewsByPublishAndType(newsDTO.getType()), HttpStatus.OK);
    }

    @GetMapping("/get-all-by-date")
    public ResponseEntity<List<News>> getAllNewsByDate(@RequestParam("startDate")
                                                       @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                                       @RequestParam("endDate")
                                                       @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {
        return new ResponseEntity<>(newsService.getAllByDate(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/count-all-news-publish")
    public ResponseEntity<Integer> countAllNewsActiveByDate(@RequestParam("startDate")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
                                                            @RequestParam("endDate")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        return new ResponseEntity<>(newsService.countNewsActive(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/count-all-news")
    public ResponseEntity<Integer> countAllNewsByDate(@RequestParam("startDate")
                                                      @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                                      @RequestParam("endDate")
                                                      @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {
        return new ResponseEntity<>(newsService.countNews(startDate, endDate), HttpStatus.OK);
    }
}
