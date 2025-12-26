import java.util.Scanner;

public class EuclideanDistance {
  public static double distance(double x1, double y1, double x2, double y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public static double[] lineEquation(double x1, double y1, double x2, double y2) {
    double m, b;
    if (x1 == x2) {
      m = Double.NaN;
      b = Double.NaN; // vertical line
    } else {
      m = (y2 - y1) / (x2 - x1);
      b = y1 - m * x1;
    }
    return new double[] { m, b };
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("x1: ");
    double x1 = sc.nextDouble();
    System.out.print("y1: ");
    double y1 = sc.nextDouble();
    System.out.print("x2: ");
    double x2 = sc.nextDouble();
    System.out.print("y2: ");
    double y2 = sc.nextDouble();
    System.out.printf("Distance: %.4f\n", distance(x1, y1, x2, y2));
    double[] eq = lineEquation(x1, y1, x2, y2);
    if (Double.isNaN(eq[0]))
      System.out.println("Line is vertical (x = " + x1 + ")");
    else
      System.out.printf("Equation: y = %.4f*x + %.4f\n", eq[0], eq[1]);
    
  }
}
