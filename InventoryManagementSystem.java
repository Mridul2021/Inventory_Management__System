import java.util.*;

class InventoryManagementSystem {
    private Map<String, Item> inventory;
    private Map<String, PriorityQueue<Item>> categoryMap;
    private int restockThreshold;

    public InventoryManagementSystem(int restockThreshold) {
        this.inventory = new HashMap<>();
        this.categoryMap = new TreeMap<>();
        this.restockThreshold = restockThreshold;
    }

    public void addItem(String id, String name, String category, int quantity) {
        Item item = new Item(id, name, category, quantity);

        if (inventory.containsKey(id)) {
            Item existingItem = inventory.get(id);
            existingItem.setQuantity(quantity);

            if (quantity < restockThreshold) {
                System.out.println("Restock Alert: " + existingItem);
            }

            updateCategory(existingItem);
        } else {
            inventory.put(id, item);
            categoryMap.putIfAbsent(category, new PriorityQueue<>());
            categoryMap.get(category).add(item);

            if (quantity < restockThreshold) {
                System.out.println("Restock Alert: " + item);
            }
        }
    }

    public void deleteItem(String id) {
        if (inventory.containsKey(id)) {
            Item item = inventory.remove(id);
            categoryMap.get(item.getCategory()).remove(item);
        } else {
            System.out.println("Item not found.");
        }
    }

    public List<Item> getItemsByCategory(String category) {
        List<Item> result = new ArrayList<>();
        if (categoryMap.containsKey(category)) {
            result.addAll(categoryMap.get(category));
        }
        return result;
    }

    public void mergeInventories(InventoryManagementSystem other) {
        for (Item item : other.inventory.values()) {
            if (this.inventory.containsKey(item.getId())) {
                Item existingItem = this.inventory.get(item.getId());
                if (item.getQuantity() > existingItem.getQuantity()) {
                    addItem(item.getId(), item.getName(), item.getCategory(), item.getQuantity());
                }
            } else {
                addItem(item.getId(), item.getName(), item.getCategory(), item.getQuantity());
            }
        }
    }

    public List<Item> getTopKItems(int k) {
        PriorityQueue<Item> maxHeap = new PriorityQueue<>(inventory.values());
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            result.add(maxHeap.poll());
        }
        return result;
    }

    private void updateCategory(Item item) {
        String category = item.getCategory();
        categoryMap.get(category).remove(item);
        categoryMap.get(category).add(item);
    }

    public void displayInventory() {
        inventory.values().forEach(System.out::println);
    }
}
