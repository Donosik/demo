package com.example.demo.WebControllers;

import com.example.demo.DB.Book;
import com.example.demo.Services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class BookWebController {
    private final BookService bookService;

    @GetMapping("/books")
    public String books(Model model,
                        @RequestParam(required = false, defaultValue = "") String title,
                        @RequestParam(required = false, defaultValue = "") Long authorId,
                        @RequestParam(defaultValue = "id") String sortBy,
                        @RequestParam(defaultValue = "asc") String order) {

        model.addAttribute("books", bookService.getBooks(title, authorId, sortBy, order));
        model.addAttribute("title", title);
        model.addAttribute("authorId", authorId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        return "books";
    }

    @PostMapping("/books/update")
    public String updateBook(@RequestParam Long id, @RequestParam String title, @RequestParam Long authorId) {
        bookService.updateBook(id, title, authorId);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/books/add")
    public String addBook(@RequestParam String title, @RequestParam Long authorId) {
        bookService.addBook(title, authorId);
        return "redirect:/books";
    }
}