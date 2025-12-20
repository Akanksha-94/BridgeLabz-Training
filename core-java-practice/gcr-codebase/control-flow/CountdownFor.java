import java.util.Scanner;

public class CountdownFor {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the countdown value for rocket launch: ");
    int number = sc.nextInt();

    System.out.println("Countdown started:");
    for (int counter = number; counter >= 1; counter--) {
      System.out.println(counter);
    }
    System.out.println("Blast off!");
  }
}
