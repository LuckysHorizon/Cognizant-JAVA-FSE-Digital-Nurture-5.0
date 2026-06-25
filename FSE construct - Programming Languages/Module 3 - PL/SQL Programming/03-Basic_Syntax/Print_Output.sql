SET SERVEROUTPUT ON;

DECLARE
    v_name      VARCHAR2(30) := 'Arun Kumar';
    v_age       NUMBER(3) := 28;
    v_salary    NUMBER(10,2) := 75000.50;
    v_today     DATE := SYSDATE;
    v_city      VARCHAR2(20) := 'Chennai';
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- String Output ---');
    DBMS_OUTPUT.PUT_LINE('Hello, World!');
    DBMS_OUTPUT.PUT_LINE('Welcome to PL/SQL Programming.');

    DBMS_OUTPUT.PUT_LINE('--- Number Output ---');
    DBMS_OUTPUT.PUT_LINE(v_age);
    DBMS_OUTPUT.PUT_LINE(v_salary);
    DBMS_OUTPUT.PUT_LINE(100 + 200);

    DBMS_OUTPUT.PUT_LINE('--- Concatenation Output ---');
    DBMS_OUTPUT.PUT_LINE('Name: ' || v_name || ', Age: ' || v_age);
    DBMS_OUTPUT.PUT_LINE('City: ' || v_city || ', Salary: ' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Sum of 50 + 30 = ' || (50 + 30));

    DBMS_OUTPUT.PUT_LINE('--- Date Formatting with TO_CHAR ---');
    DBMS_OUTPUT.PUT_LINE('Default    : ' || v_today);
    DBMS_OUTPUT.PUT_LINE('DD-MON-YYYY: ' || TO_CHAR(v_today, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('YYYY/MM/DD : ' || TO_CHAR(v_today, 'YYYY/MM/DD'));
    DBMS_OUTPUT.PUT_LINE('Day, Month : ' || TO_CHAR(v_today, 'Day, Month DD, YYYY'));
    DBMS_OUTPUT.PUT_LINE('HH24:MI:SS : ' || TO_CHAR(v_today, 'DD-MON-YYYY HH24:MI:SS'));

    DBMS_OUTPUT.PUT_LINE('--- Partial Output with PUT ---');
    DBMS_OUTPUT.PUT('First ');
    DBMS_OUTPUT.PUT('Second ');
    DBMS_OUTPUT.PUT('Third');
    DBMS_OUTPUT.NEW_LINE;

    DBMS_OUTPUT.PUT('Name: ');
    DBMS_OUTPUT.PUT(v_name);
    DBMS_OUTPUT.NEW_LINE;

    DBMS_OUTPUT.PUT_LINE('--- Output Complete ---');
END;
/
