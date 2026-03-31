/**
 * Problem Statement: Sorting Large Data Efficiently
 * 
 * Objective:
 * Compare sorting algorithms: Bubble Sort (O(N²)), Merge Sort (O(N log N)),
 * and Quick Sort (O(N log N)).
 * 
 * Approach:
 * - Bubble Sort: Repeated swapping (inefficient for large data).
 * - Merge Sort: Divide & Conquer approach (stable, consistent performance).
 * - Quick Sort: Partition-based approach (fast, unstable, varies with pivot
 * selection).
 * 
 * Time Complexity:
 * - Bubble Sort: O(N²) - worst, average, and best case
 * - Merge Sort: O(N log N) - all cases
 * - Quick Sort: O(N log N) average, O(N²) worst case
 * 
 * Space Complexity:
 * - Bubble Sort: O(1)
 * - Merge Sort: O(N)
 * - Quick Sort: O(log N) for recursion stack
 */
public class SortingLargeDataEfficiently {

  /**
   * Bubble Sort - O(N²)
   * Repeatedly swaps adjacent elements if they're in wrong order
   */
  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          // Swap
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  /**
   * Merge Sort - O(N log N)
   * Divide & Conquer approach, stable sorting
   */
  public static void mergeSort(int[] arr) {
    if (arr.length < 2)
      return;
    int mid = arr.length / 2;

    int[] left = new int[mid];
    int[] right = new int[arr.length - mid];

    System.arraycopy(arr, 0, left, 0, mid);
    System.arraycopy(arr, mid, right, 0, arr.length - mid);

    mergeSort(left);
    mergeSort(right);
    merge(arr, left, right);
  }

  /**
   * Helper method for merging in Merge Sort
   */
  private static void merge(int[] arr, int[] left, int[] right) {
    int i = 0, j = 0, k = 0;

    while (i < left.length && j < right.length) {
      if (left[i] <= right[j]) {
        arr[k++] = left[i++];
      } else {
        arr[k++] = right[j++];
      }
    }

    while (i < left.length) {
      arr[k++] = left[i++];
    }

    while (j < right.length) {
      arr[k++] = right[j++];
    }
  }

  /**
   * Quick Sort - O(N log N) average case
   * Partition-based approach, faster in practice
   */
  public static void quickSort(int[] arr) {
    if (arr.length == 0)
      return;
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      int pivot = partition(arr, low, high);
      quickSort(arr, low, pivot - 1);
      quickSort(arr, pivot + 1, high);
    }
  }

  /**
   * Helper method for partitioning in Quick Sort
   */
  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
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
   * Measure sorting performance
   */
  public static void measurePerformance(int datasetSize) {
    System.out.println("\n--- Dataset Size: " + datasetSize + " ---");

    // Bubble Sort (skip for large datasets)
    if (datasetSize <= 10000) {
      int[] bubbleData = generateDataset(datasetSize);
      long startBubble = System.nanoTime();
      bubbleSort(bubbleData);
      long endBubble = System.nanoTime();
      double bubbleTime = (endBubble - startBubble) / 1_000_000.0;
      System.out.printf("Bubble Sort (O(N²)): %.3fms%n", bubbleTime);
    } else {
      System.out.println("Bubble Sort (O(N²)): [SKIPPED - Too slow for large dataset]");
    }

    // Merge Sort
    int[] mergeData = generateDataset(datasetSize);
    long startMerge = System.nanoTime();
    mergeSort(mergeData);
    long endMerge = System.nanoTime();
    double mergeTime = (endMerge - startMerge) / 1_000_000.0;
    System.out.printf("Merge Sort (O(N log N)): %.3fms%n", mergeTime);

    // Quick Sort
    int[] quickData = generateDataset(datasetSize);
    long startQuick = System.nanoTime();
    quickSort(quickData);
    long endQuick = System.nanoTime();
    double quickTime = (endQuick - startQuick) / 1_000_000.0;
    System.out.printf("Quick Sort (O(N log N)): %.3fms%n", quickTime);
  }

  public static void main(String[] args) {
    System.out.println("===== Sorting Large Data Efficiently =====");
    System.out.println("Bubble Sort: O(N²) | Merge Sort: O(N log N) | Quick Sort: O(N log N) avg");

    measurePerformance(1_000);
    measurePerformance(10_000);
    measurePerformance(100_000);

    System.out.println("\n\nConclusion:");
    System.out.println("- Bubble Sort is O(N²) and becomes impractical for large datasets");
    System.out.println("- Merge Sort and Quick Sort are both O(N log N) on average");
    System.out.println("- Quick Sort is typically faster due to better cache locality");
    System.out.println("- Merge Sort is stable and has consistent performance");
    System.out.println("- For production use, prefer Merge Sort (stable) or Quick Sort (faster)");
  }
}
