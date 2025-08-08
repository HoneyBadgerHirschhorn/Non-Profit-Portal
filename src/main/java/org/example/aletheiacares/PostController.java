package org.example.aletheiacares;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
@RestController
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Autowired
    public PostController(
            PostService postService,
            PostMapper postMapper,
            PostRepository postRepository
    ) {
        this.postService    = postService;
        this.postMapper     = postMapper;
        this.postRepository = postRepository;
    }

    /**
     * 1) CREATE a new Post
     *    – consumes JSON (PostDto), produces JSON (PostDto)
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto) {
        logger.info("Received PostDto: {}", dto);

        // 1. Map DTO → Entity
        Post entity = postMapper.toEntity(dto);

        // 2. Persist via service (which itself may set User if duplicate author, etc.)
        Post saved = postService.savePost(entity);

        // 3. Map Entity → DTO to return
        PostDto responseDto = postMapper.toDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 2) GET all Posts
     *    – produces JSON (list of PostDto)
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 3) GET a single Post by its ID
     *    – produces JSON (PostDto)
     */
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id) {
        Optional<Post> opt = postRepository.findById(id);
        return opt
                .map(post -> ResponseEntity.ok(postMapper.toDto(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 4) UPDATE an existing Post
     *    – PUT /api/posts/{id}, consumes JSON (PostDto), produces JSON (PostDto)
     */
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostDto> updatePost(
            @PathVariable("id") Integer id,
            @Valid @RequestBody PostDto updatedDto
    ) {
        return postRepository.findById(id)
                .map(existing -> {
                    // Copy only the allowed fields from DTO to entity
                    existing.setFirstName(updatedDto.getFirstName());
                    existing.setLastName(updatedDto.getLastName());
                    existing.setTitle(updatedDto.getTitle());
                    existing.setContent(updatedDto.getContent());

                    Post saved = postRepository.save(existing);
                    return ResponseEntity.ok(postMapper.toDto(saved));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 5) DELETE a Post by ID
     *    – DELETE /api/posts/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Integer id) {
        return postRepository.findById(id)
                .map(existing -> {
                    postRepository.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 6) SEARCH posts by author name (first + last)
     *    – GET /api/posts/search?firstName=Alice&lastName=Smith
     *    – produces JSON (list of PostDto)
     */
    @GetMapping(
            value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PostDto> getPostsByAuthor(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName")  String lastName
    ) {
        return postRepository
                .findByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/category/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto> getPostsByCategory(@PathVariable String categoryName) {
        return postService.findByCategory(categoryName)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * GET posts by help request ID
     * – GET /api/posts/help-request/{helpRequestId}
     */
    @GetMapping(value = "/help-request/{helpRequestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto> getPostsByHelpRequest(@PathVariable Integer helpRequestId) {
        return postRepository.findByHelpRequest_Id(helpRequestId)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * CREATE a new Post linked to a help request
     * – POST /api/posts/help-request/{helpRequestId}
     */
    @PostMapping(value = "/help-request/{helpRequestId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> createPostForHelpRequest(@PathVariable Integer helpRequestId, @Valid @RequestBody PostDto dto) {
        // Map DTO to entity
        Post post = postMapper.toEntity(dto);

        // Set the help request reference
        HelpRequest helpRequest = new HelpRequest();
        helpRequest.setId(helpRequestId);
        post.setHelpRequest(helpRequest);

        // Save post
        Post saved = postService.savePost(post);

        // Return DTO
        PostDto responseDto = postMapper.toDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
