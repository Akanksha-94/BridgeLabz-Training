import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class UppercaseToLowercaseFilter {

  public static void convertToLowercase(String sourceFile, String destinationFile) {
    File source = new File(sourceFile);

    if (!source.exists()) {
      System.out.println("Source file does not exist: " + sourceFile);
      return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))) {

      String line;
      while ((line = br.readLine()) != null) {
        String convertedLine = line.toLowerCase();
        bw.write(convertedLine);
        bw.newLine();
      }

      System.out.println("File converted successfully from " + sourceFile + " to " + destinationFile);

    } catch (IOException e) {
      System.out.println("Error during file conversion: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    convertToLowercase("uppercase_input.txt", "lowercase_output.txt");
  }
}
