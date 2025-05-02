package com.jobfit.server.service.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

public class UserCheckDuplicatedEmailCommand {
    private String username;

    public UserCheckDuplicatedEmailCommand(String username){
        this.username = username;
    }
}
