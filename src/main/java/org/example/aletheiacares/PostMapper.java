package org.example.aletheiacares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class PostMapper {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final CategoryRepository categoryRepository;

    @Autowired
    public PostMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setFirstName(post.getFirstName());
        dto.setLastName(post.getLastName());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt() != null
                ? post.getCreatedAt().format(DATE_FORMATTER)
                : null);
        dto.setCategory(post.getCategory().getName());
        return dto;
    }

    public Post toEntity(PostDto dto) {
        Post post = new Post();
        post.setFirstName(dto.getFirstName());
        post.setLastName(dto.getLastName());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        Category category = categoryRepository
                .findByName(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category not found: " + dto.getCategory()));
        post.setCategory(category);
        return post;
    }
}