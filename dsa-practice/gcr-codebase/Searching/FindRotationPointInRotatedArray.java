/**
 * Binary Search Problem 1: Find the Rotation Point in a Rotated Sorted Array
 * 
 * Problem:
 * You are given a rotated sorted array. Write a program that performs Binary
 * Search
 * to find the index of the smallest element in the array (the rotation point).
 * 
 * Approach:
 * 1. Initialize left as 0 and right as n - 1.
 * 2. Perform a binary search:
 * - Find the middle element mid = (left + right) / 2.
 * - If arr[mid] > arr[right], then the smallest element is in the right half,
 * so update left = mid + 1.
 * - If arr[mid] < arr[right], the smallest element is in the left half,
 * so update right = mid.
 * 3. Continue until left equals right, and then return arr[left] (the rotation
 * point).
 */

public class FindRotationPointInRotatedArray {

  /**
   * Finds the index of the smallest element in a rotated sorted array
   * 
   * @param arr the rotated sorted array
   * @return the index of the smallest element (rotation point)
   */
  public static int findRotationPoint(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int left = 0;
    int right = arr.length - 1;

    // If array is not rotated (already sorted)
    if (arr[left] < arr[right]) {
      return left;
    }

    while (left < right) {
      int mid = left + (right - left) / 2;

      // If middle element is greater than right element,
      // the smallest element is in the right half
      if (arr[mid] > arr[right]) {
        left = mid + 1;
      } else {
        // The smallest element is in the left half (including mid)
        right = mid;
      }
    }

    return left;
  }

  /**
   * Displays the rotation point and related information
   * 
   * @param arr the rotated sorted array
   */
  public static void displayRotationPoint(int[] arr) {
    System.out.print("Array: [");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");

    int rotationIndex = findRotationPoint(arr);
    System.out.println("Rotation point index: " + rotationIndex);
    System.out.println("Smallest element: " + arr[rotationIndex]);
    System.out.println();
  }

  /**
   * Main method to test the rotation point finding
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║  Binary Search: Find Rotation Point in Rotated Array       ║");
    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

    // Test case 1: Simple rotated array
    int[] arr1 = { 4, 5, 6, 7, 0, 1, 2 };
    System.out.println("Test Case 1: Simple rotation");
    displayRotationPoint(arr1);

    // Test case 2: Another rotated array
    int[] arr2 = { 3, 4, 5, 1, 2 };
    System.out.println("Test Case 2: Different rotation");
    displayRotationPoint(arr2);

    // Test case 3: Rotated at beginning (large rotation)
    int[] arr3 = { 1, 2, 3, 4, 5 };
    System.out.println("Test Case 3: Not rotated (smallest at beginning)");
    displayRotationPoint(arr3);

    // Test case 4: Large rotation
    int[] arr4 = { 7, 8, 9, 10, 1, 2, 3, 4, 5, 6 };
    System.out.println("Test Case 4: Large rotation");
    displayRotationPoint(arr4);

    // Test case 5: Single element
    int[] arr5 = { 5 };
    System.out.println("Test Case 5: Single element");
    displayRotationPoint(arr5);

    // Test case 6: Two elements
    int[] arr6 = { 2, 1 };
    System.out.println("Test Case 6: Two elements (rotated)");
    displayRotationPoint(arr6);

    // Test case 7: Rotation at end
    int[] arr7 = { 2, 3, 4, 5, 6, 7, 8, 1 };
    System.out.println("Test Case 7: Rotation at end");
    displayRotationPoint(arr7);

    // Performance analysis
    System.out.println("═".repeat(60));
    System.out.println("\nPerformance Analysis:\n");
    System.out.println("Time Complexity: O(log n) - binary search");
    System.out.println("Space Complexity: O(1) - constant space");
    System.out.println();
    System.out.println("Why Binary Search?");
    System.out.println("- The array is partially sorted (two sorted subarrays)");
    System.out.println("- We can eliminate half of the search space in each iteration");
    System.out.println("- This reduces time complexity from O(n) to O(log n)");
  }
}
