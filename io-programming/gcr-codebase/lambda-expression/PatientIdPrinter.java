import java.util.*;

public class PatientIdPrinter {
  public static void main(String[] args) {
    List<String> patientIds = Arrays.asList("PT-1001", "PT-1002", "PT-1003", "PT-1004");

    System.out.println("--- Patient IDs ---");
    // Method reference to print each id
    patientIds.forEach(System.out::println);
  }
}