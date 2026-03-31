import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
  String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
  BugReport[] value();
}

public class Exercise5 {
  @BugReport(description = "NullPointer")
  @BugReport(description = "Wrong sum")
  public void buggy() {
  }

  public static void main(String[] args) throws Exception {
    Method m = Exercise5.class.getMethod("buggy");
    for (BugReport b : m.getAnnotationsByType(BugReport.class))
      System.out.println(b.description());
  }
}
