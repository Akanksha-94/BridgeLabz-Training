import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class BufferedFileCopy {

  private static final int BUFFER_SIZE = 4096;

  public static long copyWithBufferedStreams(String source, String destination) {
    long startTime = System.nanoTime();

    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source), BUFFER_SIZE);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination), BUFFER_SIZE)) {

      byte[] buffer = new byte[BUFFER_SIZE];
      int bytesRead;

      while ((bytesRead = bis.read(buffer)) != -1) {
        bos.write(buffer, 0, bytesRead);
      }
      bos.flush();

    } catch (IOException e) {
      System.out.println("Error in buffered copy: " + e.getMessage());
      e.printStackTrace();
    }

    long endTime = System.nanoTime();
    return endTime - startTime;
  }

  public static long copyWithUnbufferedStreams(String source, String destination) {
    long startTime = System.nanoTime();

    try (FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination)) {

      byte[] buffer = new byte[1024];
      int bytesRead;

      while ((bytesRead = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, bytesRead);
      }

    } catch (IOException e) {
      System.out.println("Error in unbuffered copy: " + e.getMessage());
      e.printStackTrace();
    }

    long endTime = System.nanoTime();
    return endTime - startTime;
  }

  public static void main(String[] args) {
    String source = "largefile.bin";
    String bufferedDest = "buffered_copy.bin";
    String unbufferedDest = "unbuffered_copy.bin";

    long bufferedTime = copyWithBufferedStreams(source, bufferedDest);
    long unbufferedTime = copyWithUnbufferedStreams(source, unbufferedDest);

    System.out.println("Buffered Copy Time: " + bufferedTime + " ns (" + (bufferedTime / 1_000_000) + " ms)");
    System.out.println("Unbuffered Copy Time: " + unbufferedTime + " ns (" + (unbufferedTime / 1_000_000) + " ms)");
    System.out
        .println("Performance Improvement: " + ((double) (unbufferedTime - bufferedTime) / unbufferedTime * 100) + "%");
  }
}
