package miniprojects.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private final Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        logger.info("Adding book: {} by {}", book.getTitle(), book.getAuthor());
        books.put(book.getIsbn(), book);
    }

    public Book findByIsbn(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new RuntimeException("Book not found: " + isbn);
        }
        return book;
    }

    public boolean borrowBook(String isbn) {
        Book book = findByIsbn(isbn);
        if (!book.isAvailable()) {
            logger.warn("Book not available for borrowing: {}", isbn);
            return false;
        }
        book.setAvailable(false);
        logger.info("Book borrowed: {}", book.getTitle());
        return true;
    }

    public boolean returnBook(String isbn) {
        Book book = findByIsbn(isbn);
        if (book.isAvailable()) {
            logger.warn("Book was not borrowed: {}", isbn);
            return false;
        }
        book.setAvailable(true);
        logger.info("Book returned: {}", book.getTitle());
        return true;
    }

    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public int getTotalBooks() {
        return books.size();
    }
}
