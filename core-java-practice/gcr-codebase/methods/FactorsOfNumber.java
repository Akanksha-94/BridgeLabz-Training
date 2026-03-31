import java.util.Scanner;

public class FactorsOfNumber {

  /**
   * Method to find all factors of a number and store them in an array
   * 
   * @param number the number to find factors for
   * @return an array containing all factors
   */
  public static int[] findFactors(int number) {
    // First loop: count the factors
    int count = 0;
    for (int i = 1; i <= number; i++) {
      if (number % i == 0) {
        count++;
      }
    }

    // Initialize array with the count
    int[] factors = new int[count];

    // Second loop: store factors in array
    int index = 0;
    for (int i = 1; i <= number; i++) {
      if (number % i == 0) {
        factors[index] = i;
        index++;
      }
    }

    return factors;
  }

  /**
   * Method to find the sum of factors
   * 
   * @param factors array of factors
   * @return sum of all factors
   */
  public static long findSumOfFactors(int[] factors) {
    long sum = 0;
    for (int factor : factors) {
      sum += factor;
    }
    return sum;
  }

  /**
   * Method to find the product of factors
   * 
   * @param factors array of factors
   * @return product of all factors
   */
  public static long findProductOfFactors(int[] factors) {
    long product = 1;
    for (int factor : factors) {
      product *= factor;
    }
    return product;
  }

  /**
   * Method to find the sum of squares of factors
   * 
   * @param factors array of factors
   * @return sum of squares of all factors
   */
  public static long findSumOfSquares(int[] factors) {
    long sumOfSquares = 0;
    for (int factor : factors) {
      sumOfSquares += Math.pow(factor, 2);
    }
    return sumOfSquares;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter a number: ");
    int number = scanner.nextInt();

    // Find factors
    int[] factors = findFactors(number);

    // Display factors
    System.out.print("\nFactors of " + number + ": ");
    for (int factor : factors) {
      System.out.print(factor + " ");
    }
    System.out.println();

    // Calculate and display results
    long sum = findSumOfFactors(factors);
    long product = findProductOfFactors(factors);
    long sumOfSquares = findSumOfSquares(factors);

    System.out.println("\nResults:");
    System.out.println("Sum of factors: " + sum);
    System.out.println("Product of factors: " + product);
    System.out.println("Sum of squares of factors: " + sumOfSquares);

    scanner.close();
  }
}
