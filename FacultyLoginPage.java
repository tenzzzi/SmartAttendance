/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FacultyLoginPage extends JFrame {
    // Constants
    private static final Color MAROON = new Color(128, 0, 0);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 12);
    
    // Components
    private JTextField nameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;

    public FacultyLoginPage() {
        initializeFrame();
        setupUI();
    }

    private void initializeFrame() {
        setTitle("Faculty Login");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Title
        mainPanel.add(createTitleLabel(), BorderLayout.NORTH);
        
        // Form
        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        
        // Buttons
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private JLabel createTitleLabel() {
        JLabel label = new JLabel("FACULTY LOGIN", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setForeground(MAROON);
        return label;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Name field
        panel.add(createLabel("Name:"));
        nameField = createTextField();
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(15));
        
        // Password field
        panel.add(createLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(10));
        
        // Show password checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(BUTTON_FONT);
        showPasswordCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPasswordCheckBox.addItemListener(this::togglePasswordVisibility);
        panel.add(showPasswordCheckBox);
        
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(LABEL_FONT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return field;
    }

    private void togglePasswordVisibility(ItemEvent e) {
        passwordField.setEchoChar(e.getStateChange() == ItemEvent.SELECTED ? 
            (char) 0 : '•');
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Login and Cancel buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonRow.add(createButton("Cancel", e -> dispose()));
        buttonRow.add(createButton("Log In", this::handleLogin));
        
        // Back button
        JButton backButton = createButton("Back", e -> {
            new DestinationSelector().setVisible(true);
            dispose();
        });
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(buttonRow);
        panel.add(Box.createVerticalStrut(15));
        panel.add(backButton);
        
        return panel;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        button.setFont(BUTTON_FONT);
        return button;
    }

    private void handleLogin(ActionEvent e) {
        String name = nameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        if (!validateLogin(name, password)) {
            showError("Invalid credentials", "Login Error");
            return;
        }
        
        String facultyEmail = generateFacultyEmail(name);
        openFacultyProfile(name, facultyEmail);
        dispose();
    }

    private boolean validateLogin(String name, String password) {
        return !name.isEmpty() && password.length() >= 6;
    }

    private String generateFacultyEmail(String name) {
        return name.toLowerCase().replace(" ", ".") + "@pup.edu.ph";
    }

    private void openFacultyProfile(String name, String email) {
        SwingUtilities.invokeLater(() -> {
            FacultyProfilePage profile = new FacultyProfilePage(name, email);
            profile.setVisible(true);
            showSuccessMessage(profile, "Login successful!", "Welcome");
        });
    }

    private void showSuccessMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FacultyLoginPage().setVisible(true));
    }
}