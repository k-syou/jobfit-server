package com.jobfit.server.support.security;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(USER_NOT_FOUND_ERROR::exception);
        return new CustomUserDetails(user);
    }
}
