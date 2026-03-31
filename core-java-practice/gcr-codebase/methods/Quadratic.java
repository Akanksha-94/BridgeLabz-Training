import java.util.Scanner;

public class Quadratic {

  /**
   * Method to find the roots of a quadratic equation ax^2 + bx + c = 0
   * Uses the formula:
   * delta = b^2 - 4*a*c
   * If delta > 0: two roots
   * If delta = 0: one root
   * If delta < 0: no real roots (return empty array)
   * 
   * @param a coefficient of x^2
   * @param b coefficient of x
   * @param c constant term
   * @return array of roots (empty if delta < 0)
   */
  public static double[] findRoots(double a, double b, double c) {
    // Calculate discriminant (delta)
    double delta = (b * b) - (4 * a * c);

    if (delta < 0) {
      // No real roots
      System.out.println("Delta is negative. No real roots exist!");
      return new double[0];
    } else if (delta == 0) {
      // One root
      double root = -b / (2 * a);
      return new double[] { root };
    } else {
      // Two roots
      double sqrtDelta = Math.sqrt(delta);
      double root1 = (-b + sqrtDelta) / (2 * a);
      double root2 = (-b - sqrtDelta) / (2 * a);
      return new double[] { root1, root2 };
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Find the roots of quadratic equation: ax² + bx + c = 0");

    System.out.print("Enter coefficient a: ");
    double a = scanner.nextDouble();

    System.out.print("Enter coefficient b: ");
    double b = scanner.nextDouble();

    System.out.print("Enter coefficient c: ");
    double c = scanner.nextDouble();

    // Validate that a is not zero
    if (a == 0) {
      System.out.println("Error: Coefficient 'a' cannot be zero for a quadratic equation!");
      scanner.close();
      return;
    }

    // Find roots
    double[] roots = findRoots(a, b, c);

    // Display results
    System.out.println("\nEquation: " + a + "x² + " + b + "x + " + c + " = 0");

    double delta = (b * b) - (4 * a * c);
    System.out.println("Delta (Discriminant): " + delta);

    if (roots.length == 0) {
      System.out.println("No real roots exist!");
    } else if (roots.length == 1) {
      System.out.println("One real root exists:");
      System.out.println("Root: " + String.format("%.4f", roots[0]));
    } else {
      System.out.println("Two real roots exist:");
      System.out.println("Root 1: " + String.format("%.4f", roots[0]));
      System.out.println("Root 2: " + String.format("%.4f", roots[1]));
    }

    scanner.close();
  }
}
