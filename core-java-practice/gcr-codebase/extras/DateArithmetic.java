import java.util.Scanner;
import java.time.LocalDate;

public class DateArithmetic {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
     System.out.print("Enter date (yyyy-mm-dd): ");
        String inputDate = sc.nextLine();

        // Convert input string to LocalDate
        LocalDate date = LocalDate.parse(inputDate);

        // Add 7 days, 1 month, 2 years
        LocalDate updatedDate = date.plusDays(7)
                                    .plusMonths(1)
                                    .plusYears(2);

        // Subtract 3 weeks
        LocalDate finalDate = updatedDate.minusWeeks(3);

        System.out.println("Original Date: " + date);
        System.out.println("After Adding 7 days, 1 month, 2 years: " + updatedDate);
        System.out.println("After Subtracting 3 weeks: " + finalDate);

  }
}
