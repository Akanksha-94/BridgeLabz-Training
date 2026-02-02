import java.lang.annotation.*;
import java.lang.reflect.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TaskInfo {
  String priority();

  String assignedTo();
}

public class Exercise4 {
  static class TaskManager {
    @TaskInfo(priority = "HIGH", assignedTo = "Alice")
    public void task() {
      System.out.println("Task executed");
    }
  }

  public static void main(String[] args) throws Exception {
    TaskManager tm = new TaskManager();
    Method m = tm.getClass().getMethod("task");
    if (m.isAnnotationPresent(TaskInfo.class)) {
      TaskInfo t = m.getAnnotation(TaskInfo.class);
      System.out.println(t.priority() + "," + t.assignedTo());
    }
    m.invoke(tm);
  }
}
