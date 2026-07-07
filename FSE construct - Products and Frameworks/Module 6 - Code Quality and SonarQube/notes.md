# Code Quality and SonarQube - Complete Study Notes

---

# 1. Introduction to Code Quality

## What is it?

Code quality refers to how well-written, maintainable, readable, and bug-free your code is. High-quality code is easy to understand, easy to modify, and has fewer defects.

SonarQube is an open-source platform that continuously inspects code quality by performing automatic reviews with static analysis to detect bugs, code smells, and security vulnerabilities.

---

## Why do we use it?

- Catch bugs before they reach production
- Enforce coding standards across teams
- Track technical debt over time
- Improve code maintainability
- Security vulnerability detection
- Ensure consistent code quality in CI/CD pipelines

---

## How it works

```
Developer writes code
       |
       v
Code pushed to Git
       |
       v
CI/CD Pipeline triggers build
       |
       v
SonarQube Scanner analyzes code
       |
       v
Results sent to SonarQube Server
       |
       v
Dashboard shows quality metrics
       |
       v
Quality Gate: PASS or FAIL
```

---

## Key Concepts

| Concept | Definition |
|---------|------------|
| Static Analysis | Analyzing code without executing it |
| Dynamic Analysis | Analyzing code during execution (runtime testing) |
| Code Smell | Code that works but is poorly written |
| Bug | Code that is incorrect and will cause errors |
| Vulnerability | Code that can be exploited for security attacks |
| Technical Debt | Time required to fix all code quality issues |
| Quality Gate | Pass/fail criteria for code quality |
| Clean As You Code | Fix issues in new code, don't fix legacy all at once |

---

## Static Analysis vs Dynamic Analysis

| Feature | Static Analysis | Dynamic Analysis |
|---------|----------------|------------------|
| When | Before execution | During execution |
| How | Reads source code | Runs the program |
| Speed | Fast | Slow |
| Coverage | All code paths | Only executed paths |
| Bugs Found | Coding errors, patterns | Runtime errors, memory leaks |
| Tools | SonarQube, PMD, Checkstyle | JUnit, Selenium, JMeter |
| False Positives | More | Fewer |
| Cost | Low | Higher |

---

## Clean As You Code

This is SonarQube's recommended approach:

```
Traditional Approach         Clean As You Code
+------------------+         +------------------+
| Fix ALL old code |         | Fix only NEW code|
| before release   |         | on each commit   |
| (Impossible)     |         | (Practical)      |
+------------------+         +------------------+
         X                          ✓
```

**Principle:** Focus on keeping new code clean. Over time, the overall quality improves naturally.

- New code must pass the Quality Gate
- Old code quality is tracked but not blocked
- Gradual improvement instead of big-bang cleanup

---

## Interview Questions

**Q: What is SonarQube?**
A: SonarQube is an open-source platform for continuous code quality inspection. It performs static analysis to find bugs, code smells, and security vulnerabilities in your codebase.

**Q: What is the difference between static and dynamic analysis?**
A: Static analysis examines code without running it (SonarQube). Dynamic analysis tests code during execution (JUnit, Selenium).

**Q: What is Clean As You Code?**
A: An approach where you ensure all new code meets quality standards. Instead of fixing all legacy issues at once, you improve quality gradually with every commit.

---

# 2. SonarQube Architecture

## What is it?

SonarQube consists of four main components that work together to analyze code and present results.

---

## Architecture Diagram

```
+--------------------------------------------------+
|                 SONARQUBE SERVER                  |
|                                                  |
|  +------------+  +----------------+  +--------+  |
|  | Web Server |  | Compute Engine |  | Search |  |
|  | (UI/API)   |  | (Analysis)     |  | Engine |  |
|  +------------+  +----------------+  +--------+  |
|                                                  |
|  +--------------------------------------------+  |
|  |              Database                      |  |
|  |    (PostgreSQL / Oracle / SQL Server)       |  |
|  +--------------------------------------------+  |
+--------------------------------------------------+
         ^                       ^
         |                       |
+------------------+    +------------------+
|  SonarQube       |    |  SonarQube       |
|  Scanner         |    |  Scanner         |
|  (Project A)     |    |  (Project B)     |
+------------------+    +------------------+
         ^                       ^
         |                       |
+------------------+    +------------------+
|  Source Code     |    |  Source Code     |
|  (Java/JS/etc)   |    |  (Java/JS/etc)   |
+------------------+    +------------------+
```

---

## Components Explained

| Component | Role | Details |
|-----------|------|--------|
| Web Server | UI + REST API | Serves the dashboard, manages settings, shows results |
| Compute Engine (CE) | Processes analysis reports | Receives reports from scanners, computes metrics, stores results |
| Search Engine | Powers search and dashboards | Uses Elasticsearch internally for fast data retrieval |
| Database | Persistent storage | Stores configuration, metrics, quality profiles, rules |
| Scanner | Analyzes source code | Runs locally or in CI/CD, sends report to server |

---

## Analysis Workflow

```
Step 1: Scanner runs on source code
              |
              v
Step 2: Scanner applies quality rules
              |
              v
Step 3: Scanner generates analysis report
              |
              v
Step 4: Report uploaded to SonarQube Server
              |
              v
Step 5: Compute Engine processes report
              |
              v
Step 6: Results stored in Database
              |
              v
Step 7: Web Server displays results on Dashboard
              |
              v
Step 8: Quality Gate evaluates PASS/FAIL
```

---

## Important Points

- Scanner runs where the code is (developer machine or CI server)
- SonarQube Server can be on a separate machine
- Analysis reports are sent over HTTP
- Multiple projects can use the same SonarQube Server
- Default port is 9000

---

## Interview Questions

**Q: What are the main components of SonarQube?**
A: Web Server (UI/API), Compute Engine (processes analysis), Search Engine (Elasticsearch for search), Database (stores results), and Scanner (analyzes code).

**Q: Where does the Scanner run?**
A: On the machine where the code is -- either the developer's machine or the CI/CD server.

---

# 3. Maven Integration

## What is it?

Integrating SonarQube with Maven to analyze Java projects during the build process.

---

## Prerequisites

1. SonarQube Server running (localhost:9000 or remote)
2. Maven installed
3. Java project with pom.xml
4. SonarQube authentication token

---

## Step 1: Generate SonarQube Token

1. Open SonarQube dashboard (http://localhost:9000)
2. Login (default: admin/admin)
3. Go to: My Account > Security > Generate Token
4. Name: "maven-analysis"
5. Copy the token (shown only once)

---

## Step 2: Configure pom.xml

`pom.xml`
```xml
<project>
    <!-- ... existing configuration ... -->

    <properties>
        <sonar.projectKey>studentmanagement</sonar.projectKey>
        <sonar.projectName>Student Management</sonar.projectName>
        <sonar.host.url>http://localhost:9000</sonar.host.url>
        <sonar.token>your-sonar-token-here</sonar.token>
        <sonar.java.source>24</sonar.java.source>
        <sonar.coverage.jacoco.xmlReportPaths>
            ${project.build.directory}/site/jacoco/jacoco.xml
        </sonar.coverage.jacoco.xmlReportPaths>
    </properties>

    <build>
        <plugins>
            <!-- SonarQube Scanner Plugin -->
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>3.11.0.3922</version>
            </plugin>

            <!-- JaCoCo for Code Coverage -->
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.12</version>
                <executions>
                    <execution>
                        <id>prepare-agent</id>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Step 3: Run Analysis

```bash
# Full analysis with coverage
mvn clean verify sonar:sonar

# If token is not in pom.xml, pass it as parameter
mvn clean verify sonar:sonar -Dsonar.token=your-token-here

# Specify host URL
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=your-token-here
```

---

## Step 4: Understanding the Output

```
[INFO] --- sonar:3.11.0.3922:sonar (default-cli) @ studentmanagement ---
[INFO] User cache: C:\Users\user\.sonar\cache
[INFO] SonarQube version: 10.4
[INFO] Default locale: "en_US"
[INFO] ------------- Run sensors on module StudentManagement
[INFO] Load metrics repository
[INFO] Sensor JavaSensor [java]
[INFO]   Analyzed 15 source files
[INFO]   Analyzed 10 test files
[INFO] Sensor JavaSensor [java] (done)
[INFO] ------------- Analysis report generated
[INFO] ANALYSIS SUCCESSFUL, you can find the results at:
[INFO]   http://localhost:9000/dashboard?id=studentmanagement
[INFO] Note that you will be able to access the updated dashboard
[INFO] once the server has processed the submitted analysis report.
```

---

## Step 5: View Dashboard

Open browser: http://localhost:9000/dashboard?id=studentmanagement

---

## Common Errors and Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| `Not authorized` | Invalid or missing token | Generate new token, pass with -Dsonar.token |
| `Server not reachable` | SonarQube not running | Start SonarQube: `sonar.sh start` |
| `Java version not supported` | Wrong Java version | Set `sonar.java.source` property |
| `No coverage data` | JaCoCo not configured | Add JaCoCo plugin to pom.xml |
| `Quality Gate FAILED` | Code doesn't meet criteria | Fix reported issues |
| `OutOfMemoryError` | Large project | Increase Maven heap: `MAVEN_OPTS=-Xmx2g` |

---

## settings.xml Alternative

Instead of putting token in pom.xml, use Maven settings:

`~/.m2/settings.xml`
```xml
<settings>
    <pluginGroups>
        <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
    </pluginGroups>
    <profiles>
        <profile>
            <id>sonar</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <sonar.host.url>http://localhost:9000</sonar.host.url>
                <sonar.token>your-token-here</sonar.token>
            </properties>
        </profile>
    </profiles>
</settings>
```

---

## Quick Revision

- Add sonar-maven-plugin and jacoco-maven-plugin to pom.xml
- Generate token from SonarQube UI > My Account > Security
- Run: `mvn clean verify sonar:sonar`
- View results at http://localhost:9000
- JaCoCo provides coverage data to SonarQube

---

# 4. Code Analysis Metrics

## What is it?

SonarQube analyzes your code and produces metrics across several dimensions. Understanding these metrics is essential.

---

## Metrics Overview

```
+--------------------------------------------------+
|              SonarQube Dashboard                  |
|                                                  |
|  +----------+  +----------+  +----------+       |
|  |   Bugs   |  |  Vulner  |  |  Code    |       |
|  |   (3)    |  |  (1)     |  |  Smells  |       |
|  |          |  |          |  |  (15)    |       |
|  +----------+  +----------+  +----------+       |
|                                                  |
|  +----------+  +----------+  +----------+       |
|  | Coverage |  |  Dupli   |  | Tech     |       |
|  |  (78%)   |  |  (2.3%)  |  | Debt     |       |
|  |          |  |          |  | (4h)     |       |
|  +----------+  +----------+  +----------+       |
|                                                  |
|  Quality Gate: PASSED                            |
+--------------------------------------------------+
```

---

## Bugs

### What is it?
Code that is factually wrong and will cause unexpected behavior at runtime.

### Examples
```java
// Bug: NullPointerException risk
String name = null;
int length = name.length();  // Bug! Will throw NPE

// Bug: Always true condition
int x = 5;
if (x == 5 || x != 5) {  // Bug! Always true
    doSomething();
}

// Bug: Resource leak
FileInputStream fis = new FileInputStream("file.txt");
fis.read();  // Bug! Stream never closed

// Fix: Use try-with-resources
try (FileInputStream fis = new FileInputStream("file.txt")) {
    fis.read();
}
```

### Severity Levels

| Severity | Impact | Example |
|----------|--------|---------|
| Blocker | System crashes | NPE in main flow |
| Critical | Data corruption | Unclosed database connection |
| Major | Functional issue | Wrong calculation |
| Minor | Cosmetic issue | Unused import |
| Info | Informational | TODO comment |

---

## Vulnerabilities

### What is it?
Code that can be exploited by attackers to compromise security.

### Examples
```java
// Vulnerability: SQL Injection
String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
// Fix: Use parameterized queries
String query = "SELECT * FROM users WHERE name = ?";
preparedStatement.setString(1, userInput);

// Vulnerability: Hardcoded password
String password = "admin123";  // Never hardcode credentials!
// Fix: Use environment variables
String password = System.getenv("DB_PASSWORD");

// Vulnerability: Logging sensitive data
logger.info("User password: " + password);  // Never log passwords!
// Fix: Log only non-sensitive data
logger.info("User login attempt for: " + username);
```

### Common Vulnerability Categories

| Category | Risk | Example |
|----------|------|---------|
| Injection | Data theft | SQL injection, XSS |
| Authentication | Unauthorized access | Weak password, hardcoded creds |
| Sensitive Data | Data exposure | Logging passwords, weak encryption |
| Security Config | System compromise | Default passwords, open ports |

---

## Code Smells

### What is it?
Code that works correctly but is poorly written. It makes the code harder to understand, maintain, or extend.

### Examples
```java
// Smell: Long method (too many lines)
public void processEverything() {
    // 200 lines of code...
}
// Fix: Break into smaller methods

// Smell: Magic numbers
if (score > 60) { ... }  // What is 60?
// Fix: Use constants
private static final int PASSING_SCORE = 60;
if (score > PASSING_SCORE) { ... }

// Smell: Duplicate code
public void method1() {
    int a = x + y;
    int b = a * 2;
}
public void method2() {
    int a = x + y;  // Duplicated!
    int b = a * 3;
}
// Fix: Extract common logic

// Smell: Unused variable
int result = calculate();  // 'result' never used

// Smell: Empty catch block
try {
    riskyOperation();
} catch (Exception e) {
    // Empty! Swallowing exception
}
// Fix: Log or handle the exception
catch (Exception e) {
    logger.error("Operation failed", e);
}
```

---

## Code Coverage

### What is it?
Percentage of code that is executed by automated tests.

### Coverage Types

| Type | What it Measures | Example |
|------|-----------------|--------|
| Line Coverage | Lines executed | 80 of 100 lines = 80% |
| Branch Coverage | If/else branches taken | Both if and else tested |
| Method Coverage | Methods called | 9 of 10 methods = 90% |
| Class Coverage | Classes tested | All classes have tests |

### Good Coverage Numbers

| Coverage | Rating |
|----------|--------|
| 80%+ | Excellent |
| 60-80% | Good |
| 40-60% | Needs improvement |
| Below 40% | Poor |

### JaCoCo Report Example

```java
// This class
public class Calculator {
    public int add(int a, int b) {       // Line covered if tested
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {                     // Branch 1
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;                     // Branch 2
    }
}

// Test class
@Test
void testAdd() {
    assertEquals(5, calculator.add(2, 3));  // Covers add() method
}

@Test
void testDivide() {
    assertEquals(2, calculator.divide(4, 2));  // Covers branch 2
}

@Test
void testDivideByZero() {
    assertThrows(ArithmeticException.class,
            () -> calculator.divide(4, 0));    // Covers branch 1
}
// Result: 100% line coverage, 100% branch coverage
```

---

## Duplicate Code

### What is it?
Code blocks that are identical or very similar in multiple places.

### Impact
- Bug fix in one place but not the other
- Increased maintenance effort
- Larger codebase

### SonarQube Detection
- SonarQube detects blocks of 10+ duplicate lines
- Reports duplication percentage
- Goal: Keep below 3%

### Fix Strategy
- Extract common code into utility methods
- Use inheritance or composition
- Apply DRY principle (Don't Repeat Yourself)

---

## Cyclomatic Complexity

### What is it?
A metric that measures the number of independent paths through a method. Higher complexity = harder to test and maintain.

### How to Calculate

Each of these adds 1 to complexity:
- `if`
- `else if`
- `for`
- `while`
- `case` (in switch)
- `catch`
- `&&`
- `||`
- `?:` (ternary)

### Example

```java
// Complexity = 1 (just a return)
public int add(int a, int b) {
    return a + b;
}

// Complexity = 4 (1 base + 3 conditions)
public String getGrade(int score) {
    if (score >= 90) {          // +1
        return "A";
    } else if (score >= 80) {   // +1
        return "B";
    } else if (score >= 70) {   // +1
        return "C";
    } else {
        return "F";
    }
}
```

### Thresholds

| Complexity | Rating | Action |
|-----------|--------|--------|
| 1-10 | Simple | Good |
| 11-20 | Moderate | Consider refactoring |
| 21-50 | Complex | Must refactor |
| 50+ | Untestable | Critical refactor needed |

---

## Technical Debt

### What is it?
The estimated time needed to fix all code quality issues. Measured in hours/days.

### Example

```
Issue                      Estimated Time
-----                      --------------
3 Bugs                     2 hours
1 Vulnerability             1 hour
15 Code Smells             4 hours
-------------------------------------
Total Technical Debt:      7 hours
```

SonarQube also shows a Technical Debt Ratio:
```
Technical Debt Ratio = (Remediation Cost / Development Cost) x 100

Example: 7 hours debt / 200 hours development = 3.5% debt ratio
```

### Ratings

| Rating | Debt Ratio |
|--------|------------|
| A | 0-5% |
| B | 6-10% |
| C | 11-20% |
| D | 21-50% |
| E | 50%+ |

---

## Coding Standards

### What is it?
Rules that define how code should be written. SonarQube enforces these automatically.

### Common Java Rules

| Rule | Bad Example | Good Example |
|------|-------------|-------------|
| Use `equals()` on strings | `str == "hello"` | `"hello".equals(str)` |
| Close resources | `new FileInputStream()` | `try-with-resources` |
| Avoid empty blocks | `catch(Exception e){}` | `catch(Exception e){log.error(...)}` |
| Use constants | `if (status == 1)` | `if (status == ACTIVE)` |
| Naming conventions | `int x` | `int studentCount` |
| Remove dead code | `// int unused = 5;` | Remove the line |
| Avoid deep nesting | 5 levels of if | Extract methods |

---

## Quality Gate

### What is it?
A set of conditions that code must meet to pass analysis. Think of it as a "quality checkpoint."

### Default Quality Gate Conditions

| Metric | Condition | Threshold |
|--------|-----------|----------|
| New Bugs | Must be | 0 |
| New Vulnerabilities | Must be | 0 |
| New Code Smells | Rating must be | A |
| New Coverage | Must be at least | 80% |
| New Duplications | Must be less than | 3% |

### How It Works

```
Analysis Complete
       |
       v
Check each condition
       |
       +--- New Bugs = 0?         YES
       +--- New Vulnerabilities = 0?  YES
       +--- New Coverage >= 80%?   YES
       +--- New Duplications < 3%? YES
       |
       v
All conditions met?
  YES --> QUALITY GATE: PASSED (Green)
  NO  --> QUALITY GATE: FAILED (Red)
```

### Custom Quality Gate

1. Go to Quality Gates in SonarQube
2. Create new gate
3. Add conditions:
   - New Coverage on New Code >= 80%
   - New Bugs = 0
   - New Vulnerabilities = 0
   - New Security Hotspots Reviewed = 100%
4. Set as default

---

## Interview Questions

**Q: What is a Code Smell?**
A: Code that works correctly but is poorly written, making it harder to maintain. Examples: long methods, magic numbers, duplicate code, empty catch blocks.

**Q: What is Cyclomatic Complexity?**
A: A metric that counts the number of independent paths through a method. Each if, for, while, case adds 1. Lower is better. Keep it under 10.

**Q: What is a Quality Gate?**
A: A set of conditions that code must pass. If any condition fails, the gate fails. Default: 0 new bugs, 0 new vulnerabilities, 80% coverage on new code.

**Q: What is Technical Debt?**
A: The estimated time needed to fix all code quality issues. SonarQube shows it in hours/days.

**Q: What is the difference between a Bug and a Code Smell?**
A: A Bug is factually wrong code that will cause errors. A Code Smell is working code that is poorly written and hard to maintain.

**Q: What is code coverage?**
A: The percentage of code executed by automated tests. Higher coverage means more code is tested. Industry standard is 80%+.

---

# 5. SonarQube Rules and Profiles

## What is it?

Rules are individual code checks. Quality Profiles are collections of rules applied to a project.

---

## Rule Categories

| Category | Icon | Purpose |
|----------|------|--------|
| Bug | Bug icon | Detect code errors |
| Vulnerability | Lock icon | Detect security issues |
| Code Smell | Cloud icon | Detect maintainability issues |
| Security Hotspot | Fire icon | Highlight security-sensitive code |

---

## Rule Severity

| Severity | Meaning | Must Fix? |
|----------|---------|----------|
| Blocker | Impacts system stability | Yes, immediately |
| Critical | High risk | Yes, before release |
| Major | Significant issue | Yes, planned |
| Minor | Low impact | Nice to have |
| Info | Informational | Optional |

---

## Quality Profile

A quality profile is a set of activated rules for a specific language.

```
Default Profile: "Sonar way" (Java)
  +-- 500+ rules activated
  +-- Covers bugs, vulnerabilities, smells
  +-- Balanced strictness

Custom Profile: "Company Standards"
  +-- Extends "Sonar way"
  +-- Add custom rules
  +-- Adjust severity levels
  +-- Project-specific requirements
```

### Creating a Custom Profile

1. Go to Quality Profiles
2. Click "Create"
3. Name: "Company Java Standards"
4. Language: Java
5. Parent: Sonar way
6. Activate/deactivate specific rules
7. Assign to your project

---

# 6. SonarQube in CI/CD

## What is it?

Integrating SonarQube into your CI/CD pipeline so every code push is automatically analyzed.

---

## Jenkins Integration

```
Developer pushes code to Git
           |
           v
Jenkins pipeline triggered
           |
           v
Stage 1: Compile (mvn compile)
           |
           v
Stage 2: Test (mvn test)
           |
           v
Stage 3: SonarQube Analysis (mvn sonar:sonar)
           |
           v
Stage 4: Quality Gate Check
           |
    PASS --+--> Deploy to staging
           |
    FAIL --+--> Block deployment, notify team
```

### Jenkinsfile Example

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
```

---

## GitHub Actions Integration

```yaml
name: SonarQube Analysis
on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  sonarqube:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 24
        uses: actions/setup-java@v4
        with:
          java-version: '24'
          distribution: 'temurin'
      - name: Build and Analyze
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
          SONAR_HOST_URL: ${{ secrets.SONAR_HOST_URL }}
        run: mvn clean verify sonar:sonar
```

---

# 7. Best Practices

## Code Quality Best Practices

| Practice | Do | Don't |
|----------|----|---------|
| Method length | Keep under 30 lines | Write 200-line methods |
| Complexity | Keep under 10 | Nest 5 levels deep |
| Comments | Explain WHY, not WHAT | Comment every line |
| Naming | Use descriptive names | Use single letters |
| Error handling | Log and handle | Empty catch blocks |
| Constants | Use named constants | Use magic numbers |
| Dependencies | Inject via constructor | Create with `new` inside class |
| Tests | Write meaningful tests | Write tests just for coverage |
| Dead code | Remove it | Comment it out |
| Imports | Remove unused | Leave wildcard imports |

---

## SonarQube Dashboard Walkthrough

```
+----------------------------------------------------------+
|  Project: StudentManagement                              |
|  Quality Gate: PASSED                                    |
+----------------------------------------------------------+
|
|  Overall Code                    New Code (Last 30 days)
|  +-------------+                 +-------------+
|  | Bugs: 3     |                 | Bugs: 0     |
|  | Vulns: 1    |                 | Vulns: 0    |
|  | Smells: 45  |                 | Smells: 2   |
|  | Coverage:   |                 | Coverage:   |
|  |  72%        |                 |  85%        |
|  | Dupl: 4.5%  |                 | Dupl: 0.5%  |
|  | Debt: 2d    |                 | Debt: 30min |
|  +-------------+                 +-------------+
|
|  Ratings:
|  Reliability: C  Security: B  Maintainability: A
+----------------------------------------------------------+
```

---

# SonarQube Quick Revision

## One-Page Summary

**What is SonarQube?**
- Open-source code quality platform
- Performs static analysis (no code execution)
- Finds bugs, vulnerabilities, code smells
- Tracks technical debt and code coverage

**Architecture:**
- Web Server (UI), Compute Engine (analysis), Search Engine (Elasticsearch), Database
- Scanner runs on developer machine or CI server
- Results sent to SonarQube Server over HTTP

**Maven Integration:**
- Add sonar-maven-plugin to pom.xml
- Generate token from SonarQube UI
- Run: `mvn clean verify sonar:sonar`
- View at http://localhost:9000

**Key Metrics:**

| Metric | What | Good Value |
|--------|------|------------|
| Bugs | Wrong code | 0 (new code) |
| Vulnerabilities | Security risks | 0 (new code) |
| Code Smells | Bad practices | A rating |
| Coverage | Test coverage | 80%+ |
| Duplications | Copy-paste code | Below 3% |
| Complexity | Path count per method | Below 10 |
| Technical Debt | Fix time | A rating (0-5%) |

**Quality Gate:**
- Pass/fail checkpoint for code quality
- Default: 0 new bugs, 0 new vulns, 80% coverage, <3% duplication
- Can be customized per project
- Blocks deployment if FAIL

**Clean As You Code:**
- Focus on keeping new code clean
- Don't try to fix all old code at once
- Quality improves gradually

**Code Smells vs Bugs vs Vulnerabilities:**
- Bug = wrong code (will cause errors)
- Vulnerability = exploitable code (security risk)
- Code Smell = working but poorly written code (maintenance risk)

**CI/CD Integration:**
- Jenkins: withSonarQubeEnv + waitForQualityGate
- GitHub Actions: sonar:sonar in workflow
- Every push triggers analysis
- Quality Gate blocks deployment on failure

**JaCoCo:**
- Provides code coverage data
- Add jacoco-maven-plugin to pom.xml
- Generates XML report for SonarQube
- prepare-agent goal instruments bytecode
- report goal generates coverage report

**Interview Must-Know:**
- SonarQube = static analysis tool
- Bug vs Code Smell vs Vulnerability
- Quality Gate = pass/fail criteria
- Clean As You Code = fix new code only
- Cyclomatic Complexity = path count (keep <10)
- Technical Debt = time to fix all issues
- Coverage = % code tested (aim 80%+)
