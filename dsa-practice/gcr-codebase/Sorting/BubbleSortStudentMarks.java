/**
 * Bubble Sort - Sort Student Marks
 * Problem Statement:
 * A school maintains student marks in an array. Implement Bubble Sort to sort
 * the student marks in ascending order.
 * 
 * Algorithm:
 * 1. Traverse through the array multiple times
 * 2. Compare adjacent elements and swap if needed
 * 3. Repeat the process until no swaps are required
 * 
 * Time Complexity: O(n²) in all cases
 * Space Complexity: O(1)
 */
public class BubbleSortStudentMarks {

  /**
   * Performs Bubble Sort on student marks array
   * 
   * @param marks array of student marks to be sorted
   */
  public static void bubbleSort(int[] marks) {
    if (marks == null || marks.length == 0) {
      return;
    }

    int n = marks.length;

    // Outer loop for multiple passes
    for (int i = 0; i < n - 1; i++) {
      boolean isSwapped = false;

      // Inner loop for comparing adjacent elements
      for (int j = 0; j < n - i - 1; j++) {
        // Compare adjacent elements
        if (marks[j] > marks[j + 1]) {
          // Swap if current element is greater than next
          int temp = marks[j];
          marks[j] = marks[j + 1];
          marks[j + 1] = temp;
          isSwapped = true;
        }
      }

      // If no swaps occurred, array is already sorted
      if (!isSwapped) {
        break;
      }
    }
  }

  /**
   * Utility method to print the marks
   * 
   * @param marks array of student marks
   */
  public static void printMarks(int[] marks) {
    for (int mark : marks) {
      System.out.print(mark + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random marks
    int[] marks1 = { 85, 72, 91, 68, 88, 76, 95, 82 };
    System.out.println("Original marks: ");
    printMarks(marks1);

    bubbleSort(marks1);
    System.out.println("Sorted marks: ");
    printMarks(marks1);

    // Test case 2: Already sorted marks
    int[] marks2 = { 60, 65, 70, 75, 80, 85, 90 };
    System.out.println("\nAlready sorted marks: ");
    printMarks(marks2);

    bubbleSort(marks2);
    System.out.println("After Bubble Sort: ");
    printMarks(marks2);

    // Test case 3: Reverse sorted marks
    int[] marks3 = { 100, 90, 80, 70, 60, 50 };
    System.out.println("\nReverse sorted marks: ");
    printMarks(marks3);

    bubbleSort(marks3);
    System.out.println("After Bubble Sort: ");
    printMarks(marks3);

    // Test case 4: Duplicate marks
    int[] marks4 = { 75, 80, 75, 90, 80, 85 };
    System.out.println("\nMarks with duplicates: ");
    printMarks(marks4);

    bubbleSort(marks4);
    System.out.println("After Bubble Sort: ");
    printMarks(marks4);
  }
}
