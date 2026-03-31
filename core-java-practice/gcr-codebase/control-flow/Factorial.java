import java.util.Scanner;

public class Factorial {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter an integer to find its factorial: ");
    int number = sc.nextInt();

    // Check if it's a positive integer
    if (number < 0) {
      System.out.println("Please enter a positive integer!");
      return;
    }

    // Calculate factorial using while loop
    long factorial = 1;
    int counter = number;
    while (counter > 0) {
      factorial *= counter;
      counter--;
    }

    System.out.println("Factorial of " + number + " is: " + factorial);
  }
}
