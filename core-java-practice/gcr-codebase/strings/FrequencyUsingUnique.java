import java.util.Scanner;

public class FrequencyUsingUnique {
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
    int uc = 0;
    for (int i = 0; i < len; i++) {
      char c = s.charAt(i);
      boolean seen = false;
      for (int j = 0; j < i; j++)
        if (s.charAt(j) == c) {
          seen = true;
          break;
        }
      if (!seen)
        temp[uc++] = c;
    }
    char[] res = new char[uc];
    for (int i = 0; i < uc; i++)
      res[i] = temp[i];
    return res;
  }

  static String[][] freqUsingUnique(String s) {
    char[] uniques = uniqueChars(s);
    String[][] out = new String[uniques.length][2];
    for (int i = 0; i < uniques.length; i++) {
      char c = uniques[i];
      int count = 0;
      for (int j = 0;; j++) {
        try {
          if (s.charAt(j) == c)
            count++;
        } catch (IndexOutOfBoundsException e) {
          break;
        }
      }
      out[i][0] = String.valueOf(c);
      out[i][1] = String.valueOf(count);
    }
    return out;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text:");
    String line = sc.nextLine();
    String[][] table = freqUsingUnique(line);
    System.out.println("Char\tFreq");
    for (String[] r : table)
      System.out.println(r[0] + "\t" + r[1]);
    
  }
}
