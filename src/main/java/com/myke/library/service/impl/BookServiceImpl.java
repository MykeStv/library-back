package com.myke.library.service.impl;

import com.myke.library.model.Book;
import com.myke.library.repository.BookRepository;
import com.myke.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book udpateBook(String id, Book book) {
        var bookData = this.bookRepository.findById(id).orElseThrow();

        book.setId(bookData.getId());

        return saveBook(book);
    }

    @Override
    public Book deleteBook(String id) {
        var bookData = this.bookRepository.findById(id).orElseThrow();
        this.bookRepository.deleteById(id);

        return bookData;
    }

    @Override
    public String borrowBook(String id) {

        var bookData = this.bookRepository.findById(id).orElseThrow();
        if (!bookData.getBorrowed()) {
            bookData.setBorrowed(true);
            bookData.setDateBorrewed(LocalDate.now());
            var book = saveBook(bookData);
            return "Libro: " + book.getName() +" prestado!";
        } else {
            return "No se puede prestar el libro: '"+ bookData.getName() +"'. Fue prestado" +
                    " el día: "+ bookData.getDateBorrewed();
        }

    }

    @Override
    public Book returnBook(String id) {
        var bookData = this.bookRepository.findById(id).orElseThrow();

        if (bookData.getBorrowed()) {
            bookData.setBorrowed(false);
            bookData.setDateBorrewed(null);
            return saveBook(bookData);
        } else {
            return bookData;
        }
    }

    @Override
    public Book findByName(String name) {
        return this.bookRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<Book> findCategory(String category) {
        return this.bookRepository.findAll().stream().filter(b -> b.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}
