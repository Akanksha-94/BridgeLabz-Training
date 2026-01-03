// Parent Class
public class UniversityManagementSystem {

    public int rollNumber;      // public
    protected String name;      // protected
    private double cgpa;        // private

    public UniversityManagementSystem(int rollNumber, String name, double cgpa) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.cgpa = cgpa;
    }

    // Public getter
    public double getCGPA() {
        return cgpa;
    }

    // Public setter
    public void setCGPA(double cgpa) {
        if (cgpa >= 0 && cgpa <= 10) {
            this.cgpa = cgpa;
        }
    }

    public void display() {
        System.out.println("Roll: " + rollNumber + ", Name: " + name + ", CGPA: " + cgpa);
    }
}

// Subclass
class PostgraduateStudent extends UniversityManagementSystem {

    public PostgraduateStudent(int rollNumber, String name, double cgpa) {
        super(rollNumber, name, cgpa);
    }

    public void showStudentInfo() {
        System.out.println("Accessing protected name in subclass: " + name);
        System.out.println("Public roll number: " + rollNumber);
    }
}
