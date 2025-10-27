package com.cafe.util;

import com.cafe.database.DatabaseInitializer;
import com.cafe.database.UserDAO;
import com.cafe.models.User;
import com.cafe.management.UserManager;

public class TestLogin {
    public static void main(String[] args) {
        System.out.println("=== LOGIN TEST ===");
        
        // Initialize database
        System.out.println("1. Initializing database...");
        DatabaseInitializer.initializeDatabase();
        
        // Test UserDAO directly
        System.out.println("\n2. Testing UserDAO.authenticateUser()...");
        User user = UserDAO.authenticateUser("admin", "admin");
        if (user != null) {
            System.out.println("✓ Direct DAO authentication successful!");
            System.out.println("   User: " + user.getUsername() + ", Role: " + user.getRole());
        } else {
            System.out.println("✗ Direct DAO authentication failed!");
        }
        
        // Test UserManager
        System.out.println("\n3. Testing UserManager.login()...");
        UserManager userManager = new UserManager();
        User user2 = userManager.login("admin", "admin");
        if (user2 != null) {
            System.out.println("✓ UserManager authentication successful!");
            System.out.println("   User: " + user2.getUsername() + ", Role: " + user2.getRole());
        } else {
            System.out.println("✗ UserManager authentication failed!");
        }
        
        // Check if user exists
        System.out.println("\n4. Checking if admin user exists...");
        User adminUser = UserDAO.getUserByUsername("admin");
        if (adminUser != null) {
            System.out.println("✓ Admin user found!");
            System.out.println("   ID: " + adminUser.getUserId());
            System.out.println("   Username: " + adminUser.getUsername());
            System.out.println("   Password: " + adminUser.getPassword());
            System.out.println("   Full Name: " + adminUser.getFullName());
            System.out.println("   Role: " + adminUser.getRole());
            System.out.println("   Active: " + adminUser.isActive());
        } else {
            System.out.println("✗ Admin user NOT found!");
        }
        
        // Get all users
        System.out.println("\n5. All users in database:");
        var allUsers = UserDAO.getAllUsers();
        if (allUsers.isEmpty()) {
            System.out.println("   No users found!");
        } else {
            for (User u : allUsers) {
                System.out.println("   - " + u.getUsername() + " (Role: " + u.getRole() + ", Active: " + u.isActive() + ")");
            }
        }
    }
}
