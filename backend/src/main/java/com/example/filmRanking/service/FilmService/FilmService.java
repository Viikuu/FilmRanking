package com.example.filmRanking.service.FilmService;

import com.example.filmRanking.domain.FilmEntity;
import java.util.List;
import java.util.Optional;

public interface FilmService {

    FilmEntity createFilm(FilmEntity film);

    Optional<FilmEntity> getFilmById(Long id);

    List<FilmEntity> getAllFilms();

    FilmEntity updateFilm(Long id, FilmEntity filmDetails);

    void deleteFilm(Long id);
}
