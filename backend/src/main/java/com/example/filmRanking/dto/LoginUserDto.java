package com.example.filmRanking.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


}
