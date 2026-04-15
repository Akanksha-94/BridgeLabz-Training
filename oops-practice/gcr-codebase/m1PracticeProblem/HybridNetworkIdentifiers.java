import java.io.*;
import java.util.regex.*;

public class HybridNetworkIdentifiers {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String id = br.readLine().trim();
      String result = validateIdentifier(id);
      System.out.println(result);
    }
  }

  private static String validateIdentifier(String input) {
    // Pattern:
    // ^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}::([A-F0-9]{2}:){5}[A-F0-9]{2}$
    Pattern p = Pattern.compile("^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}::([A-F0-9]{2}:){5}[A-F0-9]{2}$");
    Matcher m = p.matcher(input);
    if (m.matches()) {
      return "AUTHENTIC DEVICE";
    } else {
      return "REJECTED DEVICE";
    }
  }
}