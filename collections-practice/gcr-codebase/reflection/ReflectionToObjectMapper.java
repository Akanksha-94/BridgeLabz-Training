import java.lang.reflect.Field;
import java.util.Map;

public class ReflectionToObjectMapper {
  public static <T> T toObject(Class<T> clazz, Map<String, Object> props) throws Exception {
    T instance = clazz.getDeclaredConstructor().newInstance();
    for (Field f : clazz.getDeclaredFields()) {
      if (props.containsKey(f.getName())) {
        f.setAccessible(true);
        f.set(instance, props.get(f.getName()));
      }
    }
    return instance;
  }

  public static class Bean {
    public String name;
    public int age;

    public String toString() {
      return name + ":" + age;
    }
  }

  public static void main(String[] args) throws Exception {
    java.util.Map<String, Object> m = new java.util.HashMap<>();
    m.put("name", "sam");
    m.put("age", 25);
    Bean b = toObject(Bean.class, m);
    System.out.println(b);
  }
}
