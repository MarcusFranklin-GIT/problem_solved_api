package com.marcus.leetcodestats.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphQLResponse {
    private Data data;
    private List<Object> errors;

    public GraphQLResponse() {
    }

    public GraphQLResponse(Data data, List<Object> errors) {
        this.data = data;
        this.errors = errors;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public static class Data {
        @JsonProperty("matchedUser")
        private MatchedUser matchedUser;

        @JsonProperty("userContestRanking")
        private UserContestRanking userContestRanking;

        public Data() {
        }

        public Data(MatchedUser matchedUser, UserContestRanking userContestRanking) {
            this.matchedUser = matchedUser;
            this.userContestRanking = userContestRanking;
        }

        public MatchedUser getMatchedUser() {
            return matchedUser;
        }

        public void setMatchedUser(MatchedUser matchedUser) {
            this.matchedUser = matchedUser;
        }

        public UserContestRanking getUserContestRanking() {
            return userContestRanking;
        }

        public void setUserContestRanking(UserContestRanking userContestRanking) {
            this.userContestRanking = userContestRanking;
        }
    }

    public static class MatchedUser {
        private String username;
        private SubmitStats submitStats;
        private Profile profile;

        public MatchedUser() {
        }

        public MatchedUser(String username, SubmitStats submitStats, Profile profile) {
            this.username = username;
            this.submitStats = submitStats;
            this.profile = profile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public SubmitStats getSubmitStats() {
            return submitStats;
        }

        public void setSubmitStats(SubmitStats submitStats) {
            this.submitStats = submitStats;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }
    }

    public static class SubmitStats {
        @JsonProperty("acSubmissionNum")
        private List<AcSubmission> acSubmissionNum;

        public SubmitStats() {
        }

        public SubmitStats(List<AcSubmission> acSubmissionNum) {
            this.acSubmissionNum = acSubmissionNum;
        }

        public List<AcSubmission> getAcSubmissionNum() {
            return acSubmissionNum;
        }

        public void setAcSubmissionNum(List<AcSubmission> acSubmissionNum) {
            this.acSubmissionNum = acSubmissionNum;
        }
    }

    public static class AcSubmission {
        private String difficulty;
        private Integer count;

        public AcSubmission() {
        }

        public AcSubmission(String difficulty, Integer count) {
            this.difficulty = difficulty;
            this.count = count;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    public static class Profile {
        private Integer ranking;

        public Profile() {
        }

        public Profile(Integer ranking) {
            this.ranking = ranking;
        }

        public Integer getRanking() {
            return ranking;
        }

        public void setRanking(Integer ranking) {
            this.ranking = ranking;
        }
    }

    public static class UserContestRanking {
        private Double rating;
        private Integer globalRanking;

        public UserContestRanking() {
        }

        public UserContestRanking(Double rating, Integer globalRanking) {
            this.rating = rating;
            this.globalRanking = globalRanking;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Integer getGlobalRanking() {
            return globalRanking;
        }

        public void setGlobalRanking(Integer globalRanking) {
            this.globalRanking = globalRanking;
        }
    }
}
