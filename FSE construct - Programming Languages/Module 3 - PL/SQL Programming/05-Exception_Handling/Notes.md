# PL/SQL Exception Handling

---

## Introduction

Every program can encounter errors during execution. A number might be divided by zero, a query might return no results, or a user might enter invalid data. If these errors are not handled, the program crashes and the user sees an unfriendly Oracle error message.

PL/SQL provides a structured mechanism called **Exception Handling** to catch and manage these runtime errors gracefully. Instead of crashing, the program can display a meaningful message, log the error, or take corrective action. Exception handling is one of the most important topics in PL/SQL for building robust, production-ready database applications.

---

## Definition

An **exception** is an error condition that disrupts the normal flow of execution in a PL/SQL block. When an exception occurs (is "raised"), control immediately transfers from the executable section (`BEGIN`) to the exception-handling section (`EXCEPTION`) of the block.

**Exception Handling** is the process of:
1. **Detecting** the error (the exception is raised)
2. **Catching** the error (matching it to a handler)
3. **Responding** to the error (executing recovery code)

---

## Why It Is Used

| Problem Without Exception Handling | Solution With Exception Handling |
|---|---|
| Program crashes on runtime error | Program continues gracefully |
| User sees raw Oracle error codes | User sees meaningful messages |
| No control over error recovery | Developer controls recovery logic |
| Data may be left in inconsistent state | Transactions can be rolled back safely |
| Debugging is difficult | Error details can be logged |

**Key reasons to use exception handling:**
- Prevent abrupt program termination
- Provide user-friendly error messages
- Protect data integrity with proper rollbacks
- Separate error-handling logic from business logic
- Log errors for debugging and auditing
- Build robust, production-quality applications

---

## Types of Exceptions

```
                    +---------------------+
                    |     EXCEPTIONS      |
                    +---------------------+
                           /    \
                          /      \
              +-----------+      +-------------+
              |  System   |      |    User     |
              |  Defined  |      |   Defined   |
              +-----------+      +-------------+
                /       \               |
               /         \              |
     +----------+  +-----------+  +-------------+
     |Predefined|  |Non-Predef.|  |  Declared   |
     |  (Named) |  | (Unnamed) |  |  by User    |
     +----------+  +-----------+  +-------------+
```

| Type | Description | Example |
|---|---|---|
| **Predefined (System-Defined)** | Named exceptions built into Oracle. Automatically raised by the system. | `NO_DATA_FOUND`, `ZERO_DIVIDE` |
| **Non-Predefined (System-Defined)** | Oracle errors that exist but have no predefined name. Must be associated using `PRAGMA EXCEPTION_INIT`. | ORA-00060 (Deadlock) |
| **User-Defined** | Custom exceptions declared by the programmer using the `EXCEPTION` keyword. Raised explicitly using `RAISE`. | `insufficient_balance` |

---

## Predefined Exceptions - Complete Reference Table

| Oracle Error Code | Exception Name | Description |
|---|---|---|
| ORA-01476 | `ZERO_DIVIDE` | Attempted to divide a number by zero |
| ORA-01403 | `NO_DATA_FOUND` | SELECT INTO returned no rows |
| ORA-01422 | `TOO_MANY_ROWS` | SELECT INTO returned more than one row |
| ORA-06502 | `VALUE_ERROR` | Arithmetic, conversion, truncation, or size constraint error |
| ORA-01722 | `INVALID_NUMBER` | Conversion of character string to number failed in SQL |
| ORA-00001 | `DUP_VAL_ON_INDEX` | Attempted to insert a duplicate value into a unique index column |
| ORA-06511 | `CURSOR_ALREADY_OPEN` | Attempted to open a cursor that is already open |
| ORA-01017 | `LOGIN_DENIED` | Invalid username or password during login |
| ORA-06501 | `PROGRAM_ERROR` | PL/SQL internal error |
| ORA-06500 | `STORAGE_ERROR` | PL/SQL ran out of memory |
| ORA-00051 | `TIMEOUT_ON_RESOURCE` | Timeout occurred while waiting for a resource |
| ORA-06504 | `ROWTYPE_MISMATCH` | Host cursor variable and PL/SQL cursor variable have incompatible row types |
| ORA-01001 | `INVALID_CURSOR` | Illegal cursor operation (e.g., closing a cursor that is not open) |
| ORA-06592 | `CASE_NOT_FOUND` | No matching WHEN clause found in a CASE statement with no ELSE |

---

## Syntax

### Basic Exception Handling Structure

```sql
DECLARE
    -- variable declarations
BEGIN
    -- executable statements
    -- (exception may be raised here)
EXCEPTION
    WHEN exception_name_1 THEN
        -- handler for exception 1
    WHEN exception_name_2 THEN
        -- handler for exception 2
    WHEN OTHERS THEN
        -- handler for all other exceptions
END;
/
```

### Handling Predefined Exceptions

```sql
DECLARE
    v_result NUMBER;
BEGIN
    v_result := 100 / 0;
EXCEPTION
    WHEN ZERO_DIVIDE THEN
        DBMS_OUTPUT.PUT_LINE('Cannot divide by zero.');
END;
/
```

### User-Defined Exception

```sql
DECLARE
    exception_name EXCEPTION;        -- Step 1: Declare
BEGIN
    IF some_condition THEN
        RAISE exception_name;        -- Step 2: Raise
    END IF;
EXCEPTION
    WHEN exception_name THEN         -- Step 3: Handle
        DBMS_OUTPUT.PUT_LINE('Custom error occurred.');
END;
/
```

### RAISE_APPLICATION_ERROR

```sql
BEGIN
    IF some_condition THEN
        RAISE_APPLICATION_ERROR(error_number, error_message);
    END IF;
END;
/
```

**Rules for RAISE_APPLICATION_ERROR:**

| Parameter | Rule |
|---|---|
| `error_number` | Must be between **-20000** and **-20999** |
| `error_message` | Character string up to **2048 bytes** |
| Third parameter (optional) | `TRUE` keeps error stack, `FALSE` (default) replaces it |

### WHEN OTHERS with SQLCODE and SQLERRM

```sql
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error Code: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('Error Message: ' || SQLERRM);
END;
/
```

| Function | Returns | Example Output |
|---|---|---|
| `SQLCODE` | Numeric error code | `-1476` |
| `SQLERRM` | Error message string | `ORA-01476: divisor is equal to zero` |
| `SQLERRM(code)` | Message for a specific error code | `SQLERRM(-1403)` returns the NO_DATA_FOUND message |

---

## Explanation

### How Exception Handling Works

1. An exception is **raised** when a runtime error occurs (automatically for predefined exceptions) or when the programmer explicitly uses `RAISE` or `RAISE_APPLICATION_ERROR`.

2. When an exception is raised, execution of the current `BEGIN` block **stops immediately**. No further statements in that block are executed.

3. Control passes to the `EXCEPTION` section of the **current block**.

4. Oracle searches the handlers **top to bottom** for a matching `WHEN` clause.

5. If a match is found, that handler executes. After the handler completes, control passes to the **enclosing block** (not back to the statement that caused the error).

6. If no match is found and there is no `WHEN OTHERS`, the exception **propagates** to the enclosing block.

### Exception Propagation

When an exception is not handled in the current block, it propagates outward:

```
+-----------------------------------------------+
|  OUTER BLOCK                                  |
|  BEGIN                                        |
|    +---------------------------------------+  |
|    |  INNER BLOCK                         |  |
|    |  BEGIN                               |  |
|    |      Statement causes exception  <---+--+-- Exception raised here
|    |  EXCEPTION                           |  |
|    |      No matching handler found   ----+--+-- Propagates outward
|    |  END;                                |  |
|    +---------------------------------------+  |
|  EXCEPTION                                    |
|      WHEN exception_name THEN  <--------------+-- Caught here
|          Handler executes                     |
|  END;                                         |
+-----------------------------------------------+
```

**Propagation Rules:**
- If the inner block has no handler for the exception, it propagates to the outer block.
- If the outer block also has no handler, it propagates to the calling environment (SQL*Plus, application).
- An **unhandled exception** results in an error returned to the calling environment.

### Nested Exception Handling

```sql
DECLARE
    v_outer NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Outer block started.');

    BEGIN
        v_outer := 100 / 0;
    EXCEPTION
        WHEN ZERO_DIVIDE THEN
            DBMS_OUTPUT.PUT_LINE('Inner: Division by zero caught.');
    END;

    DBMS_OUTPUT.PUT_LINE('Outer block continues after inner block.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Outer: Unexpected error caught.');
END;
/
```

**Key Point:** When an exception is handled in the inner block, the outer block **continues execution** normally after the inner block's `END`.

---

## Execution Flow

### Normal Execution (No Exception)

```
START
  |
  v
DECLARE
  |
  v
BEGIN
  |
  v
Statement 1  -----> Executes successfully
  |
  v
Statement 2  -----> Executes successfully
  |
  v
Statement 3  -----> Executes successfully
  |
  v
END;  (EXCEPTION section is SKIPPED)
  |
  v
FINISH
```

### Execution With Exception (Handled)

```
START
  |
  v
DECLARE
  |
  v
BEGIN
  |
  v
Statement 1  -----> Executes successfully
  |
  v
Statement 2  -----> EXCEPTION RAISED!
  |                       |
  X                       |  (Statement 3 is SKIPPED)
  |                       |
  |                       v
  |               EXCEPTION section
  |                       |
  |                       v
  |               WHEN matching_exception THEN
  |                       |
  |                       v
  |               Handler executes
  |                       |
  +<----------------------+
  |
  v
END;
  |
  v
FINISH (Control goes to enclosing block)
```

### Exception Propagation Flow

```
+--------------------------------------------------+
| CALLING ENVIRONMENT (SQL*Plus / Application)      |
|                                                   |
|   +--------------------------------------------+ |
|   | OUTER BLOCK                                | |
|   |                                            | |
|   |   +--------------------------------------+ | |
|   |   | INNER BLOCK                          | | |
|   |   |                                      | | |
|   |   |   Exception Raised!                  | | |
|   |   |          |                           | | |
|   |   |          v                           | | |
|   |   |   Handler found? --YES--> Handle it  | | |
|   |   |          |                    |      | | |
|   |   |          NO              Continue    | | |
|   |   |          |              outer block  | | |
|   |   +----------|--------------------------+ | |
|   |              |                             | |
|   |              v                             | |
|   |       Handler found? --YES--> Handle it    | |
|   |              |                    |        | |
|   |              NO               END block    | |
|   |              |                             | |
|   +--------------|-----------------------------+ |
|                  |                               |
|                  v                               |
|        UNHANDLED EXCEPTION                       |
|        Error returned to caller                  |
+--------------------------------------------------+
```

---

## Real Life Example

### ATM Withdrawal - Insufficient Balance

Consider an ATM machine where a user tries to withdraw money:

**Scenario:**
- Account balance: Rs. 5,000
- Withdrawal amount: Rs. 8,000
- Since withdrawal > balance, the ATM should reject the transaction

**Without Exception Handling:**
The program would crash or silently allow an invalid transaction, potentially leading to a negative balance.

**With Exception Handling (User-Defined Exception):**

```
USER inserts card and enters PIN
  |
  v
USER requests withdrawal of Rs. 8,000
  |
  v
SYSTEM checks: Is 8,000 > 5,000?
  |
  YES --> RAISE insufficient_balance exception
  |           |
  |           v
  |       EXCEPTION HANDLER activates
  |           |
  |           v
  |       Display: "Insufficient balance.
  |                  Available: Rs. 5,000"
  |           |
  |           v
  |       Transaction CANCELLED
  |       Balance UNCHANGED (Rs. 5,000)
  |
  NO --> Deduct amount
  |       New balance = Rs. 5,000 - withdrawal
  |       Dispense cash
  v
END TRANSACTION
```

This maps directly to the `UserDefinedException.sql` program in this folder.

---

## Code Explanation

### Program 1: ZeroDivide.sql

| Line | Explanation |
|---|---|
| `v_numerator NUMBER := 50;` | Declares the numerator with value 50 |
| `v_denominator NUMBER := 0;` | Declares the denominator with value 0 (will cause error) |
| `v_result := v_numerator / v_denominator;` | Attempts division: 50/0 raises ZERO_DIVIDE |
| `WHEN ZERO_DIVIDE THEN` | Catches the specific ZERO_DIVIDE exception |
| `SQLCODE` | Displays the numeric error code (-1476) |
| `SQLERRM` | Displays the full error message text |

**Expected Output:**
```
Error: Division by zero is not allowed.
SQLCODE: -1476
SQLERRM: ORA-01476: divisor is equal to zero
```

---

### Program 2: NoDataFound.sql

| Line | Explanation |
|---|---|
| `v_first_name HR.EMPLOYEES.FIRST_NAME%TYPE;` | Declares variable with same type as the column |
| `WHERE EMPLOYEE_ID = 9999` | Searches for a non-existent employee |
| `SELECT ... INTO` | Attempts to store result in variable, but no row exists |
| `WHEN NO_DATA_FOUND THEN` | Catches the exception when SELECT INTO returns zero rows |

**Expected Output:**
```
Error: No employee found with the given ID.
SQLCODE: 100
SQLERRM: ORA-01403: no data found
```

> **Note:** SQLCODE for NO_DATA_FOUND is `100` (not a negative number), which is a special case in Oracle.

---

### Program 3: TooManyRows.sql

| Line | Explanation |
|---|---|
| `v_first_name HR.EMPLOYEES.FIRST_NAME%TYPE;` | Single scalar variable can hold only one value |
| `SELECT FIRST_NAME INTO v_first_name FROM HR.EMPLOYEES;` | No WHERE clause means all rows are returned |
| `WHEN TOO_MANY_ROWS THEN` | Catches the error when SELECT INTO returns multiple rows |

**Expected Output:**
```
Error: Query returned more than one row.
Use a cursor to handle multiple rows.
SQLCODE: -1422
SQLERRM: ORA-01422: exact fetch returns more than requested number of rows
```

---

### Program 4: ValueError.sql

| Line | Explanation |
|---|---|
| `v_code VARCHAR2(5);` | Variable can hold at most 5 characters |
| `v_code := 'ABCDEFGHIJ';` | Assigns a 10-character string to a 5-character variable |
| `WHEN VALUE_ERROR THEN` | Catches the size constraint violation |

**Expected Output:**
```
Error: Value is too large or has a data type mismatch.
SQLCODE: -6502
SQLERRM: ORA-06502: PL/SQL: numeric or value error: character string buffer too small
```

---

### Program 5: UserDefinedException.sql

| Step | Line | Explanation |
|---|---|---|
| **Declare** | `insufficient_balance EXCEPTION;` | Creates a custom exception name |
| **Set values** | `v_balance := 5000; v_withdrawal_amount := 8000;` | Balance is less than withdrawal |
| **Check condition** | `IF v_withdrawal_amount > v_balance THEN` | Business logic check |
| **Raise** | `RAISE insufficient_balance;` | Explicitly triggers the exception |
| **Handle** | `WHEN insufficient_balance THEN` | Catches the custom exception |

**Three steps for user-defined exceptions:**

```
Step 1: DECLARE  -->  exception_name EXCEPTION;
Step 2: RAISE    -->  RAISE exception_name;
Step 3: HANDLE   -->  WHEN exception_name THEN ...
```

**Expected Output:**
```
Current Balance  : 5000
Withdrawal Amount: 8000
Error: Insufficient balance for this withdrawal.
Please enter an amount less than or equal to 5000
```

---

### Program 6: RaiseApplicationError.sql

| Line | Explanation |
|---|---|
| `v_salary NUMBER := -5000;` | Sets salary to an invalid negative value |
| `IF v_salary < 0 THEN` | Business rule: salary must not be negative |
| `RAISE_APPLICATION_ERROR(-20001, ...)` | Raises a custom Oracle error with code -20001 |
| `WHEN OTHERS THEN` | Catches the application error (no predefined name exists) |
| `SQLCODE` | Returns -20001 (the custom error code) |
| `SQLERRM` | Returns the custom message with ORA-20001 prefix |

**Difference between RAISE and RAISE_APPLICATION_ERROR:**

| Feature | RAISE | RAISE_APPLICATION_ERROR |
|---|---|---|
| Used for | User-defined exceptions | Custom Oracle errors |
| Error number | No error number assigned | -20000 to -20999 |
| Error message | No custom message (generic) | Custom message up to 2048 bytes |
| Can be used in | PL/SQL blocks only | PL/SQL blocks, triggers, stored procedures |
| Visible to caller | Only within the PL/SQL block | Propagates to calling application |
| SQLCODE value | +1 (user-defined) | -20000 to -20999 |

**Expected Output:**
```
Validating Salary: -5000
Error Code   : -20001
Error Message: ORA-20001: Salary cannot be negative. Entered value: -5000
```

---

## Interview Questions

**Q1: What is an exception in PL/SQL?**
An exception is a runtime error or warning condition that disrupts the normal flow of execution in a PL/SQL block. When an exception occurs, control transfers to the EXCEPTION section of the block.

**Q2: What are the types of exceptions in PL/SQL?**
There are three types: (1) Predefined system exceptions (named, like NO_DATA_FOUND), (2) Non-predefined system exceptions (unnamed Oracle errors), and (3) User-defined exceptions (declared by the programmer).

**Q3: What is the difference between SQLCODE and SQLERRM?**
SQLCODE returns the numeric error code (e.g., -1476). SQLERRM returns the corresponding error message string (e.g., "ORA-01476: divisor is equal to zero"). SQLCODE returns 0 when no exception has occurred.

**Q4: What is the purpose of WHEN OTHERS THEN?**
WHEN OTHERS is a catch-all exception handler that handles any exception not explicitly named in previous WHEN clauses. It must always be the **last** handler in the EXCEPTION section. It is often used with SQLCODE and SQLERRM to log unexpected errors.

**Q5: What is the range of error numbers for RAISE_APPLICATION_ERROR?**
The error number must be between -20000 and -20999. This range is reserved by Oracle for user-defined application errors. Using any number outside this range will cause a runtime error.

**Q6: What is the difference between RAISE and RAISE_APPLICATION_ERROR?**
RAISE is used to explicitly raise a declared user-defined exception and does not accept a custom error message. RAISE_APPLICATION_ERROR creates a custom Oracle error with a specific error number (-20000 to -20999) and a custom message, and it can propagate the error to the calling environment.

**Q7: What happens if an exception is not handled in a PL/SQL block?**
The exception propagates to the enclosing block. If there is no enclosing block or no handler is found at any level, the unhandled exception is returned to the calling environment (e.g., SQL*Plus), which displays the Oracle error message.

**Q8: Can we have multiple exception handlers in a single EXCEPTION section?**
Yes. You can have multiple WHEN clauses for different exceptions. They are checked in order from top to bottom. The first matching handler executes, and the rest are skipped.

**Q9: What is exception propagation?**
Exception propagation is the process by which an unhandled exception in an inner block is automatically passed to the enclosing (outer) block for handling. This continues outward until either a handler is found or the exception reaches the calling environment.

**Q10: Can we handle multiple exceptions in a single WHEN clause?**
Yes. You can combine exceptions using the `OR` keyword:
```sql
WHEN NO_DATA_FOUND OR TOO_MANY_ROWS THEN
    DBMS_OUTPUT.PUT_LINE('Query error occurred.');
```

**Q11: What is the SQLCODE value for NO_DATA_FOUND?**
The SQLCODE for NO_DATA_FOUND is `+100`, which is an exception to the general rule where Oracle error codes are negative. For user-defined exceptions raised with RAISE, SQLCODE returns `+1`.

**Q12: What happens to statements after the line that raised the exception?**
They are skipped. When an exception is raised, control immediately jumps to the EXCEPTION section. The remaining statements in the BEGIN block are never executed.

---

## Viva Questions

**Q1: Write the syntax to declare and raise a user-defined exception.**
```sql
DECLARE
    my_exception EXCEPTION;
BEGIN
    RAISE my_exception;
EXCEPTION
    WHEN my_exception THEN
        DBMS_OUTPUT.PUT_LINE('Exception handled.');
END;
/
```

**Q2: What will happen if you use RAISE_APPLICATION_ERROR with error number -19999?**
It will cause a runtime error because -19999 is outside the valid range. RAISE_APPLICATION_ERROR only accepts error numbers from -20000 to -20999.

**Q3: Can WHEN OTHERS be placed before other exception handlers?**
No. WHEN OTHERS must always be the **last** handler in the EXCEPTION section. Placing it before other handlers will cause a compilation error because subsequent handlers would be unreachable.

**Q4: What is the difference between NO_DATA_FOUND and TOO_MANY_ROWS?**
NO_DATA_FOUND (ORA-01403) is raised when a SELECT INTO returns zero rows. TOO_MANY_ROWS (ORA-01422) is raised when a SELECT INTO returns more than one row. Both relate to SELECT INTO but represent opposite problems.

**Q5: What is PRAGMA EXCEPTION_INIT used for?**
It associates a user-defined exception name with a specific Oracle error number. This allows you to handle non-predefined Oracle errors by name instead of using WHEN OTHERS.
```sql
DECLARE
    e_deadlock EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_deadlock, -60);
```

**Q6: Can an EXCEPTION section contain DML statements like INSERT or UPDATE?**
Yes. The EXCEPTION section can contain any valid PL/SQL statements, including INSERT, UPDATE, DELETE, COMMIT, ROLLBACK, and calls to procedures. This is commonly used for error logging.

**Q7: What is the default SQLCODE value when no exception occurs?**
When no exception has occurred, SQLCODE returns `0`.

**Q8: How does exception handling work in nested blocks?**
If an inner block raises an exception and handles it, the outer block continues normally. If the inner block does not handle the exception, it propagates to the outer block. Each block can have its own EXCEPTION section.

**Q9: Can we re-raise an exception after handling it?**
Yes. Inside an exception handler, you can use `RAISE;` (without an exception name) to re-raise the same exception so it propagates to the outer block.
```sql
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Logging error...');
        RAISE;  -- re-raises to outer block
```

**Q10: What is the maximum length of the error message in RAISE_APPLICATION_ERROR?**
The error message can be up to **2048 bytes** long.

**Q11: What exception is raised when you try to open a cursor that is already open?**
The `CURSOR_ALREADY_OPEN` exception (ORA-06511) is raised.

**Q12: Can you use RAISE_APPLICATION_ERROR inside a trigger?**
Yes. RAISE_APPLICATION_ERROR is commonly used in triggers to prevent invalid DML operations and return a meaningful error message to the calling application.

---

## Common Mistakes

| Mistake | Problem | Correction |
|---|---|---|
| Placing `WHEN OTHERS` before specific handlers | Compilation error; subsequent handlers are unreachable | Always place `WHEN OTHERS` as the **last** handler |
| Using RAISE_APPLICATION_ERROR with error number outside -20000 to -20999 | Runtime error | Use only numbers in the range -20000 to -20999 |
| Forgetting to declare a user-defined exception | Compilation error: identifier not declared | Declare it in the DECLARE section: `my_exc EXCEPTION;` |
| Using `RAISE exception_name` without declaring it | Compilation error | Declare the exception before raising it |
| Expecting execution to continue after the error line | Remaining statements in BEGIN are skipped | Place recovery logic in the EXCEPTION handler |
| Using SQLCODE/SQLERRM in SQL statements directly | Not allowed; they are PL/SQL functions | Assign to a variable first, then use in SQL |
| Writing `WHEN ZERO_DIVISION` instead of `WHEN ZERO_DIVIDE` | Compilation error; wrong exception name | Use exact predefined names: `ZERO_DIVIDE` |
| Handling NO_DATA_FOUND in a cursor FOR loop | NOT raised in cursor FOR loops (only in SELECT INTO) | Use `%NOTFOUND` attribute with explicit cursors |
| Not handling exceptions in production code | Unhandled exceptions crash the application | Always include at least a `WHEN OTHERS` handler for logging |
| Using RAISE in the DECLARE section | Syntax error; RAISE can only be used in BEGIN or EXCEPTION | Move the RAISE statement inside the BEGIN block |

---

## Quick Revision

### Exception Handling Structure

```
DECLARE  -->  EXCEPTION declaration (user-defined)
BEGIN    -->  Executable code (exceptions raised here)
EXCEPTION --> Handlers (WHEN ... THEN)
END;     -->  Block terminates
```

### Three Steps for User-Defined Exceptions

| Step | Keyword | Section |
|---|---|---|
| 1. Declare | `exception_name EXCEPTION;` | DECLARE |
| 2. Raise | `RAISE exception_name;` | BEGIN |
| 3. Handle | `WHEN exception_name THEN` | EXCEPTION |

### Key Predefined Exceptions - Quick Reference

| Exception | When Raised |
|---|---|
| `ZERO_DIVIDE` | Division by zero |
| `NO_DATA_FOUND` | SELECT INTO returns 0 rows |
| `TOO_MANY_ROWS` | SELECT INTO returns 2+ rows |
| `VALUE_ERROR` | Size constraint or type mismatch |
| `DUP_VAL_ON_INDEX` | Duplicate unique key |
| `INVALID_NUMBER` | String-to-number conversion fails in SQL |
| `CURSOR_ALREADY_OPEN` | Reopening an already open cursor |

### RAISE vs RAISE_APPLICATION_ERROR

| Feature | RAISE | RAISE_APPLICATION_ERROR |
|---|---|---|
| Error Number | +1 (generic) | -20000 to -20999 |
| Custom Message | No | Yes (up to 2048 bytes) |
| Propagates to Caller | No (stays in PL/SQL) | Yes |

### SQLCODE Special Values

| Value | Meaning |
|---|---|
| `0` | No exception |
| `+1` | User-defined exception |
| `+100` | NO_DATA_FOUND |
| Negative | Oracle error (e.g., -1476 for ZERO_DIVIDE) |

### Exception Propagation Rule

```
Inner Block (no handler) --> Outer Block (no handler) --> Calling Environment (ERROR)
Inner Block (has handler) --> Outer Block CONTINUES normally
```

### Files in This Section

| File | Concept Demonstrated |
|---|---|
| `ZeroDivide.sql` | ZERO_DIVIDE predefined exception |
| `NoDataFound.sql` | NO_DATA_FOUND predefined exception |
| `TooManyRows.sql` | TOO_MANY_ROWS predefined exception |
| `ValueError.sql` | VALUE_ERROR predefined exception |
| `UserDefinedException.sql` | User-defined exception with RAISE |
| `RaiseApplicationError.sql` | RAISE_APPLICATION_ERROR procedure |

---
