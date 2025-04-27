package com.jobfit.server.service.user;

import com.jobfit.server.domain.user.UserStatus;
import lombok.Getter;

@Getter

public class UserWithDrawCommand {
    private final Long userId;

    public UserWithDrawCommand(Long userId) {
        this.userId = userId;
    }
}

