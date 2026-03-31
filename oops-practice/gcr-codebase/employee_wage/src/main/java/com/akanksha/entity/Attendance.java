package com.akanksha.entity;

public class Attendance {
    private int employeeId;
    private String attendanceStatus;

    public Attendance() {
    }

    public Attendance(int employeeId, String attendanceStatus) {
        this.employeeId = employeeId;
        this.attendanceStatus = attendanceStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "employeeId=" + employeeId +
                ", attendanceStatus='" + attendanceStatus + '\'' +
                '}';
    }
}