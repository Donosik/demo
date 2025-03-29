package com.example.demo.Services;

import com.example.demo.DB.Reader;
import com.example.demo.Repositories.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAll() {
        return readerRepository.findAll();
    }

    public List<Reader> getReaders(String firstName, String lastName, String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        return readerRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName, sort);
    }

    public Reader getById(Long id) {
        return readerRepository.findById(id).orElse(null);
    }

    public void addReader(String firstName, String lastName) {
        Reader reader = new Reader();
        reader.setFirstName(firstName);
        reader.setLastName(lastName);
        readerRepository.save(reader);
    }

    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }

    public void delete(Long id) {
        readerRepository.deleteById(id);
    }
}