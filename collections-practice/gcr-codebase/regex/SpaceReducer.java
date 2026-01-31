package gcr.regex;

public class SpaceReducer {
  public static String reduce(String s) {
    return s == null ? "" : s.replaceAll("\\s{2,}", " ");
  }
}
