public class InventoryManagementSystemTest {
    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem(10);

        // Add items
        inventory.addItem("1", "Laptop", "Electronics", 50);
        inventory.addItem("2", "Chair", "Furniture", 30);
        inventory.addItem("3", "Apples", "Groceries", 5);
        inventory.addItem("4", "Monitor", "Electronics", 20);
        inventory.addItem("5", "Oranges", "Groceries", 9);

        // Display all items
        System.out.println("All Inventory Items:");
        inventory.displayInventory();

        // Retrieve items by category
        System.out.println("\nItems in Electronics:");
        inventory.getItemsByCategory("Electronics").forEach(System.out::println);

        // Delete an item
        inventory.deleteItem("2");
        System.out.println("\nAfter deleting item with ID 2:");
        inventory.displayInventory();

        // Merge inventories
        InventoryManagementSystem otherInventory = new InventoryManagementSystem(10);
        otherInventory.addItem("6", "Table", "Furniture", 15);
        otherInventory.addItem("3", "Apples", "Groceries", 20); // Duplicate ID
        inventory.mergeInventories(otherInventory);

        System.out.println("\nAfter merging another inventory:");
        inventory.displayInventory();

        // Top K items
        System.out.println("\nTop 2 Items by Quantity:");
        inventory.getTopKItems(2).forEach(System.out::println);
    }
}
