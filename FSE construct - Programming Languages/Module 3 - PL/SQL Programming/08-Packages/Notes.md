# PL/SQL Packages

## Introduction

As programs grow larger, managing dozens of standalone procedures and functions becomes difficult. PL/SQL solves this by providing **Packages** -- a way to group related procedures, functions, variables, cursors, constants, and exceptions into a single named unit. Packages are one of the most powerful features of PL/SQL and are used extensively in real-world Oracle database applications. They bring structure, modularity, and performance benefits that standalone subprograms cannot offer.

---

## Definition

A **Package** is a PL/SQL schema object that groups logically related types, variables, constants, cursors, exceptions, procedures, and functions into a single named unit.

Every package consists of two parts:

| Part | Purpose |
|------|---------|
| **Package Specification** | The public interface -- declares what is available to outside users |
| **Package Body** | The private implementation -- contains the actual code and hidden members |

> A package specification can exist without a body (called a **bodiless package**), but a package body cannot exist without a specification.

---

## Why It Is Used

| Reason | Explanation |
|--------|-------------|
| **Modularity** | Groups related subprograms into one logical unit |
| **Encapsulation** | Hides implementation details inside the body |
| **Information Hiding** | Private members are invisible to outside users |
| **Performance** | Entire package loads into memory on first call, reducing disk I/O |
| **Overloading** | Allows multiple subprograms with the same name but different parameters |
| **Persistence** | Package variables retain their values for the duration of a session |
| **Easier Maintenance** | Change the body without affecting the specification or dependent code |
| **Reduced Recompilation** | Changing the body does not invalidate dependent objects |
| **Namespace Management** | Avoids naming conflicts by grouping names under package name |

---

## Syntax

### Package Specification Syntax

```sql
CREATE OR REPLACE PACKAGE package_name AS

    constant_name CONSTANT datatype := value;

    PROCEDURE procedure_name(parameter_list);

    FUNCTION function_name(parameter_list) RETURN datatype;

    CURSOR cursor_name IS SELECT_statement;

    variable_name datatype;

    exception_name EXCEPTION;

END package_name;
/
```

### Package Body Syntax

```sql
CREATE OR REPLACE PACKAGE BODY package_name AS

    -- Private variable (not in specification)
    private_var datatype;

    -- Private procedure (not in specification)
    PROCEDURE private_proc IS
    BEGIN
        -- implementation
    END private_proc;

    -- Public procedure (declared in specification)
    PROCEDURE procedure_name(parameter_list) IS
    BEGIN
        -- implementation
    END procedure_name;

    -- Public function (declared in specification)
    FUNCTION function_name(parameter_list) RETURN datatype IS
    BEGIN
        -- implementation
        RETURN value;
    END function_name;

BEGIN
    -- Optional initialization section
    -- Runs once when the package is first loaded
    NULL;
END package_name;
/
```

### Drop Syntax

```sql
-- Drop entire package (specification + body)
DROP PACKAGE package_name;

-- Drop only the body (keeps specification intact)
DROP PACKAGE BODY package_name;
```

### Calling Package Members

```sql
-- Call a procedure
package_name.procedure_name(arguments);

-- Call a function
result := package_name.function_name(arguments);

-- Access a constant or variable
value := package_name.constant_name;
```

---

## Explanation

### Package Components in Detail

**Package Specification (Public Interface):**
- Acts as the contract between the package and the outside world
- Declares procedures, functions, constants, variables, cursors, and exceptions
- Only items declared here are accessible from outside the package
- Compiled independently of the body

**Package Body (Implementation):**
- Contains the actual code for all subprograms declared in the specification
- Can contain additional private subprograms, variables, and cursors
- Private members are only accessible within the package body
- Can include an initialization section that runs once per session

### Public vs Private Members

| Feature | Public Members | Private Members |
|---------|---------------|-----------------|
| Declared in | Specification AND Body | Body only |
| Accessible from outside | Yes | No |
| Can be called by other programs | Yes | No |
| Visible to other developers | Yes (via specification) | No (hidden in body) |
| Purpose | External interface | Internal helper logic |

### Package State and Persistence

- Package variables maintain their values throughout a database **session**
- Each session gets its own copy of package variables
- Values are lost when the session ends or the package is recompiled
- This is called **package state**

### Package Initialization Section

- The optional `BEGIN...END` block at the end of the package body
- Executes only ONCE -- the first time any package member is referenced in a session
- Used for one-time setup tasks like initializing variables or loading configuration

### Bodiless Packages

A package that has only a specification and no body is called a **bodiless package**. It is used to:
- Declare global constants
- Declare global types
- Declare global exceptions

```sql
CREATE OR REPLACE PACKAGE app_constants AS
    max_salary CONSTANT NUMBER := 99999;
    min_age    CONSTANT NUMBER := 18;
    app_name   CONSTANT VARCHAR2(50) := 'HR System';
END app_constants;
/
```

No body is needed because there are no subprograms to implement.

### Package Dependencies

- Objects that reference a package specification depend on it
- Changing the **specification** invalidates all dependent objects
- Changing only the **body** does NOT invalidate dependent objects
- This is a major advantage -- you can fix bugs in the body without breaking anything

---

## Package Specification vs Package Body

| Feature | Package Specification | Package Body |
|---------|----------------------|--------------|
| Purpose | Declares public interface | Contains implementation |
| Keyword | `CREATE PACKAGE` | `CREATE PACKAGE BODY` |
| Contains code logic | No (only declarations) | Yes (full implementation) |
| Required | Yes (always required) | No (bodiless packages exist) |
| Visibility | Visible to all users with access | Hidden implementation |
| Changing it | Invalidates dependent objects | Does NOT invalidate dependents |
| Can exist alone | Yes | No (needs specification first) |
| Private members | Cannot have private members | Can have private members |
| Compiled | First | Second (after specification) |
| DROP command | `DROP PACKAGE name` | `DROP PACKAGE BODY name` |

---

## Built-in Packages

Oracle provides many built-in packages. Here are the most commonly used:

| Package | Purpose | Common Procedures/Functions |
|---------|---------|----------------------------|
| `DBMS_OUTPUT` | Display output from PL/SQL blocks | `PUT_LINE`, `PUT`, `NEW_LINE`, `ENABLE`, `DISABLE` |
| `UTL_FILE` | Read and write operating system files | `FOPEN`, `PUT_LINE`, `GET_LINE`, `FCLOSE` |
| `DBMS_SQL` | Dynamic SQL execution | `OPEN_CURSOR`, `PARSE`, `EXECUTE`, `CLOSE_CURSOR` |
| `DBMS_LOB` | Manipulate Large Objects (LOBs) | `READ`, `WRITE`, `GETLENGTH`, `APPEND` |
| `DBMS_JOB` | Schedule and manage database jobs | `SUBMIT`, `REMOVE`, `RUN`, `CHANGE` |
| `DBMS_SCHEDULER` | Advanced job scheduling | `CREATE_JOB`, `DROP_JOB`, `ENABLE`, `DISABLE` |
| `DBMS_METADATA` | Extract database object definitions | `GET_DDL`, `GET_DEPENDENT_DDL` |
| `DBMS_RANDOM` | Generate random numbers | `VALUE`, `STRING`, `NORMAL` |
| `UTL_MAIL` | Send emails from the database | `SEND`, `SEND_ATTACH_RAW` |

---

## Execution Flow

### Package Creation and Execution Flow

```
Step 1: Create Package Specification
+---------------------------------------+
|   CREATE PACKAGE emp_pkg AS           |
|                                       |
|   Declares:                           |
|     - PROCEDURE display_employee      |
|     - FUNCTION get_salary             |
|     - CONSTANT tax_rate               |
|                                       |
|   (Public Interface - What is         |
|    available to the outside world)    |
+---------------------------------------+
                |
                | Compile Specification First
                v
Step 2: Create Package Body
+---------------------------------------+
|   CREATE PACKAGE BODY emp_pkg AS      |
|                                       |
|   Implements:                         |
|     - PROCEDURE display_employee      |
|     - FUNCTION get_salary             |
|                                       |
|   Also contains:                      |
|     - PROCEDURE format_output         |
|       (PRIVATE - not in spec)         |
|                                       |
|   (Hidden Implementation)             |
+---------------------------------------+
                |
                | Compile Body Second
                v
Step 3: Execute Package Members
+---------------------------------------+
|   emp_pkg.display_employee(100);      |
|   emp_pkg.get_salary(100);            |
|   emp_pkg.tax_rate;                   |
|                                       |
|   (Access via package_name.member)    |
+---------------------------------------+
```

### How Oracle Loads a Package

```
First Call to Any Package Member
                |
                v
+-------------------------------+
| Is package already in memory? |
+-------------------------------+
        |               |
       YES              NO
        |               |
        v               v
  +-----------+   +-------------------+
  | Execute   |   | Load ENTIRE       |
  | directly  |   | package into      |
  |           |   | memory (SGA)      |
  +-----------+   +-------------------+
                        |
                        v
                  +-------------------+
                  | Run initialization |
                  | section (if any)   |
                  +-------------------+
                        |
                        v
                  +-----------+
                  | Execute   |
                  | the called|
                  | member    |
                  +-----------+
```

### Public vs Private Access Flow

```
External Program (SQL*Plus, Another PL/SQL Block)
        |
        | Can access ONLY public members
        v
+---------------------------------------+
|       PACKAGE SPECIFICATION           |
|  (Public Interface)                   |
|                                       |
|   display_employee  [PUBLIC]   -------+----> Accessible
|   get_salary        [PUBLIC]   -------+----> Accessible
|   tax_rate          [PUBLIC]   -------+----> Accessible
+---------------------------------------+
        |
        | Implements
        v
+---------------------------------------+
|       PACKAGE BODY                    |
|  (Hidden Implementation)             |
|                                       |
|   display_employee  [implemented]     |
|   get_salary        [implemented]     |
|   format_output     [PRIVATE]  -------+----> NOT Accessible
|                                       |      from outside
+---------------------------------------+
```

---

## Real Life Example

### The Toolbox Analogy

Think of a package as a **toolbox**:

```
+==========================================+
|            TOOLBOX (Package)             |
+==========================================+
|                                          |
|  LABEL on the outside (Specification):   |
|  +------------------------------------+  |
|  | This toolbox contains:             |  |
|  |   - Hammer                         |  |
|  |   - Screwdriver                    |  |
|  |   - Measuring Tape                 |  |
|  +------------------------------------+  |
|                                          |
|  INSIDE the box (Body):                  |
|  +------------------------------------+  |
|  | Actual Tools:                      |  |
|  |   - Hammer (ready to use)          |  |
|  |   - Screwdriver (ready to use)     |  |
|  |   - Measuring Tape (ready to use)  |  |
|  |   - Small wrench (helper tool,     |  |
|  |     not listed on label)           |  |
|  +------------------------------------+  |
|                                          |
+==========================================+
```

- The **label** (specification) tells you what tools are available
- The **inside** (body) contains the actual tools and how they work
- The **small wrench** (private member) is inside but not listed on the label -- only the toolbox itself uses it internally
- You **cannot** add new tools to the label without updating it (changing spec invalidates dependents)
- You **can** replace a tool inside without changing the label (changing body is safe)

---

## Code Explanation

### File: PackageSpecification.sql

This file creates the **package specification** for `emp_pkg`. It is the public interface that declares what the package offers:

| Declaration | Type | Purpose |
|-------------|------|---------|
| `display_employee(p_emp_id NUMBER)` | Procedure | Displays employee details for a given employee ID |
| `get_salary(p_emp_id NUMBER) RETURN NUMBER` | Function | Returns the salary of a given employee |
| `tax_rate CONSTANT NUMBER := 0.30` | Constant | A fixed tax rate of 30% accessible as `emp_pkg.tax_rate` |

The specification contains NO implementation code. It only tells what is available. This file must be compiled **before** the package body.

### File: PackageBody.sql

This file creates the **package body** for `emp_pkg`. It contains all the implementation:

| Member | Visibility | What It Does |
|--------|-----------|--------------|
| `format_output` | **Private** | A helper procedure that formats and prints a label-value pair. It is NOT declared in the specification, so outside programs cannot call it. |
| `display_employee` | **Public** | Selects `first_name`, `last_name`, and `salary` from `HR.EMPLOYEES` using the given employee ID. Uses the private `format_output` procedure to print each value. |
| `get_salary` | **Public** | Selects the `salary` from `HR.EMPLOYEES` for the given employee ID and returns it using the `RETURN` statement. |

Key points:
- `format_output` is defined BEFORE it is used (forward declaration order matters for private members)
- Both public procedures implement exactly what was declared in the specification
- The body can use private members freely, but outside callers cannot

### File: ExecutePackage.sql

This file demonstrates how to **use** the package after both specification and body are compiled:

| Step | Code | What Happens |
|------|------|-------------|
| 1 | `emp_pkg.display_employee(100)` | Calls the public procedure to display details of employee 100 |
| 2 | `emp_pkg.get_salary(100)` | Calls the public function to get the salary of employee 100 |
| 3 | `emp_pkg.tax_rate` | Accesses the public constant (0.30) |
| 4 | Tax calculation | Multiplies salary by tax_rate to compute the tax amount |

All package members are accessed using **dot notation**: `package_name.member_name`.

---

## Interview Questions

**Q1: What is a PL/SQL package?**
A package is a schema object that groups logically related PL/SQL types, variables, constants, cursors, exceptions, procedures, and functions into a single named unit. It has two parts: specification (public interface) and body (implementation).

**Q2: What is the difference between a package specification and a package body?**
The specification declares the public interface (what is available). The body contains the actual implementation code and can also have private members. The specification must be created first.

**Q3: Can a package specification exist without a body?**
Yes. This is called a bodiless package. It is used when the package only contains constants, variables, types, or exceptions with no subprograms that need implementation.

**Q4: Can a package body exist without a specification?**
No. The specification must always be created first. The body implements what the specification declares.

**Q5: What are private members in a package?**
Private members are procedures, functions, variables, or cursors that are defined only in the package body and NOT declared in the specification. They can only be accessed within the package body.

**Q6: What happens when you modify the package body but not the specification?**
Dependent objects are NOT invalidated. This is a key advantage of packages -- you can fix bugs or change implementation without affecting other code that uses the package.

**Q7: What is the package initialization section?**
It is the optional `BEGIN...END` block at the end of the package body. It executes only once per session, the first time any package member is referenced. It is used for one-time setup.

**Q8: How does package loading improve performance?**
When any member of a package is called for the first time, Oracle loads the entire package into the Shared Global Area (SGA) in memory. Subsequent calls to any member of the package do not require disk I/O.

**Q9: What is package state?**
Package state refers to the values of package variables and cursors during a session. Each session has its own copy of package state. The state persists until the session ends or the package is recompiled.

**Q10: What is the difference between DROP PACKAGE and DROP PACKAGE BODY?**
`DROP PACKAGE` removes both the specification and body. `DROP PACKAGE BODY` removes only the body, keeping the specification intact.

**Q11: Can you overload subprograms in a package?**
Yes. A package can contain multiple procedures or functions with the same name but different parameter lists. This is called overloading.

**Q12: Name three commonly used Oracle built-in packages.**
`DBMS_OUTPUT` (display output), `UTL_FILE` (read/write OS files), `DBMS_SQL` (dynamic SQL execution).

---

## Viva Questions

**V1: What keyword is used to create a package specification?**
`CREATE OR REPLACE PACKAGE package_name AS ... END package_name;`

**V2: What keyword is used to create a package body?**
`CREATE OR REPLACE PACKAGE BODY package_name AS ... END package_name;`

**V3: How do you call a procedure from a package?**
Using dot notation: `package_name.procedure_name(arguments);`

**V4: Where are private members declared?**
Only in the package body. They are NOT declared in the specification.

**V5: What happens if you try to call a private procedure from outside the package?**
You get a compilation error: `PLS-00302: component must be declared`.

**V6: Which is compiled first -- specification or body?**
The specification is always compiled first.

**V7: Can a package contain both procedures and functions?**
Yes. A package can contain any combination of procedures, functions, variables, constants, cursors, types, and exceptions.

**V8: What is DBMS_OUTPUT.PUT_LINE?**
It is a procedure from the built-in `DBMS_OUTPUT` package that prints a line of text to the output buffer.

**V9: Does changing the package body invalidate dependent objects?**
No. Only changing the specification invalidates dependent objects.

**V10: What is a bodiless package used for?**
To declare global constants, types, variables, or exceptions that do not require any procedural implementation.

**V11: How do you remove only the body of a package?**
Using `DROP PACKAGE BODY package_name;`

**V12: Can two different packages have procedures with the same name?**
Yes. Since they are in different packages, there is no naming conflict. This is called namespace management.

---

## Common Mistakes

| Mistake | Problem | Correction |
|---------|---------|------------|
| Creating body before specification | Compilation error | Always create and compile the specification first |
| Forgetting to implement a declared subprogram | Body will not compile | Every procedure/function in the spec must be implemented in the body |
| Trying to call a private member from outside | `PLS-00302` error | Only public members (declared in spec) can be called externally |
| Mismatched parameter lists between spec and body | Compilation error | Parameter names, types, and order must match exactly |
| Forgetting the `/` after the block | Block is not executed | Always end with `/` on a new line |
| Using `DROP PACKAGE` when only body needs changing | Drops specification too, invalidating dependents | Use `DROP PACKAGE BODY` to drop only the body |
| Assuming package state persists across sessions | Variables reset for each new session | Package state is session-specific |
| Not using `SET SERVEROUTPUT ON` | No output is displayed | Always enable server output before running |
| Declaring a member in body but expecting external access | Member is private | Must declare in specification for public access |
| Creating a body for a bodiless package | Unnecessary compilation | Bodiless packages with only constants/types do not need a body |

---

## Quick Revision

| Concept | Key Point |
|---------|-----------|
| Package | Groups related PL/SQL elements into one named unit |
| Specification | Public interface -- declares what is available |
| Body | Implementation -- contains actual code and private members |
| Public members | Declared in specification, accessible from outside |
| Private members | Defined only in body, hidden from outside |
| Bodiless package | Has only specification (constants, types, exceptions) |
| Package state | Variables persist for the session, reset on new session |
| Initialization | Runs once per session on first package reference |
| Performance | Entire package loads into memory on first call |
| Overloading | Same name, different parameters in same package |
| `DROP PACKAGE` | Removes specification + body |
| `DROP PACKAGE BODY` | Removes only the body |
| Dependency rule | Changing body = safe; changing spec = invalidates dependents |
| Dot notation | Access via `package_name.member_name` |
| `DBMS_OUTPUT` | Built-in package for displaying output |
| `UTL_FILE` | Built-in package for file I/O |
| `DBMS_SQL` | Built-in package for dynamic SQL |
| Compile order | Specification first, then body |

---
