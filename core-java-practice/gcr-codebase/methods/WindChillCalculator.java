import java.util.Scanner;

public class WindChillCalculator {

  /**
   * Method to calculate the wind chill temperature
   * Formula: windChill = 35.74 + 0.6215 * temp + (0.4275 * temp - 35.75) *
   * windSpeed^0.16
   * 
   * @param temperature the temperature in Fahrenheit
   * @param windSpeed   the wind speed in mph
   * @return the calculated wind chill temperature
   */
  public double calculateWindChill(double temperature, double windSpeed) {
    double windChill = 35.74 + (0.6215 * temperature) +
        ((0.4275 * temperature - 35.75) * Math.pow(windSpeed, 0.16));
    return windChill;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    WindChillCalculator calculator = new WindChillCalculator();

    // Get user input
    System.out.print("Enter temperature (in Fahrenheit): ");
    double temperature = scanner.nextDouble();

    System.out.print("Enter wind speed (in mph): ");
    double windSpeed = scanner.nextDouble();

    // Calculate wind chill
    double windChill = calculator.calculateWindChill(temperature, windSpeed);

    // Display result
    System.out.println("Temperature: " + temperature + "°F");
    System.out.println("Wind Speed: " + windSpeed + " mph");
    System.out.println("Wind Chill Temperature: " + String.format("%.2f", windChill) + "°F");

    scanner.close();
  }
}
