SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE MANAGEMENT_TEST CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE MANAGEMENT_TEST (
    item_id     NUMBER PRIMARY KEY,
    item_name   VARCHAR2(50),
    quantity    NUMBER
);
/

CREATE OR REPLACE TRIGGER trg_before_insert_mgmt
BEFORE INSERT ON MANAGEMENT_TEST
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('Trigger FIRED: trg_before_insert_mgmt for item: ' || :NEW.item_name);
END;
/

CREATE OR REPLACE TRIGGER trg_before_update_mgmt
BEFORE UPDATE ON MANAGEMENT_TEST
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('Trigger FIRED: trg_before_update_mgmt for item: ' || :OLD.item_name);
END;
/

DECLARE
    v_status VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Step 1: Triggers Active (Default) ===');
    FOR rec IN (SELECT trigger_name, status FROM USER_TRIGGERS WHERE table_name = 'MANAGEMENT_TEST' ORDER BY trigger_name) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.trigger_name || ' -> ' || rec.status);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Inserting with triggers ENABLED:');
    INSERT INTO MANAGEMENT_TEST VALUES (1, 'Laptop', 10);
    DBMS_OUTPUT.PUT_LINE('');
END;
/

ALTER TRIGGER trg_before_insert_mgmt DISABLE;
/

DECLARE
    v_status VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Step 2: After DISABLE Single Trigger ===');
    FOR rec IN (SELECT trigger_name, status FROM USER_TRIGGERS WHERE table_name = 'MANAGEMENT_TEST' ORDER BY trigger_name) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.trigger_name || ' -> ' || rec.status);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Inserting with trg_before_insert_mgmt DISABLED:');
    INSERT INTO MANAGEMENT_TEST VALUES (2, 'Mouse', 50);
    DBMS_OUTPUT.PUT_LINE('  (No trigger message expected for insert)');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

ALTER TRIGGER trg_before_insert_mgmt ENABLE;
/

DECLARE
    v_status VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Step 3: After ENABLE Single Trigger ===');
    FOR rec IN (SELECT trigger_name, status FROM USER_TRIGGERS WHERE table_name = 'MANAGEMENT_TEST' ORDER BY trigger_name) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.trigger_name || ' -> ' || rec.status);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Inserting with trigger RE-ENABLED:');
    INSERT INTO MANAGEMENT_TEST VALUES (3, 'Keyboard', 30);
    DBMS_OUTPUT.PUT_LINE('');
END;
/

ALTER TABLE MANAGEMENT_TEST DISABLE ALL TRIGGERS;
/

DECLARE
    v_status VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Step 4: After DISABLE ALL TRIGGERS ===');
    FOR rec IN (SELECT trigger_name, status FROM USER_TRIGGERS WHERE table_name = 'MANAGEMENT_TEST' ORDER BY trigger_name) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.trigger_name || ' -> ' || rec.status);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Inserting with ALL triggers DISABLED:');
    INSERT INTO MANAGEMENT_TEST VALUES (4, 'Monitor', 15);
    DBMS_OUTPUT.PUT_LINE('  (No trigger messages expected)');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Updating with ALL triggers DISABLED:');
    UPDATE MANAGEMENT_TEST SET quantity = 20 WHERE item_id = 1;
    DBMS_OUTPUT.PUT_LINE('  (No trigger messages expected)');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

ALTER TABLE MANAGEMENT_TEST ENABLE ALL TRIGGERS;
/

DECLARE
    v_status VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Step 5: After ENABLE ALL TRIGGERS ===');
    FOR rec IN (SELECT trigger_name, status FROM USER_TRIGGERS WHERE table_name = 'MANAGEMENT_TEST' ORDER BY trigger_name) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.trigger_name || ' -> ' || rec.status);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Inserting with ALL triggers RE-ENABLED:');
    INSERT INTO MANAGEMENT_TEST VALUES (5, 'Headphones', 25);
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Updating with ALL triggers RE-ENABLED:');
    UPDATE MANAGEMENT_TEST SET quantity = 100 WHERE item_id = 2;
    DBMS_OUTPUT.PUT_LINE('');
END;
/

DROP TRIGGER trg_before_insert_mgmt;
DROP TRIGGER trg_before_update_mgmt;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Step 6: After DROP TRIGGER ===');

    DBMS_OUTPUT.PUT_LINE('Remaining triggers on MANAGEMENT_TEST:');
    FOR rec IN (SELECT trigger_name, status FROM USER_TRIGGERS WHERE table_name = 'MANAGEMENT_TEST' ORDER BY trigger_name) LOOP
        DBMS_OUTPUT.PUT_LINE('  ' || rec.trigger_name || ' -> ' || rec.status);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('  (No triggers should be listed)');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('Inserting after triggers are DROPPED:');
    INSERT INTO MANAGEMENT_TEST VALUES (6, 'Webcam', 40);
    DBMS_OUTPUT.PUT_LINE('  Insert successful. No triggers fired.');
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('--- Final Table Contents ---');
    FOR rec IN (SELECT item_id, item_name, quantity FROM MANAGEMENT_TEST ORDER BY item_id) LOOP
        DBMS_OUTPUT.PUT_LINE('  ID: ' || rec.item_id || ' | Name: ' || rec.item_name || ' | Qty: ' || rec.quantity);
    END LOOP;
END;
/

DROP TABLE MANAGEMENT_TEST;
