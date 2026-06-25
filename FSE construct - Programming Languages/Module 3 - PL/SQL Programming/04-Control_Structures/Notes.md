# PL/SQL Control Structures

## Introduction

Every program needs the ability to make decisions and repeat actions. Without control structures, a program would execute statements one after another from top to bottom with no ability to branch or loop. PL/SQL provides a rich set of control structures that allow developers to direct the flow of execution based on conditions, repeat blocks of code, and skip or exit iterations. This section covers all conditional statements (IF, CASE), iterative statements (LOOP, WHILE, FOR), and flow-altering statements (EXIT, CONTINUE, GOTO, NULL).

---

## Definition

**Control Structures** are PL/SQL constructs that determine the order in which statements are executed. They break the default sequential flow and allow programs to make decisions (conditional), repeat tasks (iterative), or jump to specific locations (sequential).

There are three categories:

| Category | Purpose | Constructs |
|---|---|---|
| Conditional | Execute code based on a condition | IF, IF-ELSE, IF-ELSIF-ELSE, CASE |
| Iterative | Repeat code multiple times | Basic LOOP, WHILE LOOP, FOR LOOP |
| Sequential | Alter flow unconditionally | GOTO, NULL, EXIT, CONTINUE |

---

## Why It Is Used

| Reason | Explanation |
|---|---|
| Decision Making | Execute different code paths based on runtime values |
| Repetition | Process rows, generate series, or repeat tasks without rewriting code |
| Data Validation | Check conditions before performing DML operations |
| Business Logic | Implement grading systems, discount calculations, approval workflows |
| Error Handling | Use conditional checks to prevent runtime errors |
| Efficiency | Avoid writing duplicate code by using loops |

---

## Syntax

### 1. Simple IF

```sql
IF condition THEN
    statements;
END IF;
```

### 2. IF-ELSE

```sql
IF condition THEN
    statements;
ELSE
    statements;
END IF;
```

### 3. IF-ELSIF-ELSE

```sql
IF condition1 THEN
    statements;
ELSIF condition2 THEN
    statements;
ELSIF condition3 THEN
    statements;
ELSE
    statements;
END IF;
```

### 4. Simple CASE

```sql
CASE selector
    WHEN value1 THEN statements;
    WHEN value2 THEN statements;
    ELSE statements;
END CASE;
```

**As Expression:**

```sql
variable := CASE selector
    WHEN value1 THEN result1
    WHEN value2 THEN result2
    ELSE default_result
END;
```

### 5. Searched CASE

```sql
CASE
    WHEN condition1 THEN statements;
    WHEN condition2 THEN statements;
    ELSE statements;
END CASE;
```

**As Expression:**

```sql
variable := CASE
    WHEN condition1 THEN result1
    WHEN condition2 THEN result2
    ELSE default_result
END;
```

### 6. Basic LOOP

```sql
LOOP
    statements;
    EXIT WHEN condition;
END LOOP;
```

### 7. WHILE LOOP

```sql
WHILE condition LOOP
    statements;
END LOOP;
```

### 8. FOR LOOP

```sql
FOR counter IN lower_bound..upper_bound LOOP
    statements;
END LOOP;
```

### 9. Reverse FOR LOOP

```sql
FOR counter IN REVERSE lower_bound..upper_bound LOOP
    statements;
END LOOP;
```

### 10. EXIT and EXIT WHEN

```sql
EXIT;                    -- Unconditional exit
EXIT WHEN condition;     -- Conditional exit
```

### 11. CONTINUE and CONTINUE WHEN

```sql
CONTINUE;                    -- Skip to next iteration
CONTINUE WHEN condition;     -- Skip when condition is true
```

### 12. GOTO Statement

```sql
GOTO label_name;
<<label_name>>
statements;
```

### 13. NULL Statement

```sql
NULL;    -- Does nothing, acts as a placeholder
```

---

## Explanation

### Conditional Statements

**Simple IF** evaluates a condition. If TRUE, the body executes. If FALSE or NULL, nothing happens.

**IF-ELSE** provides two branches. One executes when the condition is TRUE, the other when it is FALSE or NULL.

**IF-ELSIF-ELSE** is a multi-way decision ladder. Conditions are tested top to bottom. The first TRUE condition's body executes, and the rest are skipped. The ELSE block is a catch-all default.

**Simple CASE** compares a single selector expression against multiple values. It is best used when comparing one variable against discrete known values (like day numbers, status codes).

**Searched CASE** evaluates independent boolean conditions in each WHEN clause. It is more flexible than Simple CASE because each WHEN can test a different expression or range.

### Iterative Statements

**Basic LOOP** is an unconditional loop. It runs forever unless explicitly terminated by EXIT or EXIT WHEN. It is a post-test loop because the exit condition is checked inside the loop body (usually after executing some statements).

**WHILE LOOP** is a pre-test loop. The condition is evaluated before each iteration. If the condition is FALSE from the start, the loop body never executes.

**FOR LOOP** is a counter-controlled loop. The loop variable is declared implicitly, incremented automatically, and is read-only inside the loop. The bounds are evaluated once before the loop starts.

**Reverse FOR LOOP** works exactly like a FOR LOOP but counts downward from the upper bound to the lower bound. The range syntax remains `lower..upper` with the REVERSE keyword.

### Flow-Altering Statements

**EXIT** immediately terminates the current loop. **EXIT WHEN** terminates only if the condition is TRUE.

**CONTINUE** skips the remaining statements in the current iteration and jumps to the next iteration. **CONTINUE WHEN** skips only if the condition is TRUE.

**GOTO** transfers control unconditionally to a labeled statement. It should be avoided in modern programming because it creates spaghetti code, making programs hard to read and maintain.

**NULL** is a no-operation statement. It is used as a placeholder where a statement is syntactically required but no action is needed (for example, in an ELSE branch or an exception handler where you intentionally want to do nothing).

---

## Execution Flow

### IF-ELSIF-ELSE Flow

```
        +-------------------+
        |  Start            |
        +-------------------+
                 |
                 v
        +-------------------+
        | condition1 TRUE?  |---YES---> Execute Block 1 ---+
        +-------------------+                              |
                 | NO                                      |
                 v                                         |
        +-------------------+                              |
        | condition2 TRUE?  |---YES---> Execute Block 2 ---+
        +-------------------+                              |
                 | NO                                      |
                 v                                         |
        +-------------------+                              |
        | Execute ELSE Block|                              |
        +-------------------+                              |
                 |                                         |
                 +<----------------------------------------+
                 |
                 v
        +-------------------+
        |  END IF           |
        +-------------------+
```

### Basic LOOP Flow (Post-Test)

```
        +-------------------+
        |  Start            |
        +-------------------+
                 |
                 v
        +-------------------+<---------+
        | Execute Body      |          |
        +-------------------+          |
                 |                     |
                 v                     |
        +-------------------+          |
        | EXIT Condition    |          |
        | TRUE?             |          |
        +-------------------+          |
           |            |              |
          YES           NO             |
           |            |              |
           |            +--------------+
           v
        +-------------------+
        |  After Loop       |
        +-------------------+
```

### WHILE LOOP Flow (Pre-Test)

```
        +-------------------+
        |  Start            |
        +-------------------+
                 |
                 v
        +-------------------+<---------+
        | Condition TRUE?   |          |
        +-------------------+          |
           |            |              |
          YES           NO             |
           |            |              |
           v            v              |
        +----------+ +----------+     |
        | Execute  | | After    |     |
        | Body     | | Loop     |     |
        +----------+ +----------+     |
           |                           |
           +---------------------------+
```

### FOR LOOP Flow (Counter-Controlled)

```
        +-------------------------+
        |  Start                  |
        |  counter = lower_bound  |
        +-------------------------+
                   |
                   v
        +-------------------------+<------+
        | counter <= upper_bound? |       |
        +-------------------------+       |
           |              |               |
          YES             NO              |
           |              |               |
           v              v               |
        +---------+  +-----------+        |
        | Execute |  | After     |        |
        | Body    |  | Loop      |        |
        +---------+  +-----------+        |
           |                              |
           v                              |
        +-------------------------+       |
        | counter = counter + 1   |       |
        +-------------------------+       |
           |                              |
           +------------------------------+
```

### CONTINUE Statement Flow

```
        +-------------------+
        | Loop Iteration    |
        +-------------------+
                 |
                 v
        +-------------------+
        | CONTINUE WHEN     |
        | condition TRUE?   |
        +-------------------+
           |            |
          YES           NO
           |            |
           |            v
           |    +-------------------+
           |    | Execute Remaining |
           |    | Statements        |
           |    +-------------------+
           |            |
           +<-----------+
           |
           v
        +-------------------+
        | Next Iteration    |
        +-------------------+
```

---

## Real Life Example

Consider an **Employee Payroll Processing System**:

**Conditional Logic (IF-ELSIF-ELSE):**
- IF employee type is 'FULL_TIME', calculate salary with benefits.
- ELSIF employee type is 'PART_TIME', calculate hourly wages.
- ELSIF employee type is 'CONTRACT', calculate contract pay.
- ELSE flag as unknown employee type.

**CASE Statement:**
- Based on department_id, route the payroll to the appropriate department manager for approval.

**FOR LOOP:**
- Loop through all 107 employees in HR.EMPLOYEES table and calculate annual bonus for each.

**WHILE LOOP:**
- Process pending payroll records while unprocessed records exist in the queue.

**Basic LOOP with EXIT WHEN:**
- Read and process each payroll batch until the batch queue is empty.

**CONTINUE WHEN:**
- While processing all employees, skip those who are on leave (CONTINUE WHEN status = 'ON_LEAVE').

---

## Code Explanation

### File 1: If.sql
This program demonstrates the **Simple IF** statement. A variable `v_num` is assigned the value 15. The IF condition checks whether `v_num > 0`. Since 15 is greater than 0, the condition evaluates to TRUE and the message "15 is a positive number." is printed. If the value were negative or zero, nothing would be printed because there is no ELSE branch.

### File 2: IfElse.sql
This program demonstrates the **IF-ELSE** statement. A variable `v_num` holds the value 24. The MOD function calculates the remainder of dividing `v_num` by 2. If the remainder is 0, the number is even; otherwise, it is odd. Since 24 divided by 2 leaves a remainder of 0, the output will be "24 is an even number."

### File 3: IfElsif.sql
This program demonstrates the **IF-ELSIF-ELSE** ladder for grade classification. A variable `v_marks` is set to 78. The conditions are evaluated top to bottom:
- Is 78 >= 90? No.
- Is 78 >= 80? No.
- Is 78 >= 70? Yes. So `v_grade` is assigned 'C'.

The remaining ELSIF and ELSE branches are skipped entirely. The program prints both the marks and the computed grade.

### File 4: Case.sql
This program demonstrates both **Simple CASE** and **Searched CASE** expressions.

**Simple CASE:** The variable `v_day_num` is 3. The CASE expression matches it against values 1 through 7. It matches WHEN 3, so `v_day_name` becomes 'Wednesday'.

**Searched CASE:** The variable `v_marks` is 85. The CASE expression evaluates each WHEN condition:
- Is 85 >= 90? No.
- Is 85 >= 80? Yes. So `v_grade` becomes 'B'.

Both results are printed.

### File 5: Loop.sql
This program demonstrates the **Basic LOOP** with **EXIT WHEN**. A counter variable starts at 1. Inside the loop, the current counter value is printed, then the counter is incremented by 1. After incrementing, the EXIT WHEN condition checks if the counter exceeds 10. When counter becomes 11, the condition `v_counter > 10` is TRUE and the loop terminates. Output: numbers 1 through 10.

### File 6: While.sql
This program demonstrates the **WHILE LOOP**. A counter starts at 1. Before each iteration, the condition `v_counter <= 10` is evaluated. If TRUE, the body executes (print and increment). When counter reaches 11, the condition becomes FALSE and the loop stops. Output: numbers 1 through 10. The key difference from Basic LOOP is that the condition is checked before the body executes.

### File 7: For.sql
This program demonstrates the **FOR LOOP**. The loop variable `v_counter` is declared implicitly in the FOR statement itself. It iterates from 1 to 10 automatically. There is no need to declare the variable, initialize it, or increment it manually. The body simply prints each value. This is the most concise loop for known iteration counts.

### File 8: Reverse_For.sql
This program demonstrates the **Reverse FOR LOOP**. The REVERSE keyword causes the loop to start at the upper bound (10) and count down to the lower bound (1). The range is still written as `1..10`, not `10..1`. Each iteration prints the counter value. Output: 10, 9, 8, ..., 2, 1.

### File 9: OddNumbers.sql
This program prints **odd numbers from 1 to 20** using a FOR loop. The loop iterates through every number from 1 to 20. Inside the loop, the IF condition checks `MOD(v_num, 2) <> 0`. If the remainder when dividing by 2 is not zero, the number is odd and gets printed. Even numbers are silently skipped. Output: 1, 3, 5, 7, 9, 11, 13, 15, 17, 19.

### File 10: EvenNumbers.sql
This program prints **even numbers from 1 to 20** using a FOR loop. Similar to OddNumbers.sql, but the condition checks `MOD(v_num, 2) = 0`. If the remainder is zero, the number is even and gets printed. Odd numbers are skipped. Output: 2, 4, 6, 8, 10, 12, 14, 16, 18, 20.

### File 11: MultiplicationTable.sql
This program prints the **multiplication table for 5**. A variable `v_num` is set to 5. The FOR loop iterates from 1 to 10. In each iteration, the result `v_num * v_i` is calculated and stored in `v_result`. The formatted output shows "5 x 1 = 5", "5 x 2 = 10", and so on up to "5 x 10 = 50".

---

## Comparison Table: All Loop Types

| Feature | Basic LOOP | WHILE LOOP | FOR LOOP | Reverse FOR LOOP |
|---|---|---|---|---|
| Test Type | Post-test (exit inside body) | Pre-test (before body) | Pre-test (automatic) | Pre-test (automatic) |
| Minimum Executions | 1 (body runs at least once) | 0 (may never execute) | 0 (if lower > upper) | 0 (if lower > upper) |
| Counter Declaration | Manual (DECLARE section) | Manual (DECLARE section) | Implicit (automatic) | Implicit (automatic) |
| Counter Increment | Manual (inside body) | Manual (inside body) | Automatic (+1) | Automatic (-1) |
| Counter Scope | Available outside loop | Available outside loop | Only inside loop | Only inside loop |
| Exit Mechanism | EXIT or EXIT WHEN | Condition becomes FALSE | Counter exceeds upper bound | Counter goes below lower bound |
| Best Used When | Unknown iterations, at least one run needed | Unknown iterations, condition checked first | Known iteration count | Known count, reverse order needed |
| Infinite Loop Risk | High (if EXIT is missing) | Medium (if condition never changes) | None (bounded) | None (bounded) |

---

## Comparison Table: Conditional Statements

| Feature | Simple IF | IF-ELSE | IF-ELSIF-ELSE | Simple CASE | Searched CASE |
|---|---|---|---|---|---|
| Branches | 1 | 2 | Multiple | Multiple | Multiple |
| Default | No action | ELSE block | ELSE block | ELSE clause | ELSE clause |
| Condition Type | Boolean | Boolean | Boolean | Equality match | Boolean |
| Best For | Single check | Binary decision | Multi-way decision | Matching one value | Range/complex checks |
| Can Be Expression | No | No | No | Yes | Yes |

---

## Interview Questions

**Q1. What are the three types of control structures in PL/SQL?**
Conditional (IF, CASE), Iterative (LOOP, WHILE, FOR), and Sequential (GOTO, NULL, EXIT, CONTINUE).

**Q2. What is the difference between Simple CASE and Searched CASE?**
Simple CASE compares a single selector expression against discrete values using equality. Searched CASE evaluates independent boolean conditions in each WHEN clause, allowing ranges and complex comparisons.

**Q3. Can we modify the loop counter variable inside a FOR loop?**
No. The FOR loop counter is read-only. Attempting to assign a value to it results in a compilation error.

**Q4. What happens if the lower bound is greater than the upper bound in a FOR loop?**
The loop body never executes. PL/SQL does not raise an error; it simply skips the loop entirely.

**Q5. What is the difference between EXIT and EXIT WHEN?**
EXIT terminates the loop unconditionally. EXIT WHEN terminates the loop only if the specified condition evaluates to TRUE.

**Q6. What is the difference between CONTINUE and CONTINUE WHEN?**
CONTINUE skips the remaining statements in the current iteration unconditionally. CONTINUE WHEN skips only if the specified condition is TRUE.

**Q7. Why should GOTO be avoided in PL/SQL?**
GOTO creates unstructured jumps that make code difficult to read, debug, and maintain. It can lead to spaghetti code. Modern structured constructs (IF, LOOP, EXIT) can replace GOTO in all practical scenarios.

**Q8. What is the purpose of the NULL statement?**
NULL is a no-operation statement used as a placeholder where PL/SQL syntax requires at least one statement but no action is needed. Common uses include empty ELSE branches and exception handlers.

**Q9. Which loop guarantees at least one execution of the body?**
The Basic LOOP. Since the exit condition is checked inside the body (post-test), the body always executes at least once before the condition is evaluated.

**Q10. Can CASE statement be used as both a statement and an expression?**
Yes. As a statement, it ends with `END CASE;`. As an expression, it ends with `END;` and returns a value that can be assigned to a variable.

**Q11. What is the scope of a FOR loop counter variable?**
The counter is only accessible inside the loop body. It cannot be referenced before or after the loop. It does not need to be declared in the DECLARE section.

**Q12. How do you write a REVERSE FOR LOOP?**
Use `FOR counter IN REVERSE lower..upper LOOP`. Note that the range is still written as `lower..upper`, not `upper..lower`. The REVERSE keyword handles the direction.

**Q13. Can you nest loops in PL/SQL?**
Yes. PL/SQL supports nesting of any loop type inside any other loop type. You can label nested loops and use EXIT with labels to exit specific loops.

**Q14. What happens if EXIT WHEN condition is never TRUE in a Basic LOOP?**
The loop runs indefinitely (infinite loop), which will eventually cause a runtime error or hang the session.

---

## Viva Questions

**V1. Write the syntax for IF-ELSIF-ELSE.**
```sql
IF condition1 THEN
    statements;
ELSIF condition2 THEN
    statements;
ELSE
    statements;
END IF;
```

**V2. What will happen if all conditions in an IF-ELSIF ladder are FALSE and there is no ELSE?**
Nothing happens. Control passes to the statement after END IF without executing any branch.

**V3. Can you use a SELECT statement inside a CASE expression?**
Yes, but a CASE expression is used to return a value based on conditions. You can use CASE inside a SELECT statement (SQL CASE), or use a SELECT INTO inside a CASE statement branch.

**V4. How many times does `FOR i IN 5..5 LOOP` execute?**
Exactly once, with i = 5.

**V5. What is the output of `FOR i IN REVERSE 1..5 LOOP`?**
The loop prints values 5, 4, 3, 2, 1 (starts from upper bound and decrements to lower bound).

**V6. Can we use EXIT in a FOR loop?**
Yes. EXIT and EXIT WHEN can be used in any loop type (Basic LOOP, WHILE LOOP, FOR LOOP) to terminate early.

**V7. What is the difference between WHILE LOOP and Basic LOOP?**
WHILE LOOP tests the condition before each iteration (pre-test) and may execute zero times. Basic LOOP tests the condition inside the body (post-test) and always executes at least once.

**V8. Is ELSIF one word or two words in PL/SQL?**
One word: ELSIF (not ELSE IF, not ELSEIF). This is a common syntax error.

**V9. What does `MOD(v_num, 2)` return?**
It returns the remainder when v_num is divided by 2. If the result is 0, the number is even; if 1, the number is odd.

**V10. Can a CASE statement have no ELSE clause?**
Yes, but if no WHEN clause matches and there is no ELSE, PL/SQL raises a CASE_NOT_FOUND exception at runtime. It is best practice to always include an ELSE clause.

**V11. How do you terminate a PL/SQL block in SQL*Plus?**
With a forward slash `/` on a new line by itself.

**V12. What is the difference between `END CASE` and `END` in CASE?**
`END CASE;` is used for CASE statements (standalone). `END;` is used for CASE expressions (when assigning to a variable).

---

## Common Mistakes

| Mistake | Problem | Correct Approach |
|---|---|---|
| Writing `ELSE IF` instead of `ELSIF` | Compilation error | Use `ELSIF` (single word, no space) |
| Missing `END IF;` | Compilation error | Every IF must have a matching `END IF;` |
| Missing `END LOOP;` | Compilation error | Every LOOP must have a matching `END LOOP;` |
| Forgetting EXIT in Basic LOOP | Infinite loop | Always include `EXIT` or `EXIT WHEN` inside a Basic LOOP |
| Modifying FOR loop counter | Compilation error | The counter is read-only; use a separate variable if needed |
| Writing `FOR i IN 10..1 LOOP` | Loop never executes | Use `FOR i IN REVERSE 1..10 LOOP` for descending |
| No ELSE in CASE statement | CASE_NOT_FOUND exception if no match | Always include an ELSE clause |
| Using `=` instead of `:=` for assignment | Compilation error | Use `:=` for assignment, `=` for comparison |
| Forgetting `SET SERVEROUTPUT ON` | No output visible | Add it before the DECLARE/BEGIN block |
| Not incrementing counter in WHILE/Basic LOOP | Infinite loop | Always update the counter inside the loop body |
| Using GOTO across block boundaries | Compilation error | GOTO cannot jump into an IF, LOOP, or sub-block |
| Semicolon after THEN | Compilation error | No semicolon after THEN, LOOP, or ELSE keywords |

---

## Quick Revision

| Construct | Keyword Pattern | Key Point |
|---|---|---|
| Simple IF | `IF...THEN...END IF` | One-way branch |
| IF-ELSE | `IF...THEN...ELSE...END IF` | Two-way branch |
| IF-ELSIF-ELSE | `IF...ELSIF...ELSE...END IF` | Multi-way branch (ELSIF is one word) |
| Simple CASE | `CASE selector WHEN...END CASE` | Matches one variable against values |
| Searched CASE | `CASE WHEN condition...END CASE` | Evaluates independent conditions |
| Basic LOOP | `LOOP...EXIT WHEN...END LOOP` | Post-test, runs at least once |
| WHILE LOOP | `WHILE...LOOP...END LOOP` | Pre-test, may run zero times |
| FOR LOOP | `FOR i IN a..b LOOP...END LOOP` | Counter auto-declared, auto-incremented |
| Reverse FOR | `FOR i IN REVERSE a..b LOOP` | Counts downward, range still `a..b` |
| EXIT | `EXIT;` or `EXIT WHEN condition;` | Terminates the innermost loop |
| CONTINUE | `CONTINUE;` or `CONTINUE WHEN condition;` | Skips to the next iteration |
| GOTO | `GOTO label; <<label>>` | Avoid using; creates unstructured code |
| NULL | `NULL;` | No-operation placeholder |

### Summary of All SQL Files

| File | Concept | Key Construct Used |
|---|---|---|
| If.sql | Check if number is positive | Simple IF |
| IfElse.sql | Check even or odd | IF-ELSE with MOD |
| IfElsif.sql | Grade classification | IF-ELSIF-ELSE ladder |
| Case.sql | Day name and grade | Simple CASE + Searched CASE |
| Loop.sql | Print 1 to 10 | Basic LOOP with EXIT WHEN |
| While.sql | Print 1 to 10 | WHILE LOOP |
| For.sql | Print 1 to 10 | FOR LOOP |
| Reverse_For.sql | Print 10 to 1 | REVERSE FOR LOOP |
| OddNumbers.sql | Odd numbers 1-20 | FOR LOOP + IF (MOD) |
| EvenNumbers.sql | Even numbers 1-20 | FOR LOOP + IF (MOD) |
| MultiplicationTable.sql | Table of 5 | FOR LOOP with calculation |
