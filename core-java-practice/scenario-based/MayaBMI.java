import java.util.Scanner;

public class MayaBMI {
  public static void main(String[] args) {
    // Maya's BMI Fitness Tracker
    // BMI = weight(kg) / (height(m) * height(m))
    Scanner sc = new Scanner(System.in);

    System.out.println("Maya's BMI Fitness Tracker");
    System.out.print("Enter height in meters (e.g. 1.70): ");
    double height = sc.nextDouble();
    System.out.print("Enter weight in kilograms (e.g. 65.5): ");
    double weight = sc.nextDouble();

    if (height <= 0 || weight <= 0) {
      System.out.println("Invalid input: height and weight must be positive numbers.");

      return;
    }

    double bmi = weight / (height * height);
    System.out.printf("Calculated BMI: %.2f%n", bmi);

    // BMI categories
    if (bmi < 18.5) {
      System.out.println("Category: Underweight");
    } else if (bmi < 25.0) {
      System.out.println("Category: Normal");
    } else {
      System.out.println("Category: Overweight");
    }

  }
}
