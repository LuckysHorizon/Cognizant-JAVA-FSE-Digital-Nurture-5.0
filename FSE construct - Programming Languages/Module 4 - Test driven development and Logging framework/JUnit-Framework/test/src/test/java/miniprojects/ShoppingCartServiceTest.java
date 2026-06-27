package miniprojects;

import miniprojects.shopping.CartItem;
import miniprojects.shopping.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Shopping Cart Service Tests")
class ShoppingCartServiceTest {

    private ShoppingCartService cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCartService();
        cart.addItem(new CartItem("Laptop", 999.99, 1));
        cart.addItem(new CartItem("Mouse", 29.99, 2));
    }

    @Test
    void testAddItem() {
        assertEquals(2, cart.getItemCount());
    }

    @Test
    void testSubtotal() {
        assertEquals(1059.97, cart.getSubtotal(), 0.01);
    }

    @Test
    void testTax() {
        assertEquals(105.997, cart.getTax(), 0.01);
    }

    @Test
    void testTotal() {
        double expected = cart.getSubtotal() + cart.getTax();
        assertEquals(expected, cart.getTotal(), 0.01);
    }

    @Test
    void testRemoveItem() {
        assertTrue(cart.removeItem("Laptop"));
        assertEquals(1, cart.getItemCount());
    }

    @Test
    void testClearCart() {
        cart.clearCart();
        assertEquals(0, cart.getItemCount());
    }
}
