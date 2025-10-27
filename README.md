# Cafe Management System

A comprehensive Java-based cafe management system with a graphical user interface (GUI) for managing menu items, orders, employees, and business analytics.

## Features

- **Menu Management**: Add, update, delete, and view menu items with categories, prices, and inventory
- **Order Management**: Create and track orders with status updates
- **Employee Management**: Manage employee records including salary, position, and contact information
- **Dashboard**: View key statistics including total menu items, orders, employees, and payroll
- **Database**: SQLite database for persistent data storage

## Project Structure

```
cafe-management-system/
├── src/main/java/com/cafe/
│   ├── models/           # Data models (MenuItem, Order, OrderItem, Employee)
│   ├── database/         # Database operations (DAO classes)
│   ├── management/       # Business logic managers
│   └── ui/               # GUI components (Swing)
├── pom.xml               # Maven configuration
└── README.md             # This file
```

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher
- SQLite JDBC driver (included in pom.xml)

## Installation & Setup

1. **Clone or download the project**
   ```bash
   cd /path/to/cafe-management-system
   ```

2. **Build the project using Maven**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.cafe.ui.CafeManagementApp"
   ```

   Or directly run the JAR file:
   ```bash
   java -jar target/cafe-management-system.jar
   ```

## Usage

### Menu Tab
- **Add Menu Item**: Click "Add Menu Item" to add a new item to the menu
- **Edit Item**: Select an item and click "Edit" to modify it
- **Delete Item**: Select an item and click "Delete" to remove it
- **Refresh**: Click "Refresh" to reload the menu list

### Orders Tab
- **Create Order**: Click "Create Order" to create a new order with total amount and status
- **Update Status**: Select an order and click "Update Status" to change its status (Pending, Completed, Cancelled)
- **Delete Order**: Select an order and click "Delete" to remove it
- **Refresh**: Click "Refresh" to reload the order list

### Employees Tab
- **Add Employee**: Click "Add Employee" to add a new employee record
- **Edit Employee**: Select an employee and click "Edit" to modify their information
- **Delete Employee**: Select an employee and click "Delete" to remove them
- **Refresh**: Click "Refresh" to reload the employee list

### Dashboard Tab
- View key business metrics:
  - Total Menu Items
  - Total Orders
  - Total Employees
  - Total Payroll

## Classes

### Models
- `MenuItem`: Represents a menu item with id, name, category, price, quantity, and description
- `Order`: Represents an order with items, total amount, and status
- `OrderItem`: Represents individual items in an order
- `Employee`: Represents an employee with position, salary, and contact information

### Database (DAO)
- `DatabaseConnection`: Manages SQLite database connections
- `DatabaseInitializer`: Creates and initializes database tables
- `MenuItemDAO`: Data access object for menu items
- `OrderDAO`: Data access object for orders
- `EmployeeDAO`: Data access object for employees

### Management
- `MenuManager`: Business logic for menu operations
- `OrderManager`: Business logic for order operations
- `EmployeeManager`: Business logic for employee operations

### UI
- `CafeManagementApp`: Main application window with tabbed interface
- `MenuPanel`: UI for menu management
- `OrderPanel`: UI for order management
- `EmployeePanel`: UI for employee management
- `DashboardPanel`: UI for displaying business statistics

## Database

The application uses SQLite for data persistence. The database file (`cafe_management.db`) is created automatically on first run.

### Tables
- `menu_items`: Stores menu item information
- `orders`: Stores order information
- `order_items`: Stores items within orders
- `employees`: Stores employee information

## Future Enhancements

- User authentication and role-based access
- Advanced reporting and analytics
- Multiple user support with audit logs
- Inventory alerts and notifications
- Integration with payment systems
- Receipt printing functionality
- Customer loyalty program
- Table reservation system

## License

This project is open source and available for personal and commercial use.

## Author

Created as a comprehensive Java learning project demonstrating:
- Object-oriented programming principles
- Database operations with JDBC
- GUI development with Swing
- MVC (Model-View-Controller) architecture
- Data persistence and management



# java-app
