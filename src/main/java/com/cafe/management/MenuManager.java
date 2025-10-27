package com.cafe.management;

import com.cafe.models.MenuItem;
import com.cafe.database.MenuItemDAO;
import java.util.List;

public class MenuManager {

    public void addMenuItem(String name, String category, double price, int quantity, String description) {
        MenuItem item = new MenuItem(0, name, category, price, quantity, description);
        MenuItemDAO.addMenuItem(item);
    }

    public void updateMenuItem(int id, String name, String category, double price, int quantity, String description) {
        MenuItem item = new MenuItem(id, name, category, price, quantity, description);
        MenuItemDAO.updateMenuItem(item);
    }

    public void deleteMenuItem(int id) {
        MenuItemDAO.deleteMenuItem(id);
    }

    public MenuItem getMenuItem(int id) {
        return MenuItemDAO.getMenuItemById(id);
    }

    public List<MenuItem> getAllMenuItems() {
        return MenuItemDAO.getAllMenuItems();
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return MenuItemDAO.getMenuItemsByCategory(category);
    }

    public void updateItemQuantity(int itemId, int newQuantity) {
        MenuItem item = MenuItemDAO.getMenuItemById(itemId);
        if (item != null) {
            item.setQuantity(newQuantity);
            MenuItemDAO.updateMenuItem(item);
        }
    }

    public boolean isItemAvailable(int itemId, int quantity) {
        MenuItem item = MenuItemDAO.getMenuItemById(itemId);
        return item != null && item.getQuantity() >= quantity;
    }
}
