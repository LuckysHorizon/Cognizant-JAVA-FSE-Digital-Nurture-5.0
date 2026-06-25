# PL/SQL Basic Syntax

## Introduction

PL/SQL (Procedural Language/SQL) extends SQL with procedural capabilities like variables, constants, data types, control structures, and output statements. Understanding basic syntax is the foundation of writing any PL/SQL program. This section covers how to declare variables, use different data types, assign values, print output, and accept user input. Every PL/SQL program follows a structured block format, and mastering these basics is essential before moving to advanced topics like cursors, exceptions, and stored procedures.

---

## Definition

**PL/SQL Basic Syntax** refers to the fundamental building blocks used to write a PL/SQL program. This includes:

| Component | Purpose |
|---|---|
| Variables | Store temporary data during program execution |
| Constants | Store fixed values that cannot be changed |
| Data Types | Define the kind of data a variable can hold |
| Assignment Operator (:=) | Assign values to variables |
| DBMS_OUTPUT | Display output to the console |
| %TYPE | Anchor a variable to a column's data type |
| %ROWTYPE | Create a record matching a table's row structure |
| Substitution Variables | Accept user input at runtime |

---

## Why It Is Used

| Reason | Explanation |
|---|---|
| Data Storage | Variables hold intermediate results during processing |
| Type Safety | Data types ensure only valid data is stored |
| Code Clarity | Constants make code readable and maintainable |
| Dynamic Binding | %TYPE and %ROWTYPE adapt to schema changes automatically |
| Debugging | DBMS_OUTPUT helps trace program execution |
| User Interaction | Substitution variables allow runtime input |
| Data Integrity | NOT NULL and DEFAULT prevent uninitialized variables |

---

## Syntax

### Variable Declaration

```sql
DECLARE
    variable_name   DATA_TYPE(size) [:= value];
    variable_name   DATA_TYPE       DEFAULT value;
    variable_name   DATA_TYPE       NOT NULL := value;
BEGIN
    -- executable statements
END;
/
```

### Constant Declaration

```sql
DECLARE
    constant_name   CONSTANT DATA_TYPE := value;
BEGIN
    -- constant_name cannot be reassigned
END;
/
```

### %TYPE Attribute

```sql
DECLARE
    v_name  HR.EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME INTO v_name
    FROM HR.EMPLOYEES
    WHERE EMPLOYEE_ID = 100;
END;
/
```

### %ROWTYPE Attribute

```sql
DECLARE
    v_emp_rec   HR.EMPLOYEES%ROWTYPE;
BEGIN
    SELECT * INTO v_emp_rec
    FROM HR.EMPLOYEES
    WHERE EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(v_emp_rec.FIRST_NAME);
END;
/
```

### Output Statements

```sql
BEGIN
    DBMS_OUTPUT.PUT_LINE('Complete line with newline');
    DBMS_OUTPUT.PUT('Partial output without newline');
    DBMS_OUTPUT.NEW_LINE;
END;
/
```

### Substitution Variables

```sql
DECLARE
    v_id NUMBER := &enter_id;          -- Prompts every time
    v_name VARCHAR2(30) := '&&name';   -- Prompts only once per session
BEGIN
    DBMS_OUTPUT.PUT_LINE(v_id || ' ' || v_name);
END;
/
```

---

## Explanation

### Variables

A variable is a named storage location in memory. In PL/SQL, every variable must be declared in the DECLARE section before use.

**Three ways to initialize:**

| Method | Syntax | When Value is Set |
|---|---|---|
| Assignment Operator | v_name VARCHAR2(30) := 'Rahul'; | At declaration |
| DEFAULT Keyword | v_city VARCHAR2(30) DEFAULT 'Mumbai'; | At declaration |
| SELECT INTO | SELECT col INTO v_name FROM table; | At runtime |

### Constants

A constant is a variable whose value cannot change after initialization. Use the `CONSTANT` keyword.

```
c_pi  CONSTANT NUMBER(5,2) := 3.14;
```

Attempting to reassign a constant causes a compilation error.

### Data Types Comparison Table

| Data Type | Description | Size/Range | Example |
|---|---|---|---|
| NUMBER(p,s) | Numeric data with precision and scale | Up to 38 digits | NUMBER(10,2) holds 12345678.99 |
| VARCHAR2(n) | Variable-length character string | 1 to 32,767 bytes in PL/SQL | VARCHAR2(50) holds 'Hello' |
| CHAR(n) | Fixed-length character string, padded with spaces | 1 to 32,767 bytes in PL/SQL | CHAR(10) pads 'A' to 'A         ' |
| DATE | Stores date and time | 01-JAN-4712 BC to 31-DEC-9999 AD | DATE := SYSDATE |
| BOOLEAN | Logical value | TRUE, FALSE, or NULL | BOOLEAN := TRUE |
| BINARY_INTEGER | Signed integer, faster arithmetic | -2,147,483,647 to 2,147,483,647 | BINARY_INTEGER := -500 |
| PLS_INTEGER | Signed integer, uses machine arithmetic | -2,147,483,647 to 2,147,483,647 | PLS_INTEGER := 1200 |

**VARCHAR2 vs CHAR:**

| Feature | VARCHAR2 | CHAR |
|---|---|---|
| Storage | Stores only actual characters | Pads with spaces to full length |
| Memory | More efficient | Wastes space |
| Comparison | Compares actual content | Comparison includes padding |
| Usage | Names, addresses, descriptions | Fixed codes like gender (M/F) |

**BINARY_INTEGER vs PLS_INTEGER:**

| Feature | BINARY_INTEGER | PLS_INTEGER |
|---|---|---|
| Arithmetic | Library-based | Machine-native (faster) |
| Overflow | Wraps around silently | Raises exception on overflow |
| Performance | Slower | Faster |
| Recommendation | Legacy code | Preferred in modern PL/SQL |

### Assignment Operator (:=)

The `:=` operator assigns a value to a variable. It is NOT the same as `=` (which is for comparison).

```
v_salary := 50000;          -- Direct assignment
v_bonus  := v_salary * 0.1; -- Expression assignment
SELECT sal INTO v_salary ... -- Query assignment
```

### NOT NULL Constraint

When NOT NULL is specified, the variable must be initialized and can never hold NULL.

```
v_id NUMBER NOT NULL := 100;  -- Valid
v_id NUMBER NOT NULL;         -- ERROR: must be initialized
```

### DEFAULT Keyword

DEFAULT provides an initial value, similar to `:=`, but is more readable for documentation purposes.

```
v_status VARCHAR2(10) DEFAULT 'Active';
-- Same as: v_status VARCHAR2(10) := 'Active';
```

### %TYPE Attribute

%TYPE anchors a variable's data type to a table column or another variable. If the column type changes, the variable automatically adapts.

```
v_name HR.EMPLOYEES.FIRST_NAME%TYPE;
```

**Benefits:**
- No need to know exact column data type
- Automatically adapts when column type changes
- Reduces maintenance effort

### %ROWTYPE Attribute

%ROWTYPE creates a record variable that mirrors an entire table row.

```
v_emp HR.EMPLOYEES%ROWTYPE;
```

Access individual fields using dot notation: `v_emp.FIRST_NAME`, `v_emp.SALARY`.

### DBMS_OUTPUT Package

| Procedure | Description |
|---|---|
| PUT_LINE(text) | Prints text followed by a newline |
| PUT(text) | Prints text without a newline |
| NEW_LINE | Prints only a newline character |
| ENABLE(buffer_size) | Enables the output buffer |
| DISABLE | Disables output |
| GET_LINE(line, status) | Reads one line from the buffer |

**Important:** You must run `SET SERVEROUTPUT ON;` in SQL*Plus or SQL Developer before using DBMS_OUTPUT.

### Variable Naming Conventions

| Convention | Example | Description |
|---|---|---|
| v_ prefix | v_salary | Local variable |
| c_ prefix | c_tax_rate | Constant |
| p_ prefix | p_emp_id | Parameter |
| g_ prefix | g_counter | Global/package variable |
| e_ prefix | e_not_found | Exception |

### Scope and Visibility Rules

```
DECLARE                    -- Outer Block
    v_outer NUMBER := 10;
BEGIN
    DECLARE                -- Inner Block
        v_inner NUMBER := 20;
    BEGIN
        -- v_outer is VISIBLE here
        -- v_inner is VISIBLE here
        DBMS_OUTPUT.PUT_LINE(v_outer + v_inner);
    END;
    -- v_inner is NOT visible here
    -- v_outer is VISIBLE here
END;
/
```

| Rule | Description |
|---|---|
| Local Scope | Variable is visible only in the block where it is declared |
| Outer Scope | Variables from outer blocks are visible in inner blocks |
| Inner Scope | Variables from inner blocks are NOT visible in outer blocks |
| Same Name | Inner variable hides (shadows) the outer variable of the same name |

---

## Execution Flow

### PL/SQL Block Execution Flow

```
+------------------------------------------+
|          SET SERVEROUTPUT ON              |
+------------------------------------------+
                    |
                    v
+------------------------------------------+
|          DECLARE Section                 |
|  +------------------------------------+  |
|  | v_name VARCHAR2(50) := 'Rahul';   |  |
|  | v_age  NUMBER(3)    := 25;        |  |
|  | c_rate CONSTANT NUMBER := 18.5;   |  |
|  +------------------------------------+  |
|  Memory Allocation + Initialization      |
+------------------------------------------+
                    |
                    v
+------------------------------------------+
|          BEGIN Section                   |
|  +------------------------------------+  |
|  | SELECT ... INTO v_name             |  |
|  |     FROM HR.EMPLOYEES              |  |
|  |     WHERE EMPLOYEE_ID = 100;       |  |
|  +------------------------------------+  |
|                   |                      |
|                   v                      |
|  +------------------------------------+  |
|  | v_bonus := v_salary * 0.10;        |  |
|  | Computation and Assignment          |  |
|  +------------------------------------+  |
|                   |                      |
|                   v                      |
|  +------------------------------------+  |
|  | DBMS_OUTPUT.PUT_LINE(v_name);      |  |
|  | Output to Console                   |  |
|  +------------------------------------+  |
+------------------------------------------+
                    |
                    v
+------------------------------------------+
|          EXCEPTION Section (Optional)    |
|  Handles errors if any occur             |
+------------------------------------------+
                    |
                    v
+------------------------------------------+
|          END; /                          |
|  Block terminates, memory released       |
+------------------------------------------+
```

### Variable Lifecycle

```
DECLARE          BEGIN              EXCEPTION         END
   |                |                   |               |
   v                v                   v               v
+--------+    +-----------+    +-------------+    +----------+
| Memory |    | Variable  |    | Variable    |    | Memory   |
| Alloc  |--->| Used in   |--->| Available   |--->| Released |
| + Init |    | Statements|    | for Cleanup |    |          |
+--------+    +-----------+    +-------------+    +----------+
```

### Substitution Variable Flow

```
+---------------------------+
|  SQL*Plus reads script    |
+---------------------------+
            |
            v
+---------------------------+
|  Finds &variable_name     |
|  in the source code       |
+---------------------------+
            |
            v
+---------------------------+
|  Prompts user:            |
|  "Enter value for         |
|   variable_name:"         |
+---------------------------+
            |
            v
+---------------------------+
|  Replaces &variable_name  |
|  with user's input        |
+---------------------------+
            |
            v
+---------------------------+
|  Sends modified code to   |
|  PL/SQL engine for        |
|  compilation & execution  |
+---------------------------+
```

---

## Real Life Example

**Scenario: Employee Payroll Slip Generator**

A payroll system needs to:
1. Accept an employee ID
2. Fetch employee details from the database
3. Calculate bonus and tax
4. Print a formatted payroll slip

```sql
SET SERVEROUTPUT ON;

DECLARE
    c_bonus_pct     CONSTANT NUMBER(5,2) := 10.00;
    c_tax_pct       CONSTANT NUMBER(5,2) := 18.00;
    c_company       CONSTANT VARCHAR2(30) := 'TechCorp Solutions';

    v_emp_rec       HR.EMPLOYEES%ROWTYPE;
    v_dept_name     HR.DEPARTMENTS.DEPARTMENT_NAME%TYPE;

    v_gross         NUMBER(10,2);
    v_bonus         NUMBER(10,2);
    v_tax           NUMBER(10,2);
    v_net_pay       NUMBER(10,2);
BEGIN
    SELECT E.*, D.DEPARTMENT_NAME
    INTO v_emp_rec.EMPLOYEE_ID, v_emp_rec.FIRST_NAME, v_emp_rec.LAST_NAME,
         v_emp_rec.EMAIL, v_emp_rec.PHONE_NUMBER, v_emp_rec.HIRE_DATE,
         v_emp_rec.JOB_ID, v_emp_rec.SALARY, v_emp_rec.COMMISSION_PCT,
         v_emp_rec.MANAGER_ID, v_emp_rec.DEPARTMENT_ID, v_dept_name
    FROM HR.EMPLOYEES E
    JOIN HR.DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
    WHERE E.EMPLOYEE_ID = 100;

    v_gross := v_emp_rec.SALARY;
    v_bonus := v_gross * (c_bonus_pct / 100);
    v_tax   := v_gross * (c_tax_pct / 100);
    v_net_pay := v_gross + v_bonus - v_tax;

    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('        ' || c_company);
    DBMS_OUTPUT.PUT_LINE('          PAYROLL SLIP');
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('Employee ID  : ' || v_emp_rec.EMPLOYEE_ID);
    DBMS_OUTPUT.PUT_LINE('Name         : ' || v_emp_rec.FIRST_NAME || ' ' || v_emp_rec.LAST_NAME);
    DBMS_OUTPUT.PUT_LINE('Department   : ' || v_dept_name);
    DBMS_OUTPUT.PUT_LINE('Hire Date    : ' || TO_CHAR(v_emp_rec.HIRE_DATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('----------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Gross Salary : ' || TO_CHAR(v_gross, '99,999.00'));
    DBMS_OUTPUT.PUT_LINE('Bonus (' || c_bonus_pct || '%) : ' || TO_CHAR(v_bonus, '99,999.00'));
    DBMS_OUTPUT.PUT_LINE('Tax (' || c_tax_pct || '%)   : ' || TO_CHAR(v_tax, '99,999.00'));
    DBMS_OUTPUT.PUT_LINE('----------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Net Pay      : ' || TO_CHAR(v_net_pay, '99,999.00'));
    DBMS_OUTPUT.PUT_LINE('========================================');
END;
/
```

This example uses constants for fixed rates, %ROWTYPE for fetching an entire row, %TYPE for department name, assignment operators for calculations, and DBMS_OUTPUT with TO_CHAR for formatted output.

---

## Code Explanation

### File 1: Variables.sql

This program demonstrates the core ways to declare and initialize variables in PL/SQL.

| Line/Section | What It Does |
|---|---|
| `v_first_name VARCHAR2(50) := 'Rahul'` | Declares and initializes a string variable using `:=` |
| `v_is_active BOOLEAN := TRUE` | Declares a BOOLEAN variable (cannot print directly) |
| `v_city VARCHAR2(30) DEFAULT 'Mumbai'` | Uses DEFAULT keyword instead of `:=` |
| `c_tax_rate CONSTANT NUMBER(5,2) := 18.50` | Declares a constant that cannot be reassigned |
| `v_emp_name HR.EMPLOYEES.FIRST_NAME%TYPE` | Anchors data type to the FIRST_NAME column |
| `SELECT FIRST_NAME INTO v_emp_name` | Fetches a value from the database into the variable |
| `IF v_is_active THEN` | BOOLEAN must be checked with IF, not printed directly |

**Key Takeaway:** PL/SQL supports multiple initialization methods, and %TYPE makes code resilient to schema changes.

---

### File 2: DataTypes.sql

This program showcases all major PL/SQL data types in a single block.

| Variable | Data Type | Value | Key Observation |
|---|---|---|---|
| v_price | NUMBER(10,2) | 2599.99 | Precision 10, Scale 2 |
| v_product | VARCHAR2(50) | 'Wireless Headphones' | Stores only actual characters used |
| v_grade | CHAR(10) | 'A' | Padded to 10 characters with spaces |
| v_order_date | DATE | '15-JUN-2026' | Uses TO_DATE for explicit format |
| v_in_stock | BOOLEAN | TRUE | Cannot be passed to PUT_LINE directly |
| v_counter | BINARY_INTEGER | -500 | Can hold negative integers |
| v_quantity | PLS_INTEGER | 1200 | Faster than BINARY_INTEGER |

**Key Takeaway:** CHAR pads with spaces (shown with brackets in output), and BOOLEAN requires an IF statement to display.

---

### File 3: Assignment.sql

This program demonstrates all assignment methods available in PL/SQL.

| Method | Example in Code | Description |
|---|---|---|
| Direct `:=` | `v_bonus NUMBER(10,2) := 5000.00` | Value assigned at declaration |
| DEFAULT | `v_status VARCHAR2(20) DEFAULT 'Active'` | Readable alternative to `:=` |
| SELECT INTO | `SELECT FIRST_NAME, SALARY INTO v_emp_name, v_emp_salary` | Fetches from database |
| Expression | `v_bonus := v_emp_salary * 0.10` | Computed assignment |
| NOT NULL | `v_emp_id NUMBER(6) NOT NULL := 100` | Must always have a value |

**Key Takeaway:** SELECT INTO can assign multiple columns to multiple variables in one statement. NOT NULL variables must be initialized at declaration.

---

### File 4: Print_Output.sql

This program demonstrates every method of producing output in PL/SQL.

| Statement | Behavior |
|---|---|
| `PUT_LINE('Hello, World!')` | Prints string and moves to next line |
| `PUT_LINE(v_age)` | Implicitly converts NUMBER to string |
| `PUT_LINE('Name: ' \|\| v_name \|\| ', Age: ' \|\| v_age)` | Concatenates multiple values |
| `PUT_LINE(TO_CHAR(v_today, 'DD-MON-YYYY'))` | Formats DATE for display |
| `PUT('First ')` | Prints without newline |
| `NEW_LINE` | Flushes PUT buffer with a newline |

**Key Takeaway:** Use PUT_LINE for most output. Use PUT + NEW_LINE when building output incrementally. Always use TO_CHAR to format dates.

---

### File 5: User_Input.sql

This program demonstrates how to accept runtime input using substitution variables.

| Feature | Syntax | Behavior |
|---|---|---|
| Single-use prompt | `&enter_employee_id` | Prompts user each time the script runs |
| Persistent prompt | `&&enter_department_name` | Prompts only once per SQL*Plus session |
| UNDEFINE | `UNDEFINE enter_department_name` | Clears the stored value |
| SET VERIFY OFF | `SET VERIFY OFF;` | Hides the old/new substitution display |

**Key Takeaway:** Substitution variables are a SQL*Plus feature (not PL/SQL). They are replaced before compilation. Use `&` for one-time prompts and `&&` for session-persistent values.

---

## Interview Questions

**Q1: What is the difference between := and DEFAULT in PL/SQL?**
Both assign initial values to variables. `:=` is the assignment operator and can be used anywhere. `DEFAULT` is used only in declarations and is considered more readable for documenting initial values. Functionally, they are identical at declaration time.

**Q2: Can you print a BOOLEAN value using DBMS_OUTPUT.PUT_LINE?**
No. DBMS_OUTPUT.PUT_LINE does not accept BOOLEAN type. You must use an IF-THEN-ELSE statement to convert the BOOLEAN to a string before printing.

**Q3: What is the difference between VARCHAR2 and CHAR?**
VARCHAR2 is variable-length and stores only the actual characters. CHAR is fixed-length and pads the remaining space with blanks. VARCHAR2 is more memory efficient. CHAR is used for fixed-length codes like gender (M/F) or country codes.

**Q4: What happens if you try to assign NULL to a NOT NULL variable?**
Oracle raises a `VALUE_ERROR` exception at runtime. The program terminates unless the exception is handled.

**Q5: What is the advantage of using %TYPE?**
%TYPE anchors a variable's type to a table column. If the column's data type or size changes in the database, the PL/SQL variable automatically adapts without code modification. This reduces maintenance and prevents type mismatch errors.

**Q6: What is the difference between %TYPE and %ROWTYPE?**
%TYPE copies the data type of a single column. %ROWTYPE creates a record variable that contains all columns of a table or cursor. %TYPE is used for individual values, %ROWTYPE for entire rows.

**Q7: What is the maximum size of VARCHAR2 in PL/SQL vs SQL?**
In PL/SQL, VARCHAR2 can hold up to 32,767 bytes. In SQL (table columns), VARCHAR2 is limited to 4,000 bytes (or 32,767 bytes with extended data types enabled in Oracle 12c+).

**Q8: What is the difference between BINARY_INTEGER and PLS_INTEGER?**
Both are integer types. PLS_INTEGER uses machine arithmetic and is faster. PLS_INTEGER raises an overflow exception, while BINARY_INTEGER wraps around silently. PLS_INTEGER is preferred in modern PL/SQL for better performance and error detection.

**Q9: Can a CONSTANT be initialized using SELECT INTO?**
No. A constant must be initialized with a compile-time expression in its declaration. SELECT INTO is a runtime operation and cannot be used to initialize a constant.

**Q10: What is the scope of a variable declared in an inner block?**
A variable declared in an inner (nested) block is only visible within that inner block. Once the inner block's END statement executes, the variable is destroyed and cannot be accessed from the outer block.

**Q11: What does SET SERVEROUTPUT ON do?**
It enables the DBMS_OUTPUT buffer in SQL*Plus or SQL Developer so that output from PUT_LINE and PUT is displayed on screen. Without it, all DBMS_OUTPUT calls are silently ignored.

**Q12: What is the difference between & and && substitution variables?**
`&variable` prompts the user every time the script is run. `&&variable` prompts only once and stores the value for the entire SQL*Plus session. Use UNDEFINE to clear a `&&` variable.

---

## Viva Questions

**V1: Write the syntax to declare a constant in PL/SQL.**
```sql
c_pi CONSTANT NUMBER(5,2) := 3.14;
```

**V2: What is the default value of an uninitialized variable in PL/SQL?**
NULL. All uninitialized variables default to NULL unless a DEFAULT or `:=` value is specified.

**V3: Can you change the value of a CONSTANT after declaration?**
No. Attempting to reassign a constant results in a compilation error: `PLS-00363: expression 'constant_name' cannot be used as an assignment target`.

**V4: What is the purpose of TO_CHAR with dates?**
TO_CHAR converts a DATE value to a formatted VARCHAR2 string. It is essential because DBMS_OUTPUT.PUT_LINE cannot format dates automatically.

**V5: How do you declare a variable that matches the SALARY column of HR.EMPLOYEES?**
```sql
v_salary HR.EMPLOYEES.SALARY%TYPE;
```

**V6: What error do you get if you use PUT_LINE without SET SERVEROUTPUT ON?**
No error occurs. The block executes successfully, but no output is displayed on screen.

**V7: What is the difference between PUT and PUT_LINE?**
PUT_LINE appends a newline after the text. PUT does not append a newline. Multiple PUT calls accumulate text, and NEW_LINE flushes the buffer.

**V8: Can PL/SQL variables have the same name as table columns?**
Yes, but it causes ambiguity in SQL statements within PL/SQL. Use naming prefixes (v_, c_, p_) to avoid conflicts.

**V9: What is the range of PLS_INTEGER?**
-2,147,483,647 to 2,147,483,647 (same as a 32-bit signed integer).

**V10: How do you access a field from a %ROWTYPE variable?**
Using dot notation: `v_emp_rec.FIRST_NAME`, `v_emp_rec.SALARY`.

**V11: What is variable shadowing in PL/SQL?**
When an inner block declares a variable with the same name as an outer block variable, the inner variable hides (shadows) the outer one. Within the inner block, only the inner variable is accessible.

**V12: Is BOOLEAN a valid SQL data type?**
No. BOOLEAN is only available in PL/SQL. It cannot be used in SQL statements, table columns, or SQL functions.

---

## Common Mistakes

| Mistake | Problem | Correction |
|---|---|---|
| Using `=` instead of `:=` | `=` is for comparison, not assignment | Use `:=` for assignment |
| Printing BOOLEAN directly | `PUT_LINE(v_bool)` causes error | Use IF-THEN-ELSE to convert to string |
| Forgetting SET SERVEROUTPUT ON | Output is silently suppressed | Always add `SET SERVEROUTPUT ON;` at the top |
| Missing semicolon after END | `END` without `;` causes compile error | Always write `END;` |
| Missing `/` after block | Block does not execute | Add `/` on a new line after `END;` |
| NOT NULL without initialization | Compilation error | Always provide a value: `v_id NUMBER NOT NULL := 1;` |
| VARCHAR2 size mismatch | Value exceeds declared size | Declare sufficient size or use %TYPE |
| Using BOOLEAN in SQL | BOOLEAN cannot appear in SQL queries | Use VARCHAR2 or NUMBER flag instead |
| Forgetting INTO in SELECT | SELECT without INTO causes error | PL/SQL requires SELECT ... INTO ... |
| Using & in string literals | SQL*Plus treats & as substitution | Use SET DEFINE OFF or escape with \& |
| Assigning to a CONSTANT | Compilation error PLS-00363 | Constants cannot be reassigned after declaration |
| Wrong date format in TO_DATE | ORA-01861 literal does not match format | Ensure format mask matches the string exactly |

---

## Quick Revision

### PL/SQL Block Structure
```
DECLARE    -->  Variables, Constants, Cursors
BEGIN      -->  SQL + PL/SQL Statements
EXCEPTION  -->  Error Handling
END;       -->  Block Termination
/          -->  Execute the Block
```

### Declaration Cheat Sheet

| What | Syntax |
|---|---|
| Variable | `v_name VARCHAR2(50) := 'value';` |
| Constant | `c_rate CONSTANT NUMBER := 18.5;` |
| NOT NULL | `v_id NUMBER NOT NULL := 100;` |
| DEFAULT | `v_city VARCHAR2(30) DEFAULT 'Mumbai';` |
| %TYPE | `v_sal HR.EMPLOYEES.SALARY%TYPE;` |
| %ROWTYPE | `v_rec HR.EMPLOYEES%ROWTYPE;` |

### Output Cheat Sheet

| What | Syntax |
|---|---|
| Print with newline | `DBMS_OUTPUT.PUT_LINE('text');` |
| Print without newline | `DBMS_OUTPUT.PUT('text');` |
| Print newline only | `DBMS_OUTPUT.NEW_LINE;` |
| Format date | `TO_CHAR(SYSDATE, 'DD-MON-YYYY')` |
| Concatenate | `'Name: ' \|\| v_name` |

### Data Types Quick Reference

| Type | Use For | Example |
|---|---|---|
| NUMBER | Decimals, money | `NUMBER(10,2)` |
| VARCHAR2 | Text, names | `VARCHAR2(100)` |
| CHAR | Fixed codes | `CHAR(1)` |
| DATE | Dates and times | `DATE := SYSDATE` |
| BOOLEAN | True/False logic | `BOOLEAN := TRUE` |
| PLS_INTEGER | Fast counters | `PLS_INTEGER := 0` |

### Key Rules to Remember

1. Every variable must be declared in DECLARE section
2. Every PL/SQL block must end with `END;` followed by `/`
3. `:=` is for assignment, `=` is for comparison
4. BOOLEAN cannot be used in SQL or printed directly
5. %TYPE adapts to column changes automatically
6. NOT NULL variables must be initialized at declaration
7. Constants cannot be reassigned after initialization
8. SET SERVEROUTPUT ON must be executed before using DBMS_OUTPUT
9. Substitution variables (&) are resolved before PL/SQL compilation
10. Variable names should follow naming conventions (v_, c_, p_, g_)
