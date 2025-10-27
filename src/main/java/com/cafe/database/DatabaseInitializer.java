package com.cafe.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL," +
                    "full_name TEXT NOT NULL," +
                    "email TEXT," +
                    "role TEXT NOT NULL," +
                    "created_date DATE NOT NULL," +
                    "active BOOLEAN DEFAULT 1" +
                    ")";
            stmt.execute(createUsersTable);

            // Create menu items table
            String createMenuItemTable = "CREATE TABLE IF NOT EXISTS menu_items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "category TEXT NOT NULL," +
                    "price REAL NOT NULL," +
                    "quantity INTEGER NOT NULL," +
                    "description TEXT" +
                    ")";
            stmt.execute(createMenuItemTable);

            // Create orders table
            String createOrdersTable = "CREATE TABLE IF NOT EXISTS orders (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER NOT NULL," +
                    "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "total_amount REAL NOT NULL," +
                    "status TEXT NOT NULL," +
                    "FOREIGN KEY(user_id) REFERENCES users(id)" +
                    ")";
            stmt.execute(createOrdersTable);

            // Create order items table
            String createOrderItemsTable = "CREATE TABLE IF NOT EXISTS order_items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "order_id INTEGER NOT NULL," +
                    "menu_item_id INTEGER NOT NULL," +
                    "quantity INTEGER NOT NULL," +
                    "subtotal REAL NOT NULL," +
                    "FOREIGN KEY(order_id) REFERENCES orders(id)," +
                    "FOREIGN KEY(menu_item_id) REFERENCES menu_items(id)" +
                    ")";
            stmt.execute(createOrderItemsTable);

            // Create employees table
            String createEmployeesTable = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "position TEXT NOT NULL," +
                    "salary REAL NOT NULL," +
                    "phone_number TEXT," +
                    "hire_date DATE NOT NULL," +
                    "email TEXT" +
                    ")";
            stmt.execute(createEmployeesTable);

            // Insert default admin user if not exists
            String insertAdminUser = "INSERT OR IGNORE INTO users(username, password, full_name, email, role, created_date, active) " +
                    "VALUES('admin', 'admin', 'Administrator', 'admin@cafe.com', 'ADMIN', date('now'), 1)";
            stmt.execute(insertAdminUser);

            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
