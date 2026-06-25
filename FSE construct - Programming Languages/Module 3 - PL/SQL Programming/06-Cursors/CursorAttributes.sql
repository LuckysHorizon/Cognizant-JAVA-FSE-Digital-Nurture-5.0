SET SERVEROUTPUT ON

DECLARE
    CURSOR c_emp IS
        SELECT first_name, salary
        FROM HR.EMPLOYEES
        WHERE department_id = 10;

    v_name   HR.EMPLOYEES.first_name%TYPE;
    v_salary HR.EMPLOYEES.salary%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Cursor Attributes Demo ---');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Before OPEN:');
    DBMS_OUTPUT.PUT_LINE('  %ISOPEN : ' || CASE WHEN c_emp%ISOPEN THEN 'TRUE' ELSE 'FALSE' END);
    DBMS_OUTPUT.PUT_LINE('');

    OPEN c_emp;

    DBMS_OUTPUT.PUT_LINE('After OPEN:');
    DBMS_OUTPUT.PUT_LINE('  %ISOPEN   : ' || CASE WHEN c_emp%ISOPEN THEN 'TRUE' ELSE 'FALSE' END);
    DBMS_OUTPUT.PUT_LINE('  %ROWCOUNT : ' || c_emp%ROWCOUNT);
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Fetching rows:');
    LOOP
        FETCH c_emp INTO v_name, v_salary;
        EXIT WHEN c_emp%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('  Fetched: ' || RPAD(v_name, 15) || ' | Salary: ' || v_salary);
        DBMS_OUTPUT.PUT_LINE('    %FOUND    : ' || CASE WHEN c_emp%FOUND THEN 'TRUE' ELSE 'FALSE' END);
        DBMS_OUTPUT.PUT_LINE('    %NOTFOUND : ' || CASE WHEN c_emp%NOTFOUND THEN 'TRUE' ELSE 'FALSE' END);
        DBMS_OUTPUT.PUT_LINE('    %ROWCOUNT : ' || c_emp%ROWCOUNT);
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('After last FETCH (no more rows):');
    DBMS_OUTPUT.PUT_LINE('  %FOUND    : ' || CASE WHEN c_emp%FOUND THEN 'TRUE' ELSE 'FALSE' END);
    DBMS_OUTPUT.PUT_LINE('  %NOTFOUND : ' || CASE WHEN c_emp%NOTFOUND THEN 'TRUE' ELSE 'FALSE' END);
    DBMS_OUTPUT.PUT_LINE('  %ROWCOUNT : ' || c_emp%ROWCOUNT);
    DBMS_OUTPUT.PUT_LINE('');

    CLOSE c_emp;

    DBMS_OUTPUT.PUT_LINE('After CLOSE:');
    DBMS_OUTPUT.PUT_LINE('  %ISOPEN : ' || CASE WHEN c_emp%ISOPEN THEN 'TRUE' ELSE 'FALSE' END);
END;
/
