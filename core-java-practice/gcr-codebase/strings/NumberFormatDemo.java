import java.util.Scanner;

public class NumberFormatDemo {
  static void generateException(String text) {
    int n = Integer.parseInt(text);
    System.out.println("generateException parsed: " + n);
  }

  static void handleException(String text) {
    try {
      int n = Integer.parseInt(text);
      System.out.println("handleException parsed: " + n);
    } catch (NumberFormatException e) {
      System.out.println("Caught NumberFormatException: " + e.getMessage());
    } catch (RuntimeException e) {
      System.out.println("Caught RuntimeException: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text to parse as integer:");
    String input = sc.nextLine();

    System.out.println("\n--- Demonstration: generateException (will throw) ---");
    try {
      generateException(input);
    } catch (RuntimeException e) {
      System.out.println("generateException threw: " + e);
    }

    System.out.println("\n--- Demonstration: handleException (handles NumberFormatException) ---");
    handleException(input);

  }
}
