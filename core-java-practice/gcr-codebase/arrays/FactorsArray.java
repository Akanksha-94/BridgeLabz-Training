import java.util.Scanner;

public class FactorsArray {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter an integer to find factors: ");
    int num = sc.nextInt();

    int maxFactor = 10;
    int[] factors = new int[maxFactor];
    int idx = 0;

    for (int i = 1; i <= num; i++) {
      if (num % i == 0) {
        if (idx == maxFactor) {
          int newMax = maxFactor * 2;
          int[] temp = new int[newMax];
          for (int j = 0; j < maxFactor; j++)
            temp[j] = factors[j];
          factors = temp;
          maxFactor = newMax;
        }
        factors[idx++] = i;
      }
    }

    System.out.println("Factors of " + num + ":");
    for (int i = 0; i < idx; i++)
      System.out.print(factors[i] + (i + 1 < idx ? ", " : "\n"));
    
  }
}
