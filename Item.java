class Item implements Comparable<Item> {
    private String id;
    private String name;
    private String category;
    private int quantity;

    public Item(String id, String name, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public int compareTo(Item other) {
        return Integer.compare(other.quantity, this.quantity);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Category: %s, Quantity: %d", id, name, category, quantity);
    }
}
