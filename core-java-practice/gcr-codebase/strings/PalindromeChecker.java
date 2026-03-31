import java.util.Scanner;

public class PalindromeChecker {
  static boolean isPalindromeIterative(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j))
        return false;
      i++;
      j--;
    }
    return true;
  }

  static boolean isPalindromeRecursive(String s, int start, int end) {
    if (start >= end)
      return true;
    if (s.charAt(start) != s.charAt(end))
      return false;
    return isPalindromeRecursive(s, start + 1, end - 1);
  }

  static String reverseUsingCharAt(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--)
      sb.append(s.charAt(i));
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text:");
    String line = sc.nextLine();
    System.out.println("Iterative: " + isPalindromeIterative(line));
    System.out.println("Recursive: " + isPalindromeRecursive(line, 0, line.length() - 1));
    String rev = reverseUsingCharAt(line);
    System.out.println("Reverse method: " + line.equals(rev));
    
  }
}
