


package EmployeeAttendance;

import database.DatabaseConnection;
import javax.swing.*;

public class AdminFrame extends javax.swing.JFrame {

    public AdminFrame() {
        initComponents(); // Method to initialize components
        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to initialize components
    private void initComponents() {
        // Add Swing components such as buttons, labels, text fields, etc.
        JButton viewAttendanceButton = new JButton("View Attendance");
        viewAttendanceButton.addActionListener(e -> {
            // Logic to view attendance records goes here
            displayAttendanceRecords();
        });

        JPanel panel = new JPanel();
        panel.add(viewAttendanceButton);

        getContentPane().add(panel);
    }

    private void displayAttendanceRecords() {
        String attendanceRecords = DatabaseConnection.getAttendanceRecords();
        // Display the records in a dialog
        JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(attendanceRecords)), "Attendance Records", JOptionPane.PLAIN_MESSAGE);
    }

    
   
    public static void main(String[] args) {
        // Create and display the AdminFrame
        SwingUtilities.invokeLater(() -> {
            AdminFrame frame = new AdminFrame();
            frame.setVisible(true);
        });
    }
}
