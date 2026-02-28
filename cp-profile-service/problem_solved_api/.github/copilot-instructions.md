# LeetCode Stats API Development Guide

## Project Status
- ✅ Spring Boot 3.2.0 project scaffolded with Maven
- ✅ Layered architecture implemented (Controller, Service, Client, DTO layers)
- ✅ WebClient configured for non-blocking HTTP requests
- ✅ Exception handling with centralized GlobalExceptionHandler
- ✅ All core components implemented and ready for testing

## Getting Started

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

Server starts on http://localhost:8080/api

### Test the API
```bash
# Get user stats
curl http://localhost:8080/api/leetcode/tourist

# Health check
curl http://localhost:8080/api/leetcode/health
```

## Project Structure

```
problem_solved_api/
├── src/
│   ├── main/
│   │   ├── java/com/marcus/leetcodestats/
│   │   │   ├── LeetCodeStatsApplication.java
│   │   │   ├── controller/
│   │   │   │   └── LeetCodeController.java
│   │   │   ├── service/
│   │   │   │   └── LeetCodeService.java
│   │   │   ├── client/
│   │   │   │   └── LeetCodeClient.java
│   │   │   ├── dto/
│   │   │   │   ├── GraphQLRequest.java
│   │   │   │   ├── GraphQLResponse.java
│   │   │   │   └── LeetCodeResponse.java
│   │   │   ├── config/
│   │   │   │   └── WebClientConfig.java
│   │   │   └── exception/
│   │   │       ├── LeetCodeUserNotFoundException.java
│   │   │       ├── LeetCodeApiException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/marcus/leetcodestats/
│           └── LeetCodeStatsApplicationTests.java
├── pom.xml
├── README.md
└── .gitignore
```

## Key Features

### 1. REST Endpoint
- `GET /api/leetcode/{username}` - Fetch user statistics

### 2. GraphQL Integration
- Queries LeetCode's internal GraphQL endpoint
- Sends structured GraphQL mutations with username variables
- Handles nested response structure

### 3. Layered Architecture
- **Controller**: REST endpoint handling
- **Service**: Business logic and data transformation
- **Client**: External API integration with WebClient
- **DTO**: Request/response data models

### 4. Exception Handling
- `LeetCodeUserNotFoundException` - User not found (404)
- `LeetCodeApiException` - API error (503)
- `GlobalExceptionHandler` - Centralized error handling

### 5. Configuration
- WebClient bean with proper headers
- Application properties in YAML
- Logging configuration for debugging

## Next Steps (Optional Enhancements)

- [ ] Add Redis caching for user stats
- [ ] Implement rate limiting
- [ ] Add Swagger/OpenAPI documentation
- [ ] Docker containerization
- [ ] Comprehensive unit and integration tests
- [ ] CI/CD pipeline setup
- [ ] Performance monitoring and metrics

## Testing

Run all tests:
```bash
mvn test
```

## Troubleshooting

### Port Already in Use
Change port in `application.yml`:
```yaml
server:
  port: 8081
```

### LeetCode API Unreachable
- Check internet connection
- LeetCode might be blocking requests - the User-Agent header is already configured
- Consider adding proxy if behind corporate firewall

### Build Errors
Ensure Java 17+ is installed:
```bash
java -version
```

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring WebClient Guide](https://spring.io/guides/gs/reactive-rest-service/)
- [LeetCode GraphQL API](https://leetcode.com/graphql)

## Development Commands

```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests with coverage
mvn clean test jacoco:report

# Package as JAR
mvn package

# Run JAR file
java -jar target/leetcode-stats-api-1.0.0.jar

# Check for dependencies
mvn dependency:tree
```
