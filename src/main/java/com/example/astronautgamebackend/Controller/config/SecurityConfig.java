package com.example.astronautgamebackend.Controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTAuthenticFilter jwtAuthenticFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    /// configuring security of http requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                    .csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers("/api/v1/auth/astronaut-game/**").permitAll()
                    .anyRequest().authenticated().and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthenticFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
