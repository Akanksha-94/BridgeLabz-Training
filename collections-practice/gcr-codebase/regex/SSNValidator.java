public class SSNValidator {
  public static boolean isValid(String s) {
    return s != null && s.matches("^\\d{3}-\\d{2}-\\d{4}$");
  }

  public static void main(String[] args) {
    System.out.println("123-45-6789 -> " + (isValid("123-45-6789") ? "Valid" : "Invalid"));
    System.out.println("123456789 -> " + (isValid("123456789") ? "Valid" : "Invalid"));
  }
}