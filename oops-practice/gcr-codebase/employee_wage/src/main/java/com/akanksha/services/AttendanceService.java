package com.akanksha.services;

import com.sneha.entity.Attendance;
import com.sneha.repository.AttendanceRepository;
import com.sneha.repository.EmployeeRepository;

public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public String markAttendance(int employeeId) {
        if (!employeeRepository.employeeExists(employeeId)) {
            return "Employee not found";
        }

        String[] statuses = {"Present", "Absent"};
        int randomIndex = (int) (Math.random() * statuses.length);
        String status = statuses[randomIndex];

        attendanceRepository.saveAttendance(employeeId, status);
        return status;
    }

    public Attendance getAttendance(int employeeId) {
        return attendanceRepository.getAttendance(employeeId);
    }
}
