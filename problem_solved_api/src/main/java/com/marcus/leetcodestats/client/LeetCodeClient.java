package com.marcus.leetcodestats.client;

import com.marcus.leetcodestats.dto.GraphQLRequest;
import com.marcus.leetcodestats.dto.GraphQLResponse;
import com.marcus.leetcodestats.exception.LeetCodeApiException;
import com.marcus.leetcodestats.exception.LeetCodeUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LeetCodeClient {

    private final WebClient webClient;
    private static final String GRAPHQL_ENDPOINT = "/graphql";

    public LeetCodeClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Fetches user statistics from LeetCode GraphQL API
     */
    public GraphQLResponse getUserStats(String username) {
        log.info("Fetching statistics for user: {}", username);

        try {
            String query = buildGraphQLQuery();
            Map<String, String> variables = new HashMap<>();
            variables.put("username", username);

            GraphQLRequest request = new GraphQLRequest(query, variables);

            GraphQLResponse response = webClient.post()
                    .uri(GRAPHQL_ENDPOINT)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(GraphQLResponse.class)
                    .block();

            if (response == null) {
                throw new LeetCodeApiException("Failed to fetch user stats: Empty response from LeetCode");
            }

            // Check for GraphQL errors
            if (response.getErrors() != null && !response.getErrors().isEmpty()) {
                log.warn("GraphQL errors received for user: {}", username);
                throw new LeetCodeUserNotFoundException(username);
            }

            // Check if user data is null
            if (response.getData() == null || response.getData().getMatchedUser() == null) {
                log.warn("User not found: {}", username);
                throw new LeetCodeUserNotFoundException(username);
            }

            log.info("Successfully fetched statistics for user: {}", username);
            return response;

        } catch (WebClientResponseException e) {
            log.error("WebClient error while fetching user {}: {}", username, e.getMessage());
            throw new LeetCodeApiException("Failed to connect to LeetCode: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error while fetching user {}: {}", username, e.getMessage());
            if (e instanceof LeetCodeUserNotFoundException || e instanceof LeetCodeApiException) {
                throw e;
            }
            throw new LeetCodeApiException("Failed to fetch user stats: " + e.getMessage(), e);
        }
    }

    /**
     * Builds the GraphQL query for fetching user statistics
     */
    private String buildGraphQLQuery() {
        return """
                query getUserProfile($username: String!) {
                  matchedUser(username: $username) {
                    username
                    submitStats {
                      acSubmissionNum {
                        difficulty
                        count
                      }
                    }
                    profile {
                      ranking
                    }
                  }
                  userContestRanking(username: $username) {
                    rating
                    globalRanking
                  }
                }
                """;
    }
}
