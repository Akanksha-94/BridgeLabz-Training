import java.util.Scanner;

public class FrequencyNestedLoops {
  static String[][] freqNested(String s) {
    char[] arr = s.toCharArray();
    int n = arr.length;
    int[] freq = new int[n];
    for (int i = 0; i < n; i++)
      freq[i] = 1;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 0)
        continue;
      for (int j = i + 1; j < n; j++) {
        if (arr[i] == arr[j]) {
          freq[i]++;
          arr[j] = 0;
        }
      }
    }
    int count = 0;
    for (int i = 0; i < n; i++)
      if (arr[i] != 0)
        count++;
    String[][] out = new String[count][2];
    int idx = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] != 0) {
        out[idx][0] = String.valueOf(arr[i]);
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
    String[][] table = freqNested(line);
    System.out.println("Char\tFreq");
    for (String[] r : table)
      System.out.println(r[0] + "\t" + r[1]);
    
  }
}
