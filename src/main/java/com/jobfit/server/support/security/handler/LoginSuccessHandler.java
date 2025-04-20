package com.jobfit.server.support.security.handler;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.service.user.UserInfo;
import com.jobfit.server.support.security.CustomUserDetails;
import com.jobfit.server.support.security.util.JsonResponse;
import com.jobfit.server.support.security.util.JwtProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProcessor jwtProcessor;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 로그인된 사용자 정보
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user= userDetails.getUser();
        // JWT 발급
        String token = jwtProcessor.generateToken(user.getEmail());

        // 응답 구성
        JsonResponse.send(response, new LoginResponse(token, UserInfo.from(user)));
    }

    // 내부 응답 DTO (토큰 + 사용자 정보)
    record LoginResponse(String accessToken, UserInfo user) {}
}
