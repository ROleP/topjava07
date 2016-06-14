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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<h3>Meal list</h3>
<a href="meals?action=create">Add Meal</a>
<table>
    <thead>
    <tr>
        <th>Description:</th>
        <th>Calories:</th>
        <th>Date</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${food}">
        <tr class="${meal.exceed == true ? 'foodMuch' : 'foodOk'}">
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><c:out value="${f:dateConverter(meal.dateTime, 'dd-MM-yyyy HH:mm')}"/></td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a> </td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
