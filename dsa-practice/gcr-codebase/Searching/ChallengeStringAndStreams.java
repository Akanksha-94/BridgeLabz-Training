
/**
 * Challenge Problem: Compare StringBuilder, StringBuffer, FileReader, and InputStreamReader
 * 
 * Problem:
 * Write a program that:
 * 1. Uses StringBuilder and StringBuffer to concatenate a list of strings 1,000,000 times.
 * 2. Uses FileReader and InputStreamReader to read a large file and print the number of words.
 * 
 * Approach:
 * StringBuilder and StringBuffer:
 * - Create a list of strings (e.g., "hello").
 * - Concatenate the strings 1,000,000 times using both StringBuilder and StringBuffer.
 * - Measure and compare the time taken for each.
 * 
 * FileReader and InputStreamReader:
 * - Read a large text file using FileReader and InputStreamReader.
 * - Count the number of words by splitting the text on whitespace characters.
 * - Print the word count and compare the time taken for reading the file.
 */

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ChallengeStringAndStreams {

  private static final int STRING_ITERATIONS = 1_000_000;
  private static final String TEST_STRING = "hello";

  // ========================= StringBuilder Test =========================
  /**
   * Tests StringBuilder performance for string concatenation
   * 
   * @return time taken in milliseconds
   */
  public static long testStringBuilder() {
    long startTime = System.nanoTime();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < STRING_ITERATIONS; i++) {
      sb.append(TEST_STRING);
    }
    String result = sb.toString();

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1_000_000;
  }

  // ========================= StringBuffer Test =========================
  /**
   * Tests StringBuffer performance for string concatenation
   * 
   * @return time taken in milliseconds
   */
  public static long testStringBuffer() {
    long startTime = System.nanoTime();

    StringBuffer sbf = new StringBuffer();
    for (int i = 0; i < STRING_ITERATIONS; i++) {
      sbf.append(TEST_STRING);
    }
    String result = sbf.toString();

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1_000_000;
  }

  // ========================= FileReader Test =========================
  /**
   * Counts words in a file using FileReader
   * 
   * @param filePath the path to the file
   * @return an array with [wordCount, timeInMs]
   */
  public static long[] countWordsUsingFileReader(String filePath) {
    long startTime = System.nanoTime();
    int wordCount = 0;

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line;

      while ((line = reader.readLine()) != null) {
        String[] words = line.trim().split("\\s+");
        for (String word : words) {
          if (!word.isEmpty()) {
            wordCount++;
          }
        }
      }

    } catch (IOException e) {
      System.out.println("Error reading file with FileReader: " + e.getMessage());
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        System.out.println("Error closing FileReader: " + e.getMessage());
      }
    }

    long endTime = System.nanoTime();
    long timeInMs = (endTime - startTime) / 1_000_000;

    return new long[] { wordCount, timeInMs };
  }

  // ========================= InputStreamReader Test =========================
  /**
   * Counts words in a file using InputStreamReader
   * 
   * @param filePath the path to the file
   * @return an array with [wordCount, timeInMs]
   */
  public static long[] countWordsUsingInputStreamReader(String filePath) {
    long startTime = System.nanoTime();
    int wordCount = 0;

    BufferedReader reader = null;
    try {
      FileInputStream fis = new FileInputStream(filePath);
      InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
      reader = new BufferedReader(isr);
      String line;

      while ((line = reader.readLine()) != null) {
        String[] words = line.trim().split("\\s+");
        for (String word : words) {
          if (!word.isEmpty()) {
            wordCount++;
          }
        }
      }

    } catch (IOException e) {
      System.out.println("Error reading file with InputStreamReader: " + e.getMessage());
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        System.out.println("Error closing InputStreamReader: " + e.getMessage());
      }
    }

    long endTime = System.nanoTime();
    long timeInMs = (endTime - startTime) / 1_000_000;

    return new long[] { wordCount, timeInMs };
  }

  // ========================= Utility Methods =========================
  /**
   * Creates a large sample file for testing
   * 
   * @param filePath the path where the file will be created
   */
  private static void createLargeFile(String filePath) {
    System.out.println("Creating sample file: " + filePath);

    try (FileWriter writer = new FileWriter(filePath)) {
      // Write multiple lines with words
      String[] words = { "Java", "is", "a", "powerful", "programming", "language",
          "that", "supports", "object", "oriented", "programming",
          "paradigm", "with", "features", "like", "polymorphism",
          "encapsulation", "and", "inheritance", "for", "building",
          "scalable", "applications" };

      int lineCount = 1000; // Create 1000 lines
      for (int i = 0; i < lineCount; i++) {
        for (String word : words) {
          writer.write(word + " ");
        }
        writer.write("\n");
      }

      System.out.println("✓ File created with " + (lineCount * words.length) + " words\n");

    } catch (IOException e) {
      System.out.println("Error creating file: " + e.getMessage());
    }
  }

  /**
   * Main method to run all tests
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║  Challenge: Compare String Classes and File Reader Classes      ║");
    System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

    // ==================== Part 1: StringBuilder vs StringBuffer
    // ====================
    System.out.println("╔─── PART 1: String Concatenation Performance ───╗\n");

    System.out.println("Configuration:");
    System.out.println("- Number of concatenations: " + STRING_ITERATIONS);
    System.out.println("- String to append: \"" + TEST_STRING + "\"");
    System.out.println();

    // Test StringBuilder
    System.out.println("Testing StringBuilder...");
    long sbTime = testStringBuilder();
    System.out.println("✓ StringBuilder Time: " + sbTime + " ms");
    System.out.println();

    // Test StringBuffer
    System.out.println("Testing StringBuffer...");
    long sbfTime = testStringBuffer();
    System.out.println("✓ StringBuffer Time: " + sbfTime + " ms");
    System.out.println();

    // Comparison
    System.out.println("─ COMPARISON ─");
    System.out.println("StringBuilder: " + sbTime + " ms");
    System.out.println("StringBuffer:  " + sbfTime + " ms");
    System.out.println();

    if (sbTime < sbfTime) {
      double percentage = ((sbfTime - sbTime) / (double) sbfTime) * 100;
      System.out.println("✓ StringBuilder is " + String.format("%.2f", percentage) +
          "% faster than StringBuffer");
      System.out.println("  Reason: StringBuffer is synchronized, adding overhead.");
    } else if (sbfTime < sbTime) {
      double percentage = ((sbTime - sbfTime) / (double) sbTime) * 100;
      System.out.println("✓ StringBuffer is " + String.format("%.2f", percentage) +
          "% faster than StringBuilder");
    }

    System.out.println();
    System.out.println("═".repeat(60));
    System.out.println();

    // ==================== Part 2: FileReader vs InputStreamReader
    // ====================
    System.out.println("╔─── PART 2: File Reading Performance ───╗\n");

    String testFilePath = "large_test_file.txt";
    createLargeFile(testFilePath);

    // Test FileReader
    System.out.println("Testing FileReader...");
    long[] fileReaderResults = countWordsUsingFileReader(testFilePath);
    System.out.println("✓ FileReader - Words: " + fileReaderResults[0] +
        ", Time: " + fileReaderResults[1] + " ms");
    System.out.println();

    // Test InputStreamReader
    System.out.println("Testing InputStreamReader...");
    long[] inputStreamReaderResults = countWordsUsingInputStreamReader(testFilePath);
    System.out.println("✓ InputStreamReader - Words: " + inputStreamReaderResults[0] +
        ", Time: " + inputStreamReaderResults[1] + " ms");
    System.out.println();

    // Comparison
    System.out.println("─ COMPARISON ─");
    System.out.println("FileReader:       " + fileReaderResults[1] + " ms");
    System.out.println("InputStreamReader: " + inputStreamReaderResults[1] + " ms");
    System.out.println();

    long timeDiff = Math.abs(fileReaderResults[1] - inputStreamReaderResults[1]);
    if (fileReaderResults[1] < inputStreamReaderResults[1]) {
      System.out.println("✓ FileReader is " + timeDiff + " ms faster");
    } else if (inputStreamReaderResults[1] < fileReaderResults[1]) {
      System.out.println("✓ InputStreamReader is " + timeDiff + " ms faster");
    } else {
      System.out.println("✓ Both methods have similar performance");
    }

    System.out.println();
    System.out.println("═".repeat(60));
    System.out.println();

    // ==================== Summary ====================
    System.out.println("╔─── SUMMARY ───╗\n");
    System.out.println("String Concatenation:");
    System.out.println("- StringBuilder is unsynchronized and faster for single-threaded use");
    System.out.println("- StringBuffer is synchronized for thread-safe operations");
    System.out.println();
    System.out.println("File Reading:");
    System.out.println("- FileReader uses default charset");
    System.out.println("- InputStreamReader allows specifying charset explicitly");
    System.out.println("- Performance difference is usually negligible");
    System.out.println("- Choose based on your charset requirements");
  }
}
