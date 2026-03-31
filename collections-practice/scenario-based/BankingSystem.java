import java.util.*;

public class BankingSystem {
    
    static class Account {
        String accountNumber;
        String holderName;
        double balance;
        String accountType;

        Account(String accountNumber, String holderName, double balance, String accountType) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
            this.accountType = accountType;
        }

        @Override
        public String toString() {
            return accountNumber + " (" + holderName + ") - Balance: $" + 
                   String.format("%.2f", balance);
        }
    }

    static class WithdrawalRequest {
        String accountNumber;
        double amount;
        long timestamp;

        WithdrawalRequest(String accountNumber, double amount) {
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return accountNumber + " - Withdrawal: $" + String.format("%.2f", amount);
        }
    }

    static class BankManager {
        private Map<String, Account> accounts = new HashMap<>();
    
        private Map<Double, List<String>> accountsByBalance = new TreeMap<>(Collections.reverseOrder());

        private Queue<WithdrawalRequest> withdrawalQueue = new LinkedList<>();
        
        private double totalTransactions = 0;

        public void createAccount(String accountNumber, String holderName, 
                                 double initialBalance, String accountType) {
            if (accounts.containsKey(accountNumber)) {
                System.out.println("✗ Account already exists: " + accountNumber);
                return;
            }
            
            if (initialBalance < 0) {
                System.out.println("✗ Invalid initial balance");
                return;
            }
            
            Account account = new Account(accountNumber, holderName, initialBalance, accountType);
            accounts.put(accountNumber, account);

            accountsByBalance.computeIfAbsent(initialBalance, k -> new ArrayList<>())
                           .add(accountNumber);
            
            System.out.println("✓ Account created: " + accountNumber);
        }

        public void deposit(String accountNumber, double amount) {
            if (!accounts.containsKey(accountNumber)) {
                System.out.println("✗ Account not found: " + accountNumber);
                return;
            }
            
            if (amount <= 0) {
                System.out.println("✗ Invalid deposit amount");
                return;
            }
            
            Account account = accounts.get(accountNumber);
            
            accountsByBalance.get(account.balance).remove(accountNumber);
            if (accountsByBalance.get(account.balance).isEmpty()) {
                accountsByBalance.remove(account.balance);
            }
            
            account.balance += amount;
            totalTransactions += amount;

            accountsByBalance.computeIfAbsent(account.balance, k -> new ArrayList<>())
                           .add(accountNumber);
            
            System.out.println("✓ Deposited $" + String.format("%.2f", amount) + 
                             " to " + accountNumber + ". New balance: $" + 
                             String.format("%.2f", account.balance));
        }

        public void requestWithdrawal(String accountNumber, double amount) {
            if (!accounts.containsKey(accountNumber)) {
                System.out.println("✗ Account not found: " + accountNumber);
                return;
            }
            
            if (amount <= 0) {
                System.out.println("✗ Invalid withdrawal amount");
                return;
            }
            
            WithdrawalRequest request = new WithdrawalRequest(accountNumber, amount);
            withdrawalQueue.add(request);
            System.out.println("✓ Withdrawal request queued: " + request);
        }
        public void processWithdrawals() {
            System.out.println("\n=== Processing Withdrawal Requests ===");
            
            if (withdrawalQueue.isEmpty()) {
                System.out.println("No pending withdrawal requests.");
                return;
            }
            
            while (!withdrawalQueue.isEmpty()) {
                WithdrawalRequest request = withdrawalQueue.poll();
                
                Account account = accounts.get(request.accountNumber);
                if (account == null) {
                    System.out.println("✗ Account not found: " + request.accountNumber);
                    continue;
                }
                
                if (account.balance < request.amount) {
                    System.out.println("✗ Insufficient balance for " + request.accountNumber + 
                                     " (Available: $" + String.format("%.2f", account.balance) + 
                                     ", Requested: $" + String.format("%.2f", request.amount) + ")");
                    continue;
                }
                
                accountsByBalance.get(account.balance).remove(request.accountNumber);
                if (accountsByBalance.get(account.balance).isEmpty()) {
                    accountsByBalance.remove(account.balance);
                }
                
                account.balance -= request.amount;
                totalTransactions += request.amount;

                accountsByBalance.computeIfAbsent(account.balance, k -> new ArrayList<>())
                               .add(request.accountNumber);
                
                System.out.println("✓ Withdrawal processed: " + request.accountNumber + 
                                 " - $" + String.format("%.2f", request.amount) + 
                                 ". New balance: $" + String.format("%.2f", account.balance));
            }
        }

        public double getBalance(String accountNumber) {
            Account account = accounts.get(accountNumber);
            if (account == null) {
                System.out.println("✗ Account not found: " + accountNumber);
                return -1;
            }
            return account.balance;
        }

        public void displayAllAccounts() {
            System.out.println("\n=== All Accounts ===");
            if (accounts.isEmpty()) {
                System.out.println("No accounts found.");
                return;
            }
            accounts.values().forEach(System.out::println);
        }

        public void displayAccountsSortedByBalance() {
            System.out.println("\n=== Accounts Sorted by Balance (Highest First) ===");
            if (accounts.isEmpty()) {
                System.out.println("No accounts found.");
                return;
            }
            
            accountsByBalance.forEach((balance, accountNumbers) -> {
                System.out.println("Balance: $" + String.format("%.2f", balance));
                for (String accountNumber : accountNumbers) {
                    Account account = accounts.get(accountNumber);
                    System.out.println("  " + account);
                }
            });
        }

        public void displayPendingRequests() {
            System.out.println("\n=== Pending Withdrawal Requests ===");
            if (withdrawalQueue.isEmpty()) {
                System.out.println("No pending requests.");
                return;
            }
            withdrawalQueue.forEach(System.out::println);
        }

        public void displayStatistics() {
            System.out.println("\n=== Bank Statistics ===");
            System.out.println("Total Accounts: " + accounts.size());
            
            double totalBalance = accounts.values().stream()
                                        .mapToDouble(a -> a.balance)
                                        .sum();
            System.out.println("Total Balance (All Accounts): $" + String.format("%.2f", totalBalance));
            System.out.println("Total Transactions Processed: $" + String.format("%.2f", totalTransactions));
            System.out.println("Pending Withdrawals: " + withdrawalQueue.size());
            
            Map<String, Long> accountsByType = new HashMap<>();
            accounts.values().forEach(account -> 
                accountsByType.put(account.accountType, 
                    accountsByType.getOrDefault(account.accountType, 0L) + 1)
            );
            
            System.out.println("\nAccounts by Type:");
            accountsByType.forEach((type, count) -> 
                System.out.println("  " + type + ": " + count));
        }

        public void transfer(String fromAccount, String toAccount, double amount) {
            if (!accounts.containsKey(fromAccount)) {
                System.out.println("✗ Source account not found: " + fromAccount);
                return;
            }
            
            if (!accounts.containsKey(toAccount)) {
                System.out.println("✗ Destination account not found: " + toAccount);
                return;
            }
            
            if (amount <= 0) {
                System.out.println("✗ Invalid transfer amount");
                return;
            }
            
            Account from = accounts.get(fromAccount);
            if (from.balance < amount) {
                System.out.println("✗ Insufficient balance in source account");
                return;
            }

            accountsByBalance.get(from.balance).remove(fromAccount);
            if (accountsByBalance.get(from.balance).isEmpty()) {
                accountsByBalance.remove(from.balance);
            }
            
            Account to = accounts.get(toAccount);
            accountsByBalance.get(to.balance).remove(toAccount);
            if (accountsByBalance.get(to.balance).isEmpty()) {
                accountsByBalance.remove(to.balance);
            }
            
            from.balance -= amount;
            to.balance += amount;
            totalTransactions += amount;

            accountsByBalance.computeIfAbsent(from.balance, k -> new ArrayList<>())
                           .add(fromAccount);
            accountsByBalance.computeIfAbsent(to.balance, k -> new ArrayList<>())
                           .add(toAccount);
            
            System.out.println("✓ Transferred $" + String.format("%.2f", amount) + 
                             " from " + fromAccount + " to " + toAccount);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Banking System ===\n");
        
        BankManager bank = new BankManager();

        System.out.println("--- Creating Accounts ---");
        bank.createAccount("ACC001", "John Doe", 5000, "Savings");
        bank.createAccount("ACC002", "Jane Smith", 8000, "Checking");
        bank.createAccount("ACC003", "Bob Johnson", 3000, "Savings");
        bank.createAccount("ACC004", "Alice Williams", 12000, "Premium");
        bank.createAccount("ACC005", "Charlie Brown", 2000, "Checking");

        bank.displayAllAccounts();
        bank.displayAccountsSortedByBalance();

        System.out.println("\n--- Performing Transactions ---");
        bank.deposit("ACC001", 1000);
        bank.deposit("ACC002", 500);
        bank.transfer("ACC001", "ACC003", 2000);
        
        bank.displayAccountsSortedByBalance();

        System.out.println("\n--- Requesting Withdrawals ---");
        bank.requestWithdrawal("ACC001", 1500);
        bank.requestWithdrawal("ACC002", 3000);
        bank.requestWithdrawal("ACC003", 500);
        bank.requestWithdrawal("ACC004", 20000); // Insufficient balance
        
        bank.displayPendingRequests();
        bank.processWithdrawals();

        bank.displayAccountsSortedByBalance();
        bank.displayStatistics();
    }
}
