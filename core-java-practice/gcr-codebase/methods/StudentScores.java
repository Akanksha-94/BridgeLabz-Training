import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class StudentScores {
  private static final String[] GRADES = { "A", "B", "C", "D", "E", "R" };

  public static String gradeForPercentage(double p) {
    if (p >= 80)
      return "A";
    if (p >= 70)
      return "B";
    if (p >= 60)
      return "C";
    if (p >= 50)
      return "D";
    if (p >= 40)
      return "E";
    return "R";
  }

  public static int[][] generatePCM(int n) {
    Random r = new Random();
    int[][] arr = new int[n][3];
    for (int i = 0; i < n; i++) {
      arr[i][0] = r.nextInt(101); // Physics 0..100
      arr[i][1] = r.nextInt(101); // Chemistry
      arr[i][2] = r.nextInt(101); // Maths
    }
    return arr;
  }

  public static double[][] totalsAndPercentages(int[][] pcm) {
    int n = pcm.length;
    double[][] out = new double[n][4]; // total, avg, percent, placeholder
    for (int i = 0; i < n; i++) {
      int total = pcm[i][0] + pcm[i][1] + pcm[i][2];
      double avg = total / 3.0;
      double percent = total / 300.0 * 100.0;
      out[i][0] = total;
      out[i][1] = avg;
      out[i][2] = percent;
    }
    return out;
  }

  public static void printScorecard(int[][] pcm, double[][] stats) {
    System.out.printf(Locale.US, "%5s %8s %8s %8s %8s %8s %8s\n", "ID", "Phy", "Che", "Math", "Total", "%", "Grade");
    for (int i = 0; i < pcm.length; i++) {
      int id = i + 1;
      int p = pcm[i][0], c = pcm[i][1], m = pcm[i][2];
      int total = (int) stats[i][0];
      double percent = stats[i][2];
      String grade = gradeForPercentage(percent);
      System.out.printf(Locale.US, "%3d %8d %8d %8d %8d %7.2f %8s\n", id, p, c, m, total, percent, grade);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Number of students: ");
    int n = sc.nextInt();
    int[][] pcm = generatePCM(n);
    double[][] stats = totalsAndPercentages(pcm);
    printScorecard(pcm, stats);
   
  }
}
