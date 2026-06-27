package logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Banking Service Logger Tests")
class BankingServiceLoggerTest {

    private BankingServiceLogger bankingService;

    @BeforeEach
    void setUp() {
        bankingService = new BankingServiceLogger();
        bankingService.createAccount("ACC-001", 1000.0);
    }

    @Test
    void testCreateAccount() {
        bankingService.createAccount("ACC-002", 500.0);
        assertEquals(500.0, bankingService.getBalance("ACC-002"));
    }

    @Test
    void testDeposit() {
        assertTrue(bankingService.deposit("ACC-001", 500.0));
        assertEquals(1500.0, bankingService.getBalance("ACC-001"));
    }

    @Test
    void testDepositInvalidAmount() {
        assertFalse(bankingService.deposit("ACC-001", -100));
    }

    @Test
    void testWithdraw() {
        assertTrue(bankingService.withdraw("ACC-001", 300.0));
        assertEquals(700.0, bankingService.getBalance("ACC-001"));
    }

    @Test
    void testWithdrawInsufficientFunds() {
        assertFalse(bankingService.withdraw("ACC-001", 5000.0));
    }

    @Test
    void testNonExistentAccount() {
        assertEquals(-1.0, bankingService.getBalance("FAKE"));
    }
}
