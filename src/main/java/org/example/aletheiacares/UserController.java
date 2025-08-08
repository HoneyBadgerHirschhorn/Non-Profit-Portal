package org.example.aletheiacares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto saved = userService.createUser(userDto);
        return ResponseEntity.ok(saved);
    }

    /**
     * Fetch a user by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        return userService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Optional: fetch all users as DTOs
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/byName")
    public ResponseEntity<?> getUsersByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok(userService.findUserByFirstNameAndLastName(firstName, lastName));
    }
}
