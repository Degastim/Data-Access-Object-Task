package com.epam.model.dao.storage;

import com.epam.exception.DaoException;
import com.epam.model.entity.Book;
import java.util.ArrayList;
import java.util.List;

public class BookWareHouse {
    private static final BookWareHouse instance = new BookWareHouse();
    private final List<Book> bookList = new ArrayList<>();

    private BookWareHouse() {
    }

    public boolean contains(Book book) {
        return bookList.contains(book);
    }

    public static BookWareHouse getInstance() {
        return instance;
    }

    public void add(Book book) {
        bookList.add(book);
    }

    public Book get(int index) throws DaoException {
        if (index < 0 || index > bookList.size()) {
            throw new DaoException("Index out of bound");
        }
        return bookList.get(index);
    }

    public List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }

    public void update(int index, Book book) throws DaoException {
        if (index >= bookList.size()) {
            throw new DaoException("index out of bound,index=" + index);
        }
        bookList.remove(index);
        bookList.add(index, book);
    }

    public void delete(Book book) {
        bookList.remove(book);
    }

    public int size() {
        return bookList.size();
    }
}
