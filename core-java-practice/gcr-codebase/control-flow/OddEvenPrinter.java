import java.util.Scanner;

public class OddEvenPrinter {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int number = sc.nextInt();

    // Check for Natural Number
    if (number <= 0) {
      System.out.println("Please enter a positive natural number!");
      return;
    }

    System.out.println("Numbers from 1 to " + number + ":");
    for (int i = 1; i <= number; i++) {
      if (i % 2 == 0) {
        System.out.println(i + " is even");
      } else {
        System.out.println(i + " is odd");
      }
    }
  }
}
