import java.util.Scanner;

public class FinallyBlockExecution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Enter dividend: ");
      int dividend = scanner.nextInt();

      System.out.print("Enter divisor: ");
      int divisor = scanner.nextInt();

      if (divisor == 0) {
        throw new ArithmeticException("Cannot divide by zero");
      }

      int result = dividend / divisor;
      System.out.println("Result: " + result);
    } catch (ArithmeticException e) {
      System.out.println("Error: Cannot divide by zero");
    } finally {
      System.out.println("Operation completed");
      scanner.close();
    }
  }
}
