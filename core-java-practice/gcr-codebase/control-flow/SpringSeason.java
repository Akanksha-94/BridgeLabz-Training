import java.util.Scanner;

public class SpringSeason {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter month number (1-12): ");
    int month = sc.nextInt();
    System.out.print("Enter day number (1-31): ");
    int day = sc.nextInt();

    // Spring Season is from March 20 to June 20
    if ((month == 3 && day >= 20) || (month == 4) || (month == 5) || (month == 6 && day <= 20)) {
      System.out.println("Its a Spring Season");
    } else {
      System.out.println("Not a Spring Season");
    }
  }
}
