package springtesting.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import springtesting.SpringTestingApplication;
import springtesting.dto.ProductDTO;
import springtesting.entity.Product;
import springtesting.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringTestingApplication.class)
@ActiveProfiles("test")
@DisplayName("Product Integration Tests")
class ProductIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    void testCreateAndRetrieveProduct() {
        ProductDTO dto = new ProductDTO(null, "Integration Laptop", 1299.99, "Electronics");
        Product created = productService.createProduct(dto);
        assertNotNull(created.getId());

        Product retrieved = productService.getProductById(created.getId());
        assertEquals("Integration Laptop", retrieved.getName());
    }

    @Test
    void testGetProductsByCategory() {
        productService.createProduct(new ProductDTO(null, "Int Phone", 799.99, "IntElectronics"));
        productService.createProduct(new ProductDTO(null, "Int Tablet", 499.99, "IntElectronics"));
        List<Product> products = productService.getProductsByCategory("IntElectronics");
        assertTrue(products.size() >= 2);
    }

    @Test
    void testDeleteProduct() {
        ProductDTO dto = new ProductDTO(null, "ToDelete", 10.0, "Test");
        Product created = productService.createProduct(dto);
        assertDoesNotThrow(() -> productService.deleteProduct(created.getId()));
        assertThrows(RuntimeException.class, () -> productService.getProductById(created.getId()));
    }
}
