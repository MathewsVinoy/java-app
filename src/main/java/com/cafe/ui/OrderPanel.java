package com.cafe.ui;

import com.cafe.management.OrderManager;
import com.cafe.management.MenuManager;
import com.cafe.models.Order;
import com.cafe.models.MenuItem;
import com.cafe.models.OrderItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPanel extends JPanel {
    private OrderManager orderManager;
    private MenuManager menuManager;
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public OrderPanel(OrderManager orderManager, MenuManager menuManager) {
        this.orderManager = orderManager;
        this.menuManager = menuManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table
        tableModel = new DefaultTableModel(
                new String[]{"Order ID", "Total Amount", "Status"},
                0
        );
        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton createBtn = new JButton("Create Order");
        createBtn.addActionListener(e -> openCreateOrderDialog());

        JButton updateStatusBtn = new JButton("Update Status");
        updateStatusBtn.addActionListener(e -> updateOrderStatus());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteOrder());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable());

        panel.add(createBtn);
        panel.add(updateStatusBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        return panel;
    }

    private void openCreateOrderDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Create Order", true);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left panel - Menu Items selection
        JPanel leftPanel = createMenuItemsPanel();

        // Right panel - Order Details and Summary
        JPanel rightPanel = createOrderDetailsPanel(dialog);

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private JPanel createMenuItemsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Available Menu Items"));

        // Category filter
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel categoryLabel = new JLabel("Category:");
        List<String> categories = getAvailableCategories();
        categories.add(0, "All");
        JComboBox<String> categoryCombo = new JComboBox<>(categories.toArray(new String[0]));

        filterPanel.add(categoryLabel);
        filterPanel.add(categoryCombo);

        // Menu items table
        DefaultTableModel menuTableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Category", "Price", "Available", "Qty"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only Qty column is editable
            }
        };

        JTable menuTable = new JTable(menuTableModel);
        menuTable.getColumnModel().getColumn(5).setMaxWidth(50);
        JScrollPane menuScrollPane = new JScrollPane(menuTable);

        // Populate initial items
        populateMenuTable(menuTableModel, "All");

        categoryCombo.addActionListener(e -> {
            String selectedCategory = (String) categoryCombo.getSelectedItem();
            populateMenuTable(menuTableModel, selectedCategory);
        });

        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(menuScrollPane, BorderLayout.CENTER);

        // Store reference for later use
        menuTable.setName("menuTable");

        return panel;
    }

    private JPanel createOrderDetailsPanel(JDialog dialog) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Order Details"));
        panel.setPreferredSize(new Dimension(350, 600));

        // Order items table
        DefaultTableModel orderTableModel = new DefaultTableModel(
                new String[]{"Item", "Price", "Qty", "Total"}, 0
        );
        JTable orderItemsTable = new JTable(orderTableModel);
        orderItemsTable.setName("orderItemsTable");
        JScrollPane orderScrollPane = new JScrollPane(orderItemsTable);

        // Summary panel
        JPanel summaryPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));

        JLabel grandTotalLabel = new JLabel("Grand Total:");
        JLabel grandTotalValue = new JLabel("₹ 0.00");
        grandTotalValue.setFont(new Font("Arial", Font.BOLD, 14));
        grandTotalValue.setForeground(new Color(0, 100, 0));
        grandTotalValue.setName("grandTotalValue");

        summaryPanel.add(grandTotalLabel);
        summaryPanel.add(grandTotalValue);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton addItemBtn = new JButton("Add to Cart");
        addItemBtn.addActionListener(e -> addItemToOrder(orderTableModel, grandTotalValue));

        JButton clearItemBtn = new JButton("Remove Item");
        clearItemBtn.addActionListener(e -> removeItemFromOrder(orderItemsTable, orderTableModel, grandTotalValue));

        JButton placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.setBackground(new Color(0, 150, 0));
        placeOrderBtn.setForeground(Color.WHITE);
        placeOrderBtn.setFont(new Font("Arial", Font.BOLD, 12));
        placeOrderBtn.addActionListener(e -> placeOrder(orderTableModel, dialog));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonsPanel.add(addItemBtn);
        buttonsPanel.add(clearItemBtn);
        buttonsPanel.add(placeOrderBtn);
        buttonsPanel.add(cancelBtn);

        panel.add(orderScrollPane, BorderLayout.CENTER);
        panel.add(summaryPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        // Store reference to order items table for addItemToOrder
        orderItemsTable.setName("orderItemsTable");

        return panel;
    }

    private void populateMenuTable(DefaultTableModel menuTableModel, String category) {
        menuTableModel.setRowCount(0);
        List<MenuItem> items;

        if (category.equals("All")) {
            items = menuManager.getAllMenuItems();
        } else {
            items = menuManager.getMenuItemsByCategory(category);
        }

        for (MenuItem item : items) {
            menuTableModel.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getCategory(),
                    String.format("₹ %.2f", item.getPrice()),
                    item.getQuantity(),
                    0  // Quantity field
            });
        }
    }

    private List<String> getAvailableCategories() {
        List<String> categories = new ArrayList<>();
        for (MenuItem item : menuManager.getAllMenuItems()) {
            if (!categories.contains(item.getCategory())) {
                categories.add(item.getCategory());
            }
        }
        return categories;
    }

    private void addItemToOrder(DefaultTableModel orderTableModel, JLabel grandTotalValue) {
        // Find the menu table in the dialog
        Container parent = (Container) SwingUtilities.getWindowAncestor((Component) grandTotalValue);
        JTable menuTable = findTableByName(parent, "menuTable");

        if (menuTable == null) {
            JOptionPane.showMessageDialog(this, "Cannot find menu table!");
            return;
        }

        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item from the menu!");
            return;
        }

        DefaultTableModel menuTableModel = (DefaultTableModel) menuTable.getModel();
        int itemId = (int) menuTableModel.getValueAt(selectedRow, 0);
        String itemName = (String) menuTableModel.getValueAt(selectedRow, 1);
        String priceStr = (String) menuTableModel.getValueAt(selectedRow, 3);
        double price = Double.parseDouble(priceStr.replaceAll("[₹ ]", ""));
        int quantity = (int) menuTableModel.getValueAt(selectedRow, 5);

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity!");
            return;
        }

        int availableQty = (int) menuTableModel.getValueAt(selectedRow, 4);
        if (quantity > availableQty) {
            JOptionPane.showMessageDialog(this, "Only " + availableQty + " items available!");
            return;
        }

        double total = price * quantity;

        // Add to order items table
        orderTableModel.addRow(new Object[]{
                itemName,
                String.format("₹ %.2f", price),
                quantity,
                String.format("₹ %.2f", total)
        });

        // Store item ID for later use (hidden column)
        orderTableModel.addRow(new Object[]{
                itemId,
                "",
                "",
                ""
        });

        // Reset quantity field
        menuTableModel.setValueAt(0, selectedRow, 5);

        // Update grand total
        updateGrandTotal(orderTableModel, grandTotalValue);
    }

    private void removeItemFromOrder(JTable orderItemsTable, DefaultTableModel orderTableModel, JLabel grandTotalValue) {
        int selectedRow = orderItemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to remove!");
            return;
        }

        orderTableModel.removeRow(selectedRow);
        updateGrandTotal(orderTableModel, grandTotalValue);
    }

    private void updateGrandTotal(DefaultTableModel orderTableModel, JLabel grandTotalValue) {
        double grandTotal = 0;
        for (int i = 0; i < orderTableModel.getRowCount(); i++) {
            Object totalObj = orderTableModel.getValueAt(i, 3);
            if (totalObj != null && !totalObj.toString().isEmpty()) {
                String totalStr = totalObj.toString().replaceAll("[₹ ]", "");
                try {
                    grandTotal += Double.parseDouble(totalStr);
                } catch (NumberFormatException e) {
                    // Skip non-numeric values
                }
            }
        }
        grandTotalValue.setText(String.format("₹ %.2f", grandTotal));
    }

    private void placeOrder(DefaultTableModel orderTableModel, JDialog dialog) {
        if (orderTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add items to the order!");
            return;
        }

        try {
            // Create order
            Order order = new Order();
            order.setStatus("Pending");
            
            double totalAmount = 0;
            List<OrderItem> items = new ArrayList<>();

            // Process order items
            for (int i = 0; i < orderTableModel.getRowCount(); i++) {
                String itemName = (String) orderTableModel.getValueAt(i, 0);
                String priceStr = (String) orderTableModel.getValueAt(i, 1);
                int quantity = (int) orderTableModel.getValueAt(i, 2);
                String totalStr = (String) orderTableModel.getValueAt(i, 3);

                double price = Double.parseDouble(priceStr.replaceAll("[₹ ]", ""));
                double subtotal = Double.parseDouble(totalStr.replaceAll("[₹ ]", ""));
                totalAmount += subtotal;

                // Create OrderItem
                OrderItem orderItem = new OrderItem();
                orderItem.setMenuItemName(itemName);
                orderItem.setQuantity(quantity);
                orderItem.setSubtotal(subtotal);
                items.add(orderItem);
            }

            order.setTotalAmount(totalAmount);
            order.setItems(items);

            int orderId = orderManager.createOrder(order);
            refreshTable();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Order #" + orderId + " placed successfully!\nTotal: ₹ " + String.format("%.2f", totalAmount));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error placing order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private JTable findTableByName(Container container, String name) {
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp instanceof JTable && comp.getName() != null && comp.getName().equals(name)) {
                return (JTable) comp;
            }
            if (comp instanceof Container) {
                JTable result = findTableByName((Container) comp, name);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private void updateOrderStatus() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order!");
            return;
        }

        int orderId = (int) tableModel.getValueAt(selectedRow, 0);

        String[] statuses = {"Pending", "Completed", "Cancelled"};
        String status = (String) JOptionPane.showInputDialog(
                this,
                "Select new status:",
                "Update Order Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statuses,
                statuses[0]
        );

        if (status != null) {
            orderManager.updateOrderStatus(orderId, status);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Order status updated!");
        }
    }

    private void deleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to delete!");
            return;
        }

        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this order?");
        if (confirm == JOptionPane.YES_OPTION) {
            orderManager.deleteOrder(orderId);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Order deleted successfully!");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Order order : orderManager.getAllOrders()) {
            tableModel.addRow(new Object[]{
                    order.getOrderId(),
                    order.getTotalAmount(),
                    order.getStatus()
            });
        }
    }
}
