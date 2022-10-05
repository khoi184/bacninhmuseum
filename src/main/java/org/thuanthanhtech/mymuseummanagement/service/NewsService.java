package org.thuanthanhtech.mymuseummanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thuanthanhtech.mymuseummanagement.dto.NewsDTO;
import org.thuanthanhtech.mymuseummanagement.entity.News;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface NewsService {

    @Transactional
    News createNews(News news);

    @Transactional
    News updateNews(News news, Long id);

    @Transactional
    void deleteNews(Long id);

    Page<News> getAllNews(Pageable pageable, NewsDTO newsDTO);

    List<News> getAllNewsByPublishAndType(Integer type);

    List<News> getAll();

    List<News> getAllByDate(String startDate, String endDate);

    Integer countNewsActive(String startDate, String endDate);

    Integer countNews(String startDate, String endDate);

}
