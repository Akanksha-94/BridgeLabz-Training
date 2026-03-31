
/**
 * InputStreamReader Problem 1: Convert Byte Stream to Character Stream Using InputStreamReader
 * 
 * Problem:
 * Write a program that uses InputStreamReader to read binary data from a file 
 * and print it as characters. The file contains data encoded in a specific charset (e.g., UTF-8).
 * 
 * Approach:
 * 1. Create a FileInputStream object to read the binary data from the file.
 * 2. Wrap the FileInputStream in an InputStreamReader to convert the byte stream 
 *    into a character stream.
 * 3. Use a BufferedReader to read characters efficiently from the InputStreamReader.
 * 4. Read the file line by line and print the characters to the console.
 * 5. Handle any encoding exceptions as needed.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConvertByteStreamToCharacterStream {

  /**
   * Reads a file using InputStreamReader with UTF-8 encoding
   * 
   * @param filePath the path to the file to read
   */
  public static void readFileWithInputStreamReader(String filePath) {
    BufferedReader reader = null;
    try {
      // Create FileInputStream and wrap it with InputStreamReader using UTF-8
      FileInputStream fileInputStream = new FileInputStream(filePath);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
      reader = new BufferedReader(inputStreamReader);

      String line;
      int lineNumber = 1;

      System.out.println("File contents (UTF-8 encoded):");
      System.out.println("-".repeat(50));

      while ((line = reader.readLine()) != null) {
        System.out.println(lineNumber + ": " + line);
        lineNumber++;
      }

      System.out.println("-".repeat(50));
      System.out.println("Total lines: " + (lineNumber - 1));

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
   * Reads a file with specified encoding using InputStreamReader
   * 
   * @param filePath the path to the file to read
   * @param charset  the character set to use for decoding
   */
  public static void readFileWithSpecifiedCharset(String filePath, String charset) {
    BufferedReader reader = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
      reader = new BufferedReader(inputStreamReader);

      String line;
      int charCount = 0;
      int lineCount = 0;

      System.out.println("File contents (Charset: " + charset + "):");
      System.out.println("-".repeat(50));

      while ((line = reader.readLine()) != null) {
        lineCount++;
        charCount += line.length();
        System.out.println("Line " + lineCount + ": " + line);
      }

      System.out.println("-".repeat(50));
      System.out.println("Statistics:");
      System.out.println("Total lines: " + lineCount);
      System.out.println("Total characters: " + charCount);

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
   * Main method to test InputStreamReader functionality
   */
  public static void main(String[] args) {
    System.out.println("=== Convert Byte Stream to Character Stream ===\n");

    // Create a sample file with UTF-8 encoding
    String testFilePath = "utf8_sample.txt";
    createSampleFile(testFilePath);

    System.out.println("Test Case 1: Reading file with default UTF-8 encoding\n");
    readFileWithInputStreamReader(testFilePath);

    System.out.println("\n" + "=".repeat(50) + "\n");

    System.out.println("Test Case 2: Reading file with specified charset (UTF-8)\n");
    readFileWithSpecifiedCharset(testFilePath, "UTF-8");

    System.out.println("\n" + "=".repeat(50) + "\n");

    System.out.println("Test Case 3: Reading file with ISO-8859-1 charset\n");
    readFileWithSpecifiedCharset(testFilePath, "ISO-8859-1");
  }

  /**
   * Creates a sample file with UTF-8 encoding
   * 
   * @param filePath the path where the sample file will be created
   */
  private static void createSampleFile(String filePath) {
    try (java.io.FileWriter writer = new java.io.FileWriter(filePath, StandardCharsets.UTF_8)) {
      writer.write("Line 1: Welcome to InputStreamReader\n");
      writer.write("Line 2: Converts byte streams to character streams\n");
      writer.write("Line 3: Special characters: á é í ó ú\n");
      writer.write("Line 4: Unicode support: 你好世界 (Hello World in Chinese)\n");
      writer.write("Line 5: Emoji support: 🚀 💻 🎯\n");
    } catch (IOException e) {
      System.out.println("Error creating sample file: " + e.getMessage());
    }
  }
}
