package com.example.filmRanking.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    static public String generateToken() {
        return UUID.randomUUID().toString();
    }
    static public String getTokenFromBearerHeaderString(String bearerToken) {
        if(bearerToken!= null) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }
}
