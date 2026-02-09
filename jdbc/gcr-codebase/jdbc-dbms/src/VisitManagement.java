import java.sql.*;
import java.util.List;

public class VisitManagement {
    
    // UC-4.1: Record Patient Visit
    public int recordVisit(int appointmentId, int patientId, int doctorId, String diagnosis, String notes) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int visitId = -1;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Insert visit
            String sql = "INSERT INTO visits (appointment_id, patient_id, doctor_id, visit_date, diagnosis, notes) VALUES (?, ?, ?, CURDATE(), ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, appointmentId);
            pstmt.setInt(2, patientId);
            pstmt.setInt(3, doctorId);
            pstmt.setString(4, diagnosis);
            pstmt.setString(5, notes);
            
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                visitId = rs.getInt(1);
            }
            
            // Update appointment status
            String updateSql = "UPDATE appointments SET status='COMPLETED' WHERE appointment_id=?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setInt(1, appointmentId);
            pstmt.executeUpdate();
            
            conn.commit();
            System.out.println("Visit recorded with ID: " + visitId);
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
        return visitId;
    }
    
    // UC-4.2: View Patient Medical History
    public void viewMedicalHistory(int patientId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT v.visit_date, d.name AS doctor_name, v.diagnosis, v.notes " +
                        "FROM visits v " +
                        "JOIN doctors d ON v.doctor_id = d.doctor_id " +
                        "WHERE v.patient_id = ? " +
                        "ORDER BY v.visit_date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientId);
            
            rs = pstmt.executeQuery();
            System.out.println("Medical History for Patient ID: " + patientId);
            while (rs.next()) {
                System.out.println("Date: " + rs.getDate("visit_date") + 
                                 ", Doctor: " + rs.getString("doctor_name") + 
                                 ", Diagnosis: " + rs.getString("diagnosis"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    // UC-4.3: Add Prescription Details
    public void addPrescriptions(int visitId, List<String[]> medicines) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO prescriptions (visit_id, medicine_name, dosage, duration) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            for (String[] medicine : medicines) {
                pstmt.setInt(1, visitId);
                pstmt.setString(2, medicine[0]);
                pstmt.setString(3, medicine[1]);
                pstmt.setString(4, medicine[2]);
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            System.out.println("Prescriptions added");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
}
