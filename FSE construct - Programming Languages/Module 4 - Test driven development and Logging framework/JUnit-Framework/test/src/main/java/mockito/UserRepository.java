package mockito;

import java.util.List;

public interface UserRepository {
    String findById(int id);
    List<String> findAll();
    boolean save(String user);
    boolean deleteById(int id);
    boolean existsById(int id);
}
