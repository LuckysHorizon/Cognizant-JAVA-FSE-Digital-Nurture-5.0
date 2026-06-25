SET SERVEROUTPUT ON;

DECLARE
    insufficient_balance EXCEPTION;
    v_balance            NUMBER := 5000;
    v_withdrawal_amount  NUMBER := 8000;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Current Balance  : ' || v_balance);
    DBMS_OUTPUT.PUT_LINE('Withdrawal Amount: ' || v_withdrawal_amount);

    IF v_withdrawal_amount > v_balance THEN
        RAISE insufficient_balance;
    END IF;

    v_balance := v_balance - v_withdrawal_amount;
    DBMS_OUTPUT.PUT_LINE('Transaction Successful.');
    DBMS_OUTPUT.PUT_LINE('Remaining Balance: ' || v_balance);
EXCEPTION
    WHEN insufficient_balance THEN
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient balance for this withdrawal.');
        DBMS_OUTPUT.PUT_LINE('Please enter an amount less than or equal to ' || v_balance);
END;
/
