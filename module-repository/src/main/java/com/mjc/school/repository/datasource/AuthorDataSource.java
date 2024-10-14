package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.impl.AuthorEntity;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class AuthorDataSource {
    private final Logger logger;
    private final List<AuthorEntity> authors;

    public AuthorDataSource() {
        logger = Logger.getLogger(AuthorDataSource.class.getName());
        authors = new ArrayList<>();
        loadNews();
    }

    private void loadNews(){
        Properties properties = new Properties();
        try (InputStream input = NewsDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String authorFilePath = properties.getProperty("authorFilePath");
            try (var names = Files.lines(Paths.get(authorFilePath))) {
               Long id = 1L;
               names.forEach(name -> authors.add(new AuthorEntity(
                       id,
                       name,
                       LocalDateTime.now(),
                       LocalDateTime.now())));
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public List<AuthorEntity> getAuthors(){
        return authors;
    }
}
