package com.example.library.controller;

import java.io.IOException;
import java.util.List;

import com.example.library.dao.BookDAO;
import com.example.library.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books") // 🔗 URL mappée au formulaire de index.jsp
public class BookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    // 📥 doPost() : appelé quand le formulaire est soumis (method="post")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    
            throws ServletException, IOException {

        // 🧾 Récupère les données du formulaire
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("year"));

        // 📦 Crée un objet Book
        Book book = new Book(0, title, author, year);

        // 💾 Sauvegarde le livre via le DAO
        bookDAO.addBook(book);

        // 🔁 Redirige vers doGet() pour afficher la liste mise à jour
        response.sendRedirect("books"); // appel implicite à doGet()
        System.out.println("📥 Formulaire reçu !");

    }

    // 📤 doGet() : affiche tous les livres (appelé après ajout ou directement via URL)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 📚 Récupère tous les livres
        List<Book> books = bookDAO.listAllBooks();

        // 📩 Envoie la liste à la JSP
        request.setAttribute("books", books);
        request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
    }
}
