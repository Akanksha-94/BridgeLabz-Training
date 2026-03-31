import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
  String name();
}

@Author(name = "Jane Doe")
class Document {
}

public class ReflectionRetrieveAnnotation {
  public static void main(String[] args) throws Exception {
    Author a = Document.class.getAnnotation(Author.class);
    System.out.println(a.name());
  }
}
