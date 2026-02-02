import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

public class LogExecutionTimeExample {
  @LogExecutionTime
  public void fast() {
    int s = 0;
    for (int i = 0; i < 1000; i++)
      s += i;
  }

  @LogExecutionTime
  public void slow() throws Exception {
    Thread.sleep(50);
  }

  public static void main(String[] args) throws Exception {
    LogExecutionTimeExample ex = new LogExecutionTimeExample();
    for (Method m : ex.getClass().getDeclaredMethods()) {
      if (m.isAnnotationPresent(LogExecutionTime.class)) {
        long s = System.nanoTime();
        m.invoke(ex);
        long e = System.nanoTime();
        System.out.println(m.getName() + " took " + (e - s) + "ns");
      }
    }
  }
}
