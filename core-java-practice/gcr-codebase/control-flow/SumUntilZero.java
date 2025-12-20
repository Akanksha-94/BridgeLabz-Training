import java.util.Scanner;

public class SumUntilZero {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double total = 0.0;
    double userInput;

    while (true) {
      System.out.print("Enter a number (0 to stop): ");
      userInput = sc.nextDouble();

      if (userInput == 0) {
        break;
      }

      total += userInput;
    }

    System.out.println("Total sum: " + total);
  }
}
