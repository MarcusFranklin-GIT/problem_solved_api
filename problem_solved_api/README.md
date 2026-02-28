# 🚀 LeetCode User Statistics Aggregator API

A production-grade Spring Boot REST API that fetches and aggregates LeetCode user statistics by proxying requests to LeetCode's internal GraphQL endpoint. Get clean, structured data about any LeetCode user's performance metrics in seconds.

## 📋 Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Step-by-Step Installation](#step-by-step-installation)
- [How to Use](#how-to-use)
- [API Documentation](#api-documentation)
- [Project Architecture](#project-architecture)
- [File Structure](#file-structure)
- [Configuration](#configuration)
- [Error Handling](#error-handling)
- [Development Guide](#development-guide)
- [Troubleshooting](#troubleshooting)
- [Future Enhancements](#future-enhancements)

## 📖 Overview

This service acts as a backend proxy between client applications and LeetCode's GraphQL API, providing a clean REST interface to retrieve user statistics including:

- ✅ Total problems solved
- ✅ Difficulty-wise solved counts (Easy, Medium, Hard)
- ✅ Contest rating
- ✅ Global ranking
- ✅ Contest global ranking

**Why use this API?**
- LeetCode doesn't provide an official public REST API
- This project reverse-engineers their internal GraphQL endpoint
- Clean, easy-to-use REST interface
- Type-safe responses with proper error handling
- Production-ready with centralized exception handling

---

## 🔧 Prerequisites

Before you begin, ensure you have the following installed on your system:

| Requirement | Version |
|-----------|---------|
| Java | 17+ |
| Maven | 3.6+ |
| Git | Latest |
| Internet Connection | Required |

### Check Prerequisites

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Git version
git --version
```

---

## ⚡ Quick Start (5 minutes)

### 1. Clone/Download the Project
```bash
cd d:\Projects\problem_solved_api
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Test the API
```bash
# In a new terminal/PowerShell
curl http://localhost:8080/api/leetcode/tourist

# Or using PowerShell:
Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/tourist"
```

✅ You're done! The API is running on `http://localhost:8080/api`

---

## 📝 Step-by-Step Installation

### Step 1: Verify Java Installation

Open PowerShell and run:

```powershell
java -version
```

**Expected Output:**
```
java version "21.0.10" 2026-01-20 LTS
Java(TM) SE Runtime Environment (build 21.0.10+8-LTS-217)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.10+8-LTS-217, mixed mode, sharing)
```

> **Note:** If you don't have Java 17+, download it from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK.

### Step 2: Navigate to Project Directory

```powershell
cd d:\Projects\problem_solved_api
```

### Step 3: Clean Previous Builds (Optional)

```powershell
mvn clean
```

This removes any previous build artifacts.

### Step 4: Download Dependencies and Build

```powershell
mvn clean install
```

**What this does:**
- Downloads all project dependencies (takes 2-5 minutes on first run)
- Compiles Java source files
- Runs unit tests
- Creates a packaged artifact

**Expected output at the end:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: ...
[INFO] Finished at: ...
```

### Step 5: Run the Application

```powershell
mvn spring-boot:run
```

**Expected output:**
```
2026-03-01 10:30:00.000  INFO ... : Started LeetCodeStatsApplication in 3.5 seconds (JVM running for 4.2s)
```

✅ **Application is now running!** The server is listening on `http://localhost:8080/api`

### Step 6: Test the Application (in another PowerShell)

```powershell
# Method 1: Using curl
curl http://localhost:8080/api/leetcode/tourist

# Method 2: Using Invoke-WebRequest (PowerShell)
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/tourist" -UseBasicParsing
$response.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
```

**Expected Response:**
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

✅ **Success!** The API is working correctly.

---

## 🎯 How to Use

### Basic Usage

#### 1. Get User Statistics

**Endpoint:**
```
GET http://localhost:8080/api/leetcode/{username}
```

**Example:**
```powershell
# Get stats for user "tourist"
Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/tourist" -UseBasicParsing

# Get stats for user "john_doe"
Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/john_doe" -UseBasicParsing

# Get stats for user "alice"
Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/alice" -UseBasicParsing
```

#### 2. Health Check

**Endpoint:**
```
GET http://localhost:8080/api/leetcode/health
```

**PowerShell Example:**
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/health" -UseBasicParsing
```

**Expected Response:**
```
StatusCode        : 200
StatusDescription : OK
Content           : LeetCode Stats API is running
```

### Using in Your Application

#### JavaScript/Node.js

```javascript
async function getUserStats(username) {
  try {
    const response = await fetch(`http://localhost:8080/api/leetcode/${username}`);
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.error('Error:', error);
  }
}

// Usage
getUserStats('tourist');
```

#### Python

```python
import requests

def get_user_stats(username):
    url = f"http://localhost:8080/api/leetcode/{username}"
    response = requests.get(url)
    return response.json()

# Usage
stats = get_user_stats('tourist')
print(stats)
```

#### cURL

```bash
# Basic request
curl http://localhost:8080/api/leetcode/tourist

# Pretty print JSON
curl -s http://localhost:8080/api/leetcode/tourist | python -m json.tool

# Save response to file
curl -o response.json http://localhost:8080/api/leetcode/tourist
```

---

## 📚 API Documentation

### Response Fields

```json
{
  "username": "string",              // LeetCode username
  "totalSolved": "integer",          // Total problems solved
  "easySolved": "integer",           // Easy problems solved
  "mediumSolved": "integer",         // Medium problems solved
  "hardSolved": "integer",           // Hard problems solved
  "rating": "float",                 // Contest rating
  "globalRank": "integer",           // Global ranking
  "contestGlobalRank": "integer"     // Contest global ranking
}
```

### Status Codes

| Status | Meaning | Example |
|--------|---------|---------|
| **200** | Success | User found, stats returned |
| **404** | Not Found | User doesn't exist on LeetCode |
| **503** | Service Unavailable | LeetCode API unreachable |
| **500** | Internal Error | Unexpected server error |

### Error Response Format

```json
{
  "timestamp": "2026-03-01T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "LeetCode user 'invalid_user_xyz' not found",
  "path": "/api/leetcode/invalid_user_xyz"
}
```

---

## 🏗️ Project Architecture

### Architecture Pattern: Layered Architecture

```
┌─────────────────────────────────────┐
│      REST Controller Layer          │
│   (HTTP Request/Response Handler)   │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│      Service Layer                  │
│   (Business Logic & Processing)     │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│      Client Layer                   │
│  (External API Integration)         │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│   LeetCode GraphQL Endpoint         │
│  https://leetcode.com/graphql       │
└─────────────────────────────────────┘
```

### Layer Responsibilities

| Layer | Responsibility | Key Class |
|-------|----------------|-----------|
| **Controller** | Handle HTTP requests, validate input | `LeetCodeController` |
| **Service** | Process business logic, transform data | `LeetCodeService` |
| **Client** | Call external GraphQL API, error handling | `LeetCodeClient` |
| **DTO** | Data transfer objects | `GraphQLRequest`, `GraphQLResponse`, `LeetCodeResponse` |
| **Config** | Application configuration | `WebClientConfig` |
| **Exception** | Error handling | `GlobalExceptionHandler` |

### Data Flow

```
1. Client Request
   ↓
2. Controller receives request
   ↓
3. Controller calls Service
   ↓
4. Service calls LeetCodeClient
   ↓
5. LeetCodeClient sends GraphQL query to LeetCode
   ↓
6. LeetCode returns nested JSON response
   ↓
7. Client parses and transforms to LeetCodeResponse
   ↓
8. Service transforms data
   ↓
9. Controller returns JSON response to client
   ↓
10. Client displays response
```

---

## 📂 File Structure

```
problem_solved_api/
│
├── src/
│   ├── main/
│   │   ├── java/com/marcus/leetcodestats/
│   │   │   ├── LeetCodeStatsApplication.java      # Spring Boot entry point
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   └── LeetCodeController.java        # REST endpoints
│   │   │   │
│   │   │   ├── service/
│   │   │   │   └── LeetCodeService.java           # Business logic
│   │   │   │
│   │   │   ├── client/
│   │   │   │   └── LeetCodeClient.java            # GraphQL client
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── GraphQLRequest.java            # GraphQL request DTO
│   │   │   │   ├── GraphQLResponse.java           # GraphQL response DTO
│   │   │   │   └── LeetCodeResponse.java          # Final response DTO
│   │   │   │
│   │   │   ├── config/
│   │   │   │   └── WebClientConfig.java           # WebClient configuration
│   │   │   │
│   │   │   └── exception/
│   │   │       ├── LeetCodeUserNotFoundException.java
│   │   │       ├── LeetCodeApiException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   │
│   │   └── resources/
│   │       └── application.yml                    # Application configuration
│   │
│   └── test/
│       └── java/com/marcus/leetcodestats/
│           └── LeetCodeStatsApplicationTests.java # Unit tests
│
├── pom.xml                                         # Maven dependencies
├── README.md                                       # This file
├── .gitignore                                      # Git ignore rules
└── .github/
    └── copilot-instructions.md                    # Development guide
```

### Key Files Description

| File | Purpose |
|------|---------|
| `LeetCodeController.java` | REST endpoint that handles `/api/leetcode/{username}` requests |
| `LeetCodeService.java` | Transforms GraphQL response to clean API response |
| `LeetCodeClient.java` | Makes GraphQL call to LeetCode endpoint |
| `GlobalExceptionHandler.java` | Catches all exceptions and returns proper error responses |
| `application.yml` | Server port, logging level, and other settings |

---

## ⚙️ Configuration

### Default Configuration

The application runs with these default settings:

```yaml
server:
  port: 8080                    # API runs on port 8080
  servlet:
    context-path: /api          # Base path is /api

logging:
  level:
    com.marcus.leetcodestats: DEBUG    # Debug logging
```

### Change Port

Edit `src/main/resources/application.yml`:

```yaml
server:
  port: 8081    # Change 8080 to 8081
```

Then rebuild and restart:
```powershell
mvn clean install
mvn spring-boot:run
```

### Disable Debug Logging

Edit `src/main/resources/application.yml`:

```yaml
logging:
  level:
    com.marcus.leetcodestats: INFO    # Change DEBUG to INFO
```

---

## 🚨 Error Handling

### Example Error Responses

#### User Not Found (404)

**Request:**
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/nonexistentuser123"
```

**Response:**
```json
{
  "timestamp": "2026-03-01T10:35:22.123",
  "status": 404,
  "error": "Not Found",
  "message": "LeetCode user 'nonexistentuser123' not found",
  "path": "/api/leetcode/nonexistentuser123"
}
```

#### LeetCode API Unavailable (503)

**Response:**
```json
{
  "timestamp": "2026-03-01T10:36:00.456",
  "status": 503,
  "error": "Service Unavailable",
  "message": "Failed to connect to LeetCode: Connection timeout",
  "path": "/api/leetcode/tourist"
}
```

#### Internal Server Error (500)

**Response:**
```json
{
  "timestamp": "2026-03-01T10:37:15.789",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred: [error details]",
  "path": "/api/leetcode/tourist"
}
```

---

## 🛠️ Development Guide

### Build the Project

```powershell
# Full build (clean + compile + test + package)
mvn clean install

# Compile only
mvn compile

# Test only
mvn test

# Package as JAR
mvn package
```

### Run the Application

```powershell
# Using Maven
mvn spring-boot:run

# Or run the JAR directly
java -jar target/leetcode-stats-api-1.0.0.jar
```

### View Application Logs

When the application runs, you'll see logs like:

```
2026-03-01 10:30:00.000  INFO ... : Starting LeetCodeStatsApplication v1.0.0
2026-03-01 10:30:00.123  INFO ... : No active profile set
2026-03-01 10:30:01.456  INFO ... : Netty started with worker count: 8
2026-03-01 10:30:02.789  INFO ... : Started LeetCodeStatsApplication in 2.789s
```

### Add Logs to Your Code

Already implemented with `@Slf4j` annotation. All classes have access to `log`:

```java
log.info("Fetching statistics for user: {}", username);
log.warn("GraphQL errors received");
log.error("WebClient error", exception);
```

---

## 🔍 Troubleshooting

### Problem: "mvn: The term 'mvn' is not recognized"

**Solution:** Maven is not in your system PATH. Add Maven bin folder to PATH:

```powershell
# Add Maven to PATH (temporary - session only)
$env:PATH += ";C:\Program Files\Apache Maven\bin"

# Verify
mvn -version
```

### Problem: "Port 8080 is already in use"

**Solution:** Change the port in `application.yml`:

```yaml
server:
  port: 8081
```

Or kill the process using the port:

```powershell
# Find process on port 8080
netstat -ano | findstr :8080

# Kill process (replace PID with actual process ID)
taskkill /PID <PID> /F
```

### Problem: "LeetCode user 'xyz' not found"

**Causes:**
- Username doesn't exist on LeetCode
- Typo in username
- Username is case-sensitive

**Solution:** Verify the username on LeetCode.com before calling the API

### Problem: Connection Timeout

**Causes:**
- No internet connection
- LeetCode is blocking requests
- Network firewall issues

**Solution:**
- Check internet connection: `ping leetcode.com`
- Try a different network
- Check firewall settings

### Problem: BUILD FAILURE during Maven install

**Causes:**
- Java version too old
- Missing dependencies
- Corrupted Maven cache

**Solution:**
```powershell
# Clean Maven cache
mvn clean
rmdir /s %USERPROFILE%\.m2\repository

# Rebuild
mvn clean install
```

---

## 🎓 Development Examples

### Example 1: Get Top User Stats

```powershell
# Get top competitive programmer
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/leetcode/doinkie" -UseBasicParsing
$response.Content | ConvertFrom-Json | ConvertTo-Json
```

### Example 2: Batch Get Multiple Users

```powershell
$users = @("tourist", "jiangzou", "doinkie", "dotamaster")

foreach ($user in $users) {
    $uri = "http://localhost:8080/api/leetcode/$user"
    try {
        $response = Invoke-WebRequest -Uri $uri -UseBasicParsing
        $data = $response.Content | ConvertFrom-Json
        Write-Host "$($data.username): $($data.totalSolved) problems solved"
    } catch {
        Write-Host "$user: Not found"
    }
}
```

### Example 3: Export to CSV

```powershell
$users = @("tourist", "jiangzou", "doinkie")
$results = @()

foreach ($user in $users) {
    $uri = "http://localhost:8080/api/leetcode/$user"
    $response = Invoke-WebRequest -Uri $uri -UseBasicParsing
    $data = $response.Content | ConvertFrom-Json
    $results += $data
}

$results | Export-Csv -Path "leetcode_stats.csv" -NoTypeInformation
```

---

## 🚀 Future Enhancements

| Feature | Benefit | Priority |
|---------|---------|----------|
| **Redis Caching** | Reduce API calls, faster responses | High |
| **Rate Limiting** | Prevent abuse, fair usage | High |
| **Swagger API Docs** | Interactive API documentation | High |
| **Docker Image** | Easy deployment, containerization | Medium |
| **Database** | Store historical stats, analytics | Medium |
| **Batch Endpoint** | Get multiple users in one call | Medium |
| **CI/CD Pipeline** | Automated testing and deployment | Medium |
| **Performance Monitoring** | Track response times, errors | Low |

---

## 📞 Support & Contact

### Getting Help

1. **Check Troubleshooting Section** - Most common issues are covered
2. **Review Logs** - Application logs provide detailed error information
3. **Test Health Endpoint** - Verify API is running: `http://localhost:8080/api/leetcode/health`

### Common Questions

**Q: Can I run this on a different port?**
A: Yes, edit `application.yml` and change the `server.port` value.

**Q: Is my internet connection required?**
A: Yes, the API needs to connect to LeetCode's servers.

**Q: Can I cache responses?**
A: Yes, this is planned for future versions using Redis.

**Q: Can I deploy this to the cloud?**
A: Yes, you can package it as a Docker container or JAR file.

---

## 📄 License

MIT License - Feel free to use this project for any purpose.

---

## 👨‍💻 Author

**Marcus Chen**
- Email: marcus@example.com
- GitHub: [marcus-chen](https://github.com)

---

## 📈 Statistics

| Metric | Value |
|--------|-------|
| Total Lines of Code | ~800 |
| Classes | 12 |
| External Dependencies | 6 |
| Build Time | ~30 seconds |
| Average Response Time | 500-1000ms* |

*Depends on LeetCode API response time and network latency

---

## ✨ Key Features Summary

✅ **Clean REST API** - Easy to use, well-documented endpoints  
✅ **Type-Safe** - Strongly typed DTOs with proper validation  
✅ **Error Handling** - Comprehensive exception handling with meaningful messages  
✅ **Logging** - Debug logging for troubleshooting  
✅ **Production-Ready** - Follows Spring Boot best practices  
✅ **Non-Blocking** - Uses WebFlux for reactive requests  
✅ **Layered Architecture** - Easy to extend and maintain  
✅ **GraphQL Integration** - Understands LeetCode's internal API  

---

**Last Updated:** March 1, 2026  
**Version:** 1.0.0  
**Status:** Production Ready ✅
