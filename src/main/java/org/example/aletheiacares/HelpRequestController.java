package org.example.aletheiacares;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class HelpRequestController {


    private static final Logger logger = LoggerFactory.getLogger(HelpRequestController.class);
    private final HelpRequestService helpRequestService;
    private final HelpRequestMapper helpRequestMapper;

    public HelpRequestController(HelpRequestService helpRequestService, HelpRequestMapper mapper) {
        this.helpRequestService = helpRequestService;
        this.helpRequestMapper = mapper;

    }

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    public List<HelpRequestInterface> getHelpRequestSummaries() {
        return helpRequestRepository.findAllProjectedBy();
    }



    @PostMapping("/helpRequest")
    public ResponseEntity<HelpRequest> createHelpRequest(@RequestBody HelpRequest helpRequest) {

        logger.info("🔴 [DEBUG] Entered createHelpRequest method");
        if (helpRequest == null) {
            logger.error("⚠️ ERROR: request object is NULL!");
            return ResponseEntity.badRequest().build();
        }
        logger.info("📥 Received Help Request: {}", helpRequest);
        logger.info(helpRequest.toString());

        HelpRequest helpRequest1 = helpRequestMapper.helpRequest(helpRequest);
        HelpRequest savedHelpRequest = helpRequestService.saveHelpRequest(helpRequest1);

        return new ResponseEntity<>(savedHelpRequest, HttpStatus.CREATED);
    }
}




