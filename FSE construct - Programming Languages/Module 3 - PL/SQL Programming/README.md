# Module 3 - PL/SQL Programming

## Cognizant Java FSE Digital Nurture 5.0

---

## Module Overview

This module provides a complete, hands-on guide to PL/SQL (Procedural Language extension to SQL) programming. PL/SQL is Oracle Corporation's procedural extension for SQL and the Oracle relational database. It combines the data-manipulating power of SQL with the processing power of procedural languages, enabling developers to build robust, high-performance database applications.

This module is part of the **Cognizant Java Full Stack Engineer (FSE) Digital Nurture 5.0** training program under the **Programming Languages** construct. It is designed for complete beginners and covers everything from basic syntax to advanced topics like packages and triggers, with executable SQL programs and detailed notes for every concept.

---

## Learning Objectives

Upon completing this module, you will be able to:

- Understand the difference between SQL and PL/SQL and when to use each
- Set up and use PL/SQL development environments (SQL*Plus, SQL Developer, Oracle Live SQL)
- Write and execute anonymous and named PL/SQL blocks
- Declare and use variables, constants, and various PL/SQL data types
- Implement control structures including conditional statements and loops
- Handle runtime errors using predefined, user-defined, and application-level exceptions
- Work with implicit and explicit cursors for row-by-row data processing
- Create reusable stored procedures and functions with IN, OUT, and IN OUT parameters
- Design and implement PL/SQL packages for modular code organization
- Build database triggers for automatic event-driven processing
- Write interview-ready and lab-exam-ready PL/SQL programs

---

## Folder Structure

```
MODULE-3 - PL/SQL PROGRAMMING
|
├── README.md
├── Resources.md
|
├── 01-Introduction/
|      ├── Notes.md
|      └── SQL_vs_PLSQL.md
|
├── 02-Environment/
|      ├── Notes.md
|      ├── Anonymous_Block.sql
|      └── Named_Block.sql
|
├── 03-Basic_Syntax/
|      ├── Notes.md
|      ├── Variables.sql
|      ├── DataTypes.sql
|      ├── Assignment.sql
|      ├── Print_Output.sql
|      └── User_Input.sql
|
├── 04-Control_Structures/
|      ├── Notes.md
|      ├── If.sql
|      ├── IfElse.sql
|      ├── IfElsif.sql
|      ├── Case.sql
|      ├── Loop.sql
|      ├── While.sql
|      ├── For.sql
|      ├── Reverse_For.sql
|      ├── OddNumbers.sql
|      ├── EvenNumbers.sql
|      └── MultiplicationTable.sql
|
├── 05-Exception_Handling/
|      ├── Notes.md
|      ├── ZeroDivide.sql
|      ├── NoDataFound.sql
|      ├── TooManyRows.sql
|      ├── ValueError.sql
|      ├── UserDefinedException.sql
|      └── RaiseApplicationError.sql
|
├── 06-Cursors/
|      ├── Notes.md
|      ├── ImplicitCursor.sql
|      ├── ExplicitCursor.sql
|      ├── CursorOpenFetchClose.sql
|      ├── CursorAttributes.sql
|      └── EmployeeCursor.sql
|
├── 07-Procedures_Functions/
|      ├── Notes.md
|      ├── SimpleProcedure.sql
|      ├── Procedure_IN.sql
|      ├── Procedure_OUT.sql
|      ├── Procedure_IN_OUT.sql
|      ├── SimpleFunction.sql
|      └── SalaryFunction.sql
|
├── 08-Packages/
|      ├── Notes.md
|      ├── PackageSpecification.sql
|      ├── PackageBody.sql
|      └── ExecutePackage.sql
|
├── 09-Triggers/
|      ├── Notes.md
|      ├── BeforeTrigger.sql
|      ├── AfterTrigger.sql
|      ├── InsteadOfTrigger.sql
|      └── TriggerManagement.sql
|
└── 10-Practice/
       ├── MiniPrograms.sql
       ├── InterviewPrograms.sql
       └── LabPrograms.sql
```

**Total: 10 Folders | 50+ Files | Covers All Core PL/SQL Topics**

---

## Oracle Version Used

| Detail               | Value                                      |
|-----------------------|--------------------------------------------|
| Database Version      | Oracle Database 21c (compatible with 11g+) |
| Online Platform       | Oracle Live SQL (live.oracle.com)           |
| Local Tools Supported | SQL*Plus, Oracle SQL Developer              |
| Schema Used           | HR (Human Resources) Sample Schema          |

> All programs in this module are written to be compatible with Oracle 11g and above. They can be executed on Oracle Live SQL without any installation.

---

## Execution Instructions

### Method 1: Oracle Live SQL (Recommended for Beginners)

1. Visit [Oracle Live SQL](https://livesql.oracle.com)
2. Sign in with a free Oracle account
3. Click on **SQL Worksheet**
4. Copy and paste the `.sql` file content into the worksheet
5. Click **Run** to execute

### Method 2: SQL*Plus (Command Line)

```
Step 1: Open Command Prompt or Terminal
Step 2: Connect to Oracle Database
        > sqlplus username/password@database

Step 3: Enable output display
        SQL> SET SERVEROUTPUT ON

Step 4: Run the SQL file
        SQL> @path/to/filename.sql

        OR paste the code directly and press Enter
```

### Method 3: Oracle SQL Developer (GUI Tool)

```
Step 1: Open Oracle SQL Developer
Step 2: Create a new database connection
        - Connection Name : any name
        - Username        : hr (or your username)
        - Password        : your password
        - Hostname        : localhost
        - Port            : 1521
        - SID/Service     : orcl (or your database SID)

Step 3: Open a new SQL Worksheet
Step 4: Copy and paste the .sql file content
Step 5: Click the green "Run Script" button (or press F5)
```

### Important: Enable DBMS_OUTPUT

Before running any PL/SQL program that uses `DBMS_OUTPUT.PUT_LINE`, you must enable server output:

```sql
SET SERVEROUTPUT ON;
```

In SQL Developer, you can also enable it from **View > DBMS Output** and click the green **+** icon to add your connection.

---

## Recommended Learning Order

Follow the topics in the numbered order below for the best learning experience:

| Order | Folder                      | Topic                          | Estimated Time |
|-------|-----------------------------|--------------------------------|----------------|
| 1     | 01-Introduction             | SQL vs PL/SQL, Overview        | 30 minutes     |
| 2     | 02-Environment              | Block Structure, Setup         | 45 minutes     |
| 3     | 03-Basic_Syntax             | Variables, Data Types, Output  | 1 hour         |
| 4     | 04-Control_Structures       | IF, CASE, Loops                | 1.5 hours      |
| 5     | 05-Exception_Handling       | Error Handling, Custom Errors  | 1 hour         |
| 6     | 06-Cursors                  | Implicit/Explicit Cursors      | 1.5 hours      |
| 7     | 07-Procedures_Functions     | Subprograms, Parameters       | 1.5 hours      |
| 8     | 08-Packages                 | Package Spec and Body          | 1 hour         |
| 9     | 09-Triggers                 | Database Triggers              | 1 hour         |
| 10    | 10-Practice                 | Mini, Interview, Lab Programs  | 2 hours        |

**Total Estimated Time: 11-12 hours**

---

## Practice Checklist

Use this checklist to track your progress through each topic:

### Introduction and Setup
- [ ] Understand the difference between SQL and PL/SQL
- [ ] Learn the history and purpose of PL/SQL
- [ ] Set up your development environment

### Block Structure and Syntax
- [ ] Write and execute an anonymous PL/SQL block
- [ ] Write and execute a named PL/SQL block
- [ ] Declare variables with different data types
- [ ] Use assignment operators and expressions
- [ ] Display output using DBMS_OUTPUT.PUT_LINE
- [ ] Accept user input using substitution variables

### Control Structures
- [ ] Write IF-THEN statements
- [ ] Write IF-THEN-ELSE statements
- [ ] Write IF-THEN-ELSIF-ELSE statements
- [ ] Use CASE expressions and CASE statements
- [ ] Write basic LOOP with EXIT WHEN
- [ ] Write WHILE loops
- [ ] Write FOR loops (forward and reverse)
- [ ] Solve pattern programs (odd/even numbers, multiplication table)

### Exception Handling
- [ ] Handle ZERO_DIVIDE exception
- [ ] Handle NO_DATA_FOUND exception
- [ ] Handle TOO_MANY_ROWS exception
- [ ] Handle VALUE_ERROR exception
- [ ] Create and raise user-defined exceptions
- [ ] Use RAISE_APPLICATION_ERROR for custom error messages

### Cursors
- [ ] Understand implicit cursors and their attributes
- [ ] Declare, open, fetch, and close explicit cursors
- [ ] Use cursor attributes (%FOUND, %NOTFOUND, %ROWCOUNT, %ISOPEN)
- [ ] Write cursor-based programs with HR schema

### Procedures and Functions
- [ ] Create a simple stored procedure
- [ ] Use IN parameters in procedures
- [ ] Use OUT parameters in procedures
- [ ] Use IN OUT parameters in procedures
- [ ] Create a simple function with RETURN
- [ ] Write functions that query database tables

### Packages
- [ ] Create a package specification
- [ ] Create a package body
- [ ] Execute package subprograms

### Triggers
- [ ] Create BEFORE triggers
- [ ] Create AFTER triggers
- [ ] Create INSTEAD OF triggers
- [ ] Enable, disable, and drop triggers

### Practice and Revision
- [ ] Complete all mini programs
- [ ] Complete all interview programs
- [ ] Complete all lab programs
- [ ] Review Notes.md for every topic
- [ ] Practice writing programs without looking at solutions

---

## Interview Preparation Tips

Follow these tips to prepare for PL/SQL-related technical interviews:

### 1. Master the Block Structure
Every PL/SQL program follows the DECLARE-BEGIN-EXCEPTION-END structure. Be able to explain each section and write a block from memory without any reference.

### 2. Know the Difference Between SQL and PL/SQL
This is one of the most frequently asked interview questions. Prepare a clear comparison covering at least 8-10 differences including execution model, error handling, and performance.

### 3. Understand Exception Handling Thoroughly
Be able to explain the difference between predefined exceptions (NO_DATA_FOUND, TOO_MANY_ROWS, ZERO_DIVIDE), user-defined exceptions, and RAISE_APPLICATION_ERROR. Know when to use each type.

### 4. Practice Cursor Programs
Cursors are a very common interview topic. Be comfortable writing explicit cursor programs with OPEN-FETCH-CLOSE and cursor FOR loops. Know all four cursor attributes and their return types.

### 5. Compare Procedures and Functions
Know at least 5-6 differences between procedures and functions. Understand parameter modes (IN, OUT, IN OUT) and be able to write examples of each from memory.

### 6. Explain Packages Clearly
Understand why packages are used (encapsulation, modularity, performance). Be able to explain the difference between package specification and package body, and what happens when you recompile each.

### 7. Know Trigger Types and Timing
Be able to explain BEFORE vs AFTER triggers, row-level vs statement-level triggers, and INSTEAD OF triggers. Know the :NEW and :OLD qualifiers and when each is available.

### 8. Write Clean Code Under Pressure
Practice writing programs on paper or a whiteboard without any auto-complete or syntax help. Interviewers test your ability to write syntactically correct code without IDE support.

### 9. Prepare Real-World Scenarios
Be ready to explain how PL/SQL is used in real projects: batch processing, data validation, audit logging, complex business logic, report generation, and data migration.

### 10. Know Performance Concepts
Understand BULK COLLECT, FORALL, and why cursor FOR loops are preferred over explicit OPEN-FETCH-CLOSE in modern PL/SQL. Know the concept of context switching between SQL and PL/SQL engines.

### 11. Practice Common Programs
The following programs appear frequently in interviews and lab exams:
- Factorial of a number
- Fibonacci series
- Palindrome check
- Employee salary report using cursors
- Audit trail trigger
- Package with multiple related procedures

### 12. Review Oracle Error Codes
Memorize common Oracle error codes: ORA-00001 (unique constraint), ORA-01403 (no data found), ORA-01422 (too many rows), ORA-06502 (value error), ORA-01476 (zero divide).

---

## How to Use This Repository

1. Start with folder `01-Introduction` and read the `Notes.md` file
2. Move to the next numbered folder in sequence
3. For each folder:
   - Read `Notes.md` first to understand the theory
   - Study each `.sql` file and understand the code
   - Execute each `.sql` file in your chosen environment
   - Modify the programs and experiment with different inputs
4. After completing folders 01-09, move to `10-Practice` for revision
5. Use `Resources.md` for quick reference tables and additional study material

---

## Contributing

This repository is part of the Cognizant Digital Nurture 5.0 training program. If you find any errors or have suggestions for improvement, please raise an issue or submit a pull request.

---

## License

This project is for educational purposes as part of the Cognizant Java FSE Digital Nurture 5.0 program.

---

**Happy Learning!**
