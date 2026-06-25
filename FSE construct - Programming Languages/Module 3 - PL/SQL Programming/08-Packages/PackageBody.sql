CREATE OR REPLACE PACKAGE BODY emp_pkg AS

    PROCEDURE format_output(p_label VARCHAR2, p_value VARCHAR2) IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE(p_label || ': ' || p_value);
    END format_output;

    PROCEDURE display_employee(p_emp_id NUMBER) IS
        v_first_name HR.EMPLOYEES.FIRST_NAME%TYPE;
        v_last_name  HR.EMPLOYEES.LAST_NAME%TYPE;
        v_salary     HR.EMPLOYEES.SALARY%TYPE;
    BEGIN
        SELECT FIRST_NAME, LAST_NAME, SALARY
        INTO v_first_name, v_last_name, v_salary
        FROM HR.EMPLOYEES
        WHERE EMPLOYEE_ID = p_emp_id;

        format_output('First Name', v_first_name);
        format_output('Last Name', v_last_name);
        format_output('Salary', TO_CHAR(v_salary));
    END display_employee;

    FUNCTION get_salary(p_emp_id NUMBER) RETURN NUMBER IS
        v_salary HR.EMPLOYEES.SALARY%TYPE;
    BEGIN
        SELECT SALARY
        INTO v_salary
        FROM HR.EMPLOYEES
        WHERE EMPLOYEE_ID = p_emp_id;

        RETURN v_salary;
    END get_salary;

END emp_pkg;
/
