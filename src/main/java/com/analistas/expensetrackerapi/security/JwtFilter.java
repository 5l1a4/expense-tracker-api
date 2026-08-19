package com.analistas.expensetrackerapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestAuth = request.getHeader("Authorization");

        if (requestAuth != null && requestAuth.startsWith("Bearer ")) {
            String token = requestAuth.substring("Bearer ".length());

            if (jwtService.validateToken(token)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        jwtService.getUsernameFromToken(token), null, Collections.emptyList());

                SecurityContext context = SecurityContextHolder.getContext();

                context.setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request, response);

    }
}
