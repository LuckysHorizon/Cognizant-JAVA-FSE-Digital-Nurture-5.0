SET SERVEROUTPUT ON;

DECLARE
    v_num NUMBER := 24;
BEGIN
    IF MOD(v_num, 2) = 0 THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is an even number.');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_num || ' is an odd number.');
    END IF;
END;
/
