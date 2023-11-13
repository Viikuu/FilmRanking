package com.example.filmRanking.service;

import com.example.filmRanking.domain.FilmEntity;
import com.example.filmRanking.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImplementation implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImplementation(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmEntity createFilm(FilmEntity film) {
        return filmRepository.save(film);
    }

    @Override
    public Optional<FilmEntity> getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public List<FilmEntity> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public FilmEntity updateFilm(Long id, FilmEntity filmDetails) {
        FilmEntity film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found for id: " + id));

        film.setTitle(filmDetails.getTitle());
        film.setDescription(filmDetails.getDescription());

        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

}
