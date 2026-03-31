import java.util.Scanner;

public class NumberSignCheck {

  /**
   * Method to check whether a number is positive, negative, or zero
   * 
   * @param number the number to check
   * @return -1 for negative, 1 for positive, 0 for zero
   */
  public static int checkSign(int number) {
    if (number < 0) {
      return -1;
    } else if (number > 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter a number: ");
    int number = scanner.nextInt();

    // Check sign of number
    int sign = checkSign(number);

    // Display result
    String result;
    if (sign == -1) {
      result = "negative";
    } else if (sign == 1) {
      result = "positive";
    } else {
      result = "zero";
    }

    System.out.println("The number " + number + " is " + result);

    scanner.close();
  }
}
