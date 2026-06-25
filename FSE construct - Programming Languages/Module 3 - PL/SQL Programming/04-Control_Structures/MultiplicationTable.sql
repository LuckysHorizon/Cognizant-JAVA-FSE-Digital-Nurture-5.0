SET SERVEROUTPUT ON;

DECLARE
    v_num    NUMBER := 5;
    v_result NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Multiplication Table for ' || v_num);
    DBMS_OUTPUT.PUT_LINE('----------------------------');

    FOR v_i IN 1..10 LOOP
        v_result := v_num * v_i;
        DBMS_OUTPUT.PUT_LINE(v_num || ' x ' || v_i || ' = ' || v_result);
    END LOOP;
END;
/
