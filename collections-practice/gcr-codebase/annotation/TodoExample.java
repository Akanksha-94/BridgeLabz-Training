import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
  String task();

  String assignedTo();

  String priority() default "MEDIUM";
}

public class TodoExample {
  @Todo(task = "Refactor", assignedTo = "Bob", priority = "HIGH")
  public void a() {
  }

  @Todo(task = "Add tests", assignedTo = "Carol")
  public void b() {
  }

  public static void main(String[] args) throws Exception {
    for (Method m : TodoExample.class.getDeclaredMethods()) {
      if (m.isAnnotationPresent(Todo.class)) {
        Todo t = m.getAnnotation(Todo.class);
        System.out.println(m.getName() + " - " + t.task() + " - " + t.assignedTo() + " - " + t.priority());
      }
    }
  }
}
