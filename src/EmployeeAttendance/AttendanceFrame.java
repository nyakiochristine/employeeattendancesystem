package EmployeeAttendance;

import database.DatabaseConnection;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AttendanceFrame extends javax.swing.JFrame {

    private JTextField employeeIdField;
    private JTextField employeeNameField;
    private JTextField departmentField;
    private JTextField positionField;
    private JTextArea timeRecordsTextArea;

    public AttendanceFrame() {
        initComponents();
        setTitle("Employee Attendance");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JLabel idLabel = new JLabel("Employee ID:");
        JLabel nameLabel = new JLabel("Employee Name:");
        JLabel departmentLabel = new JLabel("Department:");
        JLabel positionLabel = new JLabel("Position:");
        JLabel timeInLabel = new JLabel("Time In:");
        JLabel timeOutLabel = new JLabel("Time Out:");
        employeeIdField = new JTextField(10); // New field for employee ID
        employeeNameField = new JTextField(10);
        departmentField = new JTextField(10);
        positionField = new JTextField(10);
        JTextField timeInField = new JTextField(10);
        JTextField timeOutField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        timeRecordsTextArea = new JTextArea(10, 40);
        timeRecordsTextArea.setEditable(false); // Make it read-only

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when submit button is clicked
                String employeeId = employeeIdField.getText(); // Retrieve employee ID
                String employeeName = employeeNameField.getText();
                String department = departmentField.getText();
                String position = positionField.getText();
                String timeIn = timeInField.getText();
                String timeOut = timeOutField.getText();
                // Display employee info and time records
                displayEmployeeInfo(employeeId, employeeName, department, position);
                displayTimeRecords(timeIn, timeOut);
                // Store employee info and time records to database (call appropriate methods)
                displayinsertEmployeeInfo(employeeId, employeeName, department, position);
                displayinsertTimeRecords(timeIn, timeOut);
            }
        });

        JPanel mainPanel = new JPanel(new GridLayout(7, 2)); // Adjusted layout for employee ID
        mainPanel.add(idLabel);
        mainPanel.add(employeeIdField);
        mainPanel.add(nameLabel);
        mainPanel.add(employeeNameField);
        mainPanel.add(departmentLabel);
        mainPanel.add(departmentField);
        mainPanel.add(positionLabel);
        mainPanel.add(positionField);
        mainPanel.add(timeInLabel);
        mainPanel.add(timeInField);
        mainPanel.add(timeOutLabel);
        mainPanel.add(timeOutField);
        mainPanel.add(submitButton);

        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(timeRecordsTextArea), BorderLayout.CENTER);
    }

    private void displayEmployeeInfo(String employeeId, String employeeName, String department, String position) {
        timeRecordsTextArea.append("Employee ID: " + employeeId + "\n"); // Display employee ID
        timeRecordsTextArea.append("Employee Name: " + employeeName + "\n");
        timeRecordsTextArea.append("Department: " + department + "\n");
        timeRecordsTextArea.append("Position: " + position + "\n");
    }

    private void displayTimeRecords(String timeIn, String timeOut) {
        // Append time in and time out records to the text area
        timeRecordsTextArea.append("Time In: " + timeIn + ", Time Out: " + timeOut + "\n");
    }

    public static void displayinsertEmployeeInfo(String employeeId, String employeeName, String department, String position) {
        DatabaseConnection.insertEmployeeInfo(employeeId, employeeName, department, position); 
    }

    private void displayinsertTimeRecords(String timeIn, String timeOut) {
        DatabaseConnection.insertTimeRecords(timeIn, timeOut); // Handle exception
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AttendanceFrame frame = new AttendanceFrame();
            frame.setVisible(true);
        });
    }
}



















































