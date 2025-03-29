package com.example.demo.ApiControllers;

import com.example.demo.Services.BookService;
import com.example.demo.DB.Book;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false) Long authorId,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        return bookService.getBooks(title, authorId, sortBy, order);
    }

    @PostMapping("/update")
    public void updateBook(@RequestParam Long id, @RequestParam String title, @RequestParam Long authorId) {
        bookService.updateBook(id, title, authorId);
    }

    @GetMapping("/delete")
    public void deleteBook(@RequestParam Long id) {
        bookService.delete(id);
    }

    @PostMapping("/add")
    public void addBook(@RequestParam String title, @RequestParam Long authorId) {
        bookService.addBook(title, authorId);
    }
}
