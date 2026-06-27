package mockito;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserById(int id) {
        String user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }

    public List<String> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean createUser(String user) {
        if (user == null || user.isBlank()) {
            throw new IllegalArgumentException("User name cannot be blank");
        }
        return userRepository.save(user);
    }

    public boolean deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return userRepository.deleteById(id);
    }
}
