package com.interview.roja.bookstore.service;

import com.interview.roja.bookstore.data.model.Book;
import com.interview.roja.bookstore.exception.ResourceAlreadyExistsException;
import com.interview.roja.bookstore.exception.ResourceNotFoundException;
import com.interview.roja.bookstore.rest.handler.BookRequest;
import com.interview.roja.bookstore.rest.handler.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    MessageResponse addNewBook(BookRequest bookRequest) throws ResourceAlreadyExistsException;

    MessageResponse updateBook(String isbn, BookRequest bookRequest) throws ResourceNotFoundException;

    List<Book> getBooksByTitle(String title) throws ResourceNotFoundException;

    List<Book> getBooksByAuthorName(String authorName) throws ResourceNotFoundException;

    Book getBookByIsbn(String isbn) throws ResourceNotFoundException;

    List<Book> getAllBooks() throws ResourceNotFoundException;

    MessageResponse deleteBook(String isbn) throws ResourceNotFoundException;
}
