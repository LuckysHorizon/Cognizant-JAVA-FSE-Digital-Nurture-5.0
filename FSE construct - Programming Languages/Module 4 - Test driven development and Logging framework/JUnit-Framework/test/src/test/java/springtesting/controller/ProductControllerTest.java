package springtesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springtesting.dto.ProductDTO;
import springtesting.entity.Product;
import springtesting.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@DisplayName("Product Controller Tests")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, "Electronics"),
                new Product("Phone", 699.99, "Electronics")
        );
        when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product("Laptop", 999.99, "Electronics");
        product.setId(1L);
        when(productService.getProductById(1L)).thenReturn(product);
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO dto = new ProductDTO(null, "Tablet", 499.99, "Electronics");
        Product saved = new Product("Tablet", 499.99, "Electronics");
        saved.setId(1L);
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(saved);
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Tablet"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductDTO dto = new ProductDTO(1L, "Updated", 599.99, "Electronics");
        Product updated = new Product("Updated", 599.99, "Electronics");
        updated.setId(1L);
        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(updated);
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}
