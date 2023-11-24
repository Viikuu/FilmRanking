package com.example.filmRanking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(10)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "film", nullable = false)
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;
}
