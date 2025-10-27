package com.cafe.ui;

import com.cafe.management.MenuManager;
import com.cafe.models.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuPanel extends JPanel {
    private MenuManager menuManager;
    private JTable menuTable;
    private DefaultTableModel tableModel;

    public MenuPanel(MenuManager menuManager) {
        this.menuManager = menuManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Category", "Price", "Quantity", "Description"},
                0
        );
        menuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(menuTable);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton addBtn = new JButton("Add Menu Item");
        addBtn.addActionListener(e -> openAddDialog());

        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> openEditDialog());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteMenuItem());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable());

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        return panel;
    }

    private void openAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Menu Item", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField descriptionField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String description = descriptionField.getText();

                menuManager.addMenuItem(name, category, price, quantity, description);
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Menu item added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(saveBtn);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void openEditDialog() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a menu item to edit!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        MenuItem item = menuManager.getMenuItem(id);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Menu Item", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField(item.getName());
        JTextField categoryField = new JTextField(item.getCategory());
        JTextField priceField = new JTextField(String.valueOf(item.getPrice()));
        JTextField quantityField = new JTextField(String.valueOf(item.getQuantity()));
        JTextField descriptionField = new JTextField(item.getDescription());

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String description = descriptionField.getText();

                menuManager.updateMenuItem(id, name, category, price, quantity, description);
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Menu item updated successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(updateBtn);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void deleteMenuItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a menu item to delete!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?");
        if (confirm == JOptionPane.YES_OPTION) {
            menuManager.deleteMenuItem(id);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Menu item deleted successfully!");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (MenuItem item : menuManager.getAllMenuItems()) {
            tableModel.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getCategory(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getDescription()
            });
        }
    }
}
