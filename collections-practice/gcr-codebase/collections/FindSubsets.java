import java.util.*;

/**
 * Find Subsets
 * Check if one set is a subset of another.
 * Example: Set1: {2, 3}, Set2: {1, 2, 3, 4} → Output: true
 */
public class FindSubsets {
  public static boolean isSubset(Set<Integer> set1, Set<Integer> set2) {
    return set2.containsAll(set1);
  }

  public static void main(String[] args) {
    // Example 1
    Set<Integer> set1 = new HashSet<>(Arrays.asList(2, 3));
    Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
    System.out.println("Set1: " + set1);
    System.out.println("Set2: " + set2);
    System.out.println("Is Set1 a subset of Set2? " + isSubset(set1, set2));

    // Example 2 - Not a subset
    Set<Integer> set3 = new HashSet<>(Arrays.asList(2, 5));
    Set<Integer> set4 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
    System.out.println("\nSet3: " + set3);
    System.out.println("Set4: " + set4);
    System.out.println("Is Set3 a subset of Set4? " + isSubset(set3, set4));

    // Example 3 - Empty set is subset of any set
    Set<Integer> emptySet = new HashSet<>();
    Set<Integer> set5 = new HashSet<>(Arrays.asList(1, 2, 3));
    System.out.println("\nEmpty Set: " + emptySet);
    System.out.println("Set5: " + set5);
    System.out.println("Is Empty Set a subset of Set5? " + isSubset(emptySet, set5));

    // Example 4 - Same sets
    Set<Integer> set6 = new HashSet<>(Arrays.asList(1, 2, 3));
    Set<Integer> set7 = new HashSet<>(Arrays.asList(1, 2, 3));
    System.out.println("\nSet6: " + set6);
    System.out.println("Set7: " + set7);
    System.out.println("Is Set6 a subset of Set7? " + isSubset(set6, set7));
  }
}
