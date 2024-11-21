package com.example.feed.post.repository.jpa;

import com.example.feed.post.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

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
            SET p.likeCount = :#{#postEntity.getLikeCount()},
                p.modDt = now()
            WHERE p.id = :#{#postEntity.getId()}
            """)
    void updateLikeCount(PostEntity postEntity);

}
