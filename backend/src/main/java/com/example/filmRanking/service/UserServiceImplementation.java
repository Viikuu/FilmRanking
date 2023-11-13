package com.example.filmRanking.service;

import com.example.filmRanking.domain.UserEntity;

import com.example.filmRanking.repository.UserRepository;
import com.example.filmRanking.utils.PasswordEncoderService;
import com.example.filmRanking.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Value("${app.security.pepper}")
    private final String pepper;
    private final SecureRandom random = new SecureRandom();

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, @Value("${app.security.pepper}") String pepper) {
        this.userRepository = userRepository;
        this.pepper = pepper;
    }

    public boolean checkPassword(String rawPassword, UserEntity userEntity) {
        String passwordWithSaltAndPepper = userEntity.getSalt() + rawPassword + pepper;
        return PasswordEncoderService.verifyPassword(passwordWithSaltAndPepper, userEntity.getPassword());
    }

    @Override
    public void registerUser(String username, String password) {
        String salt = generateSalt();
        String passwordWithSaltAndPepper = salt + password + pepper;
        String encodedPassword = PasswordEncoderService.hashPassword(passwordWithSaltAndPepper);
        UserEntity user = new UserEntity(null, username, encodedPassword, salt, null);
        userRepository.save(user);
    }

    @Override
    public String loginUser(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (checkPassword(password, user)) {
            String token = TokenService.generateToken();
            user.setToken(token);
            userRepository.save(user);
            return token;
        } else {
            throw new RuntimeException("Password is incorrect");
        }
    }

    public boolean existsByToken(String token) {
        return userRepository.findByToken(token) != null;
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}

