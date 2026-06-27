package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BankingServiceLogger {

    private static final Logger logger = LoggerFactory.getLogger(BankingServiceLogger.class);
    private final Map<String, Double> accounts = new HashMap<>();

    public void createAccount(String accountId, double initialBalance) {
        logger.info("Creating account: {} with initial balance: {}", accountId, initialBalance);
        if (accounts.containsKey(accountId)) {
            logger.warn("Account already exists: {}", accountId);
            return;
        }
        accounts.put(accountId, initialBalance);
        logger.info("Account created successfully: {}", accountId);
    }

    public boolean deposit(String accountId, double amount) {
        logger.info("Deposit request - Account: {}, Amount: {}", accountId, amount);
        if (!accounts.containsKey(accountId)) {
            logger.error("Deposit failed - Account not found: {}", accountId);
            return false;
        }
        if (amount <= 0) {
            logger.warn("Invalid deposit amount: {}", amount);
            return false;
        }
        double newBalance = accounts.get(accountId) + amount;
        accounts.put(accountId, newBalance);
        logger.info("Deposit successful - Account: {}, New Balance: {}", accountId, newBalance);
        return true;
    }

    public boolean withdraw(String accountId, double amount) {
        logger.info("Withdrawal request - Account: {}, Amount: {}", accountId, amount);
        if (!accounts.containsKey(accountId)) {
            logger.error("Withdrawal failed - Account not found: {}", accountId);
            return false;
        }
        double currentBalance = accounts.get(accountId);
        if (amount > currentBalance) {
            logger.warn("Insufficient funds - Account: {}, Balance: {}, Requested: {}", accountId, currentBalance, amount);
            return false;
        }
        accounts.put(accountId, currentBalance - amount);
        logger.info("Withdrawal successful - Account: {}, Amount: {}", accountId, amount);
        return true;
    }

    public double getBalance(String accountId) {
        logger.debug("Balance inquiry for account: {}", accountId);
        return accounts.getOrDefault(accountId, -1.0);
    }
}
