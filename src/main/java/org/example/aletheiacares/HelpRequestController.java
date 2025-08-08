package org.example.aletheiacares;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/help-requests")
@CrossOrigin(origins = "*")
public class HelpRequestController {

    private static final Logger logger = LoggerFactory.getLogger(HelpRequestController.class);

    private final HelpRequestService helpRequestService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public HelpRequestController(
            HelpRequestService helpRequestService,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.helpRequestService = helpRequestService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    private HelpRequestMapper helpRequestMapper;

    @GetMapping("/category/{name}")
    public List<HelpRequestDto> getByCategoryName(@PathVariable String name) {

        List<HelpRequest> requests = helpRequestService.getByCategoryName(name);
        System.out.println("REQUEST COUNT: " + requests.size());
        for (HelpRequest hr : requests) {
            System.out.println("DEBUG ENTITY: title=" + hr.getTitle() + ", firstName=" + hr.getFirstName() + ", lastName=" + hr.getLastName());
        }


return helpRequestService.getByCategoryName(name).stream()
        .map(hr -> {
            HelpRequestDto dto = new HelpRequestDto();
            dto.setId(hr.getId()); // Add this line to set the ID
            dto.setTitle(hr.getTitle());
            dto.setFirstName(hr.getFirstName());
            dto.setLastName(hr.getLastName());
            dto.setUserId(hr.getUser() != null ? hr.getUser().getId() : null);
            dto.setCategory(hr.getCategory() != null ? hr.getCategory().getName() : null);
            dto.setContent(hr.getContent());
            return dto;
        })
        .toList();

    }

    @PostMapping
    public ResponseEntity<?> createHelpRequest(@Valid @RequestBody HelpRequestDto dto) {
        logger.info("📥 Received Help Request DTO: {}", dto);

        // 1) Lookup the user by ID
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID " + dto.getUserId()));

        // 2) Lookup the category by name
        Category category = categoryRepository.findByName(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with name " + dto.getCategory()));

        // 3) Construct a new HelpRequest (defaulting status to "OPEN")
        HelpRequest helpRequest = new HelpRequest(
                "OPEN",
                dto.getTitle(),
                dto.getFirstName(),
                dto.getLastName(),
                user,
                category,
                dto.getContent()
        );

        System.out.println("FirstName: " + dto.getFirstName());
        System.out.println("LastName: " + dto.getLastName());
        System.out.println("UserId: " + dto.getUserId());
        System.out.println("Category: " + dto.getCategory());
        System.out.println("Content: " + dto.getContent());


        // 4) Save and return
        HelpRequest saved = helpRequestService.saveHelpRequest(helpRequest);

        // Use the mapper to convert to DTO for the response
        HelpRequestDto responseDto = helpRequestMapper.toDto(saved);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }
}
