import java.util.*;

/**
 * Merge Two Maps
 * Merge two maps such that if a key exists in both, sum their values.
 * Example: Map1: {A=1, B=2}, Map2: {B=3, C=4} → Output: {A=1, B=5, C=4}
 */
public class MergeMaps {
  public static Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
    Map<String, Integer> result = new HashMap<>(map1);

    for (Map.Entry<String, Integer> entry : map2.entrySet()) {
      result.put(entry.getKey(),
          result.getOrDefault(entry.getKey(), 0) + entry.getValue());
    }

    return result;
  }

  // Alternative method using Java 8 features
  public static Map<String, Integer> mergeMapsStream(Map<String, Integer> map1, Map<String, Integer> map2) {
    Map<String, Integer> result = new HashMap<>(map1);
    map2.forEach((key, value) -> result.merge(key, value, Integer::sum));
    return result;
  }

  public static void displayMaps(Map<String, Integer> map1, Map<String, Integer> map2,
      Map<String, Integer> merged) {
    System.out.println("Map 1: " + map1);
    System.out.println("Map 2: " + map2);
    System.out.println("Merged: " + merged);
  }

  public static void main(String[] args) {
    System.out.println("=== Merge Two Maps ===\n");

    // Example 1: Basic merge
    System.out.println("Example 1: Basic Merge");
    Map<String, Integer> map1A = new LinkedHashMap<>();
    map1A.put("A", 1);
    map1A.put("B", 2);

    Map<String, Integer> map2A = new LinkedHashMap<>();
    map2A.put("B", 3);
    map2A.put("C", 4);

    Map<String, Integer> merged1 = mergeMaps(map1A, map2A);
    displayMaps(map1A, map2A, merged1);

    // Example 2: Fruit counts
    System.out.println("\n\nExample 2: Fruit Inventory Merge");
    Map<String, Integer> inventory1 = new LinkedHashMap<>();
    inventory1.put("Apple", 10);
    inventory1.put("Banana", 5);
    inventory1.put("Orange", 8);

    Map<String, Integer> inventory2 = new LinkedHashMap<>();
    inventory2.put("Banana", 3);
    inventory2.put("Orange", 2);
    inventory2.put("Grape", 6);

    Map<String, Integer> merged2 = mergeMaps(inventory1, inventory2);
    displayMaps(inventory1, inventory2, merged2);

    // Example 3: Student scores from different exams
    System.out.println("\n\nExample 3: Combined Student Scores");
    Map<String, Integer> exam1 = new LinkedHashMap<>();
    exam1.put("Alice", 85);
    exam1.put("Bob", 90);
    exam1.put("Carol", 78);

    Map<String, Integer> exam2 = new LinkedHashMap<>();
    exam2.put("Alice", 88);
    exam2.put("Carol", 82);
    exam2.put("David", 91);

    Map<String, Integer> merged3 = mergeMaps(exam1, exam2);
    displayMaps(exam1, exam2, merged3);
    System.out.println("(Scores are summed)");

    // Example 4: Using Stream version
    System.out.println("\n\nExample 4: Using Stream Merge Method");
    Map<String, Integer> sales1 = new LinkedHashMap<>();
    sales1.put("Product1", 100);
    sales1.put("Product2", 150);
    sales1.put("Product3", 75);

    Map<String, Integer> sales2 = new LinkedHashMap<>();
    sales2.put("Product1", 50);
    sales2.put("Product4", 200);

    Map<String, Integer> merged4 = mergeMapsStream(sales1, sales2);
    displayMaps(sales1, sales2, merged4);

    // Example 5: Empty maps
    System.out.println("\n\nExample 5: Merge with Empty Maps");
    Map<String, Integer> emptyMap = new LinkedHashMap<>();
    Map<String, Integer> map5 = new LinkedHashMap<>();
    map5.put("X", 10);
    map5.put("Y", 20);

    Map<String, Integer> merged5 = mergeMaps(emptyMap, map5);
    System.out.println("Empty Map: " + emptyMap);
    System.out.println("Map with data: " + map5);
    System.out.println("Merged: " + merged5);

    // Example 6: Both empty maps
    System.out.println("\n\nExample 6: Both Empty Maps");
    Map<String, Integer> emptyMap1 = new LinkedHashMap<>();
    Map<String, Integer> emptyMap2 = new LinkedHashMap<>();

    Map<String, Integer> merged6 = mergeMaps(emptyMap1, emptyMap2);
    System.out.println("Map 1: " + emptyMap1);
    System.out.println("Map 2: " + emptyMap2);
    System.out.println("Merged: " + merged6);

    // Example 7: Employee salary merge
    System.out.println("\n\nExample 7: Employee Salary Consolidation");
    Map<String, Integer> dept1Salary = new LinkedHashMap<>();
    dept1Salary.put("John", 50000);
    dept1Salary.put("Jane", 60000);
    dept1Salary.put("Jack", 55000);

    Map<String, Integer> dept2Salary = new LinkedHashMap<>();
    dept2Salary.put("Alice", 65000);
    dept2Salary.put("Bob", 58000);
    dept2Salary.put("John", 5000); // Bonus for John

    Map<String, Integer> merged7 = mergeMaps(dept1Salary, dept2Salary);
    displayMaps(dept1Salary, dept2Salary, merged7);
    System.out.println("(Note: John's salary is updated with bonus)");
  }
}
