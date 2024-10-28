package com.mjc.school;

import com.mjc.school.controller.processor.AuthorCommandProcessor;
import com.mjc.school.controller.processor.NewsCommandProcessor;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class View {
    private final NewsCommandProcessor newsCommandProcessor;
    private final AuthorCommandProcessor authorCommandProcessor;
    private final String OPERATION = "Operation: ";
    private final String ENTER_NEWS_ID = "Enter news id:";
    private final String ENTER_AUTHOR_ID = "Enter author id:";
    private final String ENTER_AUTHOR_NAME = "Enter author name:";
    private final String ENTER_NEWS_TITLE = "Enter news title:";
    private final String ENTER_NEWS_CONTENT = "Enter news content:";
    private final String NEWS = "News";
    private final String AUTHOR = "Author";

    @Autowired
    public View(NewsController newsController, AuthorController authorController){
        newsCommandProcessor = new NewsCommandProcessor(newsController);
        authorCommandProcessor = new AuthorCommandProcessor(authorController);
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
                    readAllNews();
                    break;
                case 2:
                    readByIdNews(scanner);
                    break;
                case 3:
                    createNews(scanner);
                    break;
                case 4:
                    updateNews(scanner);
                    break;
                case 5:
                    deleteNews(scanner);
                    break;
                case 6:
                    readAllAuthors();
                    break;
                case 7:
                    readByIdAuthor(scanner);
                    break;
                case 8:
                    createAuthor(scanner);
                    break;
                case 9:
                    updateAuthor(scanner);
                    break;
                case 10:
                    deleteAuthor(scanner);
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

    private void readAllNews() {
        System.out.printf("%sGet all news.%n", OPERATION);
        System.out.println(newsCommandProcessor.processCommand("readAll", null));
    }

    private void readByIdNews(Scanner scanner) {
        System.out.printf("%sGet news by id.%n", OPERATION);
        Map<String, Object> params = new HashMap<>();
        System.out.println(ENTER_NEWS_ID);
        try {
            params.put("id", validateNumericValue(scanner.nextLine(), NEWS));
            System.out.println(newsCommandProcessor.processCommand("readById", params));
        } catch (Exception e) {
            readByIdNews(scanner);
        }
    }

    private void createNews(Scanner scanner) {
        System.out.printf("%sCreate news.%n", OPERATION);
        Map<String, Object> params = new HashMap<>();
        System.out.println(ENTER_NEWS_TITLE);
        params.put("title", scanner.nextLine());
        System.out.println(ENTER_NEWS_CONTENT);
        params.put("content", scanner.nextLine());
        System.out.println(ENTER_AUTHOR_ID);
        try {
            params.put("authorId", validateNumericValue(scanner.nextLine(), AUTHOR));
            System.out.println(newsCommandProcessor.processCommand("create", params));
        } catch (Exception e) {
            createNews(scanner);
        }
    }

    private void updateNews(Scanner scanner) {
        System.out.printf("%sUpdate news.%n", OPERATION);
        Map<String, Object> params = new HashMap<>();
        System.out.println(ENTER_NEWS_ID);
        try {
            params.put("id", validateNumericValue(scanner.nextLine(), NEWS));
        } catch (Exception e) {
            updateNews(scanner);
        }
        System.out.println(ENTER_NEWS_TITLE);
        params.put("title", scanner.nextLine());
        System.out.println(ENTER_NEWS_CONTENT);
        params.put("content", scanner.nextLine());
        System.out.println(ENTER_AUTHOR_ID);
        try {
            params.put("authorId", validateNumericValue(scanner.nextLine(), AUTHOR));
            System.out.println(newsCommandProcessor.processCommand("update", params));
        } catch (Exception e) {
            updateNews(scanner);
        }
    }

    private void deleteNews(Scanner scanner) {
        Map<String, Object> params = new HashMap<>();
        System.out.printf("%sRemove news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            params.put("id", validateNumericValue(scanner.nextLine(), NEWS));
            System.out.println(newsCommandProcessor.processCommand("delete", params));
        } catch (Exception e) {
            deleteNews(scanner);
        }
    }

    private void readAllAuthors() {
        System.out.printf("%sGet all authors.%n", OPERATION);
        System.out.println(authorCommandProcessor.processCommand("readAll", null));
    }

    private void readByIdAuthor(Scanner scanner) {
        System.out.printf("%sGet author by id.%n", OPERATION);
        Map<String, Object> params = new HashMap<>();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            params.put("id", validateNumericValue(scanner.nextLine(), AUTHOR));
            System.out.println(authorCommandProcessor.processCommand("readById", params));
        } catch (Exception e) {
            readByIdAuthor(scanner);
        }
    }

    private void createAuthor(Scanner scanner) {
        System.out.printf("%sCreate author.%n", OPERATION);
        Map<String, Object> params = new HashMap<>();
        System.out.println(ENTER_AUTHOR_NAME);
        params.put("name", scanner.nextLine());
        try {
            System.out.println(authorCommandProcessor.processCommand("create", params));
        } catch (Exception e) {
            createAuthor(scanner);
        }

    }

    private void updateAuthor(Scanner scanner) {
        System.out.printf("%sUpdate author.%n", OPERATION);
        Map<String, Object> params = new HashMap<>();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            params.put("id", validateNumericValue(scanner.nextLine(), AUTHOR));
        } catch (Exception e) {
            updateAuthor(scanner);
        }
        System.out.println(ENTER_AUTHOR_NAME);
        params.put("name", scanner.nextLine());
        try {
            System.out.println(authorCommandProcessor.processCommand("update", params));
        } catch (Exception e) {
            updateAuthor(scanner);
        }
    }

    private void deleteAuthor(Scanner scanner) {
        Map<String, Object> params = new HashMap<>();
        System.out.printf("%sRemove author by id.%n", OPERATION);
        System.out.println(ENTER_AUTHOR_ID);
        try {
            params.put("id", validateNumericValue(scanner.nextLine(), AUTHOR));
            System.out.println(authorCommandProcessor.processCommand("delete", params));
        } catch (Exception e) {
            deleteAuthor(scanner);
        }
    }

    private Long validateNumericValue(String id, String subValue) throws Exception {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: "  + subValue + " Id should be number");
            throw new Exception();
        }
    }
}
