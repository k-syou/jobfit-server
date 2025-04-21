package com.jobfit.server.interfaces.api.user;

import com.jobfit.server.service.user.UserCheckDuplicatedEmailCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCheckDuplicatedEmailRequest {
    private String email;

    public UserCheckDuplicatedEmailCommand toCommand(){
        return new UserCheckDuplicatedEmailCommand(email);
    }

}
