package com.akanksha.repository;

import com.sneha.entity.Attendance;
import java.util.HashMap;
import java.util.Map;

public class AttendanceRepository {
    private Map<Integer, Attendance> attendanceRecords = new HashMap<>();

    public void saveAttendance(int employeeId, String status) {
        attendanceRecords.put(employeeId, new Attendance(employeeId, status));
    }

    public Attendance getAttendance(int employeeId) {
        return attendanceRecords.get(employeeId);
    }

    public Map<Integer, Attendance> getAllAttendance() {
        return attendanceRecords;
    }
}