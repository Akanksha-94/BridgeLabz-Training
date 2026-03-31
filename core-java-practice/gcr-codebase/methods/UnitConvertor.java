import java.util.Scanner;

public class UnitConvertor {

  // Distance conversion constants
  private static final double KM_TO_MILES = 0.621371;
  private static final double MILES_TO_KM = 1.60934;
  private static final double METERS_TO_FEET = 3.28084;
  private static final double FEET_TO_METERS = 0.3048;
  private static final double YARDS_TO_FEET = 3;
  private static final double FEET_TO_YARDS = 0.333333;
  private static final double METERS_TO_INCHES = 39.3701;
  private static final double INCHES_TO_METERS = 0.0254;
  private static final double INCHES_TO_CM = 2.54;

  // Temperature and weight conversion constants
  private static final double POUNDS_TO_KG = 0.453592;
  private static final double KG_TO_POUNDS = 2.20462;
  private static final double GALLONS_TO_LITERS = 3.78541;
  private static final double LITERS_TO_GALLONS = 0.264172;

  // ===== Distance Conversions =====

  /**
   * Convert kilometers to miles
   */
  public static double convertKmToMiles(double km) {
    return km * KM_TO_MILES;
  }

  /**
   * Convert miles to kilometers
   */
  public static double convertMilesToKm(double miles) {
    return miles * MILES_TO_KM;
  }

  /**
   * Convert meters to feet
   */
  public static double convertMetersToFeet(double meters) {
    return meters * METERS_TO_FEET;
  }

  /**
   * Convert feet to meters
   */
  public static double convertFeetToMeters(double feet) {
    return feet * FEET_TO_METERS;
  }

  /**
   * Convert yards to feet
   */
  public static double convertYardsToFeet(double yards) {
    return yards * YARDS_TO_FEET;
  }

  /**
   * Convert feet to yards
   */
  public static double convertFeetToYards(double feet) {
    return feet * FEET_TO_YARDS;
  }

  /**
   * Convert meters to inches
   */
  public static double convertMetersToInches(double meters) {
    return meters * METERS_TO_INCHES;
  }

  /**
   * Convert inches to meters
   */
  public static double convertInchesToMeters(double inches) {
    return inches * INCHES_TO_METERS;
  }

  /**
   * Convert inches to centimeters
   */
  public static double convertInchesToCm(double inches) {
    return inches * INCHES_TO_CM;
  }

  // ===== Temperature Conversions =====

  /**
   * Convert Fahrenheit to Celsius
   */
  public static double convertFahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - 32) * 5 / 9;
  }

  /**
   * Convert Celsius to Fahrenheit
   */
  public static double convertCelsiusToFahrenheit(double celsius) {
    return (celsius * 9 / 5) + 32;
  }

  // ===== Weight Conversions =====

  /**
   * Convert pounds to kilograms
   */
  public static double convertPoundsToKg(double pounds) {
    return pounds * POUNDS_TO_KG;
  }

  /**
   * Convert kilograms to pounds
   */
  public static double convertKgToPounds(double kg) {
    return kg * KG_TO_POUNDS;
  }

  // ===== Volume Conversions =====

  /**
   * Convert gallons to liters
   */
  public static double convertGallonsToLiters(double gallons) {
    return gallons * GALLONS_TO_LITERS;
  }

  /**
   * Convert liters to gallons
   */
  public static double convertLitersToGallons(double liters) {
    return liters * LITERS_TO_GALLONS;
  }

  // ===== Menu driven main method =====

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n===== Unit Convertor =====");
      System.out.println("1. Kilometers to Miles");
      System.out.println("2. Miles to Kilometers");
      System.out.println("3. Meters to Feet");
      System.out.println("4. Feet to Meters");
      System.out.println("5. Yards to Feet");
      System.out.println("6. Feet to Yards");
      System.out.println("7. Meters to Inches");
      System.out.println("8. Inches to Meters");
      System.out.println("9. Inches to Centimeters");
      System.out.println("10. Fahrenheit to Celsius");
      System.out.println("11. Celsius to Fahrenheit");
      System.out.println("12. Pounds to Kilograms");
      System.out.println("13. Kilograms to Pounds");
      System.out.println("14. Gallons to Liters");
      System.out.println("15. Liters to Gallons");
      System.out.println("0. Exit");
      System.out.print("Enter your choice: ");

      int choice = scanner.nextInt();

      if (choice == 0) {
        System.out.println("Thank you for using Unit Convertor!");
        break;
      }

      switch (choice) {
        case 1:
          System.out.print("Enter kilometers: ");
          double km = scanner.nextDouble();
          System.out.println(km + " km = " + String.format("%.4f", convertKmToMiles(km)) + " miles");
          break;
        case 2:
          System.out.print("Enter miles: ");
          double miles = scanner.nextDouble();
          System.out.println(miles + " miles = " + String.format("%.4f", convertMilesToKm(miles)) + " km");
          break;
        case 3:
          System.out.print("Enter meters: ");
          double meters = scanner.nextDouble();
          System.out.println(meters + " m = " + String.format("%.4f", convertMetersToFeet(meters)) + " feet");
          break;
        case 4:
          System.out.print("Enter feet: ");
          double feet = scanner.nextDouble();
          System.out.println(feet + " feet = " + String.format("%.4f", convertFeetToMeters(feet)) + " m");
          break;
        case 5:
          System.out.print("Enter yards: ");
          double yards = scanner.nextDouble();
          System.out.println(yards + " yards = " + String.format("%.4f", convertYardsToFeet(yards)) + " feet");
          break;
        case 6:
          System.out.print("Enter feet: ");
          feet = scanner.nextDouble();
          System.out.println(feet + " feet = " + String.format("%.4f", convertFeetToYards(feet)) + " yards");
          break;
        case 7:
          System.out.print("Enter meters: ");
          meters = scanner.nextDouble();
          System.out.println(meters + " m = " + String.format("%.4f", convertMetersToInches(meters)) + " inches");
          break;
        case 8:
          System.out.print("Enter inches: ");
          double inches = scanner.nextDouble();
          System.out.println(inches + " inches = " + String.format("%.4f", convertInchesToMeters(inches)) + " m");
          break;
        case 9:
          System.out.print("Enter inches: ");
          inches = scanner.nextDouble();
          System.out.println(inches + " inches = " + String.format("%.4f", convertInchesToCm(inches)) + " cm");
          break;
        case 10:
          System.out.print("Enter Fahrenheit: ");
          double fahrenheit = scanner.nextDouble();
          System.out
              .println(fahrenheit + "°F = " + String.format("%.4f", convertFahrenheitToCelsius(fahrenheit)) + "°C");
          break;
        case 11:
          System.out.print("Enter Celsius: ");
          double celsius = scanner.nextDouble();
          System.out.println(celsius + "°C = " + String.format("%.4f", convertCelsiusToFahrenheit(celsius)) + "°F");
          break;
        case 12:
          System.out.print("Enter pounds: ");
          double pounds = scanner.nextDouble();
          System.out.println(pounds + " lbs = " + String.format("%.4f", convertPoundsToKg(pounds)) + " kg");
          break;
        case 13:
          System.out.print("Enter kilograms: ");
          double kg = scanner.nextDouble();
          System.out.println(kg + " kg = " + String.format("%.4f", convertKgToPounds(kg)) + " lbs");
          break;
        case 14:
          System.out.print("Enter gallons: ");
          double gallons = scanner.nextDouble();
          System.out
              .println(gallons + " gallons = " + String.format("%.4f", convertGallonsToLiters(gallons)) + " liters");
          break;
        case 15:
          System.out.print("Enter liters: ");
          double liters = scanner.nextDouble();
          System.out
              .println(liters + " liters = " + String.format("%.4f", convertLitersToGallons(liters)) + " gallons");
          break;
        default:
          System.out.println("Invalid choice! Please try again.");
      }
    }

    scanner.close();
  }
}
