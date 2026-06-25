SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EMPLOYEE_AUDIT CASCADE CONSTRAINTS';
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

CREATE TABLE EMPLOYEE_AUDIT (
    audit_id    NUMBER GENERATED ALWAYS AS IDENTITY,
    emp_name    VARCHAR2(50),
    action_type VARCHAR2(20),
    action_date DATE
);
/

CREATE OR REPLACE TRIGGER before_emp_insert
BEFORE INSERT ON EMPLOYEE_TEST
FOR EACH ROW
BEGIN
    IF :NEW.salary <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Salary must be greater than zero. Provided: ' || :NEW.salary);
    END IF;

    INSERT INTO EMPLOYEE_AUDIT (emp_name, action_type, action_date)
    VALUES (:NEW.emp_name, 'INSERT', SYSDATE);

    DBMS_OUTPUT.PUT_LINE('BEFORE INSERT Trigger Fired for: ' || :NEW.emp_name);
END;
/

DECLARE
    v_error_msg VARCHAR2(200);
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Test 1: Valid Insert (salary = 50000) ---');
    INSERT INTO EMPLOYEE_TEST VALUES (1, 'Alice', 50000);
    DBMS_OUTPUT.PUT_LINE('Insert successful for Alice.');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Test 2: Valid Insert (salary = 30000) ---');
    INSERT INTO EMPLOYEE_TEST VALUES (2, 'Bob', 30000);
    DBMS_OUTPUT.PUT_LINE('Insert successful for Bob.');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Test 3: Invalid Insert (salary = -500) ---');
    BEGIN
        INSERT INTO EMPLOYEE_TEST VALUES (3, 'Charlie', -500);
    EXCEPTION
        WHEN OTHERS THEN
            v_error_msg := SQLERRM;
            DBMS_OUTPUT.PUT_LINE('Error caught: ' || v_error_msg);
    END;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Test 4: Invalid Insert (salary = 0) ---');
    BEGIN
        INSERT INTO EMPLOYEE_TEST VALUES (4, 'Diana', 0);
    EXCEPTION
        WHEN OTHERS THEN
            v_error_msg := SQLERRM;
            DBMS_OUTPUT.PUT_LINE('Error caught: ' || v_error_msg);
    END;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Employee Test Table ---');
    FOR rec IN (SELECT emp_id, emp_name, salary FROM EMPLOYEE_TEST ORDER BY emp_id) LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || rec.emp_id || ' | Name: ' || rec.emp_name || ' | Salary: ' || rec.salary);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Audit Log ---');
    FOR rec IN (SELECT audit_id, emp_name, action_type, TO_CHAR(action_date, 'DD-MON-YYYY') AS action_date FROM EMPLOYEE_AUDIT ORDER BY audit_id) LOOP
        DBMS_OUTPUT.PUT_LINE('Audit ID: ' || rec.audit_id || ' | Name: ' || rec.emp_name || ' | Action: ' || rec.action_type || ' | Date: ' || rec.action_date);
    END LOOP;
END;
/

DROP TRIGGER before_emp_insert;
DROP TABLE EMPLOYEE_AUDIT;
DROP TABLE EMPLOYEE_TEST;
