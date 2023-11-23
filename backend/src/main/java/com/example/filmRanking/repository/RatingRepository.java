package com.example.filmRanking.repository;

import com.example.filmRanking.domain.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> findByFilmId(Long filmId);
}
