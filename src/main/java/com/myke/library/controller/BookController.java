package com.myke.library.controller;

import com.myke.library.model.Book;
import com.myke.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    private List<Book> getBooks() {
        return this.bookService.getBooks();
    }
    @GetMapping(path = "/{id}")
    private Book findBookId(@PathVariable("id") String id) {
        return this.bookService.getBookById(id);
    }

    @PostMapping
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
    private String borrowBook(@PathVariable("id") String id) {
        return this.bookService.borrowBook(id);
    }

    @PutMapping(path = "/{id}/return")
    private ResponseEntity<String> returnBook(@PathVariable("id") String id) {
        var book =  this.bookService.getBookById(id);
        if(!book.getBorrowed()) {
           return new ResponseEntity<>("No se puede devolver el libro porque no está prestado", HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.returnBook(id);
            return new ResponseEntity<>(
                    "Libro devuelto satisfactoriamente",
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(path = "/search")
    private Book findBookName(@RequestParam("name") String name) {
        return this.bookService.findByName(name);
    }

    @GetMapping(path = "/category/{category}")
    private List<Book> categoryRecommendations(@PathVariable("category") String category) {
        return this.bookService.findCategory(category);
    }
}
