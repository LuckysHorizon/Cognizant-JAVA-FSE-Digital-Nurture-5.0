CREATE OR REPLACE PROCEDURE get_employee_salary(
    p_emp_id    IN  NUMBER,
    p_salary    OUT NUMBER
)
IS
BEGIN
    SELECT salary
    INTO p_salary
    FROM HR.EMPLOYEES
    WHERE employee_id = p_emp_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_salary := 0;
END get_employee_salary;
/

SET SERVEROUTPUT ON

DECLARE
    v_salary    NUMBER;
BEGIN
    get_employee_salary(100, v_salary);
    DBMS_OUTPUT.PUT_LINE('Salary of Employee 100: ' || v_salary);
END;
/
