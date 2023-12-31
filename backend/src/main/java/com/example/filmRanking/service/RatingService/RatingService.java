package com.example.filmRanking.service.RatingService;

import com.example.filmRanking.domain.RatingEntity;
import com.example.filmRanking.utils.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    RatingEntity createRating(RatingEntity rating);

    Optional<RatingEntity> getRatingById(Long id);

    List<RatingEntity> getAllRatings();

    RatingEntity updateRating(Long id, RatingEntity ratingDetails) throws ResourceNotFoundException;

    void deleteRating(Long id);

    void validateRating(RatingEntity rating);


}
