package com.example.demo.WebControllers;

import com.example.demo.DB.Reader;
import com.example.demo.Services.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ReaderWebController {
    private final ReaderService readerService;

    @GetMapping("/readers")
    public String readers(Model model,
                          @RequestParam(required = false,defaultValue = "") String firstName,
                          @RequestParam(required = false,defaultValue = "") String lastName,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "asc") String order) {
        model.addAttribute("readers", readerService.getReaders(firstName,lastName, sortBy, order));
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        return "readers";
    }

    @GetMapping("/readers-QBE")
    public String readersQBE(Model model,@RequestParam(required = false) String lastName)
    {
        model.addAttribute("readers", readerService.getReadersQBE(lastName));
        model.addAttribute("lastName", lastName);
        return "readers-QBE";
    }

    @PostMapping("/readers/update")
    public String updateReader(@ModelAttribute Reader reader) {
        readerService.save(reader);
        return "redirect:/readers";
    }

    @GetMapping("/readers/delete")
    public String deleteReader(@RequestParam Long id) {
        readerService.delete(id);
        return "redirect:/readers";
    }

    @PostMapping("/readers/add")
    public String addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        readerService.addReader(firstName,lastName);
        return "redirect:/readers";
    }
}