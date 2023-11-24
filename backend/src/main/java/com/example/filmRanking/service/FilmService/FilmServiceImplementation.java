package com.example.filmRanking.service.FilmService;

import com.example.filmRanking.domain.FilmEntity;
import com.example.filmRanking.repository.FilmRepository;
import com.example.filmRanking.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public FilmEntity updateFilm(Long id, FilmEntity filmDetails) throws ResourceNotFoundException {
        FilmEntity film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for id: " + id));

        film.setTitle(filmDetails.getTitle());
        film.setDescription(filmDetails.getDescription());

        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public void validateReleaseYear(int releaseYear) {
        if(releaseYear != 0 && ( releaseYear < 1895 || releaseYear > 2023 )) {
            throw new IllegalArgumentException("Incorrect release year!");
        }
    }

}
