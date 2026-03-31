/**
 * Insertion Sort - Sort Employee IDs
 * Problem Statement:
 * A company stores employee IDs in an unsorted array. Implement Insertion Sort
 * to sort the employee IDs in ascending order.
 * 
 * Algorithm:
 * 1. Divide the array into sorted and unsorted parts
 * 2. Pick an element from the unsorted part and insert it into its correct
 * position in the sorted part
 * 3. Repeat for all elements
 * 
 * Time Complexity: O(n²) worst case, O(n) best case
 * Space Complexity: O(1)
 */
public class InsertionSortEmployeeIDs {

  /**
   * Performs Insertion Sort on employee IDs array
   * 
   * @param employeeIds array of employee IDs to be sorted
   */
  public static void insertionSort(int[] employeeIds) {
    if (employeeIds == null || employeeIds.length == 0) {
      return;
    }

    int n = employeeIds.length;

    // Start from the second element (index 1)
    for (int i = 1; i < n; i++) {
      int currentId = employeeIds[i];
      int j = i - 1;

      // Move elements greater than currentId one position to the right
      while (j >= 0 && employeeIds[j] > currentId) {
        employeeIds[j + 1] = employeeIds[j];
        j--;
      }

      // Insert the current element at its correct position
      employeeIds[j + 1] = currentId;
    }
  }

  /**
   * Utility method to print employee IDs
   * 
   * @param employeeIds array of employee IDs
   */
  public static void printIds(int[] employeeIds) {
    for (int id : employeeIds) {
      System.out.print(id + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random employee IDs
    int[] ids1 = { 1047, 1023, 1089, 1001, 1056, 1034, 1078 };
    System.out.println("Original Employee IDs: ");
    printIds(ids1);

    insertionSort(ids1);
    System.out.println("Sorted Employee IDs: ");
    printIds(ids1);

    // Test case 2: Already sorted IDs
    int[] ids2 = { 1001, 1002, 1003, 1004, 1005 };
    System.out.println("\nAlready sorted Employee IDs: ");
    printIds(ids2);

    insertionSort(ids2);
    System.out.println("After Insertion Sort: ");
    printIds(ids2);

    // Test case 3: Reverse sorted IDs
    int[] ids3 = { 1100, 1090, 1080, 1070, 1060 };
    System.out.println("\nReverse sorted Employee IDs: ");
    printIds(ids3);

    insertionSort(ids3);
    System.out.println("After Insertion Sort: ");
    printIds(ids3);

    // Test case 4: Duplicate IDs
    int[] ids4 = { 1050, 1040, 1050, 1020, 1040, 1030 };
    System.out.println("\nEmployee IDs with duplicates: ");
    printIds(ids4);

    insertionSort(ids4);
    System.out.println("After Insertion Sort: ");
    printIds(ids4);

    // Test case 5: Single element
    int[] ids5 = { 1025 };
    System.out.println("\nSingle Employee ID: ");
    printIds(ids5);

    insertionSort(ids5);
    System.out.println("After Insertion Sort: ");
    printIds(ids5);
  }
}
