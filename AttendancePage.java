/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class AttendancePage extends JFrame {
    public AttendancePage(String professorName) {
        setTitle("Attendance System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel with GridBagLayout for perfect centering
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 15, 5);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.anchor = GridBagConstraints.WEST;
        formGbc.insets = new Insets(5, 5, 5, 5);
        
        // Name of Subject
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formPanel.add(new JLabel("Name of Subject:"), formGbc);
        
        formGbc.gridx = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.weightx = 1;
        JTextField subjectField = new JTextField(20);
        formPanel.add(subjectField, formGbc);
        
        // Professor
        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.NONE;
        formGbc.weightx = 0;
        formPanel.add(new JLabel("Professor:"), formGbc);
        
        formGbc.gridx = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.weightx = 1;
        JTextField professorField = new JTextField(professorName, 20);
        professorField.setEditable(false);
        formPanel.add(professorField, formGbc);
        
        // Year Level and Section
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formGbc.fill = GridBagConstraints.NONE;
        formGbc.weightx = 0;
        formPanel.add(new JLabel("Year Level and Section:"), formGbc);
        
        formGbc.gridx = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.weightx = 1;
        JTextField yearSectionField = new JTextField(20);
        formPanel.add(yearSectionField, formGbc);
        
        // Add form panel to main panel
        mainPanel.add(formPanel, gbc);
        
        // Attendance title
        JLabel attendanceLabel = new JLabel("ATTENDANCE", SwingConstants.CENTER);
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(attendanceLabel, gbc);
        
        // Scan button
        JButton scanButton = new JButton("Scan");
        scanButton.setFont(new Font("Arial", Font.BOLD, 16));
        scanButton.setPreferredSize(new Dimension(100, 40));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(scanButton);
        mainPanel.add(buttonPanel, gbc);
        
        // Table for attendance records
        String[] columnNames = {"Name", "Student Number", "Time and Date"};
        Object[][] data = new Object[10][3];
        
        JTable attendanceTable = new JTable(data, columnNames);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.setRowHeight(30);
        
        // Center align table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < attendanceTable.getColumnCount(); i++) {
            attendanceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Center align table headers
        JTableHeader header = attendanceTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        JScrollPane tableScrollPane = new JScrollPane(attendanceTable);
        tableScrollPane.setBorder(new LineBorder(Color.BLACK));
        tableScrollPane.setPreferredSize(new Dimension(500, 250));
        
        
        gbc.weighty = 1;
        mainPanel.add(tableScrollPane, gbc);
        
        // Center everything in the frame
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(mainPanel);
        add(centerWrapper);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AttendancePage attendancePage = new AttendancePage("Dr. Smith");
            attendancePage.setVisible(true);
        });
    }

    AttendancePage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}