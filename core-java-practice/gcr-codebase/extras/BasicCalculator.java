import java.util.Scanner;

public class BasicCalculator {

  // Addition function
  static double add(double num1, double num2) {
    return num1 + num2;
  }

  // Subtraction function
  static double subtract(double num1, double num2) {
    return num1 - num2;
  }

  // Multiplication function
  static double multiply(double num1, double num2) {
    return num1 * num2;
  }

  // Division function
  static double divide(double num1, double num2) {
    if (num2 == 0) {
      System.out.println("Error: Division by zero is not allowed!");
      return 0;
    }
    return num1 / num2;
  }

  // Take input from user
  static void runCalculator() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Basic Calculator ===");
    System.out.println("Choose an operation:");
    System.out.println("1. Addition (+)");
    System.out.println("2. Subtraction (-)");
    System.out.println("3. Multiplication (*)");
    System.out.println("4. Division (/)");
    System.out.print("Enter your choice (1-4): ");

    int choice = scanner.nextInt();

    System.out.print("Enter first number: ");
    double num1 = scanner.nextDouble();

    System.out.print("Enter second number: ");
    double num2 = scanner.nextDouble();

    double result = 0;
    String operation = "";
    boolean validChoice = true;

    switch (choice) {
      case 1:
        result = add(num1, num2);
        operation = "+";
        break;

      case 2:
        result = subtract(num1, num2);
        operation = "-";
        break;

      case 3:
        result = multiply(num1, num2);
        operation = "*";
        break;

      case 4:
        result = divide(num1, num2);
        operation = "/";
        break;

      default:
        System.out.println("Invalid choice! Please enter a number between 1 and 4.");
        validChoice = false;
    }

    if (validChoice) {
      System.out.println("\n--- Result ---");
      System.out.println(num1 + " " + operation + " " + num2 + " = " + result);
    }
  }

  public static void main(String[] args) {
    runCalculator();
  }
}
