/**
 * Binary Search Problem 4: Find the First and Last Occurrence of an Element in
 * a Sorted Array
 * 
 * Problem:
 * Given a sorted array and a target element, write a program that uses Binary
 * Search
 * to find the first and last occurrence of the target element in the array.
 * If the element is not found, return -1.
 * 
 * Approach:
 * 1. Use binary search to find the first occurrence:
 * - Perform a regular binary search, but if the target is found,
 * continue searching on the left side (right = mid - 1) to find the first
 * occurrence.
 * 2. Use binary search to find the last occurrence:
 * - Similar to finding the first occurrence, but once the target is found,
 * continue searching on the right side (left = mid + 1) to find the last
 * occurrence.
 * 3. Return the indices of the first and last occurrence. If not found, return
 * -1.
 */

public class FindFirstAndLastOccurrence {

  /**
   * Finds the first occurrence of a target in a sorted array
   * 
   * @param arr    the sorted array
   * @param target the value to search for
   * @return the index of first occurrence, or -1 if not found
   */
  public static int findFirstOccurrence(int[] arr, int target) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int left = 0;
    int right = arr.length - 1;
    int result = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] == target) {
        result = mid;
        // Continue searching in the left half
        right = mid - 1;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return result;
  }

  /**
   * Finds the last occurrence of a target in a sorted array
   * 
   * @param arr    the sorted array
   * @param target the value to search for
   * @return the index of last occurrence, or -1 if not found
   */
  public static int findLastOccurrence(int[] arr, int target) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int left = 0;
    int right = arr.length - 1;
    int result = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] == target) {
        result = mid;
        // Continue searching in the right half
        left = mid + 1;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return result;
  }

  /**
   * Finds both first and last occurrence of a target
   * 
   * @param arr    the sorted array
   * @param target the value to search for
   * @return an array [first, last], or [-1, -1] if not found
   */
  public static int[] findFirstAndLast(int[] arr, int target) {
    int first = findFirstOccurrence(arr, target);

    if (first == -1) {
      return new int[] { -1, -1 };
    }

    int last = findLastOccurrence(arr, target);
    return new int[] { first, last };
  }

  /**
   * Counts occurrences of a target in a sorted array
   * 
   * @param arr    the sorted array
   * @param target the value to search for
   * @return the count of occurrences
   */
  public static int countOccurrences(int[] arr, int target) {
    int[] positions = findFirstAndLast(arr, target);

    if (positions[0] == -1) {
      return 0;
    }

    return positions[1] - positions[0] + 1;
  }

  /**
   * Displays search results
   * 
   * @param arr    the sorted array
   * @param target the value to search for
   */
  public static void displayResults(int[] arr, int target) {
    System.out.print("Array: [");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");
    System.out.println("Target: " + target);

    int[] positions = findFirstAndLast(arr, target);

    if (positions[0] == -1) {
      System.out.println("✗ Target not found in array");
    } else {
      System.out.println("✓ First occurrence at index: " + positions[0]);
      System.out.println("✓ Last occurrence at index: " + positions[1]);
      System.out.println("  Count: " + (positions[1] - positions[0] + 1));
    }
    System.out.println();
  }

  /**
   * Main method to test first and last occurrence finding
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║  Binary Search: Find First and Last Occurrence             ║");
    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

    // Test case 1: Multiple occurrences in middle
    int[] arr1 = { 1, 2, 3, 3, 3, 3, 4, 5, 6 };
    System.out.println("Test Case 1: Multiple occurrences in middle");
    displayResults(arr1, 3);

    // Test case 2: Multiple occurrences at beginning
    int[] arr2 = { 1, 1, 1, 2, 3, 4, 5 };
    System.out.println("Test Case 2: Multiple occurrences at beginning");
    displayResults(arr2, 1);

    // Test case 3: Multiple occurrences at end
    int[] arr3 = { 1, 2, 3, 4, 5, 5, 5, 5 };
    System.out.println("Test Case 3: Multiple occurrences at end");
    displayResults(arr3, 5);

    // Test case 4: Single occurrence
    int[] arr4 = { 1, 2, 3, 4, 5 };
    System.out.println("Test Case 4: Single occurrence");
    displayResults(arr4, 3);

    // Test case 5: Element not found
    int[] arr5 = { 1, 2, 3, 4, 5 };
    System.out.println("Test Case 5: Element not found");
    displayResults(arr5, 6);

    // Test case 6: All elements are the same
    int[] arr6 = { 5, 5, 5, 5, 5, 5 };
    System.out.println("Test Case 6: All elements are the same");
    displayResults(arr6, 5);

    // Test case 7: Single element (found)
    int[] arr7 = { 42 };
    System.out.println("Test Case 7: Single element (found)");
    displayResults(arr7, 42);

    // Test case 8: Single element (not found)
    int[] arr8 = { 42 };
    System.out.println("Test Case 8: Single element (not found)");
    displayResults(arr8, 50);

    // Test case 9: Longer array with duplicates
    int[] arr9 = { 1, 2, 2, 2, 3, 3, 4, 5, 5, 5, 5, 6 };
    System.out.println("Test Case 9: Longer array with duplicates");
    displayResults(arr9, 5);
    displayResults(arr9, 2);

    // Performance analysis
    System.out.println("═".repeat(60));
    System.out.println("\nPerformance Analysis:\n");
    System.out.println("Time Complexity: O(log n) for each operation");
    System.out.println("Total: O(log n) to find first + O(log n) to find last");
    System.out.println("       = O(2 log n) = O(log n)");
    System.out.println();
    System.out.println("Space Complexity: O(1) - constant space");
    System.out.println();
    System.out.println("Key Insight:");
    System.out.println("- After finding target, continue search in one direction");
    System.out.println("- For first: continue searching left (right = mid - 1)");
    System.out.println("- For last: continue searching right (left = mid + 1)");
    System.out.println("- This ensures we find the boundary occurrences");
  }
}
