package com.example.feed.auth.repository.entity;

import com.example.feed.auth.domain.UserAuth;
import com.example.feed.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_auth")
@Entity
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private String email;
    private String password;
    private String role;
    private Long userId;
    private LocalDateTime lastLoginDt;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail();
        this.password = userAuth.getPassword();
        this.role = userAuth.getRole();
        this.userId = userId;
    }

    public UserAuth toUserAuth() {
        return new UserAuth(userId, email, password, role);
    }

    public void updateLastLoginAt() {
        this.lastLoginDt = LocalDateTime.now();
    }
}