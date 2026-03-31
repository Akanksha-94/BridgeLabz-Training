import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplitCompare {
  static String[] customSplit(String s) {
    List<String> words = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == ' ') {
        if (sb.length() > 0) {
          words.add(sb.toString());
          sb.setLength(0);
        }
      } else {
        sb.append(c);
      }
    }
    if (sb.length() > 0)
      words.add(sb.toString());
    return words.toArray(new String[0]);
  }

  static boolean compareArrays(String[] a, String[] b) {
    if (a.length != b.length)
      return false;
    for (int i = 0; i < a.length; i++)
      if (!a[i].equals(b[i]))
        return false;
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a line of text:");
    String line = sc.nextLine();
    String[] builtin = line.split("\\s+");
    String[] custom = customSplit(line);
    System.out.println("Built-in split -> length: " + builtin.length);
    System.out.println("Custom split -> length: " + custom.length);
    System.out.println("Are they equal? " + compareArrays(builtin, custom));

  }
}
