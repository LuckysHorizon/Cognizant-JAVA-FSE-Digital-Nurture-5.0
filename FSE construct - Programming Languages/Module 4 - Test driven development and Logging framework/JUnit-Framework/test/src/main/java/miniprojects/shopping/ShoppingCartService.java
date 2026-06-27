package miniprojects.shopping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);
    private final List<CartItem> items = new ArrayList<>();
    private static final double TAX_RATE = 0.1;

    public void addItem(CartItem item) {
        logger.info("Adding item: {} x{}", item.getProductName(), item.getQuantity());
        items.add(item);
    }

    public boolean removeItem(String productName) {
        return items.removeIf(item -> item.getProductName().equals(productName));
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public int getItemCount() {
        return items.size();
    }

    public void clearCart() {
        logger.info("Clearing cart");
        items.clear();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
}
