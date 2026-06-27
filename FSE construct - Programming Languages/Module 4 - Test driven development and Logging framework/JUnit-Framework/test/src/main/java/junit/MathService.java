package junit;

public class MathService {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) a / b;
    }

    public int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative numbers not allowed");
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public int max(int a, int b) {
        return Math.max(a, b);
    }

    public int abs(int number) {
        return Math.abs(number);
    }
}
