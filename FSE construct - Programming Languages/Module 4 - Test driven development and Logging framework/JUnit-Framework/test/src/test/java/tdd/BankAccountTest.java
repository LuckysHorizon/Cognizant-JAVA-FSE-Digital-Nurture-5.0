package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD Bank Account Tests")
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("John Doe", 1000.0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void testAccountHolder() {
        assertEquals("John Doe", account.getAccountHolder());
    }

    @Test
    void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void testDepositNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    void testWithdraw() {
        account.withdraw(300.0);
        assertEquals(700.0, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        assertThrows(IllegalStateException.class, () -> account.withdraw(2000.0));
    }

    @Test
    void testTransfer() {
        BankAccount target = new BankAccount("Jane Doe", 500.0);
        account.transfer(target, 200.0);
        assertEquals(800.0, account.getBalance());
        assertEquals(700.0, target.getBalance());
    }

    @Test
    void testTransferToNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.transfer(null, 100.0));
    }

    @Test
    void testNegativeInitialBalanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Test", -100));
    }
}
