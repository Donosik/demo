package com.example.demo.DB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Author {

    private static final Logger logger = LoggerFactory.getLogger(Author.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> books;

    @PostLoad
    public void lastLookedFromDb()
    {
        logger.info("Last looked from db");
    }

    @PostUpdate
    public void changedAuthor()
    {
        logger.info("Changed author {}", id);
    }
}