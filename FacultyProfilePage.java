/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class FacultyProfilePage extends JFrame {
    private JButton menuButton;
    private final String facultyName;
    private final String facultyEmail;

    public FacultyProfilePage(String name, String email) {
        this.facultyName = name;
        this.facultyEmail = email;
        initializeFrame();
        setupUI();
    }

    private void initializeFrame() {
        setTitle("Faculty Profile - " + facultyName);
        setSize(500, 600);
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

        JLabel titleLabel = new JLabel("FACULTY PROFILE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Faculty info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        // Faculty name
        JLabel nameLabel = new JLabel(facultyName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(20));

        // Faculty email
        JLabel emailLabel = new JLabel(facultyEmail);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(emailLabel);

        // Create circular attendance button
        JButton attendanceButton = createCircularButton("A", new Color(30, 144, 255), e -> {
            takeAttendance();
            openAttendancePage();
        });

        // Create panel for the attendance button (lower right)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        buttonPanel.add(attendanceButton);
        buttonPanel.setOpaque(false);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void openAttendancePage() {
        // Close current profile page
        dispose();
        
        // Open the attendance page with faculty name
        SwingUtilities.invokeLater(() -> {
            AttendancePage attendancePage = new AttendancePage();
            attendancePage.setVisible(true);
        });
    }

    private JButton createCircularButton(String text, Color bgColor, ActionListener action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillOval(0, 0, getWidth()-1, getHeight()-1);
                
                // Draw the text
                g2.setColor(Color.WHITE);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(60, 60);
            }

            @Override
            public boolean contains(int x, int y) {
                return new Ellipse2D.Float(0, 0, getWidth(), getHeight()).contains(x, y);
            }
        };
        
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }

    private void showMenu(ActionEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem profileItem = new JMenuItem("Edit Profile");
        profileItem.addActionListener(ev -> editProfile());
        popupMenu.add(profileItem);
        
        JMenuItem scheduleItem = new JMenuItem("View Schedule");
        scheduleItem.addActionListener(ev -> viewSchedule());
        popupMenu.add(scheduleItem);
        
        popupMenu.addSeparator();
        
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(ev -> logout());
        popupMenu.add(logoutItem);
        
        popupMenu.show(menuButton, 0, menuButton.getHeight());
    }

    private void editProfile() {
        JOptionPane.showMessageDialog(this, 
            "Edit profile functionality would go here",
            "Edit Profile", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewSchedule() {
        JOptionPane.showMessageDialog(this, 
            "Schedule viewing functionality would go here",
            "View Schedule", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void takeAttendance() {
    dispose(); // Close the profile page
    new AttendancePage(facultyName).setVisible(true); // Open attendance page with faculty name
}

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to log out?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            // You would typically open a login window here
            // new LoginWindow().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FacultyProfilePage("Dr. Smith", "smith@pup.edu.ph").setVisible(true);
        });
    }
}