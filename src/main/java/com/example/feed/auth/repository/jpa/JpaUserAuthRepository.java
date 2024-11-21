package com.example.feed.auth.repository.jpa;

import com.example.feed.auth.repository.entity.UserAuthEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, Long> {

    Optional<UserAuthEntity> findByEmail(String email);
}