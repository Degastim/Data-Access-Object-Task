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
    public boolean updateById(int id, String bookTitle) {
        boolean result = bookDao.updateById(id, bookTitle);
        if (result) {
            logger.log(Level.INFO, "Successful update by bookTitle");
        } else {
            logger.log(Level.INFO, "Unsuccessful update by bookTitle");
        }
        return result;
    }

    @Override
    public boolean updateById(int id, Publisher publisher) {
        boolean result = bookDao.updateById(id, publisher);
        if (result) {
            logger.log(Level.INFO, "Successful update by publisher");
        } else {
            logger.log(Level.INFO, "Unsuccessful update by publisher");
        }
        return result;
    }

    @Override
    public Optional<Book> findById(int id) {
        Optional<Book> result;
        try {
            Book book = bookDao.findById(id);
            result = Optional.of(book);
            return result;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Id out of bound");
            result = Optional.empty();
            return result;
        }
    }

    @Override
    public List<Book> findByBookTitle(String bookTitle) {
        List<Book> bookList = bookDao.findByBookTitle(bookTitle);
        bookList.sort(Comparator.comparing(Book::getBookTitle));
        logger.log(Level.INFO, "Find list of book by bookTitle");
        return bookList;
    }

    @Override
    public List<Book> findByPublisher(Publisher publisher) {
        List<Book> bookList = bookDao.findByPublisher(publisher);
        bookList.sort(Comparator.comparing(Book::getPublisher));
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
