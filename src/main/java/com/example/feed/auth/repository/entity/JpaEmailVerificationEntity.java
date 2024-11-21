package com.example.feed.auth.repository.entity;

import com.example.feed.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="email_verification")
@Entity
public class JpaEmailVerificationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String token;
    private boolean isVerified;

    public JpaEmailVerificationEntity(String email, String token) {
        this.id = null;
        this.email = email;
        this.token = token;
        this.isVerified = false;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public void verify() {
        this.isVerified = true;
    }

    public boolean hasSameToken(String token) {
        return this.token.equals(token);
    }
}