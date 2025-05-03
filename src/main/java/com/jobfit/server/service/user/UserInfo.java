package com.jobfit.server.service.user;

import java.time.LocalDateTime;

import com.jobfit.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {

    private Long userId;
    private String username;
    private String name;
    private String email;
    private LocalDateTime createAt;

    @Builder
    private UserInfo(Long userId, String username, String name, String email, LocalDateTime createAt) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.createAt = createAt;

    }

    public static UserInfo from(User user) {
        return UserInfo.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .createAt(user.getCreatedAt())
                .build();
    }
}
