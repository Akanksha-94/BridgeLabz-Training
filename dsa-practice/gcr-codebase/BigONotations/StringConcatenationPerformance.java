/**
 * Problem Statement: String Concatenation Performance
 * 
 * Objective:
 * Compare the performance of String (O(N²)), StringBuilder (O(N)),
 * and StringBuffer (O(N)) when concatenating strings.
 * 
 * Approach:
 * - String (Immutable): Creates new object each time, wasteful for
 * concatenation.
 * - StringBuilder (Mutable, Thread-unsafe): Fast, single-threaded operations.
 * - StringBuffer (Mutable, Thread-safe): Slightly slower, multi-threaded
 * operations.
 * 
 * Time Complexity:
 * - String concatenation: O(N²) - each concatenation creates new string
 * - StringBuilder: O(N) - appends to mutable buffer
 * - StringBuffer: O(N) - synchronized StringBuilder
 * 
 * Space Complexity:
 * - String: O(N²) due to creating intermediate strings
 * - StringBuilder: O(N)
 * - StringBuffer: O(N)
 */
public class StringConcatenationPerformance {

  /**
   * String Concatenation using + operator - O(N²)
   * Inefficient due to immutability of String objects
   */
  public static String concatenateUsingString(String[] words) {
    String result = "";
    for (String word : words) {
      result += word; // Creates new String object each iteration
    }
    return result;
  }

  /**
   * String Concatenation using StringBuilder - O(N)
   * Efficient, mutable, thread-unsafe
   */
  public static String concatenateUsingStringBuilder(String[] words) {
    StringBuilder sb = new StringBuilder();
    for (String word : words) {
      sb.append(word);
    }
    return sb.toString();
  }

  /**
   * String Concatenation using StringBuffer - O(N)
   * Thread-safe version of StringBuilder, slightly slower
   */
  public static String concatenateUsingStringBuffer(String[] words) {
    StringBuffer sb = new StringBuffer();
    for (String word : words) {
      sb.append(word);
    }
    return sb.toString();
  }

  /**
   * Generate word array for concatenation
   */
  public static String[] generateWords(int count, int wordLength) {
    String[] words = new String[count];
    String baseWord = "word";
    for (int i = 0; i < count; i++) {
      words[i] = baseWord + i;
    }
    return words;
  }

  /**
   * Measure concatenation performance
   */
  public static void measurePerformance(int wordCount, int wordLength) {
    String[] words = generateWords(wordCount, wordLength);

    System.out.println("\n--- Concatenating " + wordCount + " words ---");

    // String concatenation (skip for very large counts)
    long startString = 0;
    long endString = 0;
    if (wordCount <= 5000) {
      startString = System.nanoTime();
      String result1 = concatenateUsingString(words);
      endString = System.nanoTime();
      double stringTime = (endString - startString) / 1_000_000.0;
      System.out.printf("String (O(N²)): %.3fms (Length: %d)%n", stringTime, result1.length());
    } else {
      System.out.println("String (O(N²)): [SKIPPED - Too slow]");
    }

    // StringBuilder concatenation
    long startSB = System.nanoTime();
    String result2 = concatenateUsingStringBuilder(words);
    long endSB = System.nanoTime();
    double sbTime = (endSB - startSB) / 1_000_000.0;
    System.out.printf("StringBuilder (O(N)): %.3fms (Length: %d)%n", sbTime, result2.length());

    // StringBuffer concatenation
    long startSBuf = System.nanoTime();
    String result3 = concatenateUsingStringBuffer(words);
    long endSBuf = System.nanoTime();
    double sbufTime = (endSBuf - startSBuf) / 1_000_000.0;
    System.out.printf("StringBuffer (O(N)): %.3fms (Length: %d)%n", sbufTime, result3.length());

    if (wordCount <= 5000) {
      System.out.printf("Improvement (String vs StringBuilder): %.2fx%n",
          ((endString - startString) / (double) (endSB - startSB)));
    }
  }

  /**
   * Demonstrate why String concatenation is inefficient
   */
  public static void demonstrateStringSlow() {
    System.out.println("\n===== Why String Concatenation is Slow =====");
    System.out.println("String example with just 5 concatenations:");
    System.out.println("String s = \"A\" + \"B\" + \"C\" + \"D\" + \"E\";");
    System.out.println("\nInternally, this creates:");
    System.out.println("- \"A\" (String 1)");
    System.out.println("- \"A\" + \"B\" = \"AB\" (String 2 - new object created)");
    System.out.println("- \"AB\" + \"C\" = \"ABC\" (String 3 - new object created)");
    System.out.println("- \"ABC\" + \"D\" = \"ABCD\" (String 4 - new object created)");
    System.out.println("- \"ABCD\" + \"E\" = \"ABCDE\" (String 5 - new object created)");
    System.out.println("\nFor N concatenations: O(N²) operations and O(N²) memory!");
  }

  /**
   * Demonstrate memory efficiency comparison
   */
  public static void demonstrateMemoryUsage() {
    System.out.println("\n===== Memory Usage Comparison =====");
    int count = 1000;
    String[] words = generateWords(count, 5);

    // Get initial memory
    long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    StringBuilder sb = new StringBuilder();
    for (String word : words) {
      sb.append(word);
    }

    long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long memUsed = (memAfter - memBefore) / 1024; // Convert to KB

    System.out.printf("Memory used by StringBuilder for %d words: %d KB%n", count, memUsed);
    System.out.println("(String concatenation would use approximately 1000x more memory!)");
  }

  public static void main(String[] args) {
    System.out.println("===== String Concatenation Performance =====");
    System.out.println("String: O(N²) | StringBuilder: O(N) | StringBuffer: O(N)");

    demonstrateStringSlow();

    measurePerformance(100, 5);
    measurePerformance(1_000, 5);
    measurePerformance(10_000, 5);

    demonstrateMemoryUsage();

    System.out.println("\n\nConclusion:");
    System.out.println("- String concatenation using + is O(N²) - NEVER use for loops!");
    System.out.println("- StringBuilder is O(N) and fastest - use for single-threaded code");
    System.out.println("- StringBuffer is O(N) with synchronization - use for multi-threaded code");
    System.out.println("- Performance difference becomes dramatic with large datasets");
    System.out.println("- Always use StringBuilder/StringBuffer for repeated concatenations");
  }
}
