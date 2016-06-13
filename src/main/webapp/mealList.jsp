<%--
  Created by IntelliJ IDEA.
  User: rolep
  Date: 09/06/16
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://localhost/functions" prefix="f" %>
<html>
<head>
    <style>
        tr.foodOk td { color: green; }
        tr.foodmuch td { color: red; }
    </style>
    <title>Meal List</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <thead>
    <tr>
        <td>Description:</td>
        <td>Calories:</td>
        <td>Date</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${food}">
        <tr class="${meal.exceed == true ? 'foodMuch' : 'foodOk'}">
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><c:out value="${f:dateConverter(meal.dateTime, 'dd-MM-yyyy HH:mm')}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
