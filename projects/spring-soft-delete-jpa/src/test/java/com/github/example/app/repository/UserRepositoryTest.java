package com.github.example.app.repository;

import com.github.example.app.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSoftDelete() {
        // Create and save a new user
        User user = new User();
        user.setName("John Doe");
        userRepository.save(user);

        // Ensure the user is saved
        assertNotNull(userRepository.findById(user.getId()).orElse(null));

        // Soft delete the user
        userRepository.delete(user);

        // Check if the user is no longer retrievable
        assertTrue(userRepository.findById(user.getId()).isEmpty());
    }
}