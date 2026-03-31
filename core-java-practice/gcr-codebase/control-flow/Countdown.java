import java.util.Scanner;

public class Countdown {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the countdown value for rocket launch: ");
    int counter = sc.nextInt();

    System.out.println("Countdown started:");
    while (counter >= 1) {
      System.out.println(counter);
      counter--;
    }
    System.out.println("Blast off!");
  }
}
