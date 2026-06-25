SET SERVEROUTPUT ON;

DECLARE
    v_emp_name      VARCHAR2(50);
    v_emp_salary    NUMBER(10,2);
    v_department    VARCHAR2(30);

    v_bonus         NUMBER(10,2) := 5000.00;
    v_tax           NUMBER(5,2) := 18.00;

    v_status        VARCHAR2(20) DEFAULT 'Active';
    v_country       VARCHAR2(30) DEFAULT 'India';

    v_emp_id        NUMBER(6) NOT NULL := 100;
    v_max_limit     NUMBER(4) NOT NULL DEFAULT 9999;
BEGIN
    SELECT FIRST_NAME, SALARY, DEPARTMENT_NAME
    INTO v_emp_name, v_emp_salary, v_department
    FROM HR.EMPLOYEES E
    JOIN HR.DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
    WHERE E.EMPLOYEE_ID = v_emp_id;

    v_bonus := v_emp_salary * 0.10;
    v_tax := v_emp_salary * 0.18;

    DBMS_OUTPUT.PUT_LINE('--- Assignment Operator Demo ---');
    DBMS_OUTPUT.PUT_LINE('Employee ID  : ' || v_emp_id);
    DBMS_OUTPUT.PUT_LINE('Employee     : ' || v_emp_name);
    DBMS_OUTPUT.PUT_LINE('Salary       : ' || v_emp_salary);
    DBMS_OUTPUT.PUT_LINE('Department   : ' || v_department);
    DBMS_OUTPUT.PUT_LINE('Bonus (10%)  : ' || v_bonus);
    DBMS_OUTPUT.PUT_LINE('Tax (18%)    : ' || v_tax);
    DBMS_OUTPUT.PUT_LINE('Status       : ' || v_status);
    DBMS_OUTPUT.PUT_LINE('Country      : ' || v_country);
    DBMS_OUTPUT.PUT_LINE('Max Limit    : ' || v_max_limit);
END;
/
