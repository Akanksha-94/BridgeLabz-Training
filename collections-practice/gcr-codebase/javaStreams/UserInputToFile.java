import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserInputToFile {

  public static void main(String[] args) {
    BufferedReader reader = null;
    FileWriter writer = null;

    try {
      reader = new BufferedReader(new InputStreamReader(System.in));

      System.out.print("Enter your name: ");
      String name = reader.readLine();

      System.out.print("Enter your age: ");
      String age = reader.readLine();

      System.out.print("Enter your favorite programming language: ");
      String language = reader.readLine();

      writer = new FileWriter("user_info.txt");
      writer.write("Name: " + name + "\n");
      writer.write("Age: " + age + "\n");
      writer.write("Favorite Programming Language: " + language + "\n");

      System.out.println("User information saved successfully to user_info.txt");

    } catch (IOException e) {
      System.out.println("Error during I/O operation: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        System.out.println("Error closing resources: " + e.getMessage());
      }
    }
  }
}
