package com.interview.roja.bookstore.data.repository;

import com.interview.roja.bookstore.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);

    List<Book> findByTitle(String title);
}
