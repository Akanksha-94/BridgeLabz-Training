import java.util.Scanner;

public class BMICalculator {

  /**
   * Method to calculate BMI for each person and populate the array
   * BMI = weight / (height * height) where height is in meters
   * 
   * @param data 2D array with weight (kg), height (cm), and BMI
   * @return the same array with BMI values calculated
   */
  public static double[][] calculateBMI(double[][] data) {
    for (int i = 0; i < data.length; i++) {
      double weight = data[i][0];
      double heightCm = data[i][1];
      double heightM = heightCm / 100; // Convert cm to meters

      // BMI = weight / (height^2)
      double bmi = weight / (heightM * heightM);
      data[i][2] = bmi;
    }
    return data;
  }

  /**
   * Method to determine BMI status
   * Underweight: BMI < 18.5
   * Normal: 18.5 <= BMI < 25
   * Overweight: 25 <= BMI < 30
   * Obese: BMI >= 30
   * 
   * @param bmi the BMI value
   * @return the BMI status as a string
   */
  public static String getBMIStatus(double bmi) {
    if (bmi < 18.5) {
      return "Underweight";
    } else if (bmi < 25) {
      return "Normal";
    } else if (bmi < 30) {
      return "Overweight";
    } else {
      return "Obese";
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 2D array: 10 rows (team members), 3 columns (weight, height, BMI)
    double[][] teamData = new double[10][3];

    System.out.println("Enter weight and height information for 10 team members:");

    // Input weight and height
    for (int i = 0; i < 10; i++) {
      System.out.println("\nMember " + (i + 1) + ":");
      System.out.print("  Enter weight (in kg): ");
      teamData[i][0] = scanner.nextDouble();

      System.out.print("  Enter height (in cm): ");
      teamData[i][1] = scanner.nextDouble();
    }

    // Calculate BMI for all members
    teamData = calculateBMI(teamData);

    // Display results
    System.out.println("\n========================================");
    System.out.println("         BMI ANALYSIS REPORT");
    System.out.println("========================================");
    System.out.printf("%-8s %-10s %-10s %-10s %-15s%n",
        "Member", "Weight(kg)", "Height(cm)", "BMI", "Status");
    System.out.println("----------------------------------------");

    for (int i = 0; i < 10; i++) {
      double weight = teamData[i][0];
      double height = teamData[i][1];
      double bmi = teamData[i][2];
      String status = getBMIStatus(bmi);

      System.out.printf("%-8d %-10.2f %-10.2f %-10.2f %-15s%n",
          (i + 1), weight, height, bmi, status);
    }

    System.out.println("========================================");

    scanner.close();
  }
}
