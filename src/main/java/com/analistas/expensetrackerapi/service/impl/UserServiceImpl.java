package com.analistas.expensetrackerapi.service.impl;

import com.analistas.expensetrackerapi.domain.dto.UserRequest;
import com.analistas.expensetrackerapi.domain.dto.UserResponse;
import com.analistas.expensetrackerapi.domain.entity.User;
import com.analistas.expensetrackerapi.domain.mapper.UserMapper;
import com.analistas.expensetrackerapi.exception.ResourceConflictException;
import com.analistas.expensetrackerapi.repository.UserRepository;
import com.analistas.expensetrackerapi.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(UserRequest userRequest) {

        if (userRepo.existsByEmail(userRequest.getEmail()) || userRepo.existsByUsername(userRequest.getUsername())) {
            throw new ResourceConflictException("El email o usuario ya existe en el sistema");
        }else {
            User user = userMapper.toEntity(userRequest);
            user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
            userRepo.save(user);
            return userMapper.toResponse(user);
        }
    }

}
