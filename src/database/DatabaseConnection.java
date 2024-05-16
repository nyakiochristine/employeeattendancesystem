/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.SQLException;





public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeesystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    
    
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
}
    
  
      
    
    public static void insertEmployeeInfo(String employeeId, String employeeName, String department, String position) {
    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (employee_id, employee_name, department, position) VALUES (?, ?, ?, ?)")) {
        statement.setString(1, employeeId);
        statement.setString(2, employeeName);
        statement.setString(3, department);
        statement.setString(4, position);
        statement.executeUpdate();
    } catch (SQLException ex) {
// Print the stack trace for debugging
                // Handle the exception as needed, e.g., show error message to user
        JOptionPane.showMessageDialog(null, "Error inserting employee information: " + ex.getMessage());
    }
}


    
    
    
    public static void insertTimeRecords(String timeIn, String timeOut) {
        try (Connection connection = getConnection();
             var statement = connection.prepareStatement("INSERT INTO attendances (time_in, time_out) VALUES (?, ?)")) {
            statement.setString(1, timeIn);
            statement.setString(2, timeOut);
            statement.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    
    
    
    public static String getAttendanceRecords() {
    StringBuilder records = new StringBuilder();
    try (Connection connection = getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT a.time_in, a.time_out, e.employee_id, e.employee_name, e.position, e.department " +
                                                      "FROM attendances a " +
                                                      "INNER JOIN employee e ON a.employee_id = e.employee_id")) {

        while (resultSet.next()) {
            String employeeId = resultSet.getString("employee_id");
            String employeeName = resultSet.getString("employee_name");
            String position = resultSet.getString("position");
            String department = resultSet.getString("department");
            String timeIn = resultSet.getString("time_in");
            String timeOut = resultSet.getString("time_out");

            records.append("Employee ID: ").append(employeeId).append(", ");
            records.append("Employee Name: ").append(employeeName).append(", ");
            records.append("Position: ").append(position).append(", ");
            records.append("Department: ").append(department).append(", ");
            records.append("Time In: ").append(timeIn).append(", ");
            records.append("Time Out: ").append(timeOut).append("\n");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error retrieving attendance records: " + ex.getMessage());
    }
    return records.toString();
}
    
 

    
    
    

    
   

    
    
 
    
}
    
    
    
    
    
    
    
    
    
    
    



