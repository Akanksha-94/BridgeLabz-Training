/**
 * Quick Sort - Sort Product Prices
 * Problem Statement:
 * An e-commerce company wants to display product prices in ascending order.
 * Implement Quick Sort to sort the product prices.
 * 
 * Algorithm:
 * 1. Pick a pivot element (first, last, or random)
 * 2. Partition the array such that elements smaller than the pivot are on the
 * left
 * and larger ones are on the right
 * 3. Recursively apply Quick Sort on left and right partitions
 * 
 * Time Complexity: O(n log n) average, O(n²) worst case
 * Space Complexity: O(log n) due to recursion
 */
public class QuickSortProductPrices {

  /**
   * Main method to initiate Quick Sort
   * 
   * @param prices array of product prices to be sorted
   */
  public static void quickSort(int[] prices) {
    if (prices == null || prices.length == 0) {
      return;
    }

    quickSortHelper(prices, 0, prices.length - 1);
  }

  /**
   * Recursive helper method for Quick Sort
   * 
   * @param prices array of product prices
   * @param low    starting index of the sub-array
   * @param high   ending index of the sub-array
   */
  private static void quickSortHelper(int[] prices, int low, int high) {
    if (low < high) {
      // Partition the array and get the pivot index
      int pivotIndex = partition(prices, low, high);

      // Recursively sort elements before and after partition
      quickSortHelper(prices, low, pivotIndex - 1);
      quickSortHelper(prices, pivotIndex + 1, high);
    }
  }

  /**
   * Partitions the array using the last element as pivot
   * 
   * @param prices array of product prices
   * @param low    starting index of the sub-array
   * @param high   ending index of the sub-array
   * @return the partition point
   */
  private static int partition(int[] prices, int low, int high) {
    // Select the last element as pivot
    int pivot = prices[high];

    // Index of smaller element - indicates the right position
    // of pivot found so far
    int i = low - 1;

    // Traverse through all elements
    // Compare each element with pivot
    for (int j = low; j < high; j++) {
      if (prices[j] < pivot) {
        i++; // Increment index of smaller element

        // Swap prices[i] and prices[j]
        int temp = prices[i];
        prices[i] = prices[j];
        prices[j] = temp;
      }
    }

    // Swap prices[i+1] and prices[high] (pivot)
    int temp = prices[i + 1];
    prices[i + 1] = prices[high];
    prices[high] = temp;

    return i + 1;
  }

  /**
   * Utility method to print product prices
   * 
   * @param prices array of product prices
   */
  public static void printPrices(int[] prices) {
    for (int price : prices) {
      System.out.print(price + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random product prices
    int[] prices1 = { 2499, 1299, 3999, 999, 2299, 1499, 4499 };
    System.out.println("Original Product Prices: ");
    printPrices(prices1);

    quickSort(prices1);
    System.out.println("Sorted Product Prices: ");
    printPrices(prices1);

    // Test case 2: Already sorted prices
    int[] prices2 = { 500, 1000, 1500, 2000, 2500 };
    System.out.println("\nAlready sorted Product Prices: ");
    printPrices(prices2);

    quickSort(prices2);
    System.out.println("After Quick Sort: ");
    printPrices(prices2);

    // Test case 3: Reverse sorted prices
    int[] prices3 = { 5000, 4000, 3000, 2000, 1000 };
    System.out.println("\nReverse sorted Product Prices: ");
    printPrices(prices3);

    quickSort(prices3);
    System.out.println("After Quick Sort: ");
    printPrices(prices3);

    // Test case 4: Duplicate prices
    int[] prices4 = { 1500, 1000, 1500, 750, 1000, 1250 };
    System.out.println("\nProduct Prices with duplicates: ");
    printPrices(prices4);

    quickSort(prices4);
    System.out.println("After Quick Sort: ");
    printPrices(prices4);

    // Test case 5: All same prices
    int[] prices5 = { 2000, 2000, 2000, 2000 };
    System.out.println("\nAll same Product Prices: ");
    printPrices(prices5);

    quickSort(prices5);
    System.out.println("After Quick Sort: ");
    printPrices(prices5);
  }
}
