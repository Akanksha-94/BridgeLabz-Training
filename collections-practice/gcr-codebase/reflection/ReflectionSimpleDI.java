import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

class ServiceA {
  public String ping() {
    return "pong";
  }
}

class Consumer {
  @Inject
  ServiceA serviceA;

  public String use() {
    return serviceA.ping();
  }
}

public class ReflectionSimpleDI {
  public static <T> T create(Class<T> clazz) throws Exception {
    T instance = clazz.getDeclaredConstructor().newInstance();
    for (Field f : clazz.getDeclaredFields()) {
      if (f.isAnnotationPresent(Inject.class)) {
        Class<?> dep = f.getType();
        Object d = dep.getDeclaredConstructor().newInstance();
        f.setAccessible(true);
        f.set(instance, d);
      }
    }
    return instance;
  }

  public static void main(String[] args) throws Exception {
    Consumer c = create(Consumer.class);
    System.out.println(c.use());
  }
}
