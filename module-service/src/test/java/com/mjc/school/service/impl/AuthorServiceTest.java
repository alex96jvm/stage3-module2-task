package com.mjc.school.service.impl;

import com.mjc.school.datasource.AuthorDataSource;
import com.mjc.school.datasource.NewsDataSource;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.request.AuthorRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuthorServiceTest {
    private static AuthorService authorService;

    @BeforeAll
    static void setUp() {
        AuthorDataSource authorDataSource = new AuthorDataSource();
        NewsDataSource newsDataSource = new NewsDataSource(authorDataSource);
        AuthorRepository authorRepository = new AuthorRepository(authorDataSource, newsDataSource);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void getCountOfAllAuthors() {
        Assertions.assertEquals(30, authorService.readAll().size());
    }

    @Test
    void getFirstAuthor() {
        Assertions.assertNotNull(authorService.readById(1L));
    }

    @Test
    void createAuthor() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("Leo Tolstoy");
        authorService.create(authorRequest);
        Assertions.assertEquals("Leo Tolstoy", authorService.readById(31L).getName());
    }

    @Test
    void updateAuthorName() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setId(2L);
        authorRequest.setName("Dean Koontz");
        authorService.update(authorRequest);
        Assertions.assertEquals("Dean Koontz", authorService.readById(2L).getName());
    }

    @Test
    void deleteNews() {
        Assertions.assertTrue(authorService.deleteById(3L));
    }
}
