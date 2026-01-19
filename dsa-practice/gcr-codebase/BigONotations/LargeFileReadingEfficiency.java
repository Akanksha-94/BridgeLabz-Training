import java.io.*;

/**
 * Problem Statement: Large File Reading Efficiency
 * 
 * Objective:
 * Compare FileReader (Character Stream) and InputStreamReader (Byte Stream)
 * when reading large files.
 * 
 * Approach:
 * - FileReader: Reads character by character (slower for binary files).
 * - InputStreamReader: Reads bytes and converts to characters (more efficient).
 * - Both wrapped in BufferedReader for optimal performance.
 * 
 * Time Complexity: O(N) for both where N is file size
 * Space Complexity: O(1) with buffering
 * 
 * Key Differences:
 * - FileReader uses default character encoding, InputStreamReader can specify
 * - InputStreamReader is more flexible and generally more efficient
 * - Both should be wrapped in BufferedReader for large files
 */
public class LargeFileReadingEfficiency {

  /**
   * Read file using FileReader - O(N)
   * Uses default platform character encoding
   */
  public static long readFileUsingFileReader(String filePath, int bufferSize)
      throws IOException {
    long characterCount = 0;
    try (BufferedReader reader = new BufferedReader(
        new FileReader(filePath), bufferSize)) {
      String line;
      while ((line = reader.readLine()) != null) {
        characterCount += line.length();
      }
    }
    return characterCount;
  }

  /**
   * Read file using InputStreamReader - O(N)
   * More flexible, can specify character encoding
   */
  public static long readFileUsingInputStreamReader(String filePath, int bufferSize)
      throws IOException {
    long characterCount = 0;
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(filePath), "UTF-8"), bufferSize)) {
      String line;
      while ((line = reader.readLine()) != null) {
        characterCount += line.length();
      }
    }
    return characterCount;
  }

  /**
   * Read file using FileReader without BufferedReader - O(N)
   * Direct reading character by character (slow)
   */
  public static long readFileUsingFileReaderDirect(String filePath)
      throws IOException {
    long characterCount = 0;
    try (FileReader reader = new FileReader(filePath)) {
      int ch;
      while ((ch = reader.read()) != -1) {
        characterCount++;
      }
    }
    return characterCount;
  }

  /**
   * Create a test file with specified size
   */
  public static String createTestFile(int sizeInMB) throws IOException {
    String fileName = "testfile_" + sizeInMB + "MB.txt";

    try (BufferedWriter writer = new BufferedWriter(
        new FileWriter(fileName), 8192)) {

      String testLine = "This is a test line for file reading performance measurement. ";
      long targetSize = (long) sizeInMB * 1024 * 1024;
      long currentSize = 0;
      int lineCount = 0;

      while (currentSize < targetSize) {
        writer.write(testLine);
        writer.newLine();
        currentSize += testLine.length() + 1; // +1 for newline
        lineCount++;

        // Progress indicator
        if (lineCount % 100000 == 0) {
          System.out.println("Created " + (currentSize / (1024 * 1024)) + " MB");
        }
      }
    }

    System.out.println("Test file created: " + fileName);
    return fileName;
  }

  /**
   * Delete test file
   */
  public static void deleteTestFile(String filePath) {
    File file = new File(filePath);
    if (file.exists() && file.delete()) {
      System.out.println("Test file deleted: " + filePath);
    }
  }

  /**
   * Measure reading performance
   */
  public static void measurePerformance(String filePath, int bufferSize) {
    System.out.println("\n--- Buffer Size: " + bufferSize + " bytes ---");

    try {
      // FileReader with BufferedReader
      long startFR = System.nanoTime();
      long charCountFR = readFileUsingFileReader(filePath, bufferSize);
      long endFR = System.nanoTime();
      double frTime = (endFR - startFR) / 1_000_000.0;

      // InputStreamReader with BufferedReader
      long startISR = System.nanoTime();
      long charCountISR = readFileUsingInputStreamReader(filePath, bufferSize);
      long endISR = System.nanoTime();
      double isrTime = (endISR - startISR) / 1_000_000.0;

      System.out.printf("FileReader: %.3fms (Characters: %d)%n", frTime, charCountFR);
      System.out.printf("InputStreamReader: %.3fms (Characters: %d)%n", isrTime, charCountISR);

      if (isrTime > 0) {
        System.out.printf("Ratio (FR/ISR): %.2fx%n", frTime / isrTime);
      }

    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
  }

  /**
   * Demonstrate the impact of buffer size
   */
  public static void demonstrateBufferSize() {
    System.out.println("\n===== Buffer Size Impact =====");
    System.out.println("Recommended buffer sizes:");
    System.out.println("- Small files (< 1MB): 1KB - 4KB buffer");
    System.out.println("- Medium files (1-100MB): 8KB - 64KB buffer");
    System.out.println("- Large files (> 100MB): 64KB - 256KB buffer");
    System.out.println("\nBufferSize is crucial for performance:");
    System.out.println("- Too small: Many I/O operations, slow");
    System.out.println("- Too large: Wasted memory");
    System.out.println("- Optimal: Usually 8192 (8KB) or 65536 (64KB)");
  }

  /**
   * Demonstrate character encoding impact
   */
  public static void demonstrateEncoding() {
    System.out.println("\n===== Character Encoding Impact =====");
    System.out.println("FileReader uses default platform encoding (usually UTF-8)");
    System.out.println("InputStreamReader allows explicit encoding specification");
    System.out.println("\nCommon encodings:");
    System.out.println("- UTF-8: Variable-length, universal");
    System.out.println("- UTF-16: Fixed 2-byte characters");
    System.out.println("- ISO-8859-1: Single-byte, limited character set");
    System.out.println("- ASCII: 7-bit, English only");
  }

  public static void main(String[] args) {
    System.out.println("===== Large File Reading Efficiency =====");
    System.out.println("FileReader vs InputStreamReader with BufferedReader");

    demonstrateBufferSize();
    demonstrateEncoding();

    // Create test files and measure performance
    String[] testFiles = {
        "testfile_1MB.txt",
        "testfile_10MB.txt"
    };

    try {
      // Create test files
      System.out.println("\n===== Creating Test Files =====");
      String file1MB = createTestFile(1);
      String file10MB = createTestFile(10);

      // Measure with different buffer sizes
      System.out.println("\n===== Performance Measurement (1MB file) =====");
      measurePerformance(file1MB, 1024); // 1KB
      measurePerformance(file1MB, 8192); // 8KB
      measurePerformance(file1MB, 65536); // 64KB

      System.out.println("\n===== Performance Measurement (10MB file) =====");
      measurePerformance(file10MB, 8192); // 8KB
      measurePerformance(file10MB, 65536); // 64KB
      measurePerformance(file10MB, 262144); // 256KB

      // Cleanup
      System.out.println("\n===== Cleanup =====");
      deleteTestFile(file1MB);
      deleteTestFile(file10MB);

    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\n\nConclusion:");
    System.out.println("- Both FileReader and InputStreamReader are O(N) for reading");
    System.out.println("- InputStreamReader is slightly more efficient and flexible");
    System.out.println("- ALWAYS use BufferedReader for large files - drastically improves performance");
    System.out.println("- Buffer size significantly impacts performance");
    System.out.println("- Use 8KB-64KB buffer for optimal performance");
    System.out.println("- FileReader is preferred for text files, InputStreamReader for encoding control");
  }
}
