public class LicensePlateValidator {
  public static boolean isValid(String s) {
    return s != null && s.matches("^[A-Z]{2}\\d{4}$");
  }

  public static void main(String[] args) {
    System.out.println("AB1234 -> " + (isValid("AB1234") ? "Valid" : "Invalid"));
    System.out.println("A12345 -> " + (isValid("A12345") ? "Valid" : "Invalid"));
    System.out.println("CD5678 -> " + (isValid("CD5678") ? "Valid" : "Invalid"));
    System.out.println("AB123 -> " + (isValid("AB123") ? "Valid" : "Invalid"));
  }
}


  
    
  

  
    
    
  