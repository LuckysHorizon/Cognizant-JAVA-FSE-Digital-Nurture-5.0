SET SERVEROUTPUT ON;

DECLARE
    v_first_name HR.EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME
    INTO v_first_name
    FROM HR.EMPLOYEES;

    DBMS_OUTPUT.PUT_LINE('Employee: ' || v_first_name);
EXCEPTION
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('Error: Query returned more than one row.');
        DBMS_OUTPUT.PUT_LINE('Use a cursor to handle multiple rows.');
        DBMS_OUTPUT.PUT_LINE('SQLCODE: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('SQLERRM: ' || SQLERRM);
END;
/
