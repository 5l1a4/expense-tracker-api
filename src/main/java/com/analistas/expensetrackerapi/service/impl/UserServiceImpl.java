package com.analistas.expensetrackerapi.service.impl;

import com.analistas.expensetrackerapi.domain.dto.UserRequest;
import com.analistas.expensetrackerapi.domain.dto.UserResponse;
import com.analistas.expensetrackerapi.domain.entity.User;
import com.analistas.expensetrackerapi.domain.mapper.UserMapper;
import com.analistas.expensetrackerapi.exception.ResourseConflictException;
import com.analistas.expensetrackerapi.repository.UserRepository;
import com.analistas.expensetrackerapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,  PasswordEncoder passwordEncoder) {
        this.userRepo = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(UserRequest userRequest) {

        if (userRepo.existsByEmail(userRequest.getEmail()) || userRepo.existsByUsername(userRequest.getUsername())) {
            throw new ResourseConflictException("El email o usuario ya existe en el sistema");
        }else {
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            User user = userMapper.toEntity(userRequest);
            userRepo.save(user);
            return userMapper.toResponse(user);
        }
    }
}
