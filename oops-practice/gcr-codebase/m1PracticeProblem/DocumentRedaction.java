import java.io.*;
import java.util.regex.*;

public class DocumentRedaction {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String processed = processLine(line);
      System.out.println(processed);
    }
  }

  private static String processLine(String input) {
    String result = input;
    // Rule 1: Mask National ID
    result = result.replaceAll("ID:([A-Z]{3})(\\d{6})", "ID:XXX******");
    // Rule 2: Mask Bank Account
    result = result.replaceAll("ACCT-(\\d{4})-(\\d{4})-(\\d{4})", "ACCT---$3");
    // Rule 3: Normalize repeated words
    result = result.replaceAll("(?i)\\b(\\w+)\\s+\\1\\b", "$1");
    // Rule 4: Remove trailing special symbols
    result = result.replaceAll("([!?\\.])\\1{2,}$", "$1");
    return result;
  }
}