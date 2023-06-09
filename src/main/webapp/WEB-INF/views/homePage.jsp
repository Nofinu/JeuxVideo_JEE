<%--
  Created by IntelliJ IDEA.
  User: léo
  Date: 09/06/2023
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>home page</h1>
        <c:forEach items="${jeux}" var="jeu">
            <div>${jeu.getName()} <a href="jeux?type=edit&id=${jeu.getId()}">editer</a></div>
        </c:forEach>
        <a href="jeux?type=add">ajouter un jeu</a>


</body>
</html>
