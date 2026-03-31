import java.util.Scanner;

public class QuotientAndRemainder {

  /**
   * Method to find the quotient and remainder
   * 
   * @param number  the dividend
   * @param divisor the divisor
   * @return an array where [0] is quotient and [1] is remainder
   */
  public static int[] findRemainderAndQuotient(int number, int divisor) {
    int quotient = number / divisor;
    int remainder = number % divisor;

    return new int[] { quotient, remainder };
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter the dividend (number): ");
    int number = scanner.nextInt();

    System.out.print("Enter the divisor: ");
    int divisor = scanner.nextInt();

    // Find quotient and remainder
    int[] result = findRemainderAndQuotient(number, divisor);

    // Display result
    System.out.println("When " + number + " is divided by " + divisor + ":");
    System.out.println("Quotient: " + result[0]);
    System.out.println("Remainder: " + result[1]);

    scanner.close();
  }
}
