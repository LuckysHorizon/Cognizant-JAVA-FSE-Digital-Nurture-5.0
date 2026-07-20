import java.util.HashMap;
import java.util.Map;

public class FinancialForecaster {

    private final Map<Integer, Double> memoizationCache = new HashMap<>();

    public double calculateFutureValueRecursive(double presentValue, double growthRate, int periods) {
        if (periods <= 0) {
            return presentValue;
        }
        return (1 + growthRate) * calculateFutureValueRecursive(presentValue, growthRate, periods - 1);
    }

    public double calculateFutureValueOptimized(double presentValue, double growthRate, int periods) {
        if (periods <= 0) {
            return presentValue;
        }
        
        if (memoizationCache.containsKey(periods)) {
            return memoizationCache.get(periods);
        }

        double value = (1 + growthRate) * calculateFutureValueOptimized(presentValue, growthRate, periods - 1);
        memoizationCache.put(periods, value);
        
        return value;
    }
    
    public double calculateFutureValueBottomUp(double presentValue, double growthRate, int periods) {
        if (periods <= 0) {
            return presentValue;
        }
        
        double currentValue = presentValue;
        for (int i = 1; i <= periods; i++) {
            currentValue = currentValue * (1 + growthRate);
        }
        
        return currentValue;
    }
}
