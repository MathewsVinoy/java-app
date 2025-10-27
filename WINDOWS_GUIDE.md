# Windows Quick Start Guide

## For Windows Users - Get Started in 60 Seconds!

### Step 1: Check Java Installation
Open Command Prompt and type:
```cmd
java -version
```

**Expected output:**
```
openjdk version "11" or higher
```

If you get "command not found", [install Java here](https://www.oracle.com/java/technologies/downloads/#java11)

### Step 2: Navigate to Project Directory

**Method A: Using Command Prompt**
```cmd
cd C:\Users\YourUsername\path\to\cafe-management-system
```

**Method B: Quick Navigation**
1. Open File Explorer
2. Right-click inside the project folder
3. Select "Open PowerShell window here" or "Open Command Prompt window here"

### Step 3: Run the Application

Simply type:
```cmd
java -jar target/cafe-management-system.jar
```

Press Enter and wait 3-5 seconds...

### Step 4: Login
When the application opens:
- Username: **admin**
- Password: **admin**
- Click **Login**

✅ **Done! You're in!**

---

## Alternative: Batch File (No Command Prompt!)

### Create a Run Script
1. Right-click in the project folder
2. Select "New" → "Text Document"
3. Name it: `RUN.bat`
4. Open it in Notepad and paste:
```batch
@echo off
REM Navigate to project directory
cd /d "%~dp0"

REM Run the application
echo Starting Cafe Management System...
java -jar target/cafe-management-system.jar

REM Keep window open if there's an error
pause
```

5. Save and close
6. Double-click `RUN.bat` to start the application!

---

## If JAR File Not Found

**Error:** `"The system cannot find the file specified"`

**Solution: Build it first**

Open Command Prompt in the project folder and run:
```cmd
mvn clean package -DskipTests
```

Wait for build to complete, then run:
```cmd
java -jar target/cafe-management-system.jar
```

---

## Creating a Desktop Shortcut (Windows)

### Method 1: Direct Shortcut
1. Create the batch file above
2. Right-click on `RUN.bat`
3. Select "Send to" → "Desktop (create shortcut)"
4. Now you can double-click the desktop icon to run!

### Method 2: Custom Shortcut
1. Right-click on desktop
2. Select "New" → "Shortcut"
3. Enter command:
```
C:\Windows\System32\cmd.exe /k java -jar "C:\path\to\project\target\cafe-management-system.jar"
```
4. Click "Next"
5. Name it: "Cafe Management System"
6. Click "Finish"

Now you have a desktop shortcut! 🎉

---

## Common Issues & Solutions

### Issue 1: "java is not recognized"
**Solution:**
1. Download Java from [oracle.com](https://www.oracle.com/java/technologies/downloads/#java11)
2. Run the installer
3. During installation, make sure to install JDK (not just JRE)
4. Restart Command Prompt

### Issue 2: Application won't start
**Solution:**
Check if JAR exists in target folder:
```cmd
dir target\cafe-management-system.jar
```

If not found, rebuild:
```cmd
mvn clean package -DskipTests
```

### Issue 3: Login keeps failing
**Solution:**
- Username: `admin` (all lowercase)
- Password: `admin` (all lowercase)
- Make sure Caps Lock is OFF
- Try clearing the fields and typing again

### Issue 4: "Address already in use"
**Solution:**
- Close other instances of the application
- Use Task Manager to close any remaining java.exe processes
- Wait 10 seconds before restarting

### Issue 5: Slow startup
**Solution:**
First launch is slower (database initialization). Subsequent launches are faster!

If it takes more than 10 seconds:
```cmd
java -Xmx512m -jar target/cafe-management-system.jar
```

---

## Windows File Paths Explained

### Project Location Examples
- `C:\Users\John\Documents\cafe-management-system`
- `C:\Projects\java\cafe-management-system`
- `D:\Development\cafe-management-system`

### How to Find Your Path
1. Open File Explorer
2. Right-click on the cafe-management-system folder
3. Click "Copy as path"
4. Paste it in Command Prompt (Right-click → Paste)

---

## What to Do Next

### Create Your First Order
1. Click "Create Order" tab
2. Click "Create Order" button
3. Select items from the list
4. Add quantities
5. Click "Place Order"

### Add Menu Items
1. Go to "Menu" tab
2. Click "Add Menu Item"
3. Fill in: Name, Category, Price, Quantity
4. Click "Save"

### Create New Users
1. Go to "Users" tab
2. Click "Add User"
3. Enter: Username, Password, Full Name, Role
4. Click "Save"

### View Reports
1. Click "Dashboard" tab
2. See all statistics
3. View total items, orders, employees, payroll

---

## System Requirements for Windows

| Requirement | Minimum | Recommended |
|-------------|---------|------------|
| Windows Version | Windows 7 | Windows 10 / 11 |
| Java | Java 11 | Java 11 or higher |
| RAM | 512 MB | 1 GB or more |
| Disk Space | 100 MB | 500 MB |
| Resolution | 1024x768 | 1366x768 or higher |

---

## Advanced: Custom Memory Settings

If application is slow, allocate more memory:

```cmd
java -Xms256m -Xmx1024m -jar target/cafe-management-system.jar
```

Explanation:
- `-Xms256m` = Start with 256 MB
- `-Xmx1024m` = Max 1 GB memory
- Adjust based on your RAM

---

## Getting Help

1. **Check README.md** for complete documentation
2. **See DOCUMENTATION_INDEX.md** for all guides
3. **Read QUICK_REFERENCE.md** for features
4. **Check ORDER_CREATION_GUIDE.md** for order details

All files are in the project directory!

---

## Still Need Help?

Make sure:
- ✅ Java is installed (`java -version` works)
- ✅ You're in the correct directory
- ✅ The JAR file exists (`dir target`)
- ✅ No other instance is running
- ✅ Your antivirus isn't blocking it

Try rebuilding:
```cmd
mvn clean package -DskipTests
java -jar target/cafe-management-system.jar
```

---

**Version:** 2.1.0
**Last Updated:** October 27, 2025
**Status:** Production Ready ✅

**Happy cafeing!** ☕

