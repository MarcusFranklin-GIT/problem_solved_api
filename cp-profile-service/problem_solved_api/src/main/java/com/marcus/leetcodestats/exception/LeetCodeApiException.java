package com.marcus.leetcodestats.exception;

public class LeetCodeApiException extends RuntimeException {
    public LeetCodeApiException(String message) {
        super(message);
    }

    public LeetCodeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
