package com.cafe.ui;

import com.cafe.management.UserManager;
import com.cafe.models.User;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Runnable onLoginSuccess;
    private User currentUser;

    public LoginPanel(UserManager userManager, Runnable onLoginSuccess) {
        this.userManager = userManager;
        this.onLoginSuccess = onLoginSuccess;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Center panel with login form
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Cafe Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Login to your account");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        centerPanel.add(subtitleLabel, gbc);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        centerPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setText("admin"); // Default username
        gbc.gridx = 1;
        centerPanel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setText("admin"); // Default password
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.addActionListener(e -> handleLogin());

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setPreferredSize(new Dimension(100, 30));
        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        centerPanel.add(buttonPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userManager.login(username, password);
        if (user != null) {
            currentUser = user;
            JOptionPane.showMessageDialog(this, "Welcome, " + user.getFullName() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            onLoginSuccess.run();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void reset() {
        usernameField.setText("admin");
        passwordField.setText("admin");
        currentUser = null;
    }
}
