# PL/SQL Procedures and Functions

## Introduction

Every real-world application performs tasks repeatedly. Writing the same logic again and again leads to errors, makes maintenance difficult, and wastes time. PL/SQL solves this problem through **subprograms** -- named PL/SQL blocks that can be stored in the database, reused whenever needed, and called by name. The two types of subprograms are **Procedures** and **Functions**. Together, they form the backbone of modular, maintainable PL/SQL programming.

---

## Definition

### What Are Subprograms?

A **subprogram** is a named PL/SQL block that performs a specific task. Unlike anonymous blocks (which have no name and cannot be reused), subprograms are saved in the database with a name and can be called repeatedly from anywhere.

| Term | Meaning |
|------|---------|
| **Procedure** | A named subprogram that performs an action. It does NOT return a value directly. |
| **Function** | A named subprogram that performs a computation and MUST return exactly one value. |
| **Stored Subprogram** | A procedure or function compiled and stored permanently in the database. |
| **Standalone Subprogram** | A subprogram created at the schema level, not inside any package. |

### Stored vs Standalone Subprograms

| Aspect | Stored Subprogram | Standalone Subprogram |
|--------|--------------------|-----------------------|
| Location | Inside a package | At schema level |
| Compilation | Compiled with the package | Compiled independently |
| Scope | Accessible through the package | Accessible directly by name |
| Overloading | Supported inside packages | Not supported |
| Example | `pkg_name.proc_name` | `proc_name` |

---

## Why It Is Used

| Reason | Explanation |
|--------|-------------|
| **Reusability** | Write once, call many times from different programs |
| **Modularity** | Break complex logic into smaller, manageable units |
| **Maintainability** | Change logic in one place, all callers get the update |
| **Security** | Grant EXECUTE permission without exposing table access |
| **Performance** | Stored subprograms are compiled once and cached in memory |
| **Reduced Network Traffic** | One call executes many statements on the server side |
| **Abstraction** | Callers do not need to know internal implementation details |

---

## Syntax

### Procedure Syntax

```sql
CREATE [OR REPLACE] PROCEDURE procedure_name
    (parameter1 [IN | OUT | IN OUT] datatype,
     parameter2 [IN | OUT | IN OUT] datatype,
     ...)
IS | AS
    -- declaration section (optional)
BEGIN
    -- executable statements
EXCEPTION
    -- exception handlers (optional)
END [procedure_name];
/
```

### Calling a Procedure

**Method 1: Using EXEC (SQL*Plus / SQL Developer)**

```sql
EXEC procedure_name(arguments);
```

**Method 2: Using Anonymous Block**

```sql
BEGIN
    procedure_name(arguments);
END;
/
```

### Drop a Procedure

```sql
DROP PROCEDURE procedure_name;
```

---

### Function Syntax

```sql
CREATE [OR REPLACE] FUNCTION function_name
    (parameter1 [IN] datatype,
     parameter2 [IN] datatype,
     ...)
RETURN return_datatype
IS | AS
    -- declaration section (optional)
BEGIN
    -- executable statements
    RETURN value;
EXCEPTION
    -- exception handlers (optional)
END [function_name];
/
```

### Calling a Function

**Method 1: In a SQL Statement**

```sql
SELECT function_name(arguments) FROM DUAL;
```

**Method 2: In an Anonymous Block**

```sql
DECLARE
    v_result datatype;
BEGIN
    v_result := function_name(arguments);
    DBMS_OUTPUT.PUT_LINE(v_result);
END;
/
```

### Drop a Function

```sql
DROP FUNCTION function_name;
```

---

## Explanation

### Parameter Modes

PL/SQL supports three parameter modes that control how data flows between the caller and the subprogram.

| Mode | Direction | Default Value Allowed | Can Read? | Can Write? | Default? |
|------|-----------|----------------------|-----------|------------|----------|
| **IN** | Caller --> Subprogram | Yes | Yes | No | Yes (default mode) |
| **OUT** | Subprogram --> Caller | No | No | Yes | No |
| **IN OUT** | Both directions | No | Yes | Yes | No |

#### IN Parameter (Default)

- Passes a value INTO the subprogram
- Acts like a constant inside the subprogram -- you cannot modify it
- If no mode is specified, IN is assumed

```
Caller                     Procedure
+--------+    value    +-------------+
|  v_id  | ---------> | p_emp_id IN |
+--------+            +-------------+
```

#### OUT Parameter

- The subprogram writes a value that the caller can read AFTER the call
- The parameter has no initial value inside the subprogram
- Used to return results from procedures

```
Caller                     Procedure
+----------+   value   +--------------+
| v_salary | <-------- | p_salary OUT |
+----------+           +--------------+
```

#### IN OUT Parameter

- The caller passes a value IN, the subprogram can modify it, and the modified value is passed back OUT
- Used when the subprogram needs to read and update the same variable

```
Caller                      Procedure
+----------+   value   +----------------+
| v_phone  | -------> | p_phone IN OUT |
|          | <------- | (modified)     |
+----------+          +----------------+
```

### Procedures

- A procedure is created using `CREATE OR REPLACE PROCEDURE`
- `OR REPLACE` allows recompilation without dropping first
- Procedures can have zero or more parameters
- Procedures do NOT have a RETURN clause in the header
- Procedures CAN use RETURN (without a value) to exit early
- A procedure can call another procedure

### Functions

- A function is created using `CREATE OR REPLACE FUNCTION`
- The `RETURN datatype` clause in the header is mandatory
- The function body MUST contain at least one `RETURN value` statement
- A function must return exactly one value
- Functions can be used inside SQL statements (SELECT, WHERE, etc.) if they follow purity rules (no DML, no OUT parameters)

---

## Execution Flow

### Procedure Execution Flow

```
+---------------------------+
|   Caller (Anonymous Block |
|   or Another Subprogram)  |
+---------------------------+
            |
            | CALL procedure_name(args)
            v
+---------------------------+
|   Oracle Database Engine  |
|   1. Find the procedure   |
|   2. Check if compiled    |
|   3. Load into memory     |
+---------------------------+
            |
            v
+---------------------------+
|   Procedure Body          |
|   1. Receive IN params    |
|   2. Execute statements   |
|   3. Set OUT params       |
|   4. Handle exceptions    |
+---------------------------+
            |
            | Control returns
            v
+---------------------------+
|   Caller resumes          |
|   (OUT values available)  |
+---------------------------+
```

### Function Execution Flow

```
+---------------------------+
|   Caller (SQL Statement   |
|   or PL/SQL Block)        |
+---------------------------+
            |
            | CALL function_name(args)
            v
+---------------------------+
|   Oracle Database Engine  |
|   1. Find the function    |
|   2. Check if compiled    |
|   3. Load into memory     |
+---------------------------+
            |
            v
+---------------------------+
|   Function Body           |
|   1. Receive IN params    |
|   2. Execute statements   |
|   3. Compute return value |
|   4. RETURN value         |
+---------------------------+
            |
            | Return value sent back
            v
+---------------------------+
|   Caller receives the     |
|   returned value          |
+---------------------------+
```

### Parameter Flow Diagram

```
                    +------------------+
   IN Parameter     |                  |
   value ---------> |                  |
                    |                  |
   OUT Parameter    |    PROCEDURE     |
   value <--------- |    or FUNCTION   |
                    |                  |
   IN OUT Param     |                  |
   value ---------> |                  |
   modified <------ |                  |
                    +------------------+
```

---

## Real Life Example

### Restaurant Analogy

Think of a restaurant to understand the difference between procedures and functions.

```
+------------------+     +------------------+     +------------------+
|                  |     |                  |     |                  |
|    CUSTOMER      |     |     KITCHEN      |     |    CASHIER       |
|   (Caller)       |     |   (Procedure)    |     |   (Function)     |
|                  |     |                  |     |                  |
+--------+---------+     +--------+---------+     +--------+---------+
         |                        |                        |
         | Places order           |                        |
         | (IN parameter)         |                        |
         +----------------------->|                        |
         |                        |                        |
         |   Kitchen cooks the    |                        |
         |   food and serves it   |                        |
         |   (performs action,    |                        |
         |    no bill returned)   |                        |
         |                        |                        |
         | Asks for the bill      |                        |
         | (IN parameter)         |                        |
         +----------------------------------------------->|
         |                        |                        |
         |                        |    Calculates total    |
         |                        |    and RETURNS the     |
         |      Bill amount       |    bill amount         |
         |<------------------------------------------------+
         |                        |                        |
```

| Restaurant | PL/SQL |
|------------|--------|
| Customer places an order | Caller passes IN parameters |
| Kitchen cooks the food (action) | Procedure executes statements |
| Kitchen does not return a bill | Procedure has no RETURN value |
| Cashier calculates the bill | Function computes a value |
| Cashier gives back the bill amount | Function RETURNs a value |
| Order slip (what to cook) | IN parameter |
| Served dish (result of cooking) | OUT parameter |
| Menu with prices (read + update) | IN OUT parameter |

---

## Procedures vs Functions -- Comparison Table

| No. | Aspect | Procedure | Function |
|-----|--------|-----------|----------|
| 1 | Purpose | Performs an action | Computes and returns a value |
| 2 | RETURN clause | No RETURN clause in header | RETURN datatype is mandatory in header |
| 3 | RETURN statement | Can use RETURN (no value) to exit early | Must use RETURN value (mandatory) |
| 4 | Return value | Does not return a value directly | Must return exactly one value |
| 5 | Parameter modes | Supports IN, OUT, IN OUT | Typically uses only IN parameters |
| 6 | Calling from SQL | Cannot be called in SQL statements | Can be called in SELECT, WHERE, etc. |
| 7 | Calling syntax | EXEC or BEGIN/END block | Assignment `:=`, SELECT, or expression |
| 8 | Use case | DML operations, complex business logic | Calculations, lookups, validations |
| 9 | Multiple outputs | Yes, through multiple OUT parameters | No, only one return value |
| 10 | Standalone use | Can be executed on its own | Usually used as part of an expression |
| 11 | CREATE syntax | CREATE OR REPLACE PROCEDURE | CREATE OR REPLACE FUNCTION |
| 12 | DROP syntax | DROP PROCEDURE name | DROP FUNCTION name |

---

## Code Explanation

### Program 1: SimpleProcedure.sql -- Basic Procedure with No Parameters

This program demonstrates the simplest form of a procedure with no parameters.

- `CREATE OR REPLACE PROCEDURE display_welcome` creates a procedure named `display_welcome`
- `OR REPLACE` means if the procedure already exists, it will be replaced without error
- The procedure body uses `DBMS_OUTPUT.PUT_LINE` to print a welcome message
- The procedure is then called two ways:
  - First using `EXEC display_welcome` (SQL*Plus shorthand)
  - Then using a `BEGIN...END` anonymous block
- Both calling methods produce the same output

**Expected Output:**
```
Welcome to PL/SQL Programming
```

---

### Program 2: Procedure_IN.sql -- IN Parameter Mode

This program shows how to pass a value INTO a procedure using the IN parameter.

- The procedure `display_employee` accepts one IN parameter: `p_emp_id` of type NUMBER
- `IN` is the default mode -- even if you omit the keyword, it is still IN
- Inside the procedure, `SELECT INTO` fetches `first_name` and `salary` from `HR.EMPLOYEES` where `employee_id` matches the passed value
- The fetched values are stored in local variables and printed
- An exception handler catches `NO_DATA_FOUND` if an invalid employee ID is passed
- The procedure is called with employee_id 100 from an anonymous block

**Expected Output:**
```
Employee Name  : Steven
Employee Salary: 24000
```

---

### Program 3: Procedure_OUT.sql -- OUT Parameter Mode

This program demonstrates returning a value FROM a procedure using the OUT parameter.

- The procedure `get_employee_salary` takes two parameters:
  - `p_emp_id` (IN) -- the employee ID passed by the caller
  - `p_salary` (OUT) -- the salary value set by the procedure for the caller to read
- Inside the procedure, `SELECT salary INTO p_salary` writes the salary into the OUT parameter
- In the calling anonymous block, a variable `v_salary` is declared and passed as the OUT argument
- After the procedure call completes, `v_salary` holds the salary value and is printed
- The OUT parameter has no value before the procedure assigns one

**Expected Output:**
```
Salary of Employee 100: 24000
```

---

### Program 4: Procedure_IN_OUT.sql -- IN OUT Parameter Mode

This program demonstrates the IN OUT parameter mode where a value flows both ways.

- The procedure `format_phone` accepts one IN OUT parameter: `p_phone` of type VARCHAR2
- The caller passes a raw 10-digit phone number (e.g., '9876543210')
- Inside the procedure, `SUBSTR` extracts parts of the number and reformats it as (XXX) XXX-XXXX
- The modified value is written back to the same parameter
- In the calling block, the variable `v_phone` is printed before and after the call to show the transformation
- IN OUT parameters must be variables, not literals or constants

**Expected Output:**
```
Before Formatting: 9876543210
After Formatting : (987) 654-3210
```

---

### Program 5: SimpleFunction.sql -- Basic Function Returning a Number

This program creates a function that performs a calculation and returns a NUMBER value.

- `CREATE OR REPLACE FUNCTION get_annual_salary` creates a function with one IN parameter
- `RETURN NUMBER` in the header declares the return type -- this is mandatory for functions
- The function body computes `p_monthly_salary * 12` and returns the result
- `RETURN value` is the statement that sends the computed value back to the caller
- The function is called two ways:
  - In a `SELECT` statement from `DUAL` -- this works because the function has no side effects
  - In an anonymous block using the `:=` assignment operator
- Both methods produce the same result

**Expected Output (SELECT):**
```
ANNUAL_SALARY
-------------
       600000
```

**Expected Output (Anonymous Block):**
```
Annual Salary: 600000
```

---

### Program 6: SalaryFunction.sql -- Function Querying a Table

This program creates a function that queries the database and returns an employee's salary.

- The function `get_emp_salary` accepts an employee ID and returns the salary as a NUMBER
- Inside the function, `SELECT salary INTO v_salary` fetches the salary from `HR.EMPLOYEES`
- A local variable `v_salary` holds the fetched value before it is returned
- An exception handler returns 0 if the employee ID is not found
- The function is called from an anonymous block and the result is printed
- This function can also be used in SQL: `SELECT get_emp_salary(100) FROM DUAL`

**Expected Output:**
```
Salary of Employee 101: 17000
```

---

## Interview Questions

**Q1: What is the difference between a procedure and a function in PL/SQL?**
A procedure performs an action and does not return a value directly. A function computes a value and must return exactly one value using the RETURN statement. Functions can be used in SQL statements, procedures cannot.

**Q2: What are the three parameter modes in PL/SQL? Explain each.**
IN (default) passes a value into the subprogram and is read-only. OUT passes a value from the subprogram back to the caller and is write-only inside the subprogram. IN OUT allows the parameter to be read and modified, flowing in both directions.

**Q3: Can a procedure have a RETURN statement?**
Yes, but it cannot return a value. A procedure can use `RETURN;` (without a value) to exit the procedure early. A function must use `RETURN value;` to return a result.

**Q4: Can a function be used in a SQL SELECT statement?**
Yes, if the function meets purity rules: it must not perform DML (INSERT, UPDATE, DELETE), must not have OUT or IN OUT parameters, and must not modify package variables. Such functions can be used in SELECT, WHERE, ORDER BY, and other SQL clauses.

**Q5: What happens if you do not include a RETURN statement in a function?**
The function will compile successfully, but at runtime, if no RETURN statement is executed, Oracle raises the error `ORA-06503: PL/SQL: Function returned without value`.

**Q6: What does OR REPLACE do in CREATE OR REPLACE PROCEDURE?**
It allows you to recompile and replace an existing procedure without dropping it first. Without OR REPLACE, you would get an error if the procedure already exists.

**Q7: Can a procedure call another procedure?**
Yes. A procedure can call other procedures, functions, or even itself (recursion). This is common in modular programming where complex logic is broken into smaller procedures.

**Q8: What is the difference between actual and formal parameters?**
Formal parameters are declared in the procedure/function header (e.g., `p_emp_id IN NUMBER`). Actual parameters are the values passed when calling the subprogram (e.g., `100` in `display_employee(100)`).

**Q9: Can you pass a literal value to an OUT parameter?**
No. OUT and IN OUT parameters require a variable as the actual parameter. You cannot pass a literal (like 100) or a constant because the subprogram needs to write a value back to it.

**Q10: What is the difference between IS and AS in procedure creation?**
There is no difference. Both `IS` and `AS` are interchangeable keywords in the CREATE PROCEDURE/FUNCTION syntax. Convention often uses IS for subprograms and AS for packages.

**Q11: How do you drop a procedure and a function?**
Use `DROP PROCEDURE procedure_name;` and `DROP FUNCTION function_name;` respectively. This permanently removes the subprogram from the database.

**Q12: What is a stored subprogram?**
A stored subprogram is a procedure or function that is compiled and stored in the database permanently. It remains available until explicitly dropped and can be called by any user with EXECUTE privilege.

---

## Viva Questions

**V1: What keyword is used to create a procedure?**
`CREATE PROCEDURE` or `CREATE OR REPLACE PROCEDURE`.

**V2: Is the IN keyword mandatory when declaring an IN parameter?**
No. IN is the default parameter mode. If no mode is specified, the parameter is automatically IN.

**V3: Can a function return more than one value?**
No. A function can return only one value. To return multiple values, use a procedure with multiple OUT parameters, or return a record/object type.

**V4: What error occurs if a function does not execute a RETURN statement?**
`ORA-06503: PL/SQL: Function returned without value`.

**V5: Can you call a procedure inside a SELECT statement?**
No. Only functions can be called inside SQL statements. Procedures must be called using EXEC or within a PL/SQL block.

**V6: What is the default parameter mode in PL/SQL?**
IN is the default parameter mode.

**V7: Can an IN parameter be modified inside the procedure?**
No. An IN parameter is read-only inside the subprogram. Attempting to assign a value to it causes a compilation error.

**V8: What is the purpose of the RETURN clause in a function header?**
It declares the datatype of the value that the function will return. For example, `RETURN NUMBER` means the function returns a numeric value.

**V9: How is a function different from an anonymous block?**
A function has a name, is stored in the database, can accept parameters, returns a value, and can be reused. An anonymous block has no name, is not stored, and cannot be called by other programs.

**V10: Can a procedure and a function have the same name?**
Yes, because they are different types of database objects. However, this is not recommended as it causes confusion.

**V11: What does SERVEROUTPUT ON do?**
It enables the display of output from `DBMS_OUTPUT.PUT_LINE` in SQL*Plus or SQL Developer. Without it, printed output is not visible.

**V12: What happens if you create a procedure that already exists without using OR REPLACE?**
Oracle raises the error `ORA-00955: name is already used by an existing object`.

---

## Common Mistakes

| Mistake | Problem | Correct Approach |
|---------|---------|-----------------|
| Specifying size in parameter datatype | `p_name IN VARCHAR2(50)` causes error | Use `p_name IN VARCHAR2` (no size) |
| Forgetting RETURN in function body | Compiles but fails at runtime with ORA-06503 | Always include at least one RETURN statement |
| Passing a literal to OUT parameter | `get_salary(100, 5000)` -- 5000 cannot receive output | Declare a variable and pass it |
| Using semicolon after EXEC | `EXEC proc_name();` -- extra semicolon | Use `EXEC proc_name()` without trailing semicolon |
| Missing `/` after CREATE statement | Procedure is not compiled | Always end with `/` on a new line |
| Forgetting SET SERVEROUTPUT ON | Output from DBMS_OUTPUT is not visible | Add `SET SERVEROUTPUT ON` before execution |
| Assigning value to IN parameter | Compilation error: cannot write to IN parameter | Use a local variable instead |
| Confusing RETURN clause and RETURN statement | RETURN NUMBER (clause) vs RETURN v_val (statement) | Clause is in header, statement is in body |
| Forgetting exception handler for SELECT INTO | Raises unhandled NO_DATA_FOUND at runtime | Add EXCEPTION block with WHEN NO_DATA_FOUND |
| Calling procedure in SELECT | `SELECT proc_name() FROM DUAL` fails | Only functions can be used in SQL SELECT |

---

## Quick Revision

```
+---------------------------------------------------+
|              PL/SQL SUBPROGRAMS                    |
+---------------------------------------------------+
|                                                   |
|   +-------------------+   +-------------------+   |
|   |    PROCEDURE      |   |    FUNCTION        |  |
|   +-------------------+   +-------------------+   |
|   | Performs action    |   | Returns a value    |  |
|   | No RETURN value   |   | RETURN mandatory   |  |
|   | IN, OUT, IN OUT   |   | Usually IN only    |  |
|   | Cannot use in SQL |   | Can use in SQL     |  |
|   | EXEC / BEGIN-END  |   | := / SELECT / expr |  |
|   +-------------------+   +-------------------+   |
|                                                   |
+---------------------------------------------------+
```

| Topic | Key Point |
|-------|-----------|
| Procedure | Named block that performs an action, no return value |
| Function | Named block that computes and returns exactly one value |
| IN parameter | Default mode, read-only, passes value into subprogram |
| OUT parameter | Write-only, passes value from subprogram to caller |
| IN OUT parameter | Read-write, value flows both directions |
| RETURN clause | In function header, declares return datatype |
| RETURN statement | In function body, sends value back to caller |
| OR REPLACE | Allows recompilation without dropping first |
| EXEC | SQL*Plus command to execute a procedure |
| DROP PROCEDURE | Permanently removes a procedure from the database |
| DROP FUNCTION | Permanently removes a function from the database |
| Parameter size | Never specify size in formal parameters |
| Function in SQL | Allowed if function has no DML and no OUT params |
| Stored subprogram | Compiled and stored permanently in the database |

### One-Line Summary

> **Procedures DO things. Functions CALCULATE things and return a value.**
