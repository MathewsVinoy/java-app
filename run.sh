#!/bin/bash
# Cafe Management System - Quick Run Script

PROJECT_DIR="/media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new"
JAR_FILE="$PROJECT_DIR/target/cafe-management-system.jar"

echo "=================================="
echo "Cafe Management System Launcher"
echo "=================================="
echo ""

# Check if JAR exists
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ JAR file not found at: $JAR_FILE"
    echo ""
    echo "Building project..."
    cd "$PROJECT_DIR"
    mvn clean package -DskipTests
    if [ $? -ne 0 ]; then
        echo "❌ Build failed!"
        exit 1
    fi
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -1)
echo "✅ Using: $JAVA_VERSION"
echo ""

# Run the application
echo "🚀 Starting Cafe Management System..."
echo ""

cd "$PROJECT_DIR"
java -jar "$JAR_FILE"

echo ""
echo "👋 Application closed."
