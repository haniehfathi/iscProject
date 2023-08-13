package com.java.isc.config;

import com.java.isc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

        private User user;


        public CustomUserDetails(User user) {
            this.user = user;
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public Set<GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return authorities;
        }
        @Override
        public boolean isAccountNonExpired() {
                return true;
    }

        @Override
        public boolean isAccountNonLocked() {
                return true;
    }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
}

    @Override
    public boolean isEnabled() {
        return true;
    }
}
