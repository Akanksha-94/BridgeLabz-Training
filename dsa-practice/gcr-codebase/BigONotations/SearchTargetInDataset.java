import java.util.Arrays;

/**
 * Problem Statement: Search a Target in a Large Dataset
 * 
 * Objective:
 * Compare the performance of Linear Search (O(N)) and Binary Search (O(log N))
 * on different dataset sizes.
 * 
 * Approach:
 * - Linear Search: Scan each element until the target is found.
 * - Binary Search: Sort the data first (O(N log N)), then perform O(log N)
 * search.
 * 
 * Time Complexity:
 * - Linear Search: O(N)
 * - Binary Search: O(log N) after sorting
 * 
 * Space Complexity: O(1) for both
 */
public class SearchTargetInDataset {

  /**
   * Linear Search - O(N)
   * Iterates through each element until target is found
   */
  public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Binary Search - O(log N)
   * Requires sorted array
   */
  public static int binarySearch(int[] arr, int target) {
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
   * Measure performance of linear and binary search
   */
  public static void measurePerformance(int datasetSize, int numberOfSearches) {
    int[] dataset = generateDataset(datasetSize);
    int[] targets = new int[numberOfSearches];

    // Generate random targets
    for (int i = 0; i < numberOfSearches; i++) {
      targets[i] = dataset[(int) (Math.random() * datasetSize)];
    }

    // Linear Search Performance
    long startLinear = System.nanoTime();
    for (int target : targets) {
      linearSearch(dataset, target);
    }
    long endLinear = System.nanoTime();
    double linearTime = (endLinear - startLinear) / 1_000_000.0; // Convert to milliseconds

    // Sort dataset for binary search
    Arrays.sort(dataset);

    // Binary Search Performance
    long startBinary = System.nanoTime();
    for (int target : targets) {
      binarySearch(dataset, target);
    }
    long endBinary = System.nanoTime();
    double binaryTime = (endBinary - startBinary) / 1_000_000.0; // Convert to milliseconds

    System.out.printf("Dataset Size: %,d | Linear Search: %.3fms | Binary Search: %.3fms | " +
        "Improvement: %.2fx%n", datasetSize, linearTime, binaryTime,
        linearTime / binaryTime);
  }

  public static void main(String[] args) {
    System.out.println("===== Search Target in Large Dataset =====");
    System.out.println("Linear Search: O(N) | Binary Search: O(log N)");
    System.out.println();

    int numberOfSearches = 100;

    System.out.println("Performance Comparison (Average of " + numberOfSearches + " searches):");
    System.out.println("---------------------------------------------------");

    measurePerformance(1_000, numberOfSearches);
    measurePerformance(10_000, numberOfSearches);
    measurePerformance(100_000, numberOfSearches);
    measurePerformance(1_000_000, numberOfSearches);

    System.out.println("\nConclusion:");
    System.out.println("- Binary Search performs significantly better for large datasets");
    System.out.println("- Linear Search is O(N) while Binary Search is O(log N)");
    System.out.println("- Trade-off: Binary Search requires sorted data (O(N log N) pre-processing)");
  }
}
