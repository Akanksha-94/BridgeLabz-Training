import java.sql.*;

public class AppointmentScheduling {
    
    // UC-3.1: Book New Appointment
    public int bookAppointment(int patientId, int doctorId, String date, String time) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int appointmentId = -1;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            // Check availability
            if (!checkAvailability(doctorId, date, time)) {
                System.out.println("Slot not available");
                return -1;
            }
            
            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setDate(3, Date.valueOf(date));
            pstmt.setTime(4, Time.valueOf(time));
            
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                appointmentId = rs.getInt(1);
                System.out.println("Appointment booked with ID: " + appointmentId);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
        return appointmentId;
    }
    
    // UC-3.2: Check Doctor Availability
    public boolean checkAvailability(int doctorId, String date, String time) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND appointment_date=? AND appointment_time=? AND status='SCHEDULED'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, doctorId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setTime(3, Time.valueOf(time));
            
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
        return false;
    }
    
    // UC-3.3: Cancel Appointment
    public void cancelAppointment(int appointmentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            String sql = "UPDATE appointments SET status='CANCELLED' WHERE appointment_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, appointmentId);
            pstmt.executeUpdate();
            
            // Log in audit table
            String auditSql = "INSERT INTO appointment_audit (appointment_id, action) VALUES (?, 'CANCELLED')";
            pstmt = conn.prepareStatement(auditSql);
            pstmt.setInt(1, appointmentId);
            pstmt.executeUpdate();
            
            conn.commit();
            System.out.println("Appointment cancelled");
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
    
    // UC-3.4: Reschedule Appointment
    public void rescheduleAppointment(int appointmentId, String newDate, String newTime, int newDoctorId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            if (!checkAvailability(newDoctorId, newDate, newTime)) {
                System.out.println("New slot not available");
                conn.rollback();
                return;
            }
            
            String sql = "UPDATE appointments SET appointment_date=?, appointment_time=?, doctor_id=? WHERE appointment_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(newDate));
            pstmt.setTime(2, Time.valueOf(newTime));
            pstmt.setInt(3, newDoctorId);
            pstmt.setInt(4, appointmentId);
            
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("Appointment rescheduled");
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
    
    // UC-3.5: View Daily Appointment Schedule
    public void viewDailySchedule(String date) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT a.appointment_id, p.name AS patient_name, d.name AS doctor_name, a.appointment_time " +
                        "FROM appointments a " +
                        "JOIN patients p ON a.patient_id = p.patient_id " +
                        "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                        "WHERE a.appointment_date = ? AND a.status='SCHEDULED' " +
                        "ORDER BY a.appointment_time";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(date));
            
            rs = pstmt.executeQuery();
            System.out.println("Schedule for " + date + ":");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("appointment_id") + 
                                 ", Time: " + rs.getTime("appointment_time") + 
                                 ", Patient: " + rs.getString("patient_name") + 
                                 ", Doctor: " + rs.getString("doctor_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
}
