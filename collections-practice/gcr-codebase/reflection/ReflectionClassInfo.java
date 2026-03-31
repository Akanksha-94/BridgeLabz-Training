import java.lang.reflect.*;

public class ReflectionClassInfo {
  public static void main(String[] args) throws Exception {
    String name = args.length > 0 ? args[0] : "java.lang.String";
    Class<?> c = Class.forName(name);
    System.out.println("Class: " + c.getName());
    System.out.println("Constructors:");
    for (Constructor<?> cons : c.getDeclaredConstructors())
      System.out.println("  " + cons);
    System.out.println("Fields:");
    for (Field f : c.getDeclaredFields())
      System.out.println("  " + f);
    System.out.println("Methods:");
    for (Method m : c.getDeclaredMethods())
      System.out.println("  " + m);
  }
}
