/**
 * Heap Sort - Sort Job Applicants by Salary
 * Problem Statement:
 * A company receives job applications with different expected salary demands.
 * Implement Heap Sort to sort these salary demands in ascending order.
 * 
 * Algorithm:
 * 1. Build a Max Heap from the array
 * 2. Extract the largest element (root) and place it at the end
 * 3. Reheapify the remaining elements and repeat until sorted
 * 
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(1)
 */
public class HeapSortSalaryDemands {

  /**
   * Performs Heap Sort on salary demands array
   * 
   * @param salaries array of salary demands to be sorted
   */
  public static void heapSort(int[] salaries) {
    if (salaries == null || salaries.length == 0) {
      return;
    }

    int n = salaries.length;

    // Build max heap
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(salaries, n, i);
    }

    // Extract elements one by one from heap
    for (int i = n - 1; i > 0; i--) {
      // Move current root (maximum value) to end
      int temp = salaries[0];
      salaries[0] = salaries[i];
      salaries[i] = temp;

      // Heapify the reduced heap
      heapify(salaries, i, 0);
    }
  }

  /**
   * Maintains the max heap property
   * 
   * @param salaries array of salary demands
   * @param n        size of heap
   * @param i        index of node to heapify
   */
  private static void heapify(int[] salaries, int n, int i) {
    int largest = i; // Initialize largest as root
    int left = 2 * i + 1; // Left child
    int right = 2 * i + 2; // Right child

    // If left child is larger than root
    if (left < n && salaries[left] > salaries[largest]) {
      largest = left;
    }

    // If right child is larger than largest so far
    if (right < n && salaries[right] > salaries[largest]) {
      largest = right;
    }

    // If largest is not root, swap and recursively heapify
    if (largest != i) {
      int temp = salaries[i];
      salaries[i] = salaries[largest];
      salaries[largest] = temp;

      heapify(salaries, n, largest);
    }
  }

  /**
   * Utility method to print salary demands
   * 
   * @param salaries array of salary demands
   */
  public static void printSalaries(int[] salaries) {
    for (int salary : salaries) {
      System.out.print(salary + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random salary demands
    int[] salaries1 = { 850000, 650000, 950000, 550000, 750000, 600000, 1000000 };
    System.out.println("Original Salary Demands: ");
    printSalaries(salaries1);

    heapSort(salaries1);
    System.out.println("Sorted Salary Demands: ");
    printSalaries(salaries1);

    // Test case 2: Already sorted salaries
    int[] salaries2 = { 300000, 400000, 500000, 600000, 700000 };
    System.out.println("\nAlready sorted Salary Demands: ");
    printSalaries(salaries2);

    heapSort(salaries2);
    System.out.println("After Heap Sort: ");
    printSalaries(salaries2);

    // Test case 3: Reverse sorted salaries
    int[] salaries3 = { 1000000, 800000, 600000, 400000, 200000 };
    System.out.println("\nReverse sorted Salary Demands: ");
    printSalaries(salaries3);

    heapSort(salaries3);
    System.out.println("After Heap Sort: ");
    printSalaries(salaries3);

    // Test case 4: Duplicate salaries
    int[] salaries4 = { 700000, 500000, 700000, 900000, 500000, 600000 };
    System.out.println("\nSalary Demands with duplicates: ");
    printSalaries(salaries4);

    heapSort(salaries4);
    System.out.println("After Heap Sort: ");
    printSalaries(salaries4);

    // Test case 5: Large differences
    int[] salaries5 = { 5000000, 50000, 500000, 5000, 500 };
    System.out.println("\nSalary Demands with large differences: ");
    printSalaries(salaries5);

    heapSort(salaries5);
    System.out.println("After Heap Sort: ");
    printSalaries(salaries5);
  }
}
