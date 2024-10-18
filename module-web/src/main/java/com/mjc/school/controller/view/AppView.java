package com.mjc.school.controller.view;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.request.AuthorRequest;
import com.mjc.school.service.request.NewsRequest;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.AuthorNewsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AppView {
    private final NewsController newsController;
    private final AuthorController authorController;
    private final String OPERATION = "Operation: ";
    private final String ENTER_NEWS_ID = "Enter news id:";
    private final String ENTER_AUTHOR_ID = "Enter author id:";
    private final String ENTER_AUTHOR_NAME = "Enter author name:";
    private final String ENTER_NEWS_TITLE = "Enter news title:";
    private final String ENTER_NEWS_CONTENT = "Enter news content:";
    private final String NEWS = "News";
    private final String AUTHOR = "Author";

    @Autowired
    public AppView(NewsController newsController, AuthorController authorController){
        this.newsController = newsController;
        this.authorController = authorController;
        showControlMenu();
        selectOperation();
    }

    private void showControlMenu () {
        System.out.println("""
                Enter the number of operation:
                1 - Get all news.
                2 - Get news by id.
                3 - Create news.
                4 - Update news.
                5 - Remove news by id.
                6 - Get all authors.
                7 - Get author by id.
                8 - Create author.
                9 - Update author.
                10 - Remove author by id.
                0 - Exit."""
        );
    }

    private void selectOperation() {
        String NOT_FOUND = "Command not found.";
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    readAllNews().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println(readByIdNews(scanner));
                    break;
                case 3:
                    System.out.println(createNews(scanner));
                    break;
                case 4:
                    System.out.println(updateNews(scanner));
                    break;
                case 5:
                    System.out.println(deleteNews(scanner));
                    break;
                case 6:
                    readAllAuthors().forEach(System.out::println);
                    break;
                case 7:
                    System.out.println(readByIdAuthor(scanner));
                    break;
                case 8:
                    System.out.println(createAuthor(scanner));
                    break;
                case 9:
                    System.out.println(updateAuthor(scanner));
                    break;
                case 10:
                    System.out.println(deleteAuthor(scanner));
                    break;
                default:
                    System.out.println(NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            System.out.println(NOT_FOUND);
        }
        showControlMenu();
        selectOperation();
        scanner.close();
    }

    private List<NewsDto> readAllNews() {
        System.out.printf("%sGet all news.%n", OPERATION);
        return newsController.readAll();
    }

    private NewsDto readByIdNews(Scanner scanner) {
        System.out.printf("%sGet news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            return newsController.readById(id);
        } catch (AuthorNewsException authorNewsException) {
            System.out.println(authorNewsException);
            return readByIdNews(scanner);
        }
    }

    private NewsDto createNews(Scanner scanner) {
        System.out.printf("%sCreate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long authorId = validateNumericValue(scanner.nextLine(), AUTHOR);
            NewsRequest newsRequest = new NewsRequest(title, content, authorId);
            return newsController.create(newsRequest);
        } catch (AuthorNewsException authorNewsException){
            System.out.println(authorNewsException);
            return createNews(scanner);
        }
    }

    private NewsDto updateNews(Scanner scanner) {
        NewsRequest newsRequest = new NewsRequest();
        System.out.printf("%sUpdate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.nextLine(), NEWS);
            newsRequest.setId(id);
        } catch (AuthorNewsException authorNewsException){
            System.out.println(authorNewsException);
            updateNews(scanner);
        }
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long authorId = validateNumericValue(scanner.nextLine(), AUTHOR);
            newsRequest.setTitle(title);
            newsRequest.setContent(content);
            newsRequest.setAuthorId(authorId);
            return newsController.update(newsRequest);
        } catch (AuthorNewsException authorNewsException) {
            System.out.println(authorNewsException);
            return updateNews(scanner);
        }
    }

    private Boolean deleteNews(Scanner scanner) {
        System.out.printf("%sRemove news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            return newsController.deleteById(id);
        } catch (AuthorNewsException authorNewsException) {
            System.out.println(authorNewsException);
            return deleteNews(scanner);
        }
    }

    private List<AuthorDto> readAllAuthors() {
        System.out.printf("%sGet all authors.%n", OPERATION);
        return authorController.readAll();
    }

    private AuthorDto readByIdAuthor(Scanner scanner) {
        System.out.printf("%sGet author by id.%n", OPERATION);
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long id = validateNumericValue(scanner.next(), AUTHOR);
            return authorController.readById(id);
        } catch (AuthorNewsException authorNewsException) {
            System.out.println(authorNewsException);
            return readByIdAuthor(scanner);
        }
    }

    private AuthorDto createAuthor(Scanner scanner) {
        System.out.printf("%sCreate author.%n", OPERATION);
        System.out.println(ENTER_AUTHOR_NAME);
        String name = scanner.nextLine();
        AuthorRequest authorRequest = new AuthorRequest(name);
        return authorController.create(authorRequest);
    }

    private AuthorDto updateAuthor(Scanner scanner) {
        AuthorRequest authorRequest = new AuthorRequest();
        System.out.printf("%sUpdate author.%n", OPERATION);
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long id = validateNumericValue(scanner.nextLine(), AUTHOR);
            authorRequest.setId(id);
        } catch (AuthorNewsException authorNewsException){
            System.out.println(authorNewsException);
            updateAuthor(scanner);
        }
        System.out.println(ENTER_AUTHOR_NAME);
        String name = scanner.nextLine();
        authorRequest.setName(name);
        return authorController.update(authorRequest);
    }

    private Boolean deleteAuthor(Scanner scanner) {
        System.out.printf("%sRemove author by id.%n", OPERATION);
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long id = validateNumericValue(scanner.next(), AUTHOR);
            return authorController.deleteById(id);
        } catch (AuthorNewsException authorNewsException) {
            System.out.println(authorNewsException);
            return deleteAuthor(scanner);
        }
    }

    private Long validateNumericValue(String id, String subValue) throws AuthorNewsException {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new AuthorNewsException(ErrorCodes.NOT_NUMERIC, subValue + " Id should be number");
        }
    }
}
