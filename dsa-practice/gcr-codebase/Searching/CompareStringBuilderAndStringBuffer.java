/**
 * StringBuffer Problem 2: Compare StringBuffer with StringBuilder for String
 * Concatenation
 * 
 * Problem:
 * Write a program that compares the performance of StringBuffer and
 * StringBuilder
 * for concatenating strings. For large datasets (e.g., concatenating 1 million
 * strings),
 * compare the execution time of both classes.
 * 
 * Approach:
 * 1. Initialize two StringBuffer and StringBuilder objects.
 * 2. Perform string concatenation in both objects, appending 1 million strings
 * (e.g., "hello").
 * 3. Measure the time taken to complete the concatenation using
 * System.nanoTime()
 * for both StringBuffer and StringBuilder.
 * 4. Output the time taken by both classes for comparison.
 */

public class CompareStringBuilderAndStringBuffer {

  private static final int ITERATIONS = 1_000_000;
  private static final String TEST_STRING = "hello";

  /**
   * Performs concatenation using StringBuilder
   * 
   * @return time taken in milliseconds
   */
  public static long testStringBuilder() {
    long startTime = System.nanoTime();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ITERATIONS; i++) {
      sb.append(TEST_STRING);
    }
    String result = sb.toString();

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1_000_000; // Convert to milliseconds
  }

  /**
   * Performs concatenation using StringBuffer
   * 
   * @return time taken in milliseconds
   */
  public static long testStringBuffer() {
    long startTime = System.nanoTime();

    StringBuffer sbf = new StringBuffer();
    for (int i = 0; i < ITERATIONS; i++) {
      sbf.append(TEST_STRING);
    }
    String result = sbf.toString();

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1_000_000; // Convert to milliseconds
  }

  /**
   * Performs string concatenation using String + operator (for comparison)
   * Note: This is for small iterations to avoid excessive time
   * 
   * @param iterations number of iterations
   * @return time taken in milliseconds
   */
  public static long testStringConcatenation(int iterations) {
    long startTime = System.nanoTime();

    String result = "";
    for (int i = 0; i < iterations; i++) {
      result += TEST_STRING;
    }

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1_000_000; // Convert to milliseconds
  }

  /**
   * Main method to compare performance
   */
  public static void main(String[] args) {
    System.out.println("=== String Concatenation Performance Comparison ===\n");

    System.out.println("Configuration:");
    System.out.println("Number of iterations: " + ITERATIONS);
    System.out.println("String to append: \"" + TEST_STRING + "\"");
    System.out.println();

    // Test StringBuilder
    System.out.println("Testing StringBuilder...");
    long sbTime = testStringBuilder();
    System.out.println("StringBuilder Time: " + sbTime + " ms");
    System.out.println();

    // Test StringBuffer
    System.out.println("Testing StringBuffer...");
    long sbfTime = testStringBuffer();
    System.out.println("StringBuffer Time: " + sbfTime + " ms");
    System.out.println();

    // Comparison
    System.out.println("=== Comparison ===");
    System.out.println("StringBuilder: " + sbTime + " ms");
    System.out.println("StringBuffer:  " + sbfTime + " ms");
    System.out.println();

    if (sbTime < sbfTime) {
      double percentage = ((sbfTime - sbTime) / (double) sbfTime) * 100;
      System.out.println("StringBuilder is " + String.format("%.2f", percentage) +
          "% faster than StringBuffer");
    } else if (sbfTime < sbTime) {
      double percentage = ((sbTime - sbfTime) / (double) sbTime) * 100;
      System.out.println("StringBuffer is " + String.format("%.2f", percentage) +
          "% faster than StringBuilder");
    } else {
      System.out.println("Both have similar performance");
    }

    System.out.println();
    System.out.println("=== Why StringBuilder is faster ===");
    System.out.println("StringBuffer is synchronized (thread-safe), which adds");
    System.out.println("synchronization overhead, making it slower for single-threaded");
    System.out.println("applications. StringBuilder is unsynchronized and therefore faster");
    System.out.println("for single-threaded scenarios.");

    // Additional test: Simple string concatenation (only for 10000 iterations)
    System.out.println();
    System.out.println("=== String Concatenation using '+' operator (10,000 iterations) ===");
    long stringTime = testStringConcatenation(10_000);
    System.out.println("String concatenation using '+': " + stringTime + " ms");
    System.out.println("Note: String concatenation is extremely slow for large iterations");
    System.out.println("because each concatenation creates a new String object.");
  }
}
