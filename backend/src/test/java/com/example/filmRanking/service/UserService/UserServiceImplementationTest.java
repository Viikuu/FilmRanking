package com.example.filmRanking.service.UserService;

import com.example.filmRanking.FilmRankingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = FilmRankingApplication.class)
class UserServiceImplementationTest {

    @Autowired
    private UserService userService;
    @Test
    public void givenUserIdAndRatingCreatorIdWhenAuthenticateUserNotThrow(){
        assertDoesNotThrow(() -> userService.authenticateUser(123, 123));
        assertDoesNotThrow(() -> userService.authenticateUser(321, 321));
    }

    @Test
    public void givenUserIdAndRatingCreatorIdWhenAuthenticateUserThrow(){
        assertThrows(IllegalArgumentException.class, () -> userService.authenticateUser(123, 111));
        assertThrows(IllegalArgumentException.class, () -> userService.authenticateUser(321, 3333));
    }
}