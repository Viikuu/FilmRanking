package com.example.filmRanking.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RatingEntity> ratings;
}
