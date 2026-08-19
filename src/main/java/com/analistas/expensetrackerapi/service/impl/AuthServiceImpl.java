package com.analistas.expensetrackerapi.service.impl;

import com.analistas.expensetrackerapi.domain.dto.LoginRequest;
import com.analistas.expensetrackerapi.security.JwtService;
import com.analistas.expensetrackerapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService  jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequest loginRequest){

        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        authenticationManager.authenticate(credentials);

        return jwtService.generateToken(loginRequest.getUsername());
    }


}
