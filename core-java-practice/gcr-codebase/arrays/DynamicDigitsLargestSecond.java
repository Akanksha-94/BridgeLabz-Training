import java.util.Scanner;

public class DynamicDigitsLargestSecond {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter an integer: ");
    long num = Math.abs(sc.nextLong());

    int maxDigit = 10;
    int[] digits = new int[maxDigit];
    int idx = 0;

    while (num != 0) {
      int d = (int) (num % 10);
      if (idx == maxDigit) {
        // increase capacity by 10
        int newMax = maxDigit + 10;
        int[] temp = new int[newMax];
        for (int j = 0; j < maxDigit; j++)
          temp[j] = digits[j];
        digits = temp;
        maxDigit = newMax;
      }
      digits[idx++] = d;
      num /= 10;
    }

    if (idx == 0) {
      System.out.println("No digits to process (input was 0).");
      
      return;
    }

    int largest = -1, second = -1;
    for (int i = 0; i < idx; i++) {
      int v = digits[i];
      if (v > largest) {
        second = largest;
        largest = v;
      } else if (v > second && v != largest) {
        second = v;
      }
    }

    System.out.println("Largest digit = " + largest);
    if (second >= 0)
      System.out.println("Second largest digit = " + second);
    else
      System.out.println("Second largest digit not available.");
    
  }
}
