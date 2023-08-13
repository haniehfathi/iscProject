package com.java.isc.services;

import com.java.isc.config.CustomUserDetails;
import com.java.isc.models.User;
import com.java.isc.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final
    UserRepository userAccountRepository;

    public JpaUserDetailsService(UserRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return userAccountRepository.findByUsername(username)
                .map(CustomUserDetails::new).
                orElseThrow(
                        ()->new UsernameNotFoundException("User with username [" + username + "] not found in the system")
        );
    }
}