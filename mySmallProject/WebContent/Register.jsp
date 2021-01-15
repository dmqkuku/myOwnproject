<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>This is the Register Page</h1>
	<form action = "/ControlMySql" method = "post">
		<input type = "hidden" name = "action" value = "register"/>
		<label for = "id">ID : </label>
		<input type = "text" name = "id"/><br/>
		
		<label for = "password">PASSWORD : </label>
		<input type = "text" name = "password"/><br/>
		
		<label for = "name">NAME : </label>
		<input type = "text" name = "name"/><br/>
		
		<label for = "email">EMAIL : </label>
		<input type = "text" name = "email"/><br/>
		<input type ="submit">submit!</input>
	</form>
</body>
</html>