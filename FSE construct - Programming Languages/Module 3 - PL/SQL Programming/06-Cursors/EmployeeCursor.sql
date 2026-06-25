SET SERVEROUTPUT ON

DECLARE
    CURSOR c_all_emp IS
        SELECT employee_id, first_name, last_name, salary
        FROM HR.EMPLOYEES
        ORDER BY employee_id;

    v_total NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Employee Report Using Cursor ---');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE(RPAD('ID', 8) || RPAD('First Name', 15) || RPAD('Last Name', 15) || 'Salary');
    DBMS_OUTPUT.PUT_LINE(RPAD('-', 8, '-') || RPAD('-', 15, '-') || RPAD('-', 15, '-') || RPAD('-', 10, '-'));

    FOR rec IN c_all_emp LOOP
        DBMS_OUTPUT.PUT_LINE(
            RPAD(rec.employee_id, 8) ||
            RPAD(rec.first_name, 15) ||
            RPAD(rec.last_name, 15) ||
            rec.salary
        );
        v_total := v_total + 1;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Total Employees Processed: ' || v_total);
END;
/
