package mockito;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String getProduct(String name) {
        String product = productRepository.findByName(name);
        if (product == null) {
            throw new RuntimeException("Product not found: " + name);
        }
        return product;
    }

    public List<String> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public boolean addProduct(String product) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        return productRepository.save(product);
    }

    public long getTotalProducts() {
        return productRepository.count();
    }
}
