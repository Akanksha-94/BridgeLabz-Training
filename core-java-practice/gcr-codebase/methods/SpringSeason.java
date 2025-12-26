import java.util.Scanner;

public class SpringSeason {

  /**
   * Method to check whether a given date is in Spring Season
   * Spring Season is from March 20 to June 20
   * 
   * @param month the month (1-12)
   * @param day   the day of month
   * @return true if it's spring season, false otherwise
   */
  public static boolean isSpringSeason(int month, int day) {
    // Spring: March 20 to June 20
    if (month < 3 || month > 6) {
      return false;
    }

    if (month == 3) {
      return day >= 20;
    } else if (month == 6) {
      return day <= 20;
    } else {
      // April and May are completely in spring
      return true;
    }
  }

  public static void main(String[] args) {
    // Check if command line arguments are provided
    if (args.length < 2) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter month (1-12): ");
      int month = scanner.nextInt();

      System.out.print("Enter day: ");
      int day = scanner.nextInt();

      scanner.close();

      checkAndPrint(month, day);
    } else {
      int month = Integer.parseInt(args[0]);
      int day = Integer.parseInt(args[1]);

      checkAndPrint(month, day);
    }
  }

  private static void checkAndPrint(int month, int day) {
    if (isSpringSeason(month, day)) {
      System.out.println("Its a Spring Season");
    } else {
      System.out.println("Not a Spring Season");
    }
  }
}
