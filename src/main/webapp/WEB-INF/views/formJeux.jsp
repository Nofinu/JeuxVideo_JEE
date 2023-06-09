<%--
  Created by IntelliJ IDEA.
  User: léo
  Date: 09/06/2023
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="jeux?type=${t}" method="post">
  <label for="name">nom :</label>
  <input type="text" name="name" id="name" value="${jeu.getName()}" required>

  <label for="score">note :</label>
  <input type="number" name="score" id="score" min="0" max="5" step="0.1"  value="${jeu.getScore()}" required>

  <label for="date">date de sortie :</label>
  <input type="date" name="date" id="date" value="${jeu.getReleaseDate()}" required>

  <label for="review">review :</label>
  <textarea name="review" id="review" rows="10" cols="50" style="resize: none" required >${jeu.getReview()}</textarea>

  <label for="types">type :</label>
  <input type="text" name="types" id="types" value="${jeu.getType()}" required>

  <div>
    <button>${t}</button>
  </div>
</form>
</body>
</html>
