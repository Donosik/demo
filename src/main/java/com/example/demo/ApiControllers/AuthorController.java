package com.example.demo.ApiControllers;

import com.example.demo.Services.AuthorService;
import com.example.demo.DB.Author;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAuthors(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        return authorService.getAuthors(firstName, lastName, sortBy, order);
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
