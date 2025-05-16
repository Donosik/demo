package com.example.demo.WebControllers;

import com.example.demo.DB.Author;
import com.example.demo.Services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

@Controller
@AllArgsConstructor
public class AuthorWebController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    public String authors(Model model,
                          @RequestParam(required = false, defaultValue = "") String firstName,
                          @RequestParam(required = false, defaultValue = "") String lastName,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "asc") String order,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "1") int size) {

        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<Author> authorsPage = authorService.getAuthors(firstName, lastName, pageable);

        model.addAttribute("authorsPage", authorsPage);
        model.addAttribute("authors", authorsPage.getContent());
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("page", page);
        model.addAttribute("size", authorsPage.getSize());
        return "authors";
    }

    @GetMapping("/authors-names")
    public String authorsNames(Model model)
    {
        model.addAttribute("authorNames", authorService.getAllProjected());
        return "authors-names";
    }

    @PostMapping("/authors/update")
    public String updateAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete")
    public String deleteAuthor(@RequestParam Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        authorService.addAuthor(firstName, lastName);
        return "redirect:/authors";
    }
}
