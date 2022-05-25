<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="createAccount.css">
<title>Account Operations</title>
</head>
<body>
<center>
<div class="mainblock">
<form name="group" method="post" action="/updateGroup2">
		<div align="center">
			<h3>Enter group name:</h3>
			<input type="text" name="group"/>
			 <br>
			<h3>Enter new group name:</h3>
			<input type="text" name="newgroup"/>
			 <br>
			 <br>
			<p><input type="submit" value="update" /><br><br><a href="/taskmenu" >Goto TaskMenu</a></p>
		</div>
	</form></div></center>
</body>
</html>