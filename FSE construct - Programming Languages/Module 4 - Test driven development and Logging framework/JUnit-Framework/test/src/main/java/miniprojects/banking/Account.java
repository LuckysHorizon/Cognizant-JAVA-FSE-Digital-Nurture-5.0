package miniprojects.banking;

public class Account {
    private String accountId;
    private String holderName;
    private double balance;

    public Account(String accountId, String holderName, double balance) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountId() { return accountId; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
