# Module 4 - Test Driven Development and Logging Framework

A comprehensive Java Maven project covering TDD, JUnit 5, Mockito, Spring Testing, Automation Testing Concepts, SLF4J/Logback Logging, and Lombok. Designed for semester practicals, placement preparation, interview revision, and hands-on practice.

---

## Table of Contents

1. [Test Driven Development (TDD)](#1-test-driven-development-tdd)
2. [JUnit 5](#2-junit-5)
3. [Mockito](#3-mockito)
4. [Spring Testing](#4-spring-testing)
5. [Automation Testing Concepts](#5-automation-testing-concepts)
6. [SLF4J and Logging](#6-slf4j-and-logging)
7. [Lombok](#7-lombok)
8. [Real-World Mini Projects](#8-real-world-mini-projects)
9. [Project Structure](#9-project-structure)
10. [How to Run](#10-how-to-run)
11. [Quick Revision Section](#11-quick-revision-section)
12. [Summary Comparison Tables](#12-summary-comparison-tables)

---

## 1. Test Driven Development (TDD)

### Definition

Test Driven Development is a software development approach where tests are written before the actual production code. The developer writes a failing test first, then writes the minimum code to make it pass, and finally refactors the code for quality.

### Purpose

- Ensure code correctness from the very beginning
- Drive design decisions through testable interfaces
- Build a comprehensive regression test suite naturally
- Improve code quality and maintainability

### Advantages

- Fewer bugs reach production
- Better software design through testability
- Living documentation via test cases
- Confidence when refactoring
- Faster debugging - tests pinpoint failures

### Disadvantages

- Initial development speed may slow down
- Learning curve for new developers
- Test suite maintenance overhead
- Not ideal for prototyping or exploratory work

### Red-Green-Refactor Cycle

```
    +-------------------+
    |   RED             |
    |   Write a test    |
    |   that FAILS      |
    +---------+---------+
              |
              v
    +-------------------+
    |   GREEN           |
    |   Write MINIMUM   |
    |   code to pass    |
    +---------+---------+
              |
              v
    +-------------------+
    |   REFACTOR        |
    |   Improve code    |
    |   quality         |
    +---------+---------+
              |
              v
         [Repeat]
```

### TDD Workflow

```
Step 1: Write a test for the next feature
Step 2: Run test -> it FAILS (RED)
Step 3: Write simplest code to pass the test
Step 4: Run test -> it PASSES (GREEN)
Step 5: Refactor code and tests
Step 6: Run test -> still PASSES
Step 7: Repeat from Step 1
```

### Real-World Example

```java
// RED: Write the test first
@Test
void testAddition() {
    Calculator calc = new Calculator();
    assertEquals(8, calc.add(5, 3));
}

// GREEN: Write minimum code
public int add(int a, int b) {
    return a + b;
}

// REFACTOR: Clean up if needed
```

### Project Examples

| Class | Description | Tests |
|-------|-------------|-------|
| `tdd.Calculator` | Arithmetic: add, subtract, multiply, divide, modulo, power | 10 tests |
| `tdd.StringUtility` | String ops: reverse, palindrome, vowels, capitalize | 11 tests |
| `tdd.BankAccount` | Banking: deposit, withdraw, transfer, balance | 9 tests |
| `tdd.EmployeeSalaryCalculator` | Salary: base, overtime, bonus, tax, net | 9 tests |
| `tdd.StudentGradeCalculator` | Grades: average, letter grade, pass/fail, highest/lowest | 10 tests |

### Interview Notes

- **Q: Explain the TDD cycle.**
  A: Write a failing test (Red), write minimum code to pass (Green), improve code quality (Refactor). Repeat.

- **Q: Benefits of TDD?**
  A: Fewer bugs, better design, living documentation, safe refactoring, faster debugging.

- **Q: When NOT to use TDD?**
  A: Rapid prototyping, UI-heavy work, legacy code without tests (use characterization tests instead).

### Best Practices

- Start with the simplest test case
- Write one test at a time
- Test behavior, not implementation details
- Keep tests independent and isolated
- Name tests descriptively

### Common Mistakes

- Writing tests after code (defeats the purpose)
- Testing implementation details instead of behavior
- Skipping the refactor step
- Writing too many tests at once
- Making tests depend on each other

---

## 2. JUnit 5

### Definition

JUnit 5 is the next-generation testing framework for Java. It consists of three sub-projects: JUnit Platform, JUnit Jupiter, and JUnit Vintage.

### Architecture

```
+----------------------------------+
|         JUnit 5                  |
+----------------------------------+
|                                  |
|  +----------------------------+  |
|  | JUnit Jupiter              |  |
|  | (New API + Annotations)    |  |
|  +----------------------------+  |
|                                  |
|  +----------------------------+  |
|  | JUnit Platform             |  |
|  | (Foundation + Launcher)    |  |
|  +----------------------------+  |
|                                  |
|  +----------------------------+  |
|  | JUnit Vintage              |  |
|  | (JUnit 3/4 Backward Compat)|  |
|  +----------------------------+  |
+----------------------------------+
```

### JUnit 5 Lifecycle

```
@BeforeAll    (runs once before all tests - static method)
     |
     v
@BeforeEach   (runs before EACH test method)
     |
     v
@Test         (the actual test method)
     |
     v
@AfterEach    (runs after EACH test method)
     |
     v
@AfterAll     (runs once after all tests - static method)
```

### Annotations Reference

| Annotation | Purpose | Scope |
|-----------|---------|-------|
| `@Test` | Marks a test method | Method |
| `@DisplayName` | Custom test name | Class/Method |
| `@BeforeAll` | Setup before all tests | Static method |
| `@BeforeEach` | Setup before each test | Method |
| `@AfterAll` | Cleanup after all tests | Static method |
| `@AfterEach` | Cleanup after each test | Method |
| `@Disabled` | Skip a test | Class/Method |
| `@Nested` | Group related tests | Inner class |
| `@Tag` | Categorize tests | Class/Method |
| `@Order` | Control execution order | Method |
| `@RepeatedTest` | Run test multiple times | Method |
| `@ParameterizedTest` | Run with different inputs | Method |
| `@Timeout` | Fail if exceeds time limit | Method |

### Assertion Methods

| Method | Purpose | Example |
|--------|---------|---------|
| `assertEquals(expected, actual)` | Values are equal | `assertEquals(5, calc.add(2,3))` |
| `assertNotEquals(a, b)` | Values differ | `assertNotEquals(0, result)` |
| `assertTrue(condition)` | Condition is true | `assertTrue(age > 18)` |
| `assertFalse(condition)` | Condition is false | `assertFalse(list.isEmpty())` |
| `assertNull(object)` | Object is null | `assertNull(user)` |
| `assertNotNull(object)` | Object not null | `assertNotNull(result)` |
| `assertThrows(type, exec)` | Exception thrown | `assertThrows(Ex.class, () -> ...)` |
| `assertTimeout(dur, exec)` | Completes in time | `assertTimeout(ofMs(500), ...)` |
| `assertAll(executables)` | All pass together | `assertAll(() -> ..., () -> ...)` |
| `assertArrayEquals(a, b)` | Arrays equal | `assertArrayEquals(exp, act)` |
| `assertIterableEquals(a, b)` | Iterables equal | `assertIterableEquals(exp, act)` |
| `assertSame(a, b)` | Same reference | `assertSame(obj1, obj2)` |

### AAA Pattern (Arrange-Act-Assert)

```java
@Test
void testDeposit() {
    // Arrange - set up test data
    BankAccount account = new BankAccount("Alice", 1000);

    // Act - perform the operation
    account.deposit(500);

    // Assert - verify the result
    assertEquals(1500, account.getBalance());
}
```

### Parameterized Tests

| Source | Purpose | Example |
|--------|---------|---------|
| `@ValueSource` | Simple values | `@ValueSource(ints = {1,2,3})` |
| `@CsvSource` | CSV pairs | `@CsvSource({"1,2,3", "4,5,9"})` |
| `@MethodSource` | Method provides args | `@MethodSource("dataProvider")` |
| `@EnumSource` | Enum values | `@EnumSource(Status.class)` |
| `@NullAndEmptySource` | Null and empty | Tests edge cases |

### Testing Pyramid

```
          /\
         /  \         E2E Tests
        / E2E\        (Few, Slow, Expensive)
       /------\
      /        \      Integration Tests
     /  Integ   \     (Some, Medium Speed)
    /------------\
   /              \   Unit Tests
  /   Unit Tests   \  (Many, Fast, Cheap)
 /==================\
```

### Project Test Classes

| Test Class | Concept Demonstrated |
|-----------|---------------------|
| `junit.AssertionsTest` | All assertion types |
| `junit.LifecycleTest` | @BeforeAll, @BeforeEach, @AfterEach, @AfterAll |
| `junit.AAAPatternTest` | Arrange-Act-Assert pattern |
| `junit.ParameterizedTestExamples` | @ValueSource, @CsvSource, @MethodSource |
| `junit.RepeatedTestExamples` | @RepeatedTest with RepetitionInfo |
| `junit.NestedTestExamples` | @Nested test grouping |
| `junit.TagsAndSuitesTest` | @Tag for test categorization |
| `junit.ExecutionOrderTest` | @Order, @TestMethodOrder |
| `junit.ExceptionTestingExamples` | assertThrows, assertDoesNotThrow |
| `junit.TimeoutAndPerformanceTest` | assertTimeout, @Timeout, benchmarks |

### Interview Notes

- **Q: Difference between @BeforeAll and @BeforeEach?**
  A: @BeforeAll runs once (static), @BeforeEach runs before every test.

- **Q: What is assertAll?**
  A: Groups multiple assertions; reports all failures, not just the first.

- **Q: How do parameterized tests work?**
  A: @ParameterizedTest runs the same test method with different inputs from a source.

---

## 3. Mockito

### Definition

Mockito is a mocking framework for unit tests in Java. It creates mock objects that simulate the behavior of real objects, allowing isolated unit testing.

### Purpose

- Isolate the unit under test from its dependencies
- Control dependency behavior in tests
- Verify interactions between objects
- Test edge cases that are hard to reproduce

### Mockito Workflow

```
Create Mock -----> Define Behavior (when/thenReturn)
     |                       |
     v                       v
Inject into SUT -----> Execute Test -----> Verify Interactions
     |                                          |
     v                                          v
@Mock / mock()                          verify() / times()
@InjectMocks                            never() / verifyNoMore()
```

### Core API

```java
// Create a mock
UserRepository mockRepo = mock(UserRepository.class);

// Define behavior
when(mockRepo.findById(1)).thenReturn("Alice");

// Use in test
assertEquals("Alice", mockRepo.findById(1));

// Verify interaction
verify(mockRepo).findById(1);
verify(mockRepo, times(1)).findById(1);
verify(mockRepo, never()).deleteById(anyInt());
```

### Argument Matchers

| Matcher | Purpose | Example |
|---------|---------|---------|
| `any()` | Any object | `when(repo.save(any()))` |
| `anyInt()` | Any integer | `when(repo.findById(anyInt()))` |
| `anyString()` | Any string | `when(repo.find(anyString()))` |
| `eq(value)` | Exact value | `when(repo.findById(eq(5)))` |
| `argThat(pred)` | Custom matcher | `argThat(s -> s.length() > 3)` |
| `intThat(pred)` | Int predicate | `intThat(id -> id > 0)` |

### Mock vs Spy

| Feature | Mock | Spy |
|---------|------|-----|
| Real method calls | No (returns default) | Yes (by default) |
| Behavior | Must be stubbed | Uses real implementation |
| Use case | Full isolation | Partial mocking |
| Creation | `mock(Class.class)` | `spy(realObject)` |
| Stubbing | `when(...).thenReturn(...)` | `doReturn(...).when(...)` |
| Default returns | null/0/false | Real method result |

### Void Method Mocking

```java
// doNothing - suppress void method
doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

// doThrow - throw exception from void method
doThrow(new RuntimeException("Error"))
    .when(emailService).sendEmail(anyString(), anyString(), anyString());

// doAnswer - custom behavior for void method
doAnswer(invocation -> {
    String to = invocation.getArgument(0);
    System.out.println("Email sent to: " + to);
    return null;
}).when(emailService).sendEmail(anyString(), anyString(), anyString());
```

### @InjectMocks and @Mock

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock dependency

    @InjectMocks
    private UserService userService;  // Auto-injects mocks

    @Test
    void testGetUser() {
        when(userRepository.findById(1)).thenReturn("Alice");
        assertEquals("Alice", userService.getUserById(1));
        verify(userRepository).findById(1);
    }
}
```

### Project Test Classes

| Test Class | Concepts |
|-----------|----------|
| `mockito.BasicMockTest` | mock, when, thenReturn, verify, times, never |
| `mockito.ArgumentMatcherTest` | anyInt, anyString, eq, argThat, any |
| `mockito.SpyTest` | Partial mocking with spy |
| `mockito.VoidMethodTest` | doNothing, doThrow, doAnswer |
| `mockito.InjectMocksTest` | @InjectMocks, @Mock, constructor injection |
| `mockito.ServiceMockingTest` | Email, SMS, Payment gateway mocking |
| `mockito.RepositoryAndFileMockTest` | DB repository, file storage mocking |

### Interview Notes

- **Q: Difference between @Mock and @InjectMocks?**
  A: @Mock creates a mock object. @InjectMocks creates a real object and injects mocks into it.

- **Q: When to use Spy vs Mock?**
  A: Use Mock for full isolation. Use Spy when you want real behavior but need to override specific methods.

- **Q: How to mock void methods?**
  A: Use doNothing(), doThrow(), or doAnswer() since when() requires a return value.

### Best Practices

- Mock only direct dependencies, not the class under test
- Verify meaningful interactions, not every call
- Use argument matchers consistently (all or none in a call)
- Prefer @ExtendWith(MockitoExtension.class) over manual initialization
- Keep tests focused on one behavior

### Common Mistakes

- Mocking the class under test
- Over-verifying (testing implementation, not behavior)
- Mixing matchers and raw values incorrectly
- Not using @ExtendWith(MockitoExtension.class)
- Creating mocks manually when @Mock suffices

---

## 4. Spring Testing

### Definition

Spring Testing provides testing support for Spring applications, integrating with JUnit 5 and Mockito to test controllers, services, and repositories at various levels.

### Spring Testing Architecture

```
+-----------------------------------------------+
|  @SpringBootTest                               |
|  (Full Application Context - Integration Test) |
+-----------------------------------------------+
          |
+-----------------------------------------------+
|  @WebMvcTest                                   |
|  (Controller Layer Only - MockMvc)             |
+-----------------------------------------------+
          |
+-----------------------------------------------+
|  @DataJpaTest                                  |
|  (Repository Layer Only - H2 Database)         |
+-----------------------------------------------+
          |
+-----------------------------------------------+
|  Unit Tests with Mockito                       |
|  (Service Layer - No Spring Context)           |
+-----------------------------------------------+
```

### Key Annotations

| Annotation | Purpose | Context Loaded |
|-----------|---------|----------------|
| `@SpringBootTest` | Full integration test | Full context |
| `@WebMvcTest` | Controller layer test | Web layer only |
| `@DataJpaTest` | Repository layer test | JPA layer only |
| `@MockBean` | Replace bean with mock | Spring context |
| `@ActiveProfiles` | Select test profile | N/A |

### MockMvc Example

```java
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/api/products"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2));
    }
}
```

### Unit Test vs Integration Test

| Aspect | Unit Test | Integration Test |
|--------|-----------|------------------|
| Scope | Single class/method | Multiple components |
| Speed | Very fast (ms) | Slower (seconds) |
| Dependencies | Mocked | Real (or embedded) |
| Database | No | Yes (H2 in-memory) |
| Spring Context | Not loaded | Loaded |
| Annotation | `@ExtendWith(MockitoExtension)` | `@SpringBootTest` |
| Purpose | Test logic | Test wiring/integration |
| Failure diagnosis | Precise | Broader |

### Project Test Classes

| Test Class | Type | Annotation |
|-----------|------|------------|
| `springtesting.service.ProductServiceTest` | Unit | @ExtendWith(MockitoExtension) |
| `springtesting.controller.ProductControllerTest` | Slice | @WebMvcTest |
| `springtesting.repository.ProductRepositoryTest` | Slice | @DataJpaTest |
| `springtesting.integration.ProductIntegrationTest` | Integration | @SpringBootTest |

---

## 5. Automation Testing Concepts

### Manual vs Automation Testing

| Aspect | Manual Testing | Automation Testing |
|--------|---------------|-------------------|
| Execution | Human performs tests | Scripts/tools execute |
| Speed | Slow | Fast |
| Accuracy | Human error possible | Consistent |
| Cost (initial) | Low | High |
| Cost (long-term) | High | Low |
| Regression | Time-consuming | Efficient |
| Exploratory | Excellent | Not suitable |
| ROI | Short-term projects | Long-term projects |

### Automation Testing Lifecycle

```
Requirement Analysis
        |
        v
Test Planning (Framework, Tools, Scope)
        |
        v
Framework Design (Data/Keyword/Hybrid)
        |
        v
Test Script Development
        |
        v
Test Execution
        |
        v
Result Analysis and Reporting
        |
        v
Maintenance
```

### Framework Comparison

| Feature | Data Driven | Keyword Driven | Hybrid |
|---------|------------|----------------|--------|
| Test data | External sources (CSV, Excel) | Keywords map to actions | Combined approach |
| Flexibility | Medium | High | Highest |
| Complexity | Low | Medium | High |
| Maintenance | Easy | Moderate | Moderate |
| Reusability | Data-level | Action-level | Both levels |
| Best for | Same flow, different data | Non-technical testers | Enterprise projects |

### Page Object Model (POM)

```
+-------------------+     +-------------------+
|    Test Class      |     |    Page Class     |
|                    |---->|                   |
|  - test methods    |     |  - web elements   |
|  - assertions      |     |  - page actions   |
+-------------------+     +-------------------+
                                    |
                           +-------------------+
                           |  Actual Web Page   |
                           +-------------------+
```

### JUnit 5 vs TestNG

| Feature | JUnit 5 | TestNG |
|---------|---------|--------|
| Annotations | @Test, @BeforeEach | @Test, @BeforeMethod |
| Parallel execution | Limited (experimental) | Built-in support |
| Data providers | @ParameterizedTest | @DataProvider |
| Test grouping | @Tag | groups attribute |
| Suite configuration | @Suite (platform) | testng.xml |
| Dependency testing | Not built-in | dependsOnMethods |
| Assertions | jupiter Assertions | TestNG Assert |
| Community | Large (standard) | Large (enterprise) |

### Project Classes

| Class | Pattern Demonstrated |
|-------|---------------------|
| `automation.config.FrameworkConfig` | Configuration management |
| `automation.utils.TestUtility` | Utility methods |
| `automation.data.TestData` | Test data provider |
| `automation.framework.DataDrivenExample` | Data-driven pattern |
| `automation.framework.KeywordDrivenExample` | Keyword-driven pattern |
| `automation.framework.HybridFrameworkExample` | Hybrid approach |
| `automation.pages.LoginPage` | Page Object Model |

---

## 6. SLF4J and Logging

### Definition

SLF4J (Simple Logging Facade for Java) is a logging facade that provides a common API for various logging frameworks. Logback is its native implementation.

### Purpose

- Decouple application code from logging implementation
- Provide consistent logging API across the project
- Enable runtime configuration of log levels and appenders
- Support structured and parameterized logging

### Logging Levels (Low to High)

```
TRACE  ->  DEBUG  ->  INFO  ->  WARN  ->  ERROR
  |          |         |         |          |
  v          v         v         v          v
Most       Dev      Business  Potential   Actual
Detailed   Debug    Events    Problems    Errors
```

| Level | When to Use | Example |
|-------|-------------|---------|
| TRACE | Entering/exiting methods, loop iterations | `logger.trace("Entering method X")` |
| DEBUG | Variable values, internal state | `logger.debug("User count: {}", count)` |
| INFO | Business events, milestones | `logger.info("Order placed: {}", orderId)` |
| WARN | Potential issues, deprecated usage | `logger.warn("Retry attempt {}", count)` |
| ERROR | Failures, exceptions | `logger.error("Payment failed", exception)` |

### Logging Architecture

```
+------------------+
| Application Code |
| logger.info(...) |
+--------+---------+
         |
+--------+---------+
|   SLF4J API      |
|   (Facade)       |
+--------+---------+
         |
+--------+---------+
|   Logback        |
|   (Implementation)|
+--------+---------+
         |
    +----+----+
    |         |
+---+---+ +---+---+
|Console| | File  |
|Appender| |Appender|
+-------+ +-------+
```

### Parameterized Logging (Best Practice)

```java
// GOOD - parameterized (efficient, no string concat if level disabled)
logger.info("User '{}' logged in after {} attempts", username, attempts);

// BAD - string concatenation (always evaluates)
logger.info("User '" + username + "' logged in after " + attempts + " attempts");
```

### Exception Logging

```java
try {
    riskyOperation();
} catch (Exception e) {
    // GOOD - log message + full stack trace
    logger.error("Operation failed: {}", e.getMessage(), e);

    // BAD - loses stack trace
    logger.error("Operation failed: " + e.getMessage());
}
```

### SLF4J vs Log4J

| Feature | SLF4J | Log4J |
|---------|-------|-------|
| Type | Facade / API | Implementation |
| Parameterized logging | Yes (native `{}`) | Yes (2.x) |
| Performance | Better (lazy eval) | Good |
| Flexibility | Swap backends easily | Tied to Log4J |
| Configuration | Via backend (logback.xml) | log4j2.xml |
| Spring Boot default | Yes (with Logback) | Requires config |

### Logback Configuration (logback.xml)

```xml
<configuration>
    <!-- Console Appender -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- File Appender with Rolling Policy -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/application.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/app.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

### Project Classes

| Class | Demonstrates |
|-------|-------------|
| `logging.LoggingBasics` | Logger creation, all 5 levels, parameterized, exception logging |
| `logging.BankingServiceLogger` | Real-world banking with structured logging |
| `logging.EmployeeServiceLogger` | CRUD operations with appropriate log levels |
| `logging.StudentManagementLogger` | Student management with logging best practices |
| `logging.LoggingBestPractices` | Do's and don'ts, performance considerations |

### Interview Notes

- **Q: What is SLF4J?**
  A: A logging facade that provides a common API. You code against SLF4J and swap implementations (Logback, Log4J) without code changes.

- **Q: Why use parameterized logging?**
  A: Avoids string concatenation cost when the log level is disabled. `{}` placeholders are only resolved if the message is actually logged.

- **Q: What is the default logging in Spring Boot?**
  A: SLF4J with Logback. The default level is INFO.

---

## 7. Lombok

### Definition

Project Lombok is a Java library that uses annotation processing to auto-generate boilerplate code at compile time -- getters, setters, constructors, builders, toString, equals, hashCode, and logging.

### How It Works

```
Source Code            Annotation Processor         Bytecode
(with Lombok)    --->  (javac plugin)          ---> (with generated methods)
@Data                  Generates getters,           .class file has all
class User {           setters, toString,           methods present
  String name;         equals, hashCode
}
```

### Annotations Reference

| Annotation | Generates | Mutability |
|-----------|-----------|------------|
| `@Getter` | Getter methods | - |
| `@Setter` | Setter methods | - |
| `@ToString` | toString() method | - |
| `@EqualsAndHashCode` | equals() and hashCode() | - |
| `@NoArgsConstructor` | No-args constructor | - |
| `@AllArgsConstructor` | All-args constructor | - |
| `@RequiredArgsConstructor` | Constructor for final fields | - |
| `@Data` | @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor | Mutable |
| `@Value` | Immutable @Data (all fields final, no setters) | Immutable |
| `@Builder` | Builder pattern | - |
| `@Builder.Default` | Default value in builder | - |
| `@Slf4j` | Logger field | - |

### @Data vs @Value

| Feature | @Data | @Value |
|---------|-------|--------|
| Mutability | Mutable | Immutable |
| Setters | Generated | Not generated |
| Fields | Normal | Implicitly final |
| Class | Normal | Implicitly final |
| Use case | Entities, DTOs | Config, immutable objects |
| Constructor | @RequiredArgsConstructor | @AllArgsConstructor |

### Builder Pattern

```
Order order = Order.builder()     // Start building
    .orderId("ORD-001")           // Set fields
    .customerName("Alice")        // one by one
    .totalAmount(99.99)           // in any order
    .build();                     // Create object

+----------+     +---------+     +--------+
| Builder() | --> | .field()| --> | build()| --> Object
+----------+     +---------+     +--------+
```

### @Slf4j Usage

```java
@Slf4j
public class MyService {
    // Lombok generates: private static final Logger log = LoggerFactory.getLogger(MyService.class);

    public void process() {
        log.info("Processing started");  // Use 'log' directly
    }
}
```

### Project Classes

| Class | Annotations Demonstrated |
|-------|-------------------------|
| `lombokdemo.Student` | @Data, @NoArgsConstructor, @AllArgsConstructor |
| `lombokdemo.Employee` | @Getter, @Setter, @ToString, @EqualsAndHashCode |
| `lombokdemo.Order` | @Builder, @Builder.Default |
| `lombokdemo.ImmutableConfig` | @Value (immutable) |
| `lombokdemo.DatabaseConnection` | @RequiredArgsConstructor, @Slf4j |
| `lombokdemo.Address` | @Builder, @ToString, @Getter |
| `lombokdemo.Product` | @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor |

### Interview Notes

- **Q: Difference between @Data and @Value?**
  A: @Data is mutable (generates setters). @Value is immutable (all fields final, no setters, class is final).

- **Q: How does Lombok work?**
  A: Annotation processing at compile time. The javac compiler plugin reads annotations and generates bytecode.

- **Q: What does @Slf4j do?**
  A: Generates a `private static final Logger log` field using SLF4J LoggerFactory.

---

## 8. Real-World Mini Projects

Six mini projects demonstrating JUnit, Mockito, Logging, and Lombok together:

| Project | Model Class | Service Class | Tests |
|---------|-------------|---------------|-------|
| Student Management | `Student` | `StudentService` | 6 tests |
| Employee Management | `Employee` | `EmployeeService` | 6 tests |
| Library Management | `Book` | `LibraryService` | 6 tests |
| Online Banking | `Account` | `BankingService` | 6 tests |
| Shopping Cart | `CartItem` | `ShoppingCartService` | 6 tests |
| Order Service | `OrderItem` | `OrderProcessingService` | 7 tests |

Each project uses SLF4J for logging and demonstrates testable service patterns.

---

## 9. Project Structure

```
src
 +-- main
 |    +-- java
 |    |    +-- tdd/
 |    |    |    +-- Calculator.java
 |    |    |    +-- StringUtility.java
 |    |    |    +-- BankAccount.java
 |    |    |    +-- EmployeeSalaryCalculator.java
 |    |    |    +-- StudentGradeCalculator.java
 |    |    +-- junit/
 |    |    |    +-- MathService.java
 |    |    |    +-- UserValidator.java
 |    |    |    +-- TaskManager.java
 |    |    +-- mockito/
 |    |    |    +-- UserRepository.java
 |    |    |    +-- UserService.java
 |    |    |    +-- EmailService.java
 |    |    |    +-- SmsService.java
 |    |    |    +-- PaymentGateway.java
 |    |    |    +-- OrderService.java
 |    |    |    +-- NotificationService.java
 |    |    |    +-- FileStorage.java
 |    |    |    +-- ReportGenerator.java
 |    |    |    +-- ProductRepository.java
 |    |    |    +-- ProductService.java
 |    |    +-- springtesting/
 |    |    |    +-- SpringTestingApplication.java
 |    |    |    +-- entity/Product.java
 |    |    |    +-- dto/ProductDTO.java
 |    |    |    +-- repository/ProductRepository.java
 |    |    |    +-- service/ProductService.java
 |    |    |    +-- controller/ProductController.java
 |    |    +-- automation/
 |    |    |    +-- config/FrameworkConfig.java
 |    |    |    +-- utils/TestUtility.java
 |    |    |    +-- data/TestData.java
 |    |    |    +-- framework/DataDrivenExample.java
 |    |    |    +-- framework/KeywordDrivenExample.java
 |    |    |    +-- framework/HybridFrameworkExample.java
 |    |    |    +-- pages/LoginPage.java
 |    |    +-- logging/
 |    |    |    +-- LoggingBasics.java
 |    |    |    +-- BankingServiceLogger.java
 |    |    |    +-- EmployeeServiceLogger.java
 |    |    |    +-- StudentManagementLogger.java
 |    |    |    +-- LoggingBestPractices.java
 |    |    +-- lombokdemo/
 |    |    |    +-- Student.java
 |    |    |    +-- Employee.java
 |    |    |    +-- Order.java
 |    |    |    +-- ImmutableConfig.java
 |    |    |    +-- DatabaseConnection.java
 |    |    |    +-- Address.java
 |    |    |    +-- Product.java
 |    |    +-- miniprojects/
 |    |         +-- studentmgmt/ (Student, StudentService)
 |    |         +-- employeemgmt/ (Employee, EmployeeService)
 |    |         +-- library/ (Book, LibraryService)
 |    |         +-- banking/ (Account, BankingService)
 |    |         +-- shopping/ (CartItem, ShoppingCartService)
 |    |         +-- orders/ (OrderItem, OrderProcessingService)
 |    +-- resources/
 |         +-- application.properties
 |         +-- application-test.properties
 |         +-- logback.xml
 +-- test
      +-- java
           +-- tdd/ (5 test classes)
           +-- junit/ (10 test classes)
           +-- mockito/ (7 test classes)
           +-- springtesting/
           |    +-- controller/ProductControllerTest.java
           |    +-- service/ProductServiceTest.java
           |    +-- repository/ProductRepositoryTest.java
           |    +-- integration/ProductIntegrationTest.java
           +-- automation/AutomationFrameworkTest.java
           +-- logging/ (3 test classes)
           +-- lombokdemo/ (2 test classes)
           +-- miniprojects/ (6 test classes)
```

---

## 10. How to Run

### Prerequisites

- Java 17 or higher
- Apache Maven 3.8+
- IDE with Lombok plugin (IntelliJ / Eclipse)

### Build the Project

```bash
cd "Module 4 - Test driven development and Logging framework/JUnit-Framework/test"
mvn clean compile
```

### Run All Tests

```bash
mvn test
```

### Run Tests by Package

```bash
# TDD tests only
mvn test -Dtest="tdd.*"

# JUnit 5 examples only
mvn test -Dtest="junit.*"

# Mockito tests only
mvn test -Dtest="mockito.*"

# Spring tests only
mvn test -Dtest="springtesting.**"
```

### Run Tests by Tag

```bash
mvn test -Dgroups="fast"
mvn test -DexcludedGroups="slow"
```

---

## 11. Quick Revision Section

### TDD Quick Points

- Red -> Green -> Refactor
- Write test BEFORE code
- One test at a time
- Test behavior, not implementation

### JUnit 5 Quick Points

- @Test marks test methods
- @BeforeEach runs before every test
- @BeforeAll runs once (static)
- assertAll groups multiple assertions
- @ParameterizedTest runs with multiple inputs
- @Nested groups related tests
- @Tag categorizes tests

### Mockito Quick Points

- mock() creates a fake object
- when().thenReturn() defines behavior
- verify() checks interactions
- spy() wraps a real object
- @InjectMocks injects mocks automatically
- doNothing/doThrow for void methods
- Argument matchers: any(), eq(), anyInt()

### Spring Testing Quick Points

- @SpringBootTest loads full context
- @WebMvcTest loads web layer only
- @DataJpaTest loads JPA layer only
- @MockBean replaces a Spring bean with mock
- MockMvc simulates HTTP requests

### Logging Quick Points

- Levels: TRACE < DEBUG < INFO < WARN < ERROR
- Use parameterized logging: `logger.info("User: {}", name)`
- Always log exceptions with the exception object
- Use SLF4J facade, not implementation directly

### Lombok Quick Points

- @Data = @Getter + @Setter + @ToString + @EqualsAndHashCode
- @Value = Immutable version of @Data
- @Builder = Builder pattern
- @Slf4j = Logger field generation
- Requires annotation processing enabled in IDE

---

## 12. Summary Comparison Tables

### JUnit 5 vs TestNG

| Feature | JUnit 5 | TestNG |
|---------|---------|--------|
| Test annotation | @Test | @Test |
| Setup per test | @BeforeEach | @BeforeMethod |
| Setup per class | @BeforeAll | @BeforeClass |
| Parameterized | @ParameterizedTest | @DataProvider |
| Grouping | @Tag | groups attribute |
| Parallel | Experimental | Built-in |
| Suite config | @Suite annotation | testng.xml |
| Spring integration | Native | Requires config |

### Mockito vs EasyMock

| Feature | Mockito | EasyMock |
|---------|---------|----------|
| API complexity | Simple, readable | More verbose |
| Record-replay | Not required | Required (replay mode) |
| Verify | verify() after execution | verify() after replay |
| Annotations | @Mock, @InjectMocks | @Mock (limited) |
| Void methods | doNothing/doThrow | expectLastCall() |
| Popularity | Industry standard | Less common |
| Spring support | @MockBean | Manual configuration |

### SLF4J vs Log4J

| Feature | SLF4J | Log4J 2 |
|---------|-------|---------|
| Type | Facade/API | Full implementation |
| Parameterized | Native `{}` | Yes (2.x) |
| Performance | Better (lazy eval) | Good |
| Flexibility | Swap backends | Tied to Log4J |
| Spring default | Yes (Logback) | Requires config |
| Configuration | Via backend | log4j2.xml |

### @Data vs @Value

| Feature | @Data | @Value |
|---------|-------|--------|
| Mutability | Mutable | Immutable |
| Setters | Yes | No |
| Final fields | No | Yes (implicit) |
| Final class | No | Yes (implicit) |
| Use case | Entities, DTOs | Config, constants |

### Mock vs Spy

| Feature | Mock | Spy |
|---------|------|-----|
| Real methods | Never called | Called by default |
| Default return | null/0/false | Real return value |
| Stubbing | when().thenReturn() | doReturn().when() |
| Use case | Full isolation | Partial mocking |
| Verification | Same (verify()) | Same (verify()) |

### Unit Test vs Integration Test

| Aspect | Unit Test | Integration Test |
|--------|-----------|------------------|
| Scope | Single unit | Multiple components |
| Speed | Fast (ms) | Slow (seconds) |
| Dependencies | All mocked | Real or embedded |
| Database | None | H2 / embedded |
| Spring context | Not loaded | Fully loaded |
| Reliability | High | Can be flaky |
| Debugging | Easy | Harder |
| Volume | Many (70-80%) | Fewer (15-20%) |

### Manual vs Automation Testing

| Aspect | Manual | Automation |
|--------|--------|------------|
| Execution | Human | Script/tool |
| Speed | Slow | Fast |
| Cost (initial) | Low | High |
| Cost (long-term) | High | Low |
| Regression | Tedious | Efficient |
| Accuracy | Error-prone | Consistent |
| Exploratory | Excellent | Not suitable |
| Maintenance | None | Script maintenance |

### Data Driven vs Keyword Driven vs Hybrid

| Feature | Data Driven | Keyword Driven | Hybrid |
|---------|------------|----------------|--------|
| Approach | Same test, different data | Keywords map to actions | Combined |
| Data source | CSV, Excel, DB | Keyword tables | Both |
| Flexibility | Medium | High | Highest |
| Complexity | Low | Medium | High |
| Reusability | Data reuse | Action reuse | Full reuse |
| Best for | Repetitive data tests | Non-technical testers | Enterprise |
| Maintenance | Low | Moderate | Moderate |

---

## Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17+ | Programming language |
| Maven | 3.8+ | Build tool |
| Spring Boot | 3.3.x | Application framework |
| JUnit 5 | 5.10+ | Testing framework |
| Mockito | 5.x | Mocking framework |
| SLF4J | 2.x | Logging facade |
| Logback | 1.4+ | Logging implementation |
| Lombok | 1.18+ | Boilerplate reduction |
| H2 Database | 2.x | In-memory test database |

---

**Total Test Methods: 100+**
**Total Source Files: ~56 main classes + ~38 test classes**

This project serves as a complete reference for Test Driven Development, JUnit 5, Mockito, Spring Testing, Automation Testing, SLF4J Logging, and Lombok.
