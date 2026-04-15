import java.io.*;

public class KeyGeneration {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String str = br.readLine().trim();
      String result = process(str);
      System.out.println(result);
    }
  }

  private static String process(String str) {
    if (str.length() < 6)
      return "Invalid Input (length < 6)";
    if (str.isEmpty())
      return "Invalid Input (empty string)";
    boolean hasSpace = str.contains(" ");
    boolean hasDigit = str.chars().anyMatch(Character::isDigit);
    boolean hasSpecial = str.chars().anyMatch(c -> !Character.isLetter(c) && !Character.isWhitespace(c));
    if (hasSpace)
      return "Invalid Input (contains space)";
    if (hasDigit)
      return "Invalid Input (contains digits)";
    if (hasSpecial)
      return "Invalid Input (contains special character)";
    // generate key
    String lower = str.toLowerCase();
    StringBuilder sb = new StringBuilder();
    for (char c : lower.toCharArray()) {
      if ((c % 2) != 0) { // odd ASCII
        sb.append(c);
      }
    }
    sb.reverse();
    StringBuilder key = new StringBuilder();
    for (int j = 0; j < sb.length(); j++) {
      char c = sb.charAt(j);
      if (j % 2 == 0) {
        key.append(Character.toUpperCase(c));
      } else {
        key.append(c);
      }
    }
    return "The generated key is - " + key.toString();
  }
}