// Bank Account Types
class BankAccount {
    int accountNumber;
    double balance;

    void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

// Savings Account
class SavingsAccount extends BankAccount {
    double interestRate;

    void displayAccountType() {
        System.out.println("Account Type: Savings Account");
        displayDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("----------------------------");
    }
}

// Checking Account
class CheckingAccount extends BankAccount {
    int withdrawalLimit;

    void displayAccountType() {
        System.out.println("Account Type: Checking Account");
        displayDetails();
        System.out.println("Withdrawal Limit: ₹" + withdrawalLimit);
        System.out.println("----------------------------");
    }
}

// Fixed Deposit Account
class FixedDepositAccount extends BankAccount {
    int lockPeriod; // in years

    void displayAccountType() {
        System.out.println("Account Type: Fixed Deposit Account");
        displayDetails();
        System.out.println("Lock Period: " + lockPeriod + " years");
        System.out.println("----------------------------");
    }
}

public class BankSystem {
    public static void main(String[] args) {

        SavingsAccount sa = new SavingsAccount();
        sa.accountNumber = 101;
        sa.balance = 50000;
        sa.interestRate = 4.5;

        CheckingAccount ca = new CheckingAccount();
        ca.accountNumber = 102;
        ca.balance = 30000;
        ca.withdrawalLimit = 10000;

        FixedDepositAccount fd = new FixedDepositAccount();
        fd.accountNumber = 103;
        fd.balance = 200000;
        fd.lockPeriod = 5;

        sa.displayAccountType();
        ca.displayAccountType();
        fd.displayAccountType();
    }
}
