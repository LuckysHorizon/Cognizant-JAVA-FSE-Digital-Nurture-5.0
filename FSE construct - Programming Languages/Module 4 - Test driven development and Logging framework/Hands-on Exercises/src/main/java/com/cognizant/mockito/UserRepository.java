package com.cognizant.mockito;

public interface UserRepository {
    User findById(String id);
    void save(User user);
}
