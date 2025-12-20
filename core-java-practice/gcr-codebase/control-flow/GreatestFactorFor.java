import java.util.Scanner;

public class GreatestFactorFor {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter an integer (>1): ");
    if (!sc.hasNextInt())
      return;
    int number = sc.nextInt();
    if (number <= 1) {
      System.out.println("No greatest factor beside itself.");
      return;
    }
    int greatestFactor = 1;
    for (int i = number - 1; i >= 1; i--) {
      if (number % i == 0) {
        greatestFactor = i;
        break;
      }
    }
    System.out.println("Greatest factor beside itself: " + greatestFactor);
  }
}
