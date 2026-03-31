import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisionByZeroHandler {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Enter first number: ");
      double num1 = scanner.nextDouble();

      System.out.print("Enter second number: ");
      double num2 = scanner.nextDouble();

      if (num2 == 0) {
        throw new ArithmeticException("Cannot divide by zero");
      }

      double result = num1 / num2;
      System.out.println("Result: " + result);
    } catch (ArithmeticException e) {
      System.out.println("Error: Cannot divide by zero");
    } catch (InputMismatchException e) {
      System.out.println("Error: Please enter valid numbers");
    } finally {
      scanner.close();
    }
  }
}
