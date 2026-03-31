import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;

public class PipedStreamCommunication {

  static class WriterThread extends Thread {
    private PipedOutputStream pos;

    public WriterThread(PipedOutputStream pos) {
      this.pos = pos;
    }

    @Override
    public void run() {
      try {
        String[] messages = { "Hello", "World", "Piped", "Streams", "Work", "END" };

        for (String message : messages) {
          pos.write((message + "\n").getBytes());
          pos.flush();
          System.out.println("Written: " + message);
          Thread.sleep(1000);
        }

        pos.close();

      } catch (IOException | InterruptedException e) {
        System.out.println("Writer error: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  static class ReaderThread extends Thread {
    private PipedInputStream pis;

    public ReaderThread(PipedInputStream pis) {
      this.pis = pis;
    }

    @Override
    public void run() {
      try {
        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder message = new StringBuilder();

        while ((bytesRead = pis.read(buffer)) != -1) {
          message.append(new String(buffer, 0, bytesRead));

          int endIndex;
          while ((endIndex = message.indexOf("\n")) != -1) {
            String line = message.substring(0, endIndex);
            System.out.println("Read: " + line);

            if (line.equals("END")) {
              return;
            }

            message.delete(0, endIndex + 1);
          }
        }

        pis.close();

      } catch (IOException e) {
        System.out.println("Reader error: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    try {
      PipedInputStream pis = new PipedInputStream();
      PipedOutputStream pos = new PipedOutputStream(pis);

      WriterThread writer = new WriterThread(pos);
      ReaderThread reader = new ReaderThread(pis);

      writer.start();
      reader.start();

      writer.join();
      reader.join();

      System.out.println("Communication completed successfully");

    } catch (IOException | InterruptedException e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
