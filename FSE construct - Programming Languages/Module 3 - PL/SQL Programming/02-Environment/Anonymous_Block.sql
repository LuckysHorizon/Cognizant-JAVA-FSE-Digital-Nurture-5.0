SET SERVEROUTPUT ON;

DECLARE
    v_employee_name VARCHAR2(50);
    v_salary        NUMBER(10, 2);
BEGIN
    v_employee_name := 'Rahul Sharma';
    v_salary := 55000.00;

    DBMS_OUTPUT.PUT_LINE('Employee Name : ' || v_employee_name);
    DBMS_OUTPUT.PUT_LINE('Salary        : Rs.' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Status        : Active Employee');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
