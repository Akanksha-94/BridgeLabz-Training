import java.util.Scanner;

public class RandomNumberStats {

  /**
   * Method to generate an array of 4-digit random numbers
   * 4-digit numbers range from 1000 to 9999
   * 
   * @param size the size of the array
   * @return array of random 4-digit numbers
   */
  public static int[] generate4DigitRandomArray(int size) {
    int[] randomNumbers = new int[size];

    for (int i = 0; i < size; i++) {
      // Generate random number between 1000 and 9999
      randomNumbers[i] = 1000 + (int) (Math.random() * 9000);
    }

    return randomNumbers;
  }

  /**
   * Method to find average, minimum, and maximum values of an array
   * 
   * @param numbers the array of numbers
   * @return array containing [average, min, max]
   */
  public static double[] findAverageMinMax(int[] numbers) {
    if (numbers.length == 0) {
      return new double[] { 0, 0, 0 };
    }

    // Calculate average
    int sum = 0;
    for (int number : numbers) {
      sum += number;
    }
    double average = (double) sum / numbers.length;

    // Find minimum and maximum
    int min = numbers[0];
    int max = numbers[0];

    for (int number : numbers) {
      min = Math.min(min, number);
      max = Math.max(max, number);
    }

    return new double[] { average, min, max };
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Define size as 5
    int size = 5;

    // Generate 5 random 4-digit numbers
    int[] randomNumbers = generate4DigitRandomArray(size);

    // Display the generated numbers
    System.out.println("Generated 5 random 4-digit numbers:");
    for (int i = 0; i < randomNumbers.length; i++) {
      System.out.print(randomNumbers[i] + " ");
    }
    System.out.println();

    // Find average, min, and max
    double[] stats = findAverageMinMax(randomNumbers);

    double average = stats[0];
    int min = (int) stats[1];
    int max = (int) stats[2];

    // Display results
    System.out.println("\n===== Statistical Analysis =====");
    System.out.println("Average: " + String.format("%.2f", average));
    System.out.println("Minimum: " + min);
    System.out.println("Maximum: " + max);
    System.out.println("Range: " + (max - min));

    scanner.close();
  }
}
