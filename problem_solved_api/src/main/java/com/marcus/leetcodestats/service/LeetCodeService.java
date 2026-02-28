package com.marcus.leetcodestats.service;

import com.marcus.leetcodestats.client.LeetCodeClient;
import com.marcus.leetcodestats.dto.GraphQLResponse;
import com.marcus.leetcodestats.dto.LeetCodeResponse;
import com.marcus.leetcodestats.exception.LeetCodeApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LeetCodeService {

    private final LeetCodeClient leetCodeClient;

    public LeetCodeService(LeetCodeClient leetCodeClient) {
        this.leetCodeClient = leetCodeClient;
    }

    /**
     * Retrieves and processes LeetCode user statistics
     */
    public LeetCodeResponse getUserStats(String username) {
        log.info("Processing user stats for: {}", username);

        // Fetch data from LeetCode GraphQL API
        GraphQLResponse graphQLResponse = leetCodeClient.getUserStats(username);

        // Extract and map data to LeetCodeResponse
        LeetCodeResponse response = mapToLeetCodeResponse(graphQLResponse);
        log.info("Successfully processed stats for user: {}", username);

        return response;
    }

    /**
     * Maps GraphQL response to LeetCodeResponse DTO
     */
    private LeetCodeResponse mapToLeetCodeResponse(GraphQLResponse graphQLResponse) {
        if (graphQLResponse.getData() == null || graphQLResponse.getData().getMatchedUser() == null) {
            throw new LeetCodeApiException("Invalid response structure from LeetCode API");
        }

        GraphQLResponse.MatchedUser matchedUser = graphQLResponse.getData().getMatchedUser();
        GraphQLResponse.UserContestRanking contestRanking = graphQLResponse.getData().getUserContestRanking();

        String username = matchedUser.getUsername();
        Integer globalRank = null;
        
        // Extract global ranking from profile
        if (matchedUser.getProfile() != null) {
            globalRank = matchedUser.getProfile().getRanking();
        }

        Integer contestGlobalRank = null;
        Double rating = null;
        
        // Extract contest ranking if available
        if (contestRanking != null) {
            contestGlobalRank = contestRanking.getGlobalRanking();
            rating = contestRanking.getRating();
        }

        // Extract problem counts from submitStats
        Map<String, Integer> problemCounts = extractProblemCounts(matchedUser);

        return LeetCodeResponse.builder()
                .username(username)
                .totalSolved(problemCounts.getOrDefault("All", 0))
                .easySolved(problemCounts.getOrDefault("Easy", 0))
                .mediumSolved(problemCounts.getOrDefault("Medium", 0))
                .hardSolved(problemCounts.getOrDefault("Hard", 0))
                .rating(rating)
                .globalRank(globalRank)
                .contestGlobalRank(contestGlobalRank)
                .build();
    }

    /**
     * Extracts problem counts by difficulty from submit stats
     */
    private Map<String, Integer> extractProblemCounts(GraphQLResponse.MatchedUser matchedUser) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("All", 0);
        counts.put("Easy", 0);
        counts.put("Medium", 0);
        counts.put("Hard", 0);

        if (matchedUser.getSubmitStats() == null || 
            matchedUser.getSubmitStats().getAcSubmissionNum() == null) {
            log.warn("No submit stats found for user");
            return counts;
        }

        for (GraphQLResponse.AcSubmission submission : matchedUser.getSubmitStats().getAcSubmissionNum()) {
            String difficulty = submission.getDifficulty();
            Integer count = submission.getCount() != null ? submission.getCount() : 0;

            if (difficulty != null) {
                counts.put(difficulty, count);
            }
        }

        return counts;
    }
}
