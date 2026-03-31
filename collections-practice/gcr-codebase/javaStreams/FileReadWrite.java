import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class FileReadWrite {

  public static void copyFile(String sourceFile, String destinationFile) {
    File source = new File(sourceFile);

    if (!source.exists()) {
      System.out.println("Source file does not exist: " + sourceFile);
      return;
    }

    try (FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destinationFile)) {

      byte[] buffer = new byte[1024];
      int bytesRead;

      while ((bytesRead = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, bytesRead);
      }

      System.out.println("File copied successfully from " + sourceFile + " to " + destinationFile);

    } catch (IOException e) {
      System.out.println("Error during file copy: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    copyFile("source.txt", "destination.txt");
  }
}
