package com.interview.roja.bookstore.rest.controller;

import com.interview.roja.bookstore.data.model.Book;
import com.interview.roja.bookstore.exception.ResourceAlreadyExistsException;
import com.interview.roja.bookstore.exception.ResourceNotFoundException;
import com.interview.roja.bookstore.rest.handler.BookRequest;
import com.interview.roja.bookstore.rest.handler.MessageResponse;
import com.interview.roja.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addNewBook(@NotNull @RequestBody BookRequest bookRequest) {
        MessageResponse response = null;
        try {
            response = bookService.addNewBook(bookRequest);
        } catch (ResourceAlreadyExistsException e) {
            response = new MessageResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateBook(@NotBlank @RequestParam("isbn") String isbn, @NotNull @RequestBody BookRequest bookRequest) {
        MessageResponse response = null;
        try {
            response = bookService.updateBook(isbn, bookRequest);
        } catch (ResourceNotFoundException e) {
            response = new MessageResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<Book>> getBookByTitle(@NotBlank @RequestParam("title") String title) {
        List<Book> books = null;
        try {
            books = bookService.getBooksByTitle(title);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findByAuthorName")
    public ResponseEntity<List<Book>> getBookByAuthor(@NotBlank @RequestParam("authorName") String authorName) {
        List<Book> books = null;
        try {
            books = bookService.getBooksByAuthorName(authorName);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findByIsbn")
    public ResponseEntity<Book> getBookByIsbn(@NotBlank @RequestParam("isbn") String isbn) {
        Book book = null;
        try {
            book = bookService.getBookByIsbn(isbn);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = null;
        try {
            books = bookService.getAllBooks();
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> deleteBook(@NotBlank @RequestParam("isbn") String isbn) {
        MessageResponse response = null;
        try {
            response = bookService.deleteBook(isbn);
        } catch (ResourceNotFoundException e) {
            response = new MessageResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
