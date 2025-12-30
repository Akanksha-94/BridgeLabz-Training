class Student {
    String name;
    int rollNumber;
    int marks;
    char grade;

    // Method to calculate grade based on marks
    void calculateGrade() {
        if (marks >= 90) {
            grade = 'A';
        } else if (marks >= 75) {
            grade = 'B';
        } else if (marks >= 60) {
            grade = 'C';
        } else if (marks >= 40) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    // Method to display student details and grade
    void displayDetails() {
        System.out.println("Student Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
      
        // First student
        Student s1 = new Student();
        s1.name = "Rohan";
        s1.rollNumber = 101;
        s1.marks = 85;
        s1.calculateGrade();
        s1.displayDetails();

        // Second student
        Student s2 = new Student();
        s2.name = "Sneha";
        s2.rollNumber = 102;
        s2.marks = 72;
        s2.calculateGrade();
        s2.displayDetails();
    }
}
