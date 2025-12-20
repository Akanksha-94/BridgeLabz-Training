import java.util.Scanner;

public class PowerFor {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter base (integer): ");
    if (!sc.hasNextInt())
      return;
    int number = sc.nextInt();
    System.out.print("Enter power (non-negative integer): ");
    if (!sc.hasNextInt())
      return;
    int power = sc.nextInt();
    if (power < 0) {
      System.out.println("Power must be non-negative.");
      return;
    }
    long result = 1;
    for (int i = 1; i <= power; i++)
      result *= number;
    System.out.println(number + "^" + power + " = " + result);
  }
}
