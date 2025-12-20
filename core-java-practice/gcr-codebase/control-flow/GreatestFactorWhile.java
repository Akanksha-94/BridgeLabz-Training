import java.util.Scanner;

public class GreatestFactorWhile {
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
    int counter = number - 1;
    while (counter >= 1) {
      if (number % counter == 0) {
        greatestFactor = counter;
        break;
      }
      counter--;
    }
    System.out.println("Greatest factor beside itself: " + greatestFactor);
  }
}
