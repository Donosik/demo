package com.example.demo.Services;

import com.example.demo.DB.Author;
import com.example.demo.Repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthors(String firstName, String lastName, String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        return authorRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName, sort);
    }

    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void addAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

}
