package com.myke.library.service;

import com.myke.library.model.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    List<Book> getBooks();

    Book getBookById(String id);

    Book saveBook(Book book);

    Book udpateBook(String id, Book book);

    Book deleteBook(String id);

    Book borrowBook(String id);

    Book returnBook(String id);

    Book findByName(String name);

}
