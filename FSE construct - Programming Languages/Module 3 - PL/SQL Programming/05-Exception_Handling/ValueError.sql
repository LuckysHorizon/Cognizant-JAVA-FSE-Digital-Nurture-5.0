SET SERVEROUTPUT ON;

DECLARE
    v_code VARCHAR2(5);
BEGIN
    v_code := 'ABCDEFGHIJ';
    DBMS_OUTPUT.PUT_LINE('Code: ' || v_code);
EXCEPTION
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Error: Value is too large or has a data type mismatch.');
        DBMS_OUTPUT.PUT_LINE('SQLCODE: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('SQLERRM: ' || SQLERRM);
END;
/
