package com.example.filmRanking.controller;

import com.example.filmRanking.domain.UserEntity;
import com.example.filmRanking.service.UserService.UserService;
import com.example.filmRanking.utils.FormattedResponse;
import com.example.filmRanking.utils.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<FormattedResponse> registerUser(@RequestBody UserEntity request) {
        userService.registerUser(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(FormattedResponse.settupFormattedResponse(200, "User registered successfully", null));
    }

    @PostMapping("/login")
    public ResponseEntity<FormattedResponse> loginUser(@RequestBody UserEntity request) throws ResourceNotFoundException {
        String token = userService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(FormattedResponse.settupFormattedResponse(200, "User sign in successfully", token));
    }
}
