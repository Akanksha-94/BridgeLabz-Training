import java.util.Random;
import java.util.Locale;

public class ZaraBonus {
  public static double[][] generateEmployees(int n) {
    Random r = new Random();
    double[][] data = new double[n][2]; // [salary, years]
    for (int i = 0; i < n; i++) {
      int salary = 10000 + r.nextInt(90000); // 5-digit
      int years = r.nextInt(16); // 0..15
      data[i][0] = salary;
      data[i][1] = years;
    }
    return data;
  }

  public static double[][] calcNewSalaryAndBonus(double[][] data) {
    int n = data.length;
    double[][] out = new double[n][2]; // [newSalary, bonusAmount]
    for (int i = 0; i < n; i++) {
      double salary = data[i][0];
      double years = data[i][1];
      double pct = (years > 5) ? 0.05 : 0.02;
      double bonus = salary * pct;
      double newSalary = salary + bonus;
      out[i][0] = newSalary;
      out[i][1] = bonus;
    }
    return out;
  }

  public static void printSummary(double[][] oldData, double[][] newData) {
    double sumOld = 0, sumNew = 0, sumBonus = 0;
    System.out.printf(Locale.US, "%10s %8s %12s %12s\n", "Salary", "Years", "NewSalary", "Bonus");
    for (int i = 0; i < oldData.length; i++) {
      double s = oldData[i][0];
      double y = oldData[i][1];
      double ns = newData[i][0];
      double b = newData[i][1];
      System.out.printf(Locale.US, "%10.0f %8.0f %12.2f %12.2f\n", s, y, ns, b);
      sumOld += s;
      sumNew += ns;
      sumBonus += b;
    }
    System.out.println("------------------------------------------------");
    System.out.printf(Locale.US, "Totals: %10.0f     %12.2f %12.2f\n", sumOld, sumNew, sumBonus);
  }

  public static void main(String[] args) {
    double[][] oldData = generateEmployees(10);
    double[][] newData = calcNewSalaryAndBonus(oldData);
    printSummary(oldData, newData);
  }
}
