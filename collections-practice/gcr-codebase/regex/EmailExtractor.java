package gcr.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractor {
  private static final Pattern P = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,6}");

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
