package gcr.regex;

import java.util.regex.Pattern;

public class SSNValidator {
  private static final Pattern P = Pattern.compile("^\\d{3}-\\d{2}-\\d{4}$");

  public static boolean isValid(String s) {
    return s != null && P.matcher(s).matches();
  }
}
