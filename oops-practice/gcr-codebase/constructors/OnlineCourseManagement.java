public class OnlineCourseManagement {

    // Instance variables (unique for each course)
    private String courseName;
    private int duration; // in weeks
    private double fee;

    // Class variable (shared by all courses)
    private static String instituteName = "Default Institute";

    // Parameterized constructor
    public OnlineCourseManagement(String courseName, int duration, double fee) {
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
    }

    // Instance method: displays details of this course
    public void displayCourseDetails() {
        System.out.println("Course Name: " + courseName + ", Duration: " + duration + " weeks, Fee: $" + fee + ", Institute: " + instituteName);
    }

    // Class method: updates the institute name for all courses
    public static void updateInstituteName(String newName) {
        instituteName = newName;
    }

    // Main method to test the class
    public static void main(String[] args) {
        // Create courses
        OnlineCourseManagement c1 = new OnlineCourseManagement("Java Programming", 8, 500.0);
        OnlineCourseManagement c2 = new OnlineCourseManagement("Python for Data Science", 12, 700.0);

        // Display details before updating institute name
        c1.displayCourseDetails();
        c2.displayCourseDetails();

        // Update institute name for all courses
        OnlineCourseManagement.updateInstituteName("Global Tech Academy");

        System.out.println("\nAfter updating institute name:");

        // Display details after updating institute name
        c1.displayCourseDetails();
        c2.displayCourseDetails();
    }
}
