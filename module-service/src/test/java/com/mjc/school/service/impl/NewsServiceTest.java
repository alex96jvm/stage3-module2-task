package com.mjc.school.service.impl;

import com.mjc.school.datasource.AuthorDataSource;
import com.mjc.school.datasource.NewsDataSource;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.request.NewsRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NewsServiceTest {
    private static NewsService newsService;

    @BeforeAll
    static void setUp() {
        AuthorDataSource authorDataSource = new AuthorDataSource();
        NewsDataSource newsDataSource = new NewsDataSource(authorDataSource);
        NewsRepository newsRepository = new NewsRepository(newsDataSource);
        newsService = new NewsService(newsRepository);
    }

    @Test
    void getCountOfAllNews() {
        Assertions.assertEquals(20, newsService.readAll().size());
    }

    @Test
    void getFirstNews() {
        Assertions.assertNotNull(newsService.readById(1L));
    }

    @Test
    void createNews() {
        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setTitle("AGRICULTURE");
        newsRequest.setContent("The woman standing her ground.");
        newsRequest.setAuthorId(5L);
        newsService.create(newsRequest);
        Assertions.assertEquals("AGRICULTURE", newsService.readById(21L).getTitle());
    }

    @Test
    void updateNewsTitle() {
        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setId(15L);
        newsRequest.setTitle("AGRICULTURE");
        newsRequest.setContent("The woman standing her ground.");
        newsRequest.setAuthorId(5L);
        newsService.update(newsRequest);
        Assertions.assertEquals("AGRICULTURE", newsService.readById(15L).getTitle());
    }

    @Test
    void deleteNews() {
        Assertions.assertTrue(newsService.deleteById(3L));
    }
}
