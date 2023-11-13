package com.example.filmRanking.repository;

import com.example.filmRanking.domain.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
}
