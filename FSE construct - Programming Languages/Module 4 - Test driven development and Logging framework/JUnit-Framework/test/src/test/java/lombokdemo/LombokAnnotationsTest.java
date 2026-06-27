package lombokdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lombok Annotations Tests")
class LombokAnnotationsTest {

    @Test
    void testDataAnnotation() {
        Student student = new Student(1, "Alice", "CS", 3.8);
        assertEquals("Alice", student.getName());
        student.setName("Bob");
        assertEquals("Bob", student.getName());
    }

    @Test
    void testNoArgsConstructor() {
        Student student = new Student();
        assertNotNull(student);
        assertNull(student.getName());
    }

    @Test
    void testAllArgsConstructor() {
        Student student = new Student(1, "Charlie", "Math", 3.5);
        assertEquals(1, student.getId());
        assertEquals("Math", student.getCourse());
    }

    @Test
    void testEqualsAndHashCode() {
        Student s1 = new Student(1, "Alice", "CS", 3.8);
        Student s2 = new Student(1, "Alice", "CS", 3.8);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void testToString() {
        Student student = new Student(1, "Alice", "CS", 3.8);
        String toString = student.toString();
        assertTrue(toString.contains("Alice"));
        assertTrue(toString.contains("CS"));
    }

    @Test
    void testBuilder() {
        Order order = Order.builder()
                .orderId("ORD-001")
                .customerName("Alice")
                .totalAmount(99.99)
                .items(Arrays.asList("Laptop", "Mouse"))
                .build();
        assertEquals("ORD-001", order.getOrderId());
        assertEquals("PENDING", order.getStatus());
        assertNotNull(order.getOrderDate());
    }

    @Test
    void testBuilderDefault() {
        Order order = Order.builder()
                .orderId("ORD-002")
                .customerName("Bob")
                .totalAmount(50.0)
                .build();
        assertEquals("PENDING", order.getStatus());
        assertNotNull(order.getItems());
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void testValueAnnotation() {
        ImmutableConfig config = new ImmutableConfig("localhost", 5432, "testdb", true);
        assertEquals("localhost", config.getHost());
        assertEquals(5432, config.getPort());
        assertTrue(config.isSslEnabled());
    }

    @Test
    void testGetterSetter() {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("Alice");
        emp.setDepartment("Engineering");
        emp.setSalary(75000);
        assertEquals("Alice", emp.getName());
        assertEquals(75000, emp.getSalary());
    }

    @Test
    void testAddressBuilder() {
        Address address = Address.builder()
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .zipCode("62704")
                .country("USA")
                .build();
        assertEquals("Springfield", address.getCity());
        assertTrue(address.toString().contains("Springfield"));
    }

    @Test
    void testProductBuilderAndData() {
        Product product = Product.builder()
                .productId("P-001")
                .name("Laptop")
                .price(999.99)
                .category("Electronics")
                .quantity(10)
                .build();
        assertEquals("Laptop", product.getName());
        product.setQuantity(5);
        assertEquals(5, product.getQuantity());
    }
}
