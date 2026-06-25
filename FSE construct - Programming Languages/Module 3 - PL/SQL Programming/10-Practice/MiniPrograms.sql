SET SERVEROUTPUT ON;

DECLARE
    v_message VARCHAR2(50) := 'Hello World';
BEGIN
    DBMS_OUTPUT.PUT_LINE(v_message);
END;
/

DECLARE
    v_num1 NUMBER := 10;
    v_num2 NUMBER := 20;
    v_sum  NUMBER;
BEGIN
    v_sum := v_num1 + v_num2;
    DBMS_OUTPUT.PUT_LINE('Sum = ' || v_sum);
END;
/

DECLARE
    v_radius NUMBER := 7;
    v_area   NUMBER;
    v_pi     CONSTANT NUMBER := 3.14159;
BEGIN
    v_area := v_pi * v_radius * v_radius;
    DBMS_OUTPUT.PUT_LINE('Area of Circle = ' || ROUND(v_area, 2));
END;
/

DECLARE
    v_a NUMBER := 25;
    v_b NUMBER := 50;
    v_temp NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Before Swap: a = ' || v_a || ', b = ' || v_b);
    v_temp := v_a;
    v_a := v_b;
    v_b := v_temp;
    DBMS_OUTPUT.PUT_LINE('After Swap: a = ' || v_a || ', b = ' || v_b);
END;
/

DECLARE
    v_a NUMBER := 45;
    v_b NUMBER := 78;
    v_c NUMBER := 32;
    v_max NUMBER;
BEGIN
    IF v_a >= v_b AND v_a >= v_c THEN
        v_max := v_a;
    ELSIF v_b >= v_a AND v_b >= v_c THEN
        v_max := v_b;
    ELSE
        v_max := v_c;
    END IF;
    DBMS_OUTPUT.PUT_LINE('Maximum = ' || v_max);
END;
/

DECLARE
    v_num NUMBER := -15;
BEGIN
    IF v_num > 0 THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Positive');
    ELSIF v_num < 0 THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Negative');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Zero');
    END IF;
END;
/

DECLARE
    v_num NUMBER := 27;
BEGIN
    IF MOD(v_num, 2) = 0 THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Even');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Odd');
    END IF;
END;
/

DECLARE
    v_principal NUMBER := 1000;
    v_rate      NUMBER := 5;
    v_time      NUMBER := 2;
    v_si        NUMBER;
BEGIN
    v_si := (v_principal * v_rate * v_time) / 100;
    DBMS_OUTPUT.PUT_LINE('Simple Interest = ' || v_si);
END;
/

DECLARE
    v_celsius    NUMBER := 37;
    v_fahrenheit NUMBER;
BEGIN
    v_fahrenheit := (v_celsius * 9 / 5) + 32;
    DBMS_OUTPUT.PUT_LINE(v_celsius || ' C = ' || v_fahrenheit || ' F');
END;
/

DECLARE
    v_num  NUMBER := 144;
    v_sqrt NUMBER;
BEGIN
    v_sqrt := SQRT(v_num);
    DBMS_OUTPUT.PUT_LINE('Square Root of ' || v_num || ' = ' || v_sqrt);
END;
/

BEGIN
    FOR i IN 1..10 LOOP
        DBMS_OUTPUT.PUT_LINE('Number: ' || i);
    END LOOP;
END;
/

BEGIN
    FOR i IN REVERSE 1..10 LOOP
        DBMS_OUTPUT.PUT_LINE('Number: ' || i);
    END LOOP;
END;
/

DECLARE
    v_n   NUMBER := 100;
    v_sum NUMBER := 0;
BEGIN
    FOR i IN 1..v_n LOOP
        v_sum := v_sum + i;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Sum of first ' || v_n || ' natural numbers = ' || v_sum);
END;
/

DECLARE
    v_num       NUMBER := 5;
    v_factorial NUMBER := 1;
BEGIN
    FOR i IN 1..v_num LOOP
        v_factorial := v_factorial * i;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Factorial of ' || v_num || ' = ' || v_factorial);
END;
/

DECLARE
    v_num      NUMBER := 17;
    v_is_prime BOOLEAN := TRUE;
BEGIN
    IF v_num <= 1 THEN
        v_is_prime := FALSE;
    ELSE
        FOR i IN 2..TRUNC(SQRT(v_num)) LOOP
            IF MOD(v_num, i) = 0 THEN
                v_is_prime := FALSE;
                EXIT;
            END IF;
        END LOOP;
    END IF;

    IF v_is_prime THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Prime');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Not Prime');
    END IF;
END;
/

DECLARE
    v_terms NUMBER := 10;
    v_a     NUMBER := 0;
    v_b     NUMBER := 1;
    v_next  NUMBER;
BEGIN
    DBMS_OUTPUT.PUT('Fibonacci: ' || v_a || ' ' || v_b);
    FOR i IN 3..v_terms LOOP
        v_next := v_a + v_b;
        DBMS_OUTPUT.PUT(' ' || v_next);
        v_a := v_b;
        v_b := v_next;
    END LOOP;
    DBMS_OUTPUT.NEW_LINE;
END;
/

DECLARE
    v_num      NUMBER := 12345;
    v_reversed NUMBER := 0;
    v_temp     NUMBER;
BEGIN
    v_temp := v_num;
    WHILE v_temp > 0 LOOP
        v_reversed := v_reversed * 10 + MOD(v_temp, 10);
        v_temp := TRUNC(v_temp / 10);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Reverse of ' || v_num || ' = ' || v_reversed);
END;
/

DECLARE
    v_num      NUMBER := 121;
    v_reversed NUMBER := 0;
    v_temp     NUMBER;
BEGIN
    v_temp := v_num;
    WHILE v_temp > 0 LOOP
        v_reversed := v_reversed * 10 + MOD(v_temp, 10);
        v_temp := TRUNC(v_temp / 10);
    END LOOP;

    IF v_num = v_reversed THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is a Palindrome');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Not a Palindrome');
    END IF;
END;
/

DECLARE
    v_num   NUMBER := 98765;
    v_count NUMBER := 0;
    v_temp  NUMBER;
BEGIN
    v_temp := v_num;
    WHILE v_temp > 0 LOOP
        v_count := v_count + 1;
        v_temp := TRUNC(v_temp / 10);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Number of digits in ' || v_num || ' = ' || v_count);
END;
/

DECLARE
    v_a   NUMBER := 24;
    v_b   NUMBER := 36;
    v_ta  NUMBER;
    v_tb  NUMBER;
BEGIN
    v_ta := v_a;
    v_tb := v_b;
    WHILE v_ta != v_tb LOOP
        IF v_ta > v_tb THEN
            v_ta := v_ta - v_tb;
        ELSE
            v_tb := v_tb - v_ta;
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('GCD of ' || v_a || ' and ' || v_b || ' = ' || v_ta);
END;
/

DECLARE
    v_principal NUMBER := 10000;
    v_rate      NUMBER := 8;
    v_time      NUMBER := 3;
    v_n         NUMBER := 4;
    v_ci        NUMBER;
    v_amount    NUMBER;
BEGIN
    v_amount := v_principal * POWER((1 + v_rate / (v_n * 100)), v_n * v_time);
    v_ci := ROUND(v_amount - v_principal, 2);
    DBMS_OUTPUT.PUT_LINE('Compound Interest = ' || v_ci);
    DBMS_OUTPUT.PUT_LINE('Total Amount = ' || ROUND(v_amount, 2));
END;
/

DECLARE
    v_rows NUMBER := 5;
    v_line VARCHAR2(200);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Star Pattern (Right Triangle):');
    FOR i IN 1..v_rows LOOP
        v_line := '';
        FOR j IN 1..i LOOP
            v_line := v_line || '* ';
        END LOOP;
        DBMS_OUTPUT.PUT_LINE(v_line);
    END LOOP;
END;
/

DECLARE
    v_num       NUMBER := 153;
    v_temp      NUMBER;
    v_sum       NUMBER := 0;
    v_digit     NUMBER;
    v_digits    NUMBER := 0;
    v_temp2     NUMBER;
BEGIN
    v_temp := v_num;
    WHILE v_temp > 0 LOOP
        v_digits := v_digits + 1;
        v_temp := TRUNC(v_temp / 10);
    END LOOP;

    v_temp := v_num;
    WHILE v_temp > 0 LOOP
        v_digit := MOD(v_temp, 10);
        v_sum := v_sum + POWER(v_digit, v_digits);
        v_temp := TRUNC(v_temp / 10);
    END LOOP;

    IF v_sum = v_num THEN
        DBMS_OUTPUT.PUT_LINE(v_num || ' is an Armstrong Number');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_num || ' is Not an Armstrong Number');
    END IF;
END;
/

DECLARE
    v_num  NUMBER := 9876;
    v_sum  NUMBER := 0;
    v_temp NUMBER;
BEGIN
    v_temp := v_num;
    WHILE v_temp > 0 LOOP
        v_sum := v_sum + MOD(v_temp, 10);
        v_temp := TRUNC(v_temp / 10);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Sum of digits of ' || v_num || ' = ' || v_sum);
END;
/

DECLARE
    v_base   NUMBER := 2;
    v_exp    NUMBER := 10;
    v_result NUMBER := 1;
BEGIN
    FOR i IN 1..v_exp LOOP
        v_result := v_result * v_base;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(v_base || ' ^ ' || v_exp || ' = ' || v_result);
END;
/

DECLARE
    v_a   NUMBER := 12;
    v_b   NUMBER := 18;
    v_gcd NUMBER;
    v_lcm NUMBER;
    v_ta  NUMBER;
    v_tb  NUMBER;
BEGIN
    v_ta := v_a;
    v_tb := v_b;
    WHILE v_ta != v_tb LOOP
        IF v_ta > v_tb THEN
            v_ta := v_ta - v_tb;
        ELSE
            v_tb := v_tb - v_ta;
        END IF;
    END LOOP;
    v_gcd := v_ta;
    v_lcm := (v_a * v_b) / v_gcd;
    DBMS_OUTPUT.PUT_LINE('LCM of ' || v_a || ' and ' || v_b || ' = ' || v_lcm);
END;
/

DECLARE
    v_binary  VARCHAR2(20) := '11010';
    v_decimal NUMBER := 0;
    v_digit   NUMBER;
    v_len     NUMBER;
BEGIN
    v_len := LENGTH(v_binary);
    FOR i IN 1..v_len LOOP
        v_digit := TO_NUMBER(SUBSTR(v_binary, i, 1));
        v_decimal := v_decimal * 2 + v_digit;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Binary ' || v_binary || ' = Decimal ' || v_decimal);
END;
/

DECLARE
    v_a      NUMBER := 45;
    v_b      NUMBER := 78;
    v_c      NUMBER := 32;
    v_largest  NUMBER;
    v_second   NUMBER;
BEGIN
    IF v_a >= v_b AND v_a >= v_c THEN
        v_largest := v_a;
        IF v_b >= v_c THEN
            v_second := v_b;
        ELSE
            v_second := v_c;
        END IF;
    ELSIF v_b >= v_a AND v_b >= v_c THEN
        v_largest := v_b;
        IF v_a >= v_c THEN
            v_second := v_a;
        ELSE
            v_second := v_c;
        END IF;
    ELSE
        v_largest := v_c;
        IF v_a >= v_b THEN
            v_second := v_a;
        ELSE
            v_second := v_b;
        END IF;
    END IF;
    DBMS_OUTPUT.PUT_LINE('Second Largest = ' || v_second);
END;
/

DECLARE
    v_year    NUMBER := 2024;
    v_is_leap BOOLEAN := FALSE;
BEGIN
    IF MOD(v_year, 400) = 0 THEN
        v_is_leap := TRUE;
    ELSIF MOD(v_year, 100) = 0 THEN
        v_is_leap := FALSE;
    ELSIF MOD(v_year, 4) = 0 THEN
        v_is_leap := TRUE;
    END IF;

    IF v_is_leap THEN
        DBMS_OUTPUT.PUT_LINE(v_year || ' is a Leap Year');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_year || ' is Not a Leap Year');
    END IF;
END;
/

DECLARE
    v_random NUMBER;
BEGIN
    v_random := TRUNC(DBMS_RANDOM.VALUE(1, 100));
    DBMS_OUTPUT.PUT_LINE('Random Number (1-100): ' || v_random);
END;
/

DECLARE
    v_str     VARCHAR2(100) := 'ORACLE';
    v_rev     VARCHAR2(100) := '';
    v_len     NUMBER;
BEGIN
    v_len := LENGTH(v_str);
    FOR i IN REVERSE 1..v_len LOOP
        v_rev := v_rev || SUBSTR(v_str, i, 1);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Original: ' || v_str);
    DBMS_OUTPUT.PUT_LINE('Reversed: ' || v_rev);
END;
/

DECLARE
    v_str   VARCHAR2(100) := 'Hello Oracle Database';
    v_count NUMBER := 0;
    v_char  CHAR(1);
BEGIN
    FOR i IN 1..LENGTH(v_str) LOOP
        v_char := UPPER(SUBSTR(v_str, i, 1));
        IF v_char IN ('A', 'E', 'I', 'O', 'U') THEN
            v_count := v_count + 1;
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Vowels in "' || v_str || '" = ' || v_count);
END;
/

DECLARE
    v_choice NUMBER := 1;
    v_temp   NUMBER := 100;
    v_result NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Temperature Converter');
    DBMS_OUTPUT.PUT_LINE('1. Celsius to Fahrenheit');
    DBMS_OUTPUT.PUT_LINE('2. Fahrenheit to Celsius');
    DBMS_OUTPUT.PUT_LINE('3. Celsius to Kelvin');

    CASE v_choice
        WHEN 1 THEN
            v_result := (v_temp * 9 / 5) + 32;
            DBMS_OUTPUT.PUT_LINE(v_temp || ' C = ' || ROUND(v_result, 2) || ' F');
        WHEN 2 THEN
            v_result := (v_temp - 32) * 5 / 9;
            DBMS_OUTPUT.PUT_LINE(v_temp || ' F = ' || ROUND(v_result, 2) || ' C');
        WHEN 3 THEN
            v_result := v_temp + 273.15;
            DBMS_OUTPUT.PUT_LINE(v_temp || ' C = ' || ROUND(v_result, 2) || ' K');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Invalid Choice');
    END CASE;
END;
/

DECLARE
    v_dob DATE := TO_DATE('1998-05-15', 'YYYY-MM-DD');
    v_today DATE := SYSDATE;
    v_years NUMBER;
    v_months NUMBER;
    v_days NUMBER;
BEGIN
    v_years := TRUNC(MONTHS_BETWEEN(v_today, v_dob) / 12);
    v_months := MOD(TRUNC(MONTHS_BETWEEN(v_today, v_dob)), 12);
    v_days := v_today - ADD_MONTHS(v_dob, TRUNC(MONTHS_BETWEEN(v_today, v_dob)));
    DBMS_OUTPUT.PUT_LINE('Date of Birth: ' || TO_CHAR(v_dob, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('Age: ' || v_years || ' Years, ' || v_months || ' Months, ' || v_days || ' Days');
END;
/

DECLARE
    v_num1   NUMBER := 50;
    v_num2   NUMBER := 8;
    v_op     VARCHAR2(10) := 'MULTIPLY';
    v_result NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Simple Calculator');
    DBMS_OUTPUT.PUT_LINE('Operands: ' || v_num1 || ' and ' || v_num2);

    CASE v_op
        WHEN 'ADD' THEN
            v_result := v_num1 + v_num2;
            DBMS_OUTPUT.PUT_LINE(v_num1 || ' + ' || v_num2 || ' = ' || v_result);
        WHEN 'SUBTRACT' THEN
            v_result := v_num1 - v_num2;
            DBMS_OUTPUT.PUT_LINE(v_num1 || ' - ' || v_num2 || ' = ' || v_result);
        WHEN 'MULTIPLY' THEN
            v_result := v_num1 * v_num2;
            DBMS_OUTPUT.PUT_LINE(v_num1 || ' * ' || v_num2 || ' = ' || v_result);
        WHEN 'DIVIDE' THEN
            IF v_num2 = 0 THEN
                DBMS_OUTPUT.PUT_LINE('Error: Division by Zero');
            ELSE
                v_result := v_num1 / v_num2;
                DBMS_OUTPUT.PUT_LINE(v_num1 || ' / ' || v_num2 || ' = ' || ROUND(v_result, 2));
            END IF;
        WHEN 'MODULUS' THEN
            v_result := MOD(v_num1, v_num2);
            DBMS_OUTPUT.PUT_LINE(v_num1 || ' MOD ' || v_num2 || ' = ' || v_result);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Invalid Operation');
    END CASE;
END;
/
