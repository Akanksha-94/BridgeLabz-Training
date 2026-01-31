public class CensorBadWords {
  public static String censor(String text) {
    return text.replaceAll("(?i)\\b(damn|stupid)\\b", "****");
  }

  public static void main(String[] args) {
    String in = "This is a damn bad example with some stupid words.";
    System.out.println(censor(in));
  }
}