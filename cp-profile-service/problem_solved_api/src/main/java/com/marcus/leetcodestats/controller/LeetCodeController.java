package com.marcus.leetcodestats.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcus.leetcodestats.dto.LeetCodeResponse;
import com.marcus.leetcodestats.service.LeetCodeService;

@RestController
@RequestMapping("/leetcode")
public class LeetCodeController {

    private static final Logger log = LoggerFactory.getLogger(LeetCodeController.class);

    private final LeetCodeService leetCodeService;

    public LeetCodeController(LeetCodeService leetCodeService) {
        this.leetCodeService = leetCodeService;
    }

    /**
     * Retrieves LeetCode user statistics for a given username
     * 
     * @param username The LeetCode username
     * @return ResponseEntity containing LeetCodeResponse with user statistics
     */
    @GetMapping("/{username}")
    public ResponseEntity<LeetCodeResponse> getUserStats(@PathVariable String username) {
        log.info("Received request for user stats: {}", username);

        if (username == null || username.trim().isEmpty()) {
            log.warn("Username is empty or null");
            return ResponseEntity.badRequest().build();
        }

        LeetCodeResponse response = leetCodeService.getUserStats(username);
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("LeetCode Stats API is running");
    }
}
