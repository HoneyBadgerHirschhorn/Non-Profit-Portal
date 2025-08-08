package org.example.aletheiacares;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegisterNewUser() throws Exception {
        RegistrationRequest request = new RegistrationRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        request.setGender("Male");
        request.setAddress("123 Main St");
        request.setMaritalStatus("Single");
        request.setHasKids(false);
        request.setKidInfo("");
        request.setMember(true);
        request.setContactPhone("123-456-7890");
        request.setContactEmail("john.doe@example.com");
        request.setAttendingSince(LocalDate.now());

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.contactEmail").value("john.doe@example.com"));

        // Verify the user is in the database
        Optional<User> userOptional = userRepository.findByContactEmail("john.doe@example.com");
        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }
}
