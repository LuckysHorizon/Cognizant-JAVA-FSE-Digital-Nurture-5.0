# Spring Data JPA with Spring Boot and Hibernate - Complete Study Notes

---

# 1. Introduction

## What is it?

Spring Data JPA is a part of the Spring Data project that makes it easy to work with databases using Java Persistence API (JPA). It removes boilerplate code for database operations.

- **JPA (Java Persistence API)**: A specification that defines how Java objects should be mapped to database tables. It is just a set of rules (interfaces), not an implementation.
- **Hibernate**: The most popular implementation of JPA. It does the actual work of converting Java objects to SQL queries and vice versa.
- **Spring Data JPA**: A Spring module that sits on top of JPA/Hibernate and provides ready-made repository interfaces so you don't have to write basic CRUD code.

---

## Why do we use it?

- No need to write SQL for basic operations
- Reduces boilerplate code by 80%
- Automatic table creation from Java classes
- Built-in pagination and sorting
- Type-safe queries
- Easy integration with Spring Boot

---

## How it works

```
Spring Boot Application
       |
    Controller (REST API)
       |
    Service (Business Logic)
       |
    Repository (Spring Data JPA)
       |
    Hibernate (JPA Implementation)
       |
    JDBC Driver
       |
    MySQL Database
```

---

## Comparison Table

| Feature | JDBC | Hibernate | Spring Data JPA |
|---------|------|-----------|----------------|
| Boilerplate Code | High | Medium | Very Low |
| SQL Required | Yes | Optional (HQL) | No (Derived Queries) |
| Connection Management | Manual | Auto | Auto |
| Transaction Management | Manual | Semi-Auto | Auto |
| Pagination | Manual | Manual | Built-in |
| Learning Curve | Low | Medium | Low |
| Performance Tuning | Full Control | Good Control | Limited Control |

---

## Important Points

- JPA is a specification, Hibernate is the implementation
- Spring Data JPA uses Hibernate internally by default
- You write interfaces, Spring generates the implementation at runtime
- No need to write DAO classes manually

---

## Interview Questions

**Q: What is the difference between JPA and Hibernate?**
A: JPA is a specification (set of interfaces and rules). Hibernate is the actual implementation that follows JPA rules. Think of JPA as a blueprint and Hibernate as the building.

**Q: Why use Spring Data JPA over plain Hibernate?**
A: Spring Data JPA eliminates boilerplate code. You just define an interface extending JpaRepository, and Spring auto-generates all CRUD operations at runtime.

**Q: Can you use Spring Data JPA without Hibernate?**
A: Yes, you can use other JPA providers like EclipseLink. Hibernate is just the default.

---

# 2. Project Setup

## What is it?

Setting up a Spring Boot project with Spring Data JPA, Hibernate, and MySQL.

---

## Step 1: Spring Initializr

Go to https://start.spring.io

| Setting | Value |
|---------|-------|
| Project | Maven |
| Language | Java |
| Spring Boot | 4.0.0 |
| Group | com.example |
| Artifact | studentmanagement |
| Name | StudentManagement |
| Packaging | Jar |
| Java | 24 |

**Dependencies to add:**
- Spring Web
- Spring Data JPA
- MySQL Driver
- Lombok
- Spring Boot DevTools
- Validation

---

## Step 2: pom.xml

`pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.0.0</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>studentmanagement</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>StudentManagement</name>
    <description>Student Management System with Spring Data JPA</description>

    <properties>
        <java.version>24</java.version>
    </properties>

    <dependencies>
        <!-- Spring Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- MySQL Driver -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- DevTools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Step 3: application.properties

`src/main/resources/application.properties`
```properties
# Server Configuration
server.port=8080

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

**ddl-auto Options:**

| Value | Behavior | Use When |
|-------|----------|----------|
| `create` | Drops and recreates tables on every startup | Testing only |
| `create-drop` | Creates on startup, drops on shutdown | Unit tests |
| `update` | Updates schema without data loss | Development |
| `validate` | Validates schema, no changes | Production |
| `none` | No action | Production (with Flyway/Liquibase) |

---

## Project Structure

```
studentmanagement/
 +-- src/
 |    +-- main/
 |    |    +-- java/com/example/studentmanagement/
 |    |    |    +-- StudentManagementApplication.java
 |    |    |    +-- entity/
 |    |    |    |    +-- Student.java
 |    |    |    +-- repository/
 |    |    |    |    +-- StudentRepository.java
 |    |    |    +-- service/
 |    |    |    |    +-- StudentService.java
 |    |    |    |    +-- impl/StudentServiceImpl.java
 |    |    |    +-- controller/
 |    |    |    |    +-- StudentController.java
 |    |    |    +-- dto/
 |    |    |    |    +-- StudentDTO.java
 |    |    |    +-- exception/
 |    |    |         +-- ResourceNotFoundException.java
 |    |    +-- resources/
 |    |         +-- application.properties
 |    +-- test/
 +-- pom.xml
```

---

## Quick Revision

- Use Spring Initializr to bootstrap the project
- Add spring-boot-starter-data-jpa and mysql-connector-j
- Configure datasource URL, username, password in application.properties
- Set ddl-auto=update for development, validate for production
- show-sql=true to see generated queries in console

---

# 3. Entity Mapping

## What is it?

Entity mapping is the process of connecting a Java class to a database table. Each field in the class becomes a column in the table.

---

## Why do we use it?

- No need to write CREATE TABLE SQL
- Java objects directly represent database rows
- Changes in Java class auto-update the table schema
- Type safety at compile time

---

## How it works

```
Java Class (Entity)          Database Table
+-------------------+        +-------------------+
| @Entity           |        |                   |
| Student           | -----> | students          |
|   id              |        |   id (PK)         |
|   firstName       |        |   first_name      |
|   lastName        |        |   last_name       |
|   email           |        |   email           |
+-------------------+        +-------------------+
```

---

## Core Annotations

| Annotation | Purpose | Example |
|------------|---------|--------|
| `@Entity` | Marks class as a JPA entity | `@Entity` |
| `@Table` | Custom table name | `@Table(name = "students")` |
| `@Id` | Primary key field | `@Id` |
| `@GeneratedValue` | Auto-generate PK | `@GeneratedValue(strategy = GenerationType.IDENTITY)` |
| `@Column` | Custom column config | `@Column(name = "first_name", nullable = false)` |
| `@Transient` | Skip field from DB | `@Transient` |
| `@Enumerated` | Map enum to column | `@Enumerated(EnumType.STRING)` |
| `@Temporal` | Date/time mapping | `@Temporal(TemporalType.DATE)` |
| `@Lob` | Large objects | `@Lob` |

---

## GenerationType Strategies

| Strategy | Description | Best For |
|----------|-------------|----------|
| `IDENTITY` | Database auto-increment | MySQL |
| `SEQUENCE` | Database sequence | PostgreSQL, Oracle |
| `TABLE` | Separate table for IDs | Any database |
| `AUTO` | Let Hibernate decide | General use |
| `UUID` | UUID generation | Distributed systems |

---

## Implementation - Student Entity

`src/main/java/com/example/studentmanagement/entity/Student.java`
```java
package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
```

`src/main/java/com/example/studentmanagement/entity/Gender.java`
```java
package com.example.studentmanagement.entity;

public enum Gender {
    MALE, FEMALE, OTHER
}
```

---

## Relationship Mappings

### Overview

| Relationship | Annotation | Example |
|-------------|------------|--------|
| One-to-One | `@OneToOne` | Student has one Address |
| One-to-Many | `@OneToMany` | Department has many Students |
| Many-to-One | `@ManyToOne` | Many Students in one Department |
| Many-to-Many | `@ManyToMany` | Students enroll in many Courses |

---

### @OneToOne

```
Student          Address
+--------+       +---------+
| id (PK)|       | id (PK) |
| name   |       | city    |
|        | 1---1 | state   |
+--------+       +---------+
```

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String state;

    @OneToOne(mappedBy = "address")
    private Student student;
}
```

---

### @OneToMany and @ManyToOne

```
Department              Student
+------------+          +--------+
| id (PK)    | 1------* | id (PK)|
| name       |          | name   |
+------------+          | dept_id|
                        +--------+
```

```java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
}

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
```

---

### @ManyToMany

```
Student              Course
+--------+           +--------+
| id (PK)| *-------* | id (PK)|
| name   |           | title  |
+--------+           +--------+
         \           /
     student_courses (Join Table)
     +------------+-----------+
     | student_id | course_id |
     +------------+-----------+
```

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
```

---

## Cascade Types

| Type | Behavior |
|------|----------|
| `ALL` | All operations cascade |
| `PERSIST` | Save parent saves child |
| `MERGE` | Update parent updates child |
| `REMOVE` | Delete parent deletes child |
| `REFRESH` | Refresh parent refreshes child |
| `DETACH` | Detach parent detaches child |

---

## Fetch Types

| Type | Behavior | Default For |
|------|----------|------------|
| `EAGER` | Load immediately with parent | @OneToOne, @ManyToOne |
| `LAZY` | Load only when accessed | @OneToMany, @ManyToMany |

Best Practice: Always use `LAZY` and fetch explicitly when needed.

---

## Interview Questions

**Q: What is the difference between @JoinColumn and mappedBy?**
A: @JoinColumn is used on the owning side (the table that has the foreign key). mappedBy is used on the inverse side to indicate which field owns the relationship.

**Q: What is CascadeType.ALL?**
A: It means all operations (save, update, delete, refresh, detach) on the parent entity will automatically cascade to the child entity.

**Q: What is the N+1 problem?**
A: When you load a list of entities and each entity triggers a separate query to load its relationships. Solution: Use JOIN FETCH or @EntityGraph.

---

# 4. Repository

## What is it?

A repository is an interface that provides methods to interact with the database. Spring Data JPA generates the implementation automatically at runtime.

---

## Repository Hierarchy

```
Repository (marker interface)
    |
    +-- CrudRepository (basic CRUD)
         |
         +-- PagingAndSortingRepository (pagination + sorting)
              |
              +-- JpaRepository (JPA-specific: batch ops, flush, etc.)
```

---

## Comparison

| Interface | Key Methods | When to Use |
|-----------|-------------|-------------|
| `CrudRepository` | save, findById, findAll, delete, count | Simple CRUD |
| `PagingAndSortingRepository` | findAll(Pageable), findAll(Sort) | Need pagination |
| `JpaRepository` | saveAll, flush, deleteInBatch, getById | Full features |

Best Practice: Use `JpaRepository` - it includes everything.

---

## Implementation

`src/main/java/com/example/studentmanagement/repository/StudentRepository.java`
```java
package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Derived Query Methods
    List<Student> findByFirstName(String firstName);

    List<Student> findByLastNameIgnoreCase(String lastName);

    Optional<Student> findByEmail(String email);

    List<Student> findByFirstNameContaining(String keyword);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    boolean existsByEmail(String email);

    long countByGender(Gender gender);

    List<Student> findByOrderByFirstNameAsc();

    // JPQL Query
    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findStudentByEmail(@Param("email") String email);

    // Native SQL Query
    @Query(value = "SELECT * FROM students WHERE first_name LIKE %:keyword%", nativeQuery = true)
    List<Student> searchByFirstName(@Param("keyword") String keyword);

    // JPQL with multiple conditions
    @Query("SELECT s FROM Student s WHERE s.firstName = :first AND s.lastName = :last")
    Optional<Student> findByFullName(@Param("first") String firstName, @Param("last") String lastName);
}
```

---

## Important Points

- No need to write `@Repository` if extending JpaRepository (Spring auto-detects it), but it is good practice
- Spring generates the implementation class at runtime using proxies
- Method names must follow exact naming conventions
- Return `Optional<T>` for single results to avoid NullPointerException

---

# 5. CRUD Operations

## What is it?

CRUD stands for Create, Read, Update, Delete -- the four basic database operations exposed through REST APIs.

---

## How it works

```
Client (Postman)          Controller          Service           Repository         Database
     |                       |                  |                  |                  |
     |--- POST /students --> |                  |                  |                  |
     |                       |-- save() ------> |                  |                  |
     |                       |                  |-- save() ------> |                  |
     |                       |                  |                  |-- INSERT -------> |
     |                       |                  |                  |<-- OK ----------- |
     |                       |                  |<-- Student ------|                  |
     |                       |<-- Student ------|                  |                  |
     |<-- 201 Created -------|                  |                  |                  |
```

---

## DTO

`src/main/java/com/example/studentmanagement/dto/StudentDTO.java`
```java
package com.example.studentmanagement.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
}
```

---

## Exception Handling

`src/main/java/com/example/studentmanagement/exception/ResourceNotFoundException.java`
```java
package com.example.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

---

## Service Interface

`src/main/java/com/example/studentmanagement/service/StudentService.java`
```java
package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
}
```

---

## Service Implementation

`src/main/java/com/example/studentmanagement/service/impl/StudentServiceImpl.java`
```java
package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Gender;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = mapToEntity(dto);
        Student saved = studentRepository.save(student);
        return mapToDTO(saved);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return mapToDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getGender() != null) {
            student.setGender(Gender.valueOf(dto.getGender()));
        }

        Student updated = studentRepository.save(student);
        return mapToDTO(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    private StudentDTO mapToDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender() != null ? student.getGender().name() : null)
                .build();
    }

    private Student mapToEntity(StudentDTO dto) {
        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender() != null ? Gender.valueOf(dto.getGender()) : null)
                .build();
    }
}
```

---

## Controller

`src/main/java/com/example/studentmanagement/controller/StudentController.java`
```java
package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO created = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
```

---

## REST API Summary

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| POST | `/api/students` | Create student | 201 Created |
| GET | `/api/students/{id}` | Get by ID | 200 OK |
| GET | `/api/students` | Get all | 200 OK |
| PUT | `/api/students/{id}` | Update student | 200 OK |
| DELETE | `/api/students/{id}` | Delete student | 200 OK |

---

## Postman Examples

**POST /api/students**
```json
{
    "firstName": "Rahul",
    "lastName": "Sharma",
    "email": "rahul.sharma@example.com",
    "dateOfBirth": "2000-05-15",
    "gender": "MALE"
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "firstName": "Rahul",
    "lastName": "Sharma",
    "email": "rahul.sharma@example.com",
    "dateOfBirth": "2000-05-15",
    "gender": "MALE"
}
```

**PUT /api/students/1**
```json
{
    "firstName": "Rahul",
    "lastName": "Sharma",
    "email": "rahul.updated@example.com",
    "dateOfBirth": "2000-05-15",
    "gender": "MALE"
}
```

---

# 6. Query Methods

## What is it?

Spring Data JPA lets you define database queries just by naming your methods properly. No SQL needed.

---

## Derived Query Methods

Spring reads the method name and generates the query automatically.

| Method Name | Generated SQL (simplified) |
|-------------|---------------------------|
| `findByFirstName(String fn)` | `WHERE first_name = ?` |
| `findByFirstNameAndLastName(String fn, String ln)` | `WHERE first_name = ? AND last_name = ?` |
| `findByFirstNameOrLastName(String fn, String ln)` | `WHERE first_name = ? OR last_name = ?` |
| `findByFirstNameContaining(String kw)` | `WHERE first_name LIKE %?%` |
| `findByFirstNameStartingWith(String prefix)` | `WHERE first_name LIKE ?%` |
| `findByFirstNameEndingWith(String suffix)` | `WHERE first_name LIKE %?` |
| `findByEmailIgnoreCase(String email)` | `WHERE LOWER(email) = LOWER(?)` |
| `findByAgeBetween(int min, int max)` | `WHERE age BETWEEN ? AND ?` |
| `findByAgeGreaterThan(int age)` | `WHERE age > ?` |
| `findByAgeLessThan(int age)` | `WHERE age < ?` |
| `findByAgeGreaterThanEqual(int age)` | `WHERE age >= ?` |
| `findByFirstNameIsNull()` | `WHERE first_name IS NULL` |
| `findByFirstNameIsNotNull()` | `WHERE first_name IS NOT NULL` |
| `findByOrderByFirstNameAsc()` | `ORDER BY first_name ASC` |
| `findByOrderByFirstNameDesc()` | `ORDER BY first_name DESC` |
| `findTop5ByOrderByIdDesc()` | `LIMIT 5 ORDER BY id DESC` |
| `countByGender(Gender g)` | `SELECT COUNT(*) WHERE gender = ?` |
| `existsByEmail(String email)` | Returns boolean |
| `deleteByEmail(String email)` | Deletes matching rows |

---

## @Query - JPQL

JPQL uses entity names and field names (not table/column names).

```java
@Query("SELECT s FROM Student s WHERE s.firstName = :name")
List<Student> findByName(@Param("name") String name);

@Query("SELECT s FROM Student s WHERE s.email LIKE %:domain")
List<Student> findByEmailDomain(@Param("domain") String domain);

@Query("SELECT s FROM Student s WHERE s.gender = :gender ORDER BY s.firstName")
List<Student> findByGenderSorted(@Param("gender") Gender gender);

@Query("SELECT COUNT(s) FROM Student s WHERE s.gender = :gender")
long countByGenderQuery(@Param("gender") Gender gender);

@Query("UPDATE Student s SET s.email = :email WHERE s.id = :id")
@Modifying
@Transactional
void updateEmail(@Param("id") Long id, @Param("email") String email);

@Query("DELETE FROM Student s WHERE s.email = :email")
@Modifying
@Transactional
void deleteByEmailQuery(@Param("email") String email);
```

---

## @Query - Native SQL

Use actual SQL with table and column names.

```java
@Query(value = "SELECT * FROM students WHERE first_name = :name", nativeQuery = true)
List<Student> findByNameNative(@Param("name") String name);

@Query(value = "SELECT * FROM students ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
List<Student> findRecentStudents(@Param("limit") int limit);
```

---

## @NamedQuery and @NamedQueries

Define queries on the entity class.

```java
@Entity
@NamedQuery(name = "Student.findByEmailDomain",
            query = "SELECT s FROM Student s WHERE s.email LIKE :domain")
@NamedQueries({
    @NamedQuery(name = "Student.findAllActive",
                query = "SELECT s FROM Student s WHERE s.active = true"),
    @NamedQuery(name = "Student.countAll",
                query = "SELECT COUNT(s) FROM Student s")
})
public class Student { ... }
```

Use in repository:
```java
List<Student> findByEmailDomain(@Param("domain") String domain);
```

---

## JPQL vs Native SQL

| Feature | JPQL | Native SQL |
|---------|------|------------|
| Uses | Entity/field names | Table/column names |
| Database portable | Yes | No |
| Complex queries | Limited | Full SQL power |
| Joins | Entity relationships | SQL joins |
| nativeQuery flag | false (default) | true |

---

## Interview Questions

**Q: What is a Derived Query Method?**
A: A method where Spring reads the method name and auto-generates the SQL query. Example: findByFirstName generates WHERE first_name = ?.

**Q: Difference between JPQL and Native SQL?**
A: JPQL uses Java entity names and is database-portable. Native SQL uses actual table names and is database-specific.

**Q: When to use @Query vs Derived Methods?**
A: Use derived methods for simple queries. Use @Query for complex logic involving joins, subqueries, or aggregations.

---

# 7. Pagination and Sorting

## What is it?

Pagination splits large result sets into smaller pages. Sorting orders results by one or more columns.

---

## Why do we use it?

- Loading 1 million records at once crashes the application
- Users need to browse data page by page
- Sorting helps users find data quickly

---

## Key Classes

| Class | Purpose |
|-------|--------|
| `Pageable` | Interface for pagination info |
| `PageRequest` | Implementation of Pageable |
| `Page<T>` | Result container with pagination metadata |
| `Sort` | Sorting specification |
| `Sort.Direction` | ASC or DESC |

---

## Implementation

**Repository** (Already extends JpaRepository which has pagination)

```java
// In StudentRepository
Page<Student> findByGender(Gender gender, Pageable pageable);

@Query("SELECT s FROM Student s WHERE s.firstName LIKE %:keyword%")
Page<Student> searchStudents(@Param("keyword") String keyword, Pageable pageable);
```

**Service**

```java
public Page<StudentDTO> getStudentsPaginated(int page, int size, String sortBy, String direction) {
    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Student> studentPage = studentRepository.findAll(pageable);

    return studentPage.map(this::mapToDTO);
}

// Multiple sorting
public Page<StudentDTO> getStudentsMultiSort(int page, int size) {
    Sort sort = Sort.by(
            Sort.Order.asc("lastName"),
            Sort.Order.desc("firstName")
    );
    Pageable pageable = PageRequest.of(page, size, sort);
    return studentRepository.findAll(pageable).map(this::mapToDTO);
}
```

**Controller**

```java
@GetMapping("/paginated")
public ResponseEntity<Page<StudentDTO>> getStudentsPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    return ResponseEntity.ok(studentService.getStudentsPaginated(page, size, sortBy, direction));
}
```

---

## Page Response Structure

```json
{
    "content": [ ... ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": { "sorted": true }
    },
    "totalPages": 5,
    "totalElements": 50,
    "last": false,
    "first": true,
    "numberOfElements": 10,
    "size": 10,
    "number": 0,
    "empty": false
}
```

---

## Important Page Methods

| Method | Returns |
|--------|--------|
| `getContent()` | List of elements |
| `getTotalPages()` | Total number of pages |
| `getTotalElements()` | Total records in DB |
| `getNumber()` | Current page number |
| `getSize()` | Page size |
| `isFirst()` | Is first page |
| `isLast()` | Is last page |
| `hasNext()` | Has next page |
| `hasPrevious()` | Has previous page |

---

## Quick Revision

- PageRequest.of(page, size) creates a Pageable
- Page numbers start from 0
- Sort.by("field") for single column sorting
- Sort.by(Order.asc("a"), Order.desc("b")) for multi-column
- JpaRepository already supports pagination

---

# 8. JPA Auditing

## What is it?

Automatically track who created or modified an entity and when.

---

## Why do we use it?

- Audit trail for compliance
- No manual setting of timestamps
- Know who last modified a record

---

## Annotations

| Annotation | Purpose |
|------------|--------|
| `@CreatedDate` | Auto-set creation timestamp |
| `@LastModifiedDate` | Auto-set update timestamp |
| `@CreatedBy` | Auto-set creator username |
| `@LastModifiedBy` | Auto-set modifier username |
| `@EntityListeners` | Register audit listener |
| `@EnableJpaAuditing` | Enable auditing in app |

---

## Step-by-Step Implementation

**Step 1: Enable JPA Auditing**

`src/main/java/com/example/studentmanagement/StudentManagementApplication.java`
```java
package com.example.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }
}
```

**Step 2: Create Auditable Base Entity**

`src/main/java/com/example/studentmanagement/entity/Auditable.java`
```java
package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
```

**Step 3: Implement AuditorAware**

`src/main/java/com/example/studentmanagement/config/AuditorAwareImpl.java`
```java
package com.example.studentmanagement.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // In real app, get from SecurityContext
        // return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        return Optional.of("SYSTEM");
    }
}
```

**Step 4: Extend Entity from Auditable**

```java
@Entity
@Table(name = "students")
public class Student extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
```

Now `createdAt`, `updatedAt`, `createdBy`, `updatedBy` are automatically managed.

---

# 9. Projections

## What is it?

Projections let you fetch only specific columns from the database instead of the entire entity.

---

## Why do we use it?

- Better performance (less data transferred)
- API returns only needed fields
- Security (hide sensitive fields)

---

## Types of Projections

| Type | How | Use When |
|------|-----|----------|
| Interface Projection | Define interface with getters | Simple, read-only |
| DTO Projection | Use class constructor | Complex transformations |
| Dynamic Projection | Generic return type | Multiple views of same entity |

---

## Interface Projection (Closed)

```java
public interface StudentNameProjection {
    String getFirstName();
    String getLastName();
    String getEmail();
}

// In repository
List<StudentNameProjection> findAllProjectedBy();
List<StudentNameProjection> findByGender(Gender gender);
```

---

## Interface Projection (Open - with @Value)

```java
public interface StudentFullNameProjection {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    String getEmail();
}
```

---

## DTO Projection (Constructor Expression)

```java
public record StudentSummary(String firstName, String lastName, String email) {}

// In repository
@Query("SELECT new com.example.studentmanagement.dto.StudentSummary(s.firstName, s.lastName, s.email) FROM Student s")
List<StudentSummary> findStudentSummaries();
```

---

## Dynamic Projection

```java
// In repository
<T> List<T> findByGender(Gender gender, Class<T> type);

// Usage
List<StudentNameProjection> names = repository.findByGender(Gender.MALE, StudentNameProjection.class);
List<StudentSummary> summaries = repository.findByGender(Gender.MALE, StudentSummary.class);
```

---

# 10. Spring Boot Integration

## Auto Configuration

Spring Boot auto-configures:
- DataSource from application.properties
- EntityManagerFactory
- TransactionManager
- Repository implementations

You only need to add the dependency and write properties.

---

## application.properties - Complete Reference

```properties
# DataSource
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Connection Pool (HikariCP - default)
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.connection-timeout=20000

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.open-in-view=false

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## Multiple Data Sources

```properties
# Primary DataSource
spring.datasource.primary.url=jdbc:mysql://localhost:3306/primary_db
spring.datasource.primary.username=root
spring.datasource.primary.password=root

# Secondary DataSource
spring.datasource.secondary.url=jdbc:mysql://localhost:3306/secondary_db
spring.datasource.secondary.username=root
spring.datasource.secondary.password=root
```

```java
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
```

---

# 11. Hibernate Deep Dive

## Hibernate Features

| Feature | Purpose |
|---------|--------|
| `@DynamicInsert` | Generate INSERT only for non-null fields |
| `@DynamicUpdate` | Generate UPDATE only for changed fields |
| `@Formula` | Computed column using SQL |
| `@BatchSize` | Batch loading for collections |
| `@Where` | Default WHERE clause |
| `@SQLDelete` | Custom DELETE SQL (soft delete) |

---

## @DynamicInsert and @DynamicUpdate

```java
@Entity
@DynamicInsert
@DynamicUpdate
public class Student {
    // Without @DynamicUpdate:
    // UPDATE students SET first_name=?, last_name=?, email=?, ... WHERE id=?
    // (updates ALL columns even if only email changed)

    // With @DynamicUpdate:
    // UPDATE students SET email=? WHERE id=?
    // (updates only changed columns)
}
```

---

## @Formula

```java
@Entity
public class Student {
    @Id
    private Long id;
    private String firstName;
    private String lastName;

    @Formula("CONCAT(first_name, ' ', last_name)")
    private String fullName;  // Computed, not stored in DB
}
```

---

## Soft Delete with @SQLDelete

```java
@Entity
@SQLDelete(sql = "UPDATE students SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Student {
    @Id
    private Long id;
    private String name;
    private boolean deleted = false;
}
```

---

## Batch Processing

```properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

```java
@Transactional
public void saveAllStudents(List<Student> students) {
    // saveAll uses batch internally when batch_size is set
    studentRepository.saveAll(students);
}
```

---

## Hibernate Properties Reference

| Property | Purpose | Value |
|----------|---------|-------|
| `hibernate.dialect` | SQL dialect | MySQLDialect |
| `hibernate.show_sql` | Log SQL | true/false |
| `hibernate.format_sql` | Pretty print SQL | true/false |
| `hibernate.jdbc.batch_size` | Batch insert/update size | 50 |
| `hibernate.order_inserts` | Group inserts for batching | true |
| `hibernate.generate_statistics` | Performance stats | true/false |
| `hibernate.default_batch_fetch_size` | Lazy collection batch size | 16 |

---

## Interview Questions

**Q: What is the N+1 problem?**
A: When loading N entities that each have a lazy collection, Hibernate fires N+1 queries: 1 for the parent list and N for each child collection. Fix with JOIN FETCH, @EntityGraph, or @BatchSize.

**Q: What is the difference between get() and load() in Hibernate?**
A: get() hits the database immediately and returns null if not found. load() returns a proxy and throws ObjectNotFoundException when accessed if not found.

**Q: What is a Hibernate Session?**
A: A Session is a wrapper around a JDBC connection. It represents a unit of work with the database. It caches entities (first-level cache) and manages their lifecycle.

**Q: What is the first-level cache?**
A: It is the Session-level cache. Every entity loaded within a Session is cached. If you load the same entity twice in one Session, the second load hits the cache, not the database.

**Q: What is the second-level cache?**
A: A SessionFactory-level cache shared across Sessions. Needs explicit configuration (EhCache, Hazelcast). Useful for read-heavy, rarely-updated data.

---

# Complete Revision Sheet

## One-Page Summary

**Setup:**
- spring-boot-starter-data-jpa + mysql-connector-j
- Configure datasource URL, username, password
- ddl-auto: update (dev), validate (prod)

**Entity:**
- @Entity + @Table + @Id + @GeneratedValue
- @Column for customization
- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany for relationships
- Owner side has @JoinColumn, inverse side has mappedBy

**Repository:**
- Extend JpaRepository<Entity, ID>
- Derived methods: findByXxx, countByXxx, existsByXxx
- @Query for JPQL or native SQL
- @Modifying + @Transactional for UPDATE/DELETE queries

**CRUD:**
- save() for CREATE and UPDATE
- findById() for READ
- findAll() for READ ALL
- delete() for DELETE

**Pagination:**
- PageRequest.of(page, size, sort)
- Returns Page<T> with metadata
- Pages start from 0

**Auditing:**
- @EnableJpaAuditing + @EntityListeners(AuditingEntityListener.class)
- @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy
- Implement AuditorAware for username

**Projections:**
- Interface projection: define interface with getters
- DTO projection: constructor expression in @Query
- Dynamic: use Class<T> parameter

**Hibernate:**
- @DynamicUpdate: update only changed columns
- Batch processing: hibernate.jdbc.batch_size
- Soft delete: @SQLDelete + @Where
- N+1 fix: JOIN FETCH, @EntityGraph, @BatchSize

**Key Differences:**
- JPA = specification, Hibernate = implementation
- JPQL = entity-based, Native SQL = table-based
- EAGER = load now, LAZY = load later
- @Mock = full isolation, @MockBean = Spring context
- save() works for both insert and update (checks if ID exists)
