package com.example.feed.post.repository.jpa;

import com.example.feed.post.domain.comment.Comment;
import com.example.feed.post.repository.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = """
            UPDATE CommentEntity c
            SET c.content = :#{#comment.content},
                c.modDt = now()
            WHERE c.id = :#{#comment.id}
            """)
    void updateCommentEntity(CommentEntity comment);

    @Modifying
    @Query(value = """
            UPDATE CommentEntity c
            SET c.likeCount = c.likeCount + :likeCount,
                c.modDt = now()
            WHERE c.id = :commentId
            """)
    void updateLikeCount(Long commentId, Integer likeCount);
}
