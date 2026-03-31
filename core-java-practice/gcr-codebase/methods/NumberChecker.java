import java.util.ArrayList;
import java.util.Arrays;

public class NumberChecker {
  public static int countDigits(long n) {
    if (n == 0)
      return 1;
    n = Math.abs(n);
    int c = 0;
    while (n > 0) {
      n /= 10;
      c++;
    }
    return c;
  }

  public static int[] storeDigits(long n) {
    n = Math.abs(n);
    int len = countDigits(n);
    int[] d = new int[len];
    for (int i = len - 1; i >= 0; i--) {
      d[i] = (int) (n % 10);
      n /= 10;
    }
    return d;
  }

  public static boolean isDuck(int[] digits) {
    for (int x : digits)
      if (x == 0)
        return true;
    return false;
  }

  public static boolean isArmstrong(int[] digits) {
    int p = digits.length;
    int sum = 0;
    for (int d : digits)
      sum += Math.pow(d, p);
    int val = 0;
    for (int d : digits)
      val = val * 10 + d;
    return sum == val;
  }

  public static int[] largestAndSecondLargest(int[] a) {
    int largest = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
    for (int v : a) {
      if (v > largest) {
        second = largest;
        largest = v;
      } else if (v > second && v != largest)
        second = v;
    }
    return new int[] { largest, second };
  }

  public static int[] smallestAndSecondSmallest(int[] a) {
    int smallest = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
    for (int v : a) {
      if (v < smallest) {
        second = smallest;
        smallest = v;
      } else if (v < second && v != smallest)
        second = v;
    }
    return new int[] { smallest, second };
  }

  public static int sumDigits(int[] digits) {
    int s = 0;
    for (int d : digits)
      s += d;
    return s;
  }

  public static int sumSquares(int[] digits) {
    int s = 0;
    for (int d : digits)
      s += d * d;
    return s;
  }

  public static boolean isHarshad(int[] digits) {
    int sum = sumDigits(digits);
    int val = toNumber(digits);
    return sum != 0 && val % sum == 0;
  }

  public static int[][] digitFrequency(int[] digits) {
    int[] freq = new int[10];
    for (int d : digits)
      freq[d]++;
    int[][] res = new int[10][2];
    for (int i = 0; i < 10; i++) {
      res[i][0] = i;
      res[i][1] = freq[i];
    }
    return res;
  }

  public static int[] reverse(int[] a) {
    int[] r = Arrays.copyOf(a, a.length);
    for (int i = 0; i < r.length / 2; i++) {
      int t = r[i];
      r[i] = r[r.length - 1 - i];
      r[r.length - 1 - i] = t;
    }
    return r;
  }

  public static boolean arraysEqual(int[] a, int[] b) {
    return Arrays.equals(a, b);
  }

  public static boolean isPalindrome(int[] digits) {
    return arraysEqual(digits, reverse(digits));
  }

  public static boolean isPrime(long n) {
    if (n <= 1)
      return false;
    if (n <= 3)
      return true;
    if (n % 2 == 0)
      return false;
    for (long i = 3; i * i <= n; i += 2)
      if (n % i == 0)
        return false;
    return true;
  }

  public static boolean isNeon(long n) {
    long sq = n * n;
    int s = 0;
    while (sq > 0) {
      s += sq % 10;
      sq /= 10;
    }
    return s == n;
  }

  public static boolean isSpy(int[] digits) {
    int s = 1;
    int sum = 0;
    for (int d : digits) {
      sum += d;
      s *= d;
    }
    return sum == s;
  }

  public static boolean isAutomorphic(long n) {
    long sq = n * n;
    String s = String.valueOf(n);
    return String.valueOf(sq).endsWith(s);
  }

  public static boolean isBuzz(long n) {
    return n % 7 == 0 || n % 10 == 7;
  }

  public static int sumProperDivisors(int n) {
    if (n <= 1)
      return 0;
    int s = 1;
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        s += i;
        int j = n / i;
        if (j != i)
          s += j;
      }
    }
    return s;
  }

  public static boolean isPerfect(int n) {
    return n > 0 && sumProperDivisors(n) == n;
  }

  public static boolean isAbundant(int n) {
    return n > 0 && sumProperDivisors(n) > n;
  }

  public static boolean isDeficient(int n) {
    return n > 0 && sumProperDivisors(n) < n;
  }

  public static long factorial(int n) {
    long f = 1;
    for (int i = 2; i <= n; i++)
      f *= i;
    return f;
  }

  public static boolean isStrong(int[] digits) {
    long sum = 0;
    for (int d : digits)
      sum += factorial(d);
    return sum == toNumber(digits);
  }

  private static int toNumber(int[] digits) {
    int val = 0;
    for (int d : digits)
      val = val * 10 + d;
    return val;
  }

  public static int[] factors(int n) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 1; i <= n; i++)
      if (n % i == 0)
        list.add(i);
    return list.stream().mapToInt(Integer::intValue).toArray();
  }

  public static int greatestProperFactor(int n) {
    int[] f = factors(n);
    int max = 1;
    for (int v : f)
      if (v < n && v > max)
        max = v;
    return max;
  }

  public static int sumArray(int[] a) {
    int s = 0;
    for (int v : a)
      s += v;
    return s;
  }

  public static long productArray(int[] a) {
    long p = 1;
    for (int v : a)
      p *= v;
    return p;
  }

  public static long productCubeFactors(int[] a) {
    long p = 1;
    for (int v : a)
      p *= (long) Math.pow(v, 3);
    return p;
  }
}
