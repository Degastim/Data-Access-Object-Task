package com.epam.model.dao.impl;

import com.epam.exception.DaoException;
import com.epam.model.dao.BookDao;
import com.epam.model.dao.storage.BookWareHouse;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;

import java.util.ArrayList;
import java.util.List;
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
    public boolean updateById(int id, String bookTitle) {
        try {
            Book updateBook = wareHouse.get(id);
            Book newBook = new Book(updateBook.getBookId(), bookTitle, updateBook.getAuthors(), updateBook.getPublisher(), updateBook.getPagesNumber());
            wareHouse.update(id, newBook);
            return true;
        } catch (DaoException e) {
            return false;
        }
    }

    @Override
    public boolean updateById(int id, Publisher publisher) {
        try {
            Book updateBook = wareHouse.get(id);
            Book newBook = new Book(updateBook.getBookId(), updateBook.getBookTitle(), updateBook.getAuthors(), publisher, updateBook.getPagesNumber());
            wareHouse.update(id, newBook);
            return true;
        } catch (DaoException e) {
            return false;
        }
    }

    @Override
    public Book findById(int id) throws DaoException {
        return wareHouse.get(id);
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
