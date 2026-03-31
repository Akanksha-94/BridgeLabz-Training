import java.util.Scanner;

public class ZaraBonus {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = 10;
    double[] salary = new double[N];
    double[] years = new double[N];
    double[] newSalary = new double[N];
    double[] bonus = new double[N];

    double totalBonus = 0.0;
    double totalOldSalary = 0.0;
    double totalNewSalary = 0.0;

    for (int i = 0; i < N; i++) {
      System.out.print("Enter salary for employee " + (i + 1) + ": ");
      double s = sc.nextDouble();
      System.out.print("Enter years of service for employee " + (i + 1) + ": ");
      double y = sc.nextDouble();

      if (s <= 0 || y < 0) {
        System.out.println("Invalid input. Salary must be > 0 and years >= 0. Please re-enter.");
        i--; // retry this index
        continue;
      }

      salary[i] = s;
      years[i] = y;
    }

    for (int i = 0; i < N; i++) {
      double rate = (years[i] > 5.0) ? 0.05 : 0.02;
      bonus[i] = salary[i] * rate;
      newSalary[i] = salary[i] + bonus[i];
      totalBonus += bonus[i];
      totalOldSalary += salary[i];
      totalNewSalary += newSalary[i];
    }

    System.out.println("\nEmployee details:");
    for (int i = 0; i < N; i++) {
      System.out.printf("Emp %2d: Old=%.2f, Years=%.1f, Bonus=%.2f, New=%.2f%n", i + 1, salary[i], years[i], bonus[i],
          newSalary[i]);
    }

    System.out.printf("\nTotal old salary = %.2f%n", totalOldSalary);
    System.out.printf("Total bonus payout = %.2f%n", totalBonus);
    System.out.printf("Total new salary = %.2f%n", totalNewSalary);
  
  }
}
