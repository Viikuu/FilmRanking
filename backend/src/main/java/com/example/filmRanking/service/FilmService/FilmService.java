package com.example.filmRanking.service.FilmService;

import com.example.filmRanking.domain.FilmEntity;
import com.example.filmRanking.utils.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    FilmEntity createFilm(FilmEntity film);

    Optional<FilmEntity> getFilmById(Long id);

    List<FilmEntity> getAllFilms();

    FilmEntity updateFilm(Long id, FilmEntity filmDetails) throws ResourceNotFoundException;

    void deleteFilm(Long id);

    void validateReleaseYear(int releaseYear);

    void validateFilmEntity(FilmEntity film);
}
