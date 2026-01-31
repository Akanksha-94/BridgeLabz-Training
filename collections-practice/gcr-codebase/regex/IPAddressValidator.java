public class IPAddressValidator {
  public static boolean isValid(String ip) {
    if (ip == null)
      return false;
    return ip.matches("^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}$");
  }

  public static void main(String[] args) {
    System.out.println("192.168.0.1 -> " + (isValid("192.168.0.1") ? "Valid" : "Invalid"));
    System.out.println("256.100.0.1 -> " + (isValid("256.100.0.1") ? "Valid" : "Invalid"));
  }
}