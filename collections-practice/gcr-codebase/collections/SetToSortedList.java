import java.util.*;

/**
 * Convert a Set to a Sorted List
 * Convert a HashSet of integers into a sorted list in ascending order.
 * Example: Input: {5, 3, 9, 1} → Output: [1, 3, 5, 9]
 */
public class SetToSortedList {
  public static List<Integer> convertSetToSortedList(Set<Integer> inputSet) {
    List<Integer> sortedList = new ArrayList<>(inputSet);
    Collections.sort(sortedList);
    return sortedList;
  }

  public static void main(String[] args) {
    // Example 1
    Set<Integer> set1 = new HashSet<>(Arrays.asList(5, 3, 9, 1));
    List<Integer> sortedList1 = convertSetToSortedList(set1);
    System.out.println("Input Set: " + set1);
    System.out.println("Sorted List: " + sortedList1);

    // Example 2
    Set<Integer> set2 = new HashSet<>(Arrays.asList(100, 50, 75, 25, 80));
    List<Integer> sortedList2 = convertSetToSortedList(set2);
    System.out.println("\nInput Set: " + set2);
    System.out.println("Sorted List: " + sortedList2);

    // Example 3
    Set<Integer> set3 = new HashSet<>(Arrays.asList(7, 2, 9, 4, 1, 6));
    List<Integer> sortedList3 = convertSetToSortedList(set3);
    System.out.println("\nInput Set: " + set3);
    System.out.println("Sorted List: " + sortedList3);
  }
}
