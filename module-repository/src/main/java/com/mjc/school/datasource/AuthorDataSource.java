package com.mjc.school.datasource;

import com.mjc.school.model.impl.AuthorEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Component
public class AuthorDataSource {
    private final Logger logger;
    private final List<AuthorEntity> authors;

    public AuthorDataSource() {
        logger = Logger.getLogger(AuthorDataSource.class.getName());
        authors = new ArrayList<>();
        loadAuthors();
    }

    private void loadAuthors(){
        Properties properties = new Properties();
        try (InputStream input = AuthorDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String authorFilePath = properties.getProperty("authorFilePath");
            var names = Files.readAllLines(Paths.get(authorFilePath));
            names.forEach(name -> authors.add(new AuthorEntity(
                    name,
                    LocalDateTime.now(),
                    LocalDateTime.now())));
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public List<AuthorEntity> getAuthors(){
        return authors;
    }
}
