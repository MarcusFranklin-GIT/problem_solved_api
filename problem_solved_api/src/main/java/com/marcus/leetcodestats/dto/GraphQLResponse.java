package com.marcus.leetcodestats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphQLResponse {
    private Data data;
    private List<Object> errors;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("matchedUser")
        private MatchedUser matchedUser;

        @JsonProperty("userContestRanking")
        private UserContestRanking userContestRanking;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchedUser {
        private String username;
        private SubmitStats submitStats;
        private Profile profile;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubmitStats {
        @JsonProperty("acSubmissionNum")
        private List<AcSubmission> acSubmissionNum;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AcSubmission {
        private String difficulty;
        private Integer count;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Profile {
        private Integer ranking;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserContestRanking {
        private Double rating;
        private Integer globalRanking;
    }
}
