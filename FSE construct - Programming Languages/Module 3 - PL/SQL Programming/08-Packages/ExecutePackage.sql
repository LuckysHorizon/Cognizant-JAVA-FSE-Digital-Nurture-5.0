SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Employee Details ---');
    emp_pkg.display_employee(100);

    DBMS_OUTPUT.PUT_LINE('');

    DECLARE
        v_salary NUMBER;
        v_tax    NUMBER;
    BEGIN
        v_salary := emp_pkg.get_salary(100);
        DBMS_OUTPUT.PUT_LINE('Salary: ' || v_salary);

        v_tax := v_salary * emp_pkg.tax_rate;
        DBMS_OUTPUT.PUT_LINE('Tax Rate: ' || emp_pkg.tax_rate);
        DBMS_OUTPUT.PUT_LINE('Tax Amount: ' || v_tax);
        DBMS_OUTPUT.PUT_LINE('Net Salary: ' || (v_salary - v_tax));
    END;
END;
/
