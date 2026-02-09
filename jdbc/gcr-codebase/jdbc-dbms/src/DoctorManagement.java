import java.sql.*;

public class DoctorManagement {
    
    // UC-2.1: Add Doctor Profile
    public int addDoctor(String name, int specialtyId, String contact, double consultationFee) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int doctorId = -1;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO doctors (name, specialty_id, contact, consultation_fee) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setInt(2, specialtyId);
            pstmt.setString(3, contact);
            pstmt.setDouble(4, consultationFee);
            
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                doctorId = rs.getInt(1);
                System.out.println("Doctor added with ID: " + doctorId);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
        return doctorId;
    }
    
    // UC-2.2: Assign/Update Doctor Specialty
    public void updateDoctorSpecialty(int doctorId, int specialtyId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            String sql = "UPDATE doctors SET specialty_id=? WHERE doctor_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, specialtyId);
            pstmt.setInt(2, doctorId);
            
            int rows = pstmt.executeUpdate();
            conn.commit();
            System.out.println("Doctor specialty updated");
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
    
    // UC-2.3: View Doctors by Specialty
    public void viewDoctorsBySpecialty(String specialtyName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT d.doctor_id, d.name, d.contact, d.consultation_fee " +
                        "FROM doctors d " +
                        "JOIN specialties s ON d.specialty_id = s.specialty_id " +
                        "WHERE s.specialty_name = ? AND d.is_active = TRUE";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, specialtyName);
            
            rs = pstmt.executeQuery();
            System.out.println("Doctors in " + specialtyName + ":");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("doctor_id") + 
                                 ", Name: " + rs.getString("name") + 
                                 ", Fee: " + rs.getDouble("consultation_fee"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    // UC-2.4: Deactivate Doctor Profile
    public void deactivateDoctor(int doctorId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            // Check for future appointments
            String checkSql = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND appointment_date >= CURDATE() AND status='SCHEDULED'";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, doctorId);
            rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cannot deactivate: Doctor has future appointments");
                return;
            }
            
            String sql = "UPDATE doctors SET is_active=FALSE WHERE doctor_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, doctorId);
            pstmt.executeUpdate();
            System.out.println("Doctor deactivated");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
}
