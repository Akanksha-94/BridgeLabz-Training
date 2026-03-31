import java.lang.reflect.Field;

public class ReflectionModifyStaticField {
  static class Configuration {
    private static String API_KEY = "initial";

    public static String getKey() {
      return API_KEY;
    }
  }

  public static void main(String[] args) throws Exception {
    Field f = Configuration.class.getDeclaredField("API_KEY");
    f.setAccessible(true);
    System.out.println("before: " + Configuration.getKey());
    f.set(null, "new-key-123");
    System.out.println("after: " + Configuration.getKey());
  }
}
