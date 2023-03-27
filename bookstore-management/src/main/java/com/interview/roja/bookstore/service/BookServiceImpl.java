package com.interview.roja.bookstore.service;

import com.interview.roja.bookstore.data.model.Author;
import com.interview.roja.bookstore.data.model.Book;
import com.interview.roja.bookstore.data.repository.AuthorRepository;
import com.interview.roja.bookstore.exception.ResourceAlreadyExistsException;
import com.interview.roja.bookstore.rest.handler.BookRequest;
import com.interview.roja.bookstore.rest.handler.MessageResponse;
import com.interview.roja.bookstore.exception.ResourceNotFoundException;
import com.interview.roja.bookstore.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public MessageResponse addNewBook(BookRequest bookRequest) throws ResourceAlreadyExistsException {
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setPublishedYear(bookRequest.getPublishedYear());
        book.setPrice(bookRequest.getPrice());
        book.setGenre(bookRequest.getGenre());
        if (!CollectionUtils.isEmpty(bookRequest.getAuthors())) {
            List<Author> authors = new ArrayList<>();
            bookRequest.getAuthors().parallelStream().forEach(a -> {
                Author author = new Author();
                author.setName(a.getName());
                author.setBirthday(a.getBirthday());
                author.setBook(book);
                authors.add(author);
            });
            book.setAuthors(authors);
        }
        try {
            bookRepository.save(book);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException("Book", "isbn", bookRequest.getIsbn());
        }
        return new MessageResponse("New book saved successfully!!");
    }

    @Override
    public MessageResponse updateBook(String isbn, BookRequest bookRequest) throws ResourceNotFoundException {
        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));
        if (!book.isPresent()) {
            throw new ResourceNotFoundException("Book", "isbn", isbn);
        } else {
            if (bookRequest.getIsbn() != null) {
                book.get().setIsbn(bookRequest.getIsbn());
            }
            if (bookRequest.getTitle() != null) {
                book.get().setTitle(bookRequest.getTitle());
            }
            if (bookRequest.getPublishedYear() != null) {
                book.get().setPublishedYear(bookRequest.getPublishedYear());
            }
            if (bookRequest.getPrice() != null) {
                book.get().setPrice(bookRequest.getPrice());
            }
            if (bookRequest.getGenre() != null) {
                book.get().setGenre(bookRequest.getGenre());
            }
            if (bookRequest.getAuthors() != null) {
                if (CollectionUtils.isEmpty(bookRequest.getAuthors())) {
                    book.get().clearAuthors();
                } else {
                    List<Author> authors = new ArrayList<>();
                    bookRequest.getAuthors().parallelStream().forEach(a -> {
                        Author author = new Author();
                        author.setName(a.getName());
                        author.setBirthday(a.getBirthday());
                        author.setBook(book.get());
                        authors.add(author);
                    });
                    book.get().setAuthors(authors);
                }
            }
            bookRepository.save(book.get());
        }
        return new MessageResponse(isbn + " book updated successfully!!");
    }

    @Override
    public List<Book> getBooksByTitle(String title) throws ResourceNotFoundException {
        List<Book> books = bookRepository.findByTitle(title);
        if (CollectionUtils.isEmpty(books)) {
            throw new ResourceNotFoundException("Books", "title", title);
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthorName(String authorName) throws ResourceNotFoundException {
        List<Author> authors = authorRepository.findByName(authorName);
        if (CollectionUtils.isEmpty(authors)) {
            throw new ResourceNotFoundException("Books", "authorName", authorName);
        }
        List<Book> books = new ArrayList<>();
        authors.parallelStream().forEach(a -> {
            books.add(a.getBook());
        });
        return books;
    }

    @Override
    public Book getBookByIsbn(String isbn) throws ResourceNotFoundException {
        return Optional.ofNullable(bookRepository.findByIsbn(isbn))
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));
    }

    @Override
    public List<Book> getAllBooks() throws ResourceNotFoundException {
        Optional<List<Book>> books = Optional.ofNullable(bookRepository.findAll());
        if (!books.isPresent()) {
            throw new ResourceNotFoundException("Books");
        }
        return books.get();
    }

    @Override
    public MessageResponse deleteBook(String isbn) throws ResourceNotFoundException {
        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));
        if (!book.isPresent()) {
            throw new ResourceNotFoundException("Book", "isbn", isbn);
        } else {
            bookRepository.deleteById(book.get().getId());
        }
        return new MessageResponse(isbn + " book deleted successfully!!");
    }
}
