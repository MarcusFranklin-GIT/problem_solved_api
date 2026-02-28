package com.marcus.leetcodestats.dto;

public class LeetCodeResponse {
    private String username;
    private Integer totalSolved;
    private Integer easySolved;
    private Integer mediumSolved;
    private Integer hardSolved;
    private Double rating;
    private Integer globalRank;
    private Integer contestGlobalRank;

    public LeetCodeResponse() {
    }

    public LeetCodeResponse(String username, Integer totalSolved, Integer easySolved,
                            Integer mediumSolved, Integer hardSolved, Double rating,
                            Integer globalRank, Integer contestGlobalRank) {
        this.username = username;
        this.totalSolved = totalSolved;
        this.easySolved = easySolved;
        this.mediumSolved = mediumSolved;
        this.hardSolved = hardSolved;
        this.rating = rating;
        this.globalRank = globalRank;
        this.contestGlobalRank = contestGlobalRank;
    }

    // Builder pattern
    public static LeetCodeResponseBuilder builder() {
        return new LeetCodeResponseBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTotalSolved() {
        return totalSolved;
    }

    public void setTotalSolved(Integer totalSolved) {
        this.totalSolved = totalSolved;
    }

    public Integer getEasySolved() {
        return easySolved;
    }

    public void setEasySolved(Integer easySolved) {
        this.easySolved = easySolved;
    }

    public Integer getMediumSolved() {
        return mediumSolved;
    }

    public void setMediumSolved(Integer mediumSolved) {
        this.mediumSolved = mediumSolved;
    }

    public Integer getHardSolved() {
        return hardSolved;
    }

    public void setHardSolved(Integer hardSolved) {
        this.hardSolved = hardSolved;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(Integer globalRank) {
        this.globalRank = globalRank;
    }

    public Integer getContestGlobalRank() {
        return contestGlobalRank;
    }

    public void setContestGlobalRank(Integer contestGlobalRank) {
        this.contestGlobalRank = contestGlobalRank;
    }

    public static class LeetCodeResponseBuilder {
        private String username;
        private Integer totalSolved;
        private Integer easySolved;
        private Integer mediumSolved;
        private Integer hardSolved;
        private Double rating;
        private Integer globalRank;
        private Integer contestGlobalRank;

        public LeetCodeResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LeetCodeResponseBuilder totalSolved(Integer totalSolved) {
            this.totalSolved = totalSolved;
            return this;
        }

        public LeetCodeResponseBuilder easySolved(Integer easySolved) {
            this.easySolved = easySolved;
            return this;
        }

        public LeetCodeResponseBuilder mediumSolved(Integer mediumSolved) {
            this.mediumSolved = mediumSolved;
            return this;
        }

        public LeetCodeResponseBuilder hardSolved(Integer hardSolved) {
            this.hardSolved = hardSolved;
            return this;
        }

        public LeetCodeResponseBuilder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public LeetCodeResponseBuilder globalRank(Integer globalRank) {
            this.globalRank = globalRank;
            return this;
        }

        public LeetCodeResponseBuilder contestGlobalRank(Integer contestGlobalRank) {
            this.contestGlobalRank = contestGlobalRank;
            return this;
        }

        public LeetCodeResponse build() {
            return new LeetCodeResponse(username, totalSolved, easySolved,
                    mediumSolved, hardSolved, rating, globalRank, contestGlobalRank);
        }
    }
}
