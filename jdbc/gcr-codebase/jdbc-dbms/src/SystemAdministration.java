import java.sql.*;

public class SystemAdministration {
    
    // UC-6.1: Manage Specialty Lookup
    public void addSpecialty(String specialtyName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO specialties (specialty_name) VALUES (?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, specialtyName);
            pstmt.executeUpdate();
            System.out.println("Specialty added");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
    
    public void deleteSpecialty(int specialtyId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            // Check foreign key constraints
            String checkSql = "SELECT COUNT(*) FROM doctors WHERE specialty_id=?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, specialtyId);
            rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cannot delete: Specialty has associated doctors");
                return;
            }
            
            String sql = "DELETE FROM specialties WHERE specialty_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, specialtyId);
            pstmt.executeUpdate();
            System.out.println("Specialty deleted");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    public void listSpecialties() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM specialties";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            System.out.println("Available Specialties:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("specialty_id") + 
                                 ", Name: " + rs.getString("specialty_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    // UC-6.3: View System Audit Logs
    public void viewAuditLogs(String tableName, String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM audit_log WHERE table_name=? AND timestamp BETWEEN ? AND ? ORDER BY timestamp DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tableName);
            pstmt.setTimestamp(2, Timestamp.valueOf(startDate + " 00:00:00"));
            pstmt.setTimestamp(3, Timestamp.valueOf(endDate + " 23:59:59"));
            
            rs = pstmt.executeQuery();
            System.out.println("Audit Logs for " + tableName + ":");
            while (rs.next()) {
                System.out.println("Action: " + rs.getString("action") + 
                                 ", User: " + rs.getString("user") + 
                                 ", Time: " + rs.getTimestamp("timestamp"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    // UC-6.2: Database Metadata
    public void viewDatabaseMetadata() {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            
            System.out.println("Database Product: " + metaData.getDatabaseProductName());
            System.out.println("Database Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
            
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("\nTables:");
            while (tables.next()) {
                System.out.println("- " + tables.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, null, null);
        }
    }
}
