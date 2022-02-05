<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Member m = (Member) request.getAttribute("Member");
	%>

	<form
		action="<%=request.getContextPath()%>/control?num=<%=m.getNum()%>"
		method="post">
		<input type="hidden" name="type" value="edit"> id : <input
			type="text" name="id" value="<%=m.getId()%>" readonly><br>
		email : <input type="text" name="email" value="<%=m.getEmail()%>"><br>
		pwd : <input type="password" name="pwd" value="<%=m.getPwd()%>"><br>

		<input type="submit" value="수정">

	</form>
</body>
</html>