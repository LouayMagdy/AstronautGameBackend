package com.example.astronautgamebackend.Controller.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** for checking the JWT token and extracting username from it**/
@Component
public class JWTAuthenticFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authenticHeader = request.getHeader("Authorization"); /// bearer/JWT token is in this header
        if (authenticHeader == null || !authenticHeader.startsWith("Bearer ")) { ///pass it to the next filter in the CoR
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authenticHeader.substring(7); /// beacause "Bearer " is of length 7
        final String userName = this.jwtService.extractUserName(jwt);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) { // username is found and user is not yet authenticated
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if (this.jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authenticToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
