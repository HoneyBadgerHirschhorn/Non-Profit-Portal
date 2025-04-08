package org.example.aletheiacares;

//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestBody;



    @Service
    public class UserService {

        private final UserRepository userRepository;

        // Constructor-based injection
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        // Method to save a User entity
        @Transactional
        public User saveUser(User user) {
            return userRepository.save(user);
        }
    }

