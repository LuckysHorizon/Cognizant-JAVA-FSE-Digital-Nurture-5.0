package miniprojects.orders;

public class OrderItem {
    private String itemName;
    private double price;
    private int quantity;
    private String status;

    public OrderItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.status = "PENDING";
    }

    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() {
        return price * quantity;
    }
}
