package org.example.aletheiacares;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * ReplyMapper is responsible for converting between ReplyDto and Reply.
 *
 * Note:
 *  - When converting DTO → Entity, we ignore “post” and “parentReply” because
 *    we will look up and set those in the controller (otherwise Hibernate
 *    will try to persist a transient Post/Reply and throw an exception).
 *  - When converting Entity → DTO, we copy post.postId → dto.postId and
 *    parentReply.replyId → dto.parentReplyId so the front‐end sees those IDs.
 */
@Mapper(componentModel = "spring")
public interface ReplyMapper {

    /**
     * Convert a ReplyDto into a Reply entity.
     * We ignore:
     *   - post         (set manually in controller)
     *   - parentReply  (set manually in controller)
     *   - createdAt    (set manually in controller)
     *   - childReplies (we don’t populate this from DTO)
     *   - user         (set in the service if needed)
     */
    @Mapping(target = "post",         ignore = true)
    @Mapping(target = "parentReply",  ignore = true)
    @Mapping(target = "createdAt",    ignore = true)
    @Mapping(target = "childReplies", ignore = true)
    @Mapping(target = "user",         ignore = true)
    Reply toEntity(ReplyDto dto);

    /**
     * Convert a Reply entity into a ReplyDto.
     * We copy:
     *   - post.postId → dto.postId
     *   - parentReply.replyId → dto.parentReplyId (if parentReply is non‐null)
     *   - createdAt (formatted as ISO date‐time string)
     */
    @Mapping(target = "postId",        source = "post.postId")
    @Mapping(target = "parentReplyId", source = "parentReply.replyId", defaultExpression = "java(null)")
    @Mapping(target = "createdAt",     source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    ReplyDto toDto(Reply entity);
}
