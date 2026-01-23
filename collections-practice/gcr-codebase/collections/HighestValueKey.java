import java.util.*;

/**
 * Find the Key with the Highest Value
 * Given a Map<String, Integer>, find the key with the maximum value.
 * Example: Input: {A=10, B=20, C=15} → Output: B
 */
public class HighestValueKey {
  public static String findKeyWithHighestValue(Map<String, Integer> map) {
    if (map.isEmpty()) {
      throw new IllegalArgumentException("Map is empty");
    }

    String maxKey = null;
    Integer maxValue = Integer.MIN_VALUE;

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() > maxValue) {
        maxValue = entry.getValue();
        maxKey = entry.getKey();
      }
    }

    return maxKey;
  }

  // Alternative method using streams
  public static String findKeyWithHighestValueStream(Map<String, Integer> map) {
    if (map.isEmpty()) {
      throw new IllegalArgumentException("Map is empty");
    }

    return map.entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElseThrow();
  }

  public static void main(String[] args) {
    System.out.println("=== Find the Key with the Highest Value ===\n");

    // Example 1: Character scores
    System.out.println("Example 1: Character Scores");
    Map<String, Integer> map1 = new LinkedHashMap<>();
    map1.put("A", 10);
    map1.put("B", 20);
    map1.put("C", 15);
    System.out.println("Input: " + map1);
    System.out.println("Key with highest value: " + findKeyWithHighestValue(map1));

    // Example 2: Student scores
    System.out.println("\n\nExample 2: Student Scores");
    Map<String, Integer> map2 = new LinkedHashMap<>();
    map2.put("Alice", 85);
    map2.put("Bob", 92);
    map2.put("Carol", 78);
    map2.put("David", 88);
    System.out.println("Input: " + map2);
    System.out.println("Key with highest value: " + findKeyWithHighestValue(map2));
    System.out.println("Using Stream: " + findKeyWithHighestValueStream(map2));

    // Example 3: Product sales
    System.out.println("\n\nExample 3: Product Sales");
    Map<String, Integer> map3 = new LinkedHashMap<>();
    map3.put("Laptop", 500);
    map3.put("Phone", 800);
    map3.put("Tablet", 300);
    map3.put("Monitor", 200);
    System.out.println("Input: " + map3);
    System.out.println("Best selling product: " + findKeyWithHighestValue(map3));

    // Example 4: Population of cities
    System.out.println("\n\nExample 4: City Population");
    Map<String, Integer> map4 = new LinkedHashMap<>();
    map4.put("New York", 8336817);
    map4.put("Los Angeles", 3979576);
    map4.put("Chicago", 2693976);
    map4.put("Houston", 2320268);
    System.out.println("Input: " + map4);
    System.out.println("Most populated city: " + findKeyWithHighestValue(map4));

    // Example 5: Single entry
    System.out.println("\n\nExample 5: Single Entry");
    Map<String, Integer> map5 = new LinkedHashMap<>();
    map5.put("OnlyOne", 42);
    System.out.println("Input: " + map5);
    System.out.println("Key with highest value: " + findKeyWithHighestValue(map5));

    // Example 6: Negative values
    System.out.println("\n\nExample 6: Map with Negative Values");
    Map<String, Integer> map6 = new LinkedHashMap<>();
    map6.put("X", -5);
    map6.put("Y", -1);
    map6.put("Z", -10);
    System.out.println("Input: " + map6);
    System.out.println("Key with highest value: " + findKeyWithHighestValue(map6));

    // Example 7: With duplicate values (highest is not unique)
    System.out.println("\n\nExample 7: Duplicate Values (First occurrence is returned)");
    Map<String, Integer> map7 = new LinkedHashMap<>();
    map7.put("Apple", 100);
    map7.put("Banana", 100);
    map7.put("Cherry", 50);
    System.out.println("Input: " + map7);
    System.out.println("Key with highest value: " + findKeyWithHighestValue(map7));

    // Example 8: Empty map (exception handling)
    System.out.println("\n\nExample 8: Empty Map (Exception Handling)");
    Map<String, Integer> emptyMap = new LinkedHashMap<>();
    try {
      System.out.println(findKeyWithHighestValue(emptyMap));
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
