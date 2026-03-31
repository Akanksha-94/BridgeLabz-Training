
/**
 * FileReader Problem 2: Count the Occurrence of a Word in a File Using FileReader
 * 
 * Problem:
 * Write a program that uses FileReader and BufferedReader to read a file 
 * and count how many times a specific word appears in the file.
 * 
 * Approach:
 * 1. Create a FileReader to read from the file and wrap it in a BufferedReader.
 * 2. Initialize a counter variable to keep track of word occurrences.
 * 3. For each line in the file, split it into words and check if the target word exists.
 * 4. Increment the counter each time the word is found.
 * 5. Print the final count.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountWordOccurrenceInFile {

  /**
   * Counts the occurrence of a specific word in a file (case-insensitive)
   * 
   * @param filePath   the path to the file to read
   * @param targetWord the word to search for
   * @return the count of occurrences
   */
  public static int countWordOccurrence(String filePath, String targetWord) {
    int count = 0;
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line;

      while ((line = reader.readLine()) != null) {
        // Split the line into words
        String[] words = line.split("\\s+|[,;.!?:'\"-]+");

        // Check each word
        for (String word : words) {
          if (word.equalsIgnoreCase(targetWord)) {
            count++;
          }
        }
      }

    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        System.out.println("Error closing file: " + e.getMessage());
      }
    }

    return count;
  }

  /**
   * Counts the occurrence of a word and displays details
   * 
   * @param filePath   the path to the file to read
   * @param targetWord the word to search for
   */
  public static void countAndDisplay(String filePath, String targetWord) {
    BufferedReader reader = null;
    int count = 0;
    int lineNumber = 0;

    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line;

      System.out.println("Searching for word: \"" + targetWord + "\"\n");
      System.out.println("Occurrences by line:");
      System.out.println("-".repeat(50));

      while ((line = reader.readLine()) != null) {
        lineNumber++;
        String[] words = line.split("\\s+|[,;.!?:'\"-]+");

        int lineCount = 0;
        for (String word : words) {
          if (word.equalsIgnoreCase(targetWord)) {
            lineCount++;
            count++;
          }
        }

        if (lineCount > 0) {
          System.out.println("Line " + lineNumber + " (" + lineCount + " occurrence(s)): " + line);
        }
      }

      System.out.println("-".repeat(50));
      System.out.println("\nTotal occurrences of \"" + targetWord + "\": " + count);

    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        System.out.println("Error closing file: " + e.getMessage());
      }
    }
  }

  /**
   * Main method to test word counting functionality
   */
  public static void main(String[] args) {
    System.out.println("=== Count Word Occurrence in File Using FileReader ===\n");

    // Create a sample file for testing
    String testFilePath = "sample_text.txt";
    createSampleFile(testFilePath);

    // Test case 1: Count occurrences of "Java"
    System.out.println("Test Case 1: Searching for 'Java'\n");
    countAndDisplay(testFilePath, "Java");

    System.out.println("\n" + "=".repeat(50) + "\n");

    // Test case 2: Count occurrences of "the"
    System.out.println("Test Case 2: Searching for 'the'\n");
    countAndDisplay(testFilePath, "the");

    System.out.println("\n" + "=".repeat(50) + "\n");

    // Test case 3: Count occurrences of "programming"
    System.out.println("Test Case 3: Searching for 'programming'\n");
    int count = countWordOccurrence(testFilePath, "programming");
    System.out.println("Occurrences of 'programming': " + count);
  }

  /**
   * Creates a sample file for testing
   * 
   * @param filePath the path where the sample file will be created
   */
  private static void createSampleFile(String filePath) {
    try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
      writer.write("Java is a powerful programming language.\n");
      writer.write("Java is used for developing various applications.\n");
      writer.write("The Java Virtual Machine executes Java bytecode.\n");
      writer.write("Java programming requires understanding of OOP concepts.\n");
      writer.write("Many companies use Java for their enterprise applications.\n");
      writer.write("The Spring Framework is popular in Java development.\n");
      writer.write("Java provides robust exception handling mechanisms.\n");
    } catch (IOException e) {
      System.out.println("Error creating sample file: " + e.getMessage());
    }
  }
}
