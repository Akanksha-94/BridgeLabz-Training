import java.util.Scanner;

public class ArrayIndexDemo {
  static void generateException(String[] arr) {
    System.out.println(arr[5]);
  }

  static void handleException(String[] arr) {
    try {
      System.out.println(arr[5]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
    } catch (RuntimeException e) {
      System.out.println("Caught RuntimeException: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter comma-separated names (e.g. Alice,Bob,Charlie):");
    String line = sc.nextLine();
    String[] names = line.split("\\s*,\\s*");

    System.out.println("\n--- Demonstration: generateException (will throw if index invalid) ---");
    try {
      generateException(names);
    } catch (RuntimeException e) {
      System.out.println("generateException threw: " + e);
    }

    System.out.println("\n--- Demonstration: handleException (handles ArrayIndexOutOfBoundsException) ---");
    handleException(names);

  }
}
