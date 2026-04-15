import java.io.*;
import java.util.*;

public class URLValidationSystem {
  private static Set<String> seen = new HashSet<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String url = br.readLine().trim();
      String result = validateURL(url);
      System.out.println(result);
    }
  }

  private static String validateURL(String url) {
    if (seen.contains(url)) {
      return "DUPLICATE URL FOUND";
    }
    seen.add(url);
    // Check format: http:// or https:// website domain
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
      return "INVALID URL: protocol is invalid";
    }
    String protocol = url.startsWith("https://") ? "https://" : "http://";
    String rest = url.substring(protocol.length());
    int lastDot = rest.lastIndexOf('.');
    if (lastDot == -1) {
      return "INVALID URL: format is invalid";
    }
    String website = rest.substring(0, lastDot);
    String domain = rest.substring(lastDot);
    // Check domain
    if (!domain.equals(".com") && !domain.equals(".co") && !domain.equals(".in") && !domain.equals(".org")
        && !domain.equals(".gov")) {
      return "INVALID URL: domain is invalid";
    }
    // Check website: lowercase letters, <=10 chars
    if (website.length() > 10 || website.isEmpty()) {
      return "INVALID URL: website name is invalid";
    }
    for (char c : website.toCharArray()) {
      if (!Character.isLowerCase(c)) {
        return "INVALID URL: website name is invalid";
      }
    }
    return "VALID URL";
  }
}