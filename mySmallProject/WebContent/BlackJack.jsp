<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import = "java.util.HashMap" import  = "java.util.Map" import = "model.Card" 
    
    %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<c:url var = "ConnectionErrorPage" value = "${pageContext.request.contextPath }/Error.jsp"/>
	<%
		Object userObj = request.getAttribute("userHand");
		Object dealerObj = request.getAttribute("dealerHand");
		
	
		HashMap<String, Card> userHand = (HashMap<String, Card>) userObj;
		HashMap<String, Card> dealerHand = (HashMap<String, Card>) dealerObj;
		
	%>
	<jsp:useBean id = "map" class = "model.HashMapBean" scope = "page"/>
	<jsp:setProperty name = "map" property = "userHand" value = "<%=userHand %>" />
	<jsp:setProperty name = "map" property = "dealerHand" value = "<%=dealerHand %>"/>
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
		<table>
			<tr>
				<th>Hand index is</th>
				<th>Your card is</th>
			</tr>
			<c:forEach var = "idx" begin = "1" end = "${userHand.size() }" step = "1">
			<c:set var = "handIdx" value = "Hand${idx}"/>
			<tr>
				<td>${idx} 번째 손패!</td>
				<td><%= userHand.get("${handIdx}") %></td>
			</tr>
			</c:forEach>
		</table>
		
		
		<form action = "/Control" method = "get">
			<input type = "submit" value = "Hit"/>
		</form>
		<form action = "/Control" method = "get">
			<input type = "submit" value = "Stay"/>
		</form>
	</Section>
</body>
</html>