import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
  String name();
}

public class JsonFieldExample {
  public static class User {
    @JsonField(name = "user_name")
    private String username;
    @JsonField(name = "age")
    private int age;

    public User(String u, int a) {
      username = u;
      age = a;
    }
  }

  public static String toJson(Object obj) throws Exception {
    Map<String, Object> map = new LinkedHashMap<>();
    for (Field f : obj.getClass().getDeclaredFields()) {
      if (f.isAnnotationPresent(JsonField.class)) {
        f.setAccessible(true);
        JsonField jf = f.getAnnotation(JsonField.class);
        map.put(jf.name(), f.get(obj));
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    boolean first = true;
    for (Map.Entry<String, Object> e : map.entrySet()) {
      if (!first)
        sb.append(",");
      sb.append("\"").append(e.getKey()).append("\":");
      Object v = e.getValue();
      if (v instanceof String)
        sb.append("\"").append(v).append("\"");
      else
        sb.append(v);
      first = false;
    }
    sb.append("}");
    return sb.toString();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(toJson(new User("john", 30)));
  }
}
