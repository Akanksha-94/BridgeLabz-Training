import java.util.Scanner;

public class PalindromeCheck {

  public static boolean isPalindrome(String str) {
    str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String str = sc.nextLine();

    if (isPalindrome(str)) {
      System.out.println("\"" + str + "\" is a palindrome");
    } else {
      System.out.println("\"" + str + "\" is not a palindrome");
    }
  }
}
