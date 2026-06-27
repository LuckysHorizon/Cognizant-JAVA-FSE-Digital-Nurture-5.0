package miniprojects.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingService.class);
    private final List<OrderItem> orders = new ArrayList<>();

    public void placeOrder(OrderItem item) {
        logger.info("Placing order: {} x{}", item.getItemName(), item.getQuantity());
        if (item.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        orders.add(item);
    }

    public boolean processOrder(int index) {
        if (index < 0 || index >= orders.size()) {
            throw new IndexOutOfBoundsException("Invalid order index");
        }
        OrderItem item = orders.get(index);
        item.setStatus("PROCESSED");
        logger.info("Order processed: {}", item.getItemName());
        return true;
    }

    public boolean cancelOrder(int index) {
        if (index < 0 || index >= orders.size()) {
            throw new IndexOutOfBoundsException("Invalid order index");
        }
        OrderItem item = orders.get(index);
        if ("PROCESSED".equals(item.getStatus())) {
            logger.warn("Cannot cancel processed order: {}", item.getItemName());
            return false;
        }
        item.setStatus("CANCELLED");
        return true;
    }

    public double getTotalRevenue() {
        return orders.stream()
                .filter(o -> "PROCESSED".equals(o.getStatus()))
                .mapToDouble(OrderItem::getTotal)
                .sum();
    }

    public List<OrderItem> getOrdersByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public int getOrderCount() {
        return orders.size();
    }
}
