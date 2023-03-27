package com.interview.roja.bookstore.rest.handler;

import com.interview.roja.bookstore.data.model.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class BookRequest {

    @NotBlank
    @NotNull
    private String isbn;

    @NotBlank
    @NotNull
    private String title;

    private List<Author> authors;

    private Integer publishedYear;

    private Double price;

    private String genre;

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
        this.authors = authors;
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
        BookRequest that = (BookRequest) o;
        return isbn.equals(that.isbn) && title.equals(that.title) && authors.equals(that.authors) && Objects.equals(publishedYear, that.publishedYear) && Objects.equals(price, that.price) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authors, publishedYear, price, genre);
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                '}';
    }
}
