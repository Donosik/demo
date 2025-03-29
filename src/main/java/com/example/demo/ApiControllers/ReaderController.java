package com.example.demo.ApiControllers;

import com.example.demo.DB.Reader;
import com.example.demo.Services.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
@AllArgsConstructor
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping
    public List<Reader> getReaders(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        return readerService.getReaders(firstName, lastName, sortBy, order);
    }

    @PostMapping("/update")
    public void updateReader(@ModelAttribute Reader reader) {
        readerService.save(reader);
    }

    @GetMapping("/delete")
    public void deleteReader(@RequestParam Long id) {
        readerService.delete(id);
    }

    @PostMapping("/add")
    public void addReader(@RequestParam String firstName, @RequestParam String lastName) {
        readerService.addReader(firstName, lastName);
    }
}