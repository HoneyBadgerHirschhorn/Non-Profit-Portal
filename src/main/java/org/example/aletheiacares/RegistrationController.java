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

    public RegistrationController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> handleFormSubmission(@RequestBody RegistrationRequest request) {

        logger.info("🔴 [DEBUG] Entered handleFormSubmission method");
        if (request == null) {
            logger.error("⚠️ ERROR: request object is NULL!");
            return ResponseEntity.badRequest().build();
        }
        logger.info("📥 Received Registration Request: {}", request);
        logger.info(request.toString());

        // Map RegistrationRequest to UserDto (NOT to User entity)
        UserDto userDto = userMapper.registrationRequestToDto(request);

        // Pass UserDto to the service
        UserDto savedUser = userService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}

