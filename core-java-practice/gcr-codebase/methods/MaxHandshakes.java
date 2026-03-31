import java.util.Scanner;

public class MaxHandshakes {

  /**
   * Method to calculate the maximum number of handshakes among students
   * Using combination formula: n * (n - 1) / 2
   * 
   * @param numberOfStudents the number of students
   * @return the maximum number of handshakes possible
   */
  public static long calculateMaxHandshakes(int numberOfStudents) {
    return (long) numberOfStudents * (numberOfStudents - 1) / 2;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get user input
    System.out.print("Enter the number of students: ");
    int numberOfStudents = scanner.nextInt();

    // Calculate maximum handshakes
    long maxHandshakes = calculateMaxHandshakes(numberOfStudents);

    // Display result
    System.out.println("The maximum number of possible handshakes among " +
        numberOfStudents + " students is: " + maxHandshakes);

    scanner.close();
  }
}
