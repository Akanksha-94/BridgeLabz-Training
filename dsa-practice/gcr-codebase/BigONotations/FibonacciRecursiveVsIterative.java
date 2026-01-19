/**
 * Problem Statement: Recursive vs Iterative Fibonacci Computation
 * 
 * Objective:
 * Compare Recursive (O(2ⁿ)) vs Iterative (O(N)) Fibonacci solutions.
 * 
 * Approach:
 * - Recursive: Simple but exponentially slow due to repeated calculations.
 * - Iterative: Linear time, builds result from bottom up.
 * - Memoization: Caches results to optimize recursive approach.
 * 
 * Time Complexity:
 * - Recursive: O(2ⁿ) - exponential, completely infeasible for n > 40
 * - Iterative: O(N) - linear, very fast
 * - Memoization: O(N) - linear, caches intermediate results
 * 
 * Space Complexity:
 * - Recursive: O(N) - call stack depth
 * - Iterative: O(1) - only variables
 * - Memoization: O(N) - stores cached values
 * 
 * Key Learning:
 * - Exponential algorithms become completely impractical beyond a certain size
 * - Simple optimization (memoization) can reduce O(2ⁿ) to O(N)
 * - Iterative approach is always preferred for Fibonacci
 */
public class FibonacciRecursiveVsIterative {

  private static long recursiveCallCount = 0;

  /**
   * Recursive Fibonacci - O(2ⁿ)
   * EXTREMELY inefficient! Do NOT use for large n
   * Many duplicate calculations
   */
  public static long fibonacciRecursive(int n) {
    recursiveCallCount++;
    if (n <= 1) {
      return n;
    }
    return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
  }

  /**
   * Iterative Fibonacci - O(N)
   * Builds result from bottom up
   * Most efficient approach
   */
  public static long fibonacciIterative(int n) {
    if (n <= 1) {
      return n;
    }

    long a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
      long sum = a + b;
      a = b;
      b = sum;
    }
    return b;
  }

  /**
   * Fibonacci with Memoization - O(N)
   * Caches previously calculated values
   */
  public static long fibonacciMemo(int n) {
    long[] memo = new long[n + 1];
    return fibonacciMemoHelper(n, memo);
  }

  private static long fibonacciMemoHelper(int n, long[] memo) {
    if (n <= 1) {
      return n;
    }

    if (memo[n] != 0) {
      return memo[n];
    }

    memo[n] = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
    return memo[n];
  }

  /**
   * Demonstrate exponential growth
   */
  public static void demonstrateExponentialGrowth() {
    System.out.println("\n===== Exponential Growth Problem =====");
    System.out.println("Computing Fibonacci(n) recursively requires 2ⁿ operations:");
    System.out.println();

    for (int n = 5; n <= 30; n += 5) {
      long operations = (long) Math.pow(2, n);
      System.out.printf("fib(%2d): ~%,d operations%n", n, operations);
    }

    System.out.println("\nAt fib(40): ~1 trillion operations - takes minutes!");
    System.out.println("At fib(50): ~1 quadrillion operations - takes days!");
    System.out.println("This is why exponential algorithms are impractical!");
  }

  /**
   * Demonstrate duplicate calculations in recursion
   */
  public static void demonstrateDuplicateCalculations() {
    System.out.println("\n===== Duplicate Calculations in Recursive Approach =====");
    System.out.println("Computing fib(6) recursively:\n");
    System.out.println("                    fib(6)");
    System.out.println("                   /      \\");
    System.out.println("              fib(5)        fib(4)");
    System.out.println("             /      \\        /      \\");
    System.out.println("        fib(4)    fib(3)  fib(3)    fib(2)");
    System.out.println("       /    \\    /    \\   /    \\    /    \\");
    System.out.println("   fib(3) fib(2) fib(2) fib(1) fib(2) fib(1) fib(1)");
    System.out.println("\nNotice: fib(3) calculated 2 times, fib(2) calculated 3 times!");
    System.out.println("For larger n, this duplication becomes exponentially worse.");
  }

  /**
   * Measure performance comparison
   */
  public static void measurePerformance(int n) {
    System.out.println("\n--- Computing Fibonacci(" + n + ") ---");

    // Iterative (always fast)
    long startIter = System.nanoTime();
    long resultIter = fibonacciIterative(n);
    long endIter = System.nanoTime();
    double iterTime = (endIter - startIter) / 1_000_000.0;
    System.out.printf("Iterative O(N): %.3fms | Result: %d%n", iterTime, resultIter);

    // Memoization (also fast)
    long startMemo = System.nanoTime();
    long resultMemo = fibonacciMemo(n);
    long endMemo = System.nanoTime();
    double memoTime = (endMemo - startMemo) / 1_000_000.0;
    System.out.printf("Memoization O(N): %.3fms | Result: %d%n", memoTime, resultMemo);

    // Recursive (only for small n)
    if (n <= 30) {
      recursiveCallCount = 0;
      long startRec = System.nanoTime();
      long resultRec = fibonacciRecursive(n);
      long endRec = System.nanoTime();
      double recTime = (endRec - startRec) / 1_000_000.0;

      System.out.printf("Recursive O(2ⁿ): %.3fms | Result: %d | Calls: %,d%n",
          recTime, resultRec, recursiveCallCount);

      if (iterTime > 0) {
        System.out.printf("Recursive is %.0fx slower than Iterative!%n",
            recTime / iterTime);
      }
    } else {
      System.out.println("Recursive O(2ⁿ): [SKIPPED - Too slow for n > 30]");
    }
  }

  /**
   * Analyze why iterative is better
   */
  public static void analyzeIterativeAdvantages() {
    System.out.println("\n===== Why Iterative is Superior =====");
    System.out.println("1. Time Complexity:");
    System.out.println("   - Recursive: O(2ⁿ) - exponential");
    System.out.println("   - Iterative: O(N) - linear");
    System.out.println();
    System.out.println("2. Space Complexity:");
    System.out.println("   - Recursive: O(N) - call stack");
    System.out.println("   - Iterative: O(1) - only few variables");
    System.out.println();
    System.out.println("3. Stack Overflow Risk:");
    System.out.println("   - Recursive: Crashes for large N (stack overflow)");
    System.out.println("   - Iterative: Safe for any reasonable N");
    System.out.println();
    System.out.println("4. Practical Usage:");
    System.out.println("   - Recursive: Only good for learning recursion concept");
    System.out.println("   - Iterative: ALWAYS use in production code");
  }

  public static void main(String[] args) {
    System.out.println("===== Recursive vs Iterative Fibonacci =====");
    System.out.println("Recursive: O(2ⁿ) | Iterative: O(N)");

    demonstrateExponentialGrowth();
    demonstrateDuplicateCalculations();
    analyzeIterativeAdvantages();

    // Performance comparison
    System.out.println("\n===== Performance Comparison =====");
    measurePerformance(10);
    measurePerformance(20);
    measurePerformance(30);
    measurePerformance(40);
    measurePerformance(50);
    measurePerformance(100);

    System.out.println("\n\nConclusion:");
    System.out.println("- Recursive Fibonacci is O(2ⁿ) - completely impractical for n > 40");
    System.out.println("- Iterative Fibonacci is O(N) - always use this approach");
    System.out.println("- Memoization can optimize recursive to O(N) but iterative is simpler");
    System.out.println("- Exponential algorithms become unfeasible beyond a small threshold");
    System.out.println("- This demonstrates why algorithm selection is CRITICAL");
    System.out.println("- A poor algorithm can make a task completely impossible");
  }
}
