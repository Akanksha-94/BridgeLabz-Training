import java.util.Scanner;

public class FizzBuzz {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a positive integer: ");
    int n = sc.nextInt();
    if (n <= 0) {
      System.out.println("Please enter a positive integer.");
    
      return;
    }

    String[] result = new String[n];
    for (int i = 1; i <= n; i++) {
      if (i % 15 == 0)
        result[i - 1] = "FizzBuzz";
      else if (i % 3 == 0)
        result[i - 1] = "Fizz";
      else if (i % 5 == 0)
        result[i - 1] = "Buzz";
      else
        result[i - 1] = Integer.toString(i);
    }

    for (int i = 0; i < result.length; i++) {
      System.out.println("Position " + (i + 1) + " = " + result[i]);
    }
    
  }
}
