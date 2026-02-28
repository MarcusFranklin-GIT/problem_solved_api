package com.marcus.leetcodestats.dto;

public class GraphQLRequest {
    private String query;
    private Object variables;

    public GraphQLRequest() {
    }

    public GraphQLRequest(String query, Object variables) {
        this.query = query;
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }
}
