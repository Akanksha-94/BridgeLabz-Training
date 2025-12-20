import java.util.Scanner;

public class Multi6to9 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int num = sc.nextInt();
    int[] results = new int[4];
    int idx = 0;
    for (int i = 6; i <= 9; i++) {
      results[idx++] = num * i;
    }

    idx = 0;
    for (int i = 6; i <= 9; i++) {
      System.out.println(num + " * " + i + " = " + results[idx++]);
    }
    
  }
}
