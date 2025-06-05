package com.example.library.dao;

import com.example.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookDAOTest {

    private BookDAO bookDAO;

    @BeforeEach
    public void setUp() {
        bookDAO = new BookDAO();
    }

    @Test
    public void testAddAndListBooks() {
        Book book = new Book(0, "TitreTest", "AuteurTest", 2025);
        bookDAO.addBook(book);

        List<Book> books = bookDAO.listAllBooks();
        boolean found = books.stream().anyMatch(b ->
            b.getBookTitle().equals("TitreTest") &&
            b.getBookAuthor().equals("AuteurTest")
        );

        assertTrue(found, "Le livre ajouté devrait être trouvé dans la liste.");
    }
}
