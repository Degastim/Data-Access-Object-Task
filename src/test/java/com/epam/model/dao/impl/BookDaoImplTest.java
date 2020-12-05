package com.epam.model.dao.impl;

import com.epam.exception.DaoException;
import com.epam.model.dao.BookDao;
import com.epam.model.dao.storage.BookWareHouse;
import com.epam.model.entity.Author;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BookDaoImplTest {
    BookDao bookDao;
    BookWareHouse bookWareHouse;

    @BeforeMethod
    public void setUp() throws DaoException {
        bookDao = new BookDaoImpl();
        bookDao.add(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        bookWareHouse = BookWareHouse.getInstance();
    }

    @Test
    public void testAdd() throws DaoException {
        bookDao.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        int actual = bookWareHouse.size();
        int expected = 2;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDelete() throws DaoException {
        bookDao.delete(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        int actual = bookWareHouse.size();
        int expected = 0;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testUpdateById() {
        bookDao.updateById(0, "1234");
        List<Book> actual = bookWareHouse.getBookList();
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "1234", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testTestUpdateById() {
        bookDao.updateById(0, Publisher.EVENING_MINSK);
        List<Book> actual = bookWareHouse.getBookList();
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.EVENING_MINSK, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindById() throws DaoException {
        Book actual = bookDao.findById(0);
        Book expected = new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByBookTitle() {
        List<Book> actual = bookDao.findByBookTitle("Dubrovsky");
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublisher() {
        List<Book> actual = bookDao.findByPublisher(Publisher.POTPOURRI);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByPagesNumber() {
        List<Book> actual = bookDao.findByPagesNumber(357);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindAll() {
        List<Book> actual = bookDao.findAll();
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSize() {
        int actual = bookDao.size();
        int expected = 1;
        Assert.assertEquals(actual, expected);
    }
}