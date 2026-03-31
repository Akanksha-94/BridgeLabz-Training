import java.util.Scanner;

public class MaxOfThreeNumbers {

  // Take input from user
  static int[] takeInput() {
    Scanner scanner = new Scanner(System.in);
    int[] numbers = new int[3];

    System.out.println("=== Maximum of Three Numbers ===");
    System.out.print("Enter first number: ");
    numbers[0] = scanner.nextInt();

    System.out.print("Enter second number: ");
    numbers[1] = scanner.nextInt();

    System.out.print("Enter third number: ");
    numbers[2] = scanner.nextInt();

    return numbers;
  }

  // Calculate maximum of three numbers
  static int findMaximum(int num1, int num2, int num3) {
    int max = num1;

    if (num2 > max) {
      max = num2;
    }
    if (num3 > max) {
      max = num3;
    }

    return max;
  }

  // Display result
  static void displayResult(int num1, int num2, int num3, int maximum) {
    System.out.println("\n--- Result ---");
    System.out.println("Numbers: " + num1 + ", " + num2 + ", " + num3);
    System.out.println("Maximum number: " + maximum);
  }

  public static void main(String[] args) {
    int[] numbers = takeInput();
    int maximum = findMaximum(numbers[0], numbers[1], numbers[2]);
    displayResult(numbers[0], numbers[1], numbers[2], maximum);
  }
}
