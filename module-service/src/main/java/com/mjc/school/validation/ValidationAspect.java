package com.mjc.school.validation;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.request.AuthorRequest;
import com.mjc.school.request.NewsRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
    public final AuthorRepository authorRepository;
    public final NewsRepository newsRepository;

    public ValidationAspect(AuthorRepository authorRepository, NewsRepository newsRepository) {
        this.authorRepository = authorRepository;
        this.newsRepository = newsRepository;
    }

    @Before("@annotation(validateLength)")
    public void validateLength(JoinPoint joinPoint, ValidateLength validateLength) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof NewsRequest) {
                int titleLength = ((NewsRequest) arg).getTitle().length();
                int contentLength = ((NewsRequest) arg).getContent().length();
                if (titleLength < 5 || titleLength > 30) {
                    System.out.println(ErrorCodes.INVALID_LENGTH + "News title can not be less than 5 and more than 30 symbols. News title is " + titleLength);
                    throw new RuntimeException();
                }
                if (contentLength < 5 || contentLength > 255) {
                    System.out.println(ErrorCodes.INVALID_LENGTH + "News content can not be less than 5 and more than 255 symbols. News content is " + contentLength);
                    throw new RuntimeException();
                }
            } else if (arg instanceof AuthorRequest) {
                int nameLength = ((AuthorRequest) arg).getName().length();
                if (nameLength < 2 || nameLength > 30) {
                    System.out.println(ErrorCodes.INVALID_LENGTH + "Author's name can not be less than 2 and more than 30 symbols. Author's name is " + nameLength);
                    throw new RuntimeException();
                }
            }
        }
    }
    @Before("@annotation(validateId)")
    public void validateId(JoinPoint joinPoint, ValidateId validateId) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof NewsRequest) {
                Long authorId = ((NewsRequest) arg).getAuthorId();
                Long newsId = ((NewsRequest) arg).getId();
                if (!authorRepository.existById(authorId)) {
                    System.out.println(ErrorCodes.AUTHOR_NOT_FOUND + "Author Id does not exist. Author Id is: " + authorId);
                    throw new RuntimeException();
                }
                if (!newsRepository.existById(newsId)) {
                    System.out.println(ErrorCodes.NEWS_NOT_FOUND + "News with id " + newsId + " does not exist.");
                    throw new RuntimeException();
                }
            }
            if (arg instanceof AuthorRequest) {
                Long authorId = ((AuthorRequest) arg).getId();
                if (!authorRepository.existById(authorId)) {
                    System.out.println(ErrorCodes.AUTHOR_NOT_FOUND + "Author Id does not exist. Author Id is: " + authorId);
                    throw new RuntimeException();
                }
            }
            if (arg instanceof Long && validateId.type().equals("CHECK_NEWS_ID")) {
                if (!newsRepository.existById((Long) arg)) {
                    System.out.println(ErrorCodes.NEWS_NOT_FOUND + "News with id " + arg + " does not exist.");
                    throw new RuntimeException();
                }
            }
            if (arg instanceof Long && validateId.type().equals("CHECK_AUTHOR_ID")) {
                if (!authorRepository.existById((Long) arg)) {
                    System.out.println(ErrorCodes.AUTHOR_NOT_FOUND + "Author Id does not exist. Author Id is: " + arg);
                    throw new RuntimeException();
                }
            }
        }
    }
}
