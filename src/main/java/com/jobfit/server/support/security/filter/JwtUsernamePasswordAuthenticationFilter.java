package com.jobfit.server.support.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.interfaces.api.user.UserLoginRequest;
import com.jobfit.server.support.security.CustomUserDetails;
import com.jobfit.server.support.security.LoginResponse;
import com.jobfit.server.support.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(JWTUtil jwtUti, ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        setFilterProcessesUrl("/api/v1/user/login");
        this.jwtUtil = jwtUti;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
    }

    // 로그인 요청 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            // 요청 바디에서 로그인 정보 추출
            UserLoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);

            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            log.info("로그인 시도: {}", username);

            // 이메일 + 비밀번호로 인증 객체 생성
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

            // AuthenticationManager에게 인증 요청
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            log.error("로그인 요청 파싱 실패", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long userId = customUserDetails.getUser().getId();

        LoginResponse loginResponse = new LoginResponse(jwtUtil.createJwt(userId, 60 * 60 * 1000L));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
            HttpStatus.OK,
            HttpStatus.OK.value(),
            "로그인에 성공했습니다.",
            loginResponse
        );

        String json = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
            HttpStatus.UNAUTHORIZED,
            HttpStatus.UNAUTHORIZED.value(),
            failed.getMessage(),
            null
        );

        response.setStatus(401);

        String json = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(json);
    }
}
