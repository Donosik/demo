package com.example.demo.Repositories;

import com.example.demo.DB.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContaining(String title, Sort sort);
    List<Book> findByTitleContainingAndAuthorId(String title, Long authorId, Sort sort);
}
