package gcr.regex;

import java.util.regex.Pattern;

public class LicensePlateValidator {
  private static final Pattern P = Pattern.compile("^[A-Z]{2}\\d{4}$");

  public static boolean isValid(String s) {
    return s != null && P.matcher(s).matches();
  }
}
