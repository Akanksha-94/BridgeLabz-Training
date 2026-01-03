public class BankAccount {

    public String accountNumber;     // public
    protected String accountHolder;  // protected
    private double balance;          // private

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getter
    public double getBalance() {
        return balance;
    }

    // Deposit
    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance)
            balance -= amount;
    }

    public void display() {
        System.out.println("Account: " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + balance);
    }
}

// Subclass
class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    public void showAccountInfo() {
        System.out.println("Accessing public Account Number: " + accountNumber);
        System.out.println("Accessing protected Account Holder: " + accountHolder);
    }
}
