package springtesting.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springtesting.dto.ProductDTO;
import springtesting.entity.Product;
import springtesting.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product Service Tests")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, "Electronics"),
                new Product("Phone", 699.99, "Electronics")
        );
        when(productRepository.findAll()).thenReturn(products);
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void testGetProductById() {
        Product product = new Product("Laptop", 999.99, "Electronics");
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertEquals("Laptop", productService.getProductById(1L).getName());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productService.getProductById(99L));
    }

    @Test
    void testCreateProduct() {
        ProductDTO dto = new ProductDTO(null, "Tablet", 499.99, "Electronics");
        Product saved = new Product("Tablet", 499.99, "Electronics");
        saved.setId(1L);
        when(productRepository.save(any(Product.class))).thenReturn(saved);
        Product result = productService.createProduct(dto);
        assertEquals("Tablet", result.getName());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        Product existing = new Product("Old Name", 100.0, "Old");
        existing.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenReturn(existing);
        ProductDTO dto = new ProductDTO(1L, "New Name", 200.0, "New");
        Product result = productService.updateProduct(1L, dto);
        assertEquals("New Name", result.getName());
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);
        assertDoesNotThrow(() -> productService.deleteProduct(1L));
        verify(productRepository).deleteById(1L);
    }

    @Test
    void testDeleteProductNotFound() {
        when(productRepository.existsById(99L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> productService.deleteProduct(99L));
    }
}
