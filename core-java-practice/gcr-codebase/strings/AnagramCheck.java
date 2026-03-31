import java.util.Scanner;

public class AnagramCheck {
  static boolean isAnagram(String a, String b) {
    if (a.length() != b.length())
      return false;
    int[] freq = new int[256];
    for (int i = 0; i < a.length(); i++) {
      freq[(int) a.charAt(i)]++;
      freq[(int) b.charAt(i)]--;
    }
    for (int v : freq)
      if (v != 0)
        return false;
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter first text:");
    String a = sc.nextLine();
    System.out.println("Enter second text:");
    String b = sc.nextLine();
    System.out.println("Are anagrams? " + isAnagram(a, b));
    
  }
}
