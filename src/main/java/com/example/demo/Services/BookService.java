package com.example.demo.Services;

import com.example.demo.DB.Author;
import com.example.demo.DB.Book;
import com.example.demo.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getBooks(String title, Long authorId, String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        if (authorId != null && !authorId.equals(0L)) {
            return bookRepository.findByTitleContainingAndAuthorId(title, authorId, sort);
        } else {
            return bookRepository.findByTitleContaining(title, sort);
        }
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(String title, Long authorId) {
        Author author = authorService.getById(authorId);
        if (author == null) {
            return;
        }
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookRepository.save(book);

    }

    public void updateBook(Long bookId, String title, Long authorId) {

        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return;
        }
        book.setTitle(title);
        Author author = authorService.getById(authorId);
        if (author != null) {
            book.setAuthor(author);
        }

        bookRepository.save(book);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}