package mockito;

import java.util.List;

public interface ProductRepository {
    String findByName(String name);
    List<String> findByCategory(String category);
    boolean save(String product);
    long count();
}
