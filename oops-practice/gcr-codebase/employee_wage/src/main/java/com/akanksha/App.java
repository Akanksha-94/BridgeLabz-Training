package com.akanksha;

import com.sneha.controller.AttendanceController;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AttendanceController controller = new AttendanceController();
        Scanner scanner = new Scanner(System.in);

        controller.addEmployee(1, "John");
        controller.addEmployee(2, "Sarah");
        controller.addEmployee(3, "Mike");

        System.out.println("==== Employee Attendance System ====");
        System.out.println("1. Check Attendance\n2. View Attendance\n3. Exit");

        while (true) {
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                controller.checkAttendance(empId);
            } else if (choice == 2) {
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                controller.displayAttendance(empId);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}