CREATE OR REPLACE FUNCTION get_annual_salary(
    p_monthly_salary NUMBER
)
RETURN NUMBER
IS
BEGIN
    RETURN p_monthly_salary * 12;
END get_annual_salary;
/

SELECT get_annual_salary(50000) AS annual_salary FROM DUAL;

SET SERVEROUTPUT ON

DECLARE
    v_annual_salary NUMBER;
BEGIN
    v_annual_salary := get_annual_salary(50000);
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || v_annual_salary);
END;
/
