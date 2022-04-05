package com.myke.library.controller;

import com.myke.library.model.Book;
import com.myke.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/")
    private List<Book> getBooks() {
        return this.bookService.getBooks();
    }
    @GetMapping(path = "/{id}")
    private Book findBookId(@PathVariable("id") String id) {
        return this.bookService.getBookById(id);
    }

    @PostMapping(path = "/")
    private Book saveBook(@RequestBody Book book) {
        return this.bookService.saveBook(book);
    }

    @PutMapping(path = "/{id}")
    private Book updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        return this.bookService.udpateBook(id, book);
    }

    @DeleteMapping(path = "/{id}/delete")
    private Book deleteBook(@PathVariable("id") String id) {
        return this.bookService.deleteBook(id);
    }

    @PutMapping(path = "/{id}/borrow")
    private Book borrowBook(@PathVariable("id") String id) {
        return this.bookService.borrowBook(id);
    }

    @PutMapping(path = "/{id}/return")
    private Book returnBook(@PathVariable("id") String id) {
        return this.bookService.returnBook(id);
    }

    @GetMapping(path = "/name/{name}")
    private Book findBookName(@PathVariable("name") String name) {
        return this.bookService.findByName(name);
    }
}
