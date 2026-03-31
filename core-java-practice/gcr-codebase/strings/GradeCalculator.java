import java.util.Random;
import java.util.Scanner;

public class GradeCalculator {
  // Generate random two-digit PCM scores for n students
  static int[][] generateScores(int n) {
    Random r = new Random();
    int[][] scores = new int[n][3];
    for (int i = 0; i < n; i++) {
      scores[i][0] = 10 + r.nextInt(90); // Physics
      scores[i][1] = 10 + r.nextInt(90); // Chemistry
      scores[i][2] = 10 + r.nextInt(90); // Math
    }
    return scores;
  }

  // Calculate total, average, percentage for each student. Returns double[n][3]
  // => {total, average, percentage}
  static double[][] calcTotalsAvgPercent(int[][] scores) {
    int n = scores.length;
    double[][] result = new double[n][3];
    for (int i = 0; i < n; i++) {
      int total = scores[i][0] + scores[i][1] + scores[i][2];
      double average = total / 3.0;
      double percentage = (total / 300.0) * 100.0;
      // round to 2 decimals
      average = Math.round(average * 100.0) / 100.0;
      percentage = Math.round(percentage * 100.0) / 100.0;
      result[i][0] = total;
      result[i][1] = average;
      result[i][2] = percentage;
    }
    return result;
  }

  // Determine grade from percentage per table
  static String gradeFromPercentage(double p) {
    if (p >= 80.0)
      return "A";
    if (p >= 70.0)
      return "B";
    if (p >= 60.0)
      return "C";
    if (p >= 50.0)
      return "D";
    if (p >= 40.0)
      return "E";
    return "R"; // remedial
  }

  static String[] calcGrades(double[][] tpa) {
    String[] grades = new String[tpa.length];
    for (int i = 0; i < tpa.length; i++)
      grades[i] = gradeFromPercentage(tpa[i][2]);
    return grades;
  }

  static void printScorecard(int[][] scores, double[][] tpa, String[] grades) {
    System.out.println("--------------------------------------------------------------------");
    System.out.printf("%5s | %8s %8s %8s | %6s %8s %6s\n", "Roll", "Physics", "Chemistry", "Math", "Total", "Average",
        "%");
    System.out.println("--------------------------------------------------------------------");
    for (int i = 0; i < scores.length; i++) {
      System.out.printf("%5d | %8d %8d %8d | %6.0f %8.2f %6.2f \tGrade: %s\n",
          i + 1,
          scores[i][0], scores[i][1], scores[i][2],
          tpa[i][0], tpa[i][1], tpa[i][2], grades[i]);
    }
    System.out.println("--------------------------------------------------------------------");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of students:");
    int n = 0;
    try {
      n = Integer.parseInt(sc.nextLine().trim());
      if (n <= 0) {
        System.out.println("Number of students must be positive.");
     
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid number. Exiting.");
   
      return;
    }

    int[][] scores = generateScores(n);
    double[][] tpa = calcTotalsAvgPercent(scores);
    String[] grades = calcGrades(tpa);
    printScorecard(scores, tpa, grades);
    
  }
}
