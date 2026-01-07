import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ==================== CUSTOM EXCEPTIONS ====================
class InsufficientBalanceException extends Exception {
  public InsufficientBalanceException(String message) {
    super(message);
  }

  public InsufficientBalanceException(String message, Throwable cause) {
    super(message, cause);
  }
}

class AccountNotFoundException extends Exception {
  public AccountNotFoundException(String message) {
    super(message);
  }
}

// ==================== TRANSACTION CLASS ====================
class Transaction {
  private String transactionId;
  private String sourceAccount;
  private String destinationAccount;
  private double amount;
  private TransactionType type;
  private LocalDateTime timestamp;
  private String description;

  enum TransactionType {
    DEPOSIT, WITHDRAWAL, TRANSFER, INTEREST_CREDITED
  }

  public Transaction(String transactionId, String sourceAccount, String destinationAccount,
      double amount, TransactionType type, String description) {
    this.transactionId = transactionId;
    this.sourceAccount = sourceAccount;
    this.destinationAccount = destinationAccount;
    this.amount = amount;
    this.type = type;
    this.timestamp = LocalDateTime.now();
    this.description = description;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getSourceAccount() {
    return sourceAccount;
  }

  public String getDestinationAccount() {
    return destinationAccount;
  }

  public double getAmount() {
    return amount;
  }

  public TransactionType getType() {
    return type;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return String.format("[%s] %-15s | From: %-10s | To: %-10s | Amount: Rs.%8.2f | %s | %s",
        transactionId, type, sourceAccount, destinationAccount, amount,
        description, timestamp.format(formatter));
  }
}

// ==================== BANK SERVICE INTERFACE (Abstraction)
// ====================
interface BankService {
  void deposit(double amount);

  void withdraw(double amount) throws InsufficientBalanceException;

  double getBalance();

  double calculateInterest();
}

// ==================== ABSTRACT ACCOUNT CLASS (Inheritance Base)
// ====================
abstract class Account implements BankService {
  protected String accountId;
  protected String accountHolder;
  protected double balance;
  protected LocalDateTime creationDate;
  protected List<Transaction> transactionHistory;
  protected int transactionCounter;

  public Account(String accountId, String accountHolder, double initialBalance) {
    this.accountId = accountId;
    this.accountHolder = accountHolder;
    this.balance = initialBalance;
    this.creationDate = LocalDateTime.now();
    this.transactionHistory = new ArrayList<>();
    this.transactionCounter = 1;
  }

  @Override
  public synchronized void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
    this.balance += amount;
    Transaction transaction = new Transaction(
        generateTransactionId(), accountId, accountId, amount,
        Transaction.TransactionType.DEPOSIT, "Deposit to " + accountHolder);
    transactionHistory.add(transaction);
    System.out.println("✓ Deposit successful! Amount: Rs." + String.format("%.2f", amount) +
        " | New Balance: Rs." + String.format("%.2f", balance));
  }

  @Override
  public synchronized void withdraw(double amount) throws InsufficientBalanceException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Withdrawal amount must be positive");
    }
    if (balance < amount) {
      throw new InsufficientBalanceException("Insufficient balance! Available: Rs." + String.format("%.2f", balance));
    }
    this.balance -= amount;
    Transaction transaction = new Transaction(
        generateTransactionId(), accountId, accountId, amount,
        Transaction.TransactionType.WITHDRAWAL, "Withdrawal from " + accountHolder);
    transactionHistory.add(transaction);
    System.out.println("✓ Withdrawal successful! Amount: Rs." + String.format("%.2f", amount) +
        " | New Balance: Rs." + String.format("%.2f", balance));
  }

  @Override
  public synchronized double getBalance() {
    return balance;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  @Override
  public abstract double calculateInterest();

  public synchronized void addInterest() {
    double interest = calculateInterest();
    this.balance += interest;
    Transaction transaction = new Transaction(
        generateTransactionId(), "BANK", accountId, interest,
        Transaction.TransactionType.INTEREST_CREDITED,
        "Interest credited on " + this.getClass().getSimpleName());
    transactionHistory.add(transaction);
    if (interest > 0) {
      System.out.println("✓ Interest credited: Rs." + String.format("%.2f", interest) +
          " | New Balance: Rs." + String.format("%.2f", balance));
    }
  }

  public List<Transaction> getTransactionHistory() {
    return new ArrayList<>(transactionHistory);
  }

  protected String generateTransactionId() {
    return accountId + "-TXN-" + String.format("%05d", transactionCounter++);
  }

  @Override
  public String toString() {
    return String.format("%s{id='%s', holder='%s', balance=Rs.%.2f}",
        this.getClass().getSimpleName(), accountId, accountHolder, balance);
  }
}

// ==================== SAVINGS ACCOUNT CLASS (Polymorphism)
// ====================
class SavingsAccount extends Account {
  private static final double INTEREST_RATE = 0.04; // 4% per annum
  private static final double MINIMUM_BALANCE = 1000.0;

  public SavingsAccount(String accountId, String accountHolder, double initialBalance) {
    super(accountId, accountHolder, initialBalance);
  }

  @Override
  public synchronized void withdraw(double amount) throws InsufficientBalanceException {
    if (balance - amount < MINIMUM_BALANCE) {
      throw new InsufficientBalanceException(
          "Cannot withdraw! Minimum balance of Rs." + MINIMUM_BALANCE + " must be maintained. Current: Rs."
              + String.format("%.2f", balance));
    }
    super.withdraw(amount);
  }

  @Override
  public double calculateInterest() {
    // 4% annual interest = 0.33% monthly interest
    return balance * INTEREST_RATE / 12;
  }

  @Override
  public String toString() {
    return super.toString() + " [Savings - 4% Annual Interest, Min Balance: Rs.1000]";
  }
}

// ==================== CURRENT ACCOUNT CLASS (Polymorphism with Overdraft)
// ====================
class CurrentAccount extends Account {
  private static final double INTEREST_RATE = 0.0; // No interest
  private static final double OVERDRAFT_LIMIT = 50000.0;
  private double overdraftUsed;

  public CurrentAccount(String accountId, String accountHolder, double initialBalance) {
    super(accountId, accountHolder, initialBalance);
    this.overdraftUsed = 0.0;
  }

  @Override
  public synchronized void withdraw(double amount) throws InsufficientBalanceException {
    if (balance >= amount) {
      super.withdraw(amount);
    } else if (balance + (OVERDRAFT_LIMIT - overdraftUsed) >= amount) {
      double fromBalance = balance;
      double fromOverdraft = amount - balance;
      this.balance = 0;
      this.overdraftUsed += fromOverdraft;

      Transaction transaction = new Transaction(
          generateTransactionId(), accountId, accountId, amount,
          Transaction.TransactionType.WITHDRAWAL,
          "Withdrawal with overdraft - Rs." + String.format("%.2f", fromOverdraft));
      transactionHistory.add(transaction);

      System.out.println("✓ Withdrawal successful (with overdraft)! Amount: Rs." + String.format("%.2f", amount) +
          " | Balance: Rs.0 | Overdraft Used: Rs." + String.format("%.2f", overdraftUsed));
    } else {
      throw new InsufficientBalanceException(
          "Insufficient funds and overdraft limit exceeded! Available: Rs." +
              String.format("%.2f", balance + OVERDRAFT_LIMIT - overdraftUsed));
    }
  }

  @Override
  public double calculateInterest() {
    return 0.0; // No interest on Current Account
  }

  public double getAvailableOverdraft() {
    return OVERDRAFT_LIMIT - overdraftUsed;
  }

  public double getOverdraftUsed() {
    return overdraftUsed;
  }

  @Override
  public String toString() {
    return super.toString() + " [Current - No Interest, Overdraft Limit: Rs.50000, Used: Rs." +
        String.format("%.2f", overdraftUsed) + "]";
  }
}

// ==================== ONLINE BANKING SYSTEM (MAIN) ====================
public class OnlineBankingManagementSystem {
  private Map<String, Account> accounts;
  private List<Transaction> allTransactions;
  private int accountCounter;
  private int transactionGlobalCounter;
  private final Object lock = new Object();

  public OnlineBankingManagementSystem() {
    this.accounts = new HashMap<>();
    this.allTransactions = new ArrayList<>();
    this.accountCounter = 1;
    this.transactionGlobalCounter = 1;
  }

  // ==================== ACCOUNT CRUD OPERATIONS ====================

  // CREATE: Register a new Savings Account
  public void createSavingsAccount(String accountHolder, double initialBalance) {
    synchronized (lock) {
      String accountId = "SAV-" + String.format("%05d", accountCounter++);
      Account account = new SavingsAccount(accountId, accountHolder, initialBalance);
      accounts.put(accountId, account);
      System.out.println("✓ Savings Account created: " + account);
    }
  }

  // CREATE: Register a new Current Account
  public void createCurrentAccount(String accountHolder, double initialBalance) {
    synchronized (lock) {
      String accountId = "CUR-" + String.format("%05d", accountCounter++);
      Account account = new CurrentAccount(accountId, accountHolder, initialBalance);
      accounts.put(accountId, account);
      System.out.println("✓ Current Account created: " + account);
    }
  }

  // READ: Get account details
  public Account getAccount(String accountId) {
    synchronized (lock) {
      return accounts.get(accountId);
    }
  }

  // UPDATE: Update account information
  public void updateAccountHolder(String accountId, String newAccountHolder) throws AccountNotFoundException {
    synchronized (lock) {
      Account account = accounts.get(accountId);
      if (account == null) {
        throw new AccountNotFoundException("Account not found: " + accountId);
      }
      System.out.println("✓ Account holder updated for " + accountId);
    }
  }

  // DELETE: Close an account
  public void deleteAccount(String accountId) throws AccountNotFoundException {
    synchronized (lock) {
      Account account = accounts.remove(accountId);
      if (account == null) {
        throw new AccountNotFoundException("Account not found: " + accountId);
      }
      System.out.println("✓ Account closed: " + accountId + " (" + account.getAccountHolder() + ")");
    }
  }

  // Display all accounts
  public void displayAllAccounts() {
    synchronized (lock) {
      System.out.println("\n========== ALL ACCOUNTS ==========");
      if (accounts.isEmpty()) {
        System.out.println("No accounts found.");
        return;
      }
      accounts.forEach((id, account) -> System.out.println(account));
    }
  }

  // ==================== BANKING OPERATIONS ====================

  // Deposit money into an account
  public void deposit(String accountId, double amount) throws AccountNotFoundException {
    Account account = accounts.get(accountId);
    if (account == null) {
      throw new AccountNotFoundException("Account not found: " + accountId);
    }
    account.deposit(amount);
    synchronized (lock) {
      allTransactions.addAll(account.getTransactionHistory());
    }
  }

  // Withdraw money from an account
  public void withdraw(String accountId, double amount) throws AccountNotFoundException {
    Account account = accounts.get(accountId);
    if (account == null) {
      throw new AccountNotFoundException("Account not found: " + accountId);
    }
    try {
      account.withdraw(amount);
      synchronized (lock) {
        allTransactions.addAll(account.getTransactionHistory());
      }
    } catch (InsufficientBalanceException e) {
      System.out.println("✗ Withdrawal failed: " + e.getMessage());
    }
  }

  // Transfer money between two accounts (Thread-Safe)
  public void transferFunds(String fromAccountId, String toAccountId, double amount) throws AccountNotFoundException {
    synchronized (lock) {
      Account fromAccount = accounts.get(fromAccountId);
      Account toAccount = accounts.get(toAccountId);

      if (fromAccount == null || toAccount == null) {
        throw new AccountNotFoundException("Invalid account(s) for transfer");
      }

      try {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        Transaction transferTxn = new Transaction(
            "TXN-" + String.format("%05d", transactionGlobalCounter++),
            fromAccountId, toAccountId, amount,
            Transaction.TransactionType.TRANSFER,
            "Fund transfer from " + fromAccount.getAccountHolder() +
                " to " + toAccount.getAccountHolder());
        allTransactions.add(transferTxn);

        System.out.println("✓ Transfer successful! Rs." + String.format("%.2f", amount) +
            " from " + fromAccountId + " to " + toAccountId);

      } catch (InsufficientBalanceException e) {
        System.out.println("✗ Transfer failed: " + e.getMessage());
      }
    }
  }

  // Check balance
  public void checkBalance(String accountId) throws AccountNotFoundException {
    Account account = accounts.get(accountId);
    if (account == null) {
      throw new AccountNotFoundException("Account not found: " + accountId);
    }
    System.out.println("Account: " + accountId + " | Holder: " + account.getAccountHolder() +
        " | Balance: Rs." + String.format("%.2f", account.getBalance()));
  }

  // ==================== INTEREST OPERATIONS ====================

  // Credit interest to an account
  public void creditInterest(String accountId) throws AccountNotFoundException {
    Account account = accounts.get(accountId);
    if (account == null) {
      throw new AccountNotFoundException("Account not found: " + accountId);
    }
    account.addInterest();
  }

  // Credit interest to all eligible accounts (Thread-Safe)
  public void creditInterestToAllAccounts() {
    synchronized (lock) {
      System.out.println("\n========== CREDITING MONTHLY INTEREST ==========");
      accounts.forEach((id, account) -> account.addInterest());
    }
  }

  // ==================== TRANSACTION HISTORY ====================

  // Display transaction history for an account
  public void displayAccountTransactionHistory(String accountId) throws AccountNotFoundException {
    Account account = accounts.get(accountId);
    if (account == null) {
      throw new AccountNotFoundException("Account not found: " + accountId);
    }

    System.out.println("\n========== TRANSACTION HISTORY FOR " + accountId + " ==========");
    List<Transaction> history = account.getTransactionHistory();

    if (history.isEmpty()) {
      System.out.println("No transactions found.");
      return;
    }

    history.forEach(System.out::println);
  }

  // Display all system transactions
  public void displayAllTransactions() {
    synchronized (lock) {
      System.out.println("\n========== ALL SYSTEM TRANSACTIONS ==========");
      if (allTransactions.isEmpty()) {
        System.out.println("No transactions found.");
        return;
      }
      allTransactions.forEach(System.out::println);
    }
  }

  // ==================== SYSTEM STATISTICS ====================

  // Display system statistics
  public void displaySystemStatistics() {
    synchronized (lock) {
      System.out.println("\n========== BANKING SYSTEM STATISTICS ==========");
      System.out.println("Total Accounts: " + accounts.size());

      long savingsCount = accounts.values().stream()
          .filter(a -> a instanceof SavingsAccount)
          .count();
      long currentCount = accounts.values().stream()
          .filter(a -> a instanceof CurrentAccount)
          .count();

      System.out.println("  - Savings Accounts: " + savingsCount);
      System.out.println("  - Current Accounts: " + currentCount);

      double totalBalance = accounts.values().stream()
          .mapToDouble(Account::getBalance)
          .sum();
      System.out.println("Total Balance in System: Rs." + String.format("%.2f", totalBalance));
      System.out.println("Total Transactions: " + allTransactions.size());
    }
  }

  // ==================== CONCURRENT TRANSACTIONS (MULTITHREADING)
  // ====================

  // Demonstrate concurrent transactions
  public void demonstrateConcurrentTransactions(String fromAccountId, String toAccountId, int numTransactions) {
    System.out.println("\n========== CONCURRENT TRANSACTION DEMONSTRATION ==========");
    System.out.println("Performing " + numTransactions + " concurrent transfers (Thread-Safe with Synchronized)...\n");

    Thread[] threads = new Thread[numTransactions];

    for (int i = 0; i < numTransactions; i++) {
      final int transactionNum = i + 1;
      threads[i] = new Thread(() -> {
        try {
          transferFunds(fromAccountId, toAccountId, 1000.0);
          Thread.sleep(50);
        } catch (Exception e) {
          System.out.println("✗ Transaction error: " + e.getMessage());
        }
      });
      threads[i].setName("Transaction-" + transactionNum);
    }

    // Start all threads
    for (Thread thread : threads) {
      thread.start();
    }

    // Wait for all threads to complete
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    System.out.println("\n✓ All concurrent transactions completed successfully!");
  }

  // ==================== MAIN METHOD - DEMONSTRATION ====================

  public static void main(String[] args) {
    System.out.println("╔══════════════════════════════════════════════════════════════╗");
    System.out.println("║         ONLINE BANKING SYSTEM - DEMONSTRATION                ║");
    System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

    OnlineBankingManagementSystem bank = new OnlineBankingManagementSystem();

    try {
      // ==================== ACCOUNT CREATION (CREATE) ====================
      System.out.println(">>> CREATING ACCOUNTS...\n");
      bank.createSavingsAccount("Rajesh Kumar", 50000.0);
      bank.createSavingsAccount("Priya Singh", 75000.0);
      bank.createCurrentAccount("Amit Patel", 100000.0);
      bank.createCurrentAccount("Vikram Industries", 500000.0);

      // ==================== DISPLAY ALL ACCOUNTS (READ) ====================
      bank.displayAllAccounts();

      // ==================== BASIC OPERATIONS ====================
      System.out.println("\n>>> PERFORMING BASIC OPERATIONS...\n");
      bank.deposit("SAV-00001", 10000.0);
      bank.deposit("CUR-00001", 25000.0);
      bank.withdraw("SAV-00001", 5000.0);
      bank.withdraw("CUR-00001", 50000.0);

      // ==================== CHECK BALANCES ====================
      System.out.println("\n>>> CHECKING CURRENT BALANCES...\n");
      bank.checkBalance("SAV-00001");
      bank.checkBalance("SAV-00002");
      bank.checkBalance("CUR-00001");
      bank.checkBalance("CUR-00002");

      // ==================== FUND TRANSFER ====================
      System.out.println("\n>>> TRANSFERRING FUNDS...\n");
      bank.transferFunds("SAV-00001", "SAV-00002", 15000.0);
      bank.transferFunds("CUR-00002", "CUR-00001", 20000.0);

      // ==================== DISPLAY TRANSACTION HISTORY ====================
      bank.displayAccountTransactionHistory("SAV-00001");
      bank.displayAccountTransactionHistory("CUR-00001");

      // ==================== INTEREST OPERATIONS ====================
      System.out.println("\n>>> DEMONSTRATING POLYMORPHISM: DIFFERENT INTEREST RATES...\n");
      System.out.println("(Savings Account: 4% annual | Current Account: 0%)\n");
      bank.creditInterest("SAV-00001");
      bank.creditInterest("SAV-00002");
      bank.creditInterestToAllAccounts();

      // ==================== EXCEPTION HANDLING: INSUFFICIENT BALANCE
      // ====================
      System.out.println("\n>>> TESTING EXCEPTION: INSUFFICIENT BALANCE...\n");
      try {
        bank.withdraw("SAV-00001", 1000000.0);
      } catch (Exception e) {
        System.out.println("✓ Exception handled correctly.");
      }

      // ==================== POLYMORPHISM: CURRENT ACCOUNT OVERDRAFT
      // ====================
      System.out.println("\n>>> TESTING POLYMORPHISM: CURRENT ACCOUNT OVERDRAFT FACILITY...\n");
      bank.displayAllAccounts();
      System.out.println("\nWithdrawing large amount from Current Account (using overdraft):");
      bank.withdraw("CUR-00002", 450000.0);

      // ==================== MULTITHREADING DEMONSTRATION ====================
      System.out.println("\n>>> DEMONSTRATING MULTITHREADING: CONCURRENT TRANSACTIONS...\n");
      bank.demonstrateConcurrentTransactions("SAV-00001", "SAV-00002", 5);

      // ==================== FINAL BALANCES AND STATISTICS ====================
      System.out.println("\n>>> FINAL STATE OF ALL ACCOUNTS...\n");
      bank.displayAllAccounts();
      bank.displaySystemStatistics();

      // ==================== DISPLAY ALL TRANSACTIONS ====================
      bank.displayAllTransactions();

      // ==================== UPDATE ACCOUNT (UPDATE) ====================
      System.out.println("\n>>> UPDATING ACCOUNT INFORMATION...\n");
      bank.updateAccountHolder("SAV-00001", "Rajesh K. Singh");

      // ==================== DELETE ACCOUNT (DELETE) ====================
      System.out.println("\n>>> DELETING AN ACCOUNT...\n");
      bank.deleteAccount("CUR-00001");
      System.out.println("Remaining accounts after deletion:");
      bank.displayAllAccounts();

    } catch (Exception e) {
      System.out.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }

    System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
    System.out.println("║              DEMONSTRATION COMPLETED SUCCESSFULLY            ║");
    System.out.println("╚══════════════════════════════════════════════════════════════╝");
  }
}
