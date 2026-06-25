SET SERVEROUTPUT ON;

BEGIN
    FOR v_counter IN REVERSE 1..10 LOOP
        DBMS_OUTPUT.PUT_LINE('Counter: ' || v_counter);
    END LOOP;
END;
/
