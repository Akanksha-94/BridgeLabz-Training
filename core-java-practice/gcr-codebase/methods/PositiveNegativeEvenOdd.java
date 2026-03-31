import java.util.Scanner;

public class PositiveNegativeEvenOdd {

  /**
   * Method to check whether a number is positive or negative
   * 
   * @param number the number to check
   * @return true if positive, false if negative or zero
   */
  public static boolean isPositive(int number) {
    return number > 0;
  }

  /**
   * Method to check whether a number is even or odd
   * 
   * @param number the number to check
   * @return true if even, false if odd
   */
  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  /**
   * Method to compare two numbers
   * 
   * @param number1 the first number
   * @param number2 the second number
   * @return 1 if number1 > number2, 0 if equal, -1 if number1 < number2
   */
  public static int compare(int number1, int number2) {
    if (number1 > number2) {
      return 1;
    } else if (number1 == number2) {
      return 0;
    } else {
      return -1;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Array to store 5 numbers
    int[] numbers = new int[5];

    System.out.println("Enter 5 numbers:");

    // Input and analyze each number
    for (int i = 0; i < 5; i++) {
      System.out.print("Enter number " + (i + 1) + ": ");
      numbers[i] = scanner.nextInt();

      if (isPositive(numbers[i])) {
        System.out.print("  Positive ");
        if (isEven(numbers[i])) {
          System.out.println("Even");
        } else {
          System.out.println("Odd");
        }
      } else {
        System.out.println("  Negative");
      }
    }

    // Compare first and last elements
    System.out.println("\n===== Comparison of First and Last Element =====");
    System.out.println("First element: " + numbers[0]);
    System.out.println("Last element: " + numbers[4]);

    int comparisonResult = compare(numbers[0], numbers[4]);

    if (comparisonResult == 1) {
      System.out.println("Result: First element is GREATER than last element");
    } else if (comparisonResult == 0) {
      System.out.println("Result: First and last elements are EQUAL");
    } else {
      System.out.println("Result: First element is LESS than last element");
    }

    scanner.close();
  }
}
