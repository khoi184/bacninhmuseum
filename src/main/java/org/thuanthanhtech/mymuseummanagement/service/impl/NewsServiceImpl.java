package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thuanthanhtech.mymuseummanagement.dto.NewsDTO;
import org.thuanthanhtech.mymuseummanagement.entity.News;
import org.thuanthanhtech.mymuseummanagement.repository.NewsRepository;
import org.thuanthanhtech.mymuseummanagement.service.NewsService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class    NewsServiceImpl implements NewsService {
    private final NewsRepository  newsRepository;

    @Override
    @SneakyThrows
    public News createNews(News news) {
        news.setType(news.getType());
        news.setName(news.getName());
        news.setTitle(news.getTitle());
        news.setContent(news.getContent());
        news.setAuthor(news.getAuthor());
        news.setSlug(news.getSlug());
        news.setUpdateBy(news.getUpdateBy());
        news.setPublish(Constants.PUBLISH_ACTIVE);
        news.setStatus(Constants.STATUS_ACTIVE);
        newsRepository.save(news);

        return news;
    }

    @SneakyThrows
    @Override
    public News updateNews(News news, Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if (optional.isPresent()) {
            News ne = optional.get();

            ne.setType(news.getType());
            ne.setName(news.getName());
            ne.setTitle(news.getTitle());
            ne.setContent(news.getContent());
            ne.setAuthor(news.getAuthor());
            ne.setSlug(news.getSlug());
            ne.setUpdateBy(news.getUpdateBy());
            ne.setPublish(news.getPublish());
            ne.setModifiedDate(new Date());
            news.setStatus(Constants.STATUS_ACTIVE);

            newsRepository.save(ne);
        } else {
            throw new Exception("Can not found by id: " + id);
        }
        return news;
    }

    @Override
    public void deleteNews(Long id) {
        List<News> newsList = newsRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(newsList)) {
            throw new NoSuchElementException("Can not found!");
        }
        for (News news: newsList) {
            news.setCreatDate(new Date());
            news.setStatus(Constants.STATUS_INACTIVE);
            newsRepository.save(news);
        }
    }

    @Override
    public Page<News> getAllNews(Pageable pageable, NewsDTO newsDTO) {
        String search;
        if (StringUtils.isEmpty(newsDTO.getSearch())) {
            search = "&&";
        } else {
            search = "%" + newsDTO.getSearch().toLowerCase() + "%";
        }
        return newsRepository.findAllBySearch(pageable, search);
    }

    @Override
    public List<News> getAllNewsByPublishAndType(Integer type) {
        return newsRepository.findAllByPublishAndType(type);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public List<News> getAllByDate(Date startDate, Date endDate) {
        return newsRepository.findAllByDate(startDate, endDate);
    }

    @Override
    public Integer countNewsActive(Date startDate, Date endDate) {
        return newsRepository.countAllByDate(startDate, endDate);
    }
}
