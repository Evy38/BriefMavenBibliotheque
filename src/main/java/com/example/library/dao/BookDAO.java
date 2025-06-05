package com.example.library.dao; // 🧭 Declare que cette classe est dans le package "dao"

import java.sql.Connection; // 📦 Importe tout ce qu’il faut pour se connecter à une base SQL (JDBC)
import java.sql.DriverManager; // Pour stocker la liste des livres
import java.sql.PreparedStatement;
import java.sql.ResultSet; // 👈 On importe notre modèle Book pour pouvoir l’utiliser
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.library.model.Book;

public class BookDAO {

    // 🧪 Méthode privée pour obtenir une connexion à la base SQLite
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found.", e);
        }

        // Connexion à la base de données
        String url = "jdbc:sqlite:library.db";
        Connection conn = DriverManager.getConnection(url);

        return conn;
    }

    // 📚 Méthode pour lister tous les livres dans la base de données
    public List<Book> listAllBooks() {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books"; // 🔍 Requête SQL pour récupérer tous les livres

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // 🔁 Parcourt les résultats et crée des objets Book
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");

                Book book = new Book(id, title, author, year);
                books.add(book); // Ajoute le livre à la liste
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }

        return books; // 📤 Retourne la liste complète des livres
    }

    // ➕ Méthode pour ajouter un livre dans la base
    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES(?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getBookTitle());
            pstmt.setString(2, book.getBookAuthor());
            pstmt.setInt(3, book.getBookYear());

            pstmt.executeUpdate(); // ✅ Exécute l’insertion

        } catch (SQLException e) {
           System.err.println("Erreur SQL : " + e.getMessage());

        }
    }

    public BookDAO() {
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT NOT NULL," +
                     "author TEXT NOT NULL," +
                     "year INTEGER)";
        stmt.execute(sql);
    } catch (SQLException e) {
        System.err.println("Erreur SQL lors de la création de la table : " + e.getMessage());
    }
}
}
