<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%-- Directive JSP pour définir le type de contenu
        (HTML UTF-8) --%>
        <html>

        <head>

            <link rel="stylesheet" href="css/style.css">

            <title>Ajouter un livre</title>
        </head>

        <body>
            <h1>Ajouter un livre</h1>

            <form action="books" method="post">

                <label for="title">Titre :</label><br>
                <input type="text" id="title" name="title" required><br><br>


                <label for="author">Auteur :</label><br>
                <input type="text" id="author" name="author" required><br><br>

                <label for="year">Année :</label><br>
                <input type="number" id="year" name="year" min="0"><br><br>


                <button type="submit">Ajouter</button>
            </form>

        </body>

        </html>