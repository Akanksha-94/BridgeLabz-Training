import java.util.Scanner;

public class FibonacciSequenceGenerator {

  // Take input from user
  static int takeInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("=== Fibonacci Sequence Generator ===");
    System.out.print("Enter the number of terms: ");
    return scanner.nextInt();
  }

  // Calculate and print Fibonacci sequence
  static void printFibonacciSequence(int terms) {
    if (terms <= 0) {
      System.out.println("Please enter a positive number.");
      return;
    }

    System.out.println("\nFibonacci Sequence (" + terms + " terms):");

    if (terms >= 1) {
      System.out.print("0");
    }
    if (terms >= 2) {
      System.out.print(", 1");
    }

    if (terms > 2) {
      long a = 0, b = 1;
      for (int i = 3; i <= terms; i++) {
        long next = a + b;
        System.out.print(", " + next);
        a = b;
        b = next;
      }
    }

    System.out.println();
  }

  public static void main(String[] args) {
    int terms = takeInput();
    printFibonacciSequence(terms);
  }
}
