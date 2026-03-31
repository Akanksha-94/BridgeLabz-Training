import java.util.Scanner;

class InsufficientBalanceException extends Exception {
  public InsufficientBalanceException(String message) {
    super(message);
  }
}

class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public void withdraw(double amount) throws InsufficientBalanceException, IllegalArgumentException {
    if (amount < 0) {
      throw new IllegalArgumentException("Invalid amount!");
    }

    if (amount > balance) {
      throw new InsufficientBalanceException("Insufficient balance!");
    }

    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }
}

public class BankTransactionSystem {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    BankAccount account = new BankAccount(10000);

    try {
      System.out.println("Current balance: " + account.getBalance());
      System.out.print("Enter withdrawal amount: ");
      double amount = scanner.nextDouble();

      account.withdraw(amount);
      System.out.println("Withdrawal successful, new balance: " + account.getBalance());
    } catch (InsufficientBalanceException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } finally {
      scanner.close();
    }
  }
}
