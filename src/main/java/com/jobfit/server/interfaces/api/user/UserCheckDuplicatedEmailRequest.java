package com.jobfit.server.interfaces.api.user;

import com.jobfit.server.service.user.UserCheckDuplicatedEmailCommand;
import com.jobfit.server.support.exception.BusinessError;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCheckDuplicatedEmailRequest {
    private String username;

    public UserCheckDuplicatedEmailCommand toCommand(){
        if (username == null || username.isEmpty()) {
            throw BusinessError.USER_SIGNUP_USERNAME_DUPLICATE_NULL_OR_EMPTY.exception();
        }
        return new UserCheckDuplicatedEmailCommand(username);
    }

}
