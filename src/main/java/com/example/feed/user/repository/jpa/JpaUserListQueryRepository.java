package com.example.feed.user.repository.jpa;

import com.example.feed.user.application.dto.GetUserListResponseDto;
import com.example.feed.user.repository.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = """
        SELECT new com.example.feed.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)
        FROM UserRelationEntity ur
        INNER JOIN UserEntity u ON ur.followingUserId = u.id
        WHERE ur.followingUserId = :userId
        """
    )
    List<GetUserListResponseDto> getFolloingUserList(Long userId);


    @Query(value = """
        SELECT new com.example.feed.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)
        FROM UserRelationEntity ur
        INNER JOIN UserEntity u ON ur.followerUserId = u.id
        WHERE ur.followerUserId = :userId
        """
    )
    List<GetUserListResponseDto> getFollowedUserList(Long userId);
}
