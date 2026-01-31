import java.util.regex.*;

public class CapitalizedWordsExtractor {
  public static void extract(String text) {
    Pattern p = Pattern.compile("\\b[A-Z][a-z]+\\b");
    Matcher m = p.matcher(text);
    boolean first = true;
    while (m.find()) {
      if (!first)
        System.out.print(", ");
      System.out.print(m.group());
      first = false;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    String text = "The Eiffel Tower is in Paris and the Statue of Liberty is in New York.";
    extract(text);
  }
}