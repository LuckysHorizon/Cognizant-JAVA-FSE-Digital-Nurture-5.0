SET SERVEROUTPUT ON;

DECLARE
    v_numerator   NUMBER := 50;
    v_denominator NUMBER := 0;
    v_result      NUMBER;
BEGIN
    v_result := v_numerator / v_denominator;
    DBMS_OUTPUT.PUT_LINE('Result: ' || v_result);
EXCEPTION
    WHEN ZERO_DIVIDE THEN
        DBMS_OUTPUT.PUT_LINE('Error: Division by zero is not allowed.');
        DBMS_OUTPUT.PUT_LINE('SQLCODE: ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('SQLERRM: ' || SQLERRM);
END;
/
