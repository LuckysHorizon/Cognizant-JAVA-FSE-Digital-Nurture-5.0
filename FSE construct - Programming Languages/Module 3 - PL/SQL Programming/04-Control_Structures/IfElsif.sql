SET SERVEROUTPUT ON;

DECLARE
    v_marks NUMBER := 78;
    v_grade CHAR(1);
BEGIN
    IF v_marks >= 90 THEN
        v_grade := 'A';
    ELSIF v_marks >= 80 THEN
        v_grade := 'B';
    ELSIF v_marks >= 70 THEN
        v_grade := 'C';
    ELSIF v_marks >= 60 THEN
        v_grade := 'D';
    ELSE
        v_grade := 'F';
    END IF;

    DBMS_OUTPUT.PUT_LINE('Marks : ' || v_marks);
    DBMS_OUTPUT.PUT_LINE('Grade : ' || v_grade);
END;
/
