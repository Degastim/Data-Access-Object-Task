package com.epam.model.dao;

import com.epam.exception.DaoException;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import java.util.List;
import java.util.Optional;

public interface BookDao {

    void add(Book book) throws DaoException;

    void delete(Book book) throws DaoException;

    void updateById(int id, String BookTitle) throws DaoException;

    void updateById(int id, Publisher publisher) throws DaoException;

    Optional<Book> findById(int id) throws DaoException;

    List<Book> findAll();

    List<Book> findByBookTitle(String bookTitle);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByPagesNumber(int pagesNumber);

    int size();
}
