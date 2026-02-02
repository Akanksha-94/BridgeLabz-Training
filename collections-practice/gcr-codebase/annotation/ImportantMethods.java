import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
  String level() default "HIGH";
}

public class ImportantMethods {
  @ImportantMethod(level = "HIGH")
  public void a() {
  }

  @ImportantMethod
  public void b() {
  }

  public static void main(String[] args) throws Exception {
    for (Method m : ImportantMethods.class.getDeclaredMethods()) {
      if (m.isAnnotationPresent(ImportantMethod.class))
        System.out.println(m.getName() + " - " + m.getAnnotation(ImportantMethod.class).level());
    }
  }
}
