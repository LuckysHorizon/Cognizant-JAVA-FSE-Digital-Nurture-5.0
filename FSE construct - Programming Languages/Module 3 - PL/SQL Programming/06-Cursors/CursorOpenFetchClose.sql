SET SERVEROUTPUT ON

DECLARE
    CURSOR c_emp IS
        SELECT employee_id, first_name, salary
        FROM HR.EMPLOYEES
        WHERE department_id = 50;

    v_emp_id   HR.EMPLOYEES.employee_id%TYPE;
    v_name     HR.EMPLOYEES.first_name%TYPE;
    v_salary   HR.EMPLOYEES.salary%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Cursor OPEN-FETCH-CLOSE Lifecycle ---');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE(RPAD('ID', 8) || RPAD('Name', 20) || 'Salary');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 8, '-') || RPAD('-', 20, '-') || RPAD('-', 10, '-'));

    OPEN c_emp;

    LOOP
        FETCH c_emp INTO v_emp_id, v_name, v_salary;
        EXIT WHEN c_emp%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(RPAD(v_emp_id, 8) || RPAD(v_name, 20) || v_salary);
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Total rows fetched: ' || c_emp%ROWCOUNT);

    CLOSE c_emp;
END;
/
