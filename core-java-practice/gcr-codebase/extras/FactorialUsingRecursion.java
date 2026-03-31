import java.util.Scanner;

public class FactorialUsingRecursion {

  // Take input from user
  static int takeInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("=== Factorial Calculator (Using Recursion) ===");
    System.out.print("Enter a number: ");
    return scanner.nextInt();
  }

  // Calculate factorial recursively
  static long calculateFactorial(int number) {
    if (number < 0) {
      System.out.println("Factorial is not defined for negative numbers.");
      return -1;
    }

    if (number == 0 || number == 1) {
      return 1;
    }

    return number * calculateFactorial(number - 1);
  }

  // Display result
  static void displayResult(int number, long factorial) {
    System.out.println("\n--- Result ---");
    System.out.println("Number: " + number);
    if (factorial == -1) {
      System.out.println("Cannot calculate factorial for negative numbers.");
    } else {
      System.out.println("Factorial of " + number + " = " + factorial);
    }
  }

  public static void main(String[] args) {
    int number = takeInput();
    long factorial = calculateFactorial(number);
    displayResult(number, factorial);
  }
}
