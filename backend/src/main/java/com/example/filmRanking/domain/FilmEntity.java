package com.example.filmRanking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must not be longer than 100 characters")
    private String title;

    @Size(max = 1000, message = "Description must not be longer than 1000 characters")
    private String description;

    private int releaseYear;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    private int meanRating;

    private String trailerUrl;

    private String posterUrl;
}
