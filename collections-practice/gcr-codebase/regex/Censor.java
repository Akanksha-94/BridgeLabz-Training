package gcr.regex;

import java.util.List;
import java.util.regex.Pattern;

public class Censor {
  public static String censor(String text, List<String> badWords) {
    if (text == null || badWords == null || badWords.isEmpty())
      return text == null ? "" : text;
    StringBuilder sb = new StringBuilder();
    for (String w : badWords) {
      if (sb.length() > 0)
        sb.append("|");
      sb.append(Pattern.quote(w));
    }
    Pattern p = Pattern.compile("(?i)\\b(?:" + sb + ")\\b");
    return p.matcher(text).replaceAll("****");
  }
}
