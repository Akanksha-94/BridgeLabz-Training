import java.util.Scanner;

public class StoreSum {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double[] arr = new double[10];
    int idx = 0;
    while (true) {
      System.out.print("Enter a positive number (0 or negative to stop): ");
      double v = sc.nextDouble();
      if (v <= 0 || idx == arr.length)
        break;
      arr[idx++] = v;
    }

    double total = 0.0;
    System.out.println("Entered numbers:");
    for (int i = 0; i < idx; i++) {
      System.out.println(arr[i]);
      total += arr[i];
    }
    System.out.println("Sum = " + total);
   
  }
}
