package com.akanksha.controller;

import com.sneha.service.AttendanceService;
import com.sneha.repository.AttendanceRepository;
import com.sneha.repository.EmployeeRepository;

public class AttendanceController {
    private AttendanceService attendanceService;
    private EmployeeRepository employeeRepository;

    public AttendanceController() {
        employeeRepository = new EmployeeRepository();
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        attendanceService = new AttendanceService(attendanceRepository, employeeRepository);
    }

    public void addEmployee(int employeeId, String employeeName) {
        employeeRepository.addEmployee(employeeId, employeeName);
    }

    public void checkAttendance(int employeeId) {
        String result = attendanceService.markAttendance(employeeId);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Attendance Status: " + result);
    }

    public void displayAttendance(int employeeId) {
        com.sneha.entity.Attendance attendance = attendanceService.getAttendance(employeeId);
        if (attendance != null) {
            System.out.println("Employee " + employeeId + " - " + attendance.getAttendanceStatus());
        } else {
            System.out.println("No attendance record found for employee " + employeeId);
        }
    }
}