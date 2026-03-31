import java.util.*;

/**
 * Invert a Map
 * Invert a Map<K, V> to produce a Map<V, K>.
 * Handle duplicate values by storing them in a list.
 * Example: Input: {A=1, B=2, C=1} → Output: {1=[A, C], 2=[B]}
 */
public class InvertMap {
  public static <K, V> Map<V, List<K>> invertMap(Map<K, V> originalMap) {
    Map<V, List<K>> invertedMap = new HashMap<>();

    for (Map.Entry<K, V> entry : originalMap.entrySet()) {
      K key = entry.getKey();
      V value = entry.getValue();

      // If value doesn't exist as key, create new list
      invertedMap.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
    }

    return invertedMap;
  }

  public static void displayMap(Map<?, ?> map, String title) {
    System.out.println(title + ": " + map);
  }

  public static void main(String[] args) {
    System.out.println("=== Invert a Map ===\n");

    // Example 1: String to Integer
    System.out.println("Example 1: Character to Integer mapping");
    Map<String, Integer> map1 = new LinkedHashMap<>();
    map1.put("A", 1);
    map1.put("B", 2);
    map1.put("C", 1);
    displayMap(map1, "Original Map");

    Map<Integer, List<String>> inverted1 = invertMap(map1);
    displayMap(inverted1, "Inverted Map");

    // Example 2: Student names to grades
    System.out.println("\n\nExample 2: Student to Grade mapping");
    Map<String, String> map2 = new LinkedHashMap<>();
    map2.put("Alice", "A");
    map2.put("Bob", "B");
    map2.put("Carol", "A");
    map2.put("David", "C");
    map2.put("Eve", "B");
    displayMap(map2, "Original Map");

    Map<String, List<String>> inverted2 = invertMap(map2);
    displayMap(inverted2, "Inverted Map");

    // Example 3: Product to Price
    System.out.println("\n\nExample 3: Product to Price mapping");
    Map<String, Double> map3 = new LinkedHashMap<>();
    map3.put("Apple", 1.50);
    map3.put("Banana", 0.75);
    map3.put("Orange", 1.50);
    map3.put("Grape", 2.00);
    map3.put("Mango", 2.00);
    displayMap(map3, "Original Map");

    Map<Double, List<String>> inverted3 = invertMap(map3);
    displayMap(inverted3, "Inverted Map");

    // Example 4: Number to Roman numeral
    System.out.println("\n\nExample 4: Number to Roman Numeral mapping");
    Map<Integer, String> map4 = new LinkedHashMap<>();
    map4.put(1, "I");
    map4.put(2, "II");
    map4.put(3, "III");
    map4.put(4, "IV");
    map4.put(5, "V");
    displayMap(map4, "Original Map");

    Map<String, List<Integer>> inverted4 = invertMap(map4);
    System.out.println("Inverted Map (Roman to Numbers): " + inverted4);

    // Example 5: Empty map
    System.out.println("\n\nExample 5: Empty map");
    Map<String, Integer> emptyMap = new LinkedHashMap<>();
    displayMap(emptyMap, "Original Map");

    Map<Integer, List<String>> invertedEmpty = invertMap(emptyMap);
    displayMap(invertedEmpty, "Inverted Map");

    // Example 6: Single entry
    System.out.println("\n\nExample 6: Single entry map");
    Map<String, Integer> singleMap = new LinkedHashMap<>();
    singleMap.put("X", 100);
    displayMap(singleMap, "Original Map");

    Map<Integer, List<String>> invertedSingle = invertMap(singleMap);
    displayMap(invertedSingle, "Inverted Map");
  }
}
