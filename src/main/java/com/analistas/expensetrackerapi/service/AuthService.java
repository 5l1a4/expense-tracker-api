package com.analistas.expensetrackerapi.service;

import com.analistas.expensetrackerapi.domain.dto.LoginRequest;

public interface AuthService {

    String login(LoginRequest loginRequest);

}
