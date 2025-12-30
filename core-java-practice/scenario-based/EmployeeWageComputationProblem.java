import java.util.Scanner;

public class EmployeeWagerComputation {

  private static final int WAGE_PER_HOUR = 20;
  private static final int FULL_TIME_HOUR = 8;
  private static final int PART_TIME_HOUR = 8;
  private static final int WORKING_DAYS = 20;
  private static final int MAX_HOURS = 100;

  public static void main(String[] args) {
    System.out.println("Welcom to Emplyee Wage Computation");

    int dailyWage = calculateDailyWage();
    System.out.println("Daily Wage: " + dailyWage);

    System.out.println("Employee type: ");
    System.out.println("1. part time ");
    System.out.println("2. full time ");

    Scanner sc = new Scanner(System.in);
    int employeeType = sc.nextInt();

    int hours = getHours(employeeType);
    int wage = calculateWage(hours);
    System.out.println("Wage for selected employee type: " + wage);

    int monthlyWages = calculateMonthlyWage();
    System.out.println("Monthly wages:" + monthlyWages);

    int wageWithLimits=calculateWageUntilLimit();
    System.out.println("wages until limit:" + wageWithLimits);


  }

  private static boolean isEmployeePresent() {

    int attendance = (int) (Math.random() * 2);
    return attendance ==  1;
  }

  static int calculateDailyWage() {

    if (isEmployeePresent()) {
      return WAGE_PER_HOUR *  FULL_TIME_HOUR;
    }
    return 0;
  }

  static int calculateWage(int hours) {
    return WAGE_PER_HOUR * hours;
  }

  static int getHours(int empType) {
    switch (empType) {
      case 1:
        return FULL_TIME_HOUR;
      case 2:
        return PART_TIME_HOUR;
      default:
        return 0;
    }
  }

  static int calculateMonthlyWage() {
    int totalWage = 0;
    for (int day = 1; day <= WORKING_DAYS; day++) {
      totalWage += calculateDailyWage();
    }
    return totalWage;
  }

  static int calculateWageUntilLimit() {
    int totalWage = 0;
    int totalHours = 0;
    int days = 0;

    while (totalHours < MAX_HOURS && days < WORKING_DAYS) {
      if (isEmployeePresent()) {
        totalHours += FULL_TIME_HOUR;
        totalWage += WAGE_PER_HOUR * FULL_TIME_HOUR;
      }
      days++;
    }

    return totalWage;
  }

}