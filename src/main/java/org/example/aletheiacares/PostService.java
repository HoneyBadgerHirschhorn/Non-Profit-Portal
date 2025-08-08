package org.example.aletheiacares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository,
                       CategoryRepository categoryRepository,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.postMapper = postMapper;
    }

    /**
     * Saves a Post entity, preserving existing user linkage if names match.
     */
    public Post savePost(Post post) {
        List<Post> matches = postRepository.findByFirstNameAndLastName(
                post.getFirstName(), post.getLastName());

        if (!matches.isEmpty()) {
            // Preserve user from existing post
            post.setUser(matches.get(0).getUser());
        }

        return postRepository.save(post);
    }

    /**
     * Retrieves all posts as DTOs.
     */
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves posts filtered by category name.
     */
    @Transactional(readOnly = true)
    public List<PostDto> getPostsByCategory(String name) {
        return postRepository.findByCategory_Name(name)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new post based on DTO, assigning category and returning DTO.
     */
    @Transactional
    public PostDto createPost(PostDto dto) {
        Category category = categoryRepository
                .findByName(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category not found: " + dto.getCategory()));

        Post post = postMapper.toEntity(dto);
        post.setCategory(category);
        Post saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    public List<Post> findByCategory(String categoryName) {
        return postRepository.findByCategory_Name(categoryName);
    }
}





//package org.example.aletheiacares;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PostService {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    public Post savePost(Post post) {
//        List<Post> matches = postRepository.findByFirstNameAndLastName(post.getFirstName(), post.getLastName());
//
//        if (!matches.isEmpty()) {
//            // A Post exists with the same first and last name
//            post.setUser(matches.getFirst().getUser());
//        }
//
//        return postRepository.save(post);
//    }
//}
