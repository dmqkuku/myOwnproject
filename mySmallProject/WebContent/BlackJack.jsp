<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import = "java.util.HashMap" import  = "java.util.Map" import = "model.Card" 
    
    %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<c:url var = "ConnectionErrorPage" value = "${pageContext.request.contextPath }/Error.jsp"/>
	<%
		Object userObj = request.getAttribute("userHand");
		Object dealerObj = request.getAttribute("dealerHand");
		if( !(userObj instanceof Map<?, ?>) || !(dealerObj instanceof Map<?, ?>)){
			response.sendRedirect("${ConnectionErrorPage}");
		}else{
			HashMap<String, Card> userHand = (HashMap<String, Card>) userObj;
			HashMap<String, Card> dealerHand = (HashMap<String, Card>) dealerObj;
		}
	%>
	<jsp:useBean id = "map" class = "java.util.HashMap"/>
	<jsp:setProperty name = "map" property = "userHand" value = "${userHand }" />
	<jsp:setProperty name= "map" property = "dealerHand" value = "${dealerHand }"/>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello This is the BlackJack</h1>
	<section>
		
	</section>
	
	<Section>
		
		
		
		<form action = "/Control" method = "get">
			<input type = "submit" value = "Hit"/>
		</form>
		<form action = "/Control" method = "get">
			<input type = "submit" value = "Stay"/>
		</form>
	</Section>
</body>
</html>