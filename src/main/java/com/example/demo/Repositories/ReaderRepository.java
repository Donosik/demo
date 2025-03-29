package com.example.demo.Repositories;

import com.example.demo.DB.Author;
import com.example.demo.DB.Reader;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader,Long> {
    List<Reader> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName, Sort sort);
}
