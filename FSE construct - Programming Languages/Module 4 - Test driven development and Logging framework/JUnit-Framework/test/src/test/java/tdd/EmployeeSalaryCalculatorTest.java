package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD Employee Salary Calculator Tests")
class EmployeeSalaryCalculatorTest {

    private EmployeeSalaryCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new EmployeeSalaryCalculator();
    }

    @Test
    void testBaseSalary() {
        assertEquals(16000.0, calculator.calculateBaseSalary(100, 160));
    }

    @Test
    void testBaseSalaryCappedAtStandardHours() {
        assertEquals(16000.0, calculator.calculateBaseSalary(100, 200));
    }

    @Test
    void testOvertimePayNoOvertime() {
        assertEquals(0, calculator.calculateOvertimePay(100, 160));
    }

    @Test
    void testOvertimePayWithOvertime() {
        assertEquals(6000.0, calculator.calculateOvertimePay(100, 200));
    }

    @Test
    void testBonus() {
        assertEquals(1600.0, calculator.calculateBonus(16000, 10));
    }

    @Test
    void testBonusInvalidPercentage() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateBonus(16000, 150));
    }

    @Test
    void testTax() {
        assertEquals(3200.0, calculator.calculateTax(16000));
    }

    @Test
    void testNetSalary() {
        double netSalary = calculator.calculateNetSalary(100, 160, 10);
        double expected = (16000 + 0 + 1600) * 0.8;
        assertEquals(expected, netSalary, 0.01);
    }

    @Test
    void testNegativeRateThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateBaseSalary(-10, 160));
    }
}
