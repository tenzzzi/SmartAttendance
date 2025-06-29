/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentProfilePage extends JFrame {
    private JButton menuButton;
    private JButton backButton;
    private String studentName;
    private String studentId;

    // Default constructor (for testing)
    public StudentProfilePage() {
        this("sttudentName", "studentId"); // Default values
    }

    // Main constructor with student data
    public StudentProfilePage(String name, String id) {
        this.studentName = name;
        this.studentId = id;
        initializeFrame();
        setupUI();
    }

    private void initializeFrame() {
        setTitle("Student Profile");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setupUI() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel with menu button
        JPanel headerPanel = new JPanel(new BorderLayout());
        menuButton = new JButton("☰");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 24));
        menuButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuButton.addActionListener(this::showMenu);
        headerPanel.add(menuButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("STUDENT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Student info panel with dynamic data
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        // Student name (dynamic)
        JLabel nameLabel = new JLabel(studentName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(20));

        // Student ID (dynamic)
        JLabel idLabel = new JLabel(studentId);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(idLabel);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Footer with back button
        JPanel footerPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> goBack());
        footerPanel.add(backButton);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void showMenu(ActionEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem attendanceItem = new JMenuItem("View Attendance");
        attendanceItem.addActionListener(ev -> showAttendance());
        popupMenu.add(attendanceItem);
        
        JMenuItem scheduleItem = new JMenuItem("View Schedule");
        scheduleItem.addActionListener(ev -> showSchedule());
        popupMenu.add(scheduleItem);
        
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(ev -> logout());
        popupMenu.add(logoutItem);
        
        popupMenu.show(menuButton, 0, menuButton.getHeight());
    }

    private void showAttendance() {
        // Replace with actual attendance display logic
        JOptionPane.showMessageDialog(this, "Attendance for: " + studentName);
    }

    private void showSchedule() {
        // Replace with actual schedule display logic
        JOptionPane.showMessageDialog(this, "Schedule for: " + studentId);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to log out?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new DestinationSelector().setVisible(true);
        }
    }

    private void goBack() {
        dispose();
        new DestinationSelector().setVisible(true);
    }

    public static void main(String[] args) {
        // For testing
        SwingUtilities.invokeLater(() -> new StudentProfilePage().setVisible(true));
    }
}