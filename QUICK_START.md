# Cafe Management System - Quick Start Guide

## ✅ Project Successfully Created!

Your cafe management system is ready to use. Here's everything you need to know:

## 📋 What's Included

### ✨ Key Features
- **Menu Management** - Add, edit, delete, and organize menu items by category
- **Order Management** - Create and track orders with status updates
- **Employee Management** - Manage employee records including salary and contact info
- **Dashboard** - View key business metrics at a glance
- **SQLite Database** - Persistent data storage

### 📦 Project Structure
```
cafe-management-system/
├── src/main/java/com/cafe/
│   ├── models/              # Data models (MenuItem, Order, Employee, OrderItem)
│   ├── database/            # Database operations (DAO pattern)
│   ├── management/          # Business logic (Managers)
│   └── ui/                  # GUI components (Swing)
├── target/
│   ├── cafe-management-system.jar        # Executable JAR (13MB)
│   └── cafe-management-system-1.0.0.jar  # Regular JAR (37KB)
├── pom.xml                  # Maven configuration
└── README.md                # Full documentation
```

## 🚀 How to Run

### Option 1: Using JAR File (Easiest)
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
java -jar target/cafe-management-system.jar
```

### Option 2: Using Maven
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn exec:java -Dexec.mainClass="com.cafe.ui.CafeManagementApp"
```

### Option 3: Direct Compilation & Run
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979/f6876adb34d6/dev/java/new
javac -d . src/main/java/com/cafe/**/*.java
java com.cafe.ui.CafeManagementApp
```

## 📚 Using the Application

### Menu Tab
- **Add Item**: Enter name, category, price, quantity, and description
- **Edit Item**: Select and modify existing items
- **Delete Item**: Remove items from inventory
- **Refresh**: Reload the menu list

### Orders Tab
- **Create Order**: Set total amount and initial status
- **Update Status**: Change order status (Pending → Completed → Cancelled)
- **Delete Order**: Remove completed orders
- **Refresh**: Reload the order list

### Employees Tab
- **Add Employee**: Create employee record with salary and hire date
- **Edit Employee**: Update employee information
- **Delete Employee**: Remove employee records
- **Refresh**: Reload the employee list

### Dashboard Tab
- View total menu items in system
- View total number of orders
- View total employees
- View total payroll amount

## 🗄️ Database

The application automatically creates `cafe_management.db` (SQLite) on first run.

**Tables Created:**
- `menu_items` - Menu item inventory
- `orders` - Order records
- `order_items` - Items within orders
- `employees` - Employee records

## 🛠️ Technology Stack

- **Language**: Java 11+
- **GUI Framework**: Swing (built-in with Java)
- **Database**: SQLite
- **Build Tool**: Maven
- **JDBC Driver**: sqlite-jdbc 3.44.0.0

## 📝 Requirements Met

✅ Object-Oriented Programming (OOP)
- Classes: MenuItem, Order, OrderItem, Employee
- Inheritance: Panel classes extend JPanel
- Encapsulation: Private fields with getters/setters

✅ Database Operations
- CRUD operations for all entities
- Data persistence with SQLite
- Connection pooling

✅ GUI Interface
- Tabbed interface (4 tabs)
- Table views with sorting
- Dialog boxes for input
- Confirmation dialogs

✅ Business Logic
- Menu availability checking
- Order total calculation
- Payroll calculation
- Status management

## 🔧 Development

### Compile Only
```bash
mvn compile
```

### Build JAR
```bash
mvn package
```

### Clean Build
```bash
mvn clean install
```

### View Project Tree
```bash
tree src/
```

## 📊 Example Usage Workflow

1. **Add Menu Items**
   - Click Menu tab → Add Menu Item
   - Enter: "Espresso", "Beverages", 3.50, 50, "Single shot espresso"

2. **Create an Order**
   - Click Orders tab → Create Order
   - Enter: Total Amount: 10.50, Status: Pending

3. **Add Employees**
   - Click Employees tab → Add Employee
   - Enter: John, Barista, 25000, 9876543210, 2024-01-15, john@cafe.com

4. **View Dashboard**
   - Click Dashboard tab
   - See all system statistics

## ⚠️ Troubleshooting

### JAR Won't Run
- Ensure Java 11+ is installed: `java -version`
- Check file permissions: `chmod +x target/cafe-management-system.jar`

### Database Errors
- Delete `cafe_management.db` to reset
- Check write permissions in the project directory

### GUI Not Showing
- Ensure X11 forwarding is enabled if using SSH
- Try running: `java -Djava.awt.headless=false -jar target/cafe-management-system.jar`

## 🚀 Next Steps to Enhance

1. **Add Authentication** - User login system
2. **Export Reports** - Generate PDF/Excel reports
3. **Advanced Search** - Filter items by date/category
4. **Notifications** - Alert system for stock levels
5. **Multi-user** - Concurrent user support
6. **Table Management** - For restaurants with table seating
7. **Payment Integration** - Support multiple payment methods
8. **Backup/Restore** - Database backup functionality

## 📞 Support

For issues or questions:
1. Check the detailed README.md in the project root
2. Review the class Javadoc comments
3. Examine the test scenarios above

---

**Version**: 1.0.0
**Last Updated**: October 2025
**Status**: ✅ Ready for Production Use
