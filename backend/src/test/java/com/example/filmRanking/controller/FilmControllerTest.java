package com.example.filmRanking.controller;

import com.example.filmRanking.FilmRankingApplication;
import com.example.filmRanking.domain.FilmEntity;
import com.example.filmRanking.repository.FilmRepository;
import com.example.filmRanking.repository.UserRepository;
import com.example.filmRanking.service.UserService.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = FilmRankingApplication.class)
class FilmControllerTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    private String token;

    @BeforeEach
    public void setup(){
        token = obtainAccessToken();
    }

    private String obtainAccessToken()   {
        String username = "username";
        String password = "password";
        userService.registerUser(username, password);
        return userService.loginUser(username, password);
    }

    @AfterEach
    public void unSetup()   {
        userRepository.deleteAll();
        filmRepository.deleteAll();
    }

    @Test
    void getOneFilm() throws Exception {
        FilmEntity testFilm = createTestFilm("Test Film", "Test Description", 2020, "Test Genre");
        Long testFilmId = testFilm.getId();

        mvc.perform(get("/film/" + testFilmId) // Replace with the correct URI template
                        .header("Authorization", "Bearer " + this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Film")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.releaseYear", is(2020)))
                .andExpect(jsonPath("$.genre", is("Test Genre")));
    }
    @Test
    void getAllFilms() throws Exception {
        createTestFilm("Film 1", "Description 1", 2021, "Genre 1");
        createTestFilm("Film 2", "Description 2", 2022, "Genre 2");

        mvc.perform(get("/film") // Replace "/films" with the actual endpoint URL
                        .header("Authorization", "Bearer " + this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Film 1")))
                .andExpect(jsonPath("$[1].title", is("Film 2")));
    }

    private FilmEntity createTestFilm(String title, String description, int year, String genre) {
        FilmEntity film = new FilmEntity(null, title, description, year, genre, 0, null, null, null);
        return filmRepository.save(film);
    }

    @Test
    public void whenValidInput_thenCreateFilm_onPutRequest() throws
             Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FilmEntity film1 = new FilmEntity(null, "title","description",2020,"genre",0,null,null, null );

        mvc.perform(post("/film")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(film1)))
                .andExpect(status().isOk());

        List<FilmEntity> found = filmRepository.findAll();

        assertThat(found).extracting(FilmEntity::getTitle)
                 .containsOnly("title");
    }

    @Test
    public void whenNoValidInput_thenReturnErrorStatus_onPutRequest() throws
             Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String film1 = "123";
        mvc.perform(post("/film")
                        .header("Authorization", "Bearer " + this.token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(film1)))
                .andExpect(status().isBadRequest());
    }
}