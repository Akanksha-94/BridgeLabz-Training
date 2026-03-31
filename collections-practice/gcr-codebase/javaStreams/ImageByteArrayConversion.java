import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageByteArrayConversion {

  public static byte[] imageToByteArray(String imagePath) {
    try {
      return Files.readAllBytes(Paths.get(imagePath));
    } catch (IOException e) {
      System.out.println("Error reading image: " + e.getMessage());
      e.printStackTrace();
    }
    return new byte[0];
  }

  public static void byteArrayToImage(byte[] byteArray, String outputPath) {
    try (FileOutputStream fos = new FileOutputStream(outputPath)) {
      fos.write(byteArray);
      System.out.println("Image written successfully to " + outputPath);
    } catch (IOException e) {
      System.out.println("Error writing image: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void convertUsingByteArrayStreams(String sourceImage, String destinationImage) {
    try {
      FileInputStream fis = new FileInputStream(sourceImage);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      byte[] buffer = new byte[1024];
      int bytesRead;

      while ((bytesRead = fis.read(buffer)) != -1) {
        baos.write(buffer, 0, bytesRead);
      }
      fis.close();

      byte[] imageBytes = baos.toByteArray();
      ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);

      FileOutputStream fos = new FileOutputStream(destinationImage);
      byte[] readBuffer = new byte[1024];
      int read;

      while ((read = bais.read(readBuffer)) != -1) {
        fos.write(readBuffer, 0, read);
      }

      fos.close();
      bais.close();
      baos.close();

      byte[] originalBytes = imageToByteArray(sourceImage);
      byte[] newBytes = imageToByteArray(destinationImage);

      if (originalBytes.length == newBytes.length) {
        boolean identical = true;
        for (int i = 0; i < originalBytes.length; i++) {
          if (originalBytes[i] != newBytes[i]) {
            identical = false;
            break;
          }
        }
        if (identical) {
          System.out.println("Images are identical!");
        } else {
          System.out.println("Images differ in content");
        }
      } else {
        System.out.println("Images have different sizes");
      }

    } catch (IOException e) {
      System.out.println("Error during conversion: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String sourceImage = "original_image.jpg";
    String destinationImage = "converted_image.jpg";

    convertUsingByteArrayStreams(sourceImage, destinationImage);
  }
}
