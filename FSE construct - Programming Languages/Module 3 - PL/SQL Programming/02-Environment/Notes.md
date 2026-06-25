# PL/SQL Environment and Block Structure

## Introduction

Every programming language needs a place to write and run code. PL/SQL is no different. Before writing any PL/SQL program, you must understand **where** to write it and **how** the code is structured. This module covers the PL/SQL development environment and the fundamental building block of every PL/SQL program -- the **Block Structure**. Understanding the block structure is the first step to mastering PL/SQL because every program, whether simple or complex, follows this same structure.

---

## Definition

**PL/SQL Environment** refers to the tools and platforms used to write, compile, and execute PL/SQL code against an Oracle database.

**PL/SQL Block** is the basic unit of a PL/SQL program. It is a group of related statements that are treated as a single unit. Every PL/SQL program is made up of one or more blocks.

| Term | Meaning |
|------|---------|
| Environment | The tool or platform where you write and run PL/SQL code |
| Block | A structured group of PL/SQL statements enclosed between DECLARE/BEGIN and END |
| Anonymous Block | A block without a name, not stored in the database |
| Named Block | A block with a name (procedure, function, package, trigger), stored in the database |

---

## Why It Is Used

| Purpose | Explanation |
|---------|-------------|
| Structured Programming | Blocks divide code into logical sections making it easier to read and maintain |
| Error Handling | The EXCEPTION section allows graceful handling of runtime errors |
| Code Reusability | Named blocks (procedures, functions) can be stored and called multiple times |
| Modularity | Complex logic can be broken into smaller nested blocks |
| Variable Scope | Each block defines its own scope for variables |
| Database Integration | The environment connects your PL/SQL code directly to Oracle database |

---

## PL/SQL Development Environments

| Tool | Type | Description | Best For |
|------|------|-------------|----------|
| SQL*Plus | Command-Line | Built-in Oracle command-line tool | Quick testing, scripting, server-side execution |
| SQL Developer | GUI (Desktop) | Free Oracle IDE with editor, debugger, and object browser | Development, debugging, database administration |
| Oracle Live SQL | Web-Based | Free online platform by Oracle, no installation needed | Learning, practice, sharing code snippets |
| TOAD | GUI (Desktop) | Third-party commercial database tool | Enterprise development |
| DataGrip | GUI (Desktop) | JetBrains IDE for databases | Multi-database development |

### SET SERVEROUTPUT ON

Before running any PL/SQL block that uses `DBMS_OUTPUT.PUT_LINE`, you must enable server output display.

```sql
SET SERVEROUTPUT ON;
```

| Without This Command | With This Command |
|----------------------|-------------------|
| DBMS_OUTPUT messages are generated but NOT displayed | DBMS_OUTPUT messages are displayed on screen |
| No error occurs, output is simply hidden | Output appears after block execution completes |

---

## Syntax

### Anonymous Block Syntax

```sql
DECLARE
    -- Variable declarations (optional section)
    variable_name DATATYPE;
BEGIN
    -- Executable statements (mandatory section)
    statement1;
    statement2;
EXCEPTION
    -- Error handling (optional section)
    WHEN exception_name THEN
        handling_statement;
END;
/
```

### Named Block Syntax (Procedure)

```sql
CREATE OR REPLACE PROCEDURE procedure_name (
    parameter_name IN DATATYPE
)
IS
    -- Variable declarations (optional)
    variable_name DATATYPE;
BEGIN
    -- Executable statements (mandatory)
    statement1;
    statement2;
EXCEPTION
    -- Error handling (optional)
    WHEN exception_name THEN
        handling_statement;
END procedure_name;
/
```

### Named Block Syntax (Function)

```sql
CREATE OR REPLACE FUNCTION function_name (
    parameter_name IN DATATYPE
)
RETURN return_datatype
IS
    variable_name DATATYPE;
BEGIN
    statement1;
    RETURN value;
EXCEPTION
    WHEN exception_name THEN
        RETURN default_value;
END function_name;
/
```

---

## Explanation

### Block Section Keywords

| Keyword | Required | Purpose |
|---------|----------|---------|
| DECLARE | Optional | Declares variables, constants, cursors, and user-defined types |
| BEGIN | Mandatory | Marks the start of executable statements |
| EXCEPTION | Optional | Handles runtime errors that occur in the BEGIN section |
| END; | Mandatory | Marks the end of the PL/SQL block |
| / | Required | Tells SQL*Plus to execute the PL/SQL block |

### Named Block Keywords

| Keyword | Purpose |
|---------|---------|
| CREATE | Creates a new database object |
| OR REPLACE | Replaces the object if it already exists, avoids DROP and recreate |
| PROCEDURE | Defines a named block that performs an action |
| FUNCTION | Defines a named block that returns a value |
| IS / AS | Separates the header from the body (equivalent keywords) |
| IN | Parameter mode, value passed into the block |
| OUT | Parameter mode, value passed out of the block |
| IN OUT | Parameter mode, value passed in and modified value passed out |
| RETURN | Specifies the return type of a function |

### Other Important Keywords

| Keyword | Purpose |
|---------|---------|
| DBMS_OUTPUT.PUT_LINE | Built-in procedure to print output to the console |
| SQLERRM | Returns the error message of the most recent exception |
| SQLCODE | Returns the error code of the most recent exception |
| WHEN OTHERS | Catches all exceptions not handled by specific handlers |

---

## Execution Flow

### Anonymous Block Execution Flow

```
+--------------------------------------------------+
|               SQL*Plus / SQL Developer            |
|         SET SERVEROUTPUT ON;                      |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|              DECLARE Section                      |
|  (Memory is allocated for all variables)          |
|  v_employee_name := NULL                          |
|  v_salary := NULL                                 |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|              BEGIN Section                         |
|  (Statements execute one by one, top to bottom)   |
|                                                    |
|  Step 1: v_employee_name := 'Rahul Sharma'        |
|  Step 2: v_salary := 55000.00                     |
|  Step 3: DBMS_OUTPUT.PUT_LINE(name)               |
|  Step 4: DBMS_OUTPUT.PUT_LINE(salary)             |
|  Step 5: DBMS_OUTPUT.PUT_LINE(status)             |
|                                                    |
|  If error occurs ----+                             |
|                      |                             |
+----------------------|-----------------------------+
          |            |
    [No Error]    [Error Occurs]
          |            |
          v            v
+-------------+  +----------------------------+
|   Output    |  |    EXCEPTION Section       |
|  displayed  |  |  WHEN OTHERS THEN          |
|  on screen  |  |    Print error message     |
+-------------+  +----------------------------+
          |            |
          v            v
+--------------------------------------------------+
|                 END; /                             |
|          (Block terminates)                        |
+--------------------------------------------------+
```

### Named Block Execution Flow

```
+--------------------------------------------------+
|         CREATE OR REPLACE PROCEDURE               |
|         greet_employee(p_employee_name)            |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|    Oracle compiles and stores the procedure        |
|    in the Data Dictionary                          |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|         Call: greet_employee('Rahul Sharma')       |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|    p_employee_name receives 'Rahul Sharma'         |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|    BEGIN section executes                           |
|    DBMS_OUTPUT.PUT_LINE('Welcome...')              |
|    DBMS_OUTPUT.PUT_LINE('We are glad...')          |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|    END greet_employee; /                           |
|    (Procedure execution completes)                 |
+--------------------------------------------------+
```

### Block Structure Diagram

```
+===================================================+
|                  PL/SQL BLOCK                      |
+===================================================+
|                                                     |
|  +-----------------------------------------------+ |
|  |  DECLARE (Optional)                            | |
|  |    Variables, Constants, Cursors               | |
|  +-----------------------------------------------+ |
|                                                     |
|  +-----------------------------------------------+ |
|  |  BEGIN (Mandatory)                             | |
|  |    SQL Statements                              | |
|  |    PL/SQL Statements                           | |
|  |    Control Structures                          | |
|  |                                                | |
|  |    +---------------------------------------+   | |
|  |    |  NESTED BLOCK (Optional)              |   | |
|  |    |    DECLARE ... BEGIN ... END;          |   | |
|  |    +---------------------------------------+   | |
|  |                                                | |
|  +-----------------------------------------------+ |
|                                                     |
|  +-----------------------------------------------+ |
|  |  EXCEPTION (Optional)                          | |
|  |    WHEN ... THEN ...                           | |
|  |    WHEN OTHERS THEN ...                        | |
|  +-----------------------------------------------+ |
|                                                     |
|  +-----------------------------------------------+ |
|  |  END; (Mandatory)                              | |
|  +-----------------------------------------------+ |
|                                                     |
+===================================================+
```

---

## Real Life Example

Think of a PL/SQL block like a **recipe card** in a kitchen.

| Recipe Card Section | PL/SQL Block Section | Example |
|---------------------|----------------------|---------|
| Ingredients List | DECLARE | Flour, Sugar, Eggs (Variables like name, salary) |
| Cooking Steps | BEGIN | Mix flour, add eggs, bake for 30 min (Executable statements) |
| What if something goes wrong | EXCEPTION | If oven breaks, use microwave (Error handling) |
| Done / Serve | END | Dish is complete (Block terminates) |

**Anonymous Block** is like cooking a recipe from memory -- you make it once, eat it, and it is gone. There is no written record saved.

**Named Block** is like writing the recipe in a cookbook -- you save it with a name and can use it anytime, as many times as you want.

| Scenario | Block Type |
|----------|-----------|
| Quick one-time calculation | Anonymous Block |
| Reusable salary calculator used by HR every month | Named Block (Procedure) |
| One-time data cleanup script | Anonymous Block |
| Tax computation used across multiple applications | Named Block (Function) |

---

## Code Explanation

### Anonymous_Block.sql

```
SET SERVEROUTPUT ON;
```
This command enables the display of output from DBMS_OUTPUT.PUT_LINE on the console. Without this, the block runs but no output is visible.

```
DECLARE
    v_employee_name VARCHAR2(50);
    v_salary        NUMBER(10, 2);
```
The DECLARE section creates two variables. `v_employee_name` can hold up to 50 characters of text. `v_salary` can hold a number with up to 10 digits, 2 of which are after the decimal point. The `v_` prefix is a naming convention indicating these are local variables.

```
BEGIN
    v_employee_name := 'Rahul Sharma';
    v_salary := 55000.00;
```
The BEGIN section starts the executable code. The `:=` operator assigns values to the variables. The string value is enclosed in single quotes.

```
    DBMS_OUTPUT.PUT_LINE('Employee Name : ' || v_employee_name);
    DBMS_OUTPUT.PUT_LINE('Salary        : Rs.' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Status        : Active Employee');
```
Three lines of output are printed. The `||` operator concatenates (joins) strings together. Each PUT_LINE call prints one line of text.

```
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
```
The EXCEPTION section catches any unexpected error. `WHEN OTHERS` is a catch-all handler. `SQLERRM` returns the error message text. The block ends with `END;` and `/` tells SQL*Plus to execute the block.

**Expected Output:**
```
Employee Name : Rahul Sharma
Salary        : Rs.55000
Status        : Active Employee
```

---

### Named_Block.sql

```
SET SERVEROUTPUT ON;
```
Enables console output display.

```
CREATE OR REPLACE PROCEDURE greet_employee (
    p_employee_name IN VARCHAR2
)
IS
```
This creates a stored procedure named `greet_employee`. `OR REPLACE` means if a procedure with this name already exists, it will be replaced. The procedure accepts one parameter `p_employee_name` with mode `IN` (input only). The `p_` prefix is a naming convention for parameters. `IS` separates the procedure header from the body.

```
BEGIN
    DBMS_OUTPUT.PUT_LINE('Welcome to the Organization, ' || p_employee_name || '!');
    DBMS_OUTPUT.PUT_LINE('We are glad to have you on the team.');
END greet_employee;
/
```
The BEGIN section prints two greeting messages using the parameter value. `END greet_employee` explicitly names the procedure being ended for readability. The `/` compiles and stores the procedure.

```
BEGIN
    greet_employee('Rahul Sharma');
END;
/
```
This is a separate anonymous block that calls (executes) the stored procedure, passing the string `'Rahul Sharma'` as the argument.

**Expected Output:**
```
Procedure created.

Welcome to the Organization, Rahul Sharma!
We are glad to have you on the team.
```

---

## Comparison: Anonymous vs Named Blocks

| Feature | Anonymous Block | Named Block |
|---------|----------------|-------------|
| Has a Name | No | Yes |
| Stored in Database | No | Yes (in Data Dictionary) |
| Reusable | No, must rewrite each time | Yes, call by name anytime |
| Compiled | Every time it runs | Once at creation, stored compiled |
| Parameters | Cannot accept parameters | Can accept IN, OUT, IN OUT parameters |
| Called by Other Programs | No | Yes |
| Starts With | DECLARE or BEGIN | CREATE OR REPLACE PROCEDURE/FUNCTION |
| Use Case | Quick testing, one-time scripts | Reusable business logic |
| Performance | Compiled each execution | Pre-compiled, faster on repeated calls |
| Return Value | No | Functions can return a value |
| Can be Part of a Package | No | Yes |

---

## Nested Blocks

A PL/SQL block can contain another block inside it. The inner block is called a **nested block**. Each nested block has its own scope for variables.

```
Outer Block Scope
+---------------------------------------------+
|  DECLARE                                     |
|      v_outer VARCHAR2(20) := 'OUTER';        |
|  BEGIN                                        |
|      DBMS_OUTPUT.PUT_LINE(v_outer);           |
|                                               |
|      Inner Block Scope                        |
|      +-----------------------------------+    |
|      |  DECLARE                           |    |
|      |      v_inner VARCHAR2(20) := 'IN'; |    |
|      |  BEGIN                             |    |
|      |      DBMS_OUTPUT.PUT_LINE(v_outer);|    |  <-- Can access outer
|      |      DBMS_OUTPUT.PUT_LINE(v_inner);|    |
|      |  END;                              |    |
|      +-----------------------------------+    |
|                                               |
|      DBMS_OUTPUT.PUT_LINE(v_outer);           |
|      -- v_inner is NOT accessible here        |
|  END;                                         |
+---------------------------------------------+
```

| Rule | Description |
|------|-------------|
| Inner block can access outer block variables | The inner block can read and modify variables declared in the outer block |
| Outer block cannot access inner block variables | Variables declared inside the inner block are not visible outside it |
| Each block can have its own EXCEPTION section | Errors in the inner block can be handled separately |

---

## Interview Questions

**Q1. What is a PL/SQL block?**
A PL/SQL block is the basic unit of a PL/SQL program. It is a group of logically related statements enclosed between DECLARE/BEGIN and END keywords that are treated as a single unit by the Oracle engine.

**Q2. What are the sections of a PL/SQL block?**
A PL/SQL block has four sections: DECLARE (optional, for variable declarations), BEGIN (mandatory, for executable statements), EXCEPTION (optional, for error handling), and END (mandatory, to terminate the block).

**Q3. What is the difference between an anonymous block and a named block?**
An anonymous block has no name, is not stored in the database, cannot be called by other programs, and is compiled every time it runs. A named block has a name, is stored in the database, can be called repeatedly, and is compiled once at creation.

**Q4. What is the purpose of SET SERVEROUTPUT ON?**
SET SERVEROUTPUT ON enables the display of output generated by DBMS_OUTPUT.PUT_LINE on the console. Without it, the output is generated internally but not shown to the user.

**Q5. What is the difference between IS and AS in a procedure?**
There is no functional difference. Both IS and AS can be used interchangeably in procedure and function declarations. It is purely a matter of coding style.

**Q6. Can we have a BEGIN section without a DECLARE section?**
Yes. The DECLARE section is optional. If no variables need to be declared, the block can start directly with BEGIN.

**Q7. What is a nested block? Can you access outer block variables from an inner block?**
A nested block is a PL/SQL block placed inside another block. Yes, the inner block can access variables declared in the outer block, but the outer block cannot access variables declared in the inner block.

**Q8. What does the / (forward slash) do at the end of a PL/SQL block?**
The forward slash tells SQL*Plus or SQL Developer to execute the PL/SQL block that has been entered into the buffer. Without it, the block is stored in the buffer but not executed.

**Q9. What happens if the EXCEPTION section is missing and an error occurs?**
If there is no EXCEPTION section, the error propagates to the calling environment (SQL*Plus, application, or outer block). The block terminates abnormally and the error is displayed as an unhandled exception.

**Q10. Can an anonymous block accept parameters?**
No. Anonymous blocks cannot accept parameters. Only named blocks (procedures and functions) can accept parameters using IN, OUT, or IN OUT modes.

**Q11. What is DBMS_OUTPUT.PUT_LINE?**
DBMS_OUTPUT.PUT_LINE is a built-in Oracle procedure in the DBMS_OUTPUT package. It writes a line of text to the output buffer, which is displayed on the console when SERVEROUTPUT is enabled.

**Q12. What are the different types of named blocks in PL/SQL?**
The main types are: Procedures (perform actions, no return value), Functions (return a single value), Packages (group related procedures and functions), and Triggers (automatically execute on database events).

---

## Viva Questions

**V1. Write the minimum valid PL/SQL block.**
```sql
BEGIN
    NULL;
END;
/
```
This is the smallest valid block. BEGIN and END are mandatory. NULL is a valid executable statement that does nothing.

**V2. Is the DECLARE section mandatory?**
No. DECLARE is optional. It is only needed when you want to declare variables, constants, or cursors.

**V3. Can we write SQL statements inside a PL/SQL block?**
Yes. You can write DML statements (SELECT, INSERT, UPDATE, DELETE) inside the BEGIN section. DDL statements (CREATE, ALTER, DROP) require EXECUTE IMMEDIATE.

**V4. What is the difference between a procedure and a function?**
A procedure performs an action and does not return a value. A function must return exactly one value using the RETURN statement.

**V5. What does WHEN OTHERS mean in the EXCEPTION section?**
WHEN OTHERS is a catch-all exception handler that catches any exception not already handled by specific exception handlers. It should typically be the last handler in the EXCEPTION section.

**V6. What happens if you forget the semicolon after END?**
The block will not compile. Oracle will raise a syntax error because every PL/SQL statement must end with a semicolon, including the END statement.

**V7. Can we have multiple EXCEPTION handlers in one block?**
Yes. You can have multiple WHEN clauses for different exceptions, such as WHEN NO_DATA_FOUND, WHEN TOO_MANY_ROWS, and WHEN OTHERS, all in the same EXCEPTION section.

**V8. What is the scope of a variable declared in the DECLARE section?**
The variable is accessible from the BEGIN section through the EXCEPTION section of the same block, and in any nested blocks within it. It is not accessible outside the block.

**V9. Name three tools to run PL/SQL code.**
SQL*Plus (command-line), Oracle SQL Developer (GUI desktop application), and Oracle Live SQL (web-based online tool).

**V10. What is the difference between PUT_LINE and PUT in DBMS_OUTPUT?**
PUT_LINE writes text and adds a newline character at the end. PUT writes text without a newline, so subsequent PUT calls append to the same line until a NEW_LINE or PUT_LINE is called.

**V11. Can a named block exist without parameters?**
Yes. Parameters are optional for procedures and functions. A procedure with no parameters is declared without parentheses.

**V12. What is the role of OR REPLACE in CREATE OR REPLACE PROCEDURE?**
OR REPLACE allows the procedure to be recreated without first dropping it. If a procedure with the same name exists, it is replaced. If it does not exist, a new one is created. This avoids the "name already used" error.

---

## Common Mistakes

| Mistake | Problem | Fix |
|---------|---------|-----|
| Forgetting SET SERVEROUTPUT ON | Output from DBMS_OUTPUT.PUT_LINE is not displayed | Always run SET SERVEROUTPUT ON before executing blocks |
| Missing semicolon after END | Compilation error | Always write END; with a semicolon |
| Missing / after the block | Block is not executed in SQL*Plus | Always add / on a new line after END; |
| Using = instead of := for assignment | Compilation error, = is for comparison | Use := for assignment in PL/SQL |
| Declaring variables after BEGIN | Compilation error | All declarations must be in the DECLARE section, before BEGIN |
| Forgetting single quotes around strings | ORA-06550 error, Oracle treats text as identifier | Enclose string values in single quotes |
| Using DECLARE in a named block | Compilation error | Named blocks use IS or AS instead of DECLARE |
| Accessing inner block variable from outer block | Variable not declared error | Inner block variables are not visible in the outer block |
| Not handling exceptions | Block crashes on error with no meaningful message | Add EXCEPTION section with at least WHEN OTHERS |
| Writing DBMS_OUTPUT.PUTLINE (no underscore) | Compilation error, procedure not found | Correct syntax is DBMS_OUTPUT.PUT_LINE with an underscore |

---

## Quick Revision

| Topic | Key Point |
|-------|-----------|
| PL/SQL Block | Basic unit of PL/SQL, contains DECLARE, BEGIN, EXCEPTION, END |
| DECLARE | Optional, used to declare variables, constants, cursors |
| BEGIN | Mandatory, contains executable SQL and PL/SQL statements |
| EXCEPTION | Optional, handles runtime errors |
| END; | Mandatory, terminates the block, must have semicolon |
| / | Executes the block in SQL*Plus |
| Anonymous Block | No name, not stored, compiled every time, cannot accept parameters |
| Named Block | Has a name, stored in database, compiled once, can accept parameters |
| SET SERVEROUTPUT ON | Required to see DBMS_OUTPUT.PUT_LINE output |
| := | Assignment operator in PL/SQL |
| II (double pipe) | Concatenation operator to join strings |
| WHEN OTHERS | Catches all unhandled exceptions |
| IS / AS | Used in named blocks instead of DECLARE |
| Nested Block | A block inside another block, inner can access outer variables |
| DBMS_OUTPUT.PUT_LINE | Prints output to console |

### Block Structure at a Glance

```
DECLARE       <-- Optional (Variables)
BEGIN          <-- Mandatory (Code)
EXCEPTION     <-- Optional (Error Handling)
END;          <-- Mandatory (Termination)
/             <-- Execute
```

### Remember This Rule

```
"D-B-E-E"
Declare - Begin - Exception - End
(Only B and E-end are mandatory)
```

---
