package com.example.filmRanking.service.RatingService;

import com.example.filmRanking.domain.FilmEntity;
import com.example.filmRanking.domain.RatingEntity;
import com.example.filmRanking.repository.FilmRepository;
import com.example.filmRanking.repository.RatingRepository;
import com.example.filmRanking.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImplementation implements RatingService {

    private final RatingRepository ratingRepository;
    private final FilmRepository filmRepository;
    @Autowired
    public RatingServiceImplementation(RatingRepository ratingRepository, FilmRepository filmRepository) {
        this.ratingRepository = ratingRepository;
        this.filmRepository = filmRepository;
    }

    private void updateFilmMeanRating(Long filmId) {
        List<RatingEntity> ratings = ratingRepository.findByFilmId(filmId);
        double meanRating = ratings.stream()
                .mapToInt(RatingEntity::getRating)
                .average()
                .orElse(0.0);
        FilmEntity film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found for id: " + filmId));
        film.setMeanRating((int) Math.round(meanRating));
        filmRepository.save(film);
    }

    @Override
    public RatingEntity createRating(RatingEntity rating) {
        validateRating(rating);
        RatingEntity savedRating = ratingRepository.save(rating);
        updateFilmMeanRating(savedRating.getFilm().getId());
        return savedRating;
    }

    @Override
    public Optional<RatingEntity> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public List<RatingEntity> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public RatingEntity updateRating(Long id, RatingEntity ratingDetails) throws ResourceNotFoundException {
        validateRating(ratingDetails);
        RatingEntity rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for id: " + id));
        rating.setRating(ratingDetails.getRating());
        RatingEntity updatedRating = ratingRepository.save(rating);
        updateFilmMeanRating(updatedRating.getFilm().getId());
        return updatedRating;
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    private void validateFilmEntity(FilmEntity film) {
        if (film == null || film.getId() == null || !filmRepository.existsById(film.getId())) {
            throw new IllegalArgumentException("The associated film does not exist");
        }
    }
    @Override
    public void validateRating(RatingEntity rating) {
        validateFilmEntity(rating.getFilm());
    }

    @Override
    public void authenticateUser(long id, long userId) {
        if (id != userId) {
            throw new IllegalArgumentException("User is not authorized to update others ratings!");
        }
    }
}
