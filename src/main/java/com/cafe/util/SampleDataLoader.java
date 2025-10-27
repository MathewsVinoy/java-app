package com.cafe.util;

import com.cafe.management.MenuManager;
import com.cafe.management.EmployeeManager;
import java.time.LocalDate;

/**
 * Utility class to seed the database with sample data for testing and demonstration
 */
public class SampleDataLoader {

    /**
     * Loads sample menu items into the database
     */
    public static void loadSampleMenuItems(MenuManager menuManager) {
        // Beverages
        menuManager.addMenuItem("Espresso", "Beverages", 2.50, 100, "Single shot espresso");
        menuManager.addMenuItem("Americano", "Beverages", 3.00, 100, "Espresso with hot water");
        menuManager.addMenuItem("Cappuccino", "Beverages", 4.00, 80, "Espresso with steamed milk");
        menuManager.addMenuItem("Latte", "Beverages", 4.50, 75, "Espresso with hot milk");
        menuManager.addMenuItem("Cold Brew", "Beverages", 3.50, 60, "Chilled coffee concentrate");

        // Snacks
        menuManager.addMenuItem("Croissant", "Snacks", 3.50, 50, "Buttery French pastry");
        menuManager.addMenuItem("Muffin", "Snacks", 2.50, 60, "Chocolate chip muffin");
        menuManager.addMenuItem("Donut", "Snacks", 2.00, 100, "Glazed donut");
        menuManager.addMenuItem("Sandwich", "Snacks", 5.50, 40, "Chicken & cheese sandwich");

        // Desserts
        menuManager.addMenuItem("Cake Slice", "Desserts", 4.00, 30, "Chocolate cake slice");
        menuManager.addMenuItem("Brownie", "Desserts", 3.00, 50, "Rich chocolate brownie");
        menuManager.addMenuItem("Cheesecake", "Desserts", 5.00, 20, "New York style cheesecake");

        System.out.println("Sample menu items loaded successfully!");
    }

    /**
     * Loads sample employees into the database
     */
    public static void loadSampleEmployees(EmployeeManager employeeManager) {
        employeeManager.addEmployee(
                "John Smith",
                "Barista",
                2500.00,
                "+1-555-0101",
                LocalDate.of(2023, 1, 15),
                "john.smith@cafe.com"
        );

        employeeManager.addEmployee(
                "Sarah Johnson",
                "Manager",
                3500.00,
                "+1-555-0102",
                LocalDate.of(2022, 6, 1),
                "sarah.johnson@cafe.com"
        );

        employeeManager.addEmployee(
                "Mike Davis",
                "Chef",
                3000.00,
                "+1-555-0103",
                LocalDate.of(2023, 3, 20),
                "mike.davis@cafe.com"
        );

        employeeManager.addEmployee(
                "Emma Wilson",
                "Cashier",
                2000.00,
                "+1-555-0104",
                LocalDate.of(2023, 9, 10),
                "emma.wilson@cafe.com"
        );

        System.out.println("Sample employees loaded successfully!");
    }

    /**
     * Loads all sample data
     */
    public static void loadAllSampleData(MenuManager menuManager, EmployeeManager employeeManager) {
        System.out.println("Loading sample data...");
        loadSampleMenuItems(menuManager);
        loadSampleEmployees(employeeManager);
        System.out.println("All sample data loaded!");
    }
}
