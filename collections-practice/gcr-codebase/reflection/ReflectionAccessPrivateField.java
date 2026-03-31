import java.lang.reflect.Field;

public class ReflectionAccessPrivateField {
  static class Person {
    private int age;

    public Person(int age) {
      this.age = age;
    }
  }

  public static void main(String[] args) throws Exception {
    Person p = new Person(20);
    Field f = Person.class.getDeclaredField("age");
    f.setAccessible(true);
    System.out.println("before: " + f.getInt(p));
    f.setInt(p, 35);
    System.out.println("after: " + f.getInt(p));
  }
}
