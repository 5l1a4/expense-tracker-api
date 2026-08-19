package com.analistas.expensetrackerapi.domain.mapper;

import com.analistas.expensetrackerapi.domain.dto.UserRequest;
import com.analistas.expensetrackerapi.domain.dto.UserResponse;
import com.analistas.expensetrackerapi.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity (UserRequest request) {
        return new User(
                request.getEmail(),
                request.getUsername()
        );
    }

    public UserResponse toResponse (User user) {
        return new UserResponse(
                user.getEmail(),
                user.getUsername()
        );
    }
}
