CREATE OR REPLACE PROCEDURE display_employee(
    p_emp_id IN NUMBER
)
IS
    v_first_name    HR.EMPLOYEES.FIRST_NAME%TYPE;
    v_salary        HR.EMPLOYEES.SALARY%TYPE;
BEGIN
    SELECT first_name, salary
    INTO v_first_name, v_salary
    FROM HR.EMPLOYEES
    WHERE employee_id = p_emp_id;

    DBMS_OUTPUT.PUT_LINE('Employee Name  : ' || v_first_name);
    DBMS_OUTPUT.PUT_LINE('Employee Salary: ' || v_salary);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No employee found with ID: ' || p_emp_id);
END display_employee;
/

SET SERVEROUTPUT ON

BEGIN
    display_employee(100);
END;
/
