package com.interview.roja.bookstore.data.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`book`",
        uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeq")
    private Long id;

    @NotNull
    @NotBlank
    private String isbn;

    @NotNull
    @NotBlank
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors = new ArrayList<>();

    private Integer publishedYear;

    private Double price;

    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors.clear();
        this.authors.addAll(authors);
    }

    public void clearAuthors() {
        this.authors.clear();
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.setBook(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.setBook(null);
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && isbn.equals(book.isbn) && title.equals(book.title) && authors.equals(book.authors) && Objects.equals(publishedYear, book.publishedYear) && Objects.equals(price, book.price) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, authors, publishedYear, price, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                '}';
    }
}
