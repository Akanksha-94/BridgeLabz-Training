import java.util.HashSet;
import java.util.Random;

public class OTPGenerator {
  public static int generateOTP() {
    Random r = new Random();
    return 100000 + r.nextInt(900000);
  }

  public static boolean uniqueOTPs(int[] otps) {
    HashSet<Integer> s = new HashSet<>();
    for (int o : otps)
      s.add(o);
    return s.size() == otps.length;
  }

  public static void main(String[] args) {
    int[] otps = new int[10];
    for (int i = 0; i < 10; i++)
      otps[i] = generateOTP();
    System.out.println("Generated OTPs:");
    for (int o : otps)
      System.out.println(o);
    System.out.println("All unique? " + uniqueOTPs(otps));
  }
}
