import java.util.Scanner;

public class WordsWithLengths2D {
  static String[] customSplit(String s) {
    java.util.List<String> words = new java.util.ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == ' ') {
        if (sb.length() > 0) {
          words.add(sb.toString());
          sb.setLength(0);
        }
      } else
        sb.append(c);
    }
    if (sb.length() > 0)
      words.add(sb.toString());
    return words.toArray(new String[0]);
  }

  static int customLength(String s) {
    int count = 0;
    try {
      for (int i = 0;; i++) {
        s.charAt(i);
        count++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    return count;
  }

  static String[][] wordsWithLengths(String[] words) {
    String[][] out = new String[words.length][2];
    for (int i = 0; i < words.length; i++) {
      out[i][0] = words[i];
      out[i][1] = String.valueOf(customLength(words[i]));
    }
    return out;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a line of text:");
    String line = sc.nextLine();
    String[] words = customSplit(line);
    String[][] table = wordsWithLengths(words);
    System.out.println("Word\tLength");
    for (String[] row : table) {
      System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
    }

  }
}
