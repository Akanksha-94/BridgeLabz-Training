/**
 * Binary Search Problem 2: Find the Peak Element in an Array
 * 
 * Problem:
 * A peak element is an element that is greater than its neighbors.
 * Write a program that performs Binary Search to find a peak element in an
 * array.
 * If there are multiple peak elements, return any one of them.
 * 
 * Approach:
 * 1. Initialize left as 0 and right as n - 1.
 * 2. Perform a binary search:
 * - Find the middle element mid = (left + right) / 2.
 * - If arr[mid] > arr[mid - 1] and arr[mid] > arr[mid + 1], arr[mid] is a peak
 * element.
 * - If arr[mid] < arr[mid - 1], then search the left half, updating right = mid
 * - 1.
 * - If arr[mid] < arr[mid + 1], then search the right half, updating left = mid
 * + 1.
 * 3. Continue until a peak element is found.
 */

public class FindPeakElement {

  /**
   * Finds a peak element in an array using binary search
   * 
   * @param arr the array to search
   * @return the index of a peak element, or -1 if no peak exists
   */
  public static int findPeak(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    if (arr.length == 1) {
      return 0;
    }

    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      // Check if mid is a peak element
      boolean isGreaterThanLeft = (mid == 0) || (arr[mid] > arr[mid - 1]);
      boolean isGreaterThanRight = (mid == arr.length - 1) || (arr[mid] > arr[mid + 1]);

      if (isGreaterThanLeft && isGreaterThanRight) {
        return mid;
      }

      // If right neighbor is greater, search right half
      if (mid < arr.length - 1 && arr[mid] < arr[mid + 1]) {
        left = mid + 1;
      } else {
        // Search left half
        right = mid - 1;
      }
    }

    return -1;
  }

  /**
   * Finds all peak elements in the array
   * 
   * @param arr the array to search
   * @return array of indices of all peak elements
   */
  public static int[] findAllPeaks(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }

    java.util.List<Integer> peaks = new java.util.ArrayList<>();

    for (int i = 0; i < arr.length; i++) {
      boolean isGreaterThanLeft = (i == 0) || (arr[i] > arr[i - 1]);
      boolean isGreaterThanRight = (i == arr.length - 1) || (arr[i] > arr[i + 1]);

      if (isGreaterThanLeft && isGreaterThanRight) {
        peaks.add(i);
      }
    }

    int[] result = new int[peaks.size()];
    for (int i = 0; i < peaks.size(); i++) {
      result[i] = peaks.get(i);
    }
    return result;
  }

  /**
   * Displays peak element information
   * 
   * @param arr the array to search
   */
  public static void displayPeakElement(int[] arr) {
    System.out.print("Array: [");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");

    int peakIndex = findPeak(arr);
    if (peakIndex != -1) {
      System.out.println("✓ Peak element found at index: " + peakIndex);
      System.out.println("  Value: " + arr[peakIndex]);
    } else {
      System.out.println("✗ No peak element found");
    }

    // Display all peaks
    int[] allPeaks = findAllPeaks(arr);
    System.out.print("All peak elements: [");
    for (int i = 0; i < allPeaks.length; i++) {
      System.out.print(arr[allPeaks[i]]);
      if (i < allPeaks.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");
    System.out.println();
  }

  /**
   * Main method to test peak element finding
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║  Binary Search: Find Peak Element in Array                 ║");
    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

    // Test case 1: Single peak in middle
    int[] arr1 = { 1, 2, 3, 4, 5, 4, 3, 2, 1 };
    System.out.println("Test Case 1: Single peak in middle");
    displayPeakElement(arr1);

    // Test case 2: Multiple peaks
    int[] arr2 = { 1, 3, 2, 4, 3, 5, 2, 1 };
    System.out.println("Test Case 2: Multiple peaks");
    displayPeakElement(arr2);

    // Test case 3: Peak at beginning
    int[] arr3 = { 5, 4, 3, 2, 1 };
    System.out.println("Test Case 3: Peak at beginning");
    displayPeakElement(arr3);

    // Test case 4: Peak at end
    int[] arr4 = { 1, 2, 3, 4, 5 };
    System.out.println("Test Case 4: Peak at end");
    displayPeakElement(arr4);

    // Test case 5: Single element
    int[] arr5 = { 42 };
    System.out.println("Test Case 5: Single element");
    displayPeakElement(arr5);

    // Test case 6: Two elements (ascending)
    int[] arr6 = { 1, 5 };
    System.out.println("Test Case 6: Two elements (peak at end)");
    displayPeakElement(arr6);

    // Test case 7: Two elements (descending)
    int[] arr7 = { 5, 1 };
    System.out.println("Test Case 7: Two elements (peak at start)");
    displayPeakElement(arr7);

    // Test case 8: All equal elements
    int[] arr8 = { 3, 3, 3, 3, 3 };
    System.out.println("Test Case 8: All equal elements");
    displayPeakElement(arr8);

    // Performance analysis
    System.out.println("═".repeat(60));
    System.out.println("\nPerformance Analysis:\n");
    System.out.println("Time Complexity: O(log n) - binary search");
    System.out.println("Space Complexity: O(1) - constant space");
    System.out.println();
    System.out.println("Why Binary Search?");
    System.out.println("- We can compare the middle element with its neighbors");
    System.out.println("- If arr[mid] < arr[mid+1], a peak must exist on the right");
    System.out.println("- This allows us to eliminate half the search space each iteration");
    System.out.println();
    System.out.println("Definition of Peak:");
    System.out.println("- An element is a peak if it's > all its neighbors");
    System.out.println("- Boundary elements (first/last) only need 1 neighbor comparison");
  }
}
