import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotificationFilter {

  static class Alert {
    String id;
    String type; // e.g., "medication", "vitals", "system"
    int severity; // 1 low - 5 critical
    String message;

    Alert(String id, String type, int severity, String message) {
      this.id = id;
      this.type = type;
      this.severity = severity;
      this.message = message;
    }

    @Override
    public String toString() {
      return String.format("Alert{id=%s, type=%s, severity=%d, message='%s'}", id, type, severity, message);
    }
  }

  public static void main(String[] args) {
    List<Alert> alerts = Arrays.asList(
        new Alert("A1", "vitals", 5, "Heart rate critical"),
        new Alert("A2", "medication", 3, "Dose due"),
        new Alert("A3", "system", 2, "Device battery low"),
        new Alert("A4", "vitals", 4, "Blood pressure high"));

    // User preference: show only critical alerts (severity >= 4)
    Predicate<Alert> criticalOnly = alert -> alert.severity >= 4;

    // Or user preference: show only medication alerts
    Predicate<Alert> medicationOnly = alert -> "medication".equalsIgnoreCase(alert.type);

    System.out.println("--- Filter: critical only ---");
    List<Alert> critical = alerts.stream().filter(criticalOnly).collect(Collectors.toList());
    critical.forEach(System.out::println);

    System.out.println("\n--- Filter: medication only ---");
    List<Alert> meds = alerts.stream().filter(medicationOnly).collect(Collectors.toList());
    meds.forEach(System.out::println);

    // Combine predicates: critical medication alerts
    System.out.println("\n--- Filter: medication AND critical ---");
    List<Alert> medsAndCritical = alerts.stream()
        .filter(medicationOnly.and(criticalOnly))
        .collect(Collectors.toList());
    medsAndCritical.forEach(System.out::println);
  }
}