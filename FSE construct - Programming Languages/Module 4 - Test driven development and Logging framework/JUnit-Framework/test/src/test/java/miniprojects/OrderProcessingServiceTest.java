package miniprojects;

import miniprojects.orders.OrderItem;
import miniprojects.orders.OrderProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Order Processing Service Tests")
class OrderProcessingServiceTest {

    private OrderProcessingService service;

    @BeforeEach
    void setUp() {
        service = new OrderProcessingService();
        service.placeOrder(new OrderItem("Laptop", 999.99, 1));
        service.placeOrder(new OrderItem("Phone", 699.99, 2));
    }

    @Test
    void testPlaceOrder() {
        assertEquals(2, service.getOrderCount());
    }

    @Test
    void testPlaceOrderInvalidPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> service.placeOrder(new OrderItem("Free", 0, 1)));
    }

    @Test
    void testProcessOrder() {
        assertTrue(service.processOrder(0));
        assertEquals("PROCESSED", service.getOrdersByStatus("PROCESSED").get(0).getStatus());
    }

    @Test
    void testCancelOrder() {
        assertTrue(service.cancelOrder(0));
    }

    @Test
    void testCancelProcessedOrder() {
        service.processOrder(0);
        assertFalse(service.cancelOrder(0));
    }

    @Test
    void testTotalRevenue() {
        service.processOrder(0);
        assertEquals(999.99, service.getTotalRevenue(), 0.01);
    }

    @Test
    void testInvalidOrderIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> service.processOrder(99));
    }
}
