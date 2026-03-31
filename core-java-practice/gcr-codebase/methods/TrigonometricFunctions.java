import java.util.Scanner;

public class TrigonometricFunctions {

  /**
   * Method to calculate various trigonometric functions
   * 
   * @param angle the angle in degrees
   * @return an array where [0] is sine, [1] is cosine, [2] is tangent
   */
  public double[] calculateTrigonometricFunctions(double angle) {
    // Convert degrees to radians
    double radians = Math.toRadians(angle);

    // Calculate trigonometric functions
    double sine = Math.sin(radians);
    double cosine = Math.cos(radians);
    double tangent = Math.tan(radians);

    return new double[] { sine, cosine, tangent };
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TrigonometricFunctions trigFunctions = new TrigonometricFunctions();

    // Get user input
    System.out.print("Enter angle in degrees: ");
    double angle = scanner.nextDouble();

    // Calculate trigonometric functions
    double[] result = trigFunctions.calculateTrigonometricFunctions(angle);

    // Display result
    System.out.println("\nTrigonometric functions for " + angle + " degrees:");
    System.out.println("Sine: " + String.format("%.4f", result[0]));
    System.out.println("Cosine: " + String.format("%.4f", result[1]));
    System.out.println("Tangent: " + String.format("%.4f", result[2]));

    scanner.close();
  }
}
