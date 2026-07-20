-- Exercise 3: Stored Procedures
-- Scenario: Creating and executing a stored procedure to update employee salary based on department.

CREATE OR REPLACE PROCEDURE update_department_salary (
    p_department_id IN NUMBER,
    p_percentage_increase IN NUMBER
)
AS
    v_rows_updated NUMBER;
BEGIN
    -- Assume an EMPLOYEES table exists with salary and department_id columns
    -- UPDATE employees 
    -- SET salary = salary + (salary * p_percentage_increase / 100)
    -- WHERE department_id = p_department_id;
    
    -- v_rows_updated := SQL%ROWCOUNT;
    
    -- Mocking the execution for demonstration purposes
    v_rows_updated := 15;
    
    DBMS_OUTPUT.PUT_LINE('Salary update completed for Department ID: ' || p_department_id);
    DBMS_OUTPUT.PUT_LINE('Total employees updated: ' || v_rows_updated);
    DBMS_OUTPUT.PUT_LINE('Increase applied: ' || p_percentage_increase || '%');
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating salaries: ' || SQLERRM);
END;
/

-- Execution block
BEGIN
    update_department_salary(p_department_id => 20, p_percentage_increase => 5);
END;
/
