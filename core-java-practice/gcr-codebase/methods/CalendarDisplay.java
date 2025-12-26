import java.util.Scanner;

public class CalendarDisplay {
  private static final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
      "September", "October", "November", "December" };

  public static boolean isLeap(int y) {
    return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
  }

  // returns number of days in month m (1-12)
  public static int daysInMonth(int m, int y) {
    switch (m) {
      case 2:
        return isLeap(y) ? 29 : 28;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      default:
        return 31;
    }
  }

  // Gregorian algorithm to get day of week for a date. 0=Sunday..6=Saturday
  public static int firstDayOfMonth(int month, int year) {
    int m = month;
    int y = year;
    int d = 1;
    int y0 = y - (14 - m) / 12;
    int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
    int m0 = m + 12 * ((14 - m) / 12) - 2;
    int d0 = (d + x + (31 * m0) / 12) % 7;
    return d0;
  }

  public static void printCalendar(int month, int year) {
    System.out.println("    " + MONTHS[month - 1] + " " + year);
    System.out.println("Sun Mon Tue Wed Thu Fri Sat");
    int first = firstDayOfMonth(month, year);
    int days = daysInMonth(month, year);
    for (int i = 0; i < first; i++)
      System.out.print("    ");
    for (int d = 1; d <= days; d++) {
      System.out.printf("%3d ", d);
      if ((d + first) % 7 == 0)
        System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter month (1-12): ");
    int m = sc.nextInt();
    System.out.print("Enter year (e.g. 2005): ");
    int y = sc.nextInt();
    printCalendar(m, y);
 
  }
}
