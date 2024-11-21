package com.example.feed.post.repository.jpa;

import com.example.feed.post.repository.entity.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<Long> findALlPostIdByAuthorId(Long authorId);

    @Modifying
    @Query(value = """
            UPDATE PostEntity p
            SET p.content = :#{#postEntity.getContent()},
                p.modDt = now()
            WHERE p.id = :#{#postEntity.getId()}
            """)
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = """
            UPDATE PostEntity p
            SET p.likeCount = p.likeCount + :likeCount,
                p.modDt = now()
            WHERE p.id = :postId
            """)
    void updateLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query(value = """
            UPDATE PostEntity p
            SET p.commentCount = p.commentCount + 1,
                p.modDt = now()
            WHERE p.id = :postId
            """)
    void incrementCommentCount(Long postId);
}
