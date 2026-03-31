import java.util.Scanner;

public class ShortestLongest {
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

  static int[] wordLengths2DIndex(String[][] data) {
    // Not used; kept for compatibility
    return new int[0];
  }

  static int[] findShortestLongest(String[][] table) {
    if (table.length == 0)
      return new int[] { -1, -1 };
    int minIdx = 0, maxIdx = 0;
    for (int i = 1; i < table.length; i++) {
      int len = Integer.parseInt(table[i][1]);
      if (len < Integer.parseInt(table[minIdx][1]))
        minIdx = i;
      if (len > Integer.parseInt(table[maxIdx][1]))
        maxIdx = i;
    }
    return new int[] { minIdx, maxIdx };
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a line of text:");
    String line = sc.nextLine();
    String[] words = customSplit(line);
    String[][] table = new String[words.length][2];
    for (int i = 0; i < words.length; i++) {
      table[i][0] = words[i];
      table[i][1] = String.valueOf(customLength(words[i]));
    }
    int[] idx = findShortestLongest(table);
    if (idx[0] == -1)
      System.out.println("No words found.");
    else {
      System.out.println("Shortest: " + table[idx[0]][0] + " (" + table[idx[0]][1] + ")");
      System.out.println("Longest: " + table[idx[1]][0] + " (" + table[idx[1]][1] + ")");
    }

  }
}
