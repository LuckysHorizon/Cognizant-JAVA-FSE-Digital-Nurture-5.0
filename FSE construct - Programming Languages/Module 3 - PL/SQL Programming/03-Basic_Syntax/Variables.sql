SET SERVEROUTPUT ON;

DECLARE
    v_first_name    VARCHAR2(50) := 'Rahul';
    v_age           NUMBER(3) := 25;
    v_join_date     DATE := SYSDATE;
    v_is_active     BOOLEAN := TRUE;

    v_city          VARCHAR2(30) DEFAULT 'Mumbai';
    v_salary        NUMBER(10,2) DEFAULT 50000.75;

    c_tax_rate      CONSTANT NUMBER(5,2) := 18.50;
    c_company       CONSTANT VARCHAR2(30) := 'Cognizant';

    v_emp_name      HR.EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME
    INTO v_emp_name
    FROM HR.EMPLOYEES
    WHERE EMPLOYEE_ID = 100;

    DBMS_OUTPUT.PUT_LINE('First Name    : ' || v_first_name);
    DBMS_OUTPUT.PUT_LINE('Age           : ' || v_age);
    DBMS_OUTPUT.PUT_LINE('Join Date     : ' || TO_CHAR(v_join_date, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('City          : ' || v_city);
    DBMS_OUTPUT.PUT_LINE('Salary        : ' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Tax Rate      : ' || c_tax_rate || '%');
    DBMS_OUTPUT.PUT_LINE('Company       : ' || c_company);
    DBMS_OUTPUT.PUT_LINE('Employee Name : ' || v_emp_name);

    IF v_is_active THEN
        DBMS_OUTPUT.PUT_LINE('Is Active     : Yes');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Is Active     : No');
    END IF;
END;
/
