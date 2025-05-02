package com.jobfit.server.interfaces.api.user.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

@Getter
@NoArgsConstructor
public class UserLoginRequest {

    public String username;
    public String password;

    public static UserLoginRequest of(HttpServletRequest request) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(request.getInputStream(), UserLoginRequest.class);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("username 또는 password가 없습니다.");
        }
    }
}
