package com.jobfit.server.support.security;

import com.jobfit.server.support.security.filter.AuthenticationErrorFilter;
import com.jobfit.server.support.security.filter.JwtAuthenticationFilter;
import com.jobfit.server.support.security.filter.JwtUsernamePasswordAuthenticationFilter;
import com.jobfit.server.support.security.handler.LoginFailureHandler;
import com.jobfit.server.support.security.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationErrorFilter authenticationErrorFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    //패스워드 인코더 설정
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // 시큐리티 설정을 비활성화 - 커스텀설정으로 변경
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager
    ) {
        JwtUsernamePasswordAuthenticationFilter filter =
                new JwtUsernamePasswordAuthenticationFilter(authenticationManager, loginSuccessHandler, loginFailureHandler);
        filter.setFilterProcessesUrl("/api/v1/login");
        return filter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter) throws Exception {
        //한글 필터 설정
        http
                .addFilterBefore(encodingFilter(), CsrfFilter.class)
                .addFilterBefore(authenticationErrorFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //이쪽에서 URL 설정해야됩니다
                // Permitall 모든 사용자허가
                // .antMatchers(HttpMethod.GET, "/api/v1/**").authenticated() 로그인한 사용자만 허가
                // 추가안해주시면 작동안합니다 꼭추가해주세요
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/login", "/api/v1/signup", "/css/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
