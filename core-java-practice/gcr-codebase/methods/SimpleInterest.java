import java.util.Scanner;

public class SimpleInterest {

  /**
   * Method to calculate simple interest
   * 
   * @param principal the principal amount
   * @param rate      the rate of interest
   * @param time      the time period
   * @return the calculated simple interest
   */
  public static double calculateSimpleInterest(double principal, double rate, double time) {
    return (principal * rate * time) / 100;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter Principal amount: ");
    double principal = scanner.nextDouble();

    System.out.print("Enter Rate of Interest: ");
    double rate = scanner.nextDouble();

    System.out.print("Enter Time period (in years): ");
    double time = scanner.nextDouble();

    // Calculate simple interest
    double simpleInterest = calculateSimpleInterest(principal, rate, time);

    // Display result
    System.out.println("The Simple Interest is " + simpleInterest +
        " for Principal " + principal +
        ", Rate of Interest " + rate +
        " and Time " + time);

    scanner.close();
  }
}
