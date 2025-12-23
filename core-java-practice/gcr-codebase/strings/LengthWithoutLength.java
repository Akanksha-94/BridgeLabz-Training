import java.util.Scanner;

public class LengthWithoutLength {
  static int customLength(String s) {
    int count = 0;
    try {
      for (int i = 0;; i++) {
        s.charAt(i);
        count++;
      }
    } catch (IndexOutOfBoundsException e) {
      // reached end
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a string (single token):");
    String input = sc.next();
    int custom = customLength(input);
    int builtin = input.length();
    System.out.println("Custom length: " + custom);
    System.out.println("Built-in length: " + builtin);

  }
}
