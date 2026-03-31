import java.util.Scanner;

public class CalendarDisplay {
  static String monthName(int m) {
    String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December" };
    if (m < 1 || m > 12)
      return "";
    return months[m - 1];
  }

  static boolean isLeap(int y) {
    return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
  }

  static int daysInMonth(int m, int y) {
    int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    if (m == 2 && isLeap(y))
      return 29;
    return days[m - 1];
  }

  // Gregorian algorithm to find day of week for given date
  // returns 0=Sunday,1=Monday,...6=Saturday
  static int firstDayOfMonth(int month, int year) {
    int d = 1; // first day of month
    int y0 = year - (14 - month) / 12;
    int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
    int m0 = month + 12 * ((14 - month) / 12) - 2;
    int d0 = (d + x + (31 * m0) / 12) % 7;
    return d0;
  }

  static void printCalendar(int month, int year) {
    String mName = monthName(month);
    System.out.printf("     %s %d\n", mName, year);
    System.out.println("Su Mo Tu We Th Fr Sa");
    int first = firstDayOfMonth(month, year);
    int days = daysInMonth(month, year);
    // print initial indentation
    for (int i = 0; i < first; i++)
      System.out.print("   ");
    for (int day = 1; day <= days; day++) {
      System.out.printf("%2d", day);
      int pos = (first + day) % 7;
      if (pos == 0)
        System.out.println();
      else
        System.out.print(" ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter month and year (e.g. 7 2005):");
    try {
      int month = sc.nextInt();
      int year = sc.nextInt();
      if (month < 1 || month > 12) {
        System.out.println("Invalid month.");
      } else {
        printCalendar(month, year);
      }
    } catch (Exception e) {
      System.out.println("Invalid input.");
    } finally {
    
    }
  }
}
