package com.jobfit.server.support.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobfit.server.interfaces.api.user.UserLoginRequest;
import com.jobfit.server.support.security.handler.LoginFailureHandler;
import com.jobfit.server.support.security.handler.LoginSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandler loginFailureHandler) {
        super(authenticationManager);

        // 로그인 URL 지정
        setFilterProcessesUrl("/api/v1/user/login");

        // 로그인 성공/실패 핸들러 등록
        setAuthenticationSuccessHandler(loginSuccessHandler);
        setAuthenticationFailureHandler(loginFailureHandler);
    }

    // 로그인 요청 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            // 요청 바디에서 로그인 정보 추출
            ObjectMapper objectMapper = new ObjectMapper();
            UserLoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);

            log.info("로그인 시도: {}", loginRequest.getEmail());

            // 이메일 + 비밀번호로 인증 객체 생성
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    );

            // AuthenticationManager에게 인증 요청
            return getAuthenticationManager().authenticate(authRequest);

        } catch (IOException e) {
            log.error("로그인 요청 파싱 실패", e);
            throw new RuntimeException(e);
        }
    }
}
