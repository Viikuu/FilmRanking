package com.example.filmRanking.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderServiceTest {
    @Test
    void givenPasswordAndHashVerifyPasswordReturnTrue() {
        String password = "123";
        String hash = PasswordEncoderService.hashPassword(password);
        assertTrue(PasswordEncoderService.verifyPassword(password, hash));
        assertTrue(PasswordEncoderService.verifyPassword("3333", PasswordEncoderService.hashPassword("3333")));
    }

    @Test
    void givenPasswordAndHashVerifyPasswordReturnFalse() {
        String hash = PasswordEncoderService.hashPassword("3123123123123");
        assertFalse(PasswordEncoderService.verifyPassword("3123123123", hash));
        assertFalse(PasswordEncoderService.verifyPassword("1111111111111", hash));
    }
}