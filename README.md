# Food Order App
**Overview**
The Food Order App is an Android application designed for ordering food items. Users can browse a list of food options, view details, adjust quantities, and place or manage their orders.

### Features
-View Food Menu: Displays a list of food items with their names, images, prices, and descriptions.
-Food Details: Shows detailed information about the selected food item.
-Order Management: Allows users to add, update, and delete orders.
-SQLite Database Integration: Stores order details persistently.

### Technologies Used
-Programming Language: Java
-Database: SQLite
-Framework: Android SDK
-UI Components: RecyclerView, CardView, LinearLayout

### How It Works
1. **Main Menu**
   -Displays a list of food items fetched from a static list in MainActivity.java.

2. **Food Details**
   -Allows users to view detailed information about a food item, adjust quantities, and place an order. (DetailActivity.java).

3.**Orders**
   -Displays all orders in a RecyclerView (OrderActivity.java).
   -Supports updating or deleting orders.
