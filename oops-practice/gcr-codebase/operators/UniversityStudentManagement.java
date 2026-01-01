public class UniversityStudentManagement {

    public static void main(String[] args) {

        // Creating Student Objects
        Student s1 = new Student("101", "Hemashree", "A");
        Student s2 = new Student("102", "Sharmila", "B");

        // Display total students
        Student.displayTotalStudents();

        // Display student 1 details
        if (s1 instanceof Student) {
            s1.displayStudentDetails();
        }

        // Display student 2 details
        if (s2 instanceof Student) {
            s2.displayStudentDetails();
        }

        // Updating grade with instanceof check
        if (s2 instanceof Student) {
            s2.updateGrade("A");
        }

        // Display updated details
        if (s2 instanceof Student) {
            s2.displayStudentDetails();
        }
    }
}

class Student {

    // 1. Static members
    static String universityName = "Global University";
    static int totalStudents = 0;

    // 3. Final roll number
    private final String rollNumber;

    // Instance variables
    private String name;
    private String grade;

    // 2. Using 'this' in constructor
    public Student(String rollNumber, String name, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
        totalStudents++;
    }

    // Static method
    public static void displayTotalStudents() {
        System.out.println("Total Students Enrolled: " + totalStudents);
    }

    // Method to display student details
    public void displayStudentDetails() {
        System.out.println("University Name: " + universityName);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }

    // Method to update grade
    public void updateGrade(String newGrade) {
        if (this instanceof Student) {
            this.grade = newGrade;
            System.out.println("Grade updated to: " + grade);
        }
    }
}
