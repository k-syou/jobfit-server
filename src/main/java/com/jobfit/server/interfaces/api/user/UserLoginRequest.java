package com.jobfit.server.interfaces.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.net.http.HttpRequest;

@Getter
@NoArgsConstructor
public class UserLoginRequest {
    public String email;
    public String password;

    public static UserLoginRequest of(HttpServletRequest request) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(request.getInputStream(), UserLoginRequest.class);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("email 또는 password가 없습니다.");
        }
    }
}
