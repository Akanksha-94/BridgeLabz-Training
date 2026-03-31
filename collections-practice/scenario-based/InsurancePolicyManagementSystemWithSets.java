import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class InsurancePolicyManagementSystemWithSets {
    
    static class Policy implements Comparable<Policy> {
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

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Policy)) return false;
            Policy other = (Policy) obj;
            return this.policyNumber.equals(other.policyNumber);
        }

        @Override
        public int hashCode() {
            return policyNumber.hashCode();
        }

        @Override
        public int compareTo(Policy other) {
       
            int dateComparison = this.expiryDate.compareTo(other.expiryDate);
            return dateComparison != 0 ? dateComparison : 
                   this.policyNumber.compareTo(other.policyNumber);
        }
    }

    static class PolicyManager {
        
        private Set<Policy> hashSetPolicies = new HashSet<>();
        
        private Set<Policy> linkedHashSetPolicies = new LinkedHashSet<>();

        private Set<Policy> treeSetPolicies = new TreeSet<>();

        private List<Policy> allPolicies = new ArrayList<>();

        public void addPolicy(Policy policy) {
            allPolicies.add(policy);
            hashSetPolicies.add(policy);
            linkedHashSetPolicies.add(policy);
            treeSetPolicies.add(policy);
            System.out.println("✓ Policy added: " + policy.policyNumber);
        }

        public void displayAllUniquePolicies() {
            System.out.println("\n=== All Unique Policies (HashSet) ===");
            if (hashSetPolicies.isEmpty()) {
                System.out.println("No policies found.");
                return;
            }
            hashSetPolicies.forEach(System.out::println);
            System.out.println("Total unique policies: " + hashSetPolicies.size());
        }

        public void displayPoliciesByInsertionOrder() {
            System.out.println("\n=== Policies by Insertion Order (LinkedHashSet) ===");
            if (linkedHashSetPolicies.isEmpty()) {
                System.out.println("No policies found.");
                return;
            }
            linkedHashSetPolicies.forEach(System.out::println);
        }

        public void displayPoliciesByExpiryDate() {
            System.out.println("\n=== Policies Sorted by Expiry Date (TreeSet) ===");
            if (treeSetPolicies.isEmpty()) {
                System.out.println("No policies found.");
                return;
            }
            treeSetPolicies.forEach(System.out::println);
        }

        public void findExpiringPolicies() {
            System.out.println("\n=== Policies Expiring in Next 30 Days ===");
            LocalDate today = LocalDate.now();
            LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);
            
            Set<Policy> expiringPolicies = new HashSet<>();
            for (Policy policy : hashSetPolicies) {
                if (!policy.expiryDate.isBefore(today) && 
                    !policy.expiryDate.isAfter(thirtyDaysLater)) {
                    expiringPolicies.add(policy);
                }
            }

            if (expiringPolicies.isEmpty()) {
                System.out.println("No policies expiring in the next 30 days.");
            } else {
                expiringPolicies.forEach(System.out::println);
                System.out.println("Total expiring policies: " + expiringPolicies.size());
            }
        }

        public void getPoliciesByCoverageType(String coverageType) {
            System.out.println("\n=== Policies with Coverage Type: " + coverageType + " ===");
            
            Set<Policy> filteredPolicies = new HashSet<>();
            for (Policy policy : hashSetPolicies) {
                if (policy.coverageType.equalsIgnoreCase(coverageType)) {
                    filteredPolicies.add(policy);
                }
            }

            if (filteredPolicies.isEmpty()) {
                System.out.println("No policies found with coverage type: " + coverageType);
            } else {
                filteredPolicies.forEach(System.out::println);
                System.out.println("Total: " + filteredPolicies.size());
            }
        }

        public void findDuplicatePolicies() {
            System.out.println("\n=== Duplicate Policy Detection ===");
            
            Set<String> seenPolicies = new HashSet<>();
            Set<String> duplicates = new HashSet<>();
            
            for (Policy policy : allPolicies) {
                if (!seenPolicies.add(policy.policyNumber)) {
                    duplicates.add(policy.policyNumber);
                }
            }

            if (duplicates.isEmpty()) {
                System.out.println("No duplicate policies found.");
            } else {
                System.out.println("Duplicate policy numbers found:");
                duplicates.forEach(dup -> System.out.println("  " + dup));
            }
            
            System.out.println("Total policies added: " + allPolicies.size());
            System.out.println("Unique policies: " + hashSetPolicies.size());
            System.out.println("Duplicate count: " + (allPolicies.size() - hashSetPolicies.size()));
        }

        public void performanceComparison() {
            System.out.println("\n=== Performance Comparison ===");
            
            int iterationCount = 10000;
            
            long startTime = System.nanoTime();
            Set<Policy> testHashSet = new HashSet<>();
            for (int i = 0; i < iterationCount; i++) {
                testHashSet.add(new Policy("POL" + i, "Holder" + i, 
                    LocalDate.now().plusDays(i % 365), 
                    "Health", 500 + i));
            }
            long hashSetAddTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            for (Policy p : testHashSet) {
                testHashSet.contains(p);
            }
            long hashSetSearchTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            Set<Policy> testLinkedHashSet = new LinkedHashSet<>();
            for (int i = 0; i < iterationCount; i++) {
                testLinkedHashSet.add(new Policy("POL" + i, "Holder" + i, 
                    LocalDate.now().plusDays(i % 365), 
                    "Auto", 500 + i));
            }
            long linkedHashSetAddTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            for (Policy p : testLinkedHashSet) {
                testLinkedHashSet.contains(p);
            }
            long linkedHashSetSearchTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            Set<Policy> testTreeSet = new TreeSet<>();
            for (int i = 0; i < iterationCount; i++) {
                testTreeSet.add(new Policy("POL" + i, "Holder" + i, 
                    LocalDate.now().plusDays(i % 365), 
                    "Home", 500 + i));
            }
            long treeSetAddTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            for (Policy p : testTreeSet) {
                testTreeSet.contains(p);
            }
            long treeSetSearchTime = System.nanoTime() - startTime;
            
            System.out.println("Operation count: " + iterationCount);
            System.out.println("\nAddition Performance:");
            System.out.println("  HashSet:      " + (hashSetAddTime / 1000000.0) + " ms");
            System.out.println("  LinkedHashSet: " + (linkedHashSetAddTime / 1000000.0) + " ms");
            System.out.println("  TreeSet:      " + (treeSetAddTime / 1000000.0) + " ms");
            
            System.out.println("\nSearch Performance:");
            System.out.println("  HashSet:      " + (hashSetSearchTime / 1000000.0) + " ms");
            System.out.println("  LinkedHashSet: " + (linkedHashSetSearchTime / 1000000.0) + " ms");
            System.out.println("  TreeSet:      " + (treeSetSearchTime / 1000000.0) + " ms");
        }

        public void displayStatistics() {
            System.out.println("\n=== Policy Statistics ===");
            System.out.println("Total unique policies: " + hashSetPolicies.size());
            
            Map<String, Integer> coverageCount = new HashMap<>();
            for (Policy policy : hashSetPolicies) {
                coverageCount.put(policy.coverageType,
                    coverageCount.getOrDefault(policy.coverageType, 0) + 1);
            }
            
            System.out.println("Coverage Types:");
            coverageCount.forEach((type, count) -> 
                System.out.println("  " + type + ": " + count));
            
            double avgPremium = hashSetPolicies.stream()
                                             .mapToDouble(p -> p.premiumAmount)
                                             .average()
                                             .orElse(0);
            System.out.println("Average Premium: $" + String.format("%.2f", avgPremium));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Insurance Policy Management System with Sets ===\n");
        
        PolicyManager manager = new PolicyManager();

        LocalDate today = LocalDate.now();
        manager.addPolicy(new Policy("POL001", "John Doe", today.plusDays(15), "Health", 500.0));
        manager.addPolicy(new Policy("POL002", "Jane Smith", today.plusDays(45), "Auto", 800.0));
        manager.addPolicy(new Policy("POL003", "John Doe", today.plusDays(60), "Home", 1200.0));
        manager.addPolicy(new Policy("POL004", "Bob Johnson", today.plusDays(20), "Health", 550.0));
        manager.addPolicy(new Policy("POL005", "Alice Williams", today.plusDays(5), "Auto", 850.0));
        manager.addPolicy(new Policy("POL006", "Jane Smith", today.minusDays(10), "Home", 1100.0));
        manager.addPolicy(new Policy("POL007", "Charlie Brown", today.plusDays(35), "Health", 600.0));  
        manager.addPolicy(new Policy("POL001", "John Doe", today.plusDays(15), "Health", 500.0));

        manager.displayAllUniquePolicies();
        manager.displayPoliciesByInsertionOrder();
        manager.displayPoliciesByExpiryDate();

        manager.findExpiringPolicies();
        manager.getPoliciesByCoverageType("Health");
        manager.getPoliciesByCoverageType("Auto");
        manager.getPoliciesByCoverageType("Home");

        manager.findDuplicatePolicies();

        manager.displayStatistics();

        manager.performanceComparison();
    }
}
