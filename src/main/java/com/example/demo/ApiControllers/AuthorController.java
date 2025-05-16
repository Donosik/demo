package com.example.demo.ApiControllers;

import com.example.demo.Services.AuthorService;
import com.example.demo.DB.Author;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public Page<Author> getAuthors(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return authorService.getAuthors(firstName, lastName,pageable);
    }

    @PostMapping("/update")
    public void updateAuthor(@ModelAttribute Author author) {
        authorService.save(author);
    }

    @GetMapping("/delete")
    public void deleteAuthor(@RequestParam Long id) {
        authorService.delete(id);
    }

    @PostMapping("/add")
    public void addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        authorService.addAuthor(firstName, lastName);
    }
}
