package com.appapi.projvid.config;

import static com.appapi.projvid.entity.user.Permission.ADMIN_CREATE;
import static com.appapi.projvid.entity.user.Permission.ADMIN_READ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

        private static final String[] WHITE_LIST_URL = { "/auth/**"};

        @Autowired
        WebSecurityFilter jwtAuthFilter;

        @Autowired
        AuthenticationProvider authenticationProvider;

        @Autowired
        LogoutHandler logoutHandler;

        @Bean
        SecurityFilterChain webSecurityChain(HttpSecurity http) throws Exception {
                return http
                        .csrf(csrf -> csrf.disable())

                        .authorizeHttpRequests(authorize ->
                                        authorize.requestMatchers(WHITE_LIST_URL).permitAll()
                                        .requestMatchers(HttpMethod.GET, "/show/animes").hasAnyAuthority(ADMIN_READ.name())
                                        .requestMatchers(HttpMethod.GET, "/show/episodes").hasAnyAuthority(ADMIN_READ.name())
                                        .requestMatchers(HttpMethod.POST, "/show/anime").hasAnyAuthority(ADMIN_CREATE.name())
                                        .requestMatchers(HttpMethod.POST, "/show/episode").hasAnyAuthority(ADMIN_CREATE.name())
                                        .anyRequest().authenticated())

                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        .authenticationProvider(authenticationProvider)
                        .logout(logout -> logout.logoutUrl("/user/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
                        .build();
        }
}
