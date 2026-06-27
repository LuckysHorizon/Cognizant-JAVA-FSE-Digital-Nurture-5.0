package tdd;

public class EmployeeSalaryCalculator {

    private static final double TAX_RATE = 0.2;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final int STANDARD_HOURS = 160;

    public double calculateBaseSalary(double hourlyRate, int hoursWorked) {
        if (hourlyRate < 0 || hoursWorked < 0) {
            throw new IllegalArgumentException("Rate and hours must be non-negative");
        }
        return hourlyRate * Math.min(hoursWorked, STANDARD_HOURS);
    }

    public double calculateOvertimePay(double hourlyRate, int hoursWorked) {
        if (hoursWorked <= STANDARD_HOURS) {
            return 0;
        }
        int overtimeHours = hoursWorked - STANDARD_HOURS;
        return overtimeHours * hourlyRate * OVERTIME_MULTIPLIER;
    }

    public double calculateBonus(double baseSalary, double bonusPercentage) {
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("Bonus percentage must be between 0 and 100");
        }
        return baseSalary * (bonusPercentage / 100);
    }

    public double calculateTax(double grossSalary) {
        if (grossSalary < 0) {
            throw new IllegalArgumentException("Gross salary cannot be negative");
        }
        return grossSalary * TAX_RATE;
    }

    public double calculateNetSalary(double hourlyRate, int hoursWorked, double bonusPercentage) {
        double baseSalary = calculateBaseSalary(hourlyRate, hoursWorked);
        double overtime = calculateOvertimePay(hourlyRate, hoursWorked);
        double bonus = calculateBonus(baseSalary, bonusPercentage);
        double grossSalary = baseSalary + overtime + bonus;
        double tax = calculateTax(grossSalary);
        return grossSalary - tax;
    }
}
