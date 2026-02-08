import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankingSystem {
    
    static class BankAccount {
        private double balance;
        
        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }
        
        public boolean withdraw(String customerName, double amount) {
            System.out.println("[" + customerName + "] Attempting to withdraw " + amount);
            
            if (balance >= amount) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                System.out.println("Transaction successful: " + customerName + ", Amount: " + amount + ", Balance: " + balance + " at " + timestamp);
                return true;
            } else {
                System.out.println("Transaction failed: " + customerName + ", Insufficient funds. Current balance: " + balance);
                return false;
            }
        }
        
        public double getBalance() {
            return balance;
        }
    }
    
    static class Transaction implements Runnable {
        private BankAccount account;
        private String customerName;
        private double amount;
        
        public Transaction(BankAccount account, String customerName, double amount) {
            this.account = account;
            this.customerName = customerName;
            this.amount = amount;
        }
        
        @Override
        public void run() {
            System.out.println("[" + Thread.currentThread().getName() + "] State: " + Thread.currentThread().getState());
            account.withdraw(customerName, amount);
        }
    }
    
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10000);
        
        System.out.println("Initial Balance: " + account.getBalance());
        System.out.println("\nStarting transactions...\n");
        
        Thread t1 = new Thread(new Transaction(account, "Customer-1", 3000), "Customer-1");
        Thread t2 = new Thread(new Transaction(account, "Customer-2", 4000), "Customer-2");
        Thread t3 = new Thread(new Transaction(account, "Customer-3", 2000), "Customer-3");
        Thread t4 = new Thread(new Transaction(account, "Customer-4", 5000), "Customer-4");
        Thread t5 = new Thread(new Transaction(account, "Customer-5", 1500), "Customer-5");
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nFinal Balance: " + account.getBalance());
        System.out.println("\nNote: Race condition observed - balance may be inconsistent!");
    }
}