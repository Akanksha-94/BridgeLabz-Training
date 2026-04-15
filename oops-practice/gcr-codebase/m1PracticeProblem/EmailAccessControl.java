import java.io.*;

public class EmailAccessControl {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String email = br.readLine().trim();
      if (validate(email)) {
        System.out.println("Access Granted");
      } else {
        System.out.println("Access Denied");
      }
    }
  }

  private static boolean validate(String email) {
    if (email.contains(" "))
      return false;
    String[] parts = email.split("@");
    if (parts.length != 2)
      return false;
    String domain = parts[1];
    String[] domainParts = domain.split("\\.");
    if (domainParts.length != 3 || !domainParts[1].equals("company") || !domainParts[2].equals("com"))
      return false;
    String dept = domainParts[0];
    if (!dept.equals("sales") && !dept.equals("marketing") && !dept.equals("IT") && !dept.equals("product"))
      return false;
    String local = parts[0];
    String[] plusParts = local.split("\\+");
    if (plusParts.length != 2)
      return false;
    String digits = plusParts[1];
    if (digits.length() < 4)
      return false;
    for (char c : digits.toCharArray()) {
      if (!Character.isDigit(c))
        return false;
    }
    String name = plusParts[0];
    String[] nameParts = name.split("\\.");
    if (nameParts.length != 2)
      return false;
    String first = nameParts[0];
    String last = nameParts[1];
    if (first.length() < 3 || last.length() < 3)
      return false;
    for (char c : first.toCharArray()) {
      if (!Character.isLowerCase(c))
        return false;
    }
    for (char c : last.toCharArray()) {
      if (!Character.isLowerCase(c))
        return false;
    }
    return true;
  }
}