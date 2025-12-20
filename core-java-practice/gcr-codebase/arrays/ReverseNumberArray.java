import java.util.Scanner;

public class ReverseNumberArray {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter an integer to reverse: ");
    long numInput = sc.nextLong();
    long num = Math.abs(numInput);

    // count digits
    if (num == 0) {
      System.out.println("Reversed number: 0");
   
      return;
    }

    int maxDigit = 20; // safe initial capacity
    int[] digits = new int[maxDigit];
    int idx = 0;
    while (num != 0) {
      digits[idx++] = (int) (num % 10);
      num /= 10;
      if (idx == digits.length) {
        int[] tmp = new int[digits.length * 2];
        for (int i = 0; i < digits.length; i++)
          tmp[i] = digits[i];
        digits = tmp;
      }
    }

    System.out.print("Reversed digits: ");
    for (int i = 0; i < idx; i++)
      System.out.print(digits[i]);
    if (numInput < 0)
      System.out.print(" (negative input)");
    System.out.println();
  
  }
}
