package com.example.filmRanking.service;

import com.example.filmRanking.domain.UserEntity;

public interface UserService {
    void registerUser(String username, String password);
    boolean checkPassword(String rawPassword, UserEntity userEntity);
    String loginUser(String username, String password);

    boolean existsByToken(String token);
}