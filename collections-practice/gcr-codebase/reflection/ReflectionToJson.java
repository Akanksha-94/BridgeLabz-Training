import java.lang.reflect.Field;

public class ReflectionToJson {
  public static String toJson(Object obj) throws Exception {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    boolean first = true;
    for (Field f : obj.getClass().getDeclaredFields()) {
      f.setAccessible(true);
      if (!first)
        sb.append(",");
      sb.append("\"").append(f.getName()).append("\":");
      Object v = f.get(obj);
      if (v == null)
        sb.append("null");
      else if (v instanceof String)
        sb.append("\"").append(v).append("\"");
      else
        sb.append(v);
      first = false;
    }
    sb.append("}");
    return sb.toString();
  }

  public static class Person {
    private String name = "Ann";
    private int age = 28;
  }

  public static void main(String[] args) throws Exception {
    System.out.println(toJson(new Person()));
  }
}
