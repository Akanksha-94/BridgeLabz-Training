import java.util.Scanner;

public class PalindromeChecker {

  // Take input from user
  static String takeInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("=== Palindrome Checker ===");
    System.out.print("Enter a string: ");
    return scanner.nextLine();
  }

  // Check if string is palindrome
  static boolean isPalindrome(String str) {
    // Remove spaces and convert to lowercase
    str = str.replaceAll("\\s", "").toLowerCase();

    // Remove non-alphanumeric characters
    str = str.replaceAll("[^a-zA-Z0-9]", "");

    int left = 0;
    int right = str.length() - 1;

    while (left < right) {
      if (str.charAt(left) != str.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }

  // Display result
  static void displayResult(String input, boolean palindrome) {
    System.out.println("\n--- Result ---");
    System.out.println("Input: " + input);
    if (palindrome) {
      System.out.println("✓ This is a PALINDROME!");
    } else {
      System.out.println("✗ This is NOT a palindrome.");
    }
  }

  public static void main(String[] args) {
    String input = takeInput();
    boolean palindrome = isPalindrome(input);
    displayResult(input, palindrome);
  }
}
