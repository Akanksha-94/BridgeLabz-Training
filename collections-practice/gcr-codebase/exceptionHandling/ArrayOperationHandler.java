import java.util.Scanner;

public class ArrayOperationHandler {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] array = null;

    try {
      System.out.print("Enter array size: ");
      int size = scanner.nextInt();
      array = new int[size];

      System.out.println("Enter array elements:");
      for (int i = 0; i < size; i++) {
        System.out.print("Element " + i + ": ");
        array[i] = scanner.nextInt();
      }

      System.out.print("Enter index to retrieve: ");
      int index = scanner.nextInt();

      if (array == null) {
        throw new NullPointerException("Array is not initialized");
      }

      System.out.println("Value at index " + index + ": " + array[index]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid index!");
    } catch (NullPointerException e) {
      System.out.println("Array is not initialized!");
    } finally {
      scanner.close();
    }
  }
}
