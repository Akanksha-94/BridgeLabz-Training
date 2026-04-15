import java.io.*;
import java.util.*;

abstract class Account {
  String accountNumber;
  String holderName;
  double balance;

  Account(String an, String hn, double b) {
    accountNumber = an;
    holderName = hn;
    balance = b;
  }

  void deposit(double amount) {
    balance += amount;
  }

  abstract boolean withdraw(double amount);
}

class SavingsAccount extends Account {
  SavingsAccount(String an, String hn, double b) {
    super(an, hn, b);
  }

  boolean withdraw(double amount) {
    double charge = 2;
    double total = amount + charge;
    if (balance - total >= 0) {
      balance -= total;
      return true;
    }
    return false;
  }
}

class CurrentAccount extends Account {
  CurrentAccount(String an, String hn, double b) {
    super(an, hn, b);
  }

  boolean withdraw(double amount) {
    double charge = 5;
    double total = amount + charge;
    if (balance - total >= -10000) {
      balance -= total;
      return true;
    }
    return false;
  }
}

class BusinessAccount extends Account {
  BusinessAccount(String an, String hn, double b) {
    super(an, hn, b);
  }

  boolean withdraw(double amount) {
    double charge = amount * 0.01;
    double total = amount + charge;
    if (balance - total >= -50000) {
      balance -= total;
      return true;
    }
    return false;
  }
}

public class BankAccountManagement {
  private static List<Account> accounts = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(" ");
      String cmd = parts[0];
      if (cmd.equals("Create")) {
        String an = parts[1];
        String hn = parts[2];
        String type = parts[3];
        double bal = Double.parseDouble(parts[4]);
        Account acc = null;
        if (type.equals("SavingsAccount")) {
          acc = new SavingsAccount(an, hn, bal);
        } else if (type.equals("CurrentAccount")) {
          acc = new CurrentAccount(an, hn, bal);
        } else if (type.equals("BusinessAccount")) {
          acc = new BusinessAccount(an, hn, bal);
        }
        if (acc != null) {
          accounts.add(acc);
          System.out.println("Account Created: " + an);
        }
      } else if (cmd.equals("Deposit")) {
        String an = parts[1];
        double amt = Double.parseDouble(parts[2]);
        Account acc = findAccount(an);
        if (acc != null) {
          acc.deposit(amt);
          System.out.println("Deposited Successfully");
        } else {
          System.out.println("Account Not Found");
        }
      } else if (cmd.equals("Withdraw")) {
        String an = parts[1];
        double amt = Double.parseDouble(parts[2]);
        Account acc = findAccount(an);
        if (acc != null) {
          if (acc.withdraw(amt)) {
            System.out.println("Withdrawal Successful");
          } else {
            System.out.println("Insufficient Funds");
          }
        } else {
          System.out.println("Account Not Found");
        }
      }
    }
  }

  private static Account findAccount(String an) {
    for (Account a : accounts) {
      if (a.accountNumber.equals(an))
        return a;
    }
    return null;
  }
}