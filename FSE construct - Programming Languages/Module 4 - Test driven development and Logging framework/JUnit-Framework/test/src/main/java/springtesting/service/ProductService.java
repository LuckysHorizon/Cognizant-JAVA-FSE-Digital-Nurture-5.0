package springtesting.service;

import org.springframework.stereotype.Service;
import springtesting.dto.ProductDTO;
import springtesting.entity.Product;
import springtesting.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getCategory());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO dto) {
        Product product = getProductById(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
