/**
 * Linear Search Problem 1: Search for the First Negative Number
 * 
 * Problem:
 * You are given an integer array. Write a program that performs Linear Search
 * to find the first negative number in the array. If a negative number is
 * found,
 * return its index. If no negative number is found, return -1.
 * 
 * Approach:
 * 1. Iterate through the array from the start.
 * 2. Check if the current element is negative.
 * 3. If a negative number is found, return its index.
 * 4. If the loop completes without finding a negative number, return -1.
 */

public class SearchFirstNegativeNumber {

  /**
   * Performs linear search to find the first negative number
   * 
   * @param arr the array to search
   * @return the index of the first negative number, or -1 if not found
   */
  public static int findFirstNegative(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < 0) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Performs linear search and displays the result
   * 
   * @param arr         the array to search
   * @param searchLabel label for the search
   */
  public static void searchAndDisplay(int[] arr, String searchLabel) {
    System.out.println(searchLabel);
    System.out.print("Array: [");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");

    int index = findFirstNegative(arr);

    if (index != -1) {
      System.out.println("✓ First negative number found at index: " + index);
      System.out.println("  Value: " + arr[index]);
    } else {
      System.out.println("✗ No negative number found in the array");
    }
    System.out.println();
  }

  /**
   * Searches and returns detailed information about the first negative number
   * 
   * @param arr the array to search
   * @return a string with detailed information
   */
  public static String findWithDetails(int[] arr) {
    if (arr == null || arr.length == 0) {
      return "Array is empty";
    }

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < 0) {
        return "Index: " + i + ", Value: " + arr[i] +
            ", Distance from start: " + (i + 1) + " positions";
      }
    }

    return "No negative number found";
  }

  /**
   * Main method to test the search functionality
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║  Linear Search: Find First Negative Number                  ║");
    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

    // Test case 1: Array with negative number at beginning
    int[] arr1 = { -5, 2, 3, 4, 5 };
    searchAndDisplay(arr1, "Test Case 1: Negative at beginning");

    // Test case 2: Array with negative number in middle
    int[] arr2 = { 1, 2, 3, -7, 5, 6 };
    searchAndDisplay(arr2, "Test Case 2: Negative in middle");

    // Test case 3: Array with negative number at end
    int[] arr3 = { 10, 20, 30, 40, -15 };
    searchAndDisplay(arr3, "Test Case 3: Negative at end");

    // Test case 4: Array with no negative numbers
    int[] arr4 = { 1, 2, 3, 4, 5 };
    searchAndDisplay(arr4, "Test Case 4: No negative numbers");

    // Test case 5: Array with multiple negative numbers
    int[] arr5 = { 5, 4, 3, -2, -1, 0, 1 };
    searchAndDisplay(arr5, "Test Case 5: Multiple negatives (find first)");

    // Test case 6: Array with single element (negative)
    int[] arr6 = { -42 };
    searchAndDisplay(arr6, "Test Case 6: Single negative element");

    // Test case 7: Array with single element (positive)
    int[] arr7 = { 42 };
    searchAndDisplay(arr7, "Test Case 7: Single positive element");

    // Test case 8: Detailed information
    System.out.println("═".repeat(60));
    System.out.println("\nDetailed Analysis:\n");

    int[] arr8 = { 100, 50, 25, -10, -5, 0, 5 };
    System.out.print("Array: [");
    for (int i = 0; i < arr8.length; i++) {
      System.out.print(arr8[i]);
      if (i < arr8.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");
    System.out.println("Details: " + findWithDetails(arr8));

    // Performance analysis
    System.out.println("\n" + "═".repeat(60));
    System.out.println("\nPerformance Analysis:\n");
    System.out.println("Time Complexity: O(n) - where n is the array length");
    System.out.println("Space Complexity: O(1) - constant space");
    System.out.println();
    System.out.println("Best Case: O(1) - negative number at first position");
    System.out.println("Worst Case: O(n) - no negative number or at last position");
    System.out.println("Average Case: O(n/2) ≈ O(n) - negative could be anywhere");
  }
}
