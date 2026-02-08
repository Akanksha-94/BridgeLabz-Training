import java.util.*;
import java.util.stream.Collectors;

class Doctor {
    String name;
    String specialty;
    boolean availableWeekends;
    
    Doctor(String name, String specialty, boolean availableWeekends) {
        this.name = name;
        this.specialty = specialty;
        this.availableWeekends = availableWeekends;
    }
    
    @Override
    public String toString() {
        return name + " - " + specialty;
    }
}

public class HospitalDoctorAvailability {
    public static void main(String[] args) {
        List<Doctor> doctors = Arrays.asList(
            new Doctor("Dr. Smith", "Cardiology", true),
            new Doctor("Dr. Johnson", "Neurology", false),
            new Doctor("Dr. Brown", "Cardiology", true),
            new Doctor("Dr. Davis", "Orthopedics", true),
            new Doctor("Dr. Wilson", "Neurology", true),
            new Doctor("Dr. Miller", "Emergency", true)
        );
        
        List<Doctor> weekendDoctors = doctors.stream()
            .filter(doctor -> doctor.availableWeekends)
            .sorted(Comparator.comparing(doctor -> doctor.specialty))
            .collect(Collectors.toList());
        
        System.out.println("Doctors available on weekends (sorted by specialty):");
        weekendDoctors.forEach(System.out::println);
    }
}