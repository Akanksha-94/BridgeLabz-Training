package gcr.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateExtractor {
  private static final Pattern P = Pattern.compile("\\b(?:0[1-9]|[12][0-9]|3[01])/(?:0[1-9]|1[0-2])/\\d{4}\\b");

  public static List<String> extract(String text) {
    List<String> out = new ArrayList<>();
    if (text == null)
      return out;
    Matcher m = P.matcher(text);
    while (m.find())
      out.add(m.group());
    return out;
  }
}
