import java.util.Scanner;

public class LeapYear {

  /**
   * Method to check if a year is a leap year
   * Leap year rules:
   * 1. Divisible by 4 AND not divisible by 100 OR
   * 2. Divisible by 400
   * Valid for years >= 1582 (Gregorian Calendar)
   * 
   * @param year the year to check
   * @return true if leap year, false otherwise
   */
  public static boolean isLeapYear(int year) {
    // Check if year is >= 1582 (Gregorian calendar)
    if (year < 1582) {
      System.out.println("Warning: This program only works for years >= 1582 (Gregorian calendar)");
      return false;
    }

    // Leap year conditions
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter a year: ");
    int year = scanner.nextInt();

    // Check if leap year
    if (isLeapYear(year)) {
      System.out.println(year + " is a Leap Year");
    } else {
      System.out.println(year + " is not a Leap Year");
    }

    scanner.close();
  }
}
