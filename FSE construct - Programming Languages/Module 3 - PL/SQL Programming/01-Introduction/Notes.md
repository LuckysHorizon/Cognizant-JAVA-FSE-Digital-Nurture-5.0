# Introduction to PL/SQL

---

## 1. Introduction

PL/SQL stands for **Procedural Language extension to SQL**. It is a powerful programming language developed by Oracle Corporation that combines the data manipulation capabilities of SQL with the processing power of procedural languages.

In simple terms, SQL alone can only ask questions to the database (like "show me all employees"). But what if you want to check a condition, repeat something, or handle errors? SQL cannot do that alone. PL/SQL adds programming features like variables, loops, conditions, and error handling on top of SQL.

PL/SQL allows you to write complete programs that interact with Oracle databases. These programs can perform complex calculations, make decisions, handle errors, and process data efficiently.

---

## 2. Definition

> **PL/SQL (Procedural Language/Structured Query Language)** is Oracle Corporation's procedural extension to SQL and the Oracle relational database. It is a block-structured language that enables developers to combine the power of SQL with procedural constructs such as variables, conditions, loops, and exception handling to build robust database applications.

### Key Facts

| Aspect | Detail |
|---|---|
| Full Form | Procedural Language extension to SQL |
| Developed By | Oracle Corporation |
| First Released | 1991 (Oracle 6) |
| Current Integration | Oracle Database, Oracle Forms, Oracle Reports |
| Language Type | Block-structured, procedural |
| Case Sensitivity | NOT case-sensitive (keywords and identifiers) |
| Execution Environment | Oracle Database Server (PL/SQL Engine) |

### History and Origin

| Year | Milestone |
|---|---|
| 1979 | Oracle V2 released with basic SQL support |
| 1991 | PL/SQL introduced with Oracle 6 |
| 1997 | Oracle 8 added object-oriented features to PL/SQL |
| 2003 | Oracle 10g introduced native compilation |
| 2013 | Oracle 12c added implicit result sets |
| 2018 | Oracle 18c added polymorphic table functions |
| Present | PL/SQL remains core to Oracle Database development |

---

## 3. Why It Is Used

### Limitations of SQL Alone

SQL is a **non-procedural** language. It tells the database **what** to do but not **how** to do it. SQL cannot:

- Declare and use variables
- Use conditional statements (IF-THEN-ELSE)
- Use loops (FOR, WHILE)
- Handle errors gracefully
- Group multiple operations as a single unit

### How PL/SQL Solves These Problems

PL/SQL overcomes every limitation of SQL by adding procedural capabilities:

| Problem with SQL | PL/SQL Solution |
|---|---|
| Cannot store intermediate values | Variables and Constants |
| No decision making | IF-THEN-ELSE, CASE statements |
| No repetition | FOR loop, WHILE loop, Basic loop |
| No error handling | EXCEPTION block |
| Sends one statement at a time to DB | Sends entire block at once (reduces network traffic) |
| Cannot create reusable code | Procedures, Functions, Packages |
| Cannot react to database events | Triggers |

### Features of PL/SQL

| Feature | Description |
|---|---|
| Block Structure | Code is organized into logical blocks (DECLARE, BEGIN, EXCEPTION, END) |
| Variables and Constants | Store and manipulate data using named containers |
| Control Structures | IF-ELSE, CASE, LOOP, WHILE, FOR for flow control |
| Exception Handling | Built-in mechanism to catch and handle runtime errors |
| Portability | PL/SQL programs run on any platform where Oracle runs |
| Integration with SQL | SQL statements can be directly embedded inside PL/SQL |
| Modularity | Code can be broken into procedures, functions, and packages |
| Cursors | Row-by-row processing of query results |
| Triggers | Automatic execution of code in response to database events |

### Advantages of PL/SQL over SQL

| Advantage | Explanation |
|---|---|
| Reduced Network Traffic | Entire block sent to server at once instead of individual statements |
| Better Performance | Multiple SQL statements processed in one block |
| Error Handling | Graceful handling of runtime errors using EXCEPTION block |
| Procedural Capabilities | Supports variables, loops, conditions |
| Code Reusability | Procedures and functions can be stored and reused |
| Security | Stored programs can enforce business rules at the database level |
| Tight Integration | SQL statements are natively embedded in PL/SQL code |
| Portability | Runs wherever Oracle Database is installed |

### Applications of PL/SQL

| Application | Example |
|---|---|
| Database Triggers | Automatically log changes when a record is updated |
| Stored Procedures | Process employee payroll calculations |
| Stored Functions | Calculate tax for a given salary |
| Packages | Group related procedures and functions together |
| Enterprise Applications | Banking systems, ERP systems, inventory management |
| Data Validation | Enforce complex business rules before inserting data |
| Batch Processing | Process thousands of records in a loop |
| Report Generation | Generate complex reports by processing query results |

---

## 4. Syntax

### PL/SQL Block Structure

PL/SQL code is organized into **blocks**. Every PL/SQL program is made of one or more blocks.

```sql
DECLARE
    -- Declaration Section (Optional)
    -- Declare variables, constants, cursors
BEGIN
    -- Executable Section (Mandatory)
    -- Write SQL and PL/SQL statements
EXCEPTION
    -- Exception Handling Section (Optional)
    -- Handle errors here
END;
/
```

### Two Types of Blocks

#### Anonymous Block (Unnamed)

```sql
DECLARE
    v_message VARCHAR2(50) := 'Hello, PL/SQL!';
BEGIN
    DBMS_OUTPUT.PUT_LINE(v_message);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred');
END;
/
```

#### Named Block (Stored Procedure)

```sql
CREATE OR REPLACE PROCEDURE greet_user(p_name IN VARCHAR2)
IS
    v_message VARCHAR2(100);
BEGIN
    v_message := 'Welcome, ' || p_name || '!';
    DBMS_OUTPUT.PUT_LINE(v_message);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in greet_user');
END greet_user;
/
```

### Minimal Block (Simplest Valid PL/SQL Block)

```sql
BEGIN
    DBMS_OUTPUT.PUT_LINE('This is the simplest PL/SQL block');
END;
/
```

> **Note**: Only the BEGIN and END sections are mandatory. DECLARE and EXCEPTION are optional.

---

## 5. Explanation

### Keywords Explained

| Keyword | Required? | Purpose | What Goes Here |
|---|---|---|---|
| DECLARE | Optional | Declaration section | Variables, constants, cursors, user-defined types |
| BEGIN | Mandatory | Executable section starts | SQL statements, PL/SQL logic, procedure calls |
| EXCEPTION | Optional | Error handling section | WHEN clauses to catch and handle specific errors |
| END; | Mandatory | Marks the end of the block | Always followed by a semicolon |
| / | Required | Executes the block | Must be on a new line by itself |

### Detailed Keyword Breakdown

**DECLARE**
- This is where you create (declare) variables, constants, and cursors.
- Variables are like containers that hold data temporarily.
- This section is optional. If you do not need variables, you can skip it.
- In named blocks (procedures/functions), the keyword IS or AS replaces DECLARE.

**BEGIN**
- This is the main body of the program where actual work happens.
- All SQL statements (SELECT, INSERT, UPDATE, DELETE) go here.
- All PL/SQL logic (IF-ELSE, loops, assignments) goes here.
- This section is mandatory. Every PL/SQL block must have BEGIN.

**EXCEPTION**
- This section catches and handles errors that occur during execution.
- Without this section, errors will crash the program.
- Common exceptions: NO_DATA_FOUND, TOO_MANY_ROWS, ZERO_DIVIDE.
- This section is optional but strongly recommended.

**END;**
- Marks the end of the PL/SQL block.
- Must always end with a semicolon.
- In named blocks, you can optionally repeat the name: END procedure_name;

**/ (Forward Slash)**
- Tells Oracle to execute the PL/SQL block.
- Must be placed on a new line by itself.
- Without this, the block is parsed but not executed.

---

## 6. Execution Flow

### PL/SQL Block Execution Flow

```
+--------------------------------------------------+
|              PL/SQL BLOCK EXECUTION               |
+--------------------------------------------------+
|                                                    |
|   DECLARE (Optional)                               |
|   +--------------------------------------------+  |
|   | Allocate memory for variables, constants,  |  |
|   | cursors, and user-defined types             |  |
|   +--------------------------------------------+  |
|                      |                             |
|                      v                             |
|   BEGIN (Mandatory)                                |
|   +--------------------------------------------+  |
|   | Execute SQL statements                      |  |
|   | Execute PL/SQL logic (IF, LOOP, etc.)       |  |
|   | Call procedures and functions                |  |
|   +--------------------------------------------+  |
|           |                    |                    |
|      (No Error)           (Error Occurs)           |
|           |                    |                    |
|           v                    v                    |
|        END;          EXCEPTION (Optional)          |
|                      +-------------------------+   |
|                      | Match error type        |   |
|                      | Execute handler code    |   |
|                      +-------------------------+   |
|                                |                   |
|                                v                   |
|                             END;                   |
|                                                    |
+--------------------------------------------------+
|                      /                             |
|            (Execute the block)                     |
+--------------------------------------------------+
```

### Step-by-Step Flow

```
Step 1:  DECLARE section executes
              |
              | Variables are created and initialized
              |
              v
Step 2:  BEGIN section executes
              |
              | SQL and PL/SQL statements run one by one
              |
              +--------+--------+
              |                 |
         No Error          Error Occurs
              |                 |
              v                 v
Step 3a: END reached     Step 3b: Control jumps
         (Normal Exit)           to EXCEPTION section
                                 |
                                 v
                          Step 4: Matching WHEN
                                  clause executes
                                  |
                                  v
                          Step 5: END reached
                                  (Handled Exit)
```

### PL/SQL Architecture

PL/SQL has its own engine that processes PL/SQL code. Understanding the architecture helps you know what happens behind the scenes.

```
+----------------------------------------------------------+
|                    APPLICATION PROGRAM                     |
|              (SQL*Plus / Application Code)                 |
+----------------------------------------------------------+
                          |
                          | Sends PL/SQL Block
                          v
+----------------------------------------------------------+
|                     ORACLE SERVER                          |
|  +----------------------------------------------------+  |
|  |                  PL/SQL ENGINE                      |  |
|  |                                                      |  |
|  |   +------------------+    +---------------------+   |  |
|  |   | PL/SQL BLOCK     |    |                     |   |  |
|  |   |                  |    |  PROCEDURAL         |   |  |
|  |   | DECLARE          |--->|  STATEMENT          |   |  |
|  |   | BEGIN            |    |  EXECUTOR           |   |  |
|  |   |   PL/SQL stmts --+--->|  (Handles IF, LOOP, |   |  |
|  |   |   SQL stmts -----+--+|   variables, etc.)  |   |  |
|  |   | EXCEPTION        |  ||                     |   |  |
|  |   | END              |  |+---------------------+   |  |
|  |   +------------------+  |                           |  |
|  |                         |                           |  |
|  +-------------------------+---------------------------+  |
|                            |                              |
|                            | SQL Statements Only          |
|                            v                              |
|  +----------------------------------------------------+  |
|  |            SQL STATEMENT EXECUTOR                   |  |
|  |                                                      |  |
|  |   Processes: SELECT, INSERT, UPDATE, DELETE          |  |
|  |   Interacts with the actual database                 |  |
|  +----------------------------------------------------+  |
|                            |                              |
|                            v                              |
|  +----------------------------------------------------+  |
|  |              ORACLE DATABASE                         |  |
|  |          (Tables, Indexes, Data)                     |  |
|  +----------------------------------------------------+  |
+----------------------------------------------------------+
```

### How the PL/SQL Engine Works

| Step | Action | Component |
|---|---|---|
| 1 | Application sends PL/SQL block to Oracle Server | Application Program |
| 2 | PL/SQL Engine receives the block | PL/SQL Engine |
| 3 | Procedural statements (IF, LOOP, variables) are processed | Procedural Statement Executor |
| 4 | SQL statements are extracted and sent separately | PL/SQL Engine |
| 5 | SQL statements are executed against the database | SQL Statement Executor |
| 6 | Results are returned to the PL/SQL Engine | SQL Statement Executor |
| 7 | PL/SQL Engine combines results and continues processing | PL/SQL Engine |
| 8 | Final output is sent back to the application | PL/SQL Engine |

---

## 7. Real Life Example

### Example 1: ATM Transaction

Think of an ATM withdrawal as a PL/SQL block:

```
DECLARE  -->  You insert your card (identify yourself)
              ATM reads your account number and balance

BEGIN    -->  You enter the amount to withdraw
              ATM checks: Do you have enough balance?
              IF yes: Dispense cash, deduct from balance
              IF no:  Show "Insufficient funds" message

EXCEPTION --> Card gets stuck? Machine error? Network failure?
              ATM shows an error message and returns your card

END      -->  Transaction is complete, receipt is printed
```

**Mapping to PL/SQL:**

| ATM Step | PL/SQL Equivalent |
|---|---|
| Insert card, read account | DECLARE variables (account_no, balance) |
| Check balance, dispense cash | BEGIN block with IF-ELSE logic |
| Machine error, network failure | EXCEPTION handling |
| Print receipt, eject card | END of block |

### Example 2: Online Shopping Order

```
DECLARE  -->  Customer selects items (item_id, quantity, price)

BEGIN    -->  Calculate total price
              Check if items are in stock
              IF in stock: Place order, reduce inventory
              IF not: Show "Out of Stock" message
              Process payment

EXCEPTION --> Payment failed? Server error? Invalid address?
              Cancel order, refund payment, show error message

END      -->  Order confirmed, send confirmation email
```

### Example 3: Student Exam Result Processing

```
DECLARE  -->  Student roll number, marks in each subject

BEGIN    -->  Calculate total marks
              Calculate percentage
              IF percentage >= 40: Result = PASS
              IF percentage < 40:  Result = FAIL
              Assign grade (A, B, C, D, F)
              Store result in database

EXCEPTION --> Invalid marks entered? Database connection lost?
              Show appropriate error message

END      -->  Result is published
```

---

## 8. Code Explanation

### Program 1: Anonymous Block

**File: Anonymous_Block.sql**

```sql
DECLARE
    v_employee_name VARCHAR2(50);
    v_salary        NUMBER(10,2);
    v_annual_salary  NUMBER(12,2);
BEGIN
    SELECT FIRST_NAME || ' ' || LAST_NAME, SALARY
    INTO v_employee_name, v_salary
    FROM HR.EMPLOYEES
    WHERE EMPLOYEE_ID = 100;

    v_annual_salary := v_salary * 12;

    DBMS_OUTPUT.PUT_LINE('Employee : ' || v_employee_name);
    DBMS_OUTPUT.PUT_LINE('Monthly  : ' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Annual   : ' || v_annual_salary);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee not found');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
```

**Line-by-Line Explanation:**

| Line | Code | Explanation |
|---|---|---|
| 1 | DECLARE | Start of declaration section |
| 2 | v_employee_name VARCHAR2(50) | Variable to store employee full name (up to 50 characters) |
| 3 | v_salary NUMBER(10,2) | Variable to store monthly salary (10 digits, 2 decimal) |
| 4 | v_annual_salary NUMBER(12,2) | Variable to store calculated annual salary |
| 5 | BEGIN | Start of executable section |
| 6-9 | SELECT ... INTO ... | Fetch employee name and salary from table into variables |
| 10 | WHERE EMPLOYEE_ID = 100 | Filter for employee with ID 100 |
| 11 | v_annual_salary := v_salary * 12 | Calculate annual salary (multiply monthly by 12) |
| 12-14 | DBMS_OUTPUT.PUT_LINE(...) | Print results to console |
| 15 | EXCEPTION | Start of error handling section |
| 16-17 | WHEN NO_DATA_FOUND | If no employee found with that ID, show message |
| 18-19 | WHEN OTHERS | Catch any other unexpected error |
| 20 | END; | End of PL/SQL block |
| 21 | / | Execute the block |

### Program 2: Named Block (Stored Procedure)

**File: Named_Block.sql**

```sql
CREATE OR REPLACE PROCEDURE get_employee_details(
    p_emp_id IN NUMBER
)
IS
    v_name   VARCHAR2(50);
    v_salary NUMBER(10,2);
    v_dept   VARCHAR2(30);
BEGIN
    SELECT e.FIRST_NAME || ' ' || e.LAST_NAME,
           e.SALARY,
           d.DEPARTMENT_NAME
    INTO v_name, v_salary, v_dept
    FROM HR.EMPLOYEES e
    JOIN HR.DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
    WHERE e.EMPLOYEE_ID = p_emp_id;

    DBMS_OUTPUT.PUT_LINE('Name       : ' || v_name);
    DBMS_OUTPUT.PUT_LINE('Salary     : ' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Department : ' || v_dept);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No employee found with ID: ' || p_emp_id);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END get_employee_details;
/
```

**Line-by-Line Explanation:**

| Line | Code | Explanation |
|---|---|---|
| 1 | CREATE OR REPLACE PROCEDURE | Creates a new procedure or replaces existing one |
| 2 | p_emp_id IN NUMBER | Input parameter to accept employee ID |
| 4 | IS | Replaces DECLARE keyword in named blocks |
| 5-7 | Variable declarations | Variables to store fetched data |
| 8 | BEGIN | Start of executable section |
| 9-15 | SELECT ... INTO ... | Fetch employee details using JOIN |
| 17-19 | DBMS_OUTPUT.PUT_LINE(...) | Display the results |
| 20 | EXCEPTION | Error handling section |
| 21-22 | WHEN NO_DATA_FOUND | Handle case when employee ID does not exist |
| 23-24 | WHEN OTHERS | Handle any other unexpected errors |
| 25 | END get_employee_details; | End block with procedure name (optional but recommended) |
| 26 | / | Execute to create the procedure |

### Anonymous Block vs Named Block Comparison

| Aspect | Anonymous Block | Named Block |
|---|---|---|
| Has a Name | No | Yes (procedure/function name) |
| Stored in Database | No (runs once and is gone) | Yes (permanently stored) |
| Reusable | No (must retype to run again) | Yes (call by name anytime) |
| Declaration Keyword | DECLARE | IS or AS |
| Parameters | Cannot accept parameters | Can accept input/output parameters |
| Compilation | Compiled every time it runs | Compiled once, stored in compiled form |
| Use Case | Quick testing, one-time scripts | Production code, reusable logic |

---

## 9. Interview Questions

### Q1: What is PL/SQL?
**Answer:** PL/SQL stands for Procedural Language extension to SQL. It is Oracle Corporation's procedural language that extends SQL with programming features like variables, conditions, loops, and exception handling. It allows writing complete programs that interact with Oracle databases.

### Q2: What are the main sections of a PL/SQL block?
**Answer:** A PL/SQL block has four sections:
1. **DECLARE** (Optional) - For declaring variables, constants, and cursors
2. **BEGIN** (Mandatory) - For executable statements
3. **EXCEPTION** (Optional) - For error handling
4. **END;** (Mandatory) - Marks the end of the block

### Q3: What is the difference between an anonymous block and a named block?
**Answer:** An anonymous block has no name, is not stored in the database, cannot be reused, and is compiled every time it runs. A named block (procedure/function) has a name, is stored in the database permanently, can be called multiple times, and is compiled only once.

### Q4: Explain the PL/SQL architecture.
**Answer:** The PL/SQL architecture consists of two main components:
1. **PL/SQL Engine** - Contains the Procedural Statement Executor that processes PL/SQL-specific statements (IF, LOOP, variables)
2. **SQL Statement Executor** - Processes SQL statements (SELECT, INSERT, UPDATE, DELETE) by interacting with the database

When a PL/SQL block is submitted, the PL/SQL Engine separates procedural and SQL statements. Procedural statements are handled internally, while SQL statements are sent to the SQL Statement Executor.

### Q5: What is DBMS_OUTPUT.PUT_LINE?
**Answer:** DBMS_OUTPUT.PUT_LINE is a built-in Oracle procedure used to display output on the screen. DBMS_OUTPUT is a package, and PUT_LINE is a procedure within that package. Before using it, you must enable output using SET SERVEROUTPUT ON in SQL*Plus.

### Q6: What is the purpose of the / (forward slash) at the end of a PL/SQL block?
**Answer:** The forward slash (/) tells Oracle to execute the PL/SQL block that has been entered. It must be placed on a new line by itself. Without it, the block is only parsed and buffered but not executed.

### Q7: Can we write a PL/SQL block without the DECLARE section?
**Answer:** Yes. The DECLARE section is optional. The simplest valid PL/SQL block requires only BEGIN and END:
```sql
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello');
END;
/
```

### Q8: What are the advantages of PL/SQL over SQL?
**Answer:**
- Supports procedural constructs (variables, loops, conditions)
- Reduces network traffic by sending entire blocks instead of individual statements
- Provides exception handling for error management
- Allows code reusability through procedures and functions
- Better performance for complex operations
- Supports modular programming through packages

### Q9: What is the difference between IS and DECLARE?
**Answer:** Both serve the same purpose of starting the declaration section. DECLARE is used in anonymous blocks, while IS (or AS) is used in named blocks (procedures, functions, packages). They are functionally identical but used in different contexts.

### Q10: What is a stored procedure in PL/SQL?
**Answer:** A stored procedure is a named PL/SQL block that is compiled once and stored permanently in the Oracle database. It can accept input parameters, perform operations, and return output parameters. It can be called by name from any application, other procedures, or SQL*Plus.

### Q11: What happens if an error occurs and there is no EXCEPTION section?
**Answer:** If an error occurs and no EXCEPTION section is present, the error propagates to the calling environment (such as SQL*Plus), the program terminates abnormally, and an Oracle error message is displayed. Any uncommitted changes made in the BEGIN section are not automatically rolled back unless explicitly done.

### Q12: Name some built-in exceptions in PL/SQL.
**Answer:**
- NO_DATA_FOUND - SELECT INTO returns no rows
- TOO_MANY_ROWS - SELECT INTO returns more than one row
- ZERO_DIVIDE - Division by zero attempted
- VALUE_ERROR - Arithmetic, conversion, or size constraint error
- INVALID_CURSOR - Illegal cursor operation
- DUP_VAL_ON_INDEX - Duplicate value on unique index

---

## 10. Viva Questions

### V1: Who developed PL/SQL?
**Answer:** PL/SQL was developed by Oracle Corporation. It was first introduced in 1991 with Oracle Database version 6.

### V2: What does PL/SQL stand for?
**Answer:** PL/SQL stands for Procedural Language extension to Structured Query Language.

### V3: Is PL/SQL case-sensitive?
**Answer:** No. PL/SQL is not case-sensitive for keywords and identifiers. However, string literals enclosed in single quotes are case-sensitive. For example, 'Hello' and 'hello' are different values.

### V4: What is the minimum required structure of a PL/SQL block?
**Answer:** The minimum valid PL/SQL block requires only BEGIN and END:
```sql
BEGIN
    NULL;
END;
/
```

### V5: What is the difference between a procedure and a function?
**Answer:** A procedure performs an action and does not return a value (it may use OUT parameters). A function must return exactly one value using the RETURN statement. Functions can be used in SQL statements, but procedures cannot.

### V6: Can SQL statements be written inside PL/SQL?
**Answer:** Yes. SQL statements like SELECT, INSERT, UPDATE, and DELETE can be directly embedded inside PL/SQL blocks. This is one of the key features of PL/SQL - tight integration with SQL.

### V7: What is the role of the PL/SQL Engine?
**Answer:** The PL/SQL Engine processes PL/SQL blocks by separating procedural statements from SQL statements. It handles procedural logic internally using the Procedural Statement Executor and sends SQL statements to the SQL Statement Executor for database interaction.

### V8: What is an anonymous block?
**Answer:** An anonymous block is a PL/SQL block that has no name, is not stored in the database, and cannot be called by other programs. It is compiled and executed immediately. It is primarily used for testing, one-time scripts, and ad-hoc operations.

### V9: What is the use of SET SERVEROUTPUT ON?
**Answer:** SET SERVEROUTPUT ON is a SQL*Plus command that enables the display of output from DBMS_OUTPUT.PUT_LINE. Without this command, the output generated by PL/SQL programs will not be visible on the screen.

### V10: What are the data types available in PL/SQL?
**Answer:** Common PL/SQL data types include:
- VARCHAR2 (variable-length string)
- NUMBER (numeric values)
- DATE (date and time)
- BOOLEAN (TRUE, FALSE, NULL)
- CHAR (fixed-length string)
- CLOB (large character data)
- BLOB (large binary data)

### V11: Can PL/SQL run on non-Oracle databases?
**Answer:** No. PL/SQL is proprietary to Oracle and can only run on Oracle Database environments. Other databases have their own procedural languages (e.g., T-SQL for SQL Server, PL/pgSQL for PostgreSQL).

### V12: What is the := symbol in PL/SQL?
**Answer:** The := symbol is the assignment operator in PL/SQL. It assigns a value to a variable. For example, v_count := 10; assigns the value 10 to the variable v_count. This is different from = which is used for comparison.

---

## 11. Common Mistakes

### Mistake 1: Missing Semicolons

```sql
-- WRONG
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello')
END;
/

-- CORRECT
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello');
END;
/
```
Every statement inside a PL/SQL block must end with a semicolon (;).

### Mistake 2: Forgetting the Forward Slash

```sql
-- WRONG (Block is not executed)
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello');
END;

-- CORRECT
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello');
END;
/
```
The / on a new line is required to execute the block.

### Mistake 3: Using = Instead of := for Assignment

```sql
-- WRONG
v_count = 10;

-- CORRECT
v_count := 10;
```
PL/SQL uses := for assignment. The = operator is used only for comparison.

### Mistake 4: Forgetting SET SERVEROUTPUT ON

If you do not execute SET SERVEROUTPUT ON before running your PL/SQL block, you will not see any output from DBMS_OUTPUT.PUT_LINE even though the block executes successfully.

### Mistake 5: SELECT Without INTO

```sql
-- WRONG (inside PL/SQL)
BEGIN
    SELECT FIRST_NAME FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = 100;
END;
/

-- CORRECT
DECLARE
    v_name VARCHAR2(50);
BEGIN
    SELECT FIRST_NAME INTO v_name FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(v_name);
END;
/
```
Inside PL/SQL, every SELECT must have an INTO clause to store results in variables.

### Mistake 6: Not Handling Exceptions

```sql
-- RISKY (No exception handling)
DECLARE
    v_name VARCHAR2(50);
BEGIN
    SELECT FIRST_NAME INTO v_name FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = 9999;
    DBMS_OUTPUT.PUT_LINE(v_name);
END;
/

-- SAFE (With exception handling)
DECLARE
    v_name VARCHAR2(50);
BEGIN
    SELECT FIRST_NAME INTO v_name FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = 9999;
    DBMS_OUTPUT.PUT_LINE(v_name);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee not found');
END;
/
```

### Mistake 7: Using DECLARE in Named Blocks

```sql
-- WRONG
CREATE OR REPLACE PROCEDURE my_proc
DECLARE
    v_count NUMBER;
BEGIN
    v_count := 10;
END;
/

-- CORRECT
CREATE OR REPLACE PROCEDURE my_proc
IS
    v_count NUMBER;
BEGIN
    v_count := 10;
END;
/
```
Named blocks use IS or AS instead of DECLARE.

### Mistake 8: Placing / on the Same Line as END

```sql
-- WRONG
END; /

-- CORRECT
END;
/
```
The forward slash must be on its own line with no other characters.

### Common Mistakes Summary Table

| Mistake | Problem | Fix |
|---|---|---|
| Missing semicolon | Compilation error | Add ; after every statement |
| No forward slash | Block not executed | Add / on a new line |
| Using = for assignment | Compilation error | Use := for assignment |
| No SERVEROUTPUT ON | No visible output | Run SET SERVEROUTPUT ON first |
| SELECT without INTO | Compilation error | Always use SELECT ... INTO |
| No EXCEPTION block | Program crashes on error | Add EXCEPTION section |
| DECLARE in named blocks | Compilation error | Use IS or AS instead |
| / not on its own line | Execution error | Place / alone on a new line |

---

## 12. Quick Revision

### PL/SQL At a Glance

| Item | Detail |
|---|---|
| Full Form | Procedural Language extension to SQL |
| Developer | Oracle Corporation |
| Type | Block-structured procedural language |
| Runs On | Oracle Database Server |
| Case Sensitive | No (except string literals) |
| Minimum Block | BEGIN ... END; / |

### Block Structure Quick Reference

| Section | Keyword | Required? | Purpose |
|---|---|---|---|
| Declaration | DECLARE | Optional | Variables, constants, cursors |
| Execution | BEGIN | Mandatory | SQL + PL/SQL statements |
| Error Handling | EXCEPTION | Optional | Catch and handle errors |
| Termination | END; | Mandatory | Close the block |
| Execution | / | Required | Run the block |

### Operators Quick Reference

| Operator | Meaning | Example |
|---|---|---|
| := | Assignment | v_x := 10 |
| = | Comparison (equals) | IF v_x = 10 |
| \|\| | String concatenation | 'Hello' \|\| 'World' |
| -- | Single-line comment | -- This is a comment |
| /* */ | Multi-line comment | /* comment */ |

### Key Components

| Component | Purpose |
|---|---|
| PL/SQL Engine | Processes PL/SQL blocks |
| Procedural Statement Executor | Handles IF, LOOP, variables |
| SQL Statement Executor | Handles SELECT, INSERT, UPDATE, DELETE |
| DBMS_OUTPUT.PUT_LINE | Displays output to screen |

### Block Types Summary

| Type | Stored? | Reusable? | Declaration Keyword | Example |
|---|---|---|---|---|
| Anonymous Block | No | No | DECLARE | Ad-hoc scripts, testing |
| Procedure | Yes | Yes | IS / AS | Business logic, data processing |
| Function | Yes | Yes | IS / AS | Calculations, return values |
| Package | Yes | Yes | IS / AS | Group related subprograms |
| Trigger | Yes | Yes | DECLARE | Auto-execute on events |

### Common Exceptions

| Exception | When It Occurs |
|---|---|
| NO_DATA_FOUND | SELECT INTO returns zero rows |
| TOO_MANY_ROWS | SELECT INTO returns more than one row |
| ZERO_DIVIDE | Division by zero |
| VALUE_ERROR | Type conversion or size error |
| INVALID_CURSOR | Illegal cursor operation |
| CURSOR_ALREADY_OPEN | Opening an already open cursor |
| DUP_VAL_ON_INDEX | Unique constraint violation |

### Quick Syntax Cheat Sheet

```
Anonymous Block          Named Block (Procedure)
------------------       ---------------------------
DECLARE                  CREATE OR REPLACE PROCEDURE
    variables;               name(params)
BEGIN                    IS
    statements;              variables;
EXCEPTION                BEGIN
    handlers;                statements;
END;                     EXCEPTION
/                            handlers;
                         END name;
                         /
```

---

*End of Notes - Introduction to PL/SQL*
