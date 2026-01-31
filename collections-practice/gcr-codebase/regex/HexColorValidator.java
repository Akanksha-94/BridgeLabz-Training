package gcr.regex;

import java.util.regex.Pattern;

public class HexColorValidator {
  private static final Pattern P = Pattern.compile("^#[0-9A-Fa-f]{6}$");

  public static boolean isValid(String s) {
    return s != null && P.matcher(s).matches();
  }
}
