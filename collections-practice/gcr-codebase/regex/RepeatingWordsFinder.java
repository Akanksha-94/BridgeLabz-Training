package gcr.regex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepeatingWordsFinder {
  private static final Pattern P = Pattern.compile("\\b([a-zA-Z]+)\\s+\\1\\b", Pattern.CASE_INSENSITIVE);

  public static List<String> find(String text) {
    Set<String> out = new LinkedHashSet<>();
    if (text == null)
      return new ArrayList<>();
    Matcher m = P.matcher(text);
    while (m.find())
      out.add(m.group(1).toLowerCase());
    return new ArrayList<>(out);
  }
}
