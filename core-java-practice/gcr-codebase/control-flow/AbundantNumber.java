import java.util.Scanner;

public class AbundantNumber {
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

    // Calculate sum of divisors (excluding the number itself)
    int sum = 0;
    for (int i = 1; i < number; i++) {
      if (number % i == 0) {
        sum += i;
      }
    }

    // Check if Abundant number
    if (sum > number) {
      System.out.println(number + " is an Abundant Number");
      System.out.println("Sum of divisors: " + sum + " > " + number);
    } else {
      System.out.println(number + " is Not an Abundant Number");
      System.out.println("Sum of divisors: " + sum + " <= " + number);
    }
  }
}
