import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {
}

public class CacheResultExample {
  public static class Expensive {
    @CacheResult
    public int fib(int n) {
      if (n < 2)
        return n;
      return fib(n - 1) + fib(n - 2);
    }
  }

  static class Cache {
    static Map<String, Object> map = new HashMap<>();
  }

  public static Object invokeWithCache(Object obj, String methodName, Object... args) throws Exception {
    Method target = null;
    for (Method m : obj.getClass().getDeclaredMethods())
      if (m.getName().equals(methodName)) {
        target = m;
        break;
      }
    if (target == null)
      throw new NoSuchMethodException();
    if (!target.isAnnotationPresent(CacheResult.class))
      return target.invoke(obj, args);
    String key = methodName + Arrays.deepToString(args);
    if (Cache.map.containsKey(key))
      return Cache.map.get(key);
    Object r = target.invoke(obj, args);
    Cache.map.put(key, r);
    return r;
  }

  public static void main(String[] args) throws Exception {
    Expensive e = new Expensive();
    long s = System.nanoTime();
    Object r1 = invokeWithCache(e, "fib", 30);
    long t1 = System.nanoTime();
    Object r2 = invokeWithCache(e, "fib", 30);
    long t2 = System.nanoTime();
    System.out.println("first:" + r1 + " time " + (t1 - s));
    System.out.println("second:" + r2 + " time " + (t2 - t1));
  }
}
