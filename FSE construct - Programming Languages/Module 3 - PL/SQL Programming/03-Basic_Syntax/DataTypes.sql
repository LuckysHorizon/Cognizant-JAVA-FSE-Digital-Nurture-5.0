SET SERVEROUTPUT ON;

DECLARE
    v_price         NUMBER(10,2) := 2599.99;
    v_product       VARCHAR2(50) := 'Wireless Headphones';
    v_grade         CHAR(10) := 'A';
    v_order_date    DATE := TO_DATE('15-JUN-2026', 'DD-MON-YYYY');
    v_in_stock      BOOLEAN := TRUE;
    v_counter       BINARY_INTEGER := -500;
    v_quantity      PLS_INTEGER := 1200;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- PL/SQL Data Types Demo ---');
    DBMS_OUTPUT.PUT_LINE('NUMBER(10,2)     : ' || v_price);
    DBMS_OUTPUT.PUT_LINE('VARCHAR2(50)     : ' || v_product);
    DBMS_OUTPUT.PUT_LINE('CHAR(10)         : [' || v_grade || ']');
    DBMS_OUTPUT.PUT_LINE('DATE             : ' || TO_CHAR(v_order_date, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('BINARY_INTEGER   : ' || v_counter);
    DBMS_OUTPUT.PUT_LINE('PLS_INTEGER      : ' || v_quantity);

    IF v_in_stock THEN
        DBMS_OUTPUT.PUT_LINE('BOOLEAN          : TRUE');
    ELSE
        DBMS_OUTPUT.PUT_LINE('BOOLEAN          : FALSE');
    END IF;

    DBMS_OUTPUT.PUT_LINE('CHAR is padded with spaces to fill 10 characters.');
    DBMS_OUTPUT.PUT_LINE('BOOLEAN cannot be printed directly, needs IF check.');
END;
/
