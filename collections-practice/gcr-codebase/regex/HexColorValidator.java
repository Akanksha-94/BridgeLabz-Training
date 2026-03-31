public class HexColorValidator {
  public static boolean isValid(String s) {
    return s != null && s.matches("^#[0-9A-Fa-f]{6}$");
  }

  public static void main(String[] args) {
    System.out.println("#FFA500 -> " + (isValid("#FFA500") ? "Valid" : "Invalid"));
    System.out.println("#ff4500 -> " + (isValid("#ff4500") ? "Valid" : "Invalid"));
    System.out.println("#123 -> " + (isValid("#123") ? "Valid" : "Invalid"));
  }
}