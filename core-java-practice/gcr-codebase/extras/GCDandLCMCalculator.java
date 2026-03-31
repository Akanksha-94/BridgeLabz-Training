import java.util.Scanner;

public class GCDandLCMCalculator {

  // Take input from user
  static int[] takeInput() {
    Scanner scanner = new Scanner(System.in);
    int[] numbers = new int[2];

    System.out.println("=== GCD and LCM Calculator ===");
    System.out.print("Enter first number: ");
    numbers[0] = scanner.nextInt();

    System.out.print("Enter second number: ");
    numbers[1] = scanner.nextInt();

    return numbers;
  }

  // Calculate GCD using Euclidean algorithm
  static int calculateGCD(int num1, int num2) {
    num1 = Math.abs(num1);
    num2 = Math.abs(num2);

    while (num2 != 0) {
      int temp = num2;
      num2 = num1 % num2;
      num1 = temp;
    }

    return num1;
  }

  // Calculate LCM using GCD
  static long calculateLCM(int num1, int num2) {
    int gcd = calculateGCD(num1, num2);
    long lcm = ((long) Math.abs(num1) * Math.abs(num2)) / gcd;
    return lcm;
  }

  // Display result
  static void displayResult(int num1, int num2, int gcd, long lcm) {
    System.out.println("\n--- Result ---");
    System.out.println("Numbers: " + num1 + " and " + num2);
    System.out.println("GCD (Greatest Common Divisor): " + gcd);
    System.out.println("LCM (Least Common Multiple): " + lcm);
  }

  public static void main(String[] args) {
    int[] numbers = takeInput();
    int gcd = calculateGCD(numbers[0], numbers[1]);
    long lcm = calculateLCM(numbers[0], numbers[1]);
    displayResult(numbers[0], numbers[1], gcd, lcm);
  }
}
