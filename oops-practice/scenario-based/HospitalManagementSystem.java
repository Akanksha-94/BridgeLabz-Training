import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Hospital Management System
 * Real Scenario: Hospitals manage patients, doctors, appointments, and medical
 * records.
 * 
 * Concepts Used:
 * - OOP: Patient, Doctor, Appointment
 * - Inheritance: Person → Patient / Doctor
 * - Abstraction: HospitalService interface
 * - Polymorphism: Consultation fee calculation
 * - Exception Handling: AppointmentNotAvailableException
 */

// Custom Exception
class AppointmentNotAvailableException extends Exception {
  public AppointmentNotAvailableException(String message) {
    super(message);
  }
}

// Medical Record
class MedicalRecord {
  private String recordId;
  private String diagnosis;
  private String treatment;
  private String medication;
  private LocalDateTime visitDate;

  public MedicalRecord(String diagnosis, String treatment, String medication) {
    this.recordId = "MED" + System.currentTimeMillis();
    this.diagnosis = diagnosis;
    this.treatment = treatment;
    this.medication = medication;
    this.visitDate = LocalDateTime.now();
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return String.format("Visit: %s | Diagnosis: %-25s | Treatment: %-25s | Medication: %s",
        visitDate.format(formatter), diagnosis, treatment, medication);
  }
}

// Appointment
class Appointment {
  private String appointmentId;
  private String patientId;
  private String doctorId;
  private LocalDateTime appointmentTime;
  private String status; // SCHEDULED, COMPLETED, CANCELLED
  private String notes;

  public Appointment(String patientId, String doctorId, LocalDateTime appointmentTime) {
    this.appointmentId = "APP" + System.currentTimeMillis();
    this.patientId = patientId;
    this.doctorId = doctorId;
    this.appointmentTime = appointmentTime;
    this.status = "SCHEDULED";
    this.notes = "";
  }

  public String getAppointmentId() {
    return appointmentId;
  }

  public String getPatientId() {
    return patientId;
  }

  public String getDoctorId() {
    return doctorId;
  }

  public LocalDateTime getAppointmentTime() {
    return appointmentTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return String.format("%-15s | Patient: %-10s | Doctor: %-10s | Time: %s | Status: %s",
        appointmentId, patientId, doctorId, appointmentTime.format(formatter), status);
  }
}

// Person base class
abstract class Person {
  protected String id;
  protected String name;
  protected String email;
  protected String phone;

  public Person(String id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  abstract void displayInfo();
}

// Patient class
class Patient extends Person {
  private String bloodGroup;
  private String allergies;
  private List<MedicalRecord> medicalHistory;
  private List<String> appointmentIds;

  public Patient(String id, String name, String email, String phone, String bloodGroup, String allergies) {
    super(id, name, email, phone);
    this.bloodGroup = bloodGroup;
    this.allergies = allergies;
    this.medicalHistory = new ArrayList<>();
    this.appointmentIds = new ArrayList<>();
  }

  public String getBloodGroup() {
    return bloodGroup;
  }

  public String getAllergies() {
    return allergies;
  }

  public void addMedicalRecord(MedicalRecord record) {
    medicalHistory.add(record);
    System.out.println("✓ Medical record added for " + name);
  }

  public void addAppointment(String appointmentId) {
    appointmentIds.add(appointmentId);
  }

  public void displayMedicalHistory() {
    if (medicalHistory.isEmpty()) {
      System.out.println("No medical records available");
      return;
    }

    System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║            MEDICAL HISTORY - " + id + "                       ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════╣");

    for (MedicalRecord record : medicalHistory) {
      System.out.println(record);
    }

    System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");
  }

  @Override
  void displayInfo() {
    System.out.println("Patient " + id + " | Name: " + name + " | Blood: " + bloodGroup +
        " | Allergies: " + allergies + " | Contact: " + phone);
  }
}

// Doctor class
class Doctor extends Person {
  private String specialization;
  private double consultationFee;
  private List<String> appointmentIds;
  private int totalPatientsServed;

  public Doctor(String id, String name, String email, String phone, String specialization, double consultationFee) {
    super(id, name, email, phone);
    this.specialization = specialization;
    this.consultationFee = consultationFee;
    this.appointmentIds = new ArrayList<>();
    this.totalPatientsServed = 0;
  }

  public String getSpecialization() {
    return specialization;
  }

  public double getConsultationFee() {
    return consultationFee;
  }

  public void addAppointment(String appointmentId) {
    appointmentIds.add(appointmentId);
  }

  public void completeAppointment() {
    totalPatientsServed++;
  }

  public List<String> getAppointments() {
    return new ArrayList<>(appointmentIds);
  }

  @Override
  void displayInfo() {
    System.out.println("Doctor " + id + " | Name: " + name + " | Specialization: " + specialization +
        " | Fee: $" + String.format("%.2f", consultationFee) +
        " | Patients Served: " + totalPatientsServed);
  }
}

// Hospital Service Interface
interface HospitalService {
  void addPatient(Patient patient);

  void addDoctor(Doctor doctor);

  void bookAppointment(String patientId, String doctorId, LocalDateTime appointmentTime)
      throws AppointmentNotAvailableException;

  void completeAppointment(String appointmentId);

  void cancelAppointment(String appointmentId);

  double calculateConsultationFee(String doctorId);
}

// Hospital Service Implementation
class HospitalServiceImpl implements HospitalService {
  private Map<String, Patient> patients;
  private Map<String, Doctor> doctors;
  private Map<String, Appointment> appointments;

  public HospitalServiceImpl() {
    this.patients = new HashMap<>();
    this.doctors = new HashMap<>();
    this.appointments = new HashMap<>();
  }

  @Override
  public void addPatient(Patient patient) {
    patients.put(patient.getId(), patient);
    System.out.println("✓ Patient registered: " + patient.getName());
  }

  @Override
  public void addDoctor(Doctor doctor) {
    doctors.put(doctor.getId(), doctor);
    System.out.println("✓ Doctor added: " + doctor.getName());
  }

  @Override
  public void bookAppointment(String patientId, String doctorId, LocalDateTime appointmentTime)
      throws AppointmentNotAvailableException {
    if (!patients.containsKey(patientId)) {
      throw new AppointmentNotAvailableException("Patient not found");
    }
    if (!doctors.containsKey(doctorId)) {
      throw new AppointmentNotAvailableException("Doctor not found");
    }

    // Check for slot availability (simple check - no overlapping appointments)
    for (Appointment apt : appointments.values()) {
      if (apt.getDoctorId().equals(doctorId) && apt.getAppointmentTime().equals(appointmentTime) &&
          "SCHEDULED".equals(apt.getStatus())) {
        throw new AppointmentNotAvailableException("Doctor not available at this time");
      }
    }

    Appointment appointment = new Appointment(patientId, doctorId, appointmentTime);
    appointments.put(appointment.getAppointmentId(), appointment);

    patients.get(patientId).addAppointment(appointment.getAppointmentId());
    doctors.get(doctorId).addAppointment(appointment.getAppointmentId());

    System.out.println("✓ Appointment booked: " + appointment.getAppointmentId());
  }

  @Override
  public void completeAppointment(String appointmentId) {
    Appointment appointment = appointments.get(appointmentId);
    if (appointment != null) {
      appointment.setStatus("COMPLETED");
      doctors.get(appointment.getDoctorId()).completeAppointment();
      System.out.println("✓ Appointment completed: " + appointmentId);
    }
  }

  @Override
  public void cancelAppointment(String appointmentId) {
    Appointment appointment = appointments.get(appointmentId);
    if (appointment != null) {
      appointment.setStatus("CANCELLED");
      System.out.println("✓ Appointment cancelled: " + appointmentId);
    }
  }

  @Override
  public double calculateConsultationFee(String doctorId) {
    Doctor doctor = doctors.get(doctorId);
    return doctor != null ? doctor.getConsultationFee() : 0;
  }

  public Map<String, Appointment> getAppointments() {
    return appointments;
  }

  public Map<String, Patient> getPatients() {
    return patients;
  }

  public Map<String, Doctor> getDoctors() {
    return doctors;
  }
}

public class HospitalManagementSystem {
  public static void main(String[] args) {
    HospitalService hospitalService = new HospitalServiceImpl();

    System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║       HOSPITAL MANAGEMENT SYSTEM - DEMONSTRATION                ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Register patients
    System.out.println("--- PHASE 1: Register Patients ---");
    Patient patient1 = new Patient("PAT001", "Alice Johnson", "alice@email.com", "555-1001", "O+", "None");
    Patient patient2 = new Patient("PAT002", "Bob Smith", "bob@email.com", "555-1002", "A+", "Penicillin");
    Patient patient3 = new Patient("PAT003", "Carol White", "carol@email.com", "555-1003", "B+", "Sulfa");

    hospitalService.addPatient(patient1);
    hospitalService.addPatient(patient2);
    hospitalService.addPatient(patient3);

    // PHASE 2: Add doctors
    System.out.println("\n--- PHASE 2: Add Doctors ---");
    Doctor doctor1 = new Doctor("DOC001", "Dr. Smith", "smith@hospital.com", "555-2001", "Cardiology", 150.0);
    Doctor doctor2 = new Doctor("DOC002", "Dr. Brown", "brown@hospital.com", "555-2002", "Neurology", 200.0);
    Doctor doctor3 = new Doctor("DOC003", "Dr. Green", "green@hospital.com", "555-2003", "Pediatrics", 100.0);

    hospitalService.addDoctor(doctor1);
    hospitalService.addDoctor(doctor2);
    hospitalService.addDoctor(doctor3);

    // PHASE 3: Book appointments
    System.out.println("\n--- PHASE 3: Book Appointments ---");
    try {
      hospitalService.bookAppointment("PAT001", "DOC001", LocalDateTime.now().plusDays(1));
      hospitalService.bookAppointment("PAT002", "DOC002", LocalDateTime.now().plusDays(2));
      hospitalService.bookAppointment("PAT003", "DOC003", LocalDateTime.now().plusDays(1));
    } catch (AppointmentNotAvailableException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    // PHASE 4: Add medical records
    System.out.println("\n--- PHASE 4: Add Medical Records ---");
    patient1.addMedicalRecord(new MedicalRecord("Hypertension", "Medication", "Lisinopril"));
    patient2.addMedicalRecord(new MedicalRecord("Migraine", "Rest & Medication", "Ibuprofen"));
    patient3.addMedicalRecord(new MedicalRecord("Common Cold", "Observation", "Paracetamol"));

    patient1.displayMedicalHistory();

    // PHASE 5: Complete appointments
    System.out.println("--- PHASE 5: Complete Appointments ---");
    HospitalServiceImpl serviceImpl = (HospitalServiceImpl) hospitalService;
    for (Appointment apt : serviceImpl.getAppointments().values()) {
      hospitalService.completeAppointment(apt.getAppointmentId());
    }

    // PHASE 6: Display information
    System.out.println("\n--- PHASE 6: Display Information ---");
    System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    PATIENTS INFORMATION                          ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
    patient1.displayInfo();
    patient2.displayInfo();
    patient3.displayInfo();

    System.out.println("╟───────────────────────────────────────────────────────────────────╢");
    System.out.println("║                    DOCTORS INFORMATION                           ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
    doctor1.displayInfo();
    doctor2.displayInfo();
    doctor3.displayInfo();

    System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

    // PHASE 7: Consultation fees
    System.out.println("\n--- PHASE 7: Consultation Fees ---");
    System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║                  CONSULTATION FEES                               ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
    System.out.println(
        "Dr. Smith (Cardiology): $" + String.format("%.2f", hospitalService.calculateConsultationFee("DOC001")));
    System.out.println(
        "Dr. Brown (Neurology): $" + String.format("%.2f", hospitalService.calculateConsultationFee("DOC002")));
    System.out.println(
        "Dr. Green (Pediatrics): $" + String.format("%.2f", hospitalService.calculateConsultationFee("DOC003")));
    System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");

    System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                          ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
  }
}
