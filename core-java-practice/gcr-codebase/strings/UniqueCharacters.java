import java.util.Scanner;

public class UniqueCharacters {
  static int customLength(String s) {
    int cnt = 0;
    try {
      for (int i = 0;; i++) {
        s.charAt(i);
        cnt++;
      }
    } catch (IndexOutOfBoundsException e) {
    }
    return cnt;
  }

  static char[] uniqueChars(String s) {
    int len = customLength(s);
    char[] temp = new char[len];
    int uniqCount = 0;
    for (int i = 0; i < len; i++) {
      char c = s.charAt(i);
      boolean seen = false;
      for (int j = 0; j < i; j++) {
        if (s.charAt(j) == c) {
          seen = true;
          break;
        }
      }
      if (!seen)
        temp[uniqCount++] = c;
    }
    char[] res = new char[uniqCount];
    for (int i = 0; i < uniqCount; i++)
      res[i] = temp[i];
    return res;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text:");
    String line = sc.nextLine();
    char[] uniques = uniqueChars(line);
    System.out.print("Unique characters: ");
    for (char c : uniques)
      System.out.print(c + " ");
    System.out.println();
    
  }
}
