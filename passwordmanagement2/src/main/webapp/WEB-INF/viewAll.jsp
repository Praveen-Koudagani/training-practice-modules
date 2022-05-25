<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="view.css">
<title>All accounts</title>
</head>
<body>
<form action="" method="post">
<table border=1>
<tr>
 <th>URL </th>
 <th>GROUP</th>
 <th>UserName</th>
 <th>Password</th>
 <th>Actions</th>
</tr>

 <c:forEach items="${accounts}" var="account">
<tr>
 <td> <c:out value="${account.url}" ></c:out> </td>
 <td> <c:out value="${account.groupname}" ></c:out> </td>
 <td> <c:out value="${account.username}" ></c:out> </td>
 <td> <c:out value="${account.password}" ></c:out> </td>
 <td> <a href="#" >Edit</a> | <a href="/Delete2?url=${account.url}">Delete</a></td>
</tr>
</c:forEach>

</table></form> <br>
<a href="/taskmenu">Goto TaskMenu</a>
</body>
</html>