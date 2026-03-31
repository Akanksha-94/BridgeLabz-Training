import java.lang.reflect.Method;

public class ReflectionInvokePrivateMethod {
  static class Calculator {
    private int multiply(int a, int b) {
      return a * b;
    }
  }

  public static void main(String[] args) throws Exception {
    Calculator c = new Calculator();
    Method m = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);
    m.setAccessible(true);
    Object r = m.invoke(c, 6, 7);
    System.out.println(r);
  }
}
