import java.util.Scanner;

public class ChocolateDistribution {

  /**
   * Method to find the number of chocolates each child gets and remaining
   * chocolates
   * 
   * @param numberOfChocolates total number of chocolates
   * @param numberOfChildren   number of children
   * @return an array where [0] is chocolates per child and [1] is remaining
   *         chocolates
   */
  public static int[] distributeChocolates(int numberOfChocolates, int numberOfChildren) {
    int chocolatesPerChild = numberOfChocolates / numberOfChildren;
    int remainingChocolates = numberOfChocolates % numberOfChildren;

    return new int[] { chocolatesPerChild, remainingChocolates };
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter the number of chocolates: ");
    int numberOfChocolates = scanner.nextInt();

    System.out.print("Enter the number of children: ");
    int numberOfChildren = scanner.nextInt();

    // Distribute chocolates
    int[] result = distributeChocolates(numberOfChocolates, numberOfChildren);

    // Display result
    System.out.println("Chocolates to distribute: " + numberOfChocolates);
    System.out.println("Number of children: " + numberOfChildren);
    System.out.println("Each child will get: " + result[0] + " chocolates");
    System.out.println("Remaining chocolates: " + result[1]);

    scanner.close();
  }
}
