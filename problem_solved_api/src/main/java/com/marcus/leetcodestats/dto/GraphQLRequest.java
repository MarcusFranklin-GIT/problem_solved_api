package com.marcus.leetcodestats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphQLRequest {
    private String query;
    private Object variables;
}
