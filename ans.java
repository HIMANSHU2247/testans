import java.sql.*;

public class EmployeeJDBC {

    public static void main(String[] args) {

        // Database credentials
        String url = "jdbc:mysql://localhost:3306/TEST";
        String user = "root";        // change if needed
        String password = "password"; // change if needed

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load Driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // 1. INSERT records
            String insertQuery = "INSERT INTO employee (id, name, department, salary) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertQuery);

            // Employee 1
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Rahul");
            pstmt.setString(3, "IT");
            pstmt.setInt(4, 50000);
            pstmt.executeUpdate();

            // Employee 2
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Anita");
            pstmt.setString(3, "HR");
            pstmt.setInt(4, 45000);
            pstmt.executeUpdate();

            System.out.println("Records inserted successfully!");

            // 2. UPDATE salary
            String updateQuery = "UPDATE employee SET salary = ? WHERE id = ?";
            pstmt = con.prepareStatement(updateQuery);

            pstmt.setInt(1, 55000); // new salary
            pstmt.setInt(2, 1);     // employee id

            pstmt.executeUpdate();
            System.out.println("Salary updated successfully!");

            // 3. SELECT records
            String selectQuery = "SELECT * FROM employee";
            pstmt = con.prepareStatement(selectQuery);
            rs = pstmt.executeQuery();

            System.out.println("\nEmployee Records:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                int salary = rs.getInt("salary");

                System.out.println(id + " | " + name + " | " + dept + " | " + salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
                System.out.println("Resources closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
