package com.example.jwt.demojwt.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt.demojwt.domain.User;
import com.example.jwt.demojwt.repository.UserRepository;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + login + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(login)
                .password("{noop}".concat(user.getPassword()))
                .authorities(Collections.emptyList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }
}
