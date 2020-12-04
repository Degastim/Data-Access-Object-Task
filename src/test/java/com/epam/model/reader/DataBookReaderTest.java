package com.epam.model.reader;

import com.epam.model.entity.Author;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DataBookReaderTest {
    DataBookReader dataBookReader;
    final String filePath = "src/main/resources/data/books.txt";

    @BeforeMethod
    public void setUp() {
        dataBookReader = new DataBookReader();
    }

    @Test
    public void testCreatAllBook() {
        List<Book> actual = dataBookReader.readAllBook(filePath);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(0, "TheLordOfTheRings", EnumSet.of(Author.TOLKIEN), Publisher.WHITE_WIND, 1000));
        expected.add(new Book(1, "Viy", EnumSet.of(Author.GOGOL), Publisher.SIR_VIT, 327));
        expected.add(new Book(2, "ChristmasEve", EnumSet.of(Author.GOGOL), Publisher.SIR_VIT, 544));
        expected.add(new Book(3, "TarasBulba", EnumSet.of(Author.GOGOL), Publisher.SIR_VIT, 789));
        expected.add(new Book(4, "Silmarillion", EnumSet.of(Author.TOLKIEN), Publisher.POTPOURRI, 578));
        expected.add(new Book(5, "AdventuresOfTomSawyer", EnumSet.of(Author.TWAIN), Publisher.NARODNAYA_VOLYA, 300));
        expected.add(new Book(6, "TheAdventuresOfHuckleberryFinn", EnumSet.of(Author.TWAIN), Publisher.TALE, 572));
        expected.add(new Book(7, "Idiot", EnumSet.of(Author.DOSTOEVSKY), Publisher.TALE, 146));
        expected.add(new Book(8, "CrimeAndPunishment", EnumSet.of(Author.DOSTOEVSKY), Publisher.EVENING_MINSK, 900));
        expected.add(new Book(9, "ALittlePrince", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.EVENING_MINSK, 357));
        expected.add(new Book(10, "WarPilot", EnumSet.of(Author.SAINYT_EXUPERY), Publisher.WHITE_WIND, 754));
        expected.add(new Book(11, "Dubrovsky", EnumSet.of(Author.PUSHKIN), Publisher.NARODNAYA_VOLYA, 376));
        Assert.assertEquals(actual, expected);
    }
}