package com.example.feed.post.repository.post_queue;

import com.example.feed.post.repository.entity.PostEntity;
import com.example.feed.post.repository.entity.UserPostQueueEntity;
import com.example.feed.post.repository.jpa.JpaPostRepository;
import com.example.feed.post.repository.jpa.JpaUserPostQueueRepository;
import com.example.feed.user.repository.entity.UserEntity;
import com.example.feed.user.repository.jpa.JpaUserRelationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followersIds.stream()
                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void save(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findALlPostIdByAuthorId(targetId);
        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
