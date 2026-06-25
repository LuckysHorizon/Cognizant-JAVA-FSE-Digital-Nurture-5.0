SET SERVEROUTPUT ON;

BEGIN
    FOR v_num IN 1..20 LOOP
        IF MOD(v_num, 2) = 0 THEN
            DBMS_OUTPUT.PUT_LINE(v_num);
        END IF;
    END LOOP;
END;
/
