package miniprojects;

import miniprojects.library.Book;
import miniprojects.library.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Library Service Tests")
class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryService();
        service.addBook(new Book("ISBN-001", "Java Programming", "Author A"));
        service.addBook(new Book("ISBN-002", "Design Patterns", "Author B"));
    }

    @Test
    void testAddBook() {
        assertEquals(2, service.getTotalBooks());
    }

    @Test
    void testFindByIsbn() {
        assertEquals("Java Programming", service.findByIsbn("ISBN-001").getTitle());
    }

    @Test
    void testBorrowBook() {
        assertTrue(service.borrowBook("ISBN-001"));
        assertFalse(service.findByIsbn("ISBN-001").isAvailable());
    }

    @Test
    void testBorrowAlreadyBorrowed() {
        service.borrowBook("ISBN-001");
        assertFalse(service.borrowBook("ISBN-001"));
    }

    @Test
    void testReturnBook() {
        service.borrowBook("ISBN-001");
        assertTrue(service.returnBook("ISBN-001"));
        assertTrue(service.findByIsbn("ISBN-001").isAvailable());
    }

    @Test
    void testAvailableBooks() {
        service.borrowBook("ISBN-001");
        assertEquals(1, service.getAvailableBooks().size());
    }
}
