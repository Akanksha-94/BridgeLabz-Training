package gcr.regex;

import java.util.regex.Pattern;

public class CreditCardValidator {
  private static final Pattern VISA = Pattern.compile("^4\\d{15}$");
  private static final Pattern MASTERCARD = Pattern.compile("^5\\d{15}$");

  public static boolean isVisa(String s) {
    return s != null && VISA.matcher(s).matches();
  }

  public static boolean isMasterCard(String s) {
    return s != null && MASTERCARD.matcher(s).matches();
  }
}
