package com.epam.model.dao.impl;

import com.epam.exception.DaoException;
import com.epam.model.dao.BookDao;
import com.epam.model.dao.storage.BookWareHouse;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {
    private static final BookWareHouse wareHouse = BookWareHouse.getInstance();

    @Override
    public void add(Book book) throws DaoException {
        if (wareHouse.contains(book)) {
            throw new DaoException("This book is already there");
        }
        wareHouse.add(book);
    }

    @Override
    public void delete(Book book) throws DaoException {
        if (!wareHouse.contains(book)) {
            throw new DaoException(book + " not in the BookWareHouse");
        }
        wareHouse.delete(book);
    }

    @Override
    public void updateById(int id, String bookTitle) throws DaoException {
        Book updateBook = wareHouse.get(id);
        Book book = new Book(updateBook.getBookId(), bookTitle, updateBook.getAuthors(), updateBook.getPublisher(), updateBook.getPagesNumber());
        wareHouse.update(id, book);
    }

    @Override
    public void updateById(int id, Publisher publisher) throws DaoException {
        Book updateBook = wareHouse.get(id);
        Book book = new Book(updateBook.getBookId(), updateBook.getBookTitle(), updateBook.getAuthors(), publisher, updateBook.getPagesNumber());
        wareHouse.update(id, book);
    }

    @Override
    public Optional<Book> findById(int id) throws DaoException {
        Book resultBook = wareHouse.get(id);
        return Optional.of(resultBook);
    }

    @Override
    public List<Book> findByBookTitle(String bookTitle) {
        return wareHouse.getBookList().stream().filter(value -> value.getBookTitle().equals(bookTitle)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByPublisher(Publisher publisher) {
        return wareHouse.getBookList().stream().filter(value -> value.getPublisher() == publisher).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByPagesNumber(int pagesNumber) {
        return wareHouse.getBookList().stream().filter(value -> value.getPagesNumber() == pagesNumber).collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(wareHouse.getBookList());
    }

    @Override
    public int size() {
        return wareHouse.size();
    }
}
