package org.example.aletheiacares;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HelpRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    private User testUser;
    private Category testCategory;

    @BeforeEach
    public void setup() {
        // Create and save a test user
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setContactEmail("jane.doe@example.com");
        user.setDateOfBirth(LocalDate.of(1992, 2, 2));
        user.setGender("Female");
        user.setAddress("456 Oak Ave");
        user.setMaritalStatus("Married");
        user.setHasKids(true);
        user.setKidInfo("One child");
        user.setMember(false);
        user.setContactPhone("987-654-3210");
        user.setAttendingSince(LocalDate.now());
        testUser = userRepository.save(user);

        // Create and save a test category
        Category category = new Category();
        category.setName("Transportation");
        testCategory = categoryRepository.save(category);
    }

    @Test
    public void testCreateHelpRequest() throws Exception {
        HelpRequestDto helpRequestDto = new HelpRequestDto(
                null, // Add null for id parameter
                "Need a ride to the doctor",
                testUser.getFirstName(),
                testUser.getLastName(),
                testUser.getId(),
                testCategory.getName(),
                "I have a doctor's appointment next week and need a ride."
        );

        mockMvc.perform(post("/api/help-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(helpRequestDto)))
                .andExpect(status().isCreated());

        // Verify the help request is in the database
        HelpRequest helpRequest = helpRequestRepository.findByTitle("Need a ride to the doctor");
        assertTrue(helpRequest != null);
        assertEquals("Need a ride to the doctor", helpRequest.getTitle());
        assertEquals(testUser.getId(), helpRequest.getUser().getId());
        assertEquals(testCategory.getName(), helpRequest.getCategory().getName());
    }
}
