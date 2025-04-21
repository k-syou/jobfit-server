package com.jobfit.server.service.user;

import com.jobfit.server.domain.user.UserStatus;
import lombok.Getter;

@Getter

public class UserWithDrawCommand {
    private final String email;

    public UserWithDrawCommand(String email) {
        this.email = email;
    }
}

