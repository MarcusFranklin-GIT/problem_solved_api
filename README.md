# LeetCode Stats API

A Spring Boot REST API that fetches any LeetCode user's problem-solving statistics using LeetCode's GraphQL endpoint.

## What It Does

Send a GET request with a LeetCode username → get back their stats as clean JSON:

```json
{
  "username": "tourist",
  "totalSolved": 3475,
  "easySolved": 1089,
  "mediumSolved": 1868,
  "hardSolved": 518,
  "rating": 3214.5,
  "globalRank": 1,
  "contestGlobalRank": 45
}
```

## Tech Stack

- **Java 17** + **Spring Boot 3.2.0**
- **Spring WebFlux** (WebClient for non-blocking HTTP)
- **Jackson** (JSON processing)

## Quick Start

```bash
cd problem_solved_api
mvn clean install
mvn spring-boot:run
```

Server starts at `http://localhost:8080/api`

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/leetcode/health` | Health check |
| GET | `/api/leetcode/{username}` | Get user stats |

## Usage

**Terminal / cURL:**
```bash
curl http://localhost:8080/api/leetcode/tourist
```

**Postman:**
- Method: **GET**
- URL: `http://localhost:8080/api/leetcode/{username}`
- No headers or body needed

## Response Fields

| Field | Type | Description |
|-------|------|-------------|
| `username` | String | LeetCode username |
| `totalSolved` | Integer | Total problems solved |
| `easySolved` | Integer | Easy problems solved |
| `mediumSolved` | Integer | Medium problems solved |
| `hardSolved` | Integer | Hard problems solved |
| `rating` | Double | Contest rating |
| `globalRank` | Integer | Global ranking |
| `contestGlobalRank` | Integer | Contest global ranking |

## Error Handling

| Status | Meaning |
|--------|---------|
| 200 | Success — user stats returned |
| 404 | User not found on LeetCode |
| 503 | LeetCode API unavailable |
| 500 | Internal server error |

## Project Structure

```
problem_solved_api/
└── src/main/java/com/marcus/leetcodestats/
    ├── LeetCodeStatsApplication.java    # Entry point
    ├── controller/LeetCodeController.java
    ├── service/LeetCodeService.java
    ├── client/LeetCodeClient.java       # Calls LeetCode GraphQL
    ├── dto/
    │   ├── GraphQLRequest.java
    │   ├── GraphQLResponse.java
    │   └── LeetCodeResponse.java
    ├── config/WebClientConfig.java
    └── exception/
        ├── GlobalExceptionHandler.java
        ├── LeetCodeUserNotFoundException.java
        └── LeetCodeApiException.java
```

## Prerequisites

- Java 17+
- Maven 3.6+
