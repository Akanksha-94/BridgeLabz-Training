package gcr.regex;

import java.util.regex.Pattern;

public class IPValidator {
  private static final String OCTET = "(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)";
  private static final Pattern P = Pattern.compile("^" + OCTET + "\\." + OCTET + "\\." + OCTET + "\\." + OCTET + "$");

  public static boolean isValid(String s) {
    return s != null && P.matcher(s).matches();
  }
}
