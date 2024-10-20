package com.mjc.school.exception;

public class AuthorNewsException extends Exception{
    private final String errorCode;
    private final String errorMessage;

    public AuthorNewsException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + errorMessage;
    }
}
