import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotificationFiltering {
    
    enum AlertType { CRITICAL, HIGH, MEDIUM, LOW, INFO }
    enum Department { ICU, EMERGENCY, GENERAL, PEDIATRICS, SURGERY }
    
    static class PatientAlert {
        String patientId;
        AlertType type;
        Department department;
        String message;
        
        PatientAlert(String patientId, AlertType type, Department department, String message) {
            this.patientId = patientId;
            this.type = type;
            this.department = department;
            this.message = message;
        }
        
        @Override
        public String toString() {
            return String.format("[%s] %s - %s: %s", type, department, patientId, message);
        }
    }
    
    static class UserPreferences {
        Set<AlertType> allowedTypes;
        Set<Department> allowedDepartments;
        
        UserPreferences(Set<AlertType> types, Set<Department> departments) {
            this.allowedTypes = types;
            this.allowedDepartments = departments;
        }
    }
    
    public static void main(String[] args) {
        List<PatientAlert> alerts = Arrays.asList(
            new PatientAlert("P001", AlertType.CRITICAL, Department.ICU, "Blood pressure dropping"),
            new PatientAlert("P002", AlertType.HIGH, Department.EMERGENCY, "Requires immediate attention"),
            new PatientAlert("P003", AlertType.MEDIUM, Department.GENERAL, "Medication reminder"),
            new PatientAlert("P004", AlertType.CRITICAL, Department.SURGERY, "Post-op complications"),
            new PatientAlert("P005", AlertType.LOW, Department.PEDIATRICS, "Routine checkup due"),
            new PatientAlert("P006", AlertType.INFO, Department.GENERAL, "Discharge paperwork ready")
        );
        
        // ICU Doctor preferences - only critical and high alerts from ICU and Surgery
        UserPreferences icuDoctor = new UserPreferences(
            Set.of(AlertType.CRITICAL, AlertType.HIGH),
            Set.of(Department.ICU, Department.SURGERY)
        );
        
        // General Nurse preferences - medium and above from General and Pediatrics
        UserPreferences generalNurse = new UserPreferences(
            Set.of(AlertType.CRITICAL, AlertType.HIGH, AlertType.MEDIUM),
            Set.of(Department.GENERAL, Department.PEDIATRICS)
        );
        
        System.out.println("All Alerts:");
        alerts.forEach(System.out::println);
        
        // Filter for ICU Doctor using lambda with Predicate
        Predicate<PatientAlert> icuFilter = alert -> 
            icuDoctor.allowedTypes.contains(alert.type) && 
            icuDoctor.allowedDepartments.contains(alert.department);
        
        System.out.println("\nICU Doctor Filtered Alerts:");
        alerts.stream()
              .filter(icuFilter)
              .forEach(System.out::println);
        
        // Filter for General Nurse using lambda with Predicate
        Predicate<PatientAlert> nurseFilter = alert -> 
            generalNurse.allowedTypes.contains(alert.type) && 
            generalNurse.allowedDepartments.contains(alert.department);
        
        System.out.println("\nGeneral Nurse Filtered Alerts:");
        alerts.stream()
              .filter(nurseFilter)
              .forEach(System.out::println);
        
        // Emergency filter - only critical alerts
        System.out.println("\nEmergency Alerts Only:");
        alerts.stream()
              .filter(alert -> alert.type == AlertType.CRITICAL)
              .forEach(System.out::println);
    }
}