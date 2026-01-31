package gcr.regex;

import java.util.regex.Pattern;

public class UsernameValidator {
  private static final Pattern P = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{4,14}$");

  public static boolean isValid(String s) {
    return s != null && P.matcher(s).matches();
  }
}
