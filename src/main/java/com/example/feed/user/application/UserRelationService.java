package com.example.feed.user.application;

import com.example.feed.user.application.dto.FollowUserRequestDto;
import com.example.feed.user.application.interfaces.UserRelationRepository;
import com.example.feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService,
            UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            return;
        }

        userRelationRepository.save(user, targetUser);
        user.follow(targetUser);
    }

    public void unfollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            return;
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }
}
