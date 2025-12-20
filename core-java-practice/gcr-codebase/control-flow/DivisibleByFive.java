import java.util.Scanner;

public class DivisibleByFive {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int num = sc.nextInt();

    if (num <= 0) {
      System.out.println("Please enter a positive integer.");
    } else {

      if (num % 5 == 0) {
        System.out.println("Is the number " + num + " divisible by 5? YES");
      } else {
        System.out.println("Is the num " + num + " divisible by 5? NO");
      }
    }

  }
}
