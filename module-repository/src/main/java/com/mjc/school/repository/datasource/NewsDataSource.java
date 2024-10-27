package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.impl.NewsEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

@Component
public class NewsDataSource {
    private final AuthorDataSource authorDataSource;
    private final Logger logger;
    private final List<NewsEntity> allNews;

    public NewsDataSource(AuthorDataSource authorDataSource) {
        this.authorDataSource = authorDataSource;
        this.logger = Logger.getLogger(NewsDataSource.class.getName());
        this.allNews = new ArrayList<>();
        loadNews();
    }

    private void loadNews(){
        int INITIAL_CAPACITY = 20;
        Properties properties = new Properties();
        try (InputStream input = NewsDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String newsFilePath = properties.getProperty("newsFilePath");
            String contentFilePath = properties.getProperty("contentFilePath");
            var titles = Files.readAllLines(Paths.get(newsFilePath));
            var contents = Files.readAllLines(Paths.get(contentFilePath));
            var authors = authorDataSource.getAuthors();
            Random random = new Random();
            for (int i = 0; i < INITIAL_CAPACITY; i++){
                allNews.add(new NewsEntity(
                        titles.get(random.nextInt(titles.size())),
                        contents.get(random.nextInt(contents.size())),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        authors.get(random.nextInt(authors.size())).getId()
                ));
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public List<NewsEntity> getAllNews(){
        return allNews;
    }
}

