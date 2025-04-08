
package org.example.aletheiacares;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    // Constructor Injection for UserRepository
    public RegistrationController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<User> handleFormSubmission(@RequestBody RegistrationRequest request) {

        logger.info("🔴 [DEBUG] Entered handleFormSubmission method");
        if (request == null) {
            logger.error("⚠️ ERROR: request object is NULL!");
            return ResponseEntity.badRequest().build();
        }
        logger.info("📥 Received Registration Request: {}", request);
        logger.info(request.toString());

        User user = userMapper.mapRequest(request);
        User savedUser = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}

