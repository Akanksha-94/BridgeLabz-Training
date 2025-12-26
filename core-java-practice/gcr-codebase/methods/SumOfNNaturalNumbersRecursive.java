import java.util.Scanner;

public class SumOfNNaturalNumbersRecursive {

  /**
   * Method to find sum of n natural numbers using recursion
   * 
   * @param n the number of natural numbers to sum
   * @return the sum of first n natural numbers
   */
  public static long sumRecursive(int n) {
    if (n <= 0) {
      return 0;
    }
    return n + sumRecursive(n - 1);
  }

  /**
   * Method to find sum of n natural numbers using formula
   * Formula: sum = n * (n + 1) / 2
   * 
   * @param n the number of natural numbers to sum
   * @return the sum using formula
   */
  public static long sumUsingFormula(int n) {
    return (long) n * (n + 1) / 2;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter a natural number: ");
    int n = scanner.nextInt();

    // Validate for natural number
    if (n <= 0) {
      System.out.println("Please enter a positive natural number!");
      scanner.close();
      return;
    }

    // Calculate sum using both methods
    long sumRecursiveResult = sumRecursive(n);
    long sumFormulaResult = sumUsingFormula(n);

    // Display results
    System.out.println("\nSum of first " + n + " natural numbers:");
    System.out.println("Using Recursive Method: " + sumRecursiveResult);
    System.out.println("Using Formula n*(n+1)/2: " + sumFormulaResult);

    // Compare results
    if (sumRecursiveResult == sumFormulaResult) {
      System.out.println("\n✓ Both methods produce the same result - CORRECT!");
    } else {
      System.out.println("\n✗ Results don't match - ERROR!");
    }

    scanner.close();
  }
}
