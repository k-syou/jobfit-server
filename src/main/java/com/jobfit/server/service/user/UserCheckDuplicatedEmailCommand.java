package com.jobfit.server.service.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

public class UserCheckDuplicatedEmailCommand {
    private String email;

    public UserCheckDuplicatedEmailCommand(String email){
        this.email=email;
    }
}
