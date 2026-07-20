public class Main {
    public static void main(String[] args) {
        FinancialForecaster forecaster = new FinancialForecaster();
        
        double initialInvestment = 10000.0;
        double annualGrowthRate = 0.05; 
        int yearsToForecast = 10;
        
        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Expected Annual Growth: " + (annualGrowthRate * 100) + "%");
        System.out.println("Forecast Period: " + yearsToForecast + " years\n");

        long startRecursive = System.nanoTime();
        double valRecursive = forecaster.calculateFutureValueRecursive(initialInvestment, annualGrowthRate, yearsToForecast);
        long endRecursive = System.nanoTime();
        
        System.out.printf("Recursive Approach: $%.2f (Time: %d ns)%n", valRecursive, (endRecursive - startRecursive));

        long startOptimized = System.nanoTime();
        double valOptimized = forecaster.calculateFutureValueOptimized(initialInvestment, annualGrowthRate, yearsToForecast);
        long endOptimized = System.nanoTime();
        
        System.out.printf("DP Top-Down (Memoized): $%.2f (Time: %d ns)%n", valOptimized, (endOptimized - startOptimized));
        
        long startBottomUp = System.nanoTime();
        double valBottomUp = forecaster.calculateFutureValueBottomUp(initialInvestment, annualGrowthRate, yearsToForecast);
        long endBottomUp = System.nanoTime();
        
        System.out.printf("DP Bottom-Up: $%.2f (Time: %d ns)%n", valBottomUp, (endBottomUp - startBottomUp));
    }
}
