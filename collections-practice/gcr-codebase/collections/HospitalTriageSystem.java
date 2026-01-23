import java.util.*;

/**
 * Hospital Triage System
 * Simulate a hospital triage system using a PriorityQueue where patients with
 * higher severity are treated first.
 * Example: Patients: [("John", 3), ("Alice", 5), ("Bob", 2)] → Order: Alice,
 * John, Bob
 */
public class HospitalTriageSystem {
  static class Patient {
    String name;
    int severity; // 1 (low) to 5 (critical)

    Patient(String name, int severity) {
      this.name = name;
      this.severity = severity;
    }

    @Override
    public String toString() {
      return name + " (severity: " + severity + ")";
    }
  }

  public static void main(String[] args) {
    // Create a priority queue that orders patients by severity (highest first)
    PriorityQueue<Patient> triageQueue = new PriorityQueue<>(
        (p1, p2) -> Integer.compare(p2.severity, p1.severity) // Descending order
    );

    // Example 1: Add patients
    System.out.println("=== Hospital Triage System ===\n");
    System.out.println("Patients arriving:");

    Patient patient1 = new Patient("John", 3);
    Patient patient2 = new Patient("Alice", 5);
    Patient patient3 = new Patient("Bob", 2);

    triageQueue.add(patient1);
    triageQueue.add(patient2);
    triageQueue.add(patient3);

    System.out.println("Added: " + patient1);
    System.out.println("Added: " + patient2);
    System.out.println("Added: " + patient3);

    // Treat patients in order of severity
    System.out.println("\nTreatment order:");
    int order = 1;
    while (!triageQueue.isEmpty()) {
      Patient patient = triageQueue.poll();
      System.out.println(order + ". " + patient);
      order++;
    }

    // Example 2: More patients
    System.out.println("\n\n=== Second Round ===\n");
    System.out.println("New patients arriving:");

    Patient[] patients = {
        new Patient("Carol", 4),
        new Patient("David", 1),
        new Patient("Eve", 5),
        new Patient("Frank", 3),
        new Patient("Grace", 2)
    };

    for (Patient p : patients) {
      triageQueue.add(p);
      System.out.println("Added: " + p);
    }

    System.out.println("\nTreatment order:");
    order = 1;
    while (!triageQueue.isEmpty()) {
      Patient patient = triageQueue.poll();
      System.out.println(order + ". " + patient);
      order++;
    }
  }
}
