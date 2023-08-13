package com.java.isc.config;

import com.java.isc.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import  com.java.isc.services.JpaUserDetailsService;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class securityConfiguration {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/css/**",
            "/login/**",
            "/home","/h2-console/**","/","/logout",
    };

    private final JpaUserDetailsService JpaUserDetailsService;

    public securityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
        JpaUserDetailsService = jpaUserDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .headers(h->h.frameOptions().sameOrigin())
                .authorizeRequests()
                .antMatchers(ENDPOINTS_WHITELIST).permitAll().
                 antMatchers("/admin/**").hasAuthority("ADMIN").
                 antMatchers("/student/**").hasAuthority("STUDENT")
                .anyRequest().authenticated()
                .and()
                .userDetailsService(JpaUserDetailsService)
                .formLogin().failureUrl("/errorPage").successForwardUrl("/").defaultSuccessUrl("/")
                .usernameParameter("username").permitAll().and().rememberMe()
                .and().exceptionHandling().accessDeniedPage("/errorPage")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).deleteCookies("remember-me").and().
                httpBasic();

        return http.build();
    }
}
