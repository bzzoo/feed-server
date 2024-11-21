package com.example.feed.user.repository;

import com.example.feed.post.repository.post_queue.UserPostQueueCommandRepository;
import com.example.feed.user.application.interfaces.UserRelationRepository;
import com.example.feed.user.domain.User;
import com.example.feed.user.repository.entity.UserEntity;
import com.example.feed.user.repository.entity.UserRelationEntity;
import com.example.feed.user.repository.entity.UserRelationIdEntity;
import com.example.feed.user.repository.jpa.JpaUserRelationRepository;
import com.example.feed.user.repository.jpa.JpaUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.save(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
