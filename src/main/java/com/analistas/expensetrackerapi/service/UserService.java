package com.analistas.expensetrackerapi.service;

import com.analistas.expensetrackerapi.domain.dto.UserRequest;
import com.analistas.expensetrackerapi.domain.dto.UserResponse;

public interface UserService {

    UserResponse register(UserRequest userRequest);

}
