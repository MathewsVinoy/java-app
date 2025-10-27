package com.cafe.management;

import com.cafe.models.Order;
import com.cafe.database.OrderDAO;
import java.util.List;

public class OrderManager {

    public int createOrder(Order order) {
        return OrderDAO.addOrder(order);
    }

    public void updateOrderStatus(int orderId, String status) {
        OrderDAO.updateOrderStatus(orderId, status);
    }

    public Order getOrder(int orderId) {
        return OrderDAO.getOrderById(orderId);
    }

    public List<Order> getAllOrders() {
        return OrderDAO.getAllOrders();
    }

    public void deleteOrder(int orderId) {
        OrderDAO.deleteOrder(orderId);
    }

    public double calculateTotalOrderAmount(Order order) {
        return order.getItems().stream()
                .mapToDouble(item -> item.getSubtotal())
                .sum();
    }

    public List<Order> getOrdersByStatus(String status) {
        List<Order> orders = getAllOrders();
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .toList();
    }
}
