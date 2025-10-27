package com.cafe.ui;

import com.cafe.database.DatabaseInitializer;
import com.cafe.management.MenuManager;
import com.cafe.management.OrderManager;
import com.cafe.management.EmployeeManager;
import com.cafe.management.UserManager;
import com.cafe.models.User;

import javax.swing.*;
import java.awt.*;

public class CafeManagementApp extends JFrame {
    private MenuManager menuManager;
    private OrderManager orderManager;
    private EmployeeManager employeeManager;
    private UserManager userManager;
    private JTabbedPane tabbedPane;
    private User currentUser;
    private LoginPanel loginPanel;

    public CafeManagementApp() {
        setTitle("Cafe Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Initialize managers
        menuManager = new MenuManager();
        orderManager = new OrderManager();
        employeeManager = new EmployeeManager();
        userManager = new UserManager();

        // Initialize database
        DatabaseInitializer.initializeDatabase();

        // Show login screen
        showLoginScreen();
        setVisible(true);
    }

    private void showLoginScreen() {
        // Remove previous components
        getContentPane().removeAll();
        
        loginPanel = new LoginPanel(userManager, this::showMainApp);
        add(loginPanel);
        revalidate();
        repaint();
    }

    private void showMainApp() {
        currentUser = loginPanel.getCurrentUser();
        
        // Remove login panel
        getContentPane().removeAll();

        // Create menu bar with user info and logout
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem changePasswordItem = new JMenuItem("Change Password");
        changePasswordItem.addActionListener(e -> changePassword());
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> logout());
        fileMenu.add(changePasswordItem);
        fileMenu.addSeparator();
        fileMenu.add(logoutItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue());
        JLabel userLabel = new JLabel("Logged in as: " + currentUser.getFullName() + " (" + currentUser.getRole() + ")");
        userLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        menuBar.add(userLabel);
        
        setJMenuBar(menuBar);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Add tabs based on user role
        tabbedPane.addTab("Menu", new MenuPanel(menuManager));
        tabbedPane.addTab("Create Order", new OrderPanel(orderManager, menuManager));
        tabbedPane.addTab("Order Details", new OrderDetailsPanel(orderManager, menuManager));
        
        if (currentUser.getRole().equals("ADMIN") || currentUser.getRole().equals("MANAGER")) {
            tabbedPane.addTab("Employees", new EmployeePanel(employeeManager));
            tabbedPane.addTab("Users", new UserManagementPanel(userManager));
        }
        
        tabbedPane.addTab("Dashboard", new DashboardPanel(menuManager, orderManager, employeeManager));

        add(tabbedPane);
        revalidate();
        repaint();
    }

    private void changePassword() {
        JDialog dialog = new JDialog(this, "Change Password", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();

        panel.add(new JLabel("Old Password:"));
        panel.add(oldPasswordField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);

        JButton changeBtn = new JButton("Change");
        changeBtn.addActionListener(e -> {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!oldPassword.equals(currentUser.getPassword())) {
                JOptionPane.showMessageDialog(this, "Old password is incorrect!");
                return;
            }

            if (newPassword.isEmpty() || !newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "New passwords do not match!");
                return;
            }

            userManager.changePassword(currentUser.getUserId(), newPassword);
            JOptionPane.showMessageDialog(this, "Password changed successfully!");
            dialog.dispose();
        });

        panel.add(changeBtn);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?");
        if (confirm == JOptionPane.YES_OPTION) {
            currentUser = null;
            loginPanel.reset();
            showLoginScreen();
        }
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Cafe Management System v1.0\n\n" +
                "A professional cafe management application\n" +
                "for managing menu, orders, and employees.\n\n" +
                "User: " + currentUser.getFullName(),
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CafeManagementApp());
    }
}
