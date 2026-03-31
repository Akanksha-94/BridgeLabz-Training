import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class InsurancePolicyManagementSystem {
    
    static class Policy {
        String policyNumber;
        String holderName;
        LocalDate expiryDate;
        String coverageType;
        double premiumAmount;

        Policy(String policyNumber, String holderName, LocalDate expiryDate, 
               String coverageType, double premiumAmount) {
            this.policyNumber = policyNumber;
            this.holderName = holderName;
            this.expiryDate = expiryDate;
            this.coverageType = coverageType;
            this.premiumAmount = premiumAmount;
        }

        @Override
        public String toString() {
            return "Policy{" +
                    "Number='" + policyNumber + '\'' +
                    ", Holder='" + holderName + '\'' +
                    ", Expiry=" + expiryDate +
                    ", Coverage='" + coverageType + '\'' +
                    ", Premium=$" + premiumAmount +
                    '}';
        }
    }

    static class InsuranceManager {
        private Map<String, Policy> policyMapHashMap = new HashMap<>();
        
        private Map<String, Policy> policyMapLinkedHashMap = new LinkedHashMap<>();
        
        private Map<LocalDate, List<Policy>> policyMapTreeMap = new TreeMap<>();

        public void addPolicy(Policy policy) {
            policyMapHashMap.put(policy.policyNumber, policy);
            policyMapLinkedHashMap.put(policy.policyNumber, policy);
            
            policyMapTreeMap.computeIfAbsent(policy.expiryDate, k -> new ArrayList<>()).add(policy);
            
            System.out.println("✓ Policy added: " + policy.policyNumber);
        }

        public Policy getPolicyByNumber(String policyNumber) {
            return policyMapHashMap.getOrDefault(policyNumber, null);
        }

        public void listAllPolicies() {
            System.out.println("\n=== All Policies (Insertion Order) ===");
            if (policyMapLinkedHashMap.isEmpty()) {
                System.out.println("No policies found.");
                return;
            }
            policyMapLinkedHashMap.values().forEach(System.out::println);
        }

        public void listExpiringPolicies() {
            System.out.println("\n=== Policies Expiring in Next 30 Days ===");
            LocalDate today = LocalDate.now();
            LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);
            
            List<Policy> expiringPolicies = new ArrayList<>();
            for (Policy policy : policyMapHashMap.values()) {
                if (!policy.expiryDate.isBefore(today) && 
                    !policy.expiryDate.isAfter(thirtyDaysLater)) {
                    expiringPolicies.add(policy);
                }
            }

            if (expiringPolicies.isEmpty()) {
                System.out.println("No policies expiring in the next 30 days.");
            } else {
                expiringPolicies.sort(Comparator.comparing(p -> p.expiryDate));
                expiringPolicies.forEach(System.out::println);
            }
        }

        public void listPoliciesByHolder(String holderName) {
            System.out.println("\n=== Policies for " + holderName + " ===");
            List<Policy> holderPolicies = new ArrayList<>();
            for (Policy policy : policyMapHashMap.values()) {
                if (policy.holderName.equalsIgnoreCase(holderName)) {
                    holderPolicies.add(policy);
                }
            }

            if (holderPolicies.isEmpty()) {
                System.out.println("No policies found for " + holderName);
            } else {
                holderPolicies.forEach(System.out::println);
            }
        }

        public void removeExpiredPolicies() {
            System.out.println("\n=== Removing Expired Policies ===");
            LocalDate today = LocalDate.now();
            List<String> expiredKeys = new ArrayList<>();

            for (Map.Entry<String, Policy> entry : policyMapHashMap.entrySet()) {
                if (entry.getValue().expiryDate.isBefore(today)) {
                    expiredKeys.add(entry.getKey());
                }
            }

            if (expiredKeys.isEmpty()) {
                System.out.println("No expired policies found.");
            } else {
                for (String key : expiredKeys) {
                    Policy removedPolicy = policyMapHashMap.remove(key);
                    policyMapLinkedHashMap.remove(key);
                    policyMapTreeMap.get(removedPolicy.expiryDate).remove(removedPolicy);
                    System.out.println("✓ Removed: " + key);
                }
            }
        }

        public void listPoliciesByExpiryDate() {
            System.out.println("\n=== Policies Sorted by Expiry Date ===");
            if (policyMapTreeMap.isEmpty()) {
                System.out.println("No policies found.");
                return;
            }
            policyMapTreeMap.forEach((date, policies) -> {
                System.out.println("Expiry Date: " + date);
                policies.forEach(p -> System.out.println("  " + p));
            });
        }

        public void displayStatistics() {
            System.out.println("\n=== Policy Statistics ===");
            System.out.println("Total Policies: " + policyMapHashMap.size());
            
            Map<String, Long> coverageCount = new HashMap<>();
            double totalPremium = 0;
            for (Policy policy : policyMapHashMap.values()) {
                coverageCount.put(policy.coverageType, 
                    coverageCount.getOrDefault(policy.coverageType, 0L) + 1);
                totalPremium += policy.premiumAmount;
            }
            
            System.out.println("Coverage Types:");
            coverageCount.forEach((type, count) -> 
                System.out.println("  " + type + ": " + count));
            System.out.println("Total Premium Amount: $" + String.format("%.2f", totalPremium));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Insurance Policy Management System ===\n");
        
        InsuranceManager manager = new InsuranceManager();
        LocalDate today = LocalDate.now();
        manager.addPolicy(new Policy("POL001", "John Doe", today.plusDays(15), "Health", 500.0));
        manager.addPolicy(new Policy("POL002", "Jane Smith", today.plusDays(45), "Auto", 800.0));
        manager.addPolicy(new Policy("POL003", "John Doe", today.plusDays(60), "Home", 1200.0));
        manager.addPolicy(new Policy("POL004", "Bob Johnson", today.plusDays(20), "Health", 550.0));
        manager.addPolicy(new Policy("POL005", "Alice Williams", today.plusDays(5), "Auto", 850.0));
        manager.addPolicy(new Policy("POL006", "Jane Smith", today.minusDays(10), "Home", 1100.0));
        manager.addPolicy(new Policy("POL007", "Charlie Brown", today.plusDays(35), "Health", 600.0));

        manager.listAllPolicies();
        manager.listExpiringPolicies();
        manager.listPoliciesByHolder("John Doe");
        manager.listPoliciesByExpiryDate();
        manager.displayStatistics();
     
        System.out.println("\n=== Retrieve Specific Policy ===");
        Policy policy = manager.getPolicyByNumber("POL001");
        if (policy != null) {
            System.out.println("Found: " + policy);
        }

        manager.removeExpiredPolicies();
        
        System.out.println("\n=== Final Statistics ===");
        manager.displayStatistics();
    }
}
