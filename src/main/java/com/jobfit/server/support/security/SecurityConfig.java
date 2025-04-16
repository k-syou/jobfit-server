package com.jobfit.server.support.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //패스워드 인코더 설정
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // 시큐리티 설정을 비활성화 - 커스텀설정으로 변경
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                //6.1 버전이상부터 사용되는 csrf 람다식
                .csrf(csrf->csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/signup", "/css/**").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

}
