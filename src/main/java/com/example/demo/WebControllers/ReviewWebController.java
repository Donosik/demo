package com.example.demo.WebControllers;

import com.example.demo.Services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ReviewWebController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public String reviews(Model model,
                          @RequestParam(required = false,defaultValue = "0") String rating,
                          @RequestParam(required = false, defaultValue = "") Long readerId,
                          @RequestParam(required = false, defaultValue = "") Long bookId,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "desc") String order) {

        model.addAttribute("reviews", reviewService.getReviews(rating,readerId,bookId, sortBy, order));
        model.addAttribute("readerId", readerId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        return "reviews";
    }

    @PostMapping("/reviews/update")
    public String updateReview(@RequestParam Long id, @RequestParam int rating,@RequestParam String comment, @RequestParam Long bookId,@RequestParam Long readerId) {
        reviewService.updateReview(id,rating,comment,bookId,readerId);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/delete")
    public String deleteReview(@RequestParam Long id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/add")
    public String addReview(@RequestParam int rating, @RequestParam(required = false) String comment, @RequestParam Long bookId, @RequestParam Long readerId) {

        reviewService.addReview(rating,comment, bookId, readerId);
        return "redirect:/reviews";
    }
}