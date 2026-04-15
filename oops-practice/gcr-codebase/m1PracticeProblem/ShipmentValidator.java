import java.io.*;
import java.time.*;
import java.time.format.*;

public class ShipmentValidator {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String record = br.readLine().trim();
      if (validateRecord(record)) {
        System.out.println("COMPLIANT RECORD");
      } else {
        System.out.println("NON-COMPLIANT RECORD");
      }
    }
  }

  private static boolean validateRecord(String record) {
    String[] parts = record.split("\\|");
    if (parts.length != 5)
      return false;
    return validateCode(parts[0]) && validateDate(parts[1]) && validateMode(parts[2]) && validateWeight(parts[3])
        && validateStatus(parts[4]);
  }

  private static boolean validateCode(String code) {
    if (!code.startsWith("SHIP-"))
      return false;
    String digits = code.substring(5);
    if (digits.length() != 6)
      return false;
    if (digits.charAt(0) == '0')
      return false;
    for (char c : digits.toCharArray()) {
      if (!Character.isDigit(c))
        return false;
    }
    // check no 4 consecutive same
    for (int i = 0; i < digits.length() - 3; i++) {
      if (digits.charAt(i) == digits.charAt(i + 1) && digits.charAt(i + 1) == digits.charAt(i + 2)
          && digits.charAt(i + 2) == digits.charAt(i + 3)) {
        return false;
      }
    }
    return true;
  }

  private static boolean validateDate(String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate d = LocalDate.parse(date, formatter);
      int year = d.getYear();
      if (year < 2000 || year > 2099)
        return false;
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean validateMode(String mode) {
    return mode.equals("AIR") || mode.equals("SEA") || mode.equals("ROAD") || mode.equals("RAIL")
        || mode.equals("EXPRESS") || mode.equals("FREIGHT");
  }

  private static boolean validateWeight(String weight) {
    try {
      double w = Double.parseDouble(weight);
      if (w <= 0 || w > 999999.99)
        return false;
      String[] parts = weight.split("\\.");
      if (parts.length > 2)
        return false;
      String intPart = parts[0];
      if (intPart.length() > 6)
        return false;
      if (intPart.startsWith("0") && intPart.length() > 1)
        return false;
      if (parts.length == 2) {
        String decPart = parts[1];
        if (decPart.length() > 2)
          return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean validateStatus(String status) {
    return status.equals("DELIVERED") || status.equals("CANCELLED") || status.equals("IN_TRANSIT");
  }
}