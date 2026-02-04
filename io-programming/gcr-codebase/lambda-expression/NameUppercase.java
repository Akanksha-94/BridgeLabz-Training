import java.util.*;
import java.util.stream.Collectors;

public class NameUppercase {
  public static void main(String[] args) {
    List<String> employees = Arrays.asList("Alice", "bob", "Charlie", "diana");

    System.out.println("Original names: " + employees);

    // Use method reference String::toUpperCase in a stream
    List<String> upper = employees.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());

    System.out.println("Uppercased names: " + upper);
  }
}