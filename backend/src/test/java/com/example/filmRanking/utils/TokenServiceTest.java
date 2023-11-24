package com.example.filmRanking.utils;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @Test
    void givenBearerTokenWhenGetTokenFromBearerHeaderStringReturnToken() {
        assertEquals(TokenService.getTokenFromBearerHeaderString("Bearer 1234567"), "1234567");
        assertEquals(TokenService.getTokenFromBearerHeaderString("Bearer 123456234342342341523"), "123456234342342341523");
    }

    @RepeatedTest(5)
    void givenBearerTokenWhenGetTokenFromBearerHeaderStringReturnNull() {
        assertNull(TokenService.getTokenFromBearerHeaderString(null));
        assertNull(TokenService.getTokenFromBearerHeaderString(""));
    }
}