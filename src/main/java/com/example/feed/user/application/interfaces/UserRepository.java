package com.example.feed.user.application.interfaces;

import com.example.feed.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
