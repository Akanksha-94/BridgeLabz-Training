//  Educational Course Hierarchy

class Course {
    String courseName;
    int duration; // in weeks

    void displayCourse() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + duration + " weeks");
    }
}

class OnlineCourse extends Course {
    String platform;
    boolean isRecorded;

    @Override
    void displayCourse() {
        super.displayCourse();
        System.out.println("Platform: " + platform);
        System.out.println("Recorded Course: " + isRecorded);
    }
}

class PaidOnlineCourse extends OnlineCourse {
    double fee;
    double discount;

    @Override
    void displayCourse() {
        super.displayCourse();
        System.out.println("Course Fee: ₹" + fee);
        System.out.println("Discount: " + discount + "%");
        System.out.println("---------------------------");
    }
}

public class EducationalCourseHierarchy {
    public static void main(String[] args) {

        PaidOnlineCourse course = new PaidOnlineCourse();

        course.courseName = "Java Full Stack";
        course.duration = 12;
        course.platform = "Apna College";
        course.isRecorded = true;
        course.fee = 15000;
        course.discount = 20;

        course.displayCourse();
    }
}
