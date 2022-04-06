package com.myke.library.repository;

import com.myke.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByName(String name);
    Optional<List<Book>> findByCategory(String category);
}
