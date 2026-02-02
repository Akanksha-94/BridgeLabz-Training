import java.lang.reflect.Method;

public class ReflectionDynamicMethodInvocation {
  public static class MathOperations {
    public int add(int a, int b) {
      return a + b;
    }

    public int subtract(int a, int b) {
      return a - b;
    }

    public int multiply(int a, int b) {
      return a * b;
    }
  }

  public static void main(String[] args) throws Exception {
    String method = args.length > 0 ? args[0] : "add";
    int x = args.length > 1 ? Integer.parseInt(args[1]) : 5;
    int y = args.length > 2 ? Integer.parseInt(args[2]) : 3;
    MathOperations m = new MathOperations();
    Method meth = MathOperations.class.getMethod(method, int.class, int.class);
    Object r = meth.invoke(m, x, y);
    System.out.println(r);
  }
}
