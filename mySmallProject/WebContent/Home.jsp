<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Welcome To Our Site!</h1>
	<form method = "post" action = "/Control">
		<input type = "hidden" name= "action" value = "login"/>
		<label for = "id">ID : </label>
		<input type = "text" name = "id"/><br/>
		
		<label for = "password">PASSWORD : </label>
		<input type = "text" name = "password"/><br/>
		<input type = "submit">
	</form>
	<a href = "/Control?action=goToReg">go to register page!</a>
</body>
</html>