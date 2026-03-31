import java.util.Scanner;

public class SmallestAndLargest {

  /**
   * Method to find the smallest and largest of three numbers
   * 
   * @param number1 the first number
   * @param number2 the second number
   * @param number3 the third number
   * @return an array where [0] is smallest and [1] is largest
   */
  public static int[] findSmallestAndLargest(int number1, int number2, int number3) {
    int smallest = Math.min(Math.min(number1, number2), number3);
    int largest = Math.max(Math.max(number1, number2), number3);

    return new int[] { smallest, largest };
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter first number: ");
    int number1 = scanner.nextInt();

    System.out.print("Enter second number: ");
    int number2 = scanner.nextInt();

    System.out.print("Enter third number: ");
    int number3 = scanner.nextInt();

    // Find smallest and largest
    int[] result = findSmallestAndLargest(number1, number2, number3);

    // Display result
    System.out.println("The smallest number is: " + result[0]);
    System.out.println("The largest number is: " + result[1]);

    scanner.close();
  }
}
