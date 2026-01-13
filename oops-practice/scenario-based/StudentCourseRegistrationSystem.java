import java.util.*;

/**
 * Student Course Registration System
 * Real Scenario: Students enroll in courses, drop courses, and view grades.
 * 
 * Concepts Used:
 * - Encapsulation: Student data protection
 * - Inheritance: Person → Student
 * - Abstraction: RegistrationService
 * - Exception Handling: CourseLimitExceededException, CourseNotFound
 */

// Custom Exceptions
class CourseLimitExceededException extends Exception {
  public CourseLimitExceededException(String message) {
    super(message);
  }
}

class CourseNotFoundException extends Exception {
  public CourseNotFoundException(String message) {
    super(message);
  }
}

// Course class
class Course {
  private String courseId;
  private String courseName;
  private int credits;
  private int maxCapacity;
  private int enrolledCount;
  private String instructor;

  public Course(String courseId, String courseName, int credits, int maxCapacity, String instructor) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.credits = credits;
    this.maxCapacity = maxCapacity;
    this.enrolledCount = 0;
    this.instructor = instructor;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public int getCredits() {
    return credits;
  }

  public String getInstructor() {
    return instructor;
  }

  public int getAvailableSeats() {
    return maxCapacity - enrolledCount;
  }

  public boolean canEnroll() {
    return enrolledCount < maxCapacity;
  }

  public void enrollStudent() {
    enrolledCount++;
  }

  public void withdrawStudent() {
    if (enrolledCount > 0) {
      enrolledCount--;
    }
  }

  @Override
  public String toString() {
    return String.format("%-10s | %-30s | Credits: %-2d | Instructor: %-20s | Enrolled: %-3d/%d",
        courseId, courseName, credits, instructor, enrolledCount, maxCapacity);
  }
}

// Grade class
class Grade {
  private String courseId;
  private String courseName;
  private double marks;
  private char grade;
  private int credits;

  public Grade(String courseId, String courseName, double marks, int credits) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.marks = marks;
    this.credits = credits;
    this.grade = calculateGrade(marks);
  }

  private char calculateGrade(double marks) {
    if (marks >= 90)
      return 'A';
    if (marks >= 80)
      return 'B';
    if (marks >= 70)
      return 'C';
    if (marks >= 60)
      return 'D';
    return 'F';
  }

  public double getGradePoint() {
    switch (grade) {
      case 'A':
        return 4.0;
      case 'B':
        return 3.0;
      case 'C':
        return 2.0;
      case 'D':
        return 1.0;
      default:
        return 0.0;
    }
  }

  public String getCourseId() {
    return courseId;
  }

  public double getMarks() {
    return marks;
  }

  public char getGrade() {
    return grade;
  }

  public int getCredits() {
    return credits;
  }

  @Override
  public String toString() {
    return String.format("%-30s | Marks: %-6.2f | Grade: %c | Credits: %d",
        courseName, marks, grade, credits);
  }
}

// Person base class
abstract class Person {
  protected String id;
  protected String name;
  protected String email;

  public Person(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
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

  abstract void displayInfo();
}

// Student class
class Student extends Person {
  private List<Course> enrolledCourses;
  private List<Grade> grades;
  private int maxCoursesPerSemester;
  private static final int DEFAULT_MAX_COURSES = 5;

  public Student(String id, String name, String email) {
    super(id, name, email);
    this.enrolledCourses = new ArrayList<>();
    this.grades = new ArrayList<>();
    this.maxCoursesPerSemester = DEFAULT_MAX_COURSES;
  }

  public void enrollCourse(Course course) throws CourseLimitExceededException {
    if (enrolledCourses.size() >= maxCoursesPerSemester) {
      throw new CourseLimitExceededException(
          "Cannot enroll! Maximum course limit (" + maxCoursesPerSemester + ") reached");
    }

    if (!course.canEnroll()) {
      throw new CourseLimitExceededException("Course is full!");
    }

    enrolledCourses.add(course);
    course.enrollStudent();
    System.out.println("✓ " + name + " enrolled in " + course.getCourseName());
  }

  public void dropCourse(String courseId) throws CourseNotFoundException {
    for (int i = 0; i < enrolledCourses.size(); i++) {
      if (enrolledCourses.get(i).getCourseId().equals(courseId)) {
        Course course = enrolledCourses.remove(i);
        course.withdrawStudent();
        System.out.println("✓ " + name + " dropped " + course.getCourseName());
        return;
      }
    }
    throw new CourseNotFoundException("Course not found in enrollment");
  }

  public void addGrade(Grade grade) {
    grades.add(grade);
    System.out.println("✓ Grade added for " + grade.getCourseId());
  }

  public double calculateGPA() {
    if (grades.isEmpty())
      return 0.0;

    double totalGradePoints = 0;
    int totalCredits = 0;

    for (Grade grade : grades) {
      totalGradePoints += grade.getGradePoint() * grade.getCredits();
      totalCredits += grade.getCredits();
    }

    return totalCredits > 0 ? totalGradePoints / totalCredits : 0.0;
  }

  public List<Course> getEnrolledCourses() {
    return new ArrayList<>(enrolledCourses);
  }

  public void displayEnrolledCourses() {
    if (enrolledCourses.isEmpty()) {
      System.out.println("No courses enrolled");
      return;
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║          ENROLLED COURSES - " + id + "                   ║");
    System.out.println("╠════════════════════════════════════════════════════════════════╣");

    for (Course course : enrolledCourses) {
      System.out.println(course);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
  }

  public void displayGrades() {
    if (grades.isEmpty()) {
      System.out.println("No grades available");
      return;
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║            GRADES - " + id + "                                ║");
    System.out.println("╠════════════════════════════════════════════════════════════════╣");

    for (Grade grade : grades) {
      System.out.println(grade);
    }

    System.out.println("╟────────────────────────────────────────────────────────────────╢");
    System.out.println("GPA: " + String.format("%.2f", calculateGPA()));
    System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
  }

  @Override
  void displayInfo() {
    System.out.println("Student ID: " + id + " | Name: " + name + " | Email: " + email +
        " | Enrolled Courses: " + enrolledCourses.size());
  }
}

// Registration Service Interface
interface RegistrationService {
  void enrollStudent(Student student, Course course) throws CourseLimitExceededException;

  void dropCourse(Student student, String courseId) throws CourseNotFoundException;

  void recordGrade(Student student, Grade grade);

  double getStudentGPA(Student student);
}

// Registration Service Implementation
class RegistrationServiceImpl implements RegistrationService {
  @Override
  public void enrollStudent(Student student, Course course) throws CourseLimitExceededException {
    student.enrollCourse(course);
  }

  @Override
  public void dropCourse(Student student, String courseId) throws CourseNotFoundException {
    student.dropCourse(courseId);
  }

  @Override
  public void recordGrade(Student student, Grade grade) {
    student.addGrade(grade);
  }

  @Override
  public double getStudentGPA(Student student) {
    return student.calculateGPA();
  }
}

public class StudentCourseRegistrationSystem {
  private Map<String, Course> courses;
  private Map<String, Student> students;
  private RegistrationService registrationService;

  public StudentCourseRegistrationSystem() {
    this.courses = new HashMap<>();
    this.students = new HashMap<>();
    this.registrationService = new RegistrationServiceImpl();
  }

  public void addCourse(Course course) {
    courses.put(course.getCourseId(), course);
    System.out.println("✓ Course added: " + course.getCourseName());
  }

  public void addStudent(Student student) {
    students.put(student.getId(), student);
    System.out.println("✓ Student added: " + student.getName());
  }

  public void displayAllCourses() {
    System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    AVAILABLE COURSES                          ║");
    System.out.println("╠════════════════════════════════════════════════════════════════╣");

    for (Course course : courses.values()) {
      System.out.println(course);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
  }

  public static void main(String[] args) {
    StudentCourseRegistrationSystem system = new StudentCourseRegistrationSystem();

    System.out.println("╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║     STUDENT COURSE REGISTRATION SYSTEM - DEMONSTRATION        ║");
    System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Add courses
    System.out.println("--- PHASE 1: Add Courses ---");
    system.addCourse(new Course("CS101", "Introduction to Programming", 3, 30, "Dr. Smith"));
    system.addCourse(new Course("CS102", "Data Structures", 4, 25, "Dr. Johnson"));
    system.addCourse(new Course("CS103", "Database Management", 3, 20, "Dr. Brown"));
    system.addCourse(new Course("CS104", "Web Development", 3, 30, "Dr. White"));
    system.addCourse(new Course("CS105", "Artificial Intelligence", 4, 25, "Dr. Garcia"));

    system.displayAllCourses();

    // PHASE 2: Add students
    System.out.println("--- PHASE 2: Add Students ---");
    Student student1 = new Student("STU001", "Alice Johnson", "alice@university.edu");
    Student student2 = new Student("STU002", "Bob Smith", "bob@university.edu");

    system.addStudent(student1);
    system.addStudent(student2);

    // PHASE 3: Student enrollment
    System.out.println("\n--- PHASE 3: Student Enrollment ---");
    try {
      system.registrationService.enrollStudent(student1, system.courses.get("CS101"));
      system.registrationService.enrollStudent(student1, system.courses.get("CS102"));
      system.registrationService.enrollStudent(student1, system.courses.get("CS103"));

      system.registrationService.enrollStudent(student2, system.courses.get("CS104"));
      system.registrationService.enrollStudent(student2, system.courses.get("CS105"));
    } catch (CourseLimitExceededException e) {
      System.out.println("✗ Enrollment error: " + e.getMessage());
    }

    student1.displayEnrolledCourses();
    student2.displayEnrolledCourses();

    // PHASE 4: Record grades
    System.out.println("--- PHASE 4: Record Grades ---");
    system.registrationService.recordGrade(student1, new Grade("CS101", "Introduction to Programming", 92, 3));
    system.registrationService.recordGrade(student1, new Grade("CS102", "Data Structures", 85, 4));
    system.registrationService.recordGrade(student1, new Grade("CS103", "Database Management", 78, 3));

    system.registrationService.recordGrade(student2, new Grade("CS104", "Web Development", 88, 3));
    system.registrationService.recordGrade(student2, new Grade("CS105", "Artificial Intelligence", 95, 4));

    // PHASE 5: View grades and GPA
    System.out.println("--- PHASE 5: View Grades and GPA ---");
    student1.displayGrades();
    student2.displayGrades();

    // PHASE 6: Drop course
    System.out.println("--- PHASE 6: Drop Course ---");
    try {
      system.registrationService.dropCourse(student1, "CS103");
    } catch (CourseNotFoundException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    student1.displayEnrolledCourses();

    // PHASE 7: Error handling - exceed course limit
    System.out.println("--- PHASE 7: Test Course Limit ---");
    try {
      system.registrationService.enrollStudent(student1, system.courses.get("CS104"));
      system.registrationService.enrollStudent(student1, system.courses.get("CS105"));
      system.registrationService.enrollStudent(student1, system.courses.get("CS103"));
      system.registrationService.enrollStudent(student1, system.courses.get("CS103")); // Should fail
    } catch (CourseLimitExceededException e) {
      System.out.println("✗ Enrollment limit error: " + e.getMessage());
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                       ║");
    System.out.println("╚════════════════════════════════════════════════════════════════╝");
  }
}
