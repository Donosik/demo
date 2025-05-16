package com.example.demo.Repositories;

import com.example.demo.DB.Author;
import com.example.demo.projections.AuthorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Sort;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName, Pageable pageable);
    List<AuthorView> findAllProjectedBy();
}