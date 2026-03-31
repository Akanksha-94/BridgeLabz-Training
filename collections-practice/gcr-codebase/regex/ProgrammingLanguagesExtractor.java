import java.util.regex.*;

public class ProgrammingLanguagesExtractor {
  public static void extract(String text) {
    Pattern p = Pattern.compile("\\b(Java|Python|JavaScript|Go)\\b");
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
    String text = "I love Java, Python, and JavaScript, but I haven't tried Go yet.";
    extract(text);
  }
}