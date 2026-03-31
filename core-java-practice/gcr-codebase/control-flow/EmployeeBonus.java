import java.util.Scanner;

public class EmployeeBonus {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter salary: ");
    double salary = sc.nextDouble();

    System.out.print("Enter year of service: ");
    int yearsOfService = sc.nextInt();

    double bonus = 0.0;

    // If year of service is more than 5 years, give 5% bonus
    if (yearsOfService > 5) {
      bonus = salary * 0.05;
      System.out.println("Employee is eligible for bonus!");
      System.out.println("Bonus amount (5%): " + bonus);
    } else {
      System.out.println("Employee is not eligible for bonus!");
    }
  }
}
