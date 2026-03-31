import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Online Banking System
 * Real Scenario: Users perform transactions, check balance, and transfer money
 * concurrently.
 * 
 * Concepts Used:
 * - OOP: Account, SavingsAccount, CurrentAccount
 * - Inheritance: Account → SavingsAccount, CurrentAccount
 * - Abstraction: BankService interface
 * - Polymorphism: Different interest calculations
 * - Exception Handling: InsufficientBalanceException, InvalidAmountException
 */

// Custom Exception
class InsufficientBalanceException extends Exception {
  public InsufficientBalanceException(String message) {
    super(message);
  }
}

class InvalidAmountException extends Exception {
  public InvalidAmountException(String message) {
    super(message);
  }
}

// Transaction class
class Transaction {
  private String transactionId;
  private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
  private double amount;
  private LocalDateTime timestamp;
  private String description;

  public Transaction(String type, double amount, String description) {
    this.transactionId = "TXN" + System.currentTimeMillis();
    this.type = type;
    this.amount = amount;
    this.timestamp = LocalDateTime.now();
    this.description = description;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return String.format("%-15s | %-12s | $%-10.2f | %s | %s",
        transactionId, type, amount, timestamp.format(formatter), description);
  }
}

// Abstract Account class
abstract class Account {
  protected String accountNumber;
  protected String accountHolder;
  protected double balance;
  protected List<Transaction> transactionHistory;
  protected LocalDateTime createdDate;

  public Account(String accountNumber, String accountHolder, double initialBalance) {
    this.accountNumber = accountNumber;
    this.accountHolder = accountHolder;
    this.balance = initialBalance;
    this.transactionHistory = new ArrayList<>();
    this.createdDate = LocalDateTime.now();

    if (initialBalance > 0) {
      transactionHistory.add(new Transaction("DEPOSIT", initialBalance, "Initial deposit"));
    }
  }

  public void deposit(double amount) throws InvalidAmountException {
    if (amount <= 0) {
      throw new InvalidAmountException("Deposit amount must be positive");
    }
    balance += amount;
    transactionHistory.add(new Transaction("DEPOSIT", amount, "Cash deposit"));
    System.out.println("✓ Deposited $" + String.format("%.2f", amount) +
        " to account " + accountNumber);
  }

  public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
    if (amount <= 0) {
      throw new InvalidAmountException("Withdrawal amount must be positive");
    }
    if (amount > balance) {
      throw new InsufficientBalanceException(
          "Insufficient balance! Available: $" + String.format("%.2f", balance));
    }
    balance -= amount;
    transactionHistory.add(new Transaction("WITHDRAWAL", amount, "Cash withdrawal"));
    System.out.println("✓ Withdrawn $" + String.format("%.2f", amount) +
        " from account " + accountNumber);
  }

  public void transfer(Account recipient, double amount)
      throws InsufficientBalanceException, InvalidAmountException {
    if (amount <= 0) {
      throw new InvalidAmountException("Transfer amount must be positive");
    }
    if (amount > balance) {
      throw new InsufficientBalanceException(
          "Insufficient balance for transfer! Available: $" + String.format("%.2f", balance));
    }

    this.balance -= amount;
    recipient.balance += amount;

    this.transactionHistory.add(new Transaction("TRANSFER", amount,
        "Transfer to " + recipient.accountHolder));
    recipient.transactionHistory.add(new Transaction("TRANSFER", amount,
        "Transfer from " + this.accountHolder));

    System.out.println("✓ Transferred $" + String.format("%.2f", amount) +
        " from " + this.accountNumber + " to " + recipient.accountNumber);
  }

  public abstract void addInterest();

  public double getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public void displayBalance() {
    System.out.println("Account: " + accountNumber + " | Holder: " + accountHolder +
        " | Balance: $" + String.format("%.2f", balance));
  }

  public void displayTransactionHistory() {
    if (transactionHistory.isEmpty()) {
      System.out.println("No transactions yet");
      return;
    }

    System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
    System.out.println("║           TRANSACTION HISTORY - " + accountNumber + "              ║");
    System.out.println("╠══════════════════════════════════════════════════════════════════╣");

    for (Transaction txn : transactionHistory) {
      System.out.println(txn);
    }

    System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");
  }
}

// Savings Account - earns interest
class SavingsAccount extends Account {
  private double interestRate; // Annual percentage rate
  private int interestCompoundFrequency; // months

  public SavingsAccount(String accountNumber, String accountHolder,
      double initialBalance, double interestRate) {
    super(accountNumber, accountHolder, initialBalance);
    this.interestRate = interestRate;
    this.interestCompoundFrequency = 12; // Monthly
  }

  @Override
  public void addInterest() {
    double interest = balance * (interestRate / 100) / interestCompoundFrequency;
    balance += interest;
    transactionHistory.add(new Transaction("INTEREST", interest, "Interest earned"));
    System.out.println("✓ Interest added: $" + String.format("%.2f", interest) +
        " (Rate: " + interestRate + "%)");
  }

  @Override
  public String toString() {
    return "SavingsAccount [" + accountNumber + "] - " + accountHolder +
        " | Balance: $" + String.format("%.2f", balance) +
        " | Interest Rate: " + interestRate + "%";
  }
}

// Current Account - no interest, higher withdrawal limit
class CurrentAccount extends Account {
  private double overdraftLimit;
  private boolean overdraftEnabled;

  public CurrentAccount(String accountNumber, String accountHolder,
      double initialBalance, double overdraftLimit) {
    super(accountNumber, accountHolder, initialBalance);
    this.overdraftLimit = overdraftLimit;
    this.overdraftEnabled = true;
  }

  @Override
  public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
    if (amount <= 0) {
      throw new InvalidAmountException("Withdrawal amount must be positive");
    }
    if (overdraftEnabled && amount <= (balance + overdraftLimit)) {
      balance -= amount;
      transactionHistory.add(new Transaction("WITHDRAWAL", amount, "Cash withdrawal"));
      System.out.println("✓ Withdrawn $" + String.format("%.2f", amount) +
          " from account " + accountNumber);
    } else if (!overdraftEnabled && amount > balance) {
      throw new InsufficientBalanceException(
          "Insufficient balance! Available: $" + String.format("%.2f", balance));
    }
  }

  @Override
  public void addInterest() {
    // Current accounts don't earn interest
    System.out.println("ℹ Current Account - No interest earned");
  }

  public void setOverdraftLimit(double limit) {
    this.overdraftLimit = limit;
    System.out.println("✓ Overdraft limit updated to $" + String.format("%.2f", limit));
  }

  @Override
  public String toString() {
    return "CurrentAccount [" + accountNumber + "] - " + accountHolder +
        " | Balance: $" + String.format("%.2f", balance) +
        " | Overdraft Limit: $" + String.format("%.2f", overdraftLimit);
  }
}

// Bank Service Interface
interface BankService {
  void deposit(double amount) throws InvalidAmountException;

  void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException;

  void transfer(Account recipient, double amount) throws InsufficientBalanceException, InvalidAmountException;

  void addInterest();

  double getBalance();

  void displayTransactionHistory();
}

public class OnlineBankingSystem {
  private Map<String, Account> accounts;

  public OnlineBankingSystem() {
    this.accounts = new HashMap<>();
  }

  public void createAccount(Account account) {
    accounts.put(account.getAccountNumber(), account);
    System.out.println("✓ Account created: " + account.getAccountNumber());
  }

  public Account getAccount(String accountNumber) {
    return accounts.get(accountNumber);
  }

  public void displayAllAccounts() {
    if (accounts.isEmpty()) {
      System.out.println("No accounts in system");
      return;
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    ALL ACCOUNTS IN SYSTEM                        ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Account account : accounts.values()) {
      System.out.println(account);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public static void main(String[] args) {
    OnlineBankingSystem bank = new OnlineBankingSystem();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║           ONLINE BANKING SYSTEM - DEMONSTRATION                   ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Create accounts
    System.out.println("--- PHASE 1: Account Creation ---");
    SavingsAccount savings1 = new SavingsAccount("SAV001", "John Doe", 5000, 3.5);
    CurrentAccount current1 = new CurrentAccount("CUR001", "Jane Smith", 2000, 1000);
    SavingsAccount savings2 = new SavingsAccount("SAV002", "Bob Johnson", 3000, 3.5);

    bank.createAccount(savings1);
    bank.createAccount(current1);
    bank.createAccount(savings2);

    bank.displayAllAccounts();

    // PHASE 2: Deposit operations
    System.out.println("--- PHASE 2: Deposit Operations ---");
    try {
      savings1.deposit(1500);
      current1.deposit(500);
    } catch (InvalidAmountException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    bank.displayAllAccounts();

    // PHASE 3: Withdrawal operations
    System.out.println("--- PHASE 3: Withdrawal Operations ---");
    try {
      savings1.withdraw(1000);
      current1.withdraw(500);
    } catch (InsufficientBalanceException | InvalidAmountException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    bank.displayAllAccounts();

    // PHASE 4: Transfer operations
    System.out.println("--- PHASE 4: Transfer Operations ---");
    try {
      savings1.transfer(savings2, 1000);
      current1.transfer(savings1, 500);
    } catch (InsufficientBalanceException | InvalidAmountException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    bank.displayAllAccounts();

    // PHASE 5: Interest calculation
    System.out.println("--- PHASE 5: Interest Calculation ---");
    savings1.addInterest();
    current1.addInterest();
    savings2.addInterest();

    bank.displayAllAccounts();

    // PHASE 6: Transaction history
    System.out.println("--- PHASE 6: Transaction History ---");
    savings1.displayTransactionHistory();
    current1.displayTransactionHistory();

    // PHASE 7: Error handling
    System.out.println("--- PHASE 7: Error Handling ---");
    try {
      savings1.withdraw(20000); // Should fail
    } catch (InsufficientBalanceException | InvalidAmountException e) {
      System.out.println("✗ Error caught: " + e.getMessage());
    }

    try {
      savings1.deposit(-500); // Invalid amount
    } catch (InvalidAmountException e) {
      System.out.println("✗ Error caught: " + e.getMessage());
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                           ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
  }
}
