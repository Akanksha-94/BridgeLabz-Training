import java.util.Scanner;

public class FrequencyChars {
  static String[][] frequency(String s) {
    int[] freq = new int[256];
    for (int i = 0;; i++) {
      try {
        char c = s.charAt(i);
        freq[(int) c]++;
      } catch (IndexOutOfBoundsException e) {
        break;
      }
    }
    int count = 0;
    for (int i = 0; i < 256; i++)
      if (freq[i] > 0)
        count++;
    String[][] out = new String[count][2];
    int idx = 0;
    for (int i = 0; i < 256; i++) {
      if (freq[i] > 0) {
        out[idx][0] = String.valueOf((char) i);
        out[idx][1] = String.valueOf(freq[i]);
        idx++;
      }
    }
    return out;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text:");
    String line = sc.nextLine();
    String[][] table = frequency(line);
    System.out.println("Char\tFreq");
    for (String[] r : table)
      System.out.println(r[0] + "\t" + r[1]);
    
  }
}
