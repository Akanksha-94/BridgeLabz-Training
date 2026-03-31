import java.util.Scanner;

public class HarshadNumber {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter a positive integer: ");
    if (!sc.hasNextInt()) {
      System.out.println("Invalid input.");
      return;
    }
    int number = sc.nextInt();

    if (number <= 0) {
      System.out.println("Please enter a positive integer.");
      return;
    }

    // Calculate sum of digits
    int sum = 0;
    int temp = number;
    while (temp > 0) {
      sum += temp % 10;
      temp /= 10;
    }

    // Check if Harshad number
    if (number % sum == 0) {
      System.out.println(number + " is a Harshad Number");
    } else {
      System.out.println(number + " is Not a Harshad Number");
    }
  }
}
