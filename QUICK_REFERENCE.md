# Quick Reference - Order Creation Feature

## 🚀 Quick Start

```bash
cd /media/mathewsvinoy/820831ee-706c-42e4-8979-f6876adb34d6/dev/java/new
java -jar target/cafe-management-system.jar
```

**Login:** admin / admin

## 📋 What's New

The "Create Order" dialog now has a **professional two-panel layout**:

### Left Panel - Menu Browser
- Shows all menu items from database
- Filter by category
- Edit quantity for each item
- Displays: ID, Name, Category, Price, Available Qty

### Right Panel - Order Cart
- Items you've added
- Real-time total calculation
- Shows: Item Name, Price, Qty, Subtotal
- Grand total highlighted at top

## 🎯 Step-by-Step Usage

### Creating an Order

```
1. Click "Create Order" button
   ↓
2. Browse menu items on left
   - Use category dropdown to filter
   - Select any item
   ↓
3. Enter quantity in "Qty" column (rightmost)
   ↓
4. Click "Add to Cart"
   - Item appears on right panel
   - Grand total updates
   ↓
5. Repeat steps 2-4 for more items
   ↓
6. Click "Place Order"
   - Order saved to database
   - Confirmation shows Order ID & Total
   ↓
7. Order appears in orders list with "Pending" status
```

## 🔍 Key Features

| Feature | Details |
|---------|---------|
| **Database Integration** | Items loaded from menu_items table |
| **Category Filter** | Quickly find items by type |
| **Stock Validation** | Can't order more than available |
| **Real-Time Math** | Totals calculated as you add items |
| **Item Management** | Add, remove, or modify items before placing |
| **Order Confirmation** | Shows Order ID and total amount |
| **Status Tracking** | Orders auto-set to "Pending" |

## 📊 UI Components

```
Dialog Size: 900 × 600 pixels

Left Section (Menu Items):
  - Category Filter Dropdown
  - Items Table (ID, Name, Category, Price, Available, Qty)
  - Editable Qty column
  
Right Section (Order Details):
  - Order Items Table (Item, Price, Qty, Total)
  - Summary Box (Grand Total in large green text)
  - Buttons: Add to Cart, Remove Item, Place Order, Cancel
```

## ✅ Validation Features

- ❌ **Cannot add without selection** → Shows error message
- ❌ **Cannot add with zero quantity** → Shows error message
- ❌ **Cannot exceed available stock** → Shows "Only X items available"
- ✅ **Automatic quantity reset** after adding to cart
- ✅ **Real-time grand total update**
- ✅ **Error handling** for invalid inputs

## 💾 Database Operations

When you place an order:

1. **Order record created** in `orders` table
   - Order ID (auto-generated)
   - Total Amount
   - Status: "Pending"
   - Timestamp

2. **Order items stored** in `order_items` table
   - Each item with quantity
   - Subtotal for each item
   - Link to order

3. **Data persistence** - All information saved permanently

## 🎨 Visual Mockup

```
┌─────────────────────────────────────────────────────┐
│  Create Order                           [X]          │
├─────────────────────┬───────────────────────────────┤
│ AVAILABLE ITEMS     │ ORDER DETAILS                 │
│                     │                               │
│ Category: [All ▼]   │ Items in Cart                 │
│                     │ ─────────────────────────     │
│ ID  Name   Price    │ Tea      ₹50   2    ₹100     │
│ 1   Tea    ₹50      │ Coffee   ₹80   1    ₹80      │
│ 2   Coffee ₹80      │                               │
│ 3   Milk   ₹40      │ Grand Total: ₹180.00         │
│ 4   Water  ₹20      │                               │
│                     │ [Add]  [Remove]              │
│ Qty: [2]  [Add]     │ [Place Order]  [Cancel]      │
│                     │                               │
└─────────────────────┴───────────────────────────────┘
```

## 🔧 Technical Details

**Modified File:**
- `OrderPanel.java` (380+ lines of enhanced code)

**New Methods:**
- `createMenuItemsPanel()` - Left panel with items
- `createOrderDetailsPanel(dialog)` - Right panel with summary
- `populateMenuTable()` - Load items from DB
- `addItemToOrder()` - Add selected item
- `removeItemFromOrder()` - Remove item from cart
- `updateGrandTotal()` - Calculate totals
- `placeOrder()` - Save order to database

**Updated Model:**
- `OrderItem.java` - Added menuItemName field

## 📚 Full Documentation

For detailed information, see: **ORDER_CREATION_GUIDE.md**

## ❓ FAQ

**Q: How do I filter items by category?**
A: Use the "Category" dropdown in the top-left of the left panel.

**Q: Can I modify quantity after adding to cart?**
A: Remove the item and add again with correct quantity.

**Q: What happens to the order after I place it?**
A: It appears in the orders list with "Pending" status.

**Q: Can I order more than available stock?**
A: No - system shows error "Only X items available"

**Q: Do prices update automatically?**
A: Yes - they reflect current database values.

## ✨ Sample Scenarios

### Scenario 1: Simple Order
```
1. Select Tea (quantity: 2) → Add to Cart
2. Grand Total: ₹100
3. Place Order
4. Order #1 Created
```

### Scenario 2: Multiple Items
```
1. Select Tea (qty: 2) → Add to Cart → ₹100
2. Select Coffee (qty: 1) → Add to Cart → ₹80
3. Select Milk (qty: 1) → Add to Cart → ₹40
4. Grand Total: ₹220
5. Place Order
6. Order #2 Created
```

### Scenario 3: Remove & Adjust
```
1. Add Tea, Coffee, Milk to cart
2. Realize no Milk needed
3. Select Milk row → Click Remove Item
4. Grand Total updated
5. Place Order
```

## 🎯 Pro Tips

1. **Filter by category first** to reduce visible items
2. **Double-check quantities** before placing order
3. **Review grand total** before confirmation
4. **Check "Available Qty"** to avoid errors
5. **Use "Cancel"** to abort without saving

## 📞 Support

For issues or questions:
1. Check ORDER_CREATION_GUIDE.md for details
2. Verify menu items exist in database
3. Ensure quantities are positive numbers
4. Check available stock for each item

---

**Version:** 2.1.0
**Last Updated:** October 27, 2025
**Status:** ✅ Production Ready
