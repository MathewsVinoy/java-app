# Cafe Management System - Commands Reference

## 📍 Project Location
```
/media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
```

## 🚀 Running the Application

### Quick Start (Recommended)
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
java -jar target/cafe-management-system.jar
```

### Using Shell Script
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
./run.sh
```

### Using Maven
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn exec:java -Dexec.mainClass="com.cafe.ui.CafeManagementApp"
```

## 🔨 Building the Project

### Clean Build
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn clean compile
```

### Package as JAR
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn package -DskipTests
```

### Full Build Pipeline
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn clean install
```

## 📂 Navigation Commands

### View Project Structure
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
find src -type f -name "*.java" | sort
```

### View Documentation Files
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
ls -la *.md
```

### View Build Artifacts
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
ls -lh target/*.jar
```

### View Database
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
ls -lh cafe_management.db  # Created after first run
```

## 🔍 Exploration Commands

### Count Java Files
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
find src -name "*.java" | wc -l
```

### Count Lines of Code
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
find src -name "*.java" -exec wc -l {} + | tail -1
```

### View Specific Class
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
cat src/main/java/com/cafe/ui/CafeManagementApp.java
```

### View All Classes by Category
```bash
# Models
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
ls src/main/java/com/cafe/models/

# Database
ls src/main/java/com/cafe/database/

# Business Logic
ls src/main/java/com/cafe/management/

# UI
ls src/main/java/com/cafe/ui/

# Utilities
ls src/main/java/com/cafe/util/
```

## 📚 Documentation

### Read README
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
cat README.md
```

### Read Quick Start
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
cat QUICK_START.md
```

### Read Project Summary
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
cat PROJECT_SUMMARY.md
```

## 🧹 Cleanup Commands

### Delete Generated Files
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
rm -rf target/
```

### Delete Database (Reset)
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
rm -f cafe_management.db
```

### Full Clean
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn clean
rm -f cafe_management.db
```

## 📦 Deployment Commands

### Copy JAR for Distribution
```bash
cp /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new/target/cafe-management-system.jar ~/Desktop/
```

### Run from Different Location
```bash
cd ~/Desktop
java -jar cafe-management-system.jar
```

## 🔧 Maven Build Commands

### Skip Tests
```bash
mvn package -DskipTests
```

### Run Tests Only
```bash
mvn test
```

### Generate Javadoc
```bash
mvn javadoc:javadoc
```

### Check Compilation
```bash
mvn compile
```

### Full Lifecycle
```bash
mvn clean validate compile test package verify install
```

## 📊 Project Statistics

### Total Lines of Code
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
find src -name "*.java" -exec cat {} + | wc -l
```

### Class Breakdown
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
echo "Models:" && ls src/main/java/com/cafe/models/ | wc -l
echo "Database:" && ls src/main/java/com/cafe/database/ | wc -l
echo "Management:" && ls src/main/java/com/cafe/management/ | wc -l
echo "UI:" && ls src/main/java/com/cafe/ui/ | wc -l
echo "Utils:" && ls src/main/java/com/cafe/util/ | wc -l
```

## 🎯 Verification Commands

### Verify Build Success
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn clean compile 2>&1 | grep "BUILD SUCCESS"
```

### Verify JAR Exists
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
test -f target/cafe-management-system.jar && echo "JAR file exists" || echo "JAR file not found"
```

### Check Java Version
```bash
java -version
```

### Verify Maven Installation
```bash
mvn -version
```

## 💡 Advanced Commands

### View Dependency Tree
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn dependency:tree
```

### Update Project Information
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn help:describe -Dcmd=compile
```

### Generate Project Report
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn project-info-reports:dependencies
```

## 🔄 Common Workflows

### Development Workflow
```bash
# 1. Navigate to project
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new

# 2. Make changes to Java files

# 3. Compile and run
mvn clean compile
java -jar target/cafe-management-system.jar
```

### Testing Workflow
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn clean test
```

### Production Deployment
```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
mvn clean package -DskipTests
cp target/cafe-management-system.jar /var/apps/cafe/
cd /var/apps/cafe
java -jar cafe-management-system.jar
```

---

**Last Updated**: October 2025  
**Version**: 1.0.0  
**Project**: Cafe Management System
