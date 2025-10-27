package com.cafe.ui;

import com.cafe.management.MenuManager;
import com.cafe.management.OrderManager;
import com.cafe.management.EmployeeManager;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel(MenuManager menuManager, OrderManager orderManager, EmployeeManager employeeManager) {

        setLayout(new GridLayout(2, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(createStatCard("Total Menu Items", String.valueOf(menuManager.getAllMenuItems().size())));
        add(createStatCard("Total Orders", String.valueOf(orderManager.getAllOrders().size())));
        add(createStatCard("Total Employees", String.valueOf(employeeManager.getAllEmployees().size())));
        add(createStatCard("Total Payroll", String.format("$%.2f", employeeManager.getTotalPayroll())));
    }

    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        card.setBackground(new Color(230, 240, 250));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}
