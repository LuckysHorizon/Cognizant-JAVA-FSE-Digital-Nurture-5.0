SET SERVEROUTPUT ON;

DECLARE
    v_salary NUMBER := -5000;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Validating Salary: ' || v_salary);

    IF v_salary < 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Salary cannot be negative. Entered value: ' || v_salary);
    END IF;

    DBMS_OUTPUT.PUT_LINE('Salary is valid: ' || v_salary);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error Code   : ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('Error Message: ' || SQLERRM);
END;
/
