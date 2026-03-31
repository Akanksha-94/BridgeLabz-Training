import java.util.Scanner;

public class TemperatureConverter {

  // Convert Fahrenheit to Celsius
  static double fahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - 32) * 5 / 9;
  }

  // Convert Celsius to Fahrenheit
  static double celsiusToFahrenheit(double celsius) {
    return (celsius * 9 / 5) + 32;
  }

  // Take input and choice from user
  static void runConverter() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Temperature Converter ===");
    System.out.println("Choose conversion type:");
    System.out.println("1. Fahrenheit to Celsius");
    System.out.println("2. Celsius to Fahrenheit");
    System.out.print("Enter your choice (1 or 2): ");

    int choice = scanner.nextInt();

    switch (choice) {
      case 1:
        System.out.print("Enter temperature in Fahrenheit: ");
        double fahrenheit = scanner.nextDouble();
        double celsius = fahrenheitToCelsius(fahrenheit);
        System.out.println("\n--- Result ---");
        System.out.println(fahrenheit + "°F = " + String.format("%.2f", celsius) + "°C");
        break;

      case 2:
        System.out.print("Enter temperature in Celsius: ");
        double celsiusTemp = scanner.nextDouble();
        double fahrenheitTemp = celsiusToFahrenheit(celsiusTemp);
        System.out.println("\n--- Result ---");
        System.out.println(celsiusTemp + "°C = " + String.format("%.2f", fahrenheitTemp) + "°F");
        break;

      default:
        System.out.println("Invalid choice! Please enter 1 or 2.");
    }
  }

  public static void main(String[] args) {
    runConverter();
  }
}
