package com.example.demo.Services;

import com.example.demo.DB.Book;
import com.example.demo.DB.Reader;
import com.example.demo.DB.Review;
import com.example.demo.Repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookService bookService;
    private final ReaderService readerService;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviews(String rating, Long readerId, Long bookId, String sortBy, String order) {
        int ratingInt = Integer.parseInt(rating);
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        //TODO: zrobić filtrowanie

        return reviewRepository.findAll(sort);
    }

    public Review getById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public void addReview(int rating, String comment, Long bookId, Long readerId) {
        Book book = bookService.getById(bookId);
        if (book == null) {
            return;
        }
        Reader reader = readerService.getById(readerId);
        if (reader == null) {
            return;
        }
        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setBook(book);
        review.setReader(reader);
        reviewRepository.save(review);

    }

    public void updateReview(Long id, int rating, String comment, Long bookId, Long readerId) {

        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return;
        }
        review.setRating(rating);
        review.setComment(comment);
        Book book = bookService.getById(bookId);
        if (book != null) {
            review.setBook(book);
        }
        Reader reader = readerService.getById(readerId);
        if (reader != null) {
            review.setReader(reader);
        }
        reviewRepository.save(review);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}