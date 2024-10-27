package com.mjc.school.validation;

public final class ErrorCodes {
    private static final String ERROR_PREFIX = "ERROR_CODE: %s ERROR_MESSAGE: ";
    public static final String NEWS_NOT_FOUND = formatErrorCode("000001");
    public static final String AUTHOR_NOT_FOUND = formatErrorCode("000002");
    public static final String INVALID_LENGTH = formatErrorCode("000012");
    public static final String NOT_NUMERIC = formatErrorCode("000013");

    private ErrorCodes() {}

    private static String formatErrorCode(String code) {
        return String.format(ERROR_PREFIX, code);
    }
}
