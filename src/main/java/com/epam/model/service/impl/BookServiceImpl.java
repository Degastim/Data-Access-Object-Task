package com.epam.model.service.impl;

import com.epam.exception.DaoException;
import com.epam.model.dao.BookDao;
import com.epam.model.dao.impl.BookDaoImpl;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import com.epam.model.service.BookService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger();
    private static final BookDao bookDao = new BookDaoImpl();

    @Override
    public void add(Book book) {
        try {
            bookDao.add(book);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public void delete(Book book) {
        try {
            bookDao.delete(book);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public void updateById(int id, String bookTitle) {
        try {
            bookDao.updateById(id, bookTitle);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public void updateById(int id, Publisher publisher) {
        try {
            bookDao.updateById(id, publisher);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        Optional<Book> result = Optional.empty();
        try {
            result = bookDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Id out of bound");
        }
        return result;
    }

    @Override
    public List<Book> findByBookTitle(String bookTitle) {
        List<Book> bookList = bookDao.findByBookTitle(bookTitle);
        bookList.sort(Comparator.comparing(Book::getPagesNumber));
        logger.log(Level.INFO, "Find list of book by bookTitle");
        return bookList;
    }

    @Override
    public List<Book> findByPublisher(Publisher publisher) {
        List<Book> bookList = bookDao.findByPublisher(publisher);
        bookList.sort(Comparator.comparing(Book::getPagesNumber));
        logger.log(Level.INFO, "Find list of book by publisher");
        return bookList;
    }

    @Override
    public List<Book> findByPagesNumber(int pagesNumber) {
        List<Book> bookList = bookDao.findByPagesNumber(pagesNumber);
        bookList.sort(Comparator.comparing(Book::getPagesNumber));
        logger.log(Level.INFO, "Find list of book by pagesNumber");
        return bookList;
    }

    @Override
    public List<Book> findAll() {
        logger.log(Level.INFO, "Find all book");
        return bookDao.findAll();
    }

    @Override
    public int size() {
        logger.log(Level.INFO, "Find size of bookWareHouse");
        return bookDao.size();
    }
}
