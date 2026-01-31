import java.util.regex.*;
import java.util.*;

public class RepeatingWordsFinder {
  public static void findRepeats(String text) {
    Pattern p = Pattern.compile("(?i)\\b(\\w+)\\s+\\1\\b");
    Matcher m = p.matcher(text);
    Set<String> set = new LinkedHashSet<>();
    while (m.find())
      set.add(m.group(1).toLowerCase());
    boolean first = true;
    for (String w : set) {
      if (!first)
        System.out.print(", ");
      System.out.print(w);
      first = false;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    String text = "This is is a repeated repeated word test.";
    findRepeats(text);
  }
}