import java.io.*;

public class TemplateProcessor {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String template = br.readLine().trim();
      String result = process(template);
      System.out.println(result);
    }
  }

  private static String process(String template) {
    StringBuilder sb = new StringBuilder();
    int j = 0;
    while (j < template.length()) {
      if (template.startsWith("${", j)) {
        int end = template.indexOf("}", j);
        if (end == -1) {
          sb.append(template.charAt(j));
          j++;
          continue;
        }
        String placeholder = template.substring(j + 2, end);
        String replacement = processPlaceholder(placeholder);
        sb.append(replacement);
        j = end + 1;
      } else {
        sb.append(template.charAt(j));
        j++;
      }
    }
    return sb.toString();
  }

  private static String processPlaceholder(String ph) {
    int colon = ph.indexOf(":");
    if (colon == -1)
      return "INVALID";
    String type = ph.substring(0, colon);
    String value = ph.substring(colon + 1);
    if (type.equals("DATE")) {
      return convertDate(value);
    } else if (type.equals("UPPER")) {
      return value.toUpperCase();
    } else if (type.equals("LOWER")) {
      return value.toLowerCase();
    } else if (type.equals("REPEAT")) {
      return repeat(value);
    } else {
      return "INVALID";
    }
  }

  private static String convertDate(String date) {
    String[] parts = date.split("-");
    if (parts.length != 3)
      return "INVALID";
    try {
      int dd = Integer.parseInt(parts[0]);
      int mm = Integer.parseInt(parts[1]);
      int yyyy = Integer.parseInt(parts[2]);
      if (dd < 1 || dd > 31 || mm < 1 || mm > 12 || yyyy < 1000 || yyyy > 9999)
        return "INVALID";
      return String.format("%04d/%02d/%02d", yyyy, mm, dd);
    } catch (Exception e) {
      return "INVALID";
    }
  }

  private static String repeat(String value) {
    String[] parts = value.split(",");
    if (parts.length != 2)
      return "INVALID";
    String word = parts[0];
    try {
      int count = Integer.parseInt(parts[1]);
      if (count < 0)
        return "INVALID";
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < count; i++) {
        sb.append(word);
      }
      return sb.toString();
    } catch (Exception e) {
      return "INVALID";
    }
  }
}