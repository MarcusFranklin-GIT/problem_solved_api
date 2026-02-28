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

## Prerequisites

- **Java 17+** → Check: `java -version`
- **Maven 3.6+** → Check: `mvn --version`

## How to Use (Step by Step)

### 1. Build the project

```powershell
cd D:\Projects\problem_solved_api
mvn clean install
```

Wait for `BUILD SUCCESS`.

### 2. Start the server

```powershell
mvn spring-boot:run
```

Wait for `Started LeetCodeStatsApplication`. Keep this terminal open.

### 3. Test the API (open a new terminal)

**Health check:**
```powershell
curl http://localhost:8080/api/leetcode/health
```

**Get user stats:**
```powershell
curl http://localhost:8080/api/leetcode/tourist
```

Replace `tourist` with any LeetCode username.

### 4. Using Postman

- Method: **GET**
- URL: `http://localhost:8080/api/leetcode/{username}`
- No headers or body needed

### 5. Stop the server

Press `Ctrl + C` in the terminal running the server.

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/leetcode/health` | Health check |
| GET | `/api/leetcode/{username}` | Get user stats |

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

## Error Responses

**User not found (404):**
```json
{
  "timestamp": "2026-03-01T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "LeetCode user 'invaliduser' not found"
}
```

**LeetCode API unavailable (503):**
```json
{
  "timestamp": "2026-03-01T10:30:00",
  "status": 503,
  "error": "Service Unavailable",
  "message": "Failed to connect to LeetCode: ..."
}
```

## Project Structure

```
src/main/java/com/marcus/leetcodestats/
├── LeetCodeStatsApplication.java    # Entry point
├── controller/
│   └── LeetCodeController.java      # REST endpoints
├── service/
│   └── LeetCodeService.java         # Business logic
├── client/
│   └── LeetCodeClient.java          # Calls LeetCode GraphQL API
├── dto/
│   ├── GraphQLRequest.java          # GraphQL request model
│   ├── GraphQLResponse.java         # GraphQL response model
│   └── LeetCodeResponse.java        # API response model
├── config/
│   └── WebClientConfig.java         # HTTP client config
└── exception/
    ├── GlobalExceptionHandler.java   # Error handling
    ├── LeetCodeUserNotFoundException.java
    └── LeetCodeApiException.java
```

## Tech Stack

- **Java 17** + **Spring Boot 3.2.0**
- **Spring WebFlux** (WebClient for non-blocking HTTP)
- **Jackson** (JSON processing)
- **Maven** (build tool)

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `mvn` not recognized | Add `C:\apache-maven-3.9.6\bin` to your system PATH |
| Port 8080 in use | Change port in `src/main/resources/application.yml` |
| Connection timeout | Check your internet connection |
| User not found | Verify the username exists on leetcode.com |

---

**Version:** 1.0.0