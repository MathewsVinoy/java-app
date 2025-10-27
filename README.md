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

### Prerequisites
- Java 11 or higher installed
- Maven 3.6.0 or higher installed (optional, for building from source)

**Check if Java is installed:**
```bash
java -version
```

If not installed, [download Java here](https://www.oracle.com/java/technologies/downloads/#java11)

### Running the Application

#### **Option 1: Direct JAR (Quickest Method - Windows, Linux, Mac)**

1. Open Command Prompt (Windows) or Terminal (Linux/Mac)
2. Navigate to the project directory:
   ```bash
   cd /path/to/cafe-management-system
   ```
3. Run the JAR file:
   ```bash
   java -jar target/cafe-management-system.jar
   ```

**Specific paths:**
- **Windows**: `cd C:\Users\YourName\path\to\cafe-management-system`
- **Linux**: `cd /home/user/path/to/cafe-management-system`
- **Mac**: `cd /Users/username/path/to/cafe-management-system`

#### **Option 2: Run from File Manager (Windows GUI)**

1. Open File Explorer
2. Navigate to: `your-project-path\target\`
3. Right-click on `cafe-management-system.jar`
4. Select "Open with" → "Java(TM) Platform SE binary"
5. Application launches!

#### **Option 3: Build and Run with Maven**

1. Open Command Prompt and navigate to project directory
2. Clean and build:
   ```bash
   mvn clean package -DskipTests
   ```
3. Run the application:
   ```bash
   java -jar target/cafe-management-system.jar
   ```

### Platform-Specific Instructions

#### **Windows**
```batch
# Open Command Prompt (cmd.exe)
cd C:\Users\YourName\path\to\cafe-management-system
java -jar target/cafe-management-system.jar
```

Or create a batch file (`run.bat`):
```batch
@echo off
cd /d %~dp0
java -jar target/cafe-management-system.jar
pause
```
Then double-click `run.bat` to run!

#### **Linux**
```bash
cd ~/path/to/cafe-management-system
java -jar target/cafe-management-system.jar
```

#### **Mac**
```bash
cd ~/path/to/cafe-management-system
java -jar target/cafe-management-system.jar
```

### Login Credentials (Default)
After launching, login with:
- **Username:** `admin`
- **Password:** `admin`

## Usage

### Login
On first launch, you'll see the login screen:
- Username: `admin`
- Password: `admin`

### Menu Tab
- **Add Menu Item**: Click "Add Menu Item" to add a new item to the menu
- **Edit Item**: Select an item and click "Edit" to modify it
- **Delete Item**: Select an item and click "Delete" to remove it
- **Refresh**: Click "Refresh" to reload the menu list

### Orders Tab (Create Order)
- **Create Order**: Click "Create Order" to place a new order
  - Browse menu items from database
  - Filter by category
  - Select items with quantities
  - See real-time total calculation
  - Place order and get order confirmation
- **Update Status**: Select an order and click "Update Status" to change its status
- **Delete Order**: Select an order and click "Delete" to remove it

### Order Details Tab
- View all orders with totals
- View order bills and receipts
- View complete order history with items
- Print bills

### Employees Tab
- **Add Employee**: Click "Add Employee" to add a new employee record
- **Edit Employee**: Select an employee and click "Edit" to modify their information
- **Delete Employee**: Select an employee and click "Delete" to remove them

### Users Tab (Admin/Manager only)
- **Add User**: Create new user accounts
- **Edit User**: Modify user information and roles
- **Delete User**: Remove users from the system
- **Assign Roles**: Set user roles (ADMIN, MANAGER, STAFF)

### Dashboard Tab
- View key business metrics:
  - Total Menu Items
  - Total Orders
  - Total Employees
  - Total Payroll

## Troubleshooting

### Windows
- **"'java' is not recognized as an internal or external command"**
  - Java is not installed or not in PATH
  - Solution: Download and install [Java 11+](https://www.oracle.com/java/technologies/downloads/#java11)
  - After installation, restart Command Prompt

- **"Cannot find file: target/cafe-management-system.jar"**
  - JAR file not built yet
  - Solution: Run `mvn clean package -DskipTests` first

- **"Address already in use" error**
  - Another instance is running
  - Solution: Close other instances or restart your computer

- **Application window opens but won't respond**
  - Check Java version with `java -version`
  - Ensure Java 11 or higher is installed
  - Try rebuilding: `mvn clean package -DskipTests`

### All Platforms
- **Login failed with "Invalid username or password"**
  - Credentials are case-sensitive
  - Check: Username=`admin`, Password=`admin` (lowercase)
  - Make sure Caps Lock is OFF

- **Database connection error**
  - Check write permissions in project directory
  - Ensure SQLite driver is included (check pom.xml)
  - Try deleting `cafe_management.db` to reset database

- **GUI doesn't display properly**
  - This is a Swing application, ensure graphics drivers are up to date
  - Try running with: `java -Xmx512m -jar target/cafe-management-system.jar`

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
