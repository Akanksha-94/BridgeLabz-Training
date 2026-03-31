import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
  String value();
}

public class RoleAllowedExample {
  public static class Service {
    @RoleAllowed("ADMIN")
    public void admin() {
      System.out.println("admin task");
    }

    @RoleAllowed("USER")
    public void user() {
      System.out.println("user task");
    }
  }

  public static void invoke(Service s, String method, String role) throws Exception {
    Method m = s.getClass().getMethod(method);
    if (m.isAnnotationPresent(RoleAllowed.class)) {
      if (!m.getAnnotation(RoleAllowed.class).value().equals(role)) {
        System.out.println("Access Denied!");
        return;
      }
    }
    m.invoke(s);
  }

  public static void main(String[] args) throws Exception {
    Service svc = new Service();
    invoke(svc, "admin", "USER");
    invoke(svc, "admin", "ADMIN");
  }
}
