package com.example.feed.user.ui;

import com.example.feed.common.ui.Response;
import com.example.feed.user.application.UserService;
import com.example.feed.user.application.dto.CreateUserRequestDto;
import com.example.feed.user.application.dto.GetUserListResponseDto;
import com.example.feed.user.application.dto.GetUserResponseDto;
import com.example.feed.user.domain.User;
import com.example.feed.user.repository.jpa.JpaUserListQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
        return Response.ok(jpaUserListQueryRepository.getFollowedUserList(userId));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId) {
        return Response.ok(jpaUserListQueryRepository.getFolloingUserList(userId));
    }
}
