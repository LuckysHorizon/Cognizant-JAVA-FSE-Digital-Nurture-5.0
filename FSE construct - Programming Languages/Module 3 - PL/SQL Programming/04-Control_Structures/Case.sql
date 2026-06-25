SET SERVEROUTPUT ON;

DECLARE
    v_day_num  NUMBER := 3;
    v_day_name VARCHAR2(20);
    v_marks    NUMBER := 85;
    v_grade    VARCHAR2(10);
BEGIN
    v_day_name := CASE v_day_num
        WHEN 1 THEN 'Monday'
        WHEN 2 THEN 'Tuesday'
        WHEN 3 THEN 'Wednesday'
        WHEN 4 THEN 'Thursday'
        WHEN 5 THEN 'Friday'
        WHEN 6 THEN 'Saturday'
        WHEN 7 THEN 'Sunday'
        ELSE 'Invalid Day'
    END;

    DBMS_OUTPUT.PUT_LINE('Simple CASE Result:');
    DBMS_OUTPUT.PUT_LINE('Day ' || v_day_num || ' is ' || v_day_name);

    DBMS_OUTPUT.PUT_LINE('---');

    v_grade := CASE
        WHEN v_marks >= 90 THEN 'A'
        WHEN v_marks >= 80 THEN 'B'
        WHEN v_marks >= 70 THEN 'C'
        WHEN v_marks >= 60 THEN 'D'
        ELSE 'F'
    END;

    DBMS_OUTPUT.PUT_LINE('Searched CASE Result:');
    DBMS_OUTPUT.PUT_LINE('Marks: ' || v_marks || ', Grade: ' || v_grade);
END;
/
