SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE greet_employee (
    p_employee_name IN VARCHAR2
)
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Welcome to the Organization, ' || p_employee_name || '!');
    DBMS_OUTPUT.PUT_LINE('We are glad to have you on the team.');
END greet_employee;
/

BEGIN
    greet_employee('Rahul Sharma');
END;
/
