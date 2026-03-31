import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class LargeFileErrorFinder {

  public static void findErrorLines(String filename) {
    File file = new File(filename);

    if (!file.exists()) {
      System.out.println("File does not exist: " + filename);
      return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      int lineNumber = 0;
      int errorCount = 0;

      while ((line = br.readLine()) != null) {
        lineNumber++;

        if (line.toLowerCase().contains("error")) {
          System.out.println("Line " + lineNumber + ": " + line);
          errorCount++;
        }
      }

      System.out.println("\nTotal error lines found: " + errorCount);

    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    findErrorLines("largefile.txt");
  }
}
