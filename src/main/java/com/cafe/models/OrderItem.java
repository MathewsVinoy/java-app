package com.cafe.models;

public class OrderItem {
    private int itemId;
    private MenuItem menuItem;
    private String menuItemName;
    private int quantity;
    private double subtotal;

    public OrderItem(int itemId, MenuItem menuItem, int quantity) {
        this.itemId = itemId;
        this.menuItem = menuItem;
        this.menuItemName = menuItem != null ? menuItem.getName() : "";
        this.quantity = quantity;
        this.subtotal = menuItem != null ? menuItem.getPrice() * quantity : 0;
    }

    public OrderItem() {
        this.menuItemName = "";
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    private void calculateSubtotal() {
        if (menuItem != null) {
            this.subtotal = menuItem.getPrice() * quantity;
        }
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
