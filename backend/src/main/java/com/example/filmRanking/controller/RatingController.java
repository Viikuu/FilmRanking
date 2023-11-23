package com.example.filmRanking.controller;

import com.example.filmRanking.domain.RatingEntity;
import com.example.filmRanking.service.RatingService.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingEntity> createRating(@RequestBody RatingEntity rating) {

        return ResponseEntity.ok(ratingService.createRating(rating));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RatingEntity>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingEntity> updateRating(@PathVariable Long id, @RequestBody RatingEntity ratingDetails) {
        try {
            RatingEntity updatedRating = ratingService.updateRating(id, ratingDetails);
            return ResponseEntity.ok(updatedRating);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }
}
