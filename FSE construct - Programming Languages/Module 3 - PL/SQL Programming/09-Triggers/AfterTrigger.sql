SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE SALARY_LOG CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EMPLOYEE_TEST CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE EMPLOYEE_TEST (
    emp_id      NUMBER PRIMARY KEY,
    emp_name    VARCHAR2(50),
    salary      NUMBER
);
/

CREATE TABLE SALARY_LOG (
    log_id      NUMBER GENERATED ALWAYS AS IDENTITY,
    emp_id      NUMBER,
    old_salary  NUMBER,
    new_salary  NUMBER,
    change_date DATE
);
/

CREATE OR REPLACE TRIGGER after_salary_update
AFTER UPDATE OF salary ON EMPLOYEE_TEST
FOR EACH ROW
BEGIN
    INSERT INTO SALARY_LOG (emp_id, old_salary, new_salary, change_date)
    VALUES (:OLD.emp_id, :OLD.salary, :NEW.salary, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('AFTER UPDATE Trigger Fired for Employee ID: ' || :OLD.emp_id);
    DBMS_OUTPUT.PUT_LINE('  Old Salary: ' || :OLD.salary || ' -> New Salary: ' || :NEW.salary);
END;
/

BEGIN
    INSERT INTO EMPLOYEE_TEST VALUES (101, 'Alice', 50000);
    INSERT INTO EMPLOYEE_TEST VALUES (102, 'Bob', 60000);
    INSERT INTO EMPLOYEE_TEST VALUES (103, 'Charlie', 45000);

    DBMS_OUTPUT.PUT_LINE('--- Employees Before Update ---');
    FOR rec IN (SELECT emp_id, emp_name, salary FROM EMPLOYEE_TEST ORDER BY emp_id) LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || rec.emp_id || ' | Name: ' || rec.emp_name || ' | Salary: ' || rec.salary);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Performing Salary Updates ---');
    UPDATE EMPLOYEE_TEST SET salary = 55000 WHERE emp_id = 101;
    DBMS_OUTPUT.PUT_LINE('');

    UPDATE EMPLOYEE_TEST SET salary = 70000 WHERE emp_id = 102;
    DBMS_OUTPUT.PUT_LINE('');

    UPDATE EMPLOYEE_TEST SET salary = 48000 WHERE emp_id = 103;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Employees After Update ---');
    FOR rec IN (SELECT emp_id, emp_name, salary FROM EMPLOYEE_TEST ORDER BY emp_id) LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || rec.emp_id || ' | Name: ' || rec.emp_name || ' | Salary: ' || rec.salary);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Salary Change Log ---');
    FOR rec IN (SELECT log_id, emp_id, old_salary, new_salary, TO_CHAR(change_date, 'DD-MON-YYYY') AS change_date FROM SALARY_LOG ORDER BY log_id) LOOP
        DBMS_OUTPUT.PUT_LINE('Log ID: ' || rec.log_id || ' | Emp ID: ' || rec.emp_id || ' | Old: ' || rec.old_salary || ' | New: ' || rec.new_salary || ' | Date: ' || rec.change_date);
    END LOOP;
END;
/

DROP TRIGGER after_salary_update;
DROP TABLE SALARY_LOG;
DROP TABLE EMPLOYEE_TEST;
