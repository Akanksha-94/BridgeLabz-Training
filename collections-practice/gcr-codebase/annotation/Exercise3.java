import java.util.ArrayList;

public class Exercise3 {
  @SuppressWarnings("unchecked")
  public void run() {
    ArrayList list = new ArrayList();
    list.add("one");
    list.add("two");
    for (Object o : list)
      System.out.println((String) o);
  }

  public static void main(String[] args) {
    new Exercise3().run();
  }
}
