SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP VIEW EMP_DEPT_VIEW';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EMPLOYEES_TEST CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE DEPARTMENTS_TEST CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE DEPARTMENTS_TEST (
    dept_id     NUMBER PRIMARY KEY,
    dept_name   VARCHAR2(50)
);
/

CREATE TABLE EMPLOYEES_TEST (
    emp_id      NUMBER PRIMARY KEY,
    emp_name    VARCHAR2(50),
    salary      NUMBER,
    dept_id     NUMBER REFERENCES DEPARTMENTS_TEST(dept_id)
);
/

INSERT INTO DEPARTMENTS_TEST VALUES (10, 'Finance');
INSERT INTO DEPARTMENTS_TEST VALUES (20, 'Engineering');
/

INSERT INTO EMPLOYEES_TEST VALUES (1, 'Alice', 50000, 10);
INSERT INTO EMPLOYEES_TEST VALUES (2, 'Bob', 60000, 20);
/

CREATE OR REPLACE VIEW EMP_DEPT_VIEW AS
SELECT e.emp_id,
       e.emp_name,
       e.salary,
       d.dept_id,
       d.dept_name
FROM EMPLOYEES_TEST e
JOIN DEPARTMENTS_TEST d ON e.dept_id = d.dept_id;
/

CREATE OR REPLACE TRIGGER instead_of_emp_view
INSTEAD OF INSERT ON EMP_DEPT_VIEW
FOR EACH ROW
DECLARE
    v_dept_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_dept_count
    FROM DEPARTMENTS_TEST
    WHERE dept_id = :NEW.dept_id;

    IF v_dept_count = 0 THEN
        INSERT INTO DEPARTMENTS_TEST (dept_id, dept_name)
        VALUES (:NEW.dept_id, :NEW.dept_name);
        DBMS_OUTPUT.PUT_LINE('Inserted new department: ' || :NEW.dept_name || ' (ID: ' || :NEW.dept_id || ')');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Department already exists: ' || :NEW.dept_name || ' (ID: ' || :NEW.dept_id || ')');
    END IF;

    INSERT INTO EMPLOYEES_TEST (emp_id, emp_name, salary, dept_id)
    VALUES (:NEW.emp_id, :NEW.emp_name, :NEW.salary, :NEW.dept_id);

    DBMS_OUTPUT.PUT_LINE('Inserted new employee: ' || :NEW.emp_name || ' (ID: ' || :NEW.emp_id || ')');
    DBMS_OUTPUT.PUT_LINE('INSTEAD OF Trigger handled the INSERT on the view.');
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Before INSTEAD OF Trigger Test ---');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Departments:');
    FOR rec IN (SELECT dept_id, dept_name FROM DEPARTMENTS_TEST ORDER BY dept_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  Dept ID: ' || rec.dept_id || ' | Name: ' || rec.dept_name);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Employees:');
    FOR rec IN (SELECT emp_id, emp_name, salary, dept_id FROM EMPLOYEES_TEST ORDER BY emp_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  Emp ID: ' || rec.emp_id || ' | Name: ' || rec.emp_name || ' | Salary: ' || rec.salary || ' | Dept: ' || rec.dept_id);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Inserting into View (Existing Department) ---');
    INSERT INTO EMP_DEPT_VIEW (emp_id, emp_name, salary, dept_id, dept_name)
    VALUES (3, 'Charlie', 55000, 10, 'Finance');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Inserting into View (New Department) ---');
    INSERT INTO EMP_DEPT_VIEW (emp_id, emp_name, salary, dept_id, dept_name)
    VALUES (4, 'Diana', 70000, 30, 'Marketing');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- After INSTEAD OF Trigger Test ---');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Departments:');
    FOR rec IN (SELECT dept_id, dept_name FROM DEPARTMENTS_TEST ORDER BY dept_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  Dept ID: ' || rec.dept_id || ' | Name: ' || rec.dept_name);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Employees:');
    FOR rec IN (SELECT emp_id, emp_name, salary, dept_id FROM EMPLOYEES_TEST ORDER BY emp_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  Emp ID: ' || rec.emp_id || ' | Name: ' || rec.emp_name || ' | Salary: ' || rec.salary || ' | Dept: ' || rec.dept_id);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('View Data:');
    FOR rec IN (SELECT emp_id, emp_name, salary, dept_id, dept_name FROM EMP_DEPT_VIEW ORDER BY emp_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  Emp ID: ' || rec.emp_id || ' | Name: ' || rec.emp_name || ' | Salary: ' || rec.salary || ' | Dept: ' || rec.dept_name);
    END LOOP;
END;
/

DROP TRIGGER instead_of_emp_view;
DROP VIEW EMP_DEPT_VIEW;
DROP TABLE EMPLOYEES_TEST;
DROP TABLE DEPARTMENTS_TEST;
