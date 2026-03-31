import java.util.Scanner;

public class OddEvenRange {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a positive integer: ");
    if (!sc.hasNextInt())
      return;
    int number = sc.nextInt();
    if (number < 1) {
      System.out.println("Please enter a natural number (>=1).");
      return;
    }
    for (int i = 1; i <= number; i++) {
      if (i % 2 == 0)
        System.out.println(i + " is even");
      else
        System.out.println(i + " is odd");
    }
  }
}
