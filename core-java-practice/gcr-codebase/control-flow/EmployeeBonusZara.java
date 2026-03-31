import java.util.Scanner;

public class EmployeeBonusZara {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter salary: ");
    if (!sc.hasNextDouble())
      return;
    double salary = sc.nextDouble();
    System.out.print("Enter years of service: ");
    if (!sc.hasNextInt())
      return;
    int years = sc.nextInt();
    double bonus = 0.0;
    if (years > 5)
      bonus = salary * 0.05;
    System.out.printf("Bonus amount: %.2f%n", bonus);
  }
}
