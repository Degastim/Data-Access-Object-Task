package com.epam.model.service;

import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import java.util.List;
import java.util.Optional;

public interface BookService {

    void add(Book book);

    void delete(Book book);

    boolean updateById(int id, String bookTitle);

    boolean updateById(int id, Publisher publisher);

    Optional<Book> findById(int id);

    List<Book> findByBookTitle(String bookTitle);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByPagesNumber(int pagesNumber);

    List<Book> findAll();

    int size();
}
