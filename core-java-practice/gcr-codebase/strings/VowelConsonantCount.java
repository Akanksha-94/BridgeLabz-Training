import java.util.Scanner;

public class VowelConsonantCount {
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

  static int[] countVowelsConsonants(String s) {
    int vowels = 0, consonants = 0;
    for (int i = 0; i < s.length(); i++) {
      String t = charType(s.charAt(i));
      if (t.equals("Vowel"))
        vowels++;
      else if (t.equals("Consonant"))
        consonants++;
    }
    return new int[] { vowels, consonants };
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a line of text:");
    String line = sc.nextLine();
    int[] res = countVowelsConsonants(line);
    System.out.println("Vowels: " + res[0]);
    System.out.println("Consonants: " + res[1]);

  }
}
