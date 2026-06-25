# PL/SQL Triggers

## Introduction

Imagine a security camera installed at the entrance of a building. The moment someone walks through the door, the camera automatically starts recording. Nobody has to press a button or give a command. The camera is set up once, and from that point forward, it activates every single time the event (someone entering) occurs.

PL/SQL Triggers work exactly the same way. A trigger is a PL/SQL block that you define once and attach to a table, view, or database event. Once created, it fires automatically whenever the specified event occurs. You do not call a trigger manually. The database itself detects the event and executes the trigger code.

Triggers are one of the most powerful features in Oracle PL/SQL. They are used for auditing, validation, enforcing business rules, auto-generating values, and maintaining data integrity without requiring changes to the application code.

---

## Definition

A **Trigger** is a named PL/SQL block that is stored in the database and executed (fired) automatically in response to a specific event on a table, view, schema, or database.

> A trigger is NOT called by the user. It is invoked by the Oracle database engine when the triggering event occurs.

---

## Why It Is Used

| Purpose                  | Description                                                        |
|--------------------------|--------------------------------------------------------------------|
| Data Validation          | Enforce rules before data is inserted or updated                   |
| Auditing                 | Automatically log who changed what and when                        |
| Auto-Generation          | Generate sequence values, timestamps, or derived columns           |
| Enforcing Business Rules | Prevent invalid operations like negative salary                    |
| Maintaining Referential Integrity | Custom foreign key logic beyond standard constraints     |
| Synchronizing Tables     | Keep related tables in sync when one changes                       |
| Security                 | Restrict operations based on time, user, or conditions             |
| Complex Defaults         | Set default values that depend on other columns or external logic  |

---

## Syntax

### BEFORE Trigger (Row-Level)

```sql
CREATE OR REPLACE TRIGGER trigger_name
BEFORE INSERT OR UPDATE OR DELETE
ON table_name
FOR EACH ROW
BEGIN
    -- trigger body
END;
/
```

### AFTER Trigger (Row-Level)

```sql
CREATE OR REPLACE TRIGGER trigger_name
AFTER INSERT OR UPDATE OR DELETE
ON table_name
FOR EACH ROW
BEGIN
    -- trigger body
END;
/
```

### Statement-Level Trigger (No FOR EACH ROW)

```sql
CREATE OR REPLACE TRIGGER trigger_name
BEFORE INSERT ON table_name
BEGIN
    -- fires once per statement, not per row
END;
/
```

### INSTEAD OF Trigger (On Views Only)

```sql
CREATE OR REPLACE TRIGGER trigger_name
INSTEAD OF INSERT ON view_name
FOR EACH ROW
BEGIN
    -- custom logic to handle the insert on base tables
END;
/
```

### Trigger with WHEN Clause

```sql
CREATE OR REPLACE TRIGGER trigger_name
BEFORE UPDATE ON table_name
FOR EACH ROW
WHEN (NEW.salary > 50000)
BEGIN
    -- fires only when condition is met
END;
/
```

> In the WHEN clause, use `NEW` and `OLD` without the colon (:). Inside the trigger body, use `:NEW` and `:OLD` with the colon.

---

## Explanation

### Types of Triggers by Timing

| Timing      | When It Fires                                      | Use Case                          |
|-------------|-----------------------------------------------------|-----------------------------------|
| BEFORE      | Before the DML operation is performed               | Validation, modifying :NEW values |
| AFTER       | After the DML operation is performed                | Auditing, logging                 |
| INSTEAD OF  | Replaces the DML operation (views only)             | Making views updatable            |

### Types of Triggers by Event

| Event    | Fires When                          |
|----------|--------------------------------------|
| INSERT   | A new row is inserted into the table |
| UPDATE   | An existing row is modified          |
| DELETE   | A row is removed from the table      |

A single trigger can handle multiple events using `INSERT OR UPDATE OR DELETE`.

### Types of Triggers by Level

| Level           | Keyword         | Fires                                  |
|-----------------|-----------------|----------------------------------------|
| Row-Level       | FOR EACH ROW    | Once for each affected row             |
| Statement-Level | (no keyword)    | Once for the entire DML statement      |

### Trigger Types Summary Table

| Trigger Type       | Timing   | Level     | Target    | Key Use                          |
|--------------------|----------|-----------|-----------|----------------------------------|
| BEFORE ROW         | BEFORE   | Row       | Table     | Validate/modify data before save |
| BEFORE STATEMENT   | BEFORE   | Statement | Table     | Check conditions before DML      |
| AFTER ROW          | AFTER    | Row       | Table     | Audit/log after each row change  |
| AFTER STATEMENT    | AFTER    | Statement | Table     | Summary actions after DML        |
| INSTEAD OF         | INSTEAD  | Row       | View      | Make complex views updatable     |
| COMPOUND           | Multiple | Both      | Table     | Avoid mutating table errors      |

### :NEW and :OLD Pseudo-Records

These are special record variables available inside row-level triggers to access column values.

| Pseudo-Record | Available In           | Contains                          |
|---------------|------------------------|-----------------------------------|
| :NEW          | INSERT, UPDATE         | The new value being written       |
| :OLD          | UPDATE, DELETE         | The old value before the change   |

#### :NEW vs :OLD Availability by Event

| Event    | :OLD Available | :NEW Available | :OLD Value     | :NEW Value        |
|----------|----------------|----------------|----------------|-------------------|
| INSERT   | No (all NULL)  | Yes            | NULL           | New row values    |
| UPDATE   | Yes            | Yes            | Before update  | After update      |
| DELETE   | Yes            | No (all NULL)  | Deleted values | NULL              |

> :NEW values can be modified only in BEFORE triggers. In AFTER triggers, :NEW is read-only.

### INSERTING, UPDATING, DELETING Predicates

When a trigger handles multiple events, use these Boolean predicates to determine which event fired the trigger.

```sql
IF INSERTING THEN
    -- handle insert
ELSIF UPDATING THEN
    -- handle update
ELSIF DELETING THEN
    -- handle delete
END IF;
```

### Compound Triggers

A compound trigger combines BEFORE STATEMENT, BEFORE EACH ROW, AFTER EACH ROW, and AFTER STATEMENT timing points into a single trigger body. This is useful to avoid mutating table errors and to share variables across timing points.

```sql
CREATE OR REPLACE TRIGGER compound_trigger_name
FOR INSERT OR UPDATE ON table_name
COMPOUND TRIGGER

    BEFORE STATEMENT IS
    BEGIN
        NULL;
    END BEFORE STATEMENT;

    BEFORE EACH ROW IS
    BEGIN
        NULL;
    END BEFORE EACH ROW;

    AFTER EACH ROW IS
    BEGIN
        NULL;
    END AFTER EACH ROW;

    AFTER STATEMENT IS
    BEGIN
        NULL;
    END AFTER STATEMENT;

END compound_trigger_name;
/
```

### Mutating Table Error

A **mutating table error** (ORA-04091) occurs when a row-level trigger tries to read from or write to the same table that caused the trigger to fire. Oracle does not allow this because the table is in the middle of being modified.

Solutions:
- Use a compound trigger to defer the read/write to the AFTER STATEMENT section
- Use a temporary collection variable to store data
- Redesign the logic to avoid querying the triggering table

### Trigger Execution Order

When multiple triggers exist on the same table for the same event, Oracle fires them in this order:

1. BEFORE STATEMENT trigger
2. For each affected row:
   - BEFORE ROW trigger
   - The actual DML operation
   - AFTER ROW trigger
3. AFTER STATEMENT trigger

---

## Execution Flow

### Single Row INSERT with BEFORE and AFTER Triggers

```
User executes: INSERT INTO employees VALUES (...)
    |
    v
+---------------------------+
| BEFORE STATEMENT Trigger  |  (fires once)
+---------------------------+
    |
    v
+---------------------------+
| BEFORE ROW Trigger        |  (fires for each row)
| - Can modify :NEW values  |
| - Can validate data       |
| - Can raise errors        |
+---------------------------+
    |
    v
+---------------------------+
| Actual INSERT Operation   |  (row is written to table)
+---------------------------+
    |
    v
+---------------------------+
| AFTER ROW Trigger         |  (fires for each row)
| - Can read :NEW values    |
| - Can log/audit           |
+---------------------------+
    |
    v
+---------------------------+
| AFTER STATEMENT Trigger   |  (fires once)
+---------------------------+
    |
    v
INSERT Complete
```

### Multi-Row UPDATE (3 Rows Affected)

```
User executes: UPDATE employees SET salary = salary * 1.1 WHERE dept_id = 10
    |
    v
+---------------------------+
| BEFORE STATEMENT Trigger  |  (fires once)
+---------------------------+
    |
    v
+----- Row 1 ---------------+
| BEFORE ROW Trigger        |
| UPDATE row 1              |
| AFTER ROW Trigger         |
+----------------------------+
    |
    v
+----- Row 2 ---------------+
| BEFORE ROW Trigger        |
| UPDATE row 2              |
| AFTER ROW Trigger         |
+----------------------------+
    |
    v
+----- Row 3 ---------------+
| BEFORE ROW Trigger        |
| UPDATE row 3              |
| AFTER ROW Trigger         |
+----------------------------+
    |
    v
+---------------------------+
| AFTER STATEMENT Trigger   |  (fires once)
+---------------------------+
    |
    v
UPDATE Complete
```

### INSTEAD OF Trigger on a View

```
User executes: INSERT INTO emp_dept_view VALUES (...)
    |
    v
+----------------------------------+
| INSTEAD OF Trigger fires         |
| - Original INSERT is suppressed  |
| - Trigger body runs instead      |
| - Inserts into BASE tables       |
+----------------------------------+
    |
    v
+----------------------------------+
| INSERT INTO departments_test ... |
| INSERT INTO employees_test ...   |
+----------------------------------+
    |
    v
View INSERT handled via base tables
```

---

## Real Life Example

**Security Camera Analogy:**

Think of a trigger like a security camera system in a bank:

| Security Camera                          | PL/SQL Trigger                                |
|------------------------------------------|-----------------------------------------------|
| Installed once at the entrance           | Created once on a table                       |
| Automatically starts recording on motion | Automatically fires on INSERT/UPDATE/DELETE   |
| Nobody presses record manually           | Nobody calls the trigger manually             |
| Records who entered and when             | Logs who changed data and when (auditing)     |
| Can sound an alarm for intruders         | Can raise an error for invalid data           |
| Can be turned on/off                     | Can be enabled/disabled                       |
| Can be removed entirely                  | Can be dropped                                |

Just as the camera does not stop the person from entering (AFTER trigger) or can block entry if unauthorized (BEFORE trigger), PL/SQL triggers can either validate before allowing data changes or log after changes are complete.

---

## Code Explanation

### File 1: BeforeTrigger.sql

This program demonstrates a **BEFORE INSERT** row-level trigger that validates data before it enters the table.

**What It Does:**
- Creates two tables: `EMPLOYEE_TEST` (main table) and `EMPLOYEE_AUDIT` (audit log)
- Creates a trigger `before_emp_insert` that fires BEFORE every INSERT on `EMPLOYEE_TEST`
- Inside the trigger, it checks if `:NEW.salary` is less than or equal to zero
- If the salary is invalid, `RAISE_APPLICATION_ERROR` stops the insert with a custom error message
- Valid inserts go through normally
- Tests with both valid and invalid data to show the trigger in action

**Key Concept:** BEFORE triggers can modify `:NEW` values and can prevent the DML operation by raising an error.

### File 2: AfterTrigger.sql

This program demonstrates an **AFTER UPDATE** row-level trigger that logs salary changes for auditing.

**What It Does:**
- Creates `EMPLOYEE_TEST` (main table) and `SALARY_LOG` (audit log)
- Creates a trigger `after_salary_update` that fires AFTER every UPDATE on the `salary` column of `EMPLOYEE_TEST`
- Inside the trigger, it inserts a record into `SALARY_LOG` capturing the old salary (`:OLD.salary`), new salary (`:NEW.salary`), and the date of change
- Inserts sample employee data, performs salary updates, then queries the log to show recorded changes

**Key Concept:** AFTER triggers are ideal for auditing because the DML has already succeeded, so the audit log accurately reflects committed changes.

### File 3: InsteadOfTrigger.sql

This program demonstrates an **INSTEAD OF INSERT** trigger on a view that joins two tables.

**What It Does:**
- Creates two base tables: `DEPARTMENTS_TEST` and `EMPLOYEES_TEST`
- Creates a view `EMP_DEPT_VIEW` that joins both tables
- Normally, you cannot INSERT into a complex join view. The INSTEAD OF trigger intercepts the INSERT
- The trigger body performs the actual inserts into the base tables using `:NEW` values
- Tests by inserting into the view and then querying both base tables to verify data

**Key Concept:** INSTEAD OF triggers make non-updatable views behave as if they were updatable by replacing the original DML with custom logic.

### File 4: TriggerManagement.sql

This program demonstrates how to manage triggers using DDL commands.

**What It Does:**
- Creates a simple table and a BEFORE INSERT trigger
- Demonstrates `ALTER TRIGGER trigger_name DISABLE` to deactivate a trigger
- Demonstrates `ALTER TRIGGER trigger_name ENABLE` to reactivate it
- Demonstrates `ALTER TABLE table_name DISABLE ALL TRIGGERS` to disable all triggers on a table at once
- Demonstrates `ALTER TABLE table_name ENABLE ALL TRIGGERS` to re-enable them
- Demonstrates `DROP TRIGGER trigger_name` to permanently remove a trigger
- Cleans up all objects at the end

**Key Concept:** Triggers can be temporarily disabled (useful during bulk data loads) or permanently dropped when no longer needed.

---

## BEFORE vs AFTER Triggers Comparison

| Feature                    | BEFORE Trigger                        | AFTER Trigger                          |
|----------------------------|----------------------------------------|----------------------------------------|
| When It Fires              | Before the DML executes               | After the DML executes                 |
| Can Modify :NEW            | Yes                                    | No (read-only)                         |
| Can Prevent DML            | Yes (RAISE_APPLICATION_ERROR)         | Yes, but DML already executed          |
| Best For                   | Validation, setting defaults           | Auditing, logging, cascading changes   |
| Row Already in Table       | No                                     | Yes                                    |
| Performance Impact         | Slightly less (no undo needed)        | Slightly more (may need rollback)      |
| Access to :OLD             | UPDATE and DELETE only                | UPDATE and DELETE only                 |
| Typical Use Case           | Check salary > 0 before insert        | Log old and new salary after update    |

---

## Interview Questions

**Q1: What is a trigger in PL/SQL?**
A trigger is a named PL/SQL block stored in the database that automatically executes (fires) when a specific event occurs on a table, view, schema, or database. Triggers cannot be called explicitly by the user.

**Q2: What are the different types of triggers based on timing?**
There are three types based on timing: BEFORE triggers (fire before the DML operation), AFTER triggers (fire after the DML operation), and INSTEAD OF triggers (replace the DML operation, used only on views).

**Q3: What is the difference between a row-level trigger and a statement-level trigger?**
A row-level trigger (defined with FOR EACH ROW) fires once for each row affected by the DML statement. A statement-level trigger fires only once for the entire DML statement, regardless of how many rows are affected.

**Q4: What are :NEW and :OLD pseudo-records?**
:NEW and :OLD are special record variables available in row-level triggers. :NEW holds the new values being written (available in INSERT and UPDATE). :OLD holds the original values before the change (available in UPDATE and DELETE).

**Q5: Can you modify :NEW values in an AFTER trigger?**
No. :NEW values can only be modified in BEFORE triggers. In AFTER triggers, :NEW is read-only because the DML operation has already been executed.

**Q6: What is an INSTEAD OF trigger?**
An INSTEAD OF trigger is defined on a view. When a DML operation is performed on the view, the trigger fires instead of the original DML. The trigger body contains custom logic to perform the operation on the base tables.

**Q7: What is a mutating table error?**
A mutating table error (ORA-04091) occurs when a row-level trigger tries to query or modify the same table that caused the trigger to fire. Oracle prevents this because the table is in a transitional state during the DML operation.

**Q8: How can you disable a trigger without dropping it?**
Use `ALTER TRIGGER trigger_name DISABLE;` to disable a specific trigger, or use `ALTER TABLE table_name DISABLE ALL TRIGGERS;` to disable all triggers on a table.

**Q9: What are INSERTING, UPDATING, and DELETING predicates?**
These are Boolean functions available inside triggers that handle multiple events (INSERT OR UPDATE OR DELETE). They return TRUE based on which event actually fired the trigger, allowing conditional logic inside a single trigger.

**Q10: What is a compound trigger?**
A compound trigger combines all four timing points (BEFORE STATEMENT, BEFORE EACH ROW, AFTER EACH ROW, AFTER STATEMENT) into a single trigger body. It is useful for avoiding mutating table errors and sharing state across timing points.

**Q11: In what order do triggers fire when multiple triggers exist on the same table?**
The order is: BEFORE STATEMENT, then for each row (BEFORE ROW, DML operation, AFTER ROW), then AFTER STATEMENT. If multiple triggers exist at the same timing point, the order is unspecified unless the FOLLOWS clause is used.

**Q12: Can a trigger call a procedure or function?**
Yes. A trigger body can call stored procedures, functions, and packages. This is a recommended practice to keep trigger bodies small and move complex logic into reusable subprograms.

---

## Viva Questions

**V1: Can you create a trigger on a view?**
Yes, but only INSTEAD OF triggers can be created on views. BEFORE and AFTER triggers can only be created on tables.

**V2: What happens if a BEFORE trigger raises an error?**
The DML operation is cancelled. The row is not inserted, updated, or deleted. Any changes made by the trigger itself are also rolled back.

**V3: Is :OLD available in an INSERT trigger?**
Technically :OLD exists but all its fields are NULL during an INSERT because there is no previous row to reference.

**V4: Can a trigger fire another trigger?**
Yes, this is called trigger cascading. If a trigger performs a DML operation on another table that also has a trigger, the second trigger will fire. Oracle allows up to 32 levels of cascading.

**V5: How do you check if a trigger is enabled or disabled?**
Query the `USER_TRIGGERS` data dictionary view: `SELECT trigger_name, status FROM USER_TRIGGERS;`

**V6: What is the difference between WHEN clause and IF inside the trigger body?**
The WHEN clause is evaluated by Oracle before the trigger body executes, which is more efficient. The IF statement is evaluated inside the trigger body after it starts executing. Use WHEN for simple conditions on :NEW/:OLD values.

**V7: Can you use COMMIT or ROLLBACK inside a trigger?**
No. Triggers are part of the transaction that fired them. Using COMMIT or ROLLBACK inside a trigger causes an error. Exception: Autonomous transaction triggers can commit independently.

**V8: What is the maximum number of triggers on a single table?**
There is no hard limit on the number of triggers per table, but having too many triggers can make debugging and maintenance difficult.

**V9: Can a statement-level trigger access :NEW and :OLD?**
No. :NEW and :OLD pseudo-records are available only in row-level triggers (FOR EACH ROW).

**V10: What is the purpose of the FOLLOWS clause in triggers?**
The FOLLOWS clause specifies the firing order when multiple triggers of the same type and timing exist on the same table. For example: `CREATE TRIGGER t2 BEFORE INSERT ON emp FOLLOWS t1`.

**V11: Can you create a trigger on a temporary table?**
No. Oracle does not allow triggers on global temporary tables.

**V12: What is the difference between a trigger and a constraint?**
A constraint is a declarative rule enforced by the database engine (like NOT NULL, CHECK, UNIQUE). A trigger is a procedural block that can contain complex logic, call procedures, insert into other tables, and perform actions that constraints cannot.

---

## Common Mistakes

| Mistake                                        | Why It Is Wrong                                              | Correct Approach                                      |
|------------------------------------------------|--------------------------------------------------------------|-------------------------------------------------------|
| Trying to modify :NEW in an AFTER trigger      | :NEW is read-only in AFTER triggers                         | Use a BEFORE trigger to modify :NEW values            |
| Using COMMIT inside a trigger                  | Triggers are part of the calling transaction                 | Remove COMMIT; let the calling code handle it         |
| Querying the triggering table in a row trigger | Causes mutating table error (ORA-04091)                     | Use compound triggers or defer to AFTER STATEMENT     |
| Forgetting FOR EACH ROW                        | Without it, trigger is statement-level; no :NEW/:OLD access | Add FOR EACH ROW for row-level triggers               |
| Using :NEW/:OLD in statement-level triggers    | These are only available in row-level triggers              | Add FOR EACH ROW or remove :NEW/:OLD references       |
| Using :NEW in WHEN clause with colon           | WHEN clause uses NEW/OLD without colon                      | Write WHEN (NEW.salary > 0) not WHEN (:NEW.salary > 0)|
| Creating BEFORE/AFTER trigger on a view        | Only INSTEAD OF triggers work on views                      | Use INSTEAD OF for views                              |
| Not handling all events in multi-event trigger | Missing ELSIF for one of the events                         | Use INSERTING, UPDATING, DELETING for all events      |
| Excessive logic in trigger body                | Makes debugging and maintenance difficult                   | Move logic to stored procedures/functions              |
| Creating recursive triggers                    | Trigger on table A fires trigger on table B which fires A   | Design carefully to avoid infinite cascading          |

---

## Quick Revision

| Concept                    | Key Point                                                    |
|----------------------------|--------------------------------------------------------------|
| Trigger                    | Auto-executing PL/SQL block attached to a table/view         |
| BEFORE trigger             | Fires before DML; can modify :NEW; used for validation       |
| AFTER trigger              | Fires after DML; :NEW read-only; used for auditing           |
| INSTEAD OF trigger         | Fires on views only; replaces the DML operation              |
| FOR EACH ROW               | Makes trigger row-level; enables :NEW and :OLD               |
| :NEW                       | New values (INSERT, UPDATE); modifiable only in BEFORE       |
| :OLD                       | Old values (UPDATE, DELETE); always read-only                |
| WHEN clause                | Filter condition; uses NEW/OLD without colon                 |
| INSERTING/UPDATING/DELETING| Boolean predicates for multi-event triggers                  |
| Compound trigger           | Combines all 4 timing points in one trigger                  |
| Mutating table error       | ORA-04091; cannot query triggering table in row trigger      |
| DISABLE trigger            | ALTER TRIGGER name DISABLE                                   |
| ENABLE trigger             | ALTER TRIGGER name ENABLE                                    |
| DISABLE all triggers       | ALTER TABLE name DISABLE ALL TRIGGERS                        |
| DROP trigger               | DROP TRIGGER name (permanent removal)                        |
| Execution order            | BEFORE STMT > BEFORE ROW > DML > AFTER ROW > AFTER STMT     |
| No COMMIT in triggers      | Triggers share the calling transaction                       |
| Max cascade depth          | 32 levels of trigger cascading                               |
| Dictionary view            | USER_TRIGGERS stores trigger metadata                        |
