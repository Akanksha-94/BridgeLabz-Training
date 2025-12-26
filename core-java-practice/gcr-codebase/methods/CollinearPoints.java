import java.util.Scanner;

public class CollinearPoints {
  public static boolean collinearBySlope(double x1, double y1, double x2, double y2, double x3, double y3) {
    double slope12 = (x2 - x1) == 0 ? Double.POSITIVE_INFINITY : (y2 - y1) / (x2 - x1);
    double slope23 = (x3 - x2) == 0 ? Double.POSITIVE_INFINITY : (y3 - y2) / (x3 - x2);
    double slope13 = (x3 - x1) == 0 ? Double.POSITIVE_INFINITY : (y3 - y1) / (x3 - x1);
    return Double.compare(slope12, slope23) == 0 && Double.compare(slope23, slope13) == 0;
  }

  public static boolean collinearByArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    double area = 0.5 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    return Math.abs(area) < 1e-9;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter coordinates for point A (x y):");
    double x1 = sc.nextDouble(), y1 = sc.nextDouble();
    System.out.println("Enter coordinates for point B (x y):");
    double x2 = sc.nextDouble(), y2 = sc.nextDouble();
    System.out.println("Enter coordinates for point C (x y):");
    double x3 = sc.nextDouble(), y3 = sc.nextDouble();
    boolean slopeCheck = collinearBySlope(x1, y1, x2, y2, x3, y3);
    boolean areaCheck = collinearByArea(x1, y1, x2, y2, x3, y3);
    System.out.println("Collinear by slope test? " + slopeCheck);
    System.out.println("Collinear by area test? " + areaCheck);
  
  }
}
