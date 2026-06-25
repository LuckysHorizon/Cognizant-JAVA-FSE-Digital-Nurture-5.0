SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE show_dept_employees(p_dept_id IN NUMBER) IS
    CURSOR c_emp IS
        SELECT employee_id, first_name, last_name, email, salary, hire_date
        FROM HR.EMPLOYEES
        WHERE department_id = p_dept_id
        ORDER BY employee_id;

    v_rec       c_emp%ROWTYPE;
    v_count     NUMBER := 0;
    v_dept_name HR.DEPARTMENTS.DEPARTMENT_NAME%TYPE;
BEGIN
    BEGIN
        SELECT department_name INTO v_dept_name
        FROM HR.DEPARTMENTS
        WHERE department_id = p_dept_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Department ' || p_dept_id || ' does not exist.');
            RETURN;
    END;

    DBMS_OUTPUT.PUT_LINE('Department: ' || v_dept_name || ' (ID: ' || p_dept_id || ')');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 80, '-'));
    DBMS_OUTPUT.PUT_LINE(
        RPAD('EMP_ID', 8) || RPAD('NAME', 25) ||
        RPAD('EMAIL', 15) || RPAD('SALARY', 12) || 'HIRE_DATE'
    );
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 80, '-'));

    OPEN c_emp;
    LOOP
        FETCH c_emp INTO v_rec;
        EXIT WHEN c_emp%NOTFOUND;
        v_count := v_count + 1;
        DBMS_OUTPUT.PUT_LINE(
            RPAD(v_rec.employee_id, 8) ||
            RPAD(v_rec.first_name || ' ' || v_rec.last_name, 25) ||
            RPAD(v_rec.email, 15) ||
            RPAD(TO_CHAR(v_rec.salary, '99999.00'), 12) ||
            TO_CHAR(v_rec.hire_date, 'DD-MON-YYYY')
        );
    END LOOP;
    CLOSE c_emp;

    DBMS_OUTPUT.PUT_LINE(RPAD('-', 80, '-'));
    DBMS_OUTPUT.PUT_LINE('Total Employees: ' || v_count);
END;
/

BEGIN
    show_dept_employees(60);
    DBMS_OUTPUT.PUT_LINE(' ');
    show_dept_employees(10);
    DBMS_OUTPUT.PUT_LINE(' ');
    show_dept_employees(999);
END;
/

CREATE OR REPLACE FUNCTION get_dept_total_salary(p_dept_id IN NUMBER) RETURN NUMBER IS
    v_total     NUMBER := 0;
    v_dept_name HR.DEPARTMENTS.DEPARTMENT_NAME%TYPE;
BEGIN
    BEGIN
        SELECT department_name INTO v_dept_name
        FROM HR.DEPARTMENTS
        WHERE department_id = p_dept_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN -1;
    END;

    SELECT NVL(SUM(salary), 0) INTO v_total
    FROM HR.EMPLOYEES
    WHERE department_id = p_dept_id;

    RETURN v_total;
END;
/

DECLARE
    v_total NUMBER;
    TYPE t_dept_ids IS TABLE OF NUMBER;
    v_depts t_dept_ids := t_dept_ids(10, 20, 30, 60, 90, 999);
BEGIN
    DBMS_OUTPUT.PUT_LINE(RPAD('DEPT_ID', 10) || RPAD('TOTAL SALARY', 15) || 'STATUS');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 40, '-'));

    FOR i IN 1..v_depts.COUNT LOOP
        v_total := get_dept_total_salary(v_depts(i));
        IF v_total = -1 THEN
            DBMS_OUTPUT.PUT_LINE(RPAD(v_depts(i), 10) || RPAD('N/A', 15) || 'Department not found');
        ELSE
            DBMS_OUTPUT.PUT_LINE(RPAD(v_depts(i), 10) || RPAD(v_total, 15) || 'OK');
        END IF;
    END LOOP;
END;
/

DECLARE
    v_outer_result NUMBER;
    v_inner_result NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Outer Block Start ===');

    BEGIN
        DBMS_OUTPUT.PUT_LINE('  === Inner Block 1: Division by Zero ===');
        v_inner_result := 100 / 0;
        DBMS_OUTPUT.PUT_LINE('  This will not print.');
    EXCEPTION
        WHEN ZERO_DIVIDE THEN
            DBMS_OUTPUT.PUT_LINE('  Inner Block 1 Caught: Division by zero!');
            DBMS_OUTPUT.PUT_LINE('  SQLCODE: ' || SQLCODE);
    END;

    DBMS_OUTPUT.PUT_LINE('  Outer block continues after inner block 1.');

    BEGIN
        DBMS_OUTPUT.PUT_LINE('  === Inner Block 2: No Data Found ===');
        SELECT salary INTO v_inner_result
        FROM HR.EMPLOYEES
        WHERE employee_id = -9999;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('  Inner Block 2 Caught: No data found!');
    END;

    DBMS_OUTPUT.PUT_LINE('  Outer block continues after inner block 2.');

    BEGIN
        DBMS_OUTPUT.PUT_LINE('  === Inner Block 3: Value Error ===');
        DECLARE
            v_small VARCHAR2(2);
        BEGIN
            v_small := 'THIS IS TOO LONG';
        END;
    EXCEPTION
        WHEN VALUE_ERROR THEN
            DBMS_OUTPUT.PUT_LINE('  Inner Block 3 Caught: Value error!');
    END;

    DBMS_OUTPUT.PUT_LINE('=== Outer Block End: All inner exceptions handled ===');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Outer Block Caught Unexpected: ' || SQLERRM);
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE lab_emp CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE lab_emp_audit CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE lab_emp (
    employee_id   NUMBER PRIMARY KEY,
    first_name    VARCHAR2(50),
    last_name     VARCHAR2(50),
    email         VARCHAR2(100),
    salary        NUMBER,
    department_id NUMBER
);
/

CREATE SEQUENCE lab_emp_seq START WITH 1 INCREMENT BY 1 NOCACHE;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE lab_emp_seq';
    EXECUTE IMMEDIATE 'CREATE SEQUENCE lab_emp_seq START WITH 1 INCREMENT BY 1 NOCACHE';
EXCEPTION
    WHEN OTHERS THEN
        EXECUTE IMMEDIATE 'CREATE SEQUENCE lab_emp_seq START WITH 1 INCREMENT BY 1 NOCACHE';
END;
/

CREATE OR REPLACE PACKAGE emp_crud_pkg IS
    PROCEDURE create_employee(
        p_first_name    VARCHAR2,
        p_last_name     VARCHAR2,
        p_email         VARCHAR2,
        p_salary        NUMBER,
        p_department_id NUMBER
    );
    PROCEDURE read_employee(p_emp_id NUMBER);
    PROCEDURE read_all_employees;
    PROCEDURE update_employee_salary(p_emp_id NUMBER, p_new_salary NUMBER);
    PROCEDURE delete_employee(p_emp_id NUMBER);
END emp_crud_pkg;
/

CREATE OR REPLACE PACKAGE BODY emp_crud_pkg IS

    PROCEDURE create_employee(
        p_first_name    VARCHAR2,
        p_last_name     VARCHAR2,
        p_email         VARCHAR2,
        p_salary        NUMBER,
        p_department_id NUMBER
    ) IS
        v_id NUMBER;
    BEGIN
        SELECT lab_emp_seq.NEXTVAL INTO v_id FROM DUAL;
        INSERT INTO lab_emp (employee_id, first_name, last_name, email, salary, department_id)
        VALUES (v_id, p_first_name, p_last_name, p_email, p_salary, p_department_id);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Created Employee ID: ' || v_id || ' - ' || p_first_name || ' ' || p_last_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Duplicate employee record.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error Creating: ' || SQLERRM);
            ROLLBACK;
    END;

    PROCEDURE read_employee(p_emp_id NUMBER) IS
        v_rec lab_emp%ROWTYPE;
    BEGIN
        SELECT * INTO v_rec FROM lab_emp WHERE employee_id = p_emp_id;
        DBMS_OUTPUT.PUT_LINE('ID: ' || v_rec.employee_id);
        DBMS_OUTPUT.PUT_LINE('Name: ' || v_rec.first_name || ' ' || v_rec.last_name);
        DBMS_OUTPUT.PUT_LINE('Email: ' || v_rec.email);
        DBMS_OUTPUT.PUT_LINE('Salary: ' || v_rec.salary);
        DBMS_OUTPUT.PUT_LINE('Department: ' || v_rec.department_id);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee ' || p_emp_id || ' not found.');
    END;

    PROCEDURE read_all_employees IS
        CURSOR c_all IS SELECT * FROM lab_emp ORDER BY employee_id;
        v_count NUMBER := 0;
    BEGIN
        DBMS_OUTPUT.PUT_LINE(RPAD('ID', 6) || RPAD('NAME', 25) ||
                             RPAD('EMAIL', 20) || RPAD('SALARY', 10) || 'DEPT');
        DBMS_OUTPUT.PUT_LINE(RPAD('-', 71, '-'));

        FOR rec IN c_all LOOP
            v_count := v_count + 1;
            DBMS_OUTPUT.PUT_LINE(
                RPAD(rec.employee_id, 6) ||
                RPAD(rec.first_name || ' ' || rec.last_name, 25) ||
                RPAD(rec.email, 20) ||
                RPAD(rec.salary, 10) ||
                rec.department_id
            );
        END LOOP;

        IF v_count = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No employees found.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Total: ' || v_count || ' employees.');
        END IF;
    END;

    PROCEDURE update_employee_salary(p_emp_id NUMBER, p_new_salary NUMBER) IS
        v_old_salary NUMBER;
    BEGIN
        SELECT salary INTO v_old_salary FROM lab_emp WHERE employee_id = p_emp_id;
        UPDATE lab_emp SET salary = p_new_salary WHERE employee_id = p_emp_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ' || p_emp_id || ' salary updated: ' ||
                             v_old_salary || ' -> ' || p_new_salary);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee ' || p_emp_id || ' not found for update.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error Updating: ' || SQLERRM);
            ROLLBACK;
    END;

    PROCEDURE delete_employee(p_emp_id NUMBER) IS
        v_name VARCHAR2(100);
    BEGIN
        SELECT first_name || ' ' || last_name INTO v_name
        FROM lab_emp WHERE employee_id = p_emp_id;

        DELETE FROM lab_emp WHERE employee_id = p_emp_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Deleted Employee: ' || v_name || ' (ID: ' || p_emp_id || ')');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee ' || p_emp_id || ' not found for deletion.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error Deleting: ' || SQLERRM);
            ROLLBACK;
    END;

END emp_crud_pkg;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== CRUD Operations Demo ===');
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('--- CREATE ---');
    emp_crud_pkg.create_employee('Alice', 'Johnson', 'alice@test.com', 5000, 10);
    emp_crud_pkg.create_employee('Bob', 'Smith', 'bob@test.com', 6000, 20);
    emp_crud_pkg.create_employee('Charlie', 'Brown', 'charlie@test.com', 7000, 30);
    emp_crud_pkg.create_employee('Diana', 'Prince', 'diana@test.com', 8000, 10);
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('--- READ ALL ---');
    emp_crud_pkg.read_all_employees;
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('--- READ ONE ---');
    emp_crud_pkg.read_employee(1);
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('--- UPDATE ---');
    emp_crud_pkg.update_employee_salary(2, 7500);
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('--- DELETE ---');
    emp_crud_pkg.delete_employee(3);
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('--- FINAL STATE ---');
    emp_crud_pkg.read_all_employees;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE lab_audit_emp CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE lab_audit_log CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE lab_audit_emp (
    employee_id NUMBER PRIMARY KEY,
    first_name  VARCHAR2(50),
    salary      NUMBER
);
/

CREATE TABLE lab_audit_log (
    audit_id      NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    table_name    VARCHAR2(50),
    operation     VARCHAR2(10),
    employee_id   NUMBER,
    old_values    VARCHAR2(500),
    new_values    VARCHAR2(500),
    changed_by    VARCHAR2(50),
    changed_at    TIMESTAMP DEFAULT SYSTIMESTAMP
);
/

INSERT INTO lab_audit_emp VALUES (1, 'Alice', 5000);
INSERT INTO lab_audit_emp VALUES (2, 'Bob', 6000);
INSERT INTO lab_audit_emp VALUES (3, 'Charlie', 7000);
COMMIT;
/

CREATE OR REPLACE TRIGGER lab_audit_trail_trigger
AFTER INSERT OR UPDATE OR DELETE ON lab_audit_emp
FOR EACH ROW
DECLARE
    v_operation  VARCHAR2(10);
    v_old_values VARCHAR2(500);
    v_new_values VARCHAR2(500);
    v_emp_id     NUMBER;
BEGIN
    IF INSERTING THEN
        v_operation := 'INSERT';
        v_emp_id := :NEW.employee_id;
        v_old_values := NULL;
        v_new_values := 'ID=' || :NEW.employee_id ||
                        ', Name=' || :NEW.first_name ||
                        ', Salary=' || :NEW.salary;
    ELSIF UPDATING THEN
        v_operation := 'UPDATE';
        v_emp_id := :OLD.employee_id;
        v_old_values := 'ID=' || :OLD.employee_id ||
                        ', Name=' || :OLD.first_name ||
                        ', Salary=' || :OLD.salary;
        v_new_values := 'ID=' || :NEW.employee_id ||
                        ', Name=' || :NEW.first_name ||
                        ', Salary=' || :NEW.salary;
    ELSIF DELETING THEN
        v_operation := 'DELETE';
        v_emp_id := :OLD.employee_id;
        v_old_values := 'ID=' || :OLD.employee_id ||
                        ', Name=' || :OLD.first_name ||
                        ', Salary=' || :OLD.salary;
        v_new_values := NULL;
    END IF;

    INSERT INTO lab_audit_log (table_name, operation, employee_id, old_values, new_values, changed_by)
    VALUES ('LAB_AUDIT_EMP', v_operation, v_emp_id, v_old_values, v_new_values, USER);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Audit Trail Demo ===');
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('1. INSERT Operation:');
    INSERT INTO lab_audit_emp VALUES (4, 'Diana', 8000);
    DBMS_OUTPUT.PUT_LINE('   Inserted employee Diana.');

    DBMS_OUTPUT.PUT_LINE('2. UPDATE Operation:');
    UPDATE lab_audit_emp SET salary = 9500 WHERE employee_id = 1;
    DBMS_OUTPUT.PUT_LINE('   Updated Alice salary to 9500.');

    DBMS_OUTPUT.PUT_LINE('3. DELETE Operation:');
    DELETE FROM lab_audit_emp WHERE employee_id = 3;
    DBMS_OUTPUT.PUT_LINE('   Deleted employee Charlie.');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(' ');

    DBMS_OUTPUT.PUT_LINE('=== Audit Log Contents ===');
    DBMS_OUTPUT.PUT_LINE(RPAD('OPERATION', 12) || RPAD('EMP_ID', 8) ||
                         RPAD('OLD VALUES', 40) || 'NEW VALUES');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 100, '-'));

    FOR rec IN (SELECT operation, employee_id, old_values, new_values, changed_by, changed_at
                FROM lab_audit_log ORDER BY audit_id) LOOP
        DBMS_OUTPUT.PUT_LINE(
            RPAD(rec.operation, 12) ||
            RPAD(rec.employee_id, 8) ||
            RPAD(NVL(rec.old_values, 'N/A'), 40) ||
            NVL(rec.new_values, 'N/A')
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('=== Current Table State ===');
    FOR rec IN (SELECT * FROM lab_audit_emp ORDER BY employee_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  ID: ' || rec.employee_id ||
                             ', Name: ' || rec.first_name ||
                             ', Salary: ' || rec.salary);
    END LOOP;
END;
/
