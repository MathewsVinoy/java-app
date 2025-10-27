package com.cafe.ui;

import com.cafe.management.UserManager;
import com.cafe.models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserManagementPanel extends JPanel {
    private UserManager userManager;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserManagementPanel(UserManager userManager) {
        this.userManager = userManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Username", "Full Name", "Email", "Role", "Created Date", "Active"},
                0
        );
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton addBtn = new JButton("Add User");
        addBtn.addActionListener(e -> openAddDialog());

        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> openEditDialog());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteUser());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable());

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        return panel;
    }

    private void openAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add User", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField fullNameField = new JTextField();
        JTextField emailField = new JTextField();
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"ADMIN", "STAFF", "MANAGER"});

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Full Name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleCombo);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String role = (String) roleCombo.getSelectedItem();

                if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                userManager.addUser(username, password, fullName, email, role);
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "User added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(saveBtn);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void openEditDialog() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to edit!");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        User user = userManager.getUser(userId);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit User", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField usernameField = new JTextField(user.getUsername());
        JPasswordField passwordField = new JPasswordField(user.getPassword());
        JTextField fullNameField = new JTextField(user.getFullName());
        JTextField emailField = new JTextField(user.getEmail());
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"ADMIN", "STAFF", "MANAGER"});
        roleCombo.setSelectedItem(user.getRole());
        JCheckBox activeCheckBox = new JCheckBox("Active", user.isActive());

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Full Name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleCombo);
        panel.add(new JLabel(""));
        panel.add(activeCheckBox);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String role = (String) roleCombo.getSelectedItem();
                boolean active = activeCheckBox.isSelected();

                userManager.updateUser(userId, username, password, fullName, email, role, active);
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "User updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(updateBtn);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete!");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?");
        if (confirm == JOptionPane.YES_OPTION) {
            userManager.deleteUser(userId);
            refreshTable();
            JOptionPane.showMessageDialog(this, "User deleted successfully!");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (User user : userManager.getAllUsers()) {
            tableModel.addRow(new Object[]{
                    user.getUserId(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getRole(),
                    user.getCreatedDate(),
                    user.isActive() ? "Yes" : "No"
            });
        }
    }
}
