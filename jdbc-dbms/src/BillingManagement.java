import java.sql.*;

public class BillingManagement {
    
    // UC-5.1: Generate Bill for Visit
    public int generateBill(int visitId, int patientId, double consultationFee, double additionalCharges) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int billId = -1;
        
        try {
            conn = DatabaseConnection.getConnection();
            double totalAmount = consultationFee + additionalCharges;
            
            String sql = "INSERT INTO bills (visit_id, patient_id, total_amount) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, visitId);
            pstmt.setInt(2, patientId);
            pstmt.setDouble(3, totalAmount);
            
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                billId = rs.getInt(1);
                System.out.println("Bill generated with ID: " + billId + ", Amount: " + totalAmount);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
        return billId;
    }
    
    // UC-5.2: Record Payment
    public void recordPayment(int billId, double amount, String paymentMode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Update bill
            String sql = "UPDATE bills SET payment_status='PAID', payment_date=CURDATE(), payment_mode=? WHERE bill_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, paymentMode);
            pstmt.setInt(2, billId);
            pstmt.executeUpdate();
            
            // Insert transaction
            String transSql = "INSERT INTO payment_transactions (bill_id, amount, payment_date, payment_mode) VALUES (?, ?, CURDATE(), ?)";
            pstmt = conn.prepareStatement(transSql);
            pstmt.setInt(1, billId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, paymentMode);
            pstmt.executeUpdate();
            
            conn.commit();
            System.out.println("Payment recorded");
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }
    
    // UC-5.3: View Outstanding Bills
    public void viewOutstandingBills() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT b.bill_id, p.name, b.total_amount, b.created_at " +
                        "FROM bills b " +
                        "JOIN patients p ON b.patient_id = p.patient_id " +
                        "WHERE b.payment_status='UNPAID' " +
                        "ORDER BY b.created_at";
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            System.out.println("Outstanding Bills:");
            double total = 0;
            while (rs.next()) {
                double amount = rs.getDouble("total_amount");
                total += amount;
                System.out.println("Bill ID: " + rs.getInt("bill_id") + 
                                 ", Patient: " + rs.getString("name") + 
                                 ", Amount: " + amount);
            }
            System.out.println("Total Outstanding: " + total);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
    
    // UC-5.4: Generate Revenue Report
    public void generateRevenueReport(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT DATE(b.payment_date) AS date, d.name AS doctor, s.specialty_name, SUM(b.total_amount) AS revenue " +
                        "FROM bills b " +
                        "JOIN visits v ON b.visit_id = v.visit_id " +
                        "JOIN doctors d ON v.doctor_id = d.doctor_id " +
                        "JOIN specialties s ON d.specialty_id = s.specialty_id " +
                        "WHERE b.payment_status='PAID' AND b.payment_date BETWEEN ? AND ? " +
                        "GROUP BY DATE(b.payment_date), d.doctor_id, s.specialty_id " +
                        "HAVING SUM(b.total_amount) > 0 " +
                        "ORDER BY date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            
            rs = pstmt.executeQuery();
            System.out.println("Revenue Report (" + startDate + " to " + endDate + "):");
            while (rs.next()) {
                System.out.println("Date: " + rs.getDate("date") + 
                                 ", Doctor: " + rs.getString("doctor") + 
                                 ", Specialty: " + rs.getString("specialty_name") + 
                                 ", Revenue: " + rs.getDouble("revenue"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }
    }
}
