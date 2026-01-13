import java.util.*;

/**
 * Digital Wallet System
 * Real Scenario: Users add money, transfer funds, and view transaction history.
 * 
 * Concepts Used:
 * - OOP: Wallet, User, Transaction
 * - Interface: TransferService
 * - Polymorphism: Bank vs Wallet transfer
 * - Exception Handling: InsufficientBalanceException
 */

// Custom Exceptions
class InsufficientBalanceException extends Exception {
  public InsufficientBalanceException(String message) {
    super(message);
  }
}

class InvalidTransactionException extends Exception {
  public InvalidTransactionException(String message) {
    super(message);
  }
}

// Transaction class
class Transaction {
  private String transactionId;
  private String type; // ADD, WITHDRAW, TRANSFER_SENT, TRANSFER_RECEIVED
  private double amount;
  private long timestamp;
  private String description;
  private String relatedWallet; // wallet ID involved in transfer

  public Transaction(String type, double amount, String description) {
    this.transactionId = "TXN" + System.currentTimeMillis();
    this.type = type;
    this.amount = amount;
    this.timestamp = System.currentTimeMillis();
    this.description = description;
    this.relatedWallet = null;
  }

  public Transaction(String type, double amount, String description, String relatedWallet) {
    this(type, amount, description);
    this.relatedWallet = relatedWallet;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getType() {
    return type;
  }

  public double getAmount() {
    return amount;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return String.format("%-15s | %-18s | $%-10.2f | %s",
        transactionId, type, amount, description);
  }
}

// Transfer Service Interface
interface TransferService {
  void transferFunds(Wallet fromWallet, Wallet toWallet, double amount)
      throws InsufficientBalanceException, InvalidTransactionException;

  String getTransferType();
}

// Wallet-to-Wallet Transfer
class WalletTransfer implements TransferService {
  @Override
  public void transferFunds(Wallet fromWallet, Wallet toWallet, double amount)
      throws InsufficientBalanceException, InvalidTransactionException {
    if (amount <= 0) {
      throw new InvalidTransactionException("Amount must be positive");
    }
    if (amount > fromWallet.getBalance()) {
      throw new InsufficientBalanceException(
          "Insufficient balance! Available: $" + String.format("%.2f", fromWallet.getBalance()));
    }

    fromWallet.balance -= amount;
    toWallet.balance += amount;

    fromWallet.addTransaction(new Transaction("TRANSFER_SENT", amount,
        "Transfer to " + toWallet.userId, toWallet.userId));
    toWallet.addTransaction(new Transaction("TRANSFER_RECEIVED", amount,
        "Transfer from " + fromWallet.userId, fromWallet.userId));

    System.out.println("✓ Wallet transfer: $" + String.format("%.2f", amount) +
        " from " + fromWallet.userId + " to " + toWallet.userId);
  }

  @Override
  public String getTransferType() {
    return "Wallet-to-Wallet Transfer";
  }
}

// Bank Transfer
class BankTransfer implements TransferService {
  private static final double BANK_FEE = 2.50;

  @Override
  public void transferFunds(Wallet fromWallet, Wallet toWallet, double amount)
      throws InsufficientBalanceException, InvalidTransactionException {
    if (amount <= 0) {
      throw new InvalidTransactionException("Amount must be positive");
    }

    double totalAmount = amount + BANK_FEE;
    if (totalAmount > fromWallet.getBalance()) {
      throw new InsufficientBalanceException(
          "Insufficient balance for bank transfer (including $" + BANK_FEE + " fee)! Available: $" +
              String.format("%.2f", fromWallet.getBalance()));
    }

    fromWallet.balance -= totalAmount;
    toWallet.balance += amount;

    fromWallet.addTransaction(new Transaction("TRANSFER_SENT", amount,
        "Bank transfer to " + toWallet.userId + " (Fee: $" + BANK_FEE + ")", toWallet.userId));
    toWallet.addTransaction(new Transaction("TRANSFER_RECEIVED", amount,
        "Bank transfer from " + fromWallet.userId, fromWallet.userId));

    System.out.println("✓ Bank transfer: $" + String.format("%.2f", amount) +
        " from " + fromWallet.userId + " to " + toWallet.userId +
        " (Fee: $" + BANK_FEE + ")");
  }

  @Override
  public String getTransferType() {
    return "Bank Transfer (Fee: $" + BANK_FEE + ")";
  }
}

// Wallet class
class Wallet {
  protected String walletId;
  protected String userId;
  protected double balance;
  protected List<Transaction> transactionHistory;
  protected long createdDate;

  public Wallet(String walletId, String userId, double initialBalance) {
    this.walletId = walletId;
    this.userId = userId;
    this.balance = initialBalance;
    this.transactionHistory = new ArrayList<>();
    this.createdDate = System.currentTimeMillis();

    if (initialBalance > 0) {
      transactionHistory.add(new Transaction("ADD", initialBalance, "Initial deposit"));
    }
  }

  public String getWalletId() {
    return walletId;
  }

  public String getUserId() {
    return userId;
  }

  public double getBalance() {
    return balance;
  }

  public void addMoney(double amount) throws InvalidTransactionException {
    if (amount <= 0) {
      throw new InvalidTransactionException("Amount must be positive");
    }
    balance += amount;
    transactionHistory.add(new Transaction("ADD", amount, "Money added"));
    System.out.println("✓ Added $" + String.format("%.2f", amount) +
        " to wallet " + walletId);
  }

  public void withdrawMoney(double amount) throws InsufficientBalanceException, InvalidTransactionException {
    if (amount <= 0) {
      throw new InvalidTransactionException("Amount must be positive");
    }
    if (amount > balance) {
      throw new InsufficientBalanceException(
          "Insufficient balance! Available: $" + String.format("%.2f", balance));
    }
    balance -= amount;
    transactionHistory.add(new Transaction("WITHDRAW", amount, "Money withdrawn"));
    System.out.println("✓ Withdrawn $" + String.format("%.2f", amount) +
        " from wallet " + walletId);
  }

  public void addTransaction(Transaction transaction) {
    transactionHistory.add(transaction);
  }

  public void displayBalance() {
    System.out.println("Wallet: " + walletId + " | User: " + userId +
        " | Balance: $" + String.format("%.2f", balance));
  }

  public void displayTransactionHistory() {
    if (transactionHistory.isEmpty()) {
      System.out.println("No transactions yet");
      return;
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║           TRANSACTION HISTORY - " + walletId + "                  ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Transaction txn : transactionHistory) {
      System.out.println(txn);
    }

    System.out.println("═════════════════════════════════════════════════════════════════════");
    System.out.println("Current Balance: $" + String.format("%.2f", balance));
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public double calculateTotalSpent() {
    return transactionHistory.stream()
        .filter(t -> t.getType().equals("WITHDRAW") || t.getType().equals("TRANSFER_SENT"))
        .mapToDouble(Transaction::getAmount)
        .sum();
  }

  public double calculateTotalReceived() {
    return transactionHistory.stream()
        .filter(t -> t.getType().equals("ADD") || t.getType().equals("TRANSFER_RECEIVED"))
        .mapToDouble(Transaction::getAmount)
        .sum();
  }

  @Override
  public String toString() {
    return String.format("Wallet ID: %s | User: %s | Balance: $%.2f | Transactions: %d",
        walletId, userId, balance, transactionHistory.size());
  }
}

public class DigitalWalletSystem {
  private Map<String, Wallet> wallets;

  public DigitalWalletSystem() {
    this.wallets = new HashMap<>();
  }

  public Wallet createWallet(String walletId, String userId, double initialBalance) {
    Wallet wallet = new Wallet(walletId, userId, initialBalance);
    wallets.put(walletId, wallet);
    System.out.println("✓ Wallet created: " + walletId);
    return wallet;
  }

  public Wallet getWallet(String walletId) {
    return wallets.get(walletId);
  }

  public void displayAllWallets() {
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                      ALL WALLETS                                   ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Wallet wallet : wallets.values()) {
      System.out.println(wallet);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public double getTotalBalance() {
    return wallets.values().stream()
        .mapToDouble(Wallet::getBalance)
        .sum();
  }

  public static void main(String[] args) {
    DigitalWalletSystem system = new DigitalWalletSystem();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║        DIGITAL WALLET SYSTEM - DEMONSTRATION                     ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Create wallets
    System.out.println("--- PHASE 1: Create Wallets ---");
    Wallet wallet1 = system.createWallet("W001", "Alice", 1000.0);
    Wallet wallet2 = system.createWallet("W002", "Bob", 500.0);
    Wallet wallet3 = system.createWallet("W003", "Charlie", 750.0);

    system.displayAllWallets();

    // PHASE 2: Add money
    System.out.println("--- PHASE 2: Add Money ---");
    try {
      wallet1.addMoney(500);
      wallet2.addMoney(250);
    } catch (InvalidTransactionException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    system.displayAllWallets();

    // PHASE 3: Wallet-to-Wallet Transfer
    System.out.println("--- PHASE 3: Wallet-to-Wallet Transfer ---");
    TransferService walletTransfer = new WalletTransfer();
    try {
      walletTransfer.transferFunds(wallet1, wallet2, 200);
      walletTransfer.transferFunds(wallet2, wallet3, 150);
    } catch (InsufficientBalanceException | InvalidTransactionException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    system.displayAllWallets();

    // PHASE 4: Bank Transfer
    System.out.println("--- PHASE 4: Bank Transfer ---");
    TransferService bankTransfer = new BankTransfer();
    try {
      bankTransfer.transferFunds(wallet1, wallet3, 300);
    } catch (InsufficientBalanceException | InvalidTransactionException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    system.displayAllWallets();

    // PHASE 5: Withdraw money
    System.out.println("--- PHASE 5: Withdraw Money ---");
    try {
      wallet1.withdrawMoney(100);
      wallet2.withdrawMoney(50);
    } catch (InsufficientBalanceException | InvalidTransactionException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    system.displayAllWallets();

    // PHASE 6: Display transaction history
    System.out.println("--- PHASE 6: Transaction History ---");
    wallet1.displayTransactionHistory();
    wallet2.displayTransactionHistory();

    // PHASE 7: Error handling
    System.out.println("--- PHASE 7: Error Handling ---");
    try {
      wallet3.withdrawMoney(5000); // Should fail
    } catch (InsufficientBalanceException | InvalidTransactionException e) {
      System.out.println("✗ Error caught: " + e.getMessage());
    }

    try {
      walletTransfer.transferFunds(wallet3, wallet1, 10000); // Should fail
    } catch (InsufficientBalanceException | InvalidTransactionException e) {
      System.out.println("✗ Error caught: " + e.getMessage());
    }

    // PHASE 8: Statistics
    System.out.println("\n--- PHASE 8: Wallet Statistics ---");
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    WALLET STATISTICS                               ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");
    System.out.println("Total System Balance: $" + String.format("%.2f", system.getTotalBalance()));
    System.out.println("Number of Wallets: " + system.wallets.size());

    System.out.println("\nPer-Wallet Statistics:");
    for (Wallet w : system.wallets.values()) {
      System.out.println("  " + w.getUserId() + ": Spent: $" +
          String.format("%.2f", w.calculateTotalSpent()) +
          " | Received: $" + String.format("%.2f", w.calculateTotalReceived()));
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                           ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
  }
}
