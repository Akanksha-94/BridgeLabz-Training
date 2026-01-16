/**
 * Selection Sort - Sort Exam Scores
 * Problem Statement:
 * A university needs to sort students' exam scores in ascending order.
 * Implement Selection Sort to achieve this.
 * 
 * Algorithm:
 * 1. Find the minimum element in the array
 * 2. Swap it with the first unsorted element
 * 3. Repeat the process for the remaining elements
 * 
 * Time Complexity: O(n²) in all cases
 * Space Complexity: O(1)
 */
public class SelectionSortExamScores {

  /**
   * Performs Selection Sort on exam scores array
   * 
   * @param scores array of exam scores to be sorted
   */
  public static void selectionSort(int[] scores) {
    if (scores == null || scores.length == 0) {
      return;
    }

    int n = scores.length;

    // Traverse through all array elements
    for (int i = 0; i < n - 1; i++) {
      // Find the minimum element in remaining unsorted array
      int minIndex = i;

      for (int j = i + 1; j < n; j++) {
        if (scores[j] < scores[minIndex]) {
          minIndex = j;
        }
      }

      // Swap the found minimum element with the first element
      if (minIndex != i) {
        int temp = scores[i];
        scores[i] = scores[minIndex];
        scores[minIndex] = temp;
      }
    }
  }

  /**
   * Utility method to print exam scores
   * 
   * @param scores array of exam scores
   */
  public static void printScores(int[] scores) {
    for (int score : scores) {
      System.out.print(score + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random exam scores
    int[] scores1 = { 78, 92, 65, 88, 71, 85, 96 };
    System.out.println("Original Exam Scores: ");
    printScores(scores1);

    selectionSort(scores1);
    System.out.println("Sorted Exam Scores: ");
    printScores(scores1);

    // Test case 2: Already sorted scores
    int[] scores2 = { 50, 60, 70, 80, 90, 100 };
    System.out.println("\nAlready sorted Exam Scores: ");
    printScores(scores2);

    selectionSort(scores2);
    System.out.println("After Selection Sort: ");
    printScores(scores2);

    // Test case 3: Reverse sorted scores
    int[] scores3 = { 95, 85, 75, 65, 55, 45 };
    System.out.println("\nReverse sorted Exam Scores: ");
    printScores(scores3);

    selectionSort(scores3);
    System.out.println("After Selection Sort: ");
    printScores(scores3);

    // Test case 4: Duplicate scores
    int[] scores4 = { 80, 70, 80, 90, 70, 85 };
    System.out.println("\nExam Scores with duplicates: ");
    printScores(scores4);

    selectionSort(scores4);
    System.out.println("After Selection Sort: ");
    printScores(scores4);

    // Test case 5: Single element
    int[] scores5 = { 87 };
    System.out.println("\nSingle Exam Score: ");
    printScores(scores5);

    selectionSort(scores5);
    System.out.println("After Selection Sort: ");
    printScores(scores5);

    // Test case 6: Two elements
    int[] scores6 = { 75, 92 };
    System.out.println("\nTwo Exam Scores: ");
    printScores(scores6);

    selectionSort(scores6);
    System.out.println("After Selection Sort: ");
    printScores(scores6);
  }
}
