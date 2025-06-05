<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.library.model.Book" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">

    <title>Liste des livres</title>
</head>
<body>
    <h1>Liste des livres</h1>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Année</th>
            </tr>
            <% for (Book b : books) { %>
                <tr>
                    <td><%= b.getBookId() %></td>
                    <td><%= b.getBookTitle() %></td>
                    <td><%= b.getBookAuthor() %></td>
                    <td><%= b.getBookYear() %></td>
                </tr>
            <% } %>
        </table>
    <% 
        } else {
    %>
        <p>Aucun livre enregistré.</p>
    <% } %>

    <br><br>
    <a href="index.jsp">← Retour au formulaire</a>

</body>
</html>
