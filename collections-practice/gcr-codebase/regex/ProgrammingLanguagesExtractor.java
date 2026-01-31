package gcr.regex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgrammingLanguagesExtractor {
  private static final Pattern P = Pattern.compile("\\b(JavaScript|Java|Python|Go|C\\+\\+|C#|Ruby|Rust)\\b",
      Pattern.CASE_INSENSITIVE);

  public static List<String> extract(String text) {
    Set<String> out = new LinkedHashSet<>();
    if (text == null)
      return new ArrayList<>();
    Matcher m = P.matcher(text);
    while (m.find())
      out.add(m.group());
    return new ArrayList<>(out);
  }
}
