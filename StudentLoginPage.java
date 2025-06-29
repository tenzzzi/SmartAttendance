/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class StudentLoginPage extends JFrame {
    private JTextField schoolNumField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;

    public StudentLoginPage() {
        setTitle("Student Login");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("STUDENT LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(128, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        formPanel.add(createLabel("School Number:"));
        schoolNumField = new JTextField();
        schoolNumField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(schoolNumField);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(10));

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        showPasswordCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPasswordCheckBox.addItemListener((ItemEvent e) -> {
            passwordField.setEchoChar(e.getStateChange() == ItemEvent.SELECTED ? (char) 0 : '•');
        });
        formPanel.add(showPasswordCheckBox);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel loginCancelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(this::handleLogin);

        loginCancelPanel.add(cancelButton);
        loginCancelPanel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            new DestinationSelector().setVisible(true);
            dispose();
        });

        panel.add(loginCancelPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(backButton);

        return panel;
    }

    private void handleLogin(ActionEvent e) {
    String schoolNumber = schoolNumField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    if (!schoolNumber.isEmpty() && password.length() >= 6) {
        // Simulate retrieving student data from database
        String studentName = "VILLAPANDO, Sophia Kirsten C.";  // Example data
        String studentId = "2023-00245-BN-0";                  // Example data

        JOptionPane.showMessageDialog(this, "Login successful!");
        dispose();

        // Pass the retrieved data to the profile page
        SwingUtilities.invokeLater(() -> new StudentProfilePage(studentName, studentId).setVisible(true));
    } else {
        JOptionPane.showMessageDialog(this,
                "Invalid school number or password",
                "Login Error",
                JOptionPane.ERROR_MESSAGE);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentLoginPage().setVisible(true));
    }
}