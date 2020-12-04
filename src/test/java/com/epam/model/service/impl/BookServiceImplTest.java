package com.epam.model.service.impl;

import com.epam.model.dao.storage.BookWareHouse;
import com.epam.model.entity.Author;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import com.epam.model.service.BookService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class BookServiceImplTest {
    BookService bookService;
    BookWareHouse bookWareHouse;
    Book addBook;

    @BeforeMethod
    public void setUp() {
        bookService = new BookServiceImpl();
        bookWareHouse = BookWareHouse.getInstance();
        addBook = new Book("Dubrovsky", EnumSet.of(Author.PUSHKIN), Publisher.NARODNAYA_VOLYA, 376);
    }

    @Test
    public void testAdd() {
        bookService.add(addBook);
        int expected = 1;
        int actual = bookWareHouse.size();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDelete() {
        bookWareHouse.add(addBook);
        bookService.delete(addBook);
        int expected = 0;
        int actual = bookWareHouse.size();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testUpdateById() {
        bookWareHouse.add(addBook);
        bookService.updateById(0, Publisher.EVENING_MINSK);
        List<Book> actualList = bookWareHouse.getBookList();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book(0, "Dubrovsky", EnumSet.of(Author.PUSHKIN), Publisher.EVENING_MINSK, 376));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testTestUpdateById() {
        bookWareHouse.add(addBook);
        bookService.updateById(0, "12345");
        List<Book> actualList = bookWareHouse.getBookList();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book(0, "12345", EnumSet.of(Author.PUSHKIN), Publisher.NARODNAYA_VOLYA, 376));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindById() {
        bookWareHouse.add(addBook);
        Optional<Book> actual = bookService.findById(0);
        Optional<Book> expected = Optional.of(addBook);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByBookTitle() {
        bookWareHouse.add(addBook);
        bookWareHouse.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        bookWareHouse.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        List<Book> actual = bookService.findByBookTitle("Dubrovsky");
        List<Book> expected = new ArrayList<>();
        expected.add(addBook);
        expected.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublisher() {
        bookWareHouse.add(addBook);
        bookWareHouse.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        bookWareHouse.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        List<Book> actual = bookService.findByPublisher(Publisher.POTPOURRI);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        expected.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByPagesNumber() {
        bookWareHouse.add(addBook);
        bookWareHouse.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        bookWareHouse.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        List<Book> actual = bookService.findByPagesNumber(357);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        expected.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindAll() {
        bookWareHouse.add(addBook);
        bookWareHouse.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        bookWareHouse.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        List<Book> expected = bookWareHouse.getBookList();
        List<Book> actual = bookService.findAll();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSize() {
        bookWareHouse.add(addBook);
        bookWareHouse.add(new Book(1, "Dubrovsky", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        bookWareHouse.add(new Book(2, "1", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.POTPOURRI, 357));
        int actual = bookService.size();
        int expeacted = 3;
        Assert.assertEquals(actual, expeacted);
    }
}