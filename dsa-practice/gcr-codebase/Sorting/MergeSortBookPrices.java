/**
 * Merge Sort - Sort Book Prices
 * Problem Statement:
 * A bookstore maintains a list of book prices in an array. Implement Merge Sort
 * to sort the prices in ascending order.
 * 
 * Algorithm:
 * 1. Divide the array into two halves recursively
 * 2. Sort both halves individually
 * 3. Merge the sorted halves by comparing elements
 * 
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(n)
 */
public class MergeSortBookPrices {

  /**
   * Main method to initiate Merge Sort
   * 
   * @param prices array of book prices to be sorted
   */
  public static void mergeSort(int[] prices) {
    if (prices == null || prices.length == 0) {
      return;
    }

    mergeSortHelper(prices, 0, prices.length - 1);
  }

  /**
   * Recursive helper method for Merge Sort
   * 
   * @param prices array of book prices
   * @param left   starting index of the sub-array
   * @param right  ending index of the sub-array
   */
  private static void mergeSortHelper(int[] prices, int left, int right) {
    if (left < right) {
      // Find the middle point to divide the array
      int mid = left + (right - left) / 2;

      // Sort the left half
      mergeSortHelper(prices, left, mid);

      // Sort the right half
      mergeSortHelper(prices, mid + 1, right);

      // Merge the sorted halves
      merge(prices, left, mid, right);
    }
  }

  /**
   * Merges two sorted sub-arrays
   * 
   * @param prices array containing both sub-arrays
   * @param left   starting index of left sub-array
   * @param mid    ending index of left sub-array
   * @param right  ending index of right sub-array
   */
  private static void merge(int[] prices, int left, int mid, int right) {
    // Create temporary arrays for left and right sub-arrays
    int[] leftArray = new int[mid - left + 1];
    int[] rightArray = new int[right - mid];

    // Copy data to temporary arrays
    System.arraycopy(prices, left, leftArray, 0, leftArray.length);
    System.arraycopy(prices, mid + 1, rightArray, 0, rightArray.length);

    // Merge the temporary arrays back into prices array
    int i = 0; // Index for left sub-array
    int j = 0; // Index for right sub-array
    int k = left; // Index for main array

    while (i < leftArray.length && j < rightArray.length) {
      if (leftArray[i] <= rightArray[j]) {
        prices[k++] = leftArray[i++];
      } else {
        prices[k++] = rightArray[j++];
      }
    }

    // Copy remaining elements from left array
    while (i < leftArray.length) {
      prices[k++] = leftArray[i++];
    }

    // Copy remaining elements from right array
    while (j < rightArray.length) {
      prices[k++] = rightArray[j++];
    }
  }

  /**
   * Utility method to print book prices
   * 
   * @param prices array of book prices
   */
  public static void printPrices(int[] prices) {
    for (int price : prices) {
      System.out.print(price + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random book prices
    int[] prices1 = { 599, 299, 799, 199, 499, 349, 899 };
    System.out.println("Original Book Prices: ");
    printPrices(prices1);

    mergeSort(prices1);
    System.out.println("Sorted Book Prices: ");
    printPrices(prices1);

    // Test case 2: Already sorted prices
    int[] prices2 = { 100, 200, 300, 400, 500 };
    System.out.println("\nAlready sorted Book Prices: ");
    printPrices(prices2);

    mergeSort(prices2);
    System.out.println("After Merge Sort: ");
    printPrices(prices2);

    // Test case 3: Reverse sorted prices
    int[] prices3 = { 1000, 800, 600, 400, 200 };
    System.out.println("\nReverse sorted Book Prices: ");
    printPrices(prices3);

    mergeSort(prices3);
    System.out.println("After Merge Sort: ");
    printPrices(prices3);

    // Test case 4: Duplicate prices
    int[] prices4 = { 450, 300, 450, 200, 300, 350 };
    System.out.println("\nBook Prices with duplicates: ");
    printPrices(prices4);

    mergeSort(prices4);
    System.out.println("After Merge Sort: ");
    printPrices(prices4);

    // Test case 5: Single element
    int[] prices5 = { 750 };
    System.out.println("\nSingle Book Price: ");
    printPrices(prices5);

    mergeSort(prices5);
    System.out.println("After Merge Sort: ");
    printPrices(prices5);
  }
}
