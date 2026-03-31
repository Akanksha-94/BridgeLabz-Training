import java.util.*;

/**
 * Generate Binary Numbers Using a Queue
 * Generate the first N binary numbers (as strings) using a queue.
 * Example: N=5 → Output: ["1", "10", "11", "100", "101"]
 */
public class BinaryNumbersQueue {
  public static List<String> generateBinaryNumbers(int n) {
    List<String> result = new ArrayList<>();
    Queue<String> queue = new LinkedList<>();

    queue.add("1");

    for (int i = 0; i < n; i++) {
      String binary = queue.remove();
      result.add(binary);

      // Generate next two binary numbers
      queue.add(binary + "0");
      queue.add(binary + "1");
    }

    return result;
  }

  public static void main(String[] args) {
    // Example 1: N = 5
    List<String> binaryNumbers1 = generateBinaryNumbers(5);
    System.out.println("First 5 binary numbers: " + binaryNumbers1);

    // Example 2: N = 8
    List<String> binaryNumbers2 = generateBinaryNumbers(8);
    System.out.println("First 8 binary numbers: " + binaryNumbers2);

    // Example 3: N = 10
    List<String> binaryNumbers3 = generateBinaryNumbers(10);
    System.out.println("First 10 binary numbers: " + binaryNumbers3);

    // Example 4: N = 1
    List<String> binaryNumbers4 = generateBinaryNumbers(1);
    System.out.println("First 1 binary number: " + binaryNumbers4);
  }
}
