import java.util.Scanner;

public class TrimSubstringCompare {
  static int[] trimIndices(String s) {
    int start = 0, end = s.length() - 1;
    while (start <= end && s.charAt(start) == ' ')
      start++;
    while (end >= start && s.charAt(end) == ' ')
      end--;
    return new int[] { start, end };
  }

  static String customSubstring(String s, int start, int end) {
    StringBuilder sb = new StringBuilder();
    for (int i = start; i <= end; i++)
      sb.append(s.charAt(i));
    return sb.toString();
  }

  static boolean customCompare(String a, String b) {
    if (a.length() != b.length())
      return false;
    for (int i = 0; i < a.length(); i++)
      if (a.charAt(i) != b.charAt(i))
        return false;
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text with leading/trailing spaces:");
    String line = sc.nextLine();
    int[] idx = trimIndices(line);
    String customTrimmed = (idx[0] <= idx[1]) ? customSubstring(line, idx[0], idx[1]) : "";
    String builtin = line.trim();
    System.out.println("Custom trimmed: '" + customTrimmed + "'");
    System.out.println("Built-in trimmed: '" + builtin + "'");
    System.out.println("Are they equal? " + customCompare(customTrimmed, builtin));

  }
}
