SET SERVEROUTPUT ON

DECLARE
    CURSOR c_emp IS
        SELECT first_name, salary
        FROM HR.EMPLOYEES
        WHERE department_id = 10;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Explicit Cursor with FOR Loop ---');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE(RPAD('Employee Name', 25) || 'Salary');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 25, '-') || RPAD('-', 10, '-'));

    FOR rec IN c_emp LOOP
        DBMS_OUTPUT.PUT_LINE(RPAD(rec.first_name, 25) || rec.salary);
    END LOOP;
END;
/
