/**
 * Binary Search Problem 3: Search for a Target Value in a 2D Sorted Matrix
 * 
 * Problem:
 * You are given a 2D matrix where each row is sorted in ascending order,
 * and the first element of each row is greater than the last element of the
 * previous row.
 * Write a program that performs Binary Search to find a target value in the
 * matrix.
 * If the value is found, return true. Otherwise, return false.
 * 
 * Approach:
 * 1. Treat the matrix as a 1D array (flattened version).
 * 2. Initialize left as 0 and right as rows * columns - 1.
 * 3. Perform binary search:
 * - Find the middle element index mid = (left + right) / 2.
 * - Convert mid to row and column indices using:
 * row = mid / numColumns
 * col = mid % numColumns
 * - Compare the middle element with the target:
 * If it matches, return true.
 * If the target is smaller, search the left half by updating right = mid - 1.
 * If the target is larger, search the right half by updating left = mid + 1.
 * 4. If the element is not found, return false.
 */

public class SearchInSortedMatrix {

  /**
   * Searches for a target value in a sorted 2D matrix
   * 
   * @param matrix the 2D sorted matrix
   * @param target the value to search for
   * @return true if target is found, false otherwise
   */
  public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }

    int rows = matrix.length;
    int cols = matrix[0].length;
    int left = 0;
    int right = rows * cols - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      // Convert 1D index to 2D coordinates
      int row = mid / cols;
      int col = mid % cols;
      int midValue = matrix[row][col];

      if (midValue == target) {
        return true;
      } else if (midValue < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return false;
  }

  /**
   * Searches for a target value and returns its position
   * 
   * @param matrix the 2D sorted matrix
   * @param target the value to search for
   * @return an array [row, col] if found, or null if not found
   */
  public static int[] searchMatrixWithPosition(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return null;
    }

    int rows = matrix.length;
    int cols = matrix[0].length;
    int left = 0;
    int right = rows * cols - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int row = mid / cols;
      int col = mid % cols;
      int midValue = matrix[row][col];

      if (midValue == target) {
        return new int[] { row, col };
      } else if (midValue < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return null;
  }

  /**
   * Displays the matrix and search results
   * 
   * @param matrix the 2D matrix
   * @param target the value to search for
   */
  public static void displaySearchResult(int[][] matrix, int target) {
    System.out.println("Matrix:");
    for (int i = 0; i < matrix.length; i++) {
      System.out.print("  ");
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.printf("%2d ", matrix[i][j]);
      }
      System.out.println();
    }

    int[] position = searchMatrixWithPosition(matrix, target);
    if (position != null) {
      System.out.println("✓ Target " + target + " found at position: [" +
          position[0] + "][" + position[1] + "]");
    } else {
      System.out.println("✗ Target " + target + " not found in matrix");
    }
    System.out.println();
  }

  /**
   * Main method to test matrix search functionality
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║  Binary Search: Search in 2D Sorted Matrix                 ║");
    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

    // Test case 1: Standard matrix
    int[][] matrix1 = {
        { 1, 3, 5, 7 },
        { 10, 11, 16, 20 },
        { 23, 30, 34, 60 }
    };
    System.out.println("Test Case 1: Standard sorted matrix");
    displaySearchResult(matrix1, 3);
    displaySearchResult(matrix1, 13);
    displaySearchResult(matrix1, 60);

    // Test case 2: Single row
    int[][] matrix2 = { { 1, 3, 5, 7, 9, 11 } };
    System.out.println("Test Case 2: Single row matrix");
    displaySearchResult(matrix2, 5);
    displaySearchResult(matrix2, 12);

    // Test case 3: Single column
    int[][] matrix3 = { { 1 }, { 5 }, { 10 }, { 15 }, { 20 } };
    System.out.println("Test Case 3: Single column matrix");
    displaySearchResult(matrix3, 10);
    displaySearchResult(matrix3, 8);

    // Test case 4: Single element
    int[][] matrix4 = { { 42 } };
    System.out.println("Test Case 4: Single element matrix");
    displaySearchResult(matrix4, 42);
    displaySearchResult(matrix4, 50);

    // Test case 5: Larger matrix
    int[][] matrix5 = {
        { 1, 2, 3, 4, 5 },
        { 6, 7, 8, 9, 10 },
        { 11, 12, 13, 14, 15 },
        { 16, 17, 18, 19, 20 }
    };
    System.out.println("Test Case 5: 4x5 sorted matrix");
    displaySearchResult(matrix5, 8);
    displaySearchResult(matrix5, 20);
    displaySearchResult(matrix5, 21);

    // Performance analysis
    System.out.println("═".repeat(60));
    System.out.println("\nPerformance Analysis:\n");
    System.out.println("Time Complexity: O(log(m * n))");
    System.out.println("  where m = number of rows, n = number of columns");
    System.out.println();
    System.out.println("Space Complexity: O(1) - constant space");
    System.out.println();
    System.out.println("Why Binary Search?");
    System.out.println("- The matrix can be treated as a sorted 1D array");
    System.out.println("- All rows are sorted, and rows are ordered by value");
    System.out.println("- Converting 1D index to 2D coordinates:");
    System.out.println("  row = index / numColumns");
    System.out.println("  col = index % numColumns");
  }
}
