SET SERVEROUTPUT ON;

DECLARE
    v_num NUMBER := 15;
BEGIN
    IF v_num > 0 THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is a positive number.');
    END IF;
END;
/
