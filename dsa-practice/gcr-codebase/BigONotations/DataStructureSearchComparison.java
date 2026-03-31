import java.util.*;

/**
 * Problem Statement: Comparing Different Data Structures for Searching
 * 
 * Objective:
 * Compare Array (O(N)), HashSet (O(1)), and TreeSet (O(log N)) for searching
 * elements.
 * 
 * Approach:
 * - Array: Linear search O(N) without sorting, O(log N) binary search if sorted
 * - HashSet: Hash-based, O(1) average case, O(N) worst case
 * - TreeSet: Balanced BST (Red-Black Tree), O(log N) always, maintains order
 * 
 * Time Complexity (Search):
 * - Array (Linear Search): O(N)
 * - Array (Binary Search): O(log N) - requires sorting
 * - HashSet: O(1) average, O(N) worst case (hash collision)
 * - TreeSet: O(log N) always, consistent performance
 * 
 * Space Complexity:
 * - Array: O(1) extra space
 * - HashSet: O(N) extra space (hash table)
 * - TreeSet: O(N) extra space (tree nodes)
 * 
 * Trade-offs:
 * - HashSet: Fastest search but no ordering, unpredictable worst case
 * - TreeSet: Sorted, consistent performance, slower than HashSet
 * - Array: Memory efficient, but slow search
 */
public class DataStructureSearchComparison {

  /**
   * Linear search in array - O(N)
   */
  public static int linearSearchArray(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Binary search in sorted array - O(log N)
   */
  public static int binarySearchArray(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

  /**
   * Generate random dataset
   */
  public static int[] generateDataset(int size) {
    int[] dataset = new int[size];
    for (int i = 0; i < size; i++) {
      dataset[i] = (int) (Math.random() * 1000000);
    }
    return dataset;
  }

  /**
   * Measure search performance across data structures
   */
  public static void measurePerformance(int datasetSize, int numberOfSearches) {
    int[] dataset = generateDataset(datasetSize);
    int[] searchTargets = new int[numberOfSearches];

    // Generate random search targets
    for (int i = 0; i < numberOfSearches; i++) {
      searchTargets[i] = dataset[(int) (Math.random() * datasetSize)];
    }

    System.out.println("\n--- Dataset Size: " + String.format("%,d", datasetSize) + " ---");
    System.out.println("(Measuring " + numberOfSearches + " searches)");

    // 1. Array Linear Search - O(N)
    long startLinear = System.nanoTime();
    for (int target : searchTargets) {
      linearSearchArray(dataset, target);
    }
    long endLinear = System.nanoTime();
    double linearTime = (endLinear - startLinear) / 1_000_000.0;
    System.out.printf("Array Linear Search (O(N)): %.3fms%n", linearTime);

    // 2. Array Binary Search - O(log N) (requires sorting first)
    int[] sortedArray = dataset.clone();
    Arrays.sort(sortedArray);

    long startBinary = System.nanoTime();
    for (int target : searchTargets) {
      binarySearchArray(sortedArray, target);
    }
    long endBinary = System.nanoTime();
    double binaryTime = (endBinary - startBinary) / 1_000_000.0;
    System.out.printf("Array Binary Search (O(log N)): %.3fms%n", binaryTime);

    // 3. HashSet - O(1) average
    HashSet<Integer> hashSet = new HashSet<>();
    for (int value : dataset) {
      hashSet.add(value);
    }

    long startHash = System.nanoTime();
    for (int target : searchTargets) {
      hashSet.contains(target);
    }
    long endHash = System.nanoTime();
    double hashTime = (endHash - startHash) / 1_000_000.0;
    System.out.printf("HashSet Search (O(1) avg): %.3fms%n", hashTime);

    // 4. TreeSet - O(log N)
    TreeSet<Integer> treeSet = new TreeSet<>();
    for (int value : dataset) {
      treeSet.add(value);
    }

    long startTree = System.nanoTime();
    for (int target : searchTargets) {
      treeSet.contains(target);
    }
    long endTree = System.nanoTime();
    double treeTime = (endTree - startTree) / 1_000_000.0;
    System.out.printf("TreeSet Search (O(log N)): %.3fms%n", treeTime);

    // Show relative performance
    System.out.println("\nRelative Performance (vs HashSet):");
    if (hashTime > 0) {
      System.out.printf("  Linear: %.1fx slower%n", linearTime / hashTime);
      System.out.printf("  Binary: %.1fx slower%n", binaryTime / hashTime);
      System.out.printf("  Tree: %.1fx slower%n", treeTime / hashTime);
    }
  }

  /**
   * Demonstrate HashSet internal structure
   */
  public static void demonstrateHashSet() {
    System.out.println("\n===== How HashSet Works =====");
    System.out.println("HashSet uses Hash Table internally:");
    System.out.println("1. Hash Function: Maps value to bucket index");
    System.out.println("2. Fast Lookup: O(1) average case");
    System.out.println("3. Collision Handling: Chaining or Open Addressing");
    System.out.println("4. No Order: Elements not stored in any order");
    System.out.println("\nExample Hash Table for numbers:");
    System.out.println("Index 0: [15, 45]");
    System.out.println("Index 1: []");
    System.out.println("Index 2: [22]");
    System.out.println("Index 3: [3, 33]");
    System.out.println("...");
    System.out.println("\nSearch for 45: hash(45) → index 0 → found (O(1))");
  }

  /**
   * Demonstrate TreeSet internal structure
   */
  public static void demonstrateTreeSet() {
    System.out.println("\n===== How TreeSet Works =====");
    System.out.println("TreeSet uses Red-Black Tree (Balanced BST) internally:");
    System.out.println("1. Ordered: Elements kept in sorted order");
    System.out.println("2. Self-Balancing: Always O(log N) search");
    System.out.println("3. Range Queries: Can find elements in range efficiently");
    System.out.println("\nExample Red-Black Tree:");
    System.out.println("       15");
    System.out.println("      /  \\");
    System.out.println("    10   20");
    System.out.println("   /  \\ /  \\");
    System.out.println("  5  12 18 25");
    System.out.println("\nSearch for 18: 15→20→18 (3 comparisons, O(log N))");
    System.out.println("Supports: floor(), ceiling(), higher(), lower()");
  }

  /**
   * Demonstrate space-time trade-off
   */
  public static void demonstrateSpaceTimeTradeoff() {
    System.out.println("\n===== Space vs Time Trade-off =====");
    System.out.println("Data Structure | Space | Search Time | Ordering");
    System.out.println("------|-------|-------------|----------");
    System.out.println("Array | O(1) | O(N) linear | No");
    System.out.println("       | O(1) | O(log N) binary | Yes (sorted)");
    System.out.println("HashSet | O(N) | O(1) avg | No");
    System.out.println("TreeSet | O(N) | O(log N) | Yes");
    System.out.println("\nChoose based on requirements:");
    System.out.println("- Need fastest search → HashSet");
    System.out.println("- Need sorted order → TreeSet");
    System.out.println("- Memory critical → Array with binary search");
    System.out.println("- Unknown access pattern → TreeSet (consistent)");
  }

  /**
   * Show when HashSet worst case occurs
   */
  public static void demonstrateHashSetWorstCase() {
    System.out.println("\n===== HashSet Worst Case: Hash Collision =====");
    System.out.println("Scenario: Poor hash function causes many collisions");
    System.out.println("\nNormal case:");
    System.out.println("  HashSet: Quick lookup O(1)");
    System.out.println("\nWorst case (all items hash to same bucket):");
    System.out.println("  HashSet becomes O(N) - like linked list");
    System.out.println("  Must traverse chain to find element");
    System.out.println("\nMitigation:");
    System.out.println("  - Good hash function: Distributes evenly");
    System.out.println("  - Dynamic resizing: Grows when load factor high");
    System.out.println("  - In Java: HashMap/HashSet has good hash function");
  }

  public static void main(String[] args) {
    System.out.println("===== Data Structure Search Comparison =====");
    System.out.println("Array (O(N/log N)) | HashSet (O(1)) | TreeSet (O(log N))");

    demonstrateHashSet();
    demonstrateTreeSet();
    demonstrateSpaceTimeTradeoff();
    demonstrateHashSetWorstCase();

    // Performance measurement
    System.out.println("\n\n===== PERFORMANCE MEASUREMENTS =====");
    int numberOfSearches = 1000;

    measurePerformance(1_000, numberOfSearches);
    measurePerformance(100_000, numberOfSearches);
    measurePerformance(1_000_000, numberOfSearches);

    System.out.println("\n\nConclusion:");
    System.out.println("- HashSet is fastest for simple lookups (O(1) average)");
    System.out.println("- TreeSet provides consistent O(log N) with ordering");
    System.out.println("- Array linear search is O(N) - slow for large datasets");
    System.out.println("- Array binary search is O(log N) but requires sorted data");
    System.out.println("- HashSet requires extra memory but is worth it for speed");
    System.out.println("- TreeSet is best for range queries and maintaining order");
    System.out.println("- Always consider your use case before choosing data structure");
  }
}
