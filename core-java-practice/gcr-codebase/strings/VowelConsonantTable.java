import java.util.Scanner;

public class VowelConsonantTable {
  static String charType(char ch) {
    char c = ch;
    if (c >= 'A' && c <= 'Z')
      c = (char) (c + 32);
    if (c >= 'a' && c <= 'z') {
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
        return "Vowel";
      return "Consonant";
    }
    return "Not a Letter";
  }

  static String[][] classify(String s) {
    String[][] out = new String[s.length()][2];
    for (int i = 0; i < s.length(); i++) {
      out[i][0] = String.valueOf(s.charAt(i));
      out[i][1] = charType(s.charAt(i));
    }
    return out;
  }

  static void printTable(String[][] table) {
    System.out.println("Char\tType");
    for (String[] row : table)
      System.out.println(row[0] + "\t" + row[1]);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a line of text:");
    String line = sc.nextLine();
    String[][] table = classify(line);
    printTable(table);

  }
}
