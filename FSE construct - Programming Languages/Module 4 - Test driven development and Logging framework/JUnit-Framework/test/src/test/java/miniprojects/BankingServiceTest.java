package miniprojects;

import miniprojects.banking.Account;
import miniprojects.banking.BankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Banking Service Tests")
class BankingServiceTest {

    private BankingService service;

    @BeforeEach
    void setUp() {
        service = new BankingService();
        service.createAccount(new Account("ACC-001", "Alice", 1000.0));
        service.createAccount(new Account("ACC-002", "Bob", 500.0));
    }

    @Test
    void testDeposit() {
        assertTrue(service.deposit("ACC-001", 500));
        assertEquals(1500.0, service.getAccount("ACC-001").getBalance());
    }

    @Test
    void testWithdraw() {
        assertTrue(service.withdraw("ACC-001", 300));
        assertEquals(700.0, service.getAccount("ACC-001").getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        assertFalse(service.withdraw("ACC-001", 5000));
    }

    @Test
    void testTransfer() {
        assertTrue(service.transfer("ACC-001", "ACC-002", 200));
        assertEquals(800.0, service.getAccount("ACC-001").getBalance());
        assertEquals(700.0, service.getAccount("ACC-002").getBalance());
    }

    @Test
    void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> service.deposit("ACC-001", -100));
    }

    @Test
    void testAccountNotFound() {
        assertThrows(RuntimeException.class, () -> service.getAccount("FAKE"));
    }
}
