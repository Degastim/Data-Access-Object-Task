package com.epam.model.reader;

import com.epam.model.entity.Author;
import com.epam.model.entity.Book;
import com.epam.model.entity.Publisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class DataBookReader {
    private static final Logger logger = LogManager.getLogger();

    public List<Book> readAllBook(String filepath) {
        Path path = Path.of(filepath);
        List<Book> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String StringBook = scanner.nextLine();
                String[] rawBook = StringBook.split(" ");
                String bookTitle = rawBook[0];
                int pagesNumber = Integer.parseInt(rawBook[3]);
                String[] rawAuthors = rawBook[1].split(",");
                EnumSet<Author> authors = EnumSet.noneOf(Author.class);
                for (String i : rawAuthors) {
                    authors.add(Author.valueOf(i.toUpperCase()));
                }
                Publisher publisher = Publisher.valueOf(rawBook[2].toUpperCase());
                Book book = new Book(bookTitle, authors, publisher, pagesNumber);
                result.add(book);
            }
        } catch (IOException e) {
            logger.error("Error in create book: ", e);
        }
        return result;
    }
}
