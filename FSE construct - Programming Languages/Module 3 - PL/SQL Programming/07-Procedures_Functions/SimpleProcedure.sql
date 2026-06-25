CREATE OR REPLACE PROCEDURE display_welcome
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Welcome to PL/SQL Programming');
END display_welcome;
/

SET SERVEROUTPUT ON

EXEC display_welcome;

BEGIN
    display_welcome;
END;
/
