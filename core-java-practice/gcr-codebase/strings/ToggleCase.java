import java.util.Scanner;

public class ToggleCase {

  public static String toggleCase(String str) {
    String result = "";

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch >= 'A' && ch <= 'Z') {
        result += Character.toLowerCase(ch);
      } else if (ch >= 'a' && ch <= 'z') {
        result += Character.toUpperCase(ch);
      } else {
        result += ch;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String str = sc.nextLine();

    String toggled = toggleCase(str);
    System.out.println("Original string: " + str);
    System.out.println("Toggled case: " + toggled);
  }
}
