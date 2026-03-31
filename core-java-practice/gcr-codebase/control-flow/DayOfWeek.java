import java.util.Scanner;

public class DayOfWeek {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Taking input from user
    System.out.print("Enter month (1-12): ");
    int m = scanner.nextInt();

    System.out.print("Enter day (1-31): ");
    int d = scanner.nextInt();

    System.out.print("Enter year: ");
    int y = scanner.nextInt();

    // Validate input
    if (m < 1 || m > 12 || d < 1 || d > 31) {
      System.out.println("Invalid date. Please enter valid month and day.");
      scanner.close();
      return;
    }

    // Calculate day of week using Gregorian calendar formulas
    int y0 = y - (14 - m) / 12;
    int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
    int m0 = m + 12 * ((14 - m) / 12) - 2;
    int d0 = (d + x + 31 * m0 / 12) % 7;

    // Array to store day names
    String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday" };

    System.out.println("\nDate: " + m + "/" + d + "/" + y);
    System.out.println("Day of the week: " + days[d0]);
    System.out.println("Day code: " + d0);

    scanner.close();
  }
}
