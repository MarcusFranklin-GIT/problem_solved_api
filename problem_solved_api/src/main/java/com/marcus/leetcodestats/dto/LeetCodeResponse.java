package com.marcus.leetcodestats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeetCodeResponse {
    private String username;
    private Integer totalSolved;
    private Integer easySolved;
    private Integer mediumSolved;
    private Integer hardSolved;
    private Double rating;
    private Integer globalRank;
    private Integer contestGlobalRank;
}
