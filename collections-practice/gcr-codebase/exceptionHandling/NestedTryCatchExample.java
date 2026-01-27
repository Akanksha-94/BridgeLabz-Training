import java.util.Scanner;

public class NestedTryCatchExample {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Enter array size: ");
      int size = scanner.nextInt();
      int[] array = new int[size];

      System.out.println("Enter array elements:");
      for (int i = 0; i < size; i++) {
        System.out.print("Element " + i + ": ");
        array[i] = scanner.nextInt();
      }

      try {
        System.out.print("Enter index to access: ");
        int index = scanner.nextInt();
        int value = array[index];

        try {
          System.out.print("Enter divisor: ");
          int divisor = scanner.nextInt();

          if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
          }

          int result = value / divisor;
          System.out.println("Division result: " + result);
        } catch (ArithmeticException e) {
          System.out.println("Cannot divide by zero!");
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Invalid array index!");
      }
    } finally {
      scanner.close();
    }
  }
}
