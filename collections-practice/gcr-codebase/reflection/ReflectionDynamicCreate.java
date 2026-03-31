public class ReflectionDynamicCreate {
  public static class Student {
    private String name;

    public Student() {
      this.name = "unknown";
    }

    public Student(String n) {
      this.name = n;
    }

    public String toString() {
      return "Student(" + name + ")";
    }
  }

  public static void main(String[] args) throws Exception {
    Class<?> c = Class.forName("ReflectionDynamicCreate$Student");
    Object s = c.getDeclaredConstructor().newInstance();
    System.out.println(s);
    Object s2 = c.getDeclaredConstructor(String.class).newInstance("Alice");
    System.out.println(s2);
  }
}
