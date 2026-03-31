import java.util.Arrays;

public class FactorsProgram {
  public static int[] factors(int n) {
    return NumberChecker.factors(n);
  }

  public static int greatestFactor(int n) {
    return NumberChecker.greatestProperFactor(n);
  }

  public static int sumFactors(int[] a) {
    return NumberChecker.sumArray(a);
  }

  public static long productFactors(int[] a) {
    return NumberChecker.productArray(a);
  }

  public static long productCubeFactors(int[] a) {
    return NumberChecker.productCubeFactors(a);
  }

  public static void main(String[] args) {
    int n = 360; // example; change as needed or call with input
    int[] f = factors(n);
    System.out.println("Factors of " + n + ": " + Arrays.toString(f));
    System.out.println("Greatest proper factor: " + greatestFactor(n));
    System.out.println("Sum of factors: " + sumFactors(f));
    System.out.println("Product of factors: " + productFactors(f));
    System.out.println("Product of cubes of factors: " + productCubeFactors(f));
  }
}
