import java.util.Scanner;

public class Calculator {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter first number: ");
    if (!sc.hasNextDouble()) {
      System.out.println("Invalid input.");
      return;
    }
    double first = sc.nextDouble();

    System.out.print("Enter second number: ");
    if (!sc.hasNextDouble()) {
      System.out.println("Invalid input.");
      return;
    }
    double second = sc.nextDouble();

    System.out.print("Enter operator (+, -, *, /): ");
    String op = sc.next();

    double result = 0;
    boolean validOp = true;

    switch (op) {
      case "+":
        result = first + second;
        System.out.println(first + " + " + second + " = " + result);
        break;
      case "-":
        result = first - second;
        System.out.println(first + " - " + second + " = " + result);
        break;
      case "*":
        result = first * second;
        System.out.println(first + " * " + second + " = " + result);
        break;
      case "/":
        if (second == 0) {
          System.out.println("Error: Division by zero is not allowed.");
        } else {
          result = first / second;
          System.out.println(first + " / " + second + " = " + result);
        }
        break;
      default:
        System.out.println("Invalid Operator");
        validOp = false;
    }
  }
}
