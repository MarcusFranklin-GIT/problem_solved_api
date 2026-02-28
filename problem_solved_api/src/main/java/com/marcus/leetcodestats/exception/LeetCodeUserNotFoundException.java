package com.marcus.leetcodestats.exception;

public class LeetCodeUserNotFoundException extends RuntimeException {
    public LeetCodeUserNotFoundException(String username) {
        super("LeetCode user '" + username + "' not found");
    }

    public LeetCodeUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
