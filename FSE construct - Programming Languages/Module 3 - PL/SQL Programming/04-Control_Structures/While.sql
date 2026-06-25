SET SERVEROUTPUT ON;

DECLARE
    v_counter NUMBER := 1;
BEGIN
    WHILE v_counter <= 10 LOOP
        DBMS_OUTPUT.PUT_LINE('Counter: ' || v_counter);
        v_counter := v_counter + 1;
    END LOOP;
END;
/
