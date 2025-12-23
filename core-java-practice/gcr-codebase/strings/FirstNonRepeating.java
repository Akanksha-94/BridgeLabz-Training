import java.util.Scanner;

public class FirstNonRepeating {
  static char firstNonRepeating(String s) {
    int[] freq = new int[256];
    for (int i = 0;; i++) {
      try {
        char c = s.charAt(i);
        freq[(int) c]++;
      } catch (IndexOutOfBoundsException e) {
        break;
      }
    }
    for (int i = 0;; i++) {
      try {
        char c = s.charAt(i);
        if (freq[(int) c] == 1)
          return c;
      } catch (IndexOutOfBoundsException e) {
        break;
      }
    }
    return '\0';
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text:");
    String line = sc.nextLine();
    char c = firstNonRepeating(line);
    if (c == '\0')
      System.out.println("No non-repeating character found.");
    else
      System.out.println("First non-repeating character: " + c);
    
  }
}
