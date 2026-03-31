import java.util.Scanner;

public class ToLowerCompare {
  static String customToLower(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c >= 'A' && c <= 'Z') {
        c = (char) (c + 32);
      }
      sb.append(c);
    }
    return sb.toString();
  }

  static boolean customCompare(String a, String b) {
    if (a.length() != b.length())
      return false;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i))
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text to convert to lowercase:");
    String input = sc.nextLine();
    String builtin = input.toLowerCase();
    String custom = customToLower(input);
    System.out.println("Built-in: " + builtin);
    System.out.println("Custom:   " + custom);
    System.out.println("Are they equal? " + customCompare(builtin, custom));

  }
}
