# PL/SQL Cursors

---

## Introduction

When a SQL statement is executed inside a PL/SQL block, Oracle allocates a private memory area called the **Context Area**. A **Cursor** is a pointer (or handle) to this context area. It allows you to process query results one row at a time, giving you full control over how data is retrieved and used.

Cursors are essential for any PL/SQL program that needs to work with query results, especially when the query returns more than one row.

---

## Definition

A **Cursor** is a pointer to a context area that Oracle creates in memory to process a SQL statement. The context area holds the parsed statement, the rows returned by the query, and the current position within the result set.

| Term           | Meaning                                                    |
|----------------|------------------------------------------------------------|
| Context Area   | Private memory region allocated by Oracle for SQL execution |
| Cursor         | A named pointer to the context area                        |
| Active Set     | The set of rows returned by the query associated with a cursor |
| Current Row    | The row currently being pointed to by the cursor           |

---

## Why It Is Used

| Reason                          | Explanation                                                |
|---------------------------------|------------------------------------------------------------|
| Row-by-row processing           | Process each row individually from a multi-row result set  |
| Controlled data retrieval       | Decide when to fetch, how many to fetch, and when to stop  |
| DML feedback                    | Know how many rows were affected by INSERT, UPDATE, DELETE |
| Complex business logic          | Apply conditional logic on each row during processing      |
| Memory efficiency               | Process large result sets without loading all rows at once |
| Error handling per row          | Handle errors at the row level rather than statement level |

---

## Types of Cursors

```
                    PL/SQL Cursors
                    /            \
                   /              \
          Implicit Cursor     Explicit Cursor
          (Automatic)         (User-Defined)
              |                     |
     Created by Oracle      Created by programmer
     for DML and            for multi-row SELECT
     SELECT INTO            queries
```

### 1. Implicit Cursor

Oracle **automatically** creates an implicit cursor every time a DML statement (INSERT, UPDATE, DELETE) or a SELECT INTO statement is executed. The programmer does not declare, open, fetch, or close it manually.

### 2. Explicit Cursor

The programmer **manually** declares, opens, fetches from, and closes an explicit cursor. It is used when a SELECT query returns multiple rows that need to be processed one at a time.

---

## Comparison: Implicit vs Explicit Cursors

| Feature              | Implicit Cursor                         | Explicit Cursor                          |
|----------------------|-----------------------------------------|------------------------------------------|
| Created by           | Oracle automatically                    | Programmer manually                     |
| Used for             | DML (INSERT, UPDATE, DELETE, SELECT INTO) | Multi-row SELECT queries               |
| Declaration needed   | No                                      | Yes, in DECLARE section                  |
| OPEN/FETCH/CLOSE     | Not required                            | Required (or use cursor FOR loop)        |
| Cursor name          | SQL (predefined)                        | User-defined name                        |
| Number of rows       | Single row (SELECT INTO) or DML result  | Multiple rows                            |
| Control              | Minimal                                 | Full control over fetching               |
| Attributes prefix    | SQL%                                    | cursor_name%                             |
| Performance          | Good for simple operations              | Good for complex row-by-row processing   |
| Risk of errors       | TOO_MANY_ROWS, NO_DATA_FOUND possible   | Handled through FETCH loop               |

---

## Syntax

### Implicit Cursor Syntax

```sql
BEGIN
    UPDATE table_name SET column = value WHERE condition;

    IF SQL%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Rows affected: ' || SQL%ROWCOUNT);
    END IF;
END;
/
```

### Explicit Cursor Syntax (Full Lifecycle)

```sql
DECLARE
    CURSOR cursor_name IS
        SELECT column1, column2 FROM table_name WHERE condition;

    v_col1 table_name.column1%TYPE;
    v_col2 table_name.column2%TYPE;
BEGIN
    OPEN cursor_name;

    LOOP
        FETCH cursor_name INTO v_col1, v_col2;
        EXIT WHEN cursor_name%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_col1 || ' - ' || v_col2);
    END LOOP;

    CLOSE cursor_name;
END;
/
```

### Cursor FOR Loop Syntax

```sql
DECLARE
    CURSOR cursor_name IS
        SELECT column1, column2 FROM table_name;
BEGIN
    FOR rec IN cursor_name LOOP
        DBMS_OUTPUT.PUT_LINE(rec.column1 || ' - ' || rec.column2);
    END LOOP;
END;
/
```

### Parameterized Cursor Syntax

```sql
DECLARE
    CURSOR cursor_name (p_param datatype) IS
        SELECT column1, column2 FROM table_name WHERE column = p_param;
BEGIN
    FOR rec IN cursor_name('value') LOOP
        DBMS_OUTPUT.PUT_LINE(rec.column1 || ' - ' || rec.column2);
    END LOOP;
END;
/
```

---

## Explanation

### Implicit Cursor

When Oracle executes any DML statement or a SELECT INTO inside a PL/SQL block, it automatically creates a cursor called `SQL`. You can use this cursor's attributes to check the result of the operation without declaring anything.

**Implicit Cursor Attributes:**

| Attribute      | Data Type | Description                                                 |
|----------------|-----------|-------------------------------------------------------------|
| SQL%FOUND      | BOOLEAN   | Returns TRUE if the most recent DML affected one or more rows |
| SQL%NOTFOUND   | BOOLEAN   | Returns TRUE if the most recent DML affected zero rows       |
| SQL%ROWCOUNT   | NUMBER    | Returns the number of rows affected by the most recent DML   |
| SQL%ISOPEN     | BOOLEAN   | Always returns FALSE (Oracle closes implicit cursors automatically) |

### Explicit Cursor

When you need to process multiple rows returned by a SELECT query, you declare an explicit cursor. You have full control over when to open it, how to fetch rows, and when to close it.

**Explicit Cursor Attributes:**

| Attribute             | Data Type | Description                                                  |
|-----------------------|-----------|--------------------------------------------------------------|
| cursor_name%FOUND     | BOOLEAN   | TRUE if the last FETCH returned a row                        |
| cursor_name%NOTFOUND  | BOOLEAN   | TRUE if the last FETCH did not return a row                  |
| cursor_name%ROWCOUNT  | NUMBER    | Number of rows fetched so far                                |
| cursor_name%ISOPEN    | BOOLEAN   | TRUE if the cursor is currently open                         |

### Cursor FOR Loop

The cursor FOR loop is a simplified way to process an explicit cursor. Oracle automatically handles OPEN, FETCH, and CLOSE. The loop variable is implicitly declared as a record matching the cursor's SELECT list.

### Parameterized Cursors

A parameterized cursor accepts arguments when opened, allowing the same cursor to be reused with different filter values. Parameters are declared in parentheses after the cursor name in the DECLARE section.

### REF CURSOR

A REF CURSOR is a cursor variable that can point to different queries at runtime. Unlike static cursors that are bound to a single SELECT at compile time, REF cursors are dynamic. There are two types:

| Type                  | Description                                      |
|-----------------------|--------------------------------------------------|
| Strong REF CURSOR     | Has a defined return type                        |
| Weak REF CURSOR       | No return type, can point to any query (SYS_REFCURSOR) |

```sql
DECLARE
    TYPE ref_cursor_type IS REF CURSOR;
    v_cursor ref_cursor_type;
BEGIN
    OPEN v_cursor FOR SELECT first_name FROM HR.EMPLOYEES;
    CLOSE v_cursor;
END;
/
```

---

## Execution Flow

### Implicit Cursor Flow

```
+---------------------------------------------+
|          SQL Statement Executed              |
|   (INSERT / UPDATE / DELETE / SELECT INTO)   |
+---------------------------------------------+
                    |
                    v
+---------------------------------------------+
|   Oracle Automatically Creates Cursor (SQL)  |
|   - Parses the statement                    |
|   - Executes the statement                  |
|   - Sets cursor attributes                  |
+---------------------------------------------+
                    |
                    v
+---------------------------------------------+
|   Check Attributes                           |
|   SQL%FOUND  --> Did it affect rows?         |
|   SQL%ROWCOUNT --> How many rows?            |
+---------------------------------------------+
                    |
                    v
+---------------------------------------------+
|   Oracle Automatically Closes Cursor         |
+---------------------------------------------+
```

### Explicit Cursor Flow (OPEN -> FETCH -> CLOSE)

```
+-----------+
|  DECLARE  |  Define the cursor with a SELECT query
+-----------+
      |
      v
+-----------+
|   OPEN    |  Execute the query, identify the active set
+-----------+  Cursor now points BEFORE the first row
      |
      v
+-----------+     +---> Row found?
|   FETCH   |-----|     YES --> Process the row
+-----------+     |              |
      ^           |              v
      |           |     Loop back to FETCH
      +-----------+
                  |
                  +---> NO (cursor%NOTFOUND = TRUE)
                        |
                        v
                  +-----------+
                  |   CLOSE   |  Release memory, close cursor
                  +-----------+
```

### Step-by-Step Walkthrough

```
Step 1: DECLARE    cursor c IS SELECT name, sal FROM emp;
                   (Query is defined but NOT executed yet)

Step 2: OPEN c;    Query executes. Result set is created.
                   Cursor points BEFORE Row 1.

                   +-------+--------+
                   | name  |  sal   |
                   +-------+--------+
        cursor --> | Alice | 5000   |   <-- Row 1
                   | Bob   | 6000   |   <-- Row 2
                   | Carol | 7000   |   <-- Row 3
                   +-------+--------+

Step 3: FETCH c INTO v_name, v_sal;
                   v_name = 'Alice', v_sal = 5000
                   cursor%FOUND = TRUE, cursor%ROWCOUNT = 1

Step 4: FETCH c INTO v_name, v_sal;
                   v_name = 'Bob', v_sal = 6000
                   cursor%FOUND = TRUE, cursor%ROWCOUNT = 2

Step 5: FETCH c INTO v_name, v_sal;
                   v_name = 'Carol', v_sal = 7000
                   cursor%FOUND = TRUE, cursor%ROWCOUNT = 3

Step 6: FETCH c INTO v_name, v_sal;
                   No more rows.
                   cursor%NOTFOUND = TRUE --> EXIT LOOP

Step 7: CLOSE c;   Memory released. cursor%ISOPEN = FALSE
```

---

## Real Life Example

**Reading a Book Page by Page**

Think of a cursor like reading a book:

| Book Analogy             | Cursor Equivalent                      |
|--------------------------|----------------------------------------|
| The entire book          | The result set (active set)            |
| Opening the book         | OPEN cursor (execute query)            |
| Reading one page         | FETCH (retrieve one row)               |
| Bookmark on current page | Cursor pointer (current row position)  |
| Turning to next page     | Next FETCH                             |
| Reaching the last page   | %NOTFOUND becomes TRUE                 |
| Closing the book         | CLOSE cursor (release memory)          |
| Number of pages read     | %ROWCOUNT                              |

**Scenario:** A library system needs to print overdue notices for all members who have not returned books. The program declares a cursor to select all overdue records, opens it, fetches each record one by one, prints a notice for each member, and closes the cursor when all records are processed.

---

## Code Explanation

### Program 1: ImplicitCursor.sql

This program demonstrates how Oracle's implicit cursor works with DML statements.

| Step | Action                                                         |
|------|----------------------------------------------------------------|
| 1    | Creates a local table EMPLOYEE_TEST with emp_id, emp_name, salary |
| 2    | Inserts three sample rows into the table                       |
| 3    | Performs an UPDATE to increase salary for emp_id = 1           |
| 4    | Uses SQL%ROWCOUNT to display how many rows were affected       |
| 5    | Uses SQL%FOUND to confirm the update was successful            |
| 6    | Performs a DELETE on a non-existent employee (emp_id = 99)      |
| 7    | Uses SQL%NOTFOUND to show that no rows were deleted            |
| 8    | Checks SQL%ISOPEN which always returns FALSE for implicit cursors |
| 9    | Drops the table to clean up                                    |

Key takeaway: You never declare, open, or close an implicit cursor. Oracle does it all automatically. You only read the attributes using the `SQL%` prefix.

### Program 2: ExplicitCursor.sql

This program demonstrates an explicit cursor with the cursor FOR loop.

| Step | Action                                                         |
|------|----------------------------------------------------------------|
| 1    | Declares a cursor to select first_name and salary from HR.EMPLOYEES where department_id = 10 |
| 2    | Uses a cursor FOR loop which automatically opens, fetches, and closes |
| 3    | Inside the loop, prints each employee's name and salary        |

Key takeaway: The cursor FOR loop is the simplest way to use an explicit cursor. No need to manually OPEN, FETCH, or CLOSE.

### Program 3: CursorOpenFetchClose.sql

This program demonstrates the complete explicit cursor lifecycle.

| Step | Action                                                         |
|------|----------------------------------------------------------------|
| 1    | Declares a cursor to select employee_id, first_name, and salary from HR.EMPLOYEES where department_id = 50 |
| 2    | Declares variables to hold fetched values                      |
| 3    | Opens the cursor (query executes, active set is created)       |
| 4    | Enters a LOOP that fetches one row at a time                   |
| 5    | Checks cursor%NOTFOUND after each fetch to exit when done      |
| 6    | Prints each row's data                                         |
| 7    | Closes the cursor after the loop ends                          |

Key takeaway: The manual OPEN-FETCH-CLOSE cycle gives you the most control. Always close your cursors to free memory.

### Program 4: CursorAttributes.sql

This program demonstrates all four explicit cursor attributes.

| Step | Action                                                         |
|------|----------------------------------------------------------------|
| 1    | Declares a cursor for HR.EMPLOYEES (department_id = 10)        |
| 2    | Checks %ISOPEN before opening (returns FALSE)                  |
| 3    | Opens the cursor, checks %ISOPEN again (returns TRUE)          |
| 4    | Fetches rows in a loop, displaying %FOUND, %NOTFOUND, and %ROWCOUNT after each fetch |
| 5    | After the loop ends (when %NOTFOUND is TRUE), closes the cursor |
| 6    | Checks %ISOPEN after closing (returns FALSE)                   |

Key takeaway: Cursor attributes let you monitor the state of your cursor at every stage of its lifecycle.

### Program 5: EmployeeCursor.sql

This is a practical program that processes all employees.

| Step | Action                                                         |
|------|----------------------------------------------------------------|
| 1    | Declares a cursor to select employee_id, first_name, last_name, and salary from HR.EMPLOYEES |
| 2    | Declares a counter variable to track total employees           |
| 3    | Uses a cursor FOR loop to iterate through all rows             |
| 4    | Prints each employee's details in a formatted line             |
| 5    | Increments the counter for each row processed                  |
| 6    | After the loop, prints the total number of employees processed |

Key takeaway: Cursor FOR loops handle all cursor management automatically while still letting you process each row individually.

---

## Interview Questions

**Q1. What is a cursor in PL/SQL?**
A cursor is a pointer to the context area (private memory region) that Oracle allocates to process a SQL statement. It allows row-by-row processing of query results.

**Q2. What is the difference between an implicit and explicit cursor?**
An implicit cursor is created automatically by Oracle for DML statements and SELECT INTO. An explicit cursor is declared and managed by the programmer for multi-row SELECT queries.

**Q3. What are the attributes of an implicit cursor?**
SQL%FOUND (TRUE if rows affected), SQL%NOTFOUND (TRUE if no rows affected), SQL%ROWCOUNT (number of rows affected), SQL%ISOPEN (always FALSE for implicit cursors).

**Q4. Why does SQL%ISOPEN always return FALSE?**
Because Oracle automatically closes implicit cursors immediately after the SQL statement executes. By the time you check the attribute, the cursor is already closed.

**Q5. What is the lifecycle of an explicit cursor?**
DECLARE (define the cursor), OPEN (execute the query), FETCH (retrieve rows one by one), CLOSE (release memory and cursor resources).

**Q6. What happens if you FETCH from a cursor after the last row?**
The cursor%NOTFOUND attribute becomes TRUE, and the variables retain their previous values. No error is raised.

**Q7. What is a cursor FOR loop and why is it preferred?**
A cursor FOR loop automatically handles OPEN, FETCH, and CLOSE operations. It is preferred because it reduces code, eliminates the risk of forgetting to close the cursor, and the loop variable is implicitly declared.

**Q8. What is a parameterized cursor?**
A parameterized cursor accepts parameters when opened, allowing the same cursor definition to be reused with different values. Parameters are defined in parentheses after the cursor name.

**Q9. What is a REF CURSOR?**
A REF CURSOR is a cursor variable that can point to different queries at runtime. It supports dynamic SQL and can be passed as a parameter to procedures and functions.

**Q10. What happens if you forget to close an explicit cursor?**
The cursor remains open and continues to consume memory. If too many cursors are left open, Oracle raises the ORA-01000: maximum open cursors exceeded error.

**Q11. Can you use a cursor FOR loop with a parameterized cursor?**
Yes. You pass the parameters when referencing the cursor in the FOR loop: FOR rec IN cursor_name(param_value) LOOP.

**Q12. What is the difference between %FOUND and %NOTFOUND?**
%FOUND returns TRUE when the last FETCH successfully retrieved a row. %NOTFOUND returns TRUE when the last FETCH did not retrieve a row (no more data). They are logical opposites.

**Q13. When would you use an explicit cursor over a cursor FOR loop?**
When you need to fetch into specific variables, control the number of rows fetched, or perform special processing between fetches that requires manual cursor management.

---

## Viva Questions

**V1. Define a cursor in one sentence.**
A cursor is a pointer to a private memory area (context area) where Oracle processes a SQL statement and stores the result set.

**V2. Name the two types of cursors in PL/SQL.**
Implicit cursors and explicit cursors.

**V3. Who creates an implicit cursor?**
Oracle creates it automatically whenever a DML statement or SELECT INTO is executed.

**V4. What does SQL%ROWCOUNT return?**
It returns the number of rows affected by the most recent DML statement.

**V5. Write the four steps of the explicit cursor lifecycle.**
DECLARE, OPEN, FETCH, CLOSE.

**V6. What exception is raised if SELECT INTO returns more than one row?**
TOO_MANY_ROWS exception.

**V7. What exception is raised if SELECT INTO returns no rows?**
NO_DATA_FOUND exception.

**V8. What is the advantage of a cursor FOR loop?**
It automatically handles OPEN, FETCH, and CLOSE, reducing code and preventing resource leaks.

**V9. Can a cursor be reopened after closing?**
Yes. Once a cursor is closed, it can be opened again with the OPEN statement.

**V10. What error occurs if too many cursors are left open?**
ORA-01000: maximum open cursors exceeded.

**V11. What is the active set of a cursor?**
The active set is the collection of rows returned by the query associated with the cursor.

**V12. What is SYS_REFCURSOR?**
SYS_REFCURSOR is a predefined weak REF CURSOR type in Oracle that can point to any query without a fixed return type.

---

## Common Mistakes

| Mistake                                    | Problem                                         | Correction                                     |
|--------------------------------------------|--------------------------------------------------|-------------------------------------------------|
| Forgetting to CLOSE an explicit cursor     | Memory leak, ORA-01000 error                    | Always CLOSE the cursor after processing        |
| Using SELECT INTO for multi-row queries    | Raises TOO_MANY_ROWS exception                  | Use an explicit cursor for multi-row results    |
| Checking %FOUND before the first FETCH     | Returns NULL, not TRUE or FALSE                 | Always FETCH first, then check %FOUND           |
| Placing EXIT WHEN before FETCH             | Skips the first row or creates infinite loop     | Always FETCH first, then EXIT WHEN %NOTFOUND    |
| Fetching into wrong number of variables    | PL/SQL compilation error                         | Match FETCH variables with cursor SELECT columns |
| Opening an already open cursor             | Raises CURSOR_ALREADY_OPEN exception             | Check %ISOPEN before opening or close first     |
| Fetching from a closed cursor              | Raises INVALID_CURSOR exception                  | Ensure cursor is open before fetching           |
| Not using %TYPE for fetch variables        | Data type mismatch errors                        | Use table.column%TYPE for type safety           |
| Declaring cursor variable same as cursor   | Naming conflict and confusion                    | Use distinct names for cursors and variables    |
| Forgetting SET SERVEROUTPUT ON             | No output visible in SQL*Plus                    | Always add SET SERVEROUTPUT ON before the block |

---

## Quick Revision

| Topic                     | Key Point                                              |
|---------------------------|--------------------------------------------------------|
| Cursor                    | Pointer to context area in memory                      |
| Context Area              | Private memory allocated by Oracle for SQL processing  |
| Implicit Cursor           | Auto-created by Oracle for DML and SELECT INTO         |
| Implicit Cursor Name      | SQL                                                    |
| SQL%FOUND                 | TRUE if DML affected rows                              |
| SQL%NOTFOUND              | TRUE if DML affected zero rows                         |
| SQL%ROWCOUNT              | Number of rows affected                                |
| SQL%ISOPEN                | Always FALSE                                           |
| Explicit Cursor           | User-defined for multi-row SELECT                      |
| Lifecycle                 | DECLARE -> OPEN -> FETCH -> CLOSE                      |
| %FOUND                    | TRUE if last FETCH got a row                           |
| %NOTFOUND                 | TRUE if last FETCH got no row                          |
| %ROWCOUNT                 | Total rows fetched so far                              |
| %ISOPEN                   | TRUE if cursor is open                                 |
| Cursor FOR Loop           | Auto OPEN, FETCH, CLOSE                                |
| Parameterized Cursor      | Accepts arguments for flexible queries                 |
| REF CURSOR                | Dynamic cursor variable, can point to any query        |
| SYS_REFCURSOR             | Predefined weak REF CURSOR type                        |
| ORA-01000                 | Too many open cursors error                            |
| TOO_MANY_ROWS             | SELECT INTO returns more than one row                  |
| NO_DATA_FOUND             | SELECT INTO returns zero rows                          |
| Golden Rule               | Always CLOSE explicit cursors to free memory           |

---
