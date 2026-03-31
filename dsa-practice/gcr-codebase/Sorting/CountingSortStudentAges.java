/**
 * Counting Sort - Sort Student Ages
 * Problem Statement:
 * A school collects students' ages (ranging from 10 to 18) and wants them
 * sorted.
 * Implement Counting Sort for this task.
 * 
 * Algorithm:
 * 1. Create a count array to store the frequency of each age
 * 2. Compute cumulative frequencies to determine positions
 * 3. Place elements in their correct positions in the output array
 * 
 * Time Complexity: O(n + k) where k is the range of input
 * Space Complexity: O(k)
 */
public class CountingSortStudentAges {

  /**
   * Performs Counting Sort on student ages array
   * Assumes ages are in the range [10, 18]
   * 
   * @param ages array of student ages to be sorted
   */
  public static void countingSort(int[] ages) {
    if (ages == null || ages.length == 0) {
      return;
    }

    // Find the minimum and maximum ages
    int minAge = ages[0];
    int maxAge = ages[0];

    for (int age : ages) {
      if (age < minAge) {
        minAge = age;
      }
      if (age > maxAge) {
        maxAge = age;
      }
    }

    // Create count array for the range
    int range = maxAge - minAge + 1;
    int[] count = new int[range];

    // Store count of occurrences
    for (int age : ages) {
      count[age - minAge]++;
    }

    // Change count[i] so that count[i] now contains actual position
    // of this element in output array
    for (int i = 1; i < range; i++) {
      count[i] += count[i - 1];
    }

    // Build the output array
    int[] output = new int[ages.length];

    // Traverse the original array from end to maintain stability
    for (int i = ages.length - 1; i >= 0; i--) {
      int index = count[ages[i] - minAge] - 1;
      output[index] = ages[i];
      count[ages[i] - minAge]--;
    }

    // Copy the sorted elements back into original array
    System.arraycopy(output, 0, ages, 0, ages.length);
  }

  /**
   * Alternative simple implementation for fixed range [10, 18]
   * 
   * @param ages array of student ages in range [10, 18]
   */
  public static void countingSortFixed(int[] ages) {
    if (ages == null || ages.length == 0) {
      return;
    }

    int minAge = 10;
    int maxAge = 18;
    int range = maxAge - minAge + 1;

    // Create count array
    int[] count = new int[range];

    // Store frequencies
    for (int age : ages) {
      count[age - minAge]++;
    }

    // Reconstruct the sorted array
    int index = 0;
    for (int i = 0; i < range; i++) {
      int age = i + minAge;
      for (int j = 0; j < count[i]; j++) {
        ages[index++] = age;
      }
    }
  }

  /**
   * Utility method to print student ages
   * 
   * @param ages array of student ages
   */
  public static void printAges(int[] ages) {
    for (int age : ages) {
      System.out.print(age + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Test case 1: Random ages in range [10, 18]
    int[] ages1 = { 14, 12, 18, 10, 15, 13, 17, 11 };
    System.out.println("Original Student Ages: ");
    printAges(ages1);

    countingSort(ages1);
    System.out.println("Sorted Student Ages: ");
    printAges(ages1);

    // Test case 2: Already sorted ages
    int[] ages2 = { 10, 11, 12, 13, 14, 15, 16, 17, 18 };
    System.out.println("\nAlready sorted Student Ages: ");
    printAges(ages2);

    countingSort(ages2);
    System.out.println("After Counting Sort: ");
    printAges(ages2);

    // Test case 3: Reverse sorted ages
    int[] ages3 = { 18, 17, 16, 15, 14, 13, 12, 11, 10 };
    System.out.println("\nReverse sorted Student Ages: ");
    printAges(ages3);

    countingSort(ages3);
    System.out.println("After Counting Sort: ");
    printAges(ages3);

    // Test case 4: Duplicate ages
    int[] ages4 = { 15, 12, 15, 18, 12, 14, 16 };
    System.out.println("\nStudent Ages with duplicates: ");
    printAges(ages4);

    countingSort(ages4);
    System.out.println("After Counting Sort: ");
    printAges(ages4);

    // Test case 5: All same age
    int[] ages5 = { 14, 14, 14, 14 };
    System.out.println("\nAll same Student Age: ");
    printAges(ages5);

    countingSort(ages5);
    System.out.println("After Counting Sort: ");
    printAges(ages5);

    // Test case 6: Small range
    int[] ages6 = { 18, 10, 15, 10, 18 };
    System.out.println("\nStudent Ages - small range: ");
    printAges(ages6);

    countingSortFixed(ages6);
    System.out.println("After Counting Sort (Fixed): ");
    printAges(ages6);
  }
}
