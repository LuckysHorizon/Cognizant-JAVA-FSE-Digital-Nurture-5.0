SET SERVEROUTPUT ON;

DECLARE
    v_nth      NUMBER := 3;
    v_salary   NUMBER;
BEGIN
    SELECT salary INTO v_salary
    FROM (
        SELECT DISTINCT salary
        FROM HR.EMPLOYEES
        ORDER BY salary DESC
    )
    WHERE ROWNUM <= v_nth
    ORDER BY salary ASC
    FETCH FIRST 1 ROW ONLY;

    DBMS_OUTPUT.PUT_LINE(v_nth || 'rd Highest Salary = ' || v_salary);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Not enough distinct salaries found.');
END;
/

DECLARE
    v_emp_id     HR.EMPLOYEES.EMPLOYEE_ID%TYPE;
    v_first_name HR.EMPLOYEES.FIRST_NAME%TYPE;
    v_salary     HR.EMPLOYEES.SALARY%TYPE;
    v_dept_id    HR.EMPLOYEES.DEPARTMENT_ID%TYPE;
    v_avg_sal    NUMBER;

    CURSOR c_emp IS
        SELECT e.employee_id, e.first_name, e.salary, e.department_id
        FROM HR.EMPLOYEES e
        WHERE e.salary > (
            SELECT AVG(e2.salary)
            FROM HR.EMPLOYEES e2
            WHERE e2.department_id = e.department_id
        )
        ORDER BY e.department_id, e.salary DESC;
BEGIN
    DBMS_OUTPUT.PUT_LINE(RPAD('EMP_ID', 10) || RPAD('NAME', 20) ||
                         RPAD('SALARY', 12) || RPAD('DEPT_ID', 10));
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 52, '-'));

    FOR rec IN c_emp LOOP
        DBMS_OUTPUT.PUT_LINE(RPAD(rec.employee_id, 10) ||
                             RPAD(rec.first_name, 20) ||
                             RPAD(rec.salary, 12) ||
                             RPAD(rec.department_id, 10));
    END LOOP;
END;
/

DECLARE
    v_a NUMBER := 100;
    v_b NUMBER := 250;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Before Swap: a = ' || v_a || ', b = ' || v_b);
    v_a := v_a + v_b;
    v_b := v_a - v_b;
    v_a := v_a - v_b;
    DBMS_OUTPUT.PUT_LINE('After Swap:  a = ' || v_a || ', b = ' || v_b);
END;
/

DECLARE
    CURSOR c_emp IS
        SELECT employee_id, first_name, last_name, email, salary, department_id
        FROM HR.EMPLOYEES
        WHERE ROWNUM <= 10
        ORDER BY employee_id;

    v_rec c_emp%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE(RPAD('ID', 6) || RPAD('NAME', 25) ||
                         RPAD('EMAIL', 15) || RPAD('SALARY', 10) || 'DEPT');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 66, '-'));

    OPEN c_emp;
    LOOP
        FETCH c_emp INTO v_rec;
        EXIT WHEN c_emp%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(
            RPAD(v_rec.employee_id, 6) ||
            RPAD(v_rec.first_name || ' ' || v_rec.last_name, 25) ||
            RPAD(v_rec.email, 15) ||
            RPAD(v_rec.salary, 10) ||
            v_rec.department_id
        );
    END LOOP;
    CLOSE c_emp;

    DBMS_OUTPUT.PUT_LINE('Total Rows Fetched: 10');
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE practice_emp_raise CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE practice_emp_raise (
    employee_id NUMBER PRIMARY KEY,
    first_name  VARCHAR2(50),
    salary      NUMBER
);
/

INSERT INTO practice_emp_raise VALUES (1, 'Alice', 5000);
INSERT INTO practice_emp_raise VALUES (2, 'Bob', 6000);
INSERT INTO practice_emp_raise VALUES (3, 'Charlie', 7000);
INSERT INTO practice_emp_raise VALUES (4, 'Diana', 8000);
INSERT INTO practice_emp_raise VALUES (5, 'Edward', 9000);
COMMIT;
/

CREATE OR REPLACE PROCEDURE give_raise_10_percent IS
    CURSOR c_emp IS
        SELECT employee_id, first_name, salary FROM practice_emp_raise FOR UPDATE;
    v_new_salary NUMBER;
BEGIN
    FOR rec IN c_emp LOOP
        v_new_salary := rec.salary * 1.10;
        UPDATE practice_emp_raise
        SET salary = v_new_salary
        WHERE employee_id = rec.employee_id;
        DBMS_OUTPUT.PUT_LINE(rec.first_name || ': ' || rec.salary || ' -> ' || v_new_salary);
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('10% raise applied to all employees.');
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Before Raise:');
    FOR rec IN (SELECT first_name, salary FROM practice_emp_raise) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.first_name || ' = ' || rec.salary);
    END LOOP;

    give_raise_10_percent;

    DBMS_OUTPUT.PUT_LINE('After Raise:');
    FOR rec IN (SELECT first_name, salary FROM practice_emp_raise) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.first_name || ' = ' || rec.salary);
    END LOOP;
END;
/

CREATE OR REPLACE FUNCTION is_palindrome(p_str VARCHAR2) RETURN VARCHAR2 IS
    v_reversed VARCHAR2(500) := '';
    v_clean    VARCHAR2(500);
    v_len      NUMBER;
BEGIN
    v_clean := UPPER(REPLACE(p_str, ' ', ''));
    v_len := LENGTH(v_clean);

    FOR i IN REVERSE 1..v_len LOOP
        v_reversed := v_reversed || SUBSTR(v_clean, i, 1);
    END LOOP;

    IF v_clean = v_reversed THEN
        RETURN 'YES';
    ELSE
        RETURN 'NO';
    END IF;
END;
/

DECLARE
    v_test1 VARCHAR2(50) := 'MADAM';
    v_test2 VARCHAR2(50) := 'ORACLE';
    v_test3 VARCHAR2(50) := 'Race Car';
BEGIN
    DBMS_OUTPUT.PUT_LINE('"' || v_test1 || '" is palindrome? ' || is_palindrome(v_test1));
    DBMS_OUTPUT.PUT_LINE('"' || v_test2 || '" is palindrome? ' || is_palindrome(v_test2));
    DBMS_OUTPUT.PUT_LINE('"' || v_test3 || '" is palindrome? ' || is_palindrome(v_test3));
END;
/

DECLARE
    v_num1   NUMBER := 100;
    v_num2   NUMBER := 0;
    v_result NUMBER;
    v_name   VARCHAR2(5);
BEGIN
    v_result := v_num1 / v_num2;
    DBMS_OUTPUT.PUT_LINE('Result = ' || v_result);
EXCEPTION
    WHEN ZERO_DIVIDE THEN
        DBMS_OUTPUT.PUT_LINE('Error: Division by zero detected!');
        DBMS_OUTPUT.PUT_LINE('SQLCODE: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('SQLERRM: ' || SQLERRM);
END;
/

DECLARE
    v_salary NUMBER;
BEGIN
    SELECT salary INTO v_salary
    FROM HR.EMPLOYEES
    WHERE department_id = 10;
    DBMS_OUTPUT.PUT_LINE('Salary = ' || v_salary);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: No employee found!');
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('Error: Multiple employees found for this query!');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected Error: ' || SQLERRM);
END;
/

DECLARE
    v_name VARCHAR2(5);
BEGIN
    v_name := 'VERY LONG STRING VALUE';
    DBMS_OUTPUT.PUT_LINE('Name = ' || v_name);
EXCEPTION
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Error: Value too large for variable!');
        DBMS_OUTPUT.PUT_LINE('SQLCODE: ' || SQLCODE);
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE practice_emp_trigger CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE practice_emp_trigger (
    employee_id NUMBER PRIMARY KEY,
    first_name  VARCHAR2(50),
    salary      NUMBER
);
/

INSERT INTO practice_emp_trigger VALUES (1, 'Alice', 5000);
INSERT INTO practice_emp_trigger VALUES (2, 'Bob', 6000);
INSERT INTO practice_emp_trigger VALUES (3, 'Charlie', 7000);
COMMIT;
/

CREATE OR REPLACE TRIGGER prevent_salary_decrease
BEFORE UPDATE OF salary ON practice_emp_trigger
FOR EACH ROW
BEGIN
    IF :NEW.salary < :OLD.salary THEN
        RAISE_APPLICATION_ERROR(-20001,
            'Salary decrease not allowed! Current: ' || :OLD.salary ||
            ', Attempted: ' || :NEW.salary);
    END IF;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 1: Increasing salary (should succeed)');
    UPDATE practice_emp_trigger SET salary = 8000 WHERE employee_id = 1;
    DBMS_OUTPUT.PUT_LINE('Salary increased successfully for employee 1.');
    COMMIT;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Test 2: Decreasing salary (should fail)');
    UPDATE practice_emp_trigger SET salary = 3000 WHERE employee_id = 2;
    DBMS_OUTPUT.PUT_LINE('This line should not print.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Caught Error: ' || SQLERRM);
        ROLLBACK;
END;
/

CREATE OR REPLACE PACKAGE greet_pkg IS
    PROCEDURE greet(p_name VARCHAR2);
    PROCEDURE greet(p_id NUMBER);
END greet_pkg;
/

CREATE OR REPLACE PACKAGE BODY greet_pkg IS
    PROCEDURE greet(p_name VARCHAR2) IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Hello, ' || p_name || '! Welcome aboard.');
    END;

    PROCEDURE greet(p_id NUMBER) IS
        v_name VARCHAR2(100);
    BEGIN
        SELECT first_name || ' ' || last_name INTO v_name
        FROM HR.EMPLOYEES
        WHERE employee_id = p_id;
        DBMS_OUTPUT.PUT_LINE('Hello, Employee #' || p_id || ' (' || v_name || ')!');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee #' || p_id || ' not found.');
    END;
END greet_pkg;
/

BEGIN
    greet_pkg.greet('Oracle Developer');
    greet_pkg.greet(100);
    greet_pkg.greet(9999);
END;
/

DECLARE
    CURSOR c_dept_emp(p_dept_id NUMBER) IS
        SELECT employee_id, first_name, last_name, salary
        FROM HR.EMPLOYEES
        WHERE department_id = p_dept_id
        ORDER BY salary DESC;

    TYPE t_dept_ids IS TABLE OF NUMBER;
    v_depts t_dept_ids := t_dept_ids(10, 20, 30);
BEGIN
    FOR i IN 1..v_depts.COUNT LOOP
        DBMS_OUTPUT.PUT_LINE('=== Department ' || v_depts(i) || ' ===');
        DBMS_OUTPUT.PUT_LINE(RPAD('ID', 8) || RPAD('NAME', 25) || 'SALARY');
        DBMS_OUTPUT.PUT_LINE(RPAD('-', 43, '-'));

        FOR rec IN c_dept_emp(v_depts(i)) LOOP
            DBMS_OUTPUT.PUT_LINE(
                RPAD(rec.employee_id, 8) ||
                RPAD(rec.first_name || ' ' || rec.last_name, 25) ||
                rec.salary
            );
        END LOOP;
        DBMS_OUTPUT.PUT_LINE(' ');
    END LOOP;
END;
/
