import java.io.*;
import java.util.regex.*;

public class ExpressionTagScoring {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String result = evaluate(line);
      System.out.println(result);
    }
  }

  private static String evaluate(String input) {
    Pattern p = Pattern.compile("\\[([A-Z]+):([^\\]]+)\\]");
    Matcher m = p.matcher(input);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      String op = m.group(1);
      String expr = m.group(2);
      String replacement = processTag(op, expr);
      m.appendReplacement(sb, replacement);
    }
    m.appendTail(sb);
    return sb.toString();
  }

  private static String processTag(String op, String expr) {
    if (!op.equals("SUM") && !op.equals("MUL") && !op.equals("MAX") && !op.equals("MIN")) {
      return "ERROR";
    }
    String[] nums = expr.split(",");
    if (nums.length < 2)
      return "ERROR";
    int[] values = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      String num = nums[i].trim();
      if (num.isEmpty())
        return "ERROR";
      if (num.equals("0")) {
        values[i] = 0;
      } else {
        if (num.startsWith("0") || num.startsWith("-0"))
          return "ERROR";
        try {
          values[i] = Integer.parseInt(num);
        } catch (Exception e) {
          return "ERROR";
        }
      }
    }
    if (op.equals("SUM")) {
      int sum = 0;
      for (int v : values)
        sum += v;
      return String.valueOf(sum);
    } else if (op.equals("MUL")) {
      int mul = 1;
      for (int v : values)
        mul *= v;
      return String.valueOf(mul);
    } else if (op.equals("MAX")) {
      int max = Integer.MIN_VALUE;
      for (int v : values)
        if (v > max)
          max = v;
      return String.valueOf(max);
    } else if (op.equals("MIN")) {
      int min = Integer.MAX_VALUE;
      for (int v : values)
        if (v < min)
          min = v;
      return String.valueOf(min);
    }
    return "ERROR";
  }
}