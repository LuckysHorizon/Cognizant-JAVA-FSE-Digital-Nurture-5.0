# PL/SQL Programming - Resources and Quick Reference

---

## Table of Contents

1. [Official Oracle Documentation](#official-oracle-documentation)
2. [Online Practice Platforms](#online-practice-platforms)
3. [Recommended Books](#recommended-books)
4. [Video Resources](#video-resources)
5. [Quick Reference Tables](#quick-reference-tables)
   - [Data Types](#data-types)
   - [Built-in Functions](#built-in-functions)
   - [Exception Types](#exception-types)
   - [Cursor Attributes](#cursor-attributes)
   - [Trigger Types](#trigger-types)
6. [PL/SQL Reserved Keywords](#plsql-reserved-keywords)
7. [Common Oracle Error Codes](#common-oracle-error-codes)
8. [Interview Quick Reference](#interview-quick-reference)

---

## Official Oracle Documentation

| Resource | URL |
|----------|-----|
| PL/SQL Language Reference (21c) | https://docs.oracle.com/en/database/oracle/oracle-database/21/lnpls/ |
| PL/SQL Language Reference (19c) | https://docs.oracle.com/en/database/oracle/oracle-database/19/lnpls/ |
| SQL Language Reference (21c) | https://docs.oracle.com/en/database/oracle/oracle-database/21/sqlrf/ |
| PL/SQL Packages and Types Reference | https://docs.oracle.com/en/database/oracle/oracle-database/21/arpls/ |
| Database Concepts Guide | https://docs.oracle.com/en/database/oracle/oracle-database/21/cncpt/ |
| Database Development Guide | https://docs.oracle.com/en/database/oracle/oracle-database/21/adfns/ |
| HR Sample Schema Documentation | https://docs.oracle.com/en/database/oracle/oracle-database/21/comsc/ |
| Error Messages Reference | https://docs.oracle.com/en/database/oracle/oracle-database/21/errmg/ |

---

## Online Practice Platforms

| Platform | URL | Features |
|----------|-----|----------|
| Oracle Live SQL | https://livesql.oracle.com | Free Oracle database in browser, no installation needed, supports PL/SQL, save and share scripts |
| Oracle APEX | https://apex.oracle.com | Free workspace with Oracle database access, build web applications with PL/SQL |
| db-fiddle | https://www.db-fiddle.com | Quick SQL testing in browser, supports multiple database engines |
| SQL Fiddle | http://sqlfiddle.com | Online SQL testing tool, supports Oracle dialect |
| Programiz SQL Online | https://www.programiz.com/sql/online-compiler | Simple online SQL compiler for quick testing |
| OneCompiler | https://onecompiler.com/plsql | Online PL/SQL compiler with instant execution |
| Oracle Database XE | https://www.oracle.com/database/technologies/xe-downloads.html | Free local Oracle database installation for offline practice |

### Recommended Setup for Beginners

```
1. Start with Oracle Live SQL    --> No installation, works in any browser
2. Move to Oracle SQL Developer  --> Professional GUI tool for serious practice
3. Install Oracle XE locally     --> Full database for advanced projects
```

---

## Recommended Books

| Book Title | Author(s) | Level | Focus Area |
|------------|-----------|-------|------------|
| Oracle PL/SQL Programming | Steven Feuerstein, Bill Pribyl | Beginner to Advanced | Comprehensive PL/SQL reference |
| Oracle PL/SQL by Example | Benjamin Rosenzweig, Elena Silvestrova Rakhimov | Beginner | Learn-by-example approach |
| Oracle Database 12c PL/SQL Programming | Michael McLaughlin | Intermediate | Modern PL/SQL features |
| Murach's Oracle SQL and PL/SQL | Joel Murach | Beginner | Structured self-teaching |
| Expert Oracle PL/SQL | Ron Hardman, Michael McLaughlin | Advanced | Performance and best practices |
| Beginning Oracle PL/SQL | Donald Bales | Beginner | Step-by-step introduction |
| Oracle PL/SQL Best Practices | Steven Feuerstein | Intermediate | Code quality and standards |
| Oracle SQL by Example | Alice Rischert | Beginner | SQL foundation for PL/SQL |

---

## Video Resources

| Resource | Platform | Type | URL |
|----------|----------|------|-----|
| Oracle PL/SQL Fundamentals | Oracle Learning Library | Official | https://education.oracle.com |
| PL/SQL Tutorial for Beginners | YouTube (freeCodeCamp) | Free Course | https://www.youtube.com |
| Oracle PL/SQL Complete Course | Udemy | Paid Course | https://www.udemy.com |
| Database Programming with PL/SQL | Oracle Academy | Academic | https://academy.oracle.com |
| PL/SQL by Practical Examples | YouTube (Caleb Curry) | Free Tutorial | https://www.youtube.com |
| Oracle Database Administration | Coursera | Certification | https://www.coursera.org |

---

## Quick Reference Tables

### Data Types

#### Scalar Data Types

| Data Type | Description | Example Declaration |
|-----------|-------------|---------------------|
| `NUMBER` | Numeric values (integers and decimals) | `v_sal NUMBER := 50000;` |
| `NUMBER(p,s)` | Numeric with precision p and scale s | `v_price NUMBER(8,2) := 199.99;` |
| `INTEGER` | Whole numbers (subtype of NUMBER) | `v_count INTEGER := 0;` |
| `PLS_INTEGER` | Integer optimized for PL/SQL performance | `v_idx PLS_INTEGER := 1;` |
| `BINARY_INTEGER` | Integer stored in binary format | `v_num BINARY_INTEGER := 100;` |
| `BINARY_FLOAT` | Single-precision floating point | `v_float BINARY_FLOAT := 3.14f;` |
| `BINARY_DOUBLE` | Double-precision floating point | `v_double BINARY_DOUBLE := 3.14d;` |
| `CHAR(n)` | Fixed-length character string | `v_code CHAR(5) := 'ABC';` |
| `VARCHAR2(n)` | Variable-length character string | `v_name VARCHAR2(50) := 'John';` |
| `NCHAR(n)` | Fixed-length national character string | `v_nchar NCHAR(10) := 'Text';` |
| `NVARCHAR2(n)` | Variable-length national character string | `v_nvar NVARCHAR2(50) := 'Text';` |
| `LONG` | Variable-length string up to 32,760 bytes | `v_text LONG;` |
| `RAW(n)` | Raw binary data | `v_raw RAW(100);` |
| `BOOLEAN` | Logical values (TRUE, FALSE, NULL) | `v_flag BOOLEAN := TRUE;` |
| `DATE` | Date and time values | `v_date DATE := SYSDATE;` |
| `TIMESTAMP` | Date and time with fractional seconds | `v_ts TIMESTAMP := SYSTIMESTAMP;` |
| `INTERVAL YEAR TO MONTH` | Period in years and months | `v_period INTERVAL YEAR TO MONTH;` |
| `INTERVAL DAY TO SECOND` | Period in days, hours, minutes, seconds | `v_dur INTERVAL DAY TO SECOND;` |

#### Composite Data Types

| Data Type | Description | Example Declaration |
|-----------|-------------|---------------------|
| `RECORD` | Group of related fields (like a row) | `TYPE emp_rec IS RECORD (...);` |
| `TABLE` (Associative Array) | Index-by table for key-value pairs | `TYPE num_tab IS TABLE OF NUMBER INDEX BY PLS_INTEGER;` |
| `VARRAY` | Variable-size array with fixed upper bound | `TYPE name_arr IS VARRAY(10) OF VARCHAR2(50);` |
| `NESTED TABLE` | Unbounded collection stored in database | `TYPE num_nt IS TABLE OF NUMBER;` |

#### Reference and LOB Data Types

| Data Type | Description | Example Declaration |
|-----------|-------------|---------------------|
| `REF CURSOR` | Pointer to a result set | `TYPE rc IS REF CURSOR;` |
| `SYS_REFCURSOR` | Predefined weak ref cursor type | `v_cur SYS_REFCURSOR;` |
| `CLOB` | Character Large Object (up to 4 GB) | `v_clob CLOB;` |
| `BLOB` | Binary Large Object (up to 4 GB) | `v_blob BLOB;` |
| `BFILE` | Pointer to external binary file | `v_file BFILE;` |

#### Special Attributes

| Attribute | Description | Example |
|-----------|-------------|---------|
| `%TYPE` | Inherits data type from a column or variable | `v_sal HR.EMPLOYEES.SALARY%TYPE;` |
| `%ROWTYPE` | Inherits row structure from a table or cursor | `v_emp HR.EMPLOYEES%ROWTYPE;` |

---

### Built-in Functions

#### String Functions

| Function | Description | Example | Result |
|----------|-------------|---------|--------|
| `LENGTH(str)` | Returns length of string | `LENGTH('Hello')` | `5` |
| `UPPER(str)` | Converts to uppercase | `UPPER('hello')` | `'HELLO'` |
| `LOWER(str)` | Converts to lowercase | `LOWER('HELLO')` | `'hello'` |
| `INITCAP(str)` | Capitalizes first letter of each word | `INITCAP('hello world')` | `'Hello World'` |
| `SUBSTR(str,pos,len)` | Extracts substring | `SUBSTR('Hello',1,3)` | `'Hel'` |
| `INSTR(str,sub)` | Finds position of substring | `INSTR('Hello','lo')` | `4` |
| `TRIM(str)` | Removes leading/trailing spaces | `TRIM('  Hi  ')` | `'Hi'` |
| `LTRIM(str)` | Removes leading spaces/characters | `LTRIM('  Hi')` | `'Hi'` |
| `RTRIM(str)` | Removes trailing spaces/characters | `RTRIM('Hi  ')` | `'Hi'` |
| `LPAD(str,len,pad)` | Left-pads string to length | `LPAD('Hi',5,'*')` | `'***Hi'` |
| `RPAD(str,len,pad)` | Right-pads string to length | `RPAD('Hi',5,'*')` | `'Hi***'` |
| `REPLACE(str,old,new)` | Replaces occurrences | `REPLACE('Hello','l','r')` | `'Herro'` |
| `TRANSLATE(str,from,to)` | Character-by-character replacement | `TRANSLATE('abc','abc','xyz')` | `'xyz'` |
| `CONCAT(str1,str2)` | Concatenates two strings | `CONCAT('Hi',' There')` | `'Hi There'` |
| `REVERSE(str)` | Reverses string | `REVERSE('Hello')` | `'olleH'` |
| `ASCII(char)` | Returns ASCII value | `ASCII('A')` | `65` |
| `CHR(num)` | Returns character for ASCII value | `CHR(65)` | `'A'` |

#### Numeric Functions

| Function | Description | Example | Result |
|----------|-------------|---------|--------|
| `ABS(n)` | Absolute value | `ABS(-15)` | `15` |
| `CEIL(n)` | Rounds up to nearest integer | `CEIL(4.2)` | `5` |
| `FLOOR(n)` | Rounds down to nearest integer | `FLOOR(4.8)` | `4` |
| `ROUND(n,d)` | Rounds to d decimal places | `ROUND(4.567,2)` | `4.57` |
| `TRUNC(n,d)` | Truncates to d decimal places | `TRUNC(4.567,2)` | `4.56` |
| `MOD(m,n)` | Remainder of m divided by n | `MOD(10,3)` | `1` |
| `POWER(m,n)` | m raised to the power n | `POWER(2,3)` | `8` |
| `SQRT(n)` | Square root | `SQRT(25)` | `5` |
| `SIGN(n)` | Returns -1, 0, or 1 | `SIGN(-5)` | `-1` |
| `GREATEST(a,b,c)` | Returns largest value | `GREATEST(10,20,30)` | `30` |
| `LEAST(a,b,c)` | Returns smallest value | `LEAST(10,20,30)` | `10` |

#### Date Functions

| Function | Description | Example | Result |
|----------|-------------|---------|--------|
| `SYSDATE` | Current date and time | `SYSDATE` | `26-JUN-2026` |
| `SYSTIMESTAMP` | Current date/time with timezone | `SYSTIMESTAMP` | `26-JUN-26 12.00.00 AM` |
| `ADD_MONTHS(d,n)` | Adds n months to date | `ADD_MONTHS(SYSDATE,3)` | Date + 3 months |
| `MONTHS_BETWEEN(d1,d2)` | Months between two dates | `MONTHS_BETWEEN(d1,d2)` | Number of months |
| `NEXT_DAY(d,day)` | Next specified weekday | `NEXT_DAY(SYSDATE,'MONDAY')` | Next Monday |
| `LAST_DAY(d)` | Last day of the month | `LAST_DAY(SYSDATE)` | `30-JUN-2026` |
| `ROUND(d,fmt)` | Rounds date to format | `ROUND(SYSDATE,'MONTH')` | `01-JUL-2026` |
| `TRUNC(d,fmt)` | Truncates date to format | `TRUNC(SYSDATE,'MONTH')` | `01-JUN-2026` |
| `EXTRACT(part FROM d)` | Extracts year/month/day | `EXTRACT(YEAR FROM SYSDATE)` | `2026` |
| `TO_DATE(str,fmt)` | Converts string to date | `TO_DATE('26-06-2026','DD-MM-YYYY')` | Date value |

#### Conversion Functions

| Function | Description | Example | Result |
|----------|-------------|---------|--------|
| `TO_CHAR(val,fmt)` | Converts to character string | `TO_CHAR(SYSDATE,'DD-MON-YYYY')` | `'26-JUN-2026'` |
| `TO_NUMBER(str)` | Converts string to number | `TO_NUMBER('100')` | `100` |
| `TO_DATE(str,fmt)` | Converts string to date | `TO_DATE('26-06-2026','DD-MM-YYYY')` | Date value |
| `CAST(val AS type)` | Converts between data types | `CAST('100' AS NUMBER)` | `100` |
| `NVL(val,default)` | Returns default if val is NULL | `NVL(NULL,0)` | `0` |
| `NVL2(val,not_null,null)` | Returns based on NULL check | `NVL2(NULL,'A','B')` | `'B'` |
| `NULLIF(a,b)` | Returns NULL if a equals b | `NULLIF(10,10)` | `NULL` |
| `COALESCE(a,b,c...)` | Returns first non-NULL value | `COALESCE(NULL,NULL,5)` | `5` |
| `DECODE(val,s1,r1,def)` | IF-THEN-ELSE in SQL | `DECODE(1,1,'One','Other')` | `'One'` |

#### Aggregate Functions

| Function | Description | Example |
|----------|-------------|---------|
| `COUNT(col)` | Number of rows | `SELECT COUNT(*) FROM HR.EMPLOYEES;` |
| `SUM(col)` | Sum of values | `SELECT SUM(SALARY) FROM HR.EMPLOYEES;` |
| `AVG(col)` | Average of values | `SELECT AVG(SALARY) FROM HR.EMPLOYEES;` |
| `MAX(col)` | Maximum value | `SELECT MAX(SALARY) FROM HR.EMPLOYEES;` |
| `MIN(col)` | Minimum value | `SELECT MIN(SALARY) FROM HR.EMPLOYEES;` |

---

### Exception Types

#### Predefined (Named) Exceptions

| Exception Name | Oracle Error Code | SQLCODE | Description |
|----------------|-------------------|---------|-------------|
| `NO_DATA_FOUND` | ORA-01403 | -1403 | SELECT INTO returns no rows |
| `TOO_MANY_ROWS` | ORA-01422 | -1422 | SELECT INTO returns more than one row |
| `ZERO_DIVIDE` | ORA-01476 | -1476 | Division by zero attempted |
| `VALUE_ERROR` | ORA-06502 | -6502 | Arithmetic, conversion, or truncation error |
| `INVALID_CURSOR` | ORA-01001 | -1001 | Illegal cursor operation |
| `CURSOR_ALREADY_OPEN` | ORA-06511 | -6511 | Cursor is already open |
| `DUP_VAL_ON_INDEX` | ORA-00001 | -1 | Unique constraint violated |
| `INVALID_NUMBER` | ORA-01722 | -1722 | Conversion of string to number failed |
| `LOGIN_DENIED` | ORA-01017 | -1017 | Invalid username/password |
| `NOT_LOGGED_ON` | ORA-01012 | -1012 | Not connected to Oracle |
| `PROGRAM_ERROR` | ORA-06501 | -6501 | Internal PL/SQL error |
| `STORAGE_ERROR` | ORA-06500 | -6500 | Out of memory |
| `TIMEOUT_ON_RESOURCE` | ORA-00051 | -51 | Timeout waiting for resource |
| `ROWTYPE_MISMATCH` | ORA-06504 | -6504 | Host cursor variable and PL/SQL cursor incompatible |
| `ACCESS_INTO_NULL` | ORA-06530 | -6530 | Assigning values to uninitialized object |
| `COLLECTION_IS_NULL` | ORA-06531 | -6531 | Using NULL collection |
| `SUBSCRIPT_BEYOND_COUNT` | ORA-06533 | -6533 | Index exceeds collection size |
| `SUBSCRIPT_OUTSIDE_LIMIT` | ORA-06532 | -6532 | Index outside valid range |
| `SELF_IS_NULL` | ORA-30625 | -30625 | Calling method on NULL object |

#### Exception Handling Methods

| Method | Syntax | Use Case |
|--------|--------|----------|
| Predefined Exception | `WHEN NO_DATA_FOUND THEN ...` | Handle known Oracle errors by name |
| User-Defined Exception | `DECLARE e EXCEPTION; ... RAISE e;` | Handle custom business rule violations |
| PRAGMA EXCEPTION_INIT | `PRAGMA EXCEPTION_INIT(e, -error_code);` | Associate a name with an Oracle error code |
| RAISE_APPLICATION_ERROR | `RAISE_APPLICATION_ERROR(-20001, 'msg');` | Return custom error to calling environment |
| WHEN OTHERS | `WHEN OTHERS THEN ...` | Catch all unhandled exceptions |
| SQLCODE | `SQLCODE` | Returns the numeric error code |
| SQLERRM | `SQLERRM` | Returns the error message text |

---

### Cursor Attributes

#### Implicit Cursor Attributes

| Attribute | Data Type | Description |
|-----------|-----------|-------------|
| `SQL%FOUND` | BOOLEAN | TRUE if the most recent SQL statement affected one or more rows |
| `SQL%NOTFOUND` | BOOLEAN | TRUE if the most recent SQL statement affected no rows |
| `SQL%ROWCOUNT` | NUMBER | Number of rows affected by the most recent SQL statement |
| `SQL%ISOPEN` | BOOLEAN | Always FALSE for implicit cursors (Oracle closes them automatically) |

#### Explicit Cursor Attributes

| Attribute | Data Type | Description |
|-----------|-----------|-------------|
| `cursor_name%FOUND` | BOOLEAN | TRUE if the most recent FETCH returned a row |
| `cursor_name%NOTFOUND` | BOOLEAN | TRUE if the most recent FETCH did not return a row |
| `cursor_name%ROWCOUNT` | NUMBER | Total number of rows fetched so far |
| `cursor_name%ISOPEN` | BOOLEAN | TRUE if the cursor is currently open |

#### Cursor Attribute Availability

| Attribute | Before OPEN | After OPEN, Before FETCH | After FETCH (data) | After FETCH (no data) | After CLOSE |
|-----------|------------|--------------------------|--------------------|-----------------------|-------------|
| `%FOUND` | Error | NULL | TRUE | FALSE | Error |
| `%NOTFOUND` | Error | NULL | FALSE | TRUE | Error |
| `%ROWCOUNT` | Error | 0 | Row count | Row count | Error |
| `%ISOPEN` | FALSE | TRUE | TRUE | TRUE | FALSE |

---

### Trigger Types

#### By Timing

| Timing | Description | Use Case |
|--------|-------------|----------|
| `BEFORE` | Fires before the triggering DML statement | Data validation, auto-fill columns, enforce business rules before changes |
| `AFTER` | Fires after the triggering DML statement | Audit logging, cascading updates, notifications after changes |
| `INSTEAD OF` | Fires instead of the triggering DML on views | Make complex views updatable, redirect DML to base tables |

#### By Level

| Level | Description | Fires |
|-------|-------------|-------|
| Row-Level (`FOR EACH ROW`) | Fires once for each affected row | Once per row affected by the DML |
| Statement-Level (default) | Fires once for the entire statement | Once regardless of number of rows affected |

#### By Event

| Event | Description | Available Qualifiers |
|-------|-------------|---------------------|
| `INSERT` | Fires on INSERT operations | `:NEW` values available |
| `UPDATE` | Fires on UPDATE operations | `:OLD` and `:NEW` values available |
| `DELETE` | Fires on DELETE operations | `:OLD` values available |

#### Trigger Qualifier Reference

| Qualifier | INSERT | UPDATE | DELETE |
|-----------|--------|--------|--------|
| `:NEW.column` | New value being inserted | New value after update | NULL (not available) |
| `:OLD.column` | NULL (not available) | Old value before update | Value being deleted |
| `INSERTING` | TRUE | FALSE | FALSE |
| `UPDATING` | FALSE | TRUE | FALSE |
| `DELETING` | FALSE | FALSE | TRUE |
| `UPDATING('column')` | FALSE | TRUE if column is being updated | FALSE |

#### Trigger Management Commands

| Command | Description |
|---------|-------------|
| `ALTER TRIGGER trigger_name ENABLE;` | Enable a disabled trigger |
| `ALTER TRIGGER trigger_name DISABLE;` | Disable an active trigger |
| `ALTER TABLE table_name ENABLE ALL TRIGGERS;` | Enable all triggers on a table |
| `ALTER TABLE table_name DISABLE ALL TRIGGERS;` | Disable all triggers on a table |
| `DROP TRIGGER trigger_name;` | Permanently remove a trigger |

---

## PL/SQL Reserved Keywords

The following words are reserved in PL/SQL and cannot be used as identifiers (variable names, cursor names, etc.):

### Block Structure Keywords

| Keyword | Purpose |
|---------|---------|
| `DECLARE` | Start of declaration section |
| `BEGIN` | Start of executable section |
| `EXCEPTION` | Start of exception handling section |
| `END` | End of block, loop, or conditional |
| `IS` / `AS` | Used in subprogram declarations |

### Variable and Type Keywords

| Keyword | Purpose |
|---------|---------|
| `NUMBER` | Numeric data type |
| `VARCHAR2` | Variable-length string data type |
| `CHAR` | Fixed-length string data type |
| `DATE` | Date data type |
| `BOOLEAN` | Logical data type |
| `CONSTANT` | Declares a constant value |
| `DEFAULT` | Assigns default value |
| `NOT NULL` | Prevents NULL assignment |
| `TYPE` | Used to define user-defined types |
| `SUBTYPE` | Used to define constrained types |
| `RECORD` | Composite data type |
| `TABLE` | Collection data type |
| `VARRAY` | Variable-size array type |
| `REF` | Reference type |

### Control Flow Keywords

| Keyword | Purpose |
|---------|---------|
| `IF` | Conditional statement |
| `THEN` | Follows IF condition |
| `ELSIF` | Additional condition (note: not ELSEIF) |
| `ELSE` | Default branch |
| `CASE` | Multi-branch conditional |
| `WHEN` | Case branch condition |
| `LOOP` | Start of loop |
| `WHILE` | Conditional loop |
| `FOR` | Counting loop |
| `IN` | Range or parameter mode |
| `REVERSE` | Reverse loop direction |
| `EXIT` | Exit from loop |
| `CONTINUE` | Skip to next iteration (11g+) |
| `GOTO` | Jump to label |
| `NULL` | No-op statement |
| `RETURN` | Return from subprogram |

### Cursor Keywords

| Keyword | Purpose |
|---------|---------|
| `CURSOR` | Declares a cursor |
| `OPEN` | Opens a cursor |
| `FETCH` | Retrieves row from cursor |
| `CLOSE` | Closes a cursor |
| `INTO` | Target for SELECT or FETCH |

### Exception Keywords

| Keyword | Purpose |
|---------|---------|
| `EXCEPTION` | Exception handling section |
| `RAISE` | Raises an exception |
| `WHEN` | Exception handler condition |
| `OTHERS` | Catches all unhandled exceptions |
| `PRAGMA` | Compiler directive |
| `EXCEPTION_INIT` | Associates exception with error code |

### Subprogram Keywords

| Keyword | Purpose |
|---------|---------|
| `PROCEDURE` | Declares a procedure |
| `FUNCTION` | Declares a function |
| `PACKAGE` | Declares a package |
| `BODY` | Package body declaration |
| `TRIGGER` | Declares a trigger |
| `IN` | Input parameter mode |
| `OUT` | Output parameter mode |
| `IN OUT` | Input/output parameter mode |
| `NOCOPY` | Parameter passing hint |
| `DETERMINISTIC` | Function returns same result for same inputs |
| `AUTHID` | Execution authority |
| `DEFINER` | Run with definer's privileges |
| `CURRENT_USER` | Run with invoker's privileges |

### Transaction Keywords

| Keyword | Purpose |
|---------|---------|
| `COMMIT` | Make changes permanent |
| `ROLLBACK` | Undo changes |
| `SAVEPOINT` | Set a rollback point |
| `AUTONOMOUS_TRANSACTION` | Independent transaction pragma |

---

## Common Oracle Error Codes

| Error Code | SQLCODE | Exception Name | Description | Common Cause |
|------------|---------|---------------|-------------|--------------|
| ORA-00001 | -1 | DUP_VAL_ON_INDEX | Unique constraint violated | Inserting duplicate value in unique/primary key column |
| ORA-00051 | -51 | TIMEOUT_ON_RESOURCE | Timeout waiting for resource | Lock contention, resource unavailable |
| ORA-00900 | -900 | (none) | Invalid SQL statement | Syntax error in SQL |
| ORA-00904 | -904 | (none) | Invalid identifier | Column name misspelled or does not exist |
| ORA-00942 | -942 | (none) | Table or view does not exist | Wrong table name or no privileges |
| ORA-01001 | -1001 | INVALID_CURSOR | Invalid cursor | Fetching from closed cursor |
| ORA-01012 | -1012 | NOT_LOGGED_ON | Not connected to Oracle | Session disconnected |
| ORA-01017 | -1017 | LOGIN_DENIED | Invalid username/password | Wrong credentials |
| ORA-01403 | -1403 | NO_DATA_FOUND | No data found | SELECT INTO returned no rows |
| ORA-01422 | -1422 | TOO_MANY_ROWS | Exact fetch returns more than requested rows | SELECT INTO returned multiple rows |
| ORA-01476 | -1476 | ZERO_DIVIDE | Divisor is equal to zero | Division by zero in calculation |
| ORA-01722 | -1722 | INVALID_NUMBER | Invalid number | String to number conversion failed |
| ORA-02291 | -2291 | (none) | Integrity constraint violated - parent key not found | Foreign key references non-existent parent |
| ORA-02292 | -2292 | (none) | Integrity constraint violated - child record found | Deleting parent with existing child records |
| ORA-04091 | -4091 | (none) | Table is mutating | Trigger reads/modifies the triggering table |
| ORA-06500 | -6500 | STORAGE_ERROR | PL/SQL: storage error | Out of memory |
| ORA-06501 | -6501 | PROGRAM_ERROR | PL/SQL: program error | Internal PL/SQL bug |
| ORA-06502 | -6502 | VALUE_ERROR | PL/SQL: numeric or value error | Data type mismatch or truncation |
| ORA-06504 | -6504 | ROWTYPE_MISMATCH | Return types of result set variables or query do not match | Incompatible cursor variables |
| ORA-06511 | -6511 | CURSOR_ALREADY_OPEN | Cursor already open | Opening a cursor that is already open |
| ORA-06530 | -6530 | ACCESS_INTO_NULL | Reference to uninitialized composite | Object attribute access on NULL object |
| ORA-06531 | -6531 | COLLECTION_IS_NULL | Reference to uninitialized collection | Using uninitialized nested table or varray |
| ORA-06532 | -6532 | SUBSCRIPT_OUTSIDE_LIMIT | Reference to nested table or varray index outside declared range | Index out of VARRAY bounds |
| ORA-06533 | -6533 | SUBSCRIPT_BEYOND_COUNT | Reference to nested table or varray index beyond number of elements | Index exceeds element count |
| ORA-06550 | -6550 | (none) | PL/SQL compilation error | Syntax error in PL/SQL code |
| ORA-20000 to ORA-20999 | -20000 to -20999 | (user-defined) | User-defined error | RAISE_APPLICATION_ERROR range |

---

## Interview Quick Reference

### Top 25 PL/SQL Interview Topics

| No. | Topic | Key Points to Remember |
|-----|-------|----------------------|
| 1 | PL/SQL Block Structure | DECLARE (optional), BEGIN (mandatory), EXCEPTION (optional), END (mandatory) |
| 2 | SQL vs PL/SQL | SQL is declarative and set-based; PL/SQL is procedural with loops and conditions |
| 3 | Anonymous vs Named Blocks | Anonymous: not stored, no name; Named: stored in DB, reusable |
| 4 | Variables and Data Types | NUMBER, VARCHAR2, DATE, BOOLEAN; use %TYPE and %ROWTYPE for anchored types |
| 5 | IF-THEN-ELSIF | Use ELSIF (not ELSEIF); each IF needs matching END IF |
| 6 | CASE Statement | Searched CASE (conditions) vs Simple CASE (selector); must have END CASE |
| 7 | LOOP Types | Basic LOOP (EXIT WHEN), WHILE LOOP, FOR LOOP (automatic counter) |
| 8 | Exception Handling | Predefined (named), user-defined (DECLARE + RAISE), RAISE_APPLICATION_ERROR |
| 9 | SQLCODE and SQLERRM | SQLCODE returns error number; SQLERRM returns error message; use in WHEN OTHERS |
| 10 | Implicit Cursors | Created automatically for DML; attributes: SQL%FOUND, SQL%ROWCOUNT |
| 11 | Explicit Cursors | DECLARE, OPEN, FETCH, CLOSE cycle; or use cursor FOR loop |
| 12 | Cursor FOR Loop | Automatic open, fetch, close; simplest way to process cursors |
| 13 | REF CURSOR | Dynamic cursor; SYS_REFCURSOR for weak ref cursors; pass result sets |
| 14 | Procedures | Named PL/SQL blocks; do not return a value; called with EXECUTE or in blocks |
| 15 | Functions | Must return a value; can be used in SQL statements; RETURN is mandatory |
| 16 | Parameter Modes | IN (read-only, default), OUT (write-only), IN OUT (read-write) |
| 17 | Procedure vs Function | Procedure: no RETURN required, cannot use in SQL; Function: must RETURN, usable in SQL |
| 18 | Packages | Specification (public interface) + Body (implementation); overloading supported |
| 19 | Package Advantages | Modularity, encapsulation, performance (loaded once), overloading, global variables |
| 20 | BEFORE Triggers | Execute before DML; used for validation and auto-fill; can modify :NEW values |
| 21 | AFTER Triggers | Execute after DML; used for audit logging; cannot modify :NEW values |
| 22 | INSTEAD OF Triggers | Execute instead of DML on views; make complex views updatable |
| 23 | Mutating Table Error | ORA-04091; row-level trigger cannot query/modify triggering table; use compound triggers |
| 24 | BULK COLLECT | Fetches multiple rows at once into collections; reduces context switches |
| 25 | FORALL | Executes DML for all elements of a collection in one context switch |

### Quick Syntax Reference

#### PL/SQL Block

```
DECLARE
    -- declarations
BEGIN
    -- executable statements
EXCEPTION
    -- error handlers
END;
/
```

#### Procedure

```
CREATE OR REPLACE PROCEDURE proc_name (
    p_param1 IN NUMBER,
    p_param2 OUT VARCHAR2
) IS
BEGIN
    -- body
END proc_name;
/
```

#### Function

```
CREATE OR REPLACE FUNCTION func_name (
    p_param1 IN NUMBER
) RETURN VARCHAR2 IS
    v_result VARCHAR2(100);
BEGIN
    -- body
    RETURN v_result;
END func_name;
/
```

#### Explicit Cursor

```
DECLARE
    CURSOR c_cursor IS SELECT column FROM table;
    v_var table.column%TYPE;
BEGIN
    OPEN c_cursor;
    LOOP
        FETCH c_cursor INTO v_var;
        EXIT WHEN c_cursor%NOTFOUND;
        -- process
    END LOOP;
    CLOSE c_cursor;
END;
/
```

#### Package

```
-- Specification
CREATE OR REPLACE PACKAGE pkg_name IS
    PROCEDURE proc_name(p_param IN NUMBER);
    FUNCTION func_name(p_param IN NUMBER) RETURN NUMBER;
END pkg_name;
/

-- Body
CREATE OR REPLACE PACKAGE BODY pkg_name IS
    PROCEDURE proc_name(p_param IN NUMBER) IS
    BEGIN
        -- implementation
    END proc_name;

    FUNCTION func_name(p_param IN NUMBER) RETURN NUMBER IS
    BEGIN
        RETURN p_param * 2;
    END func_name;
END pkg_name;
/
```

#### Trigger

```
CREATE OR REPLACE TRIGGER trg_name
BEFORE INSERT OR UPDATE ON table_name
FOR EACH ROW
BEGIN
    :NEW.modified_date := SYSDATE;
END trg_name;
/
```

---

**End of Resources**
