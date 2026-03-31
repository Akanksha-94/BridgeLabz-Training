import java.util.Scanner;

public class SumOfNaturalNumbersFor {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a natural number: ");
    int n = sc.nextInt();

    // Check if it's a natural number
    if (n <= 0) {
      System.out.println("Please enter a positive natural number!");
      return;
    }

    // Calculate sum using for loop
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += i;
    }

    // Calculate sum using formula n*(n+1)/2
    int formulaSum = n * (n + 1) / 2;

    System.out.println("Sum using for loop: " + sum);
    System.out.println("Sum using formula n*(n+1)/2: " + formulaSum);

    if (sum == formulaSum) {
      System.out.println("Both results are equal. Computation is correct!");
    } else {
      System.out.println("Results do not match!");
    }
  }
}
