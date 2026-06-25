SET SERVEROUTPUT ON;

DECLARE
    v_first_name HR.EMPLOYEES.FIRST_NAME%TYPE;
    v_last_name  HR.EMPLOYEES.LAST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME, LAST_NAME
    INTO v_first_name, v_last_name
    FROM HR.EMPLOYEES
    WHERE EMPLOYEE_ID = 9999;

    DBMS_OUTPUT.PUT_LINE('Employee: ' || v_first_name || ' ' || v_last_name);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: No employee found with the given ID.');
        DBMS_OUTPUT.PUT_LINE('SQLCODE: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('SQLERRM: ' || SQLERRM);
END;
/
