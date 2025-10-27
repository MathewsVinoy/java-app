package com.cafe.ui;

import com.cafe.management.OrderManager;
import com.cafe.management.MenuManager;
import com.cafe.models.Order;
import com.cafe.models.OrderItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDetailsPanel extends JPanel {
    private OrderManager orderManager;
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public OrderDetailsPanel(OrderManager orderManager, MenuManager menuManager) {
        this.orderManager = orderManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table
        tableModel = new DefaultTableModel(
                new String[]{"Order ID", "Order Date", "Total Amount", "Status", "Items"},
                0
        );
        orderTable = new JTable(tableModel);
        orderTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton viewBillBtn = new JButton("View Bill");
        viewBillBtn.addActionListener(e -> viewBill());

        JButton viewDetailsBtn = new JButton("View Details");
        viewDetailsBtn.addActionListener(e -> viewOrderDetails());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable());

        panel.add(viewBillBtn);
        panel.add(viewDetailsBtn);
        panel.add(refreshBtn);

        return panel;
    }

    private void viewBill() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to view bill!");
            return;
        }

        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        Order order = orderManager.getOrder(orderId);

        if (order == null) {
            JOptionPane.showMessageDialog(this, "Order not found!");
            return;
        }

        // Generate bill
        StringBuilder bill = new StringBuilder();
        bill.append("═══════════════════════════════════\n");
        bill.append("         CAFE BILL RECEIPT\n");
        bill.append("═══════════════════════════════════\n\n");
        bill.append("Order ID: ").append(order.getOrderId()).append("\n");
        bill.append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
        bill.append("Status: ").append(order.getStatus()).append("\n");
        bill.append("\n───────────────────────────────────\n");
        bill.append("ITEMS:\n");
        bill.append("───────────────────────────────────\n");

        for (OrderItem item : order.getItems()) {
            bill.append(String.format("%-20s %5d x %.2f = %.2f\n",
                    item.getMenuItem().getName().substring(0, Math.min(20, item.getMenuItem().getName().length())),
                    item.getQuantity(),
                    item.getMenuItem().getPrice(),
                    item.getSubtotal()
            ));
        }

        bill.append("───────────────────────────────────\n");
        bill.append(String.format("TOTAL: Rs. %.2f\n", order.getTotalAmount()));
        bill.append("═══════════════════════════════════\n");
        bill.append("Thank you for your order!\n");
        bill.append("═══════════════════════════════════");

        JDialog billDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Order Bill - #" + orderId, true);
        billDialog.setSize(500, 400);
        billDialog.setLocationRelativeTo(this);

        JTextArea billText = new JTextArea(bill.toString());
        billText.setFont(new Font("Courier New", Font.PLAIN, 12));
        billText.setEditable(false);
        billText.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(billText);
        billDialog.add(scrollPane);

        JButton printBtn = new JButton("Print");
        printBtn.addActionListener(e -> {
            try {
                billText.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Print failed: " + ex.getMessage());
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(printBtn);
        billDialog.add(buttonPanel, "South");

        billDialog.setVisible(true);
    }

    private void viewOrderDetails() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to view details!");
            return;
        }

        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        Order order = orderManager.getOrder(orderId);

        if (order == null) {
            JOptionPane.showMessageDialog(this, "Order not found!");
            return;
        }

        JDialog detailsDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Order Details - #" + orderId, true);
        detailsDialog.setSize(600, 400);
        detailsDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Order Info
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.add(new JLabel("Order ID:"));
        infoPanel.add(new JLabel(String.valueOf(order.getOrderId())));
        infoPanel.add(new JLabel("Status:"));
        infoPanel.add(new JLabel(order.getStatus()));
        infoPanel.add(new JLabel("Total Amount:"));
        infoPanel.add(new JLabel(String.format("Rs. %.2f", order.getTotalAmount())));
        infoPanel.add(new JLabel("Order Date:"));
        infoPanel.add(new JLabel(order.getOrderDate() != null ? order.getOrderDate().toString() : "N/A"));

        // Items table
        DefaultTableModel itemsModel = new DefaultTableModel(
                new String[]{"Item Name", "Quantity", "Price", "Subtotal"},
                0
        );
        for (OrderItem item : order.getItems()) {
            itemsModel.addRow(new Object[]{
                    item.getMenuItem().getName(),
                    item.getQuantity(),
                    String.format("Rs. %.2f", item.getMenuItem().getPrice()),
                    String.format("Rs. %.2f", item.getSubtotal())
            });
        }

        JTable itemsTable = new JTable(itemsModel);
        JScrollPane itemsScrollPane = new JScrollPane(itemsTable);

        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(itemsScrollPane, BorderLayout.CENTER);

        detailsDialog.add(panel);
        detailsDialog.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Order order : orderManager.getAllOrders()) {
            int itemCount = order.getItems().size();
            tableModel.addRow(new Object[]{
                    order.getOrderId(),
                    order.getOrderDate() != null ? order.getOrderDate() : "N/A",
                    String.format("Rs. %.2f", order.getTotalAmount()),
                    order.getStatus(),
                    itemCount + " items"
            });
        }
    }
}
