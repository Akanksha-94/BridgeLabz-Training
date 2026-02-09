import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManagement {
    
    // UC-1.1: Register New Patient
    public int registerPatient(String name, String dob, String contact, String email, String address, String bloodGroup) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int patientId = -1;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO patients (name, dob, contact, email, address, blood_group) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setDate(2, Date.valueOf(dob));
            pstmt.setString(3, contact);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setString(6, bloodGroup);
            
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                patientId = rs.getInt(1);
                System.out.println("Patient registered with ID: " + patientId);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
        return patientId;
    }
    
    // UC-1.2: Update Patient Information
    public void updatePatient(int patientId, String name, String contact, String address) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE patients SET name=?, contact=?, address=? WHERE patient_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, contact);
            pstmt.setString(3, address);
            pstmt.setInt(4, patientId);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " patient(s) updated");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
    
    // UC-1.3: Search Patient Records
    public void searchPatients(String searchTerm) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM patients WHERE name LIKE ? OR contact=? OR patient_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + searchTerm + "%");
            pstmt.setString(2, searchTerm);
            try {
                pstmt.setInt(3, Integer.parseInt(searchTerm));
            } catch (NumberFormatException e) {
                pstmt.setInt(3, -1);
            }
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("patient_id") + 
                                 ", Name: " + rs.getString("name") + 
                                 ", Contact: " + rs.getString("contact"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    // UC-1.4: View Patient Visit History
    public void viewVisitHistory(int patientId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT v.visit_date, d.name AS doctor_name, v.diagnosis " +
                        "FROM visits v " +
                        "JOIN doctors d ON v.doctor_id = d.doctor_id " +
                        "WHERE v.patient_id = ? ORDER BY v.visit_date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientId);
            
            rs = pstmt.executeQuery();
            System.out.println("Visit History for Patient ID: " + patientId);
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
}
