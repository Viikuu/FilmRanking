package com.example.filmRanking.service.FilmService;

import com.example.filmRanking.FilmRankingApplication;
import com.example.filmRanking.domain.FilmEntity;
import com.example.filmRanking.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = FilmRankingApplication.class)
class FilmServiceImplementationTest {

    @Autowired
    private FilmService filmService;

    @MockBean
    private FilmRepository filmRepository;
    @Test
    public void givenYearWhenValidateReleaseYearNotThrow() {
        FilmEntity film1 = new FilmEntity(null, "title","description",2020,"genre",0,null,null, null );
        FilmEntity film2 = new FilmEntity(null, "title","description",1950,"genre",0,null,null, null );
        FilmEntity film3 = new FilmEntity(null, "title","description",0,"genre",0,null,null, null );
        assertDoesNotThrow(() -> filmService.validateReleaseYear(film1.getReleaseYear()));
        assertDoesNotThrow(() -> filmService.validateReleaseYear(film2.getReleaseYear()));
        assertDoesNotThrow(() -> filmService.validateReleaseYear(film3.getReleaseYear()));
    }
    @Test
    public void givenYearWhenValidateReleaseYearThrows() {
        FilmEntity film1 = new FilmEntity(null, "title","description",-3123,"genre",0,null,null, null );
        FilmEntity film2 = new FilmEntity(null, "title","description",423424,"genre",0,null,null, null );
        FilmEntity film3 = new FilmEntity(null, "title","description",1894,"genre",0,null,null, null );

        assertThrows(IllegalArgumentException.class, () ->  filmService.validateReleaseYear(film1.getReleaseYear()));
        assertThrows(IllegalArgumentException.class, () ->  filmService.validateReleaseYear(film2.getReleaseYear()));
        assertThrows(IllegalArgumentException.class, () ->  filmService.validateReleaseYear(film3.getReleaseYear()));
    }
}