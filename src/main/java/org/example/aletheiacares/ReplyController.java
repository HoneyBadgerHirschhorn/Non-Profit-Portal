package org.example.aletheiacares;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * ReplyController handles:
 *   - POST /api/replies        → create a new Reply
 *   - GET  /api/replies/post/{postId} → list all replies for a given post
 *
 * When creating a new Reply, we must:
 *   1) Map the incoming DTO → a bare Reply entity (post & parentReply are still null)
 *   2) Look up the real Post (and parent Reply if needed) from the DB
 *   3) Set those into the new Reply object
 *   4) Set createdAt (and user if applicable)
 *   5) Save the Reply via the service/repository
 *   6) Return the saved Reply as a DTO (including postId, parentReplyId, createdAt, etc.)
 */
@RequestMapping("/api/replies")
@CrossOrigin(origins = "*")
@RestController
public class ReplyController {

    private final ReplyService    replyService;
    private final ReplyMapper     replyMapper;
    private final ReplyRepository replyRepository;
    private final PostRepository  postRepository;

    @Autowired
    public ReplyController(
            ReplyService    replyService,
            ReplyMapper     replyMapper,
            ReplyRepository replyRepository,
            PostRepository  postRepository
    ) {
        this.replyService    = replyService;
        this.replyMapper     = replyMapper;
        this.replyRepository = replyRepository;
        this.postRepository  = postRepository;
    }

    /**
     * CREATE a new Reply.
     *
     * Request body: ReplyDto (must include postId, firstName, lastName, content;
     * optionally parentReplyId).
     *
     * Steps:
     *   1. Convert DTO → Reply entity (post & parentReply are still null)
     *   2. Load the managed Post from DB and assign it
     *   3. If parentReplyId was provided, load that Reply and assign it
     *   4. Set createdAt
     *   5. Save via replyService (which also attaches a User if needed)
     *   6. Convert saved Reply → ReplyDto and return with HTTP 201
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReplyDto> createReply(@Valid @RequestBody ReplyDto dto) {
        // 1) Map DTO → Reply (but post and parentReply are still null)
        Reply entity = replyMapper.toEntity(dto);

        // 2) Look up the real Post from the database
        Post managedPost = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid postId: " + dto.getPostId()
                ));
        entity.setPost(managedPost);

        // 3) If a parentReplyId was provided, look up that Reply
        if (dto.getParentReplyId() != null) {
            Reply managedParent = replyRepository.findById(dto.getParentReplyId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Invalid parentReplyId: " + dto.getParentReplyId()
                    ));
            entity.setParentReply(managedParent);
        }

        // 4) Set server‐side fields
        entity.setCreatedAt(LocalDateTime.now());
        // (Optionally) set entity.setUser(currentUser) if you have authentication

        // 5) Save the reply (now that post and parentReply are managed)
        Reply saved = replyService.saveReply(entity);

        // 6) Convert saved entity → DTO for response
        ReplyDto responseDto = replyMapper.toDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * GET all replies for a given Post.
     * URL: /api/replies/post/{postId}
     * Returns: List<ReplyDto>
     */
    @GetMapping(
            value = "/post/{postId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ReplyDto> getRepliesByPost(@PathVariable("postId") Integer postId) {
        return replyRepository
                .findByPost_PostId(postId)
                .stream()
                .map(replyMapper::toDto)
                .collect(Collectors.toList());
    }
}
