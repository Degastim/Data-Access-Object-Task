package com.epam.model.entity;

import com.epam.util.IdGenerator;
import java.util.EnumSet;

public class Book {
    private long bookId = IdGenerator.getCurrentBookId();
    private String bookTitle;
    private EnumSet<Author> authors = EnumSet.noneOf(Author.class);
    private Publisher publisher;
    private int pagesNumber;

    public Book(String bookTitle, EnumSet<Author> authors, Publisher publisher, int pagesNumber) {
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.pagesNumber = pagesNumber;
    }

    public Book(long bookId, String bookTitle, EnumSet<Author> authors, Publisher publisher, int pagesNumber) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.pagesNumber = pagesNumber;
    }

    public Book() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public EnumSet getAuthors() {
        return EnumSet.copyOf(authors);
    }

    public void setAuthors(EnumSet<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    @Override
    public int hashCode() {
        int result = (int) bookId;
        result = 31 * result + (bookTitle != null ? bookTitle.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + pagesNumber;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        if (bookId != book.getBookId()) return false;
        if (pagesNumber != book.getPagesNumber()) return false;
        if (bookTitle != null ? !bookTitle.equals(book.getBookTitle()) : book.getBookTitle() != null) return false;
        if (authors != null ? !authors.equals(book.getAuthors()) : book.getAuthors() != null) return false;
        return publisher != null ? publisher.equals(book.getPublisher()) : book.getPublisher() == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookId=").append(bookId);
        sb.append(", bookTitle='").append(bookTitle).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", publisher=").append(publisher);
        sb.append(", pagesNumber=").append(pagesNumber);
        sb.append('}');
        return sb.toString();
    }
}
