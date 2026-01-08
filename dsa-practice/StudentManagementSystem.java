class StudentNode {
    int roll;
    String name;
    int age;
    char grade;
    StudentNode next;

    StudentNode(int r, String n, int a, char g) {
        roll = r;
        name = n;
        age = a;
        grade = g;
        next = null;
    }
}

class StudentList {
    StudentNode head;

    // Add at End
    void add(int r, String n, int a, char g) {
        StudentNode newNode = new StudentNode(r, n, a, g);

        if (head == null) {
            head = newNode;
            return;
        }

        StudentNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Display
    void display() {
        StudentNode temp = head;
        while (temp != null) {
            System.out.println(temp.roll + " " + temp.name + " " +
                               temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }

    // Search
    void search(int r) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.roll == r) {
                System.out.println("Found: " + temp.name);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Not Found");
    }

    // Delete
    void delete(int r) {
        if (head.roll == r) {
            head = head.next;
            return;
        }

        StudentNode temp = head;
        while (temp.next != null && temp.next.roll != r) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Update Grade
    void update(int r, char g) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.roll == r) {
                temp.grade = g;
                return;
            }
            temp = temp.next;
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {

        StudentList list = new StudentList();

        list.add(1, "Amit", 20, 'A');
        list.add(2, "Neha", 21, 'B');
        list.add(3, "Rahul", 22, 'C');

        list.display();

        list.search(2);
        list.update(3, 'A');
        list.delete(1);

        System.out.println("After Update & Delete:");
        list.display();
    }
}
