CREATE OR REPLACE PROCEDURE format_phone(
    p_phone IN OUT VARCHAR2
)
IS
BEGIN
    p_phone := '(' || SUBSTR(p_phone, 1, 3) || ') ' ||
               SUBSTR(p_phone, 4, 3) || '-' ||
               SUBSTR(p_phone, 7, 4);
END format_phone;
/

SET SERVEROUTPUT ON

DECLARE
    v_phone VARCHAR2(20) := '9876543210';
BEGIN
    DBMS_OUTPUT.PUT_LINE('Before Formatting: ' || v_phone);
    format_phone(v_phone);
    DBMS_OUTPUT.PUT_LINE('After Formatting : ' || v_phone);
END;
/
