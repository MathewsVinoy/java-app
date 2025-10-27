package com.cafe.ui;

import com.cafe.management.EmployeeManager;
import com.cafe.models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class EmployeePanel extends JPanel {
    private EmployeeManager employeeManager;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeePanel(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Position", "Salary", "Phone", "Hire Date", "Email"},
                0
        );
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton addBtn = new JButton("Add Employee");
        addBtn.addActionListener(e -> openAddDialog());

        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> openEditDialog());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteEmployee());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable());

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        return panel;
    }

    private void openAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Employee", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField hireDateField = new JTextField("YYYY-MM-DD");
        JTextField emailField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Hire Date:"));
        panel.add(hireDateField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String position = positionField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String phone = phoneField.getText();
                LocalDate hireDate = LocalDate.parse(hireDateField.getText());
                String email = emailField.getText();

                employeeManager.addEmployee(name, position, salary, phone, hireDate, email);
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(saveBtn);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void openEditDialog() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Employee employee = employeeManager.getEmployee(id);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Employee", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField(employee.getName());
        JTextField positionField = new JTextField(employee.getPosition());
        JTextField salaryField = new JTextField(String.valueOf(employee.getSalary()));
        JTextField phoneField = new JTextField(employee.getPhoneNumber());
        JTextField hireDateField = new JTextField(employee.getHireDate().toString());
        JTextField emailField = new JTextField(employee.getEmail());

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Hire Date:"));
        panel.add(hireDateField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String position = positionField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String phone = phoneField.getText();
                LocalDate hireDate = LocalDate.parse(hireDateField.getText());
                String email = emailField.getText();

                employeeManager.updateEmployee(id, name, position, salary, phone, hireDate, email);
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(updateBtn);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?");
        if (confirm == JOptionPane.YES_OPTION) {
            employeeManager.deleteEmployee(id);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Employee emp : employeeManager.getAllEmployees()) {
            tableModel.addRow(new Object[]{
                    emp.getEmployeeId(),
                    emp.getName(),
                    emp.getPosition(),
                    emp.getSalary(),
                    emp.getPhoneNumber(),
                    emp.getHireDate(),
                    emp.getEmail()
            });
        }
    }
}
