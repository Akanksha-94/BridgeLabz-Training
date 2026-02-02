import java.lang.reflect.Method;

public class ReflectionMethodTiming {
  public static class Work {
    public void quick() {
      int s = 0;
      for (int i = 0; i < 1000; i++)
        s += i;
    }

    public void waitA() throws Exception {
      Thread.sleep(30);
    }
  }

  public static void main(String[] args) throws Exception {
    Work w = new Work();
    for (Method m : w.getClass().getDeclaredMethods()) {
      if (m.getParameterCount() == 0) {
        long s = System.nanoTime();
        m.invoke(w);
        long e = System.nanoTime();
        System.out.println(m.getName() + " took " + (e - s) + "ns");
      }
    }
  }
}
