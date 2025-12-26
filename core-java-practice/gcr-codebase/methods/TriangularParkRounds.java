import java.util.Scanner;

public class TriangularParkRounds {

  /**
   * Method to calculate the number of rounds needed to complete a 5km run
   * in a triangular park
   * 
   * @param side1 first side of triangle in meters
   * @param side2 second side of triangle in meters
   * @param side3 third side of triangle in meters
   * @return the number of rounds needed to complete 5km (5000 meters)
   */
  public static double calculateRounds(double side1, double side2, double side3) {
    double perimeter = side1 + side2 + side3;
    double distanceInMeters = 5000; // 5 km = 5000 meters
    return distanceInMeters / perimeter;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter first side of triangle (in meters): ");
    double side1 = scanner.nextDouble();

    System.out.print("Enter second side of triangle (in meters): ");
    double side2 = scanner.nextDouble();

    System.out.print("Enter third side of triangle (in meters): ");
    double side3 = scanner.nextDouble();

    // Calculate number of rounds
    double rounds = calculateRounds(side1, side2, side3);

    // Display result
    double perimeter = side1 + side2 + side3;
    System.out.println("Perimeter of the triangular park: " + perimeter + " meters");
    System.out.println("Number of rounds to complete 5km run: " + String.format("%.2f", rounds) + " rounds");

    scanner.close();
  }
}
