import java.util.Scanner;

public class SumOfNaturalNumbers {

  /**
   * Method to find the sum of n natural numbers using loop
   * 
   * @param n the number of natural numbers to sum
   * @return the sum of first n natural numbers
   */
  public static long calculateSumOfNaturalNumbers(int n) {
    long sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += i;
    }
    return sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter a number n: ");
    int n = scanner.nextInt();

    // Calculate sum of natural numbers
    long sum = calculateSumOfNaturalNumbers(n);

    // Display result
    System.out.println("The sum of first " + n + " natural numbers is: " + sum);

    scanner.close();
  }
}
