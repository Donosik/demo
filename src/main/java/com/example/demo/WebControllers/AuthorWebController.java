package com.example.demo.WebControllers;

import com.example.demo.DB.Author;
import com.example.demo.Services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthorWebController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    public String authors(Model model,
                          @RequestParam(required = false,defaultValue = "") String firstName,
                          @RequestParam(required = false,defaultValue = "") String lastName,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "asc") String order) {

        model.addAttribute("authors", authorService.getAuthors(firstName, lastName, sortBy, order));
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        return "authors";
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
        authorService.addAuthor(firstName,lastName);
        return "redirect:/authors";
    }
}
