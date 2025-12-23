import java.util.Scanner;

public class BMICalculator {
  // Compute BMI and status for one person
  static String[] computeBMI(double weightKg, double heightCm) {
    double heightM = heightCm / 100.0;
    double bmi = weightKg / (heightM * heightM);
    bmi = Math.round(bmi * 100.0) / 100.0; // 2 decimals
    String status;
    if (bmi < 18.5)
      status = "Underweight";
    else if (bmi < 25.0)
      status = "Normal";
    else if (bmi < 30.0)
      status = "Overweight";
    else
      status = "Obese";
    return new String[] { String.valueOf((int) heightCm), String.valueOf((int) weightKg), String.valueOf(bmi), status };
  }

  static String[][] computeAll(int[][] hw) {
    int n = hw.length;
    String[][] out = new String[n][4];
    for (int i = 0; i < n; i++) {
      int weight = hw[i][0];
      int height = hw[i][1];
      out[i] = computeBMI(weight, height);
    }
    return out;
  }

  static void printTable(String[][] table) {
    System.out.println("Person\tHeight(cm)\tWeight(kg)\tBMI\tStatus");
    for (int i = 0; i < table.length; i++) {
      System.out.printf("%d\t%s\t\t%s\t\t%s\t%s\n", i + 1, table[i][0], table[i][1], table[i][2], table[i][3]);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = 10;
    int[][] hw = new int[n][2];
    System.out.println("Enter weight(kg) and height(cm) for 10 persons (separated by space) each line:");
    for (int i = 0; i < n; i++) {
      System.out.printf("Person %d: ", i + 1);
      try {
        String line = sc.nextLine().trim();
        String[] parts = line.split("\\s+");
        if (parts.length < 2) {
          i--;
          continue;
        }
        hw[i][0] = Integer.parseInt(parts[0]);
        hw[i][1] = Integer.parseInt(parts[1]);
      } catch (Exception e) {
        System.out.println("Invalid input, please re-enter.");
        i--;
      }
    }
    String[][] table = computeAll(hw);
    printTable(table);
    
  }
}
