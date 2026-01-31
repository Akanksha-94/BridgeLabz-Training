public class NormalizeSpaces {
  public static String normalize(String s) {
    return s.replaceAll(" {2,}", " ");
  }

  public static void main(String[] args) {
    String in = "This  is   an example    with multiple     spaces.";
    System.out.println(normalize(in));
  }
}