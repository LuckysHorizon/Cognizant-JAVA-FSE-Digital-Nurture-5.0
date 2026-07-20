-- Exercise 1: Control Structures
-- Scenario: Employee Bonus Calculation based on performance rating using IF/ELSE and LOOPS.

DECLARE
    v_emp_id NUMBER := 101;
    v_rating NUMBER := 4;
    v_salary NUMBER := 50000;
    v_bonus NUMBER := 0;
BEGIN
    -- IF/ELSE control structure
    IF v_rating = 5 THEN
        v_bonus := v_salary * 0.20;
    ELSIF v_rating = 4 THEN
        v_bonus := v_salary * 0.15;
    ELSIF v_rating = 3 THEN
        v_bonus := v_salary * 0.10;
    ELSE
        v_bonus := v_salary * 0.05;
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('Employee ID: ' || v_emp_id);
    DBMS_OUTPUT.PUT_LINE('Calculated Bonus: $' || v_bonus);

    -- Basic LOOP structure
    DBMS_OUTPUT.PUT_LINE('--- Processing Bonus Payout Installments ---');
    DECLARE
        v_installments NUMBER := 3;
        v_paid NUMBER := 0;
        v_amount_per_installment NUMBER := v_bonus / v_installments;
    BEGIN
        FOR i IN 1..v_installments LOOP
            v_paid := v_paid + v_amount_per_installment;
            DBMS_OUTPUT.PUT_LINE('Installment ' || i || ' paid: $' || v_amount_per_installment || ' | Total Paid: $' || v_paid);
        END LOOP;
    END;
END;
/
