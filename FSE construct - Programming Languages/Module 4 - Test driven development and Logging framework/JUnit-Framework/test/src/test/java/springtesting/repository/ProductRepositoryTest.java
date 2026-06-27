package springtesting.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import springtesting.entity.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Product Repository Tests")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        productRepository.save(new Product("Laptop", 999.99, "Electronics"));
        productRepository.save(new Product("Phone", 699.99, "Electronics"));
        productRepository.save(new Product("Shirt", 29.99, "Clothing"));
    }

    @Test
    void testFindAll() {
        List<Product> products = productRepository.findAll();
        assertEquals(3, products.size());
    }

    @Test
    void testFindByCategory() {
        List<Product> electronics = productRepository.findByCategory("Electronics");
        assertEquals(2, electronics.size());
    }

    @Test
    void testFindByPriceGreaterThan() {
        List<Product> expensive = productRepository.findByPriceGreaterThan(500.0);
        assertEquals(2, expensive.size());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product("Book", 19.99, "Books");
        Product saved = productRepository.save(product);
        assertNotNull(saved.getId());
        assertEquals("Book", saved.getName());
    }

    @Test
    void testDeleteProduct() {
        Product product = productRepository.findAll().get(0);
        productRepository.deleteById(product.getId());
        assertEquals(2, productRepository.findAll().size());
    }
}
