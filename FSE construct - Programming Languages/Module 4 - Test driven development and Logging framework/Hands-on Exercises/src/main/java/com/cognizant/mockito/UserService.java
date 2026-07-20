package com.cognizant.mockito;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(String userId) {
        User user = userRepository.findById(userId);
        if (user != null) {
            return user.getName();
        }
        return "Unknown User";
    }

    public void registerNewUser(String id, String name) {
        User newUser = new User(id, name);
        userRepository.save(newUser);
    }
}
