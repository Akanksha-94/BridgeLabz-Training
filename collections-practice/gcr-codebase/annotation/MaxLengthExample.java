import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
  int value();
}

public class MaxLengthExample {
  @MaxLength(5)
  private String username;

  public MaxLengthExample(String username) {
    this.username = username;
    validate();
  }

  private void validate() {
    for (Field f : this.getClass().getDeclaredFields()) {
      if (f.isAnnotationPresent(MaxLength.class)) {
        MaxLength m = f.getAnnotation(MaxLength.class);
        try {
          f.setAccessible(true);
          Object val = f.get(this);
          if (val instanceof String) {
            String s = (String) val;
            if (s.length() > m.value())
              throw new IllegalArgumentException("Field " + f.getName() + " exceeds max length");
          }
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static void main(String[] args) {
    new MaxLengthExample("short");
    try {
      new MaxLengthExample("toolongname");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
