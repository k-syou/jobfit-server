package com.jobfit.server.service.user;

import java.time.LocalDateTime;

import com.jobfit.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {

    private Long userId;
    private String username;
    private String nickname;
    private LocalDateTime createAt;

    @Builder
    private UserInfo(Long userId, String username, String nickname, LocalDateTime createAt) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.createAt = createAt;

    }

    public static UserInfo from(User user) {
        return UserInfo.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .createAt(user.getCreatedAt())
                .build();
    }
}
