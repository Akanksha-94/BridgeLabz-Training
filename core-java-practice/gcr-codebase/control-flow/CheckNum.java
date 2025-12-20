import java.util.Scanner;

public class CheckNum {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int num = sc.nextInt();
    if (num == 0) {
      System.out.print("zero");
    } else if (num > 0) {
      System.out.print("positive");
    } else {
      System.out.print("negative");

    }

  }
}
