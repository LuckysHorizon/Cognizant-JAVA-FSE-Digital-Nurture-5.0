SET SERVEROUTPUT ON;
SET VERIFY OFF;

DECLARE
    v_emp_id        NUMBER := &enter_employee_id;
    v_bonus_pct     NUMBER := &enter_bonus_percentage;

    v_emp_name      VARCHAR2(50);
    v_salary        NUMBER(10,2);
    v_bonus         NUMBER(10,2);
    v_total         NUMBER(10,2);
BEGIN
    SELECT FIRST_NAME || ' ' || LAST_NAME, SALARY
    INTO v_emp_name, v_salary
    FROM HR.EMPLOYEES
    WHERE EMPLOYEE_ID = v_emp_id;

    v_bonus := v_salary * (v_bonus_pct / 100);
    v_total := v_salary + v_bonus;

    DBMS_OUTPUT.PUT_LINE('--- Employee Bonus Calculator ---');
    DBMS_OUTPUT.PUT_LINE('Employee ID     : ' || v_emp_id);
    DBMS_OUTPUT.PUT_LINE('Employee Name   : ' || v_emp_name);
    DBMS_OUTPUT.PUT_LINE('Current Salary  : ' || TO_CHAR(v_salary, '99,999.00'));
    DBMS_OUTPUT.PUT_LINE('Bonus Percent   : ' || v_bonus_pct || '%');
    DBMS_OUTPUT.PUT_LINE('Bonus Amount    : ' || TO_CHAR(v_bonus, '99,999.00'));
    DBMS_OUTPUT.PUT_LINE('Total Pay       : ' || TO_CHAR(v_total, '99,999.00'));
END;
/

DECLARE
    v_dept_name     VARCHAR2(50) := '&&enter_department_name';
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Persistent Substitution Variable ---');
    DBMS_OUTPUT.PUT_LINE('Department : ' || v_dept_name);
    DBMS_OUTPUT.PUT_LINE('You entered: ' || v_dept_name);
    DBMS_OUTPUT.PUT_LINE('Notice: && variable is asked only once per session.');
END;
/

UNDEFINE enter_department_name;
