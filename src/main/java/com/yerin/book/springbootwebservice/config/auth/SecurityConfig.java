package com.yerin.book.springbootwebservice.config.auth;

import com.yerin.book.springbootwebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .headers((headerConfigurer) ->
                        headerConfigurer.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/"),
                                        AntPathRequestMatcher.antMatcher("/css/**"),
                                        AntPathRequestMatcher.antMatcher("/images/**"),
                                        AntPathRequestMatcher.antMatcher("/js/**"),
                                        AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/**")).hasRole(Role.USER.name())
                                .anyRequest().authenticated()
                )

                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/")
                )
                .oauth2Login(oauth2LoginConfigurer ->
                        oauth2LoginConfigurer
                                .userInfoEndpoint(userInfoEndpointConfigurer ->
                                        userInfoEndpointConfigurer
                                                .userService(customOAuth2UserService)
                                )
                );

        return http.build();

    }
}

