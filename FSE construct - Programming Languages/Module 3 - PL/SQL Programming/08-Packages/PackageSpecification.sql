CREATE OR REPLACE PACKAGE emp_pkg AS

    tax_rate CONSTANT NUMBER := 0.30;

    PROCEDURE display_employee(p_emp_id NUMBER);

    FUNCTION get_salary(p_emp_id NUMBER) RETURN NUMBER;

END emp_pkg;
/
