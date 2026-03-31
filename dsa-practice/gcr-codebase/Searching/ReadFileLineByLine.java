
/**
 * FileReader Problem 1: Read a File Line by Line Using FileReader
 * 
 * Problem:
 * Write a program that uses FileReader to read a text file line by line 
 * and print each line to the console.
 * 
 * Approach:
 * 1. Create a FileReader object to read from the file.
 * 2. Wrap the FileReader in a BufferedReader to read lines efficiently.
 * 3. Use a loop to read each line using the readLine() method and print it to the console.
 * 4. Close the file after reading all the lines.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileLineByLine {

  /**
   * Reads a file line by line and prints each line to the console
   * 
   * @param filePath the path to the file to read
   */
  public static void readFileLineByLine(String filePath) {
    BufferedReader reader = null;
    try {
      // Create a FileReader and wrap it with BufferedReader
      reader = new BufferedReader(new FileReader(filePath));

      String line;
      int lineNumber = 1;

      // Read each line until EOF
      while ((line = reader.readLine()) != null) {
        System.out.println(lineNumber + ": " + line);
        lineNumber++;
      }

      System.out.println("\nTotal lines read: " + (lineNumber - 1));

    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    } finally {
      // Close the reader
      try {
        if (reader != null) {
          reader.close();
          System.out.println("File closed successfully.");
        }
      } catch (IOException e) {
        System.out.println("Error closing file: " + e.getMessage());
      }
    }
  }

  /**
   * Alternative method: Read file and return total line count
   * 
   * @param filePath the path to the file to read
   * @return total number of lines in the file
   */
  public static int readFileAndCountLines(String filePath) {
    int lineCount = 0;
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = reader.readLine()) != null) {
        lineCount++;
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
    return lineCount;
  }

  /**
   * Main method to test file reading
   */
  public static void main(String[] args) {
    System.out.println("=== File Reading Using FileReader and BufferedReader ===\n");

    // Create a sample file for testing
    String testFilePath = "test_input.txt";
    createSampleFile(testFilePath);

    System.out.println("Reading file: " + testFilePath);
    System.out.println();

    // Read and print file contents
    readFileLineByLine(testFilePath);

    System.out.println();

    // Count and display total lines
    int totalLines = readFileAndCountLines(testFilePath);
    System.out.println("Total lines in file: " + totalLines);
  }

  /**
   * Creates a sample file for testing
   * 
   * @param filePath the path where the sample file will be created
   */
  private static void createSampleFile(String filePath) {
    try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
      writer.write("Line 1: Introduction to FileReader\n");
      writer.write("Line 2: Reading files efficiently with BufferedReader\n");
      writer.write("Line 3: One line per read operation\n");
      writer.write("Line 4: Easy exception handling with try-catch\n");
      writer.write("Line 5: Always close resources properly\n");
      writer.write("Line 6: The end of the sample file\n");
    } catch (IOException e) {
      System.out.println("Error creating sample file: " + e.getMessage());
    }
  }
}
