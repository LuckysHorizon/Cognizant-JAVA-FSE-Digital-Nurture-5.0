CREATE OR REPLACE FUNCTION get_emp_salary(
    p_emp_id NUMBER
)
RETURN NUMBER
IS
    v_salary HR.EMPLOYEES.SALARY%TYPE;
BEGIN
    SELECT salary
    INTO v_salary
    FROM HR.EMPLOYEES
    WHERE employee_id = p_emp_id;

    RETURN v_salary;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
END get_emp_salary;
/

SET SERVEROUTPUT ON

DECLARE
    v_result NUMBER;
BEGIN
    v_result := get_emp_salary(101);
    DBMS_OUTPUT.PUT_LINE('Salary of Employee 101: ' || v_result);
END;
/
