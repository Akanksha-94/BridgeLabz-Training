class SimulateAnATM {
    String accountHolder;
    long accountNumber;
    double balance;

    // Method to deposit money
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // Method to withdraw money
    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount");
        }
    }

    // Method to display current balance
    void displayBalance() {
        System.out.println("Current Balance: " + balance);
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {

        SimulateAnATM account = new SimulateAnATM();

        // Account details
        account.accountHolder = "Rohan";
        account.accountNumber = 1234567890L;
        account.balance = 10000.0;

        System.out.println("Account Holder: " + account.accountHolder);
        System.out.println("Account Number: " + account.accountNumber);
        account.displayBalance();

        // ATM operations
        account.deposit(5000);
        account.displayBalance();

        account.withdraw(3000);
        account.displayBalance();

        account.withdraw(15000); // insufficient balance
    }
}
