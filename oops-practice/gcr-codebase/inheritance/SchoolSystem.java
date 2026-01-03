//  School System with Different Roles
class Person {
    String name;
    int age;

    void displayPersonDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Teacher subclass
class Teacher extends Person {
    String subject;

    void displayRole() {
        System.out.println("Role: Teacher");
        displayPersonDetails();
        System.out.println("Subject: " + subject);
        System.out.println("----------------------");
    }
}

// Student subclass
class Student extends Person {
    int grade;

    void displayRole() {
        System.out.println("Role: Student");
        displayPersonDetails();
        System.out.println("Grade: " + grade);
        System.out.println("----------------------");
    }
}

// Staff subclass
class Staff extends Person {
    String department;

    void displayRole() {
        System.out.println("Role: Staff");
        displayPersonDetails();
        System.out.println("Department: " + department);
        System.out.println("----------------------");
    }
}

public class SchoolSystem {
    public static void main(String[] args) {

        Teacher t = new Teacher();
        t.name = "Mr. Sharma";
        t.age = 40;
        t.subject = "Mathematics";

        Student s = new Student();
        s.name = "Rohan";
        s.age = 16;
        s.grade = 10;

        Staff st = new Staff();
        st.name = "Anita";
        st.age = 35;
        st.department = "Administration";

        t.displayRole();
        s.displayRole();
        st.displayRole();
    }
}
