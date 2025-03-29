package com.example.demo.ApiControllers;

import com.example.demo.Services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<?> getReviews(
            @RequestParam(required = false, defaultValue = "0") String rating,
            @RequestParam(required = false) Long readerId,
            @RequestParam(required = false) Long bookId,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {

        return reviewService.getReviews(rating, readerId, bookId, sortBy, order);
    }

    @PostMapping("/update")
    public void updateReview(
            @RequestParam Long id,
            @RequestParam int rating,
            @RequestParam String comment,
            @RequestParam Long bookId,
            @RequestParam Long readerId) {

        reviewService.updateReview(id, rating, comment, bookId, readerId);
    }

    @GetMapping("/delete")
    public void deleteReview(@RequestParam Long id) {
        reviewService.delete(id);
    }

    @PostMapping("/add")
    public void addReview(
            @RequestParam int rating,
            @RequestParam String comment,
            @RequestParam Long bookId,
            @RequestParam Long readerId) {

        reviewService.addReview(rating, comment, bookId, readerId);
    }
}
