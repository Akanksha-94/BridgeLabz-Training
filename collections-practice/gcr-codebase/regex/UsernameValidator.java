import java.util.regex.*;

public class UsernameValidator {
  public static boolean isValid(String s) {
    return s != null && s.matches("^[A-Za-z][A-Za-z0-9_]{4,14}$");
  }

  public static void main(String[] args) {
    System.out.println("user_123 -> " + (isValid("user_123") ? "Valid" : "Invalid"));
    System.out.println("123user -> " + (isValid("123user") ? "Valid" : "Invalid"));
    System.out.println("us -> " + (isValid("us") ? "Valid" : "Invalid"));
  }
}