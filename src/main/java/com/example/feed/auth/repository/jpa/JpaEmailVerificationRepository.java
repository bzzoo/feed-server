package com.example.feed.auth.repository.jpa;


import com.example.feed.auth.repository.entity.JpaEmailVerificationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailVerificationRepository extends
        JpaRepository<JpaEmailVerificationEntity, Long> {
    Optional<JpaEmailVerificationEntity> findByEmail(String email);
}