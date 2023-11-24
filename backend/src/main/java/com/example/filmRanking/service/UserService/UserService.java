package com.example.filmRanking.service.UserService;

import com.example.filmRanking.domain.UserEntity;
import com.example.filmRanking.utils.ResourceNotFoundException;

public interface UserService {
    void registerUser(String username, String password);
    boolean checkPassword(String rawPassword, UserEntity userEntity);
    String loginUser(String username, String password) throws ResourceNotFoundException;

    boolean existsByToken(String token);
}