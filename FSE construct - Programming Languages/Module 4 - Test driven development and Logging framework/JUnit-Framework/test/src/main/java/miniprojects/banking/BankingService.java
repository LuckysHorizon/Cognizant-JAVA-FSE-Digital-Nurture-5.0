package miniprojects.banking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BankingService {

    private static final Logger logger = LoggerFactory.getLogger(BankingService.class);
    private final Map<String, Account> accounts = new HashMap<>();

    public void createAccount(Account account) {
        logger.info("Creating account for: {}", account.getHolderName());
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountId);
        }
        return account;
    }

    public boolean deposit(String accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Account account = getAccount(accountId);
        account.setBalance(account.getBalance() + amount);
        logger.info("Deposited {} to account {}", amount, accountId);
        return true;
    }

    public boolean withdraw(String accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        Account account = getAccount(accountId);
        if (account.getBalance() < amount) {
            logger.warn("Insufficient funds in account: {}", accountId);
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        logger.info("Withdrew {} from account {}", amount, accountId);
        return true;
    }

    public boolean transfer(String fromId, String toId, double amount) {
        if (withdraw(fromId, amount)) {
            deposit(toId, amount);
            logger.info("Transferred {} from {} to {}", amount, fromId, toId);
            return true;
        }
        return false;
    }
}
